/*
 * EvolutionaryBasicParameters.java
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



public interface EvolutionaryBasicParameters {//extends AlgorithmParameters{
	
	static final String MAX_GENERATIONS = "algorithm.evolutionary.generations";
	static final String MAX_EVALUATIONS = "algorithm.evolutionary.evaluations.max";
	static final String CROSSOVER_PROB = "algorithm.evolutionary.crossover.prob";
	static final String MUTATOR_PROB = "algorithm.evolutionary.mutator.prob";
	static final String MUTATOR_STEPSIZE = "algorithm.evolutionary.mutator.stepsize";
	static final String POPULATION_SIZE = "algorithm.evolutionary.population.size";
	static final String REPLACER_SIZE = "algorithm.evolutionary.replacer.size";
	static final String SELECTOR_DUPLICATED_PARENTS = "algorithm.evolutionary.selector.duplicatedparents";
	static final String SELECTOR_TOURNAMENT_SIZE = "algorithm.evolutionary.selector.tournament.size";
	static final String SELECTOR_SIZE = "algorithm.evolutionary.selector.size";
	static final String MIGRATION_TYPE = "algorithm.evolutionary.migration.type";
	static final String MIGRATION_SIZE = "algorithm.evolutionary.migration.size";
	static final String MIGRATION_GENS = "algorithm.evolutionary.migration.generations";
	static final String BEST = "best";
	static final String RANDOM = "random";
	
	/*int getNumGenerations();
	
	double getCrossoverProb();
	
	double getMutationProb();
	
	int getPopulationSize();
	
	int getParentSelectorSize();
	
	int getReplacerSize();
	
	int getNumEvaluations();
	
	boolean getDuplicatedParents();*/
	

}
