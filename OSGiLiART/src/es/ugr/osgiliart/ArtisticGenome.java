package es.ugr.osgiliart;

import java.util.ArrayList;

import es.ugr.osgiliart.primitives.Primitive;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class ArtisticGenome implements Genome{
	
	protected ArrayList<Primitive> primitives = null;
	
	public ArtisticGenome () {
		
	}
	
	public ArtisticGenome (ArrayList<Primitive> primitives ) {
		this.primitives = primitives;
	}
	
	public Object clone () {
		ArrayList<Primitive> newPrimitives = new ArrayList<Primitive>();
		for ( Primitive primitive: primitives) {
			newPrimitives.add( (Primitive) primitive.clone() );
		}		
		return newPrimitives;
	}

	public ArrayList<Primitive> getPrimitives() {
		return primitives;
	}

	public void setPrimitives(ArrayList<Primitive> primitives) {
		this.primitives = primitives;
	}
	
	
	

}
