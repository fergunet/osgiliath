package es.ugr.osgiliath.evolutionary.basiccomponents.genomes;

import java.util.ArrayList;


import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class ListGenome implements Cloneable,Genome{
	
	ArrayList<Gene> genes;
	
	public ListGenome(){
		this.genes= new ArrayList<Gene>();
	}
	
	public ArrayList<Gene> getGeneList(){
		return this.genes;
	}
	
	public void setGenes(ArrayList<Gene> genes){
		this.genes = genes;
	}
	
	public Object clone(){
		ListGenome newGenome = new ListGenome();
		for(Gene g:genes){
			Gene newG = (Gene) g.clone();
			newGenome.genes.add(newG);
		}
		return newGenome;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(Gene g:genes)
			sb.append("[").append(g).append("]");
		return sb.toString();
	}


}
