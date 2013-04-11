/*
 * Population.java
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
package es.ugr.osgiliath.evolutionary.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public interface Population {
	
	//List<Individual> pop;
	
	public void addIndividual(Individual ind);
	
	public void addIndividuals(ArrayList<Individual> inds);
	
	public void removeIndividual(Individual ind);
	
	public void removeIndividuals(ArrayList<Individual> inds);
	/*{
		this.pop.remove(ind);
	}*/
	public void removeAllIndividuals();
	
	public Individual getIndividual(Individual ind);
	
	public Individual getRandomIndividual();
	
	public ArrayList<Individual> getNBestIndividuals(int n);
	
	public ArrayList<Individual> getNWorstIndividuals(int n);
	
	public int getSize();/*{
		return pop.size();
	}*/
	
	//public void sort()
	
	/*public List<Individual> getList(){
		return pop;
	}*/
	
	public void initializePopulation();

	
	//TODO una REFERENCIA O LA REAL???
	public ArrayList<Individual> getAllIndividuals();

}
