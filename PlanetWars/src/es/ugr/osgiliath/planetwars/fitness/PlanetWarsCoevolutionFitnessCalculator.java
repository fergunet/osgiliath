package es.ugr.osgiliath.planetwars.fitness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.GenericTreeNode;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.StopCriterion;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.planetwars.PlanetWarsParameters;
import es.ugr.osgiliath.planetwars.components.PlanetWarsCoevolutionEvaluationResult;
import es.ugr.osgiliath.evolutionary.basicimplementations.stopcriterions.MaxEvaluationsStopCriterionNoOSGi;
import es.ugr.osgiliath.planetwars.Debug;

public class PlanetWarsCoevolutionFitnessCalculator extends PlanetWarsFitnessCalculator {

	String logFile = "";
	
	int i1;
	int i2;

	int i3;
	int i4;
	

	Debug _d = new Debug();
	
	public PlanetWarsCoevolutionFitnessCalculator(int logFile) {
	super(logFile);
	
	try{
		this.logFile = this.logFile + InetAddress.getLocalHost().getHostName();
	}catch(UnknownHostException ex){
		
	}
	
	this.logFile = this.logFile + logFile;
	}

	@Override
	@Deprecated
	public Fitness calculateFitness(Individual ind) {
		return null;
	}

	public PlanetWarsCoevolutionEvaluationResult calculateFitness(Individual ind1, Individual ind2) {	
		
		String mapList = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.FITNESS_MAPLIST);
		String[] maps = mapList.split(" ");
		Integer runsPerMap = (Integer) this.getAlgorithmParameters()
				.getParameter(PlanetWarsParameters.FITNESS_RUNS_PER_MAP);

		Integer mapsUsedInEvaluation = (Integer) this.getAlgorithmParameters()
				.getParameter(
						PlanetWarsParameters.FITNESS_MAPS_USED_IN_EVALUATION);
		Boolean randomSelection = (Boolean) this.getAlgorithmParameters()
				.getParameter(PlanetWarsParameters.FITNESS_RANDOM_SELECTION);

		PlanetWarsHierarchicalFitness fit = new PlanetWarsHierarchicalFitness(
				0, 0);

		String tree1 = this.writePlanetWarsTree((TreeGenome) ind1.getGenome());
		tree1 = tree1.replace(" ", "@");
		
		String tree2 = this.writePlanetWarsTree((TreeGenome) ind2.getGenome());
		tree2 = tree2.replace(" ", "@");

		System.out.println("BATTLE:" + _d.resumeTree(tree1) + " " + _d.resumeTree(tree2));
		
