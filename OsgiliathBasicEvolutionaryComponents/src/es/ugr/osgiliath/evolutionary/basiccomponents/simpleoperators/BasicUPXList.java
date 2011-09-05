package es.ugr.osgiliath.evolutionary.basiccomponents.simpleoperators;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.individual.Gene;

public class BasicUPXList {
	
	public BasicUPXList(){}
	
	public List<ListGenome> cross(ListGenome father, ListGenome mother, List<Double> probs){ 
		List<ListGenome> childs = new ArrayList<ListGenome>();
		ListGenome a = new ListGenome();
		ListGenome b = new ListGenome();
		
		int fatherSize = father.getGeneList().size();
		int motherSize = mother.getGeneList().size();
		
		int minimum = (fatherSize<motherSize)?fatherSize:motherSize;
		
		for(int i = 0; i<minimum; i++){
			double prob = Math.random();
			Gene aG,bG;
			if(prob<probs.get(i).doubleValue()){
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
