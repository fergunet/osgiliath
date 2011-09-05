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

	List<ParentSelector> parentSelectors = new ArrayList<ParentSelector>();;
	List<Replacer> replacers = new ArrayList<Replacer>();;
	List<Recombinator> recombinators = new ArrayList<Recombinator>();;
	List<Mutator> mutators= new ArrayList<Mutator>();;
	
	public void activate(){
		 /*parentSelectors 
		 replacers 
		 recombinators 
		 mutators */ 
	}
	@Override
	public List<Individual> mutate(List<Individual> individuals) {
		int which = (int)(Math.random()*this.mutators.size());
		return this.mutators.get(which).mutate(individuals);
	}

	@Override
	public List<Individual> select(Population pop) {
		int which = (int)(Math.random()*this.parentSelectors.size());
		return this.parentSelectors.get(which).select(pop);
	}

	@Override
	public void select(Population pop, List<Individual> parents,
			List<Individual> offspring, List<Individual> mutatedOffspring) {
		int which = (int)(Math.random()*this.replacers.size());
	    this.replacers.get(which).select(pop,parents,offspring,mutatedOffspring);
		
	}

	@Override
	public List<Individual> recombine(List<Individual> parents) {
		
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
	
	

}
