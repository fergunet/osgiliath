/*
 * OptimumStopCriterion.java
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
package es.ugr.osgiliath.evolutionary.basicimplementations.stopcriterions;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.IntegerFitness;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.StopCriterion;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.problem.ProblemParameters;

public class OptimumStopCriterion extends OsgiliathService implements StopCriterion{

	private boolean finished = false;
	private Population pop;
	
	
	@Override
	public boolean hasFinished() {
		Fitness best = this.pop.getNBestIndividuals(1).get(0).getFitness();
		Object obj =  this.getProblem().getParameters().getParameter(ProblemParameters.OPTIMUM);
		
		Fitness optimum = new IntegerFitness(1, false);//TODO cafrada
		boolean maximize = best.toMaximize();
		
		if(obj instanceof Double)
			optimum = new DoubleFitness((Double) obj, maximize);
		
		if(obj instanceof Integer)
			optimum = new IntegerFitness((Integer)obj, maximize);
		
		if(optimum != null)
			if(best.compareTo(optimum)==-1 || best.compareTo(optimum)==0)
				finished = true;
		
		
		return finished;
	}

	@Override
	public void reset() {
		this.finished = false;
		
		boolean toMaximize;
	
		
		
	}

	@Override
	public void stop() {
		this.finished = true;
		
	}

	public void setPopulation(Population pop){
		this.pop = pop;
	}
	
	public void unsetPopulation(Population pop){
		this.pop = null;
	}
}
