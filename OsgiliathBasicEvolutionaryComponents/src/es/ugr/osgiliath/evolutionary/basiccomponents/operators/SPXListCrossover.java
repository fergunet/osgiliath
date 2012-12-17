
package es.ugr.osgiliath.evolutionary.basiccomponents.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.elements.Crossover;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class SPXListCrossover implements Crossover{
	/**
	 * Single point crossover of a list. The point is inclusive. Example point = 3-> 0 1 2 3 | 4 5 6. 
	 * So the point must be in range [0,size()-2]
	 * @param father
	 * @param mother
	 * @param point
	 * @return
	 */

	@Override
	public ArrayList<Genome> cross(Genome mother, Genome father) {
		ArrayList<Genome> childs = new ArrayList<Genome>();
		ListGenome fatherL = (ListGenome)father;
		ListGenome motherL = (ListGenome)mother;
		ListGenome a = new ListGenome();
		ListGenome b = new ListGenome();
		
		int maxpoint = (fatherL.getGeneList().size()<motherL.getGeneList().size())?fatherL.getGeneList().size():motherL.getGeneList().size();
		maxpoint--; //The point is inclusive, so, to avoid children equal to parents the maxpoint must be size-2
		int point = (int) (Math.random()*maxpoint);
		
		
		List<Gene> fatherLeft  = (List<Gene>) fatherL.getGeneList().subList(0, point+1); //right is exclusive!
		List<Gene> fatherRight = (List<Gene>) fatherL.getGeneList().subList(point+1, fatherL.getGeneList().size());
		
		List<Gene> motherLeft  =  (List<Gene>) motherL.getGeneList().subList(0, point+1);
		List<Gene> motherRight =  (List<Gene>) motherL.getGeneList().subList(point+1, motherL.getGeneList().size());
		
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
