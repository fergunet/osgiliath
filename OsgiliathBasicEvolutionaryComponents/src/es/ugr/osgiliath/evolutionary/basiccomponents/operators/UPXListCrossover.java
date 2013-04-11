/*
 * UPXListCrossover.java
 * 
 * Copyright (c) 2013, Pablo Garcia-Sanchez. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 * 
 * Contributors:
 */
package es.ugr.osgiliath.evolutionary.basiccomponents.operators;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.elements.Crossover;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class UPXListCrossover implements Crossover {
	
	public UPXListCrossover(){}
	
	public ArrayList<Genome> cross(Genome father, Genome mother){ 
		ArrayList<Genome> childs = new ArrayList<Genome>();
		ListGenome a = new ListGenome();
		ListGenome b = new ListGenome();
		
		ListGenome fatherL = (ListGenome) father;
		ListGenome motherL = (ListGenome) mother;
		
		int fatherSize = fatherL.getGeneList().size();
		int motherSize = motherL.getGeneList().size();
		
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
				aG = fatherL.getGeneList().get(i);
				bG = motherL.getGeneList().get(i);
			}else{
				bG = fatherL.getGeneList().get(i);
				aG = motherL.getGeneList().get(i);
			}
			a.getGeneList().add((Gene)aG.clone());
			b.getGeneList().add((Gene)bG.clone());
		}
		
		childs.add(a);
		childs.add(b);
		return childs;
		
	}

}
