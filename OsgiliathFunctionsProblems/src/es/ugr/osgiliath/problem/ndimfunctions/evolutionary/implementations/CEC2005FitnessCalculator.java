package es.ugr.osgiliath.problem.ndimfunctions.evolutionary.implementations;

import java.util.ArrayList;
import java.util.List;

import cec05.benchmark;
import cec05.test_func;
import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.events.EventCreator;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleGene;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.problem.ndimfunctions.CECFunctionProblem;
import es.ugr.osgiliath.problem.ndimfunctions.NdimFunctionProblemParameters;

public class CEC2005FitnessCalculator  extends OsgiliathService implements FitnessCalculator{

	
	test_func cecFunction;
	
	public void initialize(){
		
		benchmark b = new benchmark();
		Integer func_num = (Integer) this.getAlgorithmParameters().getParameter(CECFunctionProblem.CEC_FUNCTION_NUMBER);
		int dimension  = (Integer) this.getAlgorithmParameters().getParameter(NdimFunctionProblemParameters.DIMENSIONS_PROP);
		cecFunction = b.testFunctionFactory(func_num.intValue(), dimension);
		
	}
	
	
	@Override
	public Fitness calculateFitness(Individual ind) {
		//System.out.println("CEC FITNESS CALCULATOR");
		if(cecFunction == null)
			this.initialize();
		boolean toMaximize = false; //TODO CHECK
		Genome gen =  ind.getGenome();
		List<Gene> gens =  ((ListGenome) gen).getGeneList();
		double fv = this.evaluate(gens);
			

		Fitness f = new DoubleFitness(fv,toMaximize);

		
		return f;
		
	}


	@Override
	public ArrayList<Fitness> calculateFitnessForAll(ArrayList<Individual> inds) {
		ArrayList<Fitness> allFitness = new ArrayList<Fitness>();
		for(Individual ind:inds){
			Fitness f = calculateFitness(ind);
			allFitness.add(f);
		}
		
		this.getEventAdmin().sendEvent(EventCreator.createEvaluationsEvent(allFitness.size(), true));
		return allFitness;
	}
	
	public double evaluate(List<Gene> gens){
		double x[] = new double[gens.size()];
		
		int i = 0;
		for(Gene d: gens){
			x[i]=((DoubleGene)d).getValue();
			i++;		
			
		}
		return this.cecFunction.f(x);		
	}


}
