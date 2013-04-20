package es.ugr.osgiliart.collage;

import java.util.ArrayList;

import es.ugr.osgiliart.ArtisticGenome;
import es.ugr.osgiliart.ArtisticParameters;
import es.ugr.osgiliart.core.generators.PathGenerator;
import es.ugr.osgiliart.core.generators.point.RandomPointGenerator;
import es.ugr.osgiliart.core.rand.RandU;
import es.ugr.osgiliart.primitives.Primitive;
import es.ugr.osgiliart.primitives.basic.Circle;
import es.ugr.osgiliart.primitives.patch.Patch;
import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.Mutation;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class CollageMutation extends OsgiliathService  implements Mutation {

	private PathGenerator pathG;
	private RandomPointGenerator ranPointG = new RandomPointGenerator(
			new RandU(),
			new RandU()
			);
	
	
	@Override
	public Genome mutate(Genome genome) {		
		double prob = (Double) this.getAlgorithmParameters().getParameter(ArtisticParameters.IMAGE_MUTATION_PROB);		
		ArtisticGenome artisticGenome = (ArtisticGenome) genome;
		ArrayList<Primitive> primitives = artisticGenome.getPrimitives();		
		for ( Primitive primitive: primitives ) {
			if ( Math.random() <= prob ) {
				mutate(primitive);
			} 
		}						
		return genome;
	}

	/**
	 * Simple mutation 
	 */	
	protected Primitive mutate (Primitive primitive ) {
		if ( primitive instanceof Patch ) {			
			Patch patch = (Patch) primitive;			
			patch.setLocation( ranPointG.generate() );
			String prob = (String) this.getAlgorithmParameters().getParameter(ArtisticParameters.FRAGMENT_FOLDER);
			pathG = new PathGenerator(prob);
			
			patch.setFilePath(pathG.generate() );
		}
		return primitive;
	}

}
