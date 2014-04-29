package es.ugr.osgiliath.test;

import java.util.ArrayList;
import java.util.Collection;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BooleanGene;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleGene;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class ListGenomeSpecific  implements Genome{
	
	ArrayList<BooleanGene> genes;
	
	public ArrayList<BooleanGene> getSpecificGeneList(){
		/*ArrayList<BooleanGene> esp = new ArrayList<BooleanGene>();
		//esp.addAll((Collection<? extends BooleanGene>) this.getGeneList());
		return esp;*/
		return genes;
		
	}
	
	public void setSpecificGeneList(ArrayList<BooleanGene> g){
		this.genes = g;
	}
	
	public String getNada(){
		return "a";
	}
	
	public Object clone(){return null;}
	

}
