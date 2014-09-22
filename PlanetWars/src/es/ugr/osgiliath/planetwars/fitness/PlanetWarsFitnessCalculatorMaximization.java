package es.ugr.osgiliath.planetwars.fitness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.planetwars.PlanetWarsParameters;

public class PlanetWarsFitnessCalculatorMaximization extends
		PlanetWarsFitnessCalculator {

	public PlanetWarsFitnessCalculatorMaximization(int logFile) {
		super(logFile);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public Fitness calculateFitness(Individual ind) {
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

		PlanetWarsHierarchicalFitnessMaximization fit = new PlanetWarsHierarchicalFitnessMaximization(
				0, 0);

		String tree = this.writePlanetWarsTree((TreeGenome) ind.getGenome());
		tree = tree.replace(" ", "@");

		if (!randomSelection) {

			for (String map : maps) {
				for (int i = 0; i < runsPerMap; i++) {

					try {
						PlanetWarsHierarchicalFitnessMaximization one = (PlanetWarsHierarchicalFitnessMaximization) this
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

		} else {
			Random r = new Random();
			String map = maps[r.nextInt(maps.length)];

			for (int count_evaluations = 0; count_evaluations < mapsUsedInEvaluation; count_evaluations++) {
				try {
					PlanetWarsHierarchicalFitnessMaximization one = (PlanetWarsHierarchicalFitnessMaximization) this
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
	public Fitness executeMap(String tree, String map) throws IOException,
			InterruptedException {
		System.out.println("Testeando en mapa " + map);

		String folder = (String) this.getAlgorithmParameters().getParameter(
				PlanetWarsParameters.ENVIRONMENT_FOLDER)
				+ "/";
		// String commandline =
		// "/home/pgarcia/workspace/PlanetWars/environment/launch.sh";
		String commandline = folder + "launch.sh " + tree + " " + map + " "
				+ this.logFile;
		System.err.println("--> " + commandline);
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
		 * PlanetWarsHierarchicalFitnessMaximization(0,turnInt); } else { return
		 * new PlanetWarsHierarchicalFitnessMaximization(1,0); }
		 */
	}

}
