package es.ugr.osgiliath.problem.ndimfunctions.evolutionary.implementations;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleGene;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.Mutator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class NdimFunctionRandomMutator extends OsgiliathService implements Mutator{


	FitnessCalculator fitnessCalculator;

	@Override
	public List<Individual> mutate(List<Individual> individuals) {
		List<Individual> mutated = new ArrayList<Individual>();
		for(Individual ind: individuals){
			ListGenome gen = (ListGenome) ind.getGenome();
			EvolutionaryBasicParameters params = (EvolutionaryBasicParameters)this.getAlgorithmParameters();
			double prob = params.getMutationProb();
			double rand = Math.random();
			if(rand<prob){
				//System.out.println("MUT ANTES:"+ind);
				int position = (int) Math.random()*gen.getGeneList().size();
				DoubleGene g = (DoubleGene) gen.getGeneList().get(position);
				double stepSize = Math.random()*params.getMutationProb(); //TODO ojo con esto
				if(Math.random()>0.5)
					stepSize *=-1;
				g.setValue(g.getValue()+stepSize);
				Fitness f = fitnessCalculator.calculateFitness(ind);
				ind.setFitness(f);
				//System.out.println("MUT DESPS:"+ind);
				mutated.add(ind);
				
			}
		}
		return mutated;
	}
	
	public  void setFitnessCalculator(FitnessCalculator f){
		this.fitnessCalculator = f;
	}
	
	public  void unsetFitnessCalculator(FitnessCalculator f){
		this.fitnessCalculator = null;
	}

}
