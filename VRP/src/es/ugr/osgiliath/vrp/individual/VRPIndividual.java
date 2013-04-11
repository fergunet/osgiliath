/*
 * VRPIndividual.java
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
package es.ugr.osgiliath.vrp.individual;

import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.problem.SolutionValue;

public class VRPIndividual implements Individual{

	VRPGenome genome;
	DoubleFitness cost;
	
	
	
	public Fitness getFitness() {
		return this.cost;
	}


	public Genome getGenome() {
		return this.genome;
		
	}


	public void setFitness(Fitness cost) {
		this.cost = (DoubleFitness) cost;
		
	}


	public void setGenome(Genome genome) {
		this.genome = (VRPGenome) genome;
		/*if(this.data!=null){
			this.genome.setData(this.data);
		}*/
		
	}


	/*public int isBetterThan(Individual o) {
		return cost.isBetterThan( ((VRPIndividual)o).getFitness() );
	}*/


	/*public int compareTo(Object o) {
		return cost.compareTo( ((VRPIndividual)o).getFitness() );
	}*/
	
	public String toString(){
		if(this.cost==null)
			return new String("NULLCOST0000000000"+":"+this.genome.toString());
		else
		return new String(this.cost.toString()+":"+this.genome.toString());
	}




	@Override
	public void setSolutionValue(SolutionValue sValue) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public SolutionValue getSolutionValue() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