		if (!randomSelection) {

			for (String map : maps) {
				for (int i = 0; i < runsPerMap; i++) {

					try {
						executeMap(tree1, tree2, map);
						
						if(i1<i2){
							return new PlanetWarsCoevolutionEvaluationResult(ind1, ind2);
						}else if (i1>i2){
							return new PlanetWarsCoevolutionEvaluationResult(ind2, ind1);
						}else{
							return new PlanetWarsCoevolutionEvaluationResult(ind1, ind2);
						}
						
					} catch (Exception ex) {
						System.out.println("ERROR IN FITNESS CALCULATOR");
						ex.printStackTrace();
					}
				}
			}

		}else{
			Random r = new Random();
			String map = maps[r.nextInt(maps.length)];
		
			for( int count_evaluations = 0; count_evaluations < mapsUsedInEvaluation ; count_evaluations++){
				try {
					executeMap(tree1,tree2, map);
					
					if(i1<i2){
						return new PlanetWarsCoevolutionEvaluationResult(ind1, ind2);
					}else if (i1>i2){
						return new PlanetWarsCoevolutionEvaluationResult(ind2, ind1);
					}else{
						return new PlanetWarsCoevolutionEvaluationResult(ind1, ind2);
					}
					
				} catch (Exception ex) {
					System.out.println("ERROR IN FITNESS CALCULATOR");
					ex.printStackTrace();
				}
			}
					
		}
		System.out.println("El fitness del individuo es " + fit);
		return null;
	}

	@Override
	@Deprecated
	public ArrayList<Fitness> calculateFitnessForAll(ArrayList<Individual> inds) {
		ArrayList<Fitness> fits = new ArrayList<Fitness>();

		for (Individual ind : inds) {
			Fitness f = this.calculateFitness(ind);
			fits.add(f);
		}
		return fits;
	}

	public void executeMap(String tree1, String tree2, String map) throws IOException,
			InterruptedException {
		System.out.println("Testeando en mapa " + map);

		String folder = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_FOLDER)
				+ "/";
		// String commandline =
		// "/home/pgarcia/workspace/PlanetWars/environment/launch.sh";

		String commandline = folder + "launch2i.sh " + tree1 + " " + tree2 + " " + map + " "
				+ this.logFile;
		//System.err.println("LAUNCHING " + commandline);
		Process _p = Runtime.getRuntime().exec(commandline);

		// The mistery code of Antares
		InputStreamReader isr = new InputStreamReader(_p.getInputStream());
		BufferedReader br = new BufferedReader(isr);

		String lineRead;
		while ((lineRead = br.readLine()) != null) {
			// System.out.println(lineRead);
		}
		isr.close();
		br.close();
		_p.waitFor();
		_p.destroy();

		File archivo = new File(folder + "error" + this.logFile + ".txt");
		FileReader fr = new FileReader(archivo);
		BufferedReader br2 = new BufferedReader(fr);
		
		String i1_primary_fitness = "", i1_secondary_fitness = "";

		i1_primary_fitness = br2.readLine();
		i1 = Integer.parseInt(i1_primary_fitness);
		i1_secondary_fitness = br2.readLine();
		
		String i2_primary_fitness = "", i2_secondary_fitness = "";

		i2_primary_fitness = br2.readLine();
		i2 = Integer.parseInt(i2_primary_fitness);
		i2_secondary_fitness = br2.readLine();
			
		System.out.println("-> " + i1_primary_fitness + " " + i1_secondary_fitness);
		System.out.println("-> " + i2_primary_fitness + " " + i2_secondary_fitness);

		// In line 3 we have the numbers of turn and in line2 we have the result
		fr.close();
		br2.close();

		//return new PlanetWarsHierarchicalFitness(pfD, sfD);

	}

	public PlanetWarsCoevolutionEvaluationResult calculateFitness(
			Individual ind1, Individual ind2,
			Individual ind3, Individual ind4) {
		// TODO Auto-generated method stub
		
		String mapList = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.FITNESS_MAPLIST);
		String[] maps = mapList.split(" ");
		Integer runsPerMap = (Integer) this.getAlgorithmParameters()
				.getParameter(PlanetWarsParameters.FITNESS_RUNS_PER_MAP);

		Integer mapsUsedInEvaluation = (Integer) this.getAlgorithmParameters()
				.getParameter(
						PlanetWarsParameters.FITNESS_MAPS_USED_IN_EVALUATION);
		Boolean randomSelection = (Boolean) this.getAlgorithmParameters()
				.getParameter(PlanetWarsParameters.FITNESS_RANDOM_SELECTION);

		PlanetWarsHierarchicalFitness fit = new PlanetWarsHierarchicalFitness(
				0, 0);

		String tree1 = this.writePlanetWarsTree((TreeGenome) ind1.getGenome());
		tree1 = tree1.replace(" ", "@");
		
		String tree2 = this.writePlanetWarsTree((TreeGenome) ind2.getGenome());
		tree2 = tree2.replace(" ", "@");

		String tree3 = this.writePlanetWarsTree((TreeGenome) ind3.getGenome());
		tree3 = tree3.replace(" ", "@");
		
		String tree4 = this.writePlanetWarsTree((TreeGenome) ind4.getGenome());
		tree4 = tree4.replace(" ", "@");
		
		System.out.println("BATTLE:" + _d.resumeTree(tree1) + " " + _d.resumeTree(tree2) + " " + _d.resumeTree(tree3) + " " + _d.resumeTree(tree4));
		
		if (!randomSelection) {

			for (String map : maps) {
				for (int i = 0; i < runsPerMap; i++) {

					try {
						executeMap(tree1, tree2,tree3,tree4, map);

						ArrayList<Integer> t = new ArrayList<Integer>(5);
						
						t.add(1, i1);

						t.add(2, i2);

						t.add(3, i3);

						t.add(4, i4);
						
						int posMin = 0;
						int valueMin = 4;
						
						int posMax = 0;
						int valueMax = 0;
						
						for(int a = 1; i<t.size(); a++){
							if(t.get(a)<valueMin){
								posMin = a;
							}else if(t.get(a)> valueMax){
								posMax = a;
							}
							
						}
						
						if(posMin==1){
							if(posMax==2){ return new PlanetWarsCoevolutionEvaluationResult(ind1, ind2);
							}else if(posMax==3){ return new PlanetWarsCoevolutionEvaluationResult(ind1, ind3);
							}else if(posMax==4){ return new PlanetWarsCoevolutionEvaluationResult(ind1, ind4);}
						}else if(posMin==2){
							if(posMax==1){ return new PlanetWarsCoevolutionEvaluationResult(ind2, ind1);
							}else if(posMax==3){ return new PlanetWarsCoevolutionEvaluationResult(ind2, ind3);
							}else if(posMax==4){ return new PlanetWarsCoevolutionEvaluationResult(ind2, ind4);}
						}else if(posMin==3){
							if(posMax==1){ return new PlanetWarsCoevolutionEvaluationResult(ind3, ind1);
							}else if(posMax==2){ return new PlanetWarsCoevolutionEvaluationResult(ind3, ind2);
							}else if(posMax==4){ return new PlanetWarsCoevolutionEvaluationResult(ind3, ind4);}
						}else if(posMin==4){
							if(posMax==1){ return new PlanetWarsCoevolutionEvaluationResult(ind4, ind1);
							}else if(posMax==2){ return new PlanetWarsCoevolutionEvaluationResult(ind4, ind2);
							}else if(posMax==3){ return new PlanetWarsCoevolutionEvaluationResult(ind4, ind3);}
						}
						
						
					} catch (Exception ex) {
						System.out.println("ERROR IN FITNESS CALCULATOR");
						ex.printStackTrace();
					}
				}
			}

		}else{
			Random r = new Random();
			String map = maps[r.nextInt(maps.length)];
		
			for( int count_evaluations = 0; count_evaluations < mapsUsedInEvaluation ; count_evaluations++){
				try {
					executeMap(tree1,tree2,tree3,tree4, map);
					
					if(i1<i2){
						return new PlanetWarsCoevolutionEvaluationResult(ind1, ind2);
					}else if (i1>i2){
						return new PlanetWarsCoevolutionEvaluationResult(ind2, ind1);
					}else{
						return new PlanetWarsCoevolutionEvaluationResult(ind1, ind2);
					}
					
				} catch (Exception ex) {
					System.out.println("ERROR IN FITNESS CALCULATOR");
					ex.printStackTrace();
				}
			}
					
		}
		System.out.println("El fitness del individuo es " + fit);
		return null;
	}

	private void executeMap(String tree1, String tree2, String tree3,
			String tree4, String map) throws IOException, InterruptedException {
		System.out.println("Testeando en mapa " + map);

		String folder = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_FOLDER)
				+ "/";
		// String commandline =
		// "/home/pgarcia/workspace/PlanetWars/environment/launch.sh";

		String commandline = folder + "launch4i.sh " + tree1 + " " + tree2 + " " + tree3 + " " + tree4 + " " + map + " "
				+ this.logFile;
		System.err.println("LAUNCHING " + commandline);
		Process _p = Runtime.getRuntime().exec(commandline);

		// The mistery code of Antares
		InputStreamReader isr = new InputStreamReader(_p.getInputStream());
		BufferedReader br = new BufferedReader(isr);

		String lineRead;
		while ((lineRead = br.readLine()) != null) {
			// System.out.println(lineRead);
		}
		isr.close();
		br.close();
		_p.waitFor();
		_p.destroy();

		File archivo = new File(folder + "error" + this.logFile + ".txt");
		FileReader fr = new FileReader(archivo);
		BufferedReader br2 = new BufferedReader(fr);
		
		String i1_primary_fitness = "", i1_secondary_fitness = "";

		i1_primary_fitness = br2.readLine();
		i1 = Integer.parseInt(i1_primary_fitness);
		i1_secondary_fitness = br2.readLine();
		
		String i2_primary_fitness = "", i2_secondary_fitness = "";

		i2_primary_fitness = br2.readLine();
		i2 = Integer.parseInt(i2_primary_fitness);
		i2_secondary_fitness = br2.readLine();

		String i3_primary_fitness = "", i3_secondary_fitness = "";

		i3_primary_fitness = br2.readLine();
		i3 = Integer.parseInt(i3_primary_fitness);
		i3_secondary_fitness = br2.readLine();

		String i4_primary_fitness = "", i4_secondary_fitness = "";

		i4_primary_fitness = br2.readLine();
		i4 = Integer.parseInt(i4_primary_fitness);
		i4_secondary_fitness = br2.readLine();

		
		
		System.out.println("-> " + i1_primary_fitness + " " + i1_secondary_fitness);
		System.out.println("-> " + i2_primary_fitness + " " + i2_secondary_fitness);

		System.out.println("-> " + i3_primary_fitness + " " + i3_secondary_fitness);
		System.out.println("-> " + i4_primary_fitness + " " + i4_secondary_fitness);

		// In line 3 we have the numbers of turn and in line2 we have the result
		fr.close();
		br2.close();

		//return new PlanetWarsHierarchicalFitness(pfD, sfD);		
	}

}
