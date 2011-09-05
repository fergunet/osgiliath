package es.ugr.osgiliath.problem.ndimfunctions.evolutionary.implementations;

import java.util.List;
import java.util.ArrayList;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleGene;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.problem.ndimfunctions.GriewankFunction;
import es.ugr.osgiliath.problem.ndimfunctions.NdimFunctionSolution;
import es.ugr.osgiliath.problem.ndimfunctions.NdimFunctionValue;

public class GriewankFitnessCalculator extends OsgiliathService implements FitnessCalculator{

	@Override
	public Fitness calculateFitness(Individual ind) {
		
		boolean toMaximize = this.getProblem().getParameters().toMaximize();
		Genome gen =  ind.getGenome();
		
		//There is a separation between an Individual Representation and a Solution representation
		//Maybe the fitness is not the same that the function evaluation, for example
		
		NdimFunctionSolution sol = new NdimFunctionSolution();
		List<Gene> gens =  gen.getGeneList();
		
		List<Double> points = new ArrayList<Double>();
		for(Gene g:gens){
			DoubleGene dg = (DoubleGene) g;
			points.add(new Double(dg.getValue()));
		}
			
		sol.setPoints(points);
		GriewankFunction gf = new GriewankFunction();
		NdimFunctionValue value = gf.evaluate(sol);
		
		Fitness f = new DoubleFitness(value.getValues().get(0),toMaximize);

		
		return f;
		
	}
	
	@Override
	public List<Fitness> calculateFitnessForAll(List<Individual> inds) {
		List<Fitness> allFitness = new ArrayList<Fitness>();
		for(Individual ind:inds){
			Fitness f = calculateFitness(ind);
			allFitness.add(f);
		}
		
		return allFitness;
			
	}


}
