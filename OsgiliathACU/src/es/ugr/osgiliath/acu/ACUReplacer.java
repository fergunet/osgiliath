package es.ugr.osgiliath.acu;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Properties;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.events.EventCreator;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.Replacer;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.migrator.Migrator;
import es.ugr.osgiliath.utils.Logger;

public class ACUReplacer extends OsgiliathService implements  Replacer{

	Migrator migrator;
	Logger log;
	int bank;
	
	@Override
	public  void select(Population pop, ArrayList<Individual> parents,
			ArrayList<Individual> offspring,
			ArrayList<Individual> mutatedOffspring) {

		
		
		////////////////////////// REWARDING
		int reward = rewardingAverage(offspring, pop);
		
		///////////////////////// ADDING
		pop.addIndividuals(offspring);


		//////////////////////DELETION
		int removed = offspring.size();
		removeWorstIndividuals(removed, pop);
		//System.out.println("REPLACER: DELETED INDIVIDUALS "+removed);

		/////////////////////MIGRATION


		List<Individual> popinds = pop.getAllIndividuals(); //TODO decidir si esto es referencia o no (mejor sería no)

		ArrayList<Individual> toSend = new ArrayList<Individual>();

		String migrationType = (String) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.MIGRATION_TYPE);
		
		if(migrationType.equals("ACU")){
			for(Individual ind:popinds){
				MetaACUIndividual mind = (MetaACUIndividual) ind;
				int acusPerMigration = (Integer) this.getAlgorithmParameters().getParameter(ACUParameters.ACU_MIGRATION_PRIZE);
				if((mind.getACUs()>acusPerMigration) ){// && mind.getMigrationProb()>0.5){
					toSend.add(mind); //clone
					mind.decreaseACUs(acusPerMigration);
				}

			}
		}
		
