/*
 * ArtisticFitnessFitnessCalculator.java
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
 * Daniel Calandria
 */
package es.ugr.osgiliart.fitnesscalculators;


import java.util.ArrayList;

import es.ugr.osgiliart.ArtisticIndividual;
import es.ugr.osgiliart.ArtisticParameters;
import es.ugr.osgiliart.drawer.ProcessingDrawer;
import es.ugr.osgiliart.features.opencv.MatchImage;
import es.ugr.osgiliart.primitives.Drawer;
import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class ArtisticMatchingFitnessCalculator extends OsgiliathService implements FitnessCalculator{

	Drawer drawer;
	MatchImage matcher = null;
	
	@Override
	public Fitness calculateFitness(Individual ind) {
		/*
		 * Currently, we only support "compare with a real image"
		 * 
		 * FIXME: get fitness calculator from algorithm parameters
		 */
	    
		/* FIXME */
		if ( ((ArtisticIndividual) ind).getGeneration() < 0) { 
			return null;			
		}								
		drawer.draw((ArtisticIndividual) ind);
		
				
		if ( matcher == null ) {
			String pattern = (String) this.getAlgorithmParameters().getParameter(ArtisticParameters.MATCHING_TAMPLATE);
			matcher = new MatchImage(pattern);			
		}
		
		double result =  matcher.match( ((ArtisticIndividual) ind).getImagePath() );	
		System.out.println("Fitness: " + result);
		DoubleFitness fitness = new DoubleFitness(result, true);
		
		return fitness;		
	}

	@Override
	public ArrayList<Fitness> calculateFitnessForAll(ArrayList<Individual> inds) {		
		ArrayList<Fitness> fitnessArray = new ArrayList<Fitness>();		
		for ( Individual individual: inds ) {
			fitnessArray.add( calculateFitness(individual) );
		}			
		return fitnessArray;
	}

	public void setDrawer ( Drawer drawer ) {
		this.drawer = drawer;
	}
	
	public Drawer getDrawer () {
		return this.drawer;
	}
}
