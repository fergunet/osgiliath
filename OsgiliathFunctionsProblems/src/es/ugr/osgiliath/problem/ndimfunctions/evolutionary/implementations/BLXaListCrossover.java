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
package es.ugr.osgiliath.problem.ndimfunctions.evolutionary.implementations;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleGene;
import es.ugr.osgiliath.evolutionary.elements.Crossover;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.problem.ndimfunctions.NdimFunctionProblemParameters;

public class BLXaListCrossover extends OsgiliathService implements Crossover  {
	
	public BLXaListCrossover(){}
	
	public ArrayList<Genome> cross(Genome father, Genome mother){ 
		ArrayList<Genome> childs = new ArrayList<Genome>();
		ListGenome a = new ListGenome();
		ListGenome b = new ListGenome();
		
		ListGenome fatherL = (ListGenome) father;
		ListGenome motherL = (ListGenome) mother;
		/*System.out.println(father.toString());
		System.out.println(mother.toString());
		System.out.println(" ");*/
		int fatherSize = fatherL.getGeneList().size();
		int motherSize = motherL.getGeneList().size();
		
		double alpha = 0.5; //TODO parameter
		int minimum = (fatherSize<motherSize)?fatherSize:motherSize;
		
		double minrange = (Double)this.getAlgorithmParameters().getParameter(NdimFunctionProblemParameters.MINRANGE_PROP);
		double maxrange = (Double)this.getAlgorithmParameters().getParameter(NdimFunctionProblemParameters.MAXRANGE_PROP);
		
		for(int i = 0; i<minimum; i++){

						
			double c1 = ((DoubleGene) fatherL.getGeneList().get(i)).getValue();
			double c2 = ((DoubleGene) motherL.getGeneList().get(i)).getValue();
			
			double cmin = Math.min(c1, c2);
			double cmax = Math.max(c1, c2);
			double I = cmax - cmin;
			
			double intervalmin = cmin -I*alpha;
			double intervalmax = cmax +I*alpha;
			
			//Limit
			
			
			double randomValue1 = intervalmin + (intervalmax - intervalmin) * Math.random();
			double randomValue2 = intervalmin + (intervalmax - intervalmin) * Math.random();
			a.getGeneList().add(new DoubleGene(randomValue1));
			b.getGeneList().add(new DoubleGene(randomValue2));
		}
		
		childs.add(a);
		childs.add(b);
		return childs;
		
	}

}