		if(migrationType.equals(EvolutionaryBasicParameters.BEST)){
			int indsToMigrate = (Integer) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.MIGRATION_SIZE);
			toSend = pop.getNBestIndividuals(indsToMigrate);
		}
		
		if(migrationType.equals(EvolutionaryBasicParameters.RANDOM)){
			int indsToMigrate = (Integer) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.MIGRATION_SIZE);
			for(int i = 0; i<indsToMigrate;i++)
				toSend.add(pop.getRandomIndividual());
		}
		

		//TODO RESETEAR ACUs o mantenerlos??
		migrator.sendLOCAL(toSend); //POR AHORA CONSERVAN LOS ACUS


		////////////////////// RECEPTION
		ArrayList<Individual> received = migrator.readLOCAL();
		int receivedSize = received.size();
		if(receivedSize>0){
			for(int z = 0; z<received.size(); z++){
				MetaACUIndividual mind = (MetaACUIndividual) received.get(z);///null??
				
				if(mind == null){
					System.out.println("AQUI HAY UN POBLEMA");
					System.out.println("Received size" +received.size());
					System.out.println(received.toString());
				}
				mind.setFathers(null);
			}
			pop.addIndividuals(received);
			removeWorstIndividuals(receivedSize, pop);	
		}


		/////////////////////  ANALYSIS
		this.analyzeInformation(pop, received, toSend,reward);


	}

	private void analyzeInformation(Population pop, ArrayList<Individual> received, ArrayList<Individual> sent, int reward){

		List<Individual> all = pop.getAllIndividuals();

		//All
		Fitness bestFitness = all.get(0).getFitness();
		Fitness total = all.get(0).getFitness();
		int foreigns = 0;
		int overAverage = 0;
		
		//SENT AND RECEIVED
		String bestFitnessSentStr = "";
		String avgFitnessSentStr = "";
		String bestFitnessReceivedStr = "";
		String avgFitnessReceivedStr = "";
		
		MetaACUIndividual first = (MetaACUIndividual)all.get(0);
		
		double totalMigrationProb = first.getMigrationProb();
		int totalACUs = first.getACUs();
		int ACUs_best = totalACUs;
		int max_ACUs = first.getACUs();

		
		if(! (first.getIslandId().equals(this.getFrameworkId()) )){
			foreigns++;
			
		}

		/////////////TOTAL 
		for(int i = 1; i<all.size(); i++){
			MetaACUIndividual ind = (MetaACUIndividual) all.get(i);
			//System.out.println(ind.getFitness()+" < "+bestFitness);
			if(ind.getFitness().compareTo(bestFitness)< -1){
				bestFitness = ind.getFitness(); //TODO mejor el pop.getNBestIndividuals();
				ACUs_best = ind.getACUs();
			}
			
			if(ind.getACUs()>max_ACUs)
				max_ACUs = ind.getACUs();
			
			if(!(ind.getIslandId().equals(this.getFrameworkId())))
				foreigns++;
			total = total.add(ind.getFitness());
			totalACUs += ind.getACUs();
			totalMigrationProb += ind.getMigrationProb();
			
		}
		
		Fitness avgFitness = total.divide(all.size());
		double avgACU = ((double)totalACUs)/all.size();
		double avgMigration = totalMigrationProb/all.size();
		double percForeigns = ((double)foreigns)/all.size();
		DecimalFormat num = new DecimalFormat("####.00000000");
		String avgACUstr = num.format(avgACU);
		String avgMigrationStr = num.format(avgMigration);
		String percForeignsStr = num.format(percForeigns);
		//String info = avgACUstr+";"+avgMigrationStr+";"+percForeignsStr;

		//SENDING EVENTS
		/*this.getEventAdmin().sendEvent(EventCreator.createAverageFitnessEvent(avg.toString(), true));
		this.getEventAdmin().sendEvent(EventCreator.createMaxFitnessEvent(bestFitness.toString(), true));
		this.getEventAdmin().sendEvent(EventCreator.createGenericEvent(in	avgReceived;fo, true));
		 */
		////////////SENT
		if(sent.size()>0){
			Fitness bestFitnessSent = sent.get(0).getFitness();
			Fitness totalSent = sent.get(0).getFitness();
			
			for(int i = 1; i<sent.size(); i++){
				Individual ind = sent.get(i);
				//System.out.println(ind.getFitness()+" < "+bestFitness);
				if(ind.getFitness().compareTo(bestFitnessSent)< -1)
					bestFitnessSent = ind.getFitness(); //TODO mejor el pop.getNBestIndividuals();
				totalSent = totalSent.add(ind.getFitness());

			}
			Fitness avgFitnessSent = totalSent.divide(sent.size());
			avgFitnessSentStr = avgFitnessSent.toString();
			bestFitnessSentStr = bestFitnessSent.toString();
			//this.getEventAdmin().sendEvent(EventCreator.createNewMigrationSendEvent(sent.size(), bestFitnessSent.toString(), avgSent.toString(), true));
		}//else this.getEventAdmin().sendEvent(EventCreator.createNewMigrationSendEvent(sent.size(), "", "", true));
		
			
		///////////RECEIVED
		if(received.size()>0){
			Fitness bestFitnessReceived = received.get(0).getFitness();
			Fitness totalReceived = received.get(0).getFitness();
		
			for(int i = 1; i<received.size(); i++){
				Individual ind = received.get(i);
				//System.out.println(ind.getFitness()+" < "+bestFitness);
				if(ind.getFitness().compareTo(bestFitnessReceived)< -1)
					bestFitnessReceived = ind.getFitness(); //TODO mejor el pop.getNBestIndividuals();
				totalReceived = totalReceived.add(ind.getFitness());

			}
			Fitness avgFitnessReceived = totalReceived.divide(received.size());
			avgFitnessReceivedStr = avgFitnessReceived.toString();
			bestFitnessReceivedStr = bestFitnessReceived.toString();
			//this.getEventAdmin().sendEvent(EventCreator.createNewMigrationReceiveEvent(received.size(), bestFitnessReceived.toString(),avgReceived.toString(), true));
		}//else this.getEventAdmin().sendEvent(EventCreator.createNewMigrationReceiveEvent(received.size(), "","", true));

		
		log.stats(bestFitness.toString()+";"+
				avgFitness.toString()+";"+
				bestFitnessSentStr+";"+
				avgFitnessSentStr+";"+
				bestFitnessReceivedStr+";"+
				avgFitnessReceivedStr+";"+
				avgACUstr+";"+
				max_ACUs+";" +
				ACUs_best+";"+
				totalACUs+";"+
				avgMigrationStr+";"+
				percForeignsStr+";"+
				sent.size()+";"+
				received.size()+";" +
				reward+
				"\n");




	}
	
	private int rewardingAverage(List<Individual> offspring, Population pop){
		
		int totalAcus = 0;
		
		List<Individual> popinds = pop.getAllIndividuals();

		Fitness total = popinds.get(0).getFitness(); //ESTO ES REGULAR
		for(int i = 1; i<popinds.size();i++){
			//System.out.println(popinds.get(i));
			total = total.add(popinds.get(i).getFitness());
		}

		Fitness avg = total.divide(pop.getSize());

       //System.out.println("-->"+avg);

		for(Individual ind:offspring){
			MetaACUIndividual mind = (MetaACUIndividual) ind;

			//TODO references (esto es regular)
			if(mind.getFitness().compareTo(avg) == -1){
				
				for(MetaACUIndividual father:mind.getFathers()){
					int reward = (Integer)this.getAlgorithmParameters().getParameter(ACUParameters.ACU_REWARD);
					//if(bank>reward){
					totalAcus += reward;
						father.increaseACUs(reward);
					//	bank-=reward;
					//}
					//System.out.println("REWARDED WITH "+reward);

				}

			}
			mind.setFathers(null); //release memory

		}
		return totalAcus;
	}

	private void removeIndividualsWithoutACUs(Population pop){
		//remove individuals without ACUs	
		/*		ArrayList<Individual> all = pop.getAllIndividuals();
				ArrayList<Individual> toRemove = new ArrayList<Individual>();

				System.out.println("HAY "+all.size());
				for(Individual ind:all){
					MetaACUIndividual mind = (MetaACUIndividual) ind;
					/////////////////
					//TEST ELIMINAR ACUS al AZAR

						mind = (MetaACUIndividual) ind;
						double v = Math.random()*10;
						int numACUs = (int) v;
						mind.decreaseACUs(numACUs);
					/////////////
					if(mind.getACUs()<=0)
						toRemove.add(mind);
				}
				System.out.println("BORRO"+toRemove.size());
				pop.removeIndividuals(toRemove);
				System.out.println("Quedan "+pop.getSize());

		 */
	}

	//TODO This should be a service
	private void removeWorstIndividuals(int indstoremove, Population pop){
		List<Individual> worst = pop.getNWorstIndividuals(indstoremove);


		for(Individual i:worst)
			pop.removeIndividual(i);
	}

	public void setMigrator(Migrator mig){
		this.migrator = mig;
	}

	public void unsetMigrator(Migrator mig){
		this.migrator = null;
	}

	@Override
	public void reset() {
		this.migrator.reset();
		this.bank = (Integer)this.getAlgorithmParameters().getParameter(ACUParameters.ACU_BANK);
	}
	
	//TODO in OSGiliathService?
	public void setLogger(Logger log){
		this.log = log;
	}
	
	public void unsetLoggeR(Logger log){
		this.log = null;
	}


}
