package es.ugr.osgiliart;

import java.util.ArrayList;

import es.ugr.osgiliart.core.generators.RandomFloatGenerator;
import es.ugr.osgiliart.core.generators.color.ColorGenerator;
import es.ugr.osgiliart.core.generators.color.RandomColorGenerator;
import es.ugr.osgiliart.core.generators.point.RandomPointGenerator;
import es.ugr.osgiliart.core.rand.RandU;
import es.ugr.osgiliart.primitives.Primitive;
import es.ugr.osgiliart.primitives.basic.Circle;
import es.ugr.osgiliath.evolutionary.elements.Mutation;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class ArtisticMutation implements Mutation{
	
	private ColorGenerator   colorGenerator = new RandomColorGenerator(
			new RandU(),
			new RandU(0.5f, 1.0f),
			new RandU(0.3f, 1.0f)
			);
	
	@Override
	public Genome mutate(Genome genome) {

		ArtisticGenome curGenome = (ArtisticGenome) genome;
		ArtisticGenome newGenome = new ArtisticGenome();
		ArrayList<Primitive> curPrimitives = curGenome.getPrimitives();
		ArrayList<Primitive> newPrimitives = new ArrayList<Primitive>();

		for ( Primitive primitive: curPrimitives ) {
			if ( Math.random() > 0.95 ) {
				newPrimitives.add ( mutate (primitive) );
			} else {
				newPrimitives.add ( (Primitive) primitive.clone() );
			}
		}				
		newGenome.setPrimitives(newPrimitives);
		return newGenome;
	}

	/**
	 * Simple mutation 
	 */	
	protected Primitive mutate (Primitive primitive ) {
		Primitive newPrimitive = (Primitive) primitive.clone();		
		if ( newPrimitive instanceof Circle ) {			
			Circle circle = (Circle) newPrimitive;			
			/*TODO: we only change the color */
			circle.setColor( colorGenerator.generate() );			
		}
		return newPrimitive;
	}
}
