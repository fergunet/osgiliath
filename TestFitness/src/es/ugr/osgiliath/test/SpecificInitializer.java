package es.ugr.osgiliath.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BooleanGene;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.Initializer;
import es.ugr.osgiliath.problem.Problem;
import es.ugr.osgiliath.problem.ProblemParameters;
import es.ugr.osgiliath.problem.binary.BinaryProblem;
import es.ugr.osgiliath.problem.binary.BinaryProblemParameters;
import es.ugr.osgiliath.problem.binary.BinaryProblemRandomInitializer;
import es.ugr.osgiliath.problem.binary.OneMaxFitnessCalculator;
import es.ugr.osgiliath.util.impl.HashMapParameters;


public class SpecificInitializer implements SpecificInitializerInterface{

	Initializer init;

	
	public void activate(){
		System.out.println("ACTIVATING SPECIFIC INITIALIZER");
		AlgorithmParameters params = new HashMapParameters();
		Properties defaultProps = new Properties();
		
		 String size = System.getProperty("size");
		//String size = "4";
		defaultProps.put(BinaryProblemParameters.SIZE_PROP, size);
		
		
		params.setup(defaultProps);

		
		Problem problem = new BinaryProblem();
		ProblemParameters problemParams = (ProblemParameters) params;
		problem.setProblemParameters(problemParams);
		
		init = new BinaryProblemRandomInitializer();
		((BinaryProblemRandomInitializer) init).setAlgorithmParameters(params);
		((BinaryProblemRandomInitializer) init).setProblem(problem);
		
		((BinaryProblemRandomInitializer) init).setFitnessCalculator(new OneMaxFitnessCalculator());
	}
	public ArrayList<BasicIndividualSpecific> initializeIndividuals(int size) {
		System.out.println("CALLING");
		ArrayList<Individual> inds = this.init.initializeIndividuals(size);
		ArrayList<BasicIndividualSpecific> indIm = new ArrayList<BasicIndividualSpecific>();
		
		for(Individual i:inds){
			BasicIndividualSpecific p = new BasicIndividualSpecific();
			
			ListGenomeSpecific lge = new ListGenomeSpecific();
			ArrayList<BooleanGene> genes = new ArrayList<BooleanGene>();
			ListGenome ilg = (ListGenome) i.getGenome();
			for(Gene g:ilg.getGeneList()){
				genes.add((BooleanGene) g);
			}
			
			lge.setSpecificGeneList(genes);
			p.setGenome(lge);
			indIm.add(p);
		}
		return indIm;
	}

}
