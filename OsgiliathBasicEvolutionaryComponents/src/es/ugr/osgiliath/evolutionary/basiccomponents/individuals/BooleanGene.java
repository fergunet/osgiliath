package es.ugr.osgiliath.evolutionary.basiccomponents.individuals;

import es.ugr.osgiliath.evolutionary.individual.Gene;

public class BooleanGene implements Gene, Cloneable{
	boolean b;
	
	public BooleanGene(boolean b){
		this.b = b;
	}
	
	public Object clone(){
		return new BooleanGene(this.b);
	}
	
	public void setValue(boolean b){
		this.b = b;
	}

	public boolean getValue(){
		return this.b;
	}
	
	public String toString(){
		if(this.b==true)
			return "1";
		else
			return "0";
	}
}
