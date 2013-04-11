/*
 * NBestParentSelector.java
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
package es.osgiliath.evolutionary.basicimplementations.selectors;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.ParentSelector;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class NBestParentSelector extends OsgiliathService implements ParentSelector {

	/*
	 * This Selector obtains the N best parents IN ORDER to recombine. The order is given to recombinator
	 * @see es.ugr.osgiliath.evolutionary.components.selectors.ParentSelector#select(es.ugr.osgiliath.evolutionary.elements.Population)
	 */
	@Override
	public ArrayList<Individual> select(Population pop) {
		//System.out.println("NEW GENERATION");
		//System.out.println(pop);
		
		//EvolutionaryBasicParameters params = (EvolutionaryBasicParameters) this.getAlgorithmParameters(); //bound thanks to OsgiliathService
		int n = (Integer) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.SELECTOR_SIZE);
		ArrayList<Individual> bests = pop.getNBestIndividuals(n);
		return bests;
	}
	


}
