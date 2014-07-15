package es.ugr.osgiliath.planetwars.components;

/*
 * DeterministicTournamentSelection.java
 * 
 * Copyright (c) 2014, Antonio Fernández Ares. All rights reserved.
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import es.osgiliath.evolutionary.basicimplementations.fitnesscalculators.BasicDistributedFitnessCalculator;
import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.ParentSelector;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.planetwars.fitness.PlanetWarsCoevolutionFitnessCalculator2individual;

public class PlanetWarsCoevolutionSelector2tournament extends OsgiliathService
		implements ParentSelector {

	PlanetWarsCoevolutionDistributedFitnessCalculator fitnessCalculator;

	public Individual getRandomNotIn(Population p,
			ArrayList<Individual> previous) {
		Individual sel;
		do {
			sel = p.getRandomIndividual();
		} while (!previous.contains(sel));

		return sel;
	}

	@Override
	public ArrayList<Individual> select(Population pop) {
		ArrayList<Individual> parents = new ArrayList<Individual>();
		// First Parent
		ArrayList<Individual> t1 = new ArrayList<Individual>();

		t1.add(getRandomNotIn(pop, t1));
		t1.add(getRandomNotIn(pop, t1));
		t1.add(getRandomNotIn(pop, t1));
		t1.add(getRandomNotIn(pop, t1));

		ArrayList<PlanetWarsCoevolutionEvaluationResult> res = fitnessCalculator
				.calculateFitness(t1);

		for(PlanetWarsCoevolutionEvaluationResult r :res){
			// Removing "losers"
			pop.removeIndividual(r.loser);
			parents.add(r.winner);
		}
		
		return parents;
	}

	public void setFitnessCalculator(
			PlanetWarsCoevolutionDistributedFitnessCalculator ftc) {
		this.fitnessCalculator = ftc;

	}

}
