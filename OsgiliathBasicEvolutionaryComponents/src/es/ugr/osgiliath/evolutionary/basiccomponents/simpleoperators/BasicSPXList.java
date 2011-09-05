
package es.ugr.osgiliath.evolutionary.basiccomponents.simpleoperators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class BasicSPXList {
	/**
	 * Single point crossover of a list. The point is inclusive. Example point = 3-> 0 1 2 3 | 4 5 6. 
	 * So the point must be in range [0,size()-2]
	 * @param father
	 * @param mother
	 * @param point
	 * @return
	 */
	public List<ListGenome> cross(ListGenome father, ListGenome mother, int point){ //The point is the last in the left
		List<ListGenome> childs = new ArrayList<ListGenome>();
		ListGenome a = new ListGenome();
		ListGenome b = new ListGenome();
		
		
		
		List<Gene> fatherLeft  = (List<Gene>) father.getGeneList().subList(0, point+1); //right is exclusive!
		List<Gene> fatherRight = (List<Gene>) father.getGeneList().subList(point+1, father.getGeneList().size());
		
		List<Gene> motherLeft  =  (List<Gene>) mother.getGeneList().subList(0, point+1);
		List<Gene> motherRight =  (List<Gene>) mother.getGeneList().subList(point+1, mother.getGeneList().size());
		
		for(Gene g:fatherLeft){
			a.getGeneList().add((Gene) g.clone());
		}
		for(Gene g:motherRight){
			a.getGeneList().add((Gene) g.clone());
		}
		for(Gene g:motherLeft){
			b.getGeneList().add((Gene) g.clone());
		}
		for(Gene g:fatherRight){
			b.getGeneList().add((Gene) g.clone());
		}
		
		childs.add(a);
		childs.add(b);
		return childs;
		
	}

}
