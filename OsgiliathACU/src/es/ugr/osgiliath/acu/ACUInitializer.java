/*
 * ACUInitializer.java
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
package es.ugr.osgiliath.acu;

import java.util.ArrayList;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.Initializer;

public class ACUInitializer extends OsgiliathService implements Initializer{

	Initializer initializer;
	
	@Override
	public ArrayList<Individual> initializeIndividuals(int size) {
		ArrayList<Individual> inds = initializer.initializeIndividuals(size);
		ArrayList<Individual> minds = new ArrayList<Individual>();
		
		for(Individual i:inds){
			MetaACUIndividual mi = new MetaACUIndividual();
			mi.setGenome(i.getGenome());
			mi.setFitness(i.getFitness());
			double migrationProb = Math.random();
			mi.setMigrationProb(migrationProb);
			mi.increaseACUs(((Integer)this.getAlgorithmParameters().getParameter(ACUParameters.ACU_INITIAL_ACUS)).intValue());
			minds.add(mi);
			mi.setIslandId(this.getFrameworkId());
			
		}
		
		//System.out.println(minds);
		return minds;
		
	}
	
	//BIND/UNBIND initializer
		public void setInitializer(Initializer init){
			this.initializer = init;
		}

		public void unsetInitializer(Initializer init){
			this.initializer = null;
		}

}
