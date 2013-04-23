/*
 * ACUParameters.java
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


public class ACUParameters {// extends NDimFunctionEvolutionaryParameters{
	
	/*int reward;
	int migrationCost;
	
	int initialACUs;
	int ACUsToLive;
	
	int minimalIslandSize;
	int maximumIslandSize;
	*/
	int totalACUs; //per island
	
	public static final String ACU_INITIAL_ACUS = "algorithm.acu.initialacus";
	
	public static final String ACU_REWARD = "algorithm.acu.reward";
	public static final String ACU_BANK = "algorithm.acu.bank";
	public static final String ACU_MIGRATION_PRIZE = "algorithm.acu.migrationprize";
	public static final String ACU_MUTATION_PRIZE = "algorithm.acu.mutationprize";
	public static final String ACU_CROSSOVER_PRIZE = "algorithm.acu.crossoverprize";
	

	/*@Override
	public void setup(Properties props){
		super.setup(props);
		System.out.println("TERMINA ESTO!!!");
		
	}
	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public int getMigrationCost() {
		return migrationCost;
	}

	public void setMigrationCost(int migrationCost) {
		this.migrationCost = migrationCost;
	}

	public int getInitialACUs() {
		return initialACUs;
	}

	public void setInitialACUs(int initialACUs) {
		this.initialACUs = initialACUs;
	}

	public int getACUsToLive() {
		return ACUsToLive;
	}

	public void setACUsToLive(int aCUsToLive) {
		ACUsToLive = aCUsToLive;
	}

	public int getMinimalIslandSize() {
		return minimalIslandSize;
	}

	public void setMinimalIslandSize(int minimalIslandSize) {
		this.minimalIslandSize = minimalIslandSize;
	}

	public int getMaximumIslandSize() {
		return maximumIslandSize;
	}

	public void setMaximumIslandSize(int maximumIslandSize) {
		this.maximumIslandSize = maximumIslandSize;
	}

	public int getTotalACUs() {
		return totalACUs;
	}

	public void setTotalACUs(int totalACUs) {
		this.totalACUs = totalACUs;
	}*/
	
	

}
