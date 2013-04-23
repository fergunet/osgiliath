/*
 * MetaACUIndividual.java
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

import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;


public class MetaACUIndividual extends BasicIndividual{
	
	int numACUs;
	double migrationProb;
	String islandId;
	
	ArrayList<MetaACUIndividual> fathers;
	
	
	public MetaACUIndividual() {
		numACUs = 0;
	}
	
	public int getACUs(){
		return this.numACUs;
	}
	
	public void increaseACUs(int numACUs){
		this.numACUs += numACUs;
	}
	
	public void decreaseACUs(int numACUs){
		this.numACUs -= numACUs;
	}

	public double getMigrationProb() {
		return migrationProb;
	}
	
	public void setMigrationProb(double migrationProb) {
		this.migrationProb = migrationProb;
	}
	
	public ArrayList<MetaACUIndividual> getFathers() {
		return fathers;
	}
	
	public void setFathers(ArrayList<MetaACUIndividual> fathers) {
		this.fathers = fathers;
	}
	
	public String getIslandId(){
		return this.islandId;
	}
	
	public void setIslandId(String id){
		this.islandId = id;
	}
	
	public String toString(){
		return "["+this.getFitness().toString()+"] "+this.getACUs()+" ACUs. Prob:"+this.migrationProb;
	}
}
