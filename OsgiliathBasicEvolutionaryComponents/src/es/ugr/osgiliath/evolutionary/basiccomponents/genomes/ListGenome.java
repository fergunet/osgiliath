/*
 * ListGenome.java
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
package es.ugr.osgiliath.evolutionary.basiccomponents.genomes;

import java.util.ArrayList;


import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class ListGenome implements Cloneable,Genome{
	
	protected ArrayList<Gene> genes;
	
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
