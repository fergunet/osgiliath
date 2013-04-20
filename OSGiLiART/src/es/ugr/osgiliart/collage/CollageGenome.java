package es.ugr.osgiliart.collage;

import java.util.ArrayList;

import es.ugr.osgiliart.primitives.patch.Patch;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class CollageGenome implements Genome {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected  ArrayList<Patch> patchs = new ArrayList<Patch>();

	public ArrayList<Patch> getFragments() {
		return patchs;
	}

	public void setFragments(ArrayList<Patch> fragments) {
		this.patchs = fragments;
	}

	@Override
	public Object clone()  {
		ArrayList<Patch> newPrimitives = new ArrayList<Patch>();
		for ( Patch primitive: patchs) {
			newPrimitives.add( (Patch) primitive.clone() );
		}		
		return newPrimitives;
	}
}
