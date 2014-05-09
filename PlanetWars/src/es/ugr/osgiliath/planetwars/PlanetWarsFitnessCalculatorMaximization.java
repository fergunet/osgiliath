package es.ugr.osgiliath.planetwars;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class PlanetWarsFitnessCalculatorMaximization extends PlanetWarsFitnessCalculator {

	public PlanetWarsFitnessCalculatorMaximization(int logFile) {
		super(logFile);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fitness calculateFitness(Individual ind) {
		BasicIndividual individual = (BasicIndividual) ind;

		int numWins = 0;
		int numTurns = 0;

		String mapList = (String) this.getAlgorithmParameters().getParameter(PlanetWarsParameters.FITNESS_MAPLIST);
		String[] maps = mapList.split(" ");
		Integer runsPerMap = (Integer) this.getAlgorithmParameters().getParameter(PlanetWarsParameters.FITNESS_RUNS_PER_MAP);

		PlanetWarsHierarchicalFitnessMaximization fit = new PlanetWarsHierarchicalFitnessMaximization(0, 0);
	
		
		String tree = this.writePlanetWarsTree((TreeGenome)ind.getGenome());
		tree = tree.replace(" ", "@");

		for(String map:maps){
			for(int i = 0; i < runsPerMap; i++){

				try{
					PlanetWarsHierarchicalFitness one = (PlanetWarsHierarchicalFitness) this.executeMap(tree, map);
					System.out.println(one);
					fit.setTotalTurns(fit.getTotalTurns()+one.getTotalTurns());
					fit.setTotalWins(fit.getTotalWins()+one.getTotalWins());
				}catch(Exception ex){
					System.out.println("ERROR IN FITNESS CALCULATOR");
					ex.printStackTrace();
				}
			}
		}
		System.out.println("El fitness del individuo es "+fit);
		return fit;
	}


}
