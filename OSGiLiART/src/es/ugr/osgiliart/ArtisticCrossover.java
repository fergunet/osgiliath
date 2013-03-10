package es.ugr.osgiliart;

import java.util.ArrayList;

import es.ugr.osgiliart.primitives.Primitive;
import es.ugr.osgiliath.evolutionary.elements.Crossover;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class ArtisticCrossover implements Crossover{

	@Override
	public ArrayList<Genome> cross(Genome mother, Genome father) {
		ArrayList<Genome> childs = new ArrayList<Genome>();		
		ArtisticGenome a = new ArtisticGenome();
		ArtisticGenome b = new ArtisticGenome();
		
		ArrayList<Primitive> motherPrimitives = (( ArtisticGenome ) mother).getPrimitives();
		ArrayList<Primitive> fatherPrimitives = (( ArtisticGenome ) father).getPrimitives();
		ArrayList<Primitive> aPrimitives = new ArrayList<Primitive>();
		ArrayList<Primitive> bPrimitives = new ArrayList<Primitive>();
		
		int length = motherPrimitives.size();
		for ( int i = 0; i < length; ++i ) {
			/* merge with same probability */			
			if ( Math.random() >= 0.5 ) {
				aPrimitives.add( (Primitive) motherPrimitives.get(i).clone() );
				bPrimitives.add( (Primitive) fatherPrimitives.get(i).clone() );
			} else {
				aPrimitives.add( (Primitive) fatherPrimitives.get(i).clone() );
				bPrimitives.add( (Primitive) motherPrimitives.get(i).clone() );
			}
		}		
		a.setPrimitives(aPrimitives);
		b.setPrimitives(bPrimitives);		
		childs.add(a);
		childs.add(b);
		return childs;
	}

}
