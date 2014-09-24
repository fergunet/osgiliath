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
import es.ugr.osgiliath.planetwars.Debug;
import es.ugr.osgiliath.planetwars.fitness.PlanetWarsCoevolutionFitnessCalculator;

public class PlanetWarsCoevolutionSelector2tournament extends OsgiliathService
		implements ParentSelector {

	PlanetWarsCoevolutionDistributedFitnessCalculator fitnessCalculator;

	Debug _d = new Debug();
	int indInEvaluations;
	
	
	
	public int getIndInEvaluations() {
		return indInEvaluations;
	}

	public void setIndInEvaluations(int indInEvaluations) {
		this.indInEvaluations = indInEvaluations;
	}

	public Individual getRandomNotIn(Population p,ArrayList<Individual> previous) {
		Individual sel;
		do {
			sel = p.getRandomIndividual();
		} while (previous.contains(sel));

		return sel;
	}

	@Override
	public ArrayList<Individual> select(Population pop) {
		System.out.print("PARENTS:");
		
		ArrayList<Individual> parents = new ArrayList<Individual>();
		// First Parent
		ArrayList<Individual> t1 = new ArrayList<Individual>();

		t1.add(getRandomNotIn(pop, t1));
		System.out.print(_d.resumeTree(t1.get(t1.size()-1)));
		System.out.print(" ");
		t1.add(getRandomNotIn(pop, t1));
		System.out.print(_d.resumeTree(t1.get(t1.size()-1)));
		System.out.print("   ");
		t1.add(getRandomNotIn(pop, t1));
		System.out.print(_d.resumeTree(t1.get(t1.size()-1)));
		System.out.print("  ");
		t1.add(getRandomNotIn(pop, t1));
		System.out.print(_d.resumeTree(t1.get(t1.size()-1)));
		
		if(indInEvaluations == 4){
			t1.add(getRandomNotIn(pop, t1));
			System.out.print(_d.resumeTree(t1.get(t1.size()-1)));
			System.out.print("  ");
			t1.add(getRandomNotIn(pop, t1));
			System.out.print(_d.resumeTree(t1.get(t1.size()-1)));
			System.out.print("   ");
			t1.add(getRandomNotIn(pop, t1));
			System.out.print(_d.resumeTree(t1.get(t1.size()-1)));
			System.out.print("  ");
			t1.add(getRandomNotIn(pop, t1));
			System.out.print(_d.resumeTree(t1.get(t1.size()-1)));
			
		}
		
		System.out.print("\n");


		ArrayList<PlanetWarsCoevolutionEvaluationResult> res = fitnessCalculator
				.calculateFitness(t1);

		for(PlanetWarsCoevolutionEvaluationResult r :res){
			// Removing "losers"
			pop.removeIndividual(r.loser);
			parents.add(r.winner);
		}
		
		System.out.print(" -> ");
		System.out.print(_d.resumeTree(res.get(0).winner));
		System.out.print("♥");
		System.out.print(_d.resumeTree(res.get(1).winner));
		
		return parents;
	}

	public void setFitnessCalculator(
			PlanetWarsCoevolutionDistributedFitnessCalculator ftc) {
		this.fitnessCalculator = ftc;

	}

}
