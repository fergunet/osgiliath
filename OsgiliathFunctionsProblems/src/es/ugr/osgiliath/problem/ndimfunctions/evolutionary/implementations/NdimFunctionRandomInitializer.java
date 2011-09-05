package es.ugr.osgiliath.problem.ndimfunctions.evolutionary.implementations;




import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleGene;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.Initializer;
import es.ugr.osgiliath.problem.ndimfunctions.NdimFunctionProblemParameters;

public class NdimFunctionRandomInitializer extends OsgiliathService implements Initializer {

	FitnessCalculator fitnessCalculator;
	
	@Override
	public Individual initializeIndividual() {
		BasicIndividual ind = new BasicIndividual();
		ListGenome genome = new ListGenome();
		
		
		NdimFunctionProblemParameters problemParameters = (NdimFunctionProblemParameters) this.getProblem().getParameters();
		for(int i=0; i<problemParameters.getDimensions();i++){
			double d = Math.random();//TODO cambia esto
			Gene g = new DoubleGene(d);
			genome.getGeneList().add(g);
			
		}
		ind.setGenome(genome);
		Fitness f = fitnessCalculator.calculateFitness(ind);
		ind.setFitness(f);
		System.out.println(ind);
		return ind;
	}
	
	//BIND/UNBIND FITNESS CALCULATOR
	public void setFitnessCalculator(FitnessCalculator fitC){
		this.fitnessCalculator = fitC;
	}

	public void unsetFitnessCalculator(FitnessCalculator fitc){
		this.fitnessCalculator = null;
	}
}
