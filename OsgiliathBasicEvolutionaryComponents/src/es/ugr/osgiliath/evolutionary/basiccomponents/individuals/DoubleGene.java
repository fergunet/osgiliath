package es.ugr.osgiliath.evolutionary.basiccomponents.individuals;

import es.ugr.osgiliath.evolutionary.individual.Gene;

public class DoubleGene implements Gene,Cloneable{
	double d;
	
	public DoubleGene(double d){
		this.d = d;
	}
	
	public Object clone(){
		return new DoubleGene(this.d);
	}
	
	public double getValue(){
		return d;
	}
	
	public void setValue(double d){
		this.d = d;
	}
	
	public String toString(){
		return ""+d;
	}

}
