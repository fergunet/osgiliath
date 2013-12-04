/*
 * BasicIndividual.java
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
package es.ugr.osgiliath.evolutionary.basiccomponents.individuals;

import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.problem.SolutionValue;

public class BasicIndividual implements Individual{

	Fitness f;
	Genome g;
	int age = 0;
	
	@Override
	public int compareTo(Object arg0) {
		Individual other = (Individual) arg0;
		return f.compareTo(other.getFitness());
	}

	@Override
	public void setFitness(Fitness cost) {
		f = cost;
		
	}

	@Override
	public Fitness getFitness() {
	
		return this.f;
	}

	@Override
	public void setGenome(Genome genome) {
		this.g = genome;
		
	}

	@Override
	public Genome getGenome() {
		
		return g;
	}
	
	public String toString(){
		return f+":"+g;
	}

	@Override
	public void setSolutionValue(SolutionValue sValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SolutionValue getSolutionValue() {
		// TODO Auto-generated method stub
		return this.f;
	}

	public int getAge(){
		return this.age;
	}
	
	public void increaseAge(){
		this.age++;
	}

}
