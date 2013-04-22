package es.ugr.osgiliart;

import java.util.ArrayList;

import es.ugr.osgiliart.core.generators.PathGenerator;
import es.ugr.osgiliart.core.generators.point.RandomPointGenerator;
import es.ugr.osgiliart.core.rand.RandU;
import es.ugr.osgiliart.primitives.Primitive;
import es.ugr.osgiliart.primitives.patch.generators.PatchGenerator;
import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.Initializer;

public class ArtisticCollageInitializer extends OsgiliathService implements Initializer{
	
	FitnessCalculator fitnessCalculator;
		

	@Override
	public ArrayList<Individual> initializeIndividuals(int size) {
		ArrayList<Individual> individuals = new ArrayList<Individual>();		
		for ( int i = 0; i < size; ++i) {
			individuals.add( newIndividual() );
		}
		
		/*
		 * Calculate fitness for all 
		 */
		int id = 0;
		for ( Individual individual: individuals ) {		
			((ArtisticIndividual) individual).setGeneration(0);
			((ArtisticIndividual) individual).setUniqId(id++);
			individual.setFitness( fitnessCalculator.calculateFitness(individual) );
		}
		return individuals;
	}
	
	public void setFitnessCalculator(FitnessCalculator fitnessCalculator){
		this.fitnessCalculator = fitnessCalculator;
	}
	
	public void unsetFitnessCalculator(FitnessCalculator fitnessCalculator){
		this.fitnessCalculator = null;
	}
	

	protected ArtisticIndividual newIndividual () {		
		ArtisticIndividual individual = new ArtisticIndividual();
		ArtisticGenome genome = new ArtisticGenome();
		ArrayList<Primitive> primitives = new ArrayList<Primitive>();
		
		/* Path generators */
	
		RandU randPoint = new RandU();		
		RandomPointGenerator pointGenerator = new RandomPointGenerator(randPoint);
		String patchesFolder = (String) this.getAlgorithmParameters().getParameter(ArtisticParameters.PATCHES_FOLDER);
		PathGenerator pathGenerator = new PathGenerator(patchesFolder);
		PatchGenerator patchGenerator = new PatchGenerator(pointGenerator,pathGenerator);

		int N = (Integer) this.getAlgorithmParameters().getParameter(ArtisticParameters.GENOME_SIZE);
		for ( int i = 0; i < N; ++i ) {
			primitives.add( patchGenerator.generate() );
		}
		
		genome.setPrimitives(primitives);
		individual.setGenome(genome);		
		return individual;
		
	}

}
