package es.ugr.osgiliath.distribution.utils;

import java.util.ArrayList;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.events.EventCreator;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.migrator.Migrator;

public class MigrationRingBuffer extends OsgiliathService implements Migrator {

	ArrayList<Individual> buffer;
	ArrayList<Migrator> migrators;
	int id;
	
	/**
	 * This method sends the individuals to the migrator whose ID is immediately larger.
	 */
	@Override
	public void sendLOCAL(ArrayList<Individual> inds) {
		
		if(migrators.size() == 0)
			return;
		
		int destination = migrators.get(0).getId();
		int pos = 0;
		int min = destination;
		int max = destination;
		
		for(int i = 1; i<migrators.size();i++){
			Migrator m = migrators.get(i);
			int actual = m.getId();
			if(actual > this.id && actual<destination){
				destination = actual;
				pos = i;
			}
		}
		
		if(this.id == max)
			destination = min;
	
		
		
		//Send the individuals
		migrators.get(pos).sendREMOTE(inds);
		//System.out.println("ENVIADO "+inds.size()+" DESDE "+this.id+" A "+destination);
		
		/*int numSent = inds.size();
		if(this.getEventAdmin() != null)
			this.getEventAdmin().sendEvent(EventCreator.createNewMigrationSendEvent(numSent, true));
		*/
	}
	
	public void activate(){
		this.id = Integer.parseInt(this.getFrameworkId());
		//System.out.println("MIGRATOR ACTIVATED: "+this.id);
		this.buffer = new ArrayList<Individual>();
		this.migrators = new ArrayList<Migrator>();
	}

	/**
	 * This function READS the local buffer and CLEARS the buffer
	 */
	@Override
	public ArrayList<Individual> readLOCAL() {
		ArrayList<Individual> inds = new ArrayList<Individual>();
		inds.addAll(this.buffer);
		this.buffer.clear();
		return inds;
	}
	
	public void addMigrator(Migrator mig){
		System.out.println("--------------MIGRATOR ADDED FROM "+mig.getId());
		migrators.add(mig);
		
	}
	
	public void removeMigrator(Migrator mig){
		migrators.remove(mig);
		
	}
	
	public void reset(){
		this.buffer.clear();
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void sendREMOTE(ArrayList<Individual> inds) {
		//System.out.println("RECIBI "+inds.size());
		this.buffer.addAll(inds);
		
		
	}

	/*@Override
	public void handleEvent(Event arg0) {
		this.reset();
		System.out.println("MIGRATOR RESET");
		
	}*/

}
