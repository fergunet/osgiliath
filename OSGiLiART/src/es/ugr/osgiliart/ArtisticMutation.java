package es.ugr.osgiliart;

import java.util.ArrayList;

import es.ugr.osgiliart.core.generators.RandomFloatGenerator;
import es.ugr.osgiliart.core.generators.color.ColorGenerator;
import es.ugr.osgiliart.core.generators.color.RandomColorGenerator;
import es.ugr.osgiliart.core.generators.point.PointGenerator;
import es.ugr.osgiliart.core.generators.point.RandomPointGenerator;
import es.ugr.osgiliart.core.rand.RandU;
import es.ugr.osgiliart.primitives.Primitive;
import es.ugr.osgiliart.primitives.basic.Circle;
import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.Mutation;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class ArtisticMutation extends OsgiliathService implements Mutation{
	
	private ColorGenerator   colorGenerator = new RandomColorGenerator(
			new RandU(),
			new RandU(),
			new RandU()
			);
	
	private PointGenerator   pointGenerator = new RandomPointGenerator(
			new RandU(),
			new RandU()
			);
	
	RandomFloatGenerator radiusGenerator = new RandomFloatGenerator(new RandU());
	
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
		if ( primitive instanceof Circle ) {			
			Circle circle = (Circle) primitive;			
			/*TODO: we only change the color */
			circle.setColor( colorGenerator.generate() );
			circle.setCenter(pointGenerator.generate() );
			circle.setRadius(radiusGenerator.generate());
		}
		return primitive;
	}
}
