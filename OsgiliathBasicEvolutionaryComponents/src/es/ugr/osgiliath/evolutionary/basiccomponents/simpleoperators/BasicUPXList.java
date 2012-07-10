package es.ugr.osgiliath.evolutionary.basiccomponents.simpleoperators;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.elements.Crossover;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class BasicUPXList implements Crossover {
	
	public BasicUPXList(){}
	
	public ArrayList<Genome> cross(Genome father, Genome mother){ 
		ArrayList<Genome> childs = new ArrayList<Genome>();
		ListGenome a = new ListGenome();
		ListGenome b = new ListGenome();
		
		int fatherSize = father.getGeneList().size();
		int motherSize = mother.getGeneList().size();
		
		int minimum = (fatherSize<motherSize)?fatherSize:motherSize;
		
		for(int i = 0; i<minimum; i++){
			double prob = Math.random();
			Gene aG,bG;
			//TODO eliminado probs
			/*if(prob<probs.get(i).doubleValue()){
				aG = father.getGeneList().get(i);
				bG = mother.getGeneList().get(i);
			}else{
				bG = father.getGeneList().get(i);
				aG = mother.getGeneList().get(i);
			}*/
			//TODO changed by this (0.5)
			if(prob<0.5){
				aG = father.getGeneList().get(i);
				bG = mother.getGeneList().get(i);
			}else{
				bG = father.getGeneList().get(i);
				aG = mother.getGeneList().get(i);
			}
			a.getGeneList().add((Gene)aG.clone());
			b.getGeneList().add((Gene)bG.clone());
		}
		
		childs.add(a);
		childs.add(b);
		return childs;
		
	}

}
