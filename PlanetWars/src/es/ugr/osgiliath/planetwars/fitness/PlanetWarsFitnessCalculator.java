package es.ugr.osgiliath.planetwars.fitness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import es.ugr.osgiliath.evolutionary.basicimplementations.stopcriterions.MaxEvaluationsStopCriterionNoOSGi;

public class PlanetWarsFitnessCalculator extends OsgiliathService implements
		FitnessCalculator{

	int logFile = 0;

	public PlanetWarsFitnessCalculator(int logFile) {
		this.logFile = logFile;
	}


	@Override
	public Fitness calculateFitness(Individual ind) {
		BasicIndividual individual = (BasicIndividual) ind;

		double fitness_primary = 0;
		double fitness_secondary = 0;

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

		String tree = this.writePlanetWarsTree((TreeGenome) ind.getGenome());
		tree = tree.replace(" ", "@");

		if (!randomSelection) {

			for (String map : maps) {
				for (int i = 0; i < runsPerMap; i++) {

					try {
						PlanetWarsHierarchicalFitness one = (PlanetWarsHierarchicalFitness) this
								.executeMap(tree, map);
						System.out.println(one);
						
						fit.setSecondaryFitness(fit.getSecondaryFitness()
								+ one.getSecondaryFitness());
						fit.setPrimaryFitness(fit.getPrimaryFitness()
								+ one.getPrimaryFitness());
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
					PlanetWarsHierarchicalFitness one = (PlanetWarsHierarchicalFitness) this
							.executeMap(tree, map);
					System.out.println(one);
					
					fit.setSecondaryFitness(fit.getSecondaryFitness()
							+ one.getSecondaryFitness());
					fit.setPrimaryFitness(fit.getPrimaryFitness()
							+ one.getPrimaryFitness());
				} catch (Exception ex) {
					System.out.println("ERROR IN FITNESS CALCULATOR");
					ex.printStackTrace();
				}
			}
			
		
			
			
			
		}
		System.out.println("El fitness del individuo es " + fit);
		return fit;
	}

	@Override
	public ArrayList<Fitness> calculateFitnessForAll(ArrayList<Individual> inds) {
		ArrayList<Fitness> fits = new ArrayList<Fitness>();

		for (Individual ind : inds) {
			Fitness f = this.calculateFitness(ind);
			fits.add(f);
		}
		return fits;

	}

	public String writePlanetWarsTree(TreeGenome tree) {
		String theTree = "";

		// theTree = writePlanetWarsTreeAux(tree.getRoot(),1); //INDENTED
		theTree = writePlanetWarsTreeAux(tree.getRoot());

		return theTree;
	}

	public String writePlanetWarsTreeIndented(TreeGenome tree) {
		String theTree = "";

		theTree = writePlanetWarsTreeAux(tree.getRoot(), 1); // INDENTED
		// theTree = writePlanetWarsTreeAux(tree.getRoot());

		return theTree;

	}

	private String writePlanetWarsTreeAux(GenericTreeNode node, int indent) {
		String str = "";
		String indentStr = "";
		for (int i = 0; i < indent; i++) {
			indentStr = indentStr + " ";
		}
		str = indentStr + node.getData().toString() + "\n";

		if (node.getChildren().size() == 2) {
			str = str + writePlanetWarsTreeAux(node.getChildAt(0), indent + 1);
			str = str + indentStr + "else \n"
					+ writePlanetWarsTreeAux(node.getChildAt(1), indent + 1);
		}
		if (node.getChildren().size() == 1 || node.getChildren().size() > 2) {
			System.out.println("ERROR: NUMERO DE HIJOS ERRONEO!!! "
					+ node.getNumberOfChildren());
		}
		return str;

	}

	private String writePlanetWarsTreeAux(GenericTreeNode node) {
		String str = "";
		String indentStr = "";
		str = indentStr + node.getData().toString();

		if (node.getChildren().size() == 2) {
			str = str + writePlanetWarsTreeAux(node.getChildAt(0));
			str = str + indentStr + "else "
					+ writePlanetWarsTreeAux(node.getChildAt(1));
		}
		if (node.getChildren().size() == 1 || node.getChildren().size() > 2) {
			System.out.println("ERROR: NUMERO DE HIJOS ERRONEO!!! "
					+ node.getNumberOfChildren());
		}
		return str;

	}

	public String[] generateString(String tree, String map) {
		String folder = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_FOLDER)
				+ "/";
		String mapFolder = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_MAPS_FOLDER)
				+ "/";
		// Added the slash to the folder

		String simulator = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_SIMULATOR);
		Integer turns = (Integer) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_TURNS);
		Integer time = (Integer) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_TIME);
		String filelog = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_FILELOG);
		String agentJar = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_AGENT_JAR);
		String enemyJar = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_ENEMY_JAR);

		String agent = "java -jar " + folder + agentJar + " " + tree + " ";
		String enemyParameters = (String) this
				.getAlgorithmParameters()
				.getParameter(PlanetWarsParameters.ENVIRONMENT_ENEMY_PARAMETERS);
		String enemy = "java -jar " + folder + enemyJar + " " + enemyParameters;

		ArrayList<String> all = new ArrayList<String>();
		all.add("java");
		all.add("-jar");
		all.add(folder + simulator);
		all.add(folder + mapFolder + map);
		all.add(time.toString());
		all.add(turns.toString());
		all.add(folder + filelog);
		all.add(agent);
		all.add(enemy);

		return all.toArray(new String[0]);
	}

	public String[] generateStringScript(String tree, String map) {
		String folder = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_FOLDER)
				+ "/";
		String mapFolder = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_MAPS_FOLDER)
				+ "/";
		// Added the slash to the folder

		String simulator = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_SIMULATOR);
		Integer turns = (Integer) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_TURNS);
		Integer time = (Integer) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_TIME);
		String filelog = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_FILELOG);
		String agentJar = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_AGENT_JAR);
		String enemyJar = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_ENEMY_JAR);

		String agent = "java -jar " + folder + agentJar + " " + tree + " "
				+ this.logFile;
		String enemyParameters = (String) this
				.getAlgorithmParameters()
				.getParameter(PlanetWarsParameters.ENVIRONMENT_ENEMY_PARAMETERS);
		String enemy = "java -jar " + folder + enemyJar + " " + enemyParameters;

		ArrayList<String> all = new ArrayList<String>();
		all.add(folder + "launch.sh");

		return all.toArray(new String[0]);
	}

	public Fitness executeMapMAL(String tree, String map) throws IOException,
			InterruptedException {
		System.out.println("Testeando en mapa " + map);

		/*
		 * String line = this.generateLaunchScript(tree,map);
		 * System.out.println(line); Process _p =
		 * Runtime.getRuntime().exec(line);
		 */

		String[] cmd = this.generateString(tree, map);
		ArrayList<String> cmds = new ArrayList<String>();
		for (String part : cmd) {
			System.out.println(part + " ");
			cmds.add(part);
		}

		ProcessBuilder pb = new ProcessBuilder(cmds);
		Process _p = pb.start();

		// The mistery code of Antares
		InputStreamReader isr = new InputStreamReader(_p.getInputStream());
		BufferedReader br = new BufferedReader(isr);

		// /CLEAR
		String lineRead;
		while ((lineRead = br.readLine()) != null) {

		}

		InputStreamReader isr2 = new InputStreamReader(_p.getErrorStream());
		BufferedReader br2 = new BufferedReader(isr2);
		String linea, winner = "", turn = "";
		while ((linea = br2.readLine()) != null) {
			System.out.println(linea);
			turn = winner;
			if (winner != "Draw!") {
				winner = linea;
			} else {
				br2.readLine();
				winner = linea;
			}

		}

		isr.close();
		br.close();
		_p.waitFor();
		_p.destroy();
		String line;

		int turnInt = Integer.parseInt(turn.split(" ")[1]);
		// In line 3 we have the numbers of turn and in line2 we have the result
		isr2.close();
		br2.close();
		if (winner.charAt(7) == '2') {
			return new PlanetWarsHierarchicalFitness(0, turnInt);
		} else {
			return new PlanetWarsHierarchicalFitness(1, 0);
		}

		// return new PlanetWarsHierarchicalFitness(0,1);
	}

	public Fitness executeMap(String tree, String map) throws IOException,
			InterruptedException {
		System.out.println("Testeando en mapa " + map);

		/*
		 * String line = this.generateLaunchScript(tree,map);
		 * System.out.println(line); Process _p =
		 * Runtime.getRuntime().exec(line);
		 */

		/*
		 * String[] cmd = this.generateString(tree, map); ArrayList<String> cmds
		 * = new ArrayList<String>(); for(String part:cmd){
		 * System.out.println(part+" "); cmds.add(part); }
		 */

		String folder = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_FOLDER)
				+ "/";
		// String commandline =
		// "/home/pgarcia/workspace/PlanetWars/environment/launch.sh";

		String commandline = folder + "launch.sh " + tree + " " + map + " "
				+ this.logFile;
		System.out.println("LAUNCHING " + commandline);
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
		String line;

		// ProcessBuilder pb = new ProcessBuilder(cmds);
		// Process _p = pb.start();

		File archivo = new File(folder + "error" + this.logFile + ".txt");
		FileReader fr = new FileReader(archivo);
		BufferedReader br2 = new BufferedReader(fr);
		String primary_fitness = "", secondary_fitness = "";

		primary_fitness = br2.readLine();
		secondary_fitness = br2.readLine();

		System.err.println("-> " + primary_fitness + " " + secondary_fitness);


		double pfD = Double.parseDouble(primary_fitness);
		double sfD = Double.parseDouble(secondary_fitness);

		// In line 3 we have the numbers of turn and in line2 we have the result
		fr.close();
		br2.close();

		return new PlanetWarsHierarchicalFitnessMaximization(pfD, sfD);
		/*
		 * if (winner.charAt(7) == '2') { return new
		 * PlanetWarsHierarchicalFitness(0,turnInt); } else { return new
		 * PlanetWarsHierarchicalFitness(1,0); }
		 */
	}
}
