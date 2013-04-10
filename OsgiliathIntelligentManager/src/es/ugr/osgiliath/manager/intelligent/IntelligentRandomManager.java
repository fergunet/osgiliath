/*
 * IntelligentRandomManager.java
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

package es.ugr.osgiliath.manager.intelligent;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.Mutator;
import es.ugr.osgiliath.evolutionary.elements.ParentSelector;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.Recombinator;
import es.ugr.osgiliath.evolutionary.elements.Replacer;

import es.ugr.osgiliath.evolutionary.individual.Individual;

public class IntelligentRandomManager extends OsgiliathService implements ParentSelector, Replacer, Mutator, Recombinator{

	List<ParentSelector> parentSelectors = new ArrayList<ParentSelector>();
	List<Replacer> replacers = new ArrayList<Replacer>();
	List<Recombinator> recombinators = new ArrayList<Recombinator>();
	List<Mutator> mutators= new ArrayList<Mutator>();
	
	public void activate(){
		 /*parentSelectors 
		 replacers 
		 recombinators 
		 mutators */ 
	}
	@Override
	public ArrayList<Individual> mutate(Population pop, ArrayList<Individual> individuals) {
		int which = (int)(Math.random()*this.mutators.size());
		return this.mutators.get(which).mutate(pop, individuals);
	}

	@Override
	public ArrayList<Individual> select(Population pop) {
		int which = (int)(Math.random()*this.parentSelectors.size());
		return this.parentSelectors.get(which).select(pop);
	}

	@Override
	public void select(Population pop, ArrayList<Individual> parents,
			ArrayList<Individual> offspring, ArrayList<Individual> mutatedOffspring) {
		int which = (int)(Math.random()*this.replacers.size());
	    this.replacers.get(which).select(pop,parents,offspring,mutatedOffspring);
		
	}

	@Override
	public ArrayList<Individual> recombine(ArrayList<Individual> parents) {
		
		int which = (int)(Math.random()*this.recombinators.size());
		return this.recombinators.get(which).recombine(parents);
	}
	
	//BINDS/UNBINDS
	public void addParentSelector(ParentSelector p){
		
		if(!(p instanceof IntelligentRandomManager)){
			this.parentSelectors.add((ParentSelector)p);
			System.out.println("[IntelligentRandomManager] Add ParentSelector: "+p.getClass().getName());
		}
	} 
	
	public void removeParentSelector(ParentSelector p){
		this.parentSelectors.remove(p);
	}
	
	public void addReplacer(Replacer r){
		if(!(r instanceof IntelligentRandomManager)){
			this.replacers.add(r);
			System.out.println("[IntelligentRandomManager] Add Replacer: "+r.getClass().getName());
		}
		
	}
	
	public void removeReplacer(Replacer r){
		this.replacers.remove(r);
	}
	
	public void addRecombinator(Recombinator r){
		if(!(r instanceof IntelligentRandomManager)){
			System.out.println("[IntelligentRandomManager] Add Recombinator: "+r.getClass().getName());
		
			this.recombinators.add(r);
		}
	}
	
	public void removeRecombinator(Recombinator r){
		this.recombinators.remove(r);
	}
	
	public void addMutator(Mutator m){
		if(!(m instanceof IntelligentRandomManager)){
			System.out.println("[IntelligentRandomManager] Add Mutator: "+m.getClass().getName());
			this.mutators.add(m);
		}
	}
	
	public void removeMutator(Mutator m){
		this.mutators.remove(m);
	}

	@Override
	public void reset() {
		//TODO
		
	}
	
	

}
