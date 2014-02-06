package es.ugr.osgiliath.manager.intelligent;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.Crossover;
import es.ugr.osgiliath.evolutionary.elements.ParentSelector;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class SelectorGatherer extends OsgiliathService implements ParentSelector{

	TreeMap<String,ParentSelector> parentSelectors = new TreeMap<String,ParentSelector>();
	
	String actualParentSelector;
	

	public void activate(){
		System.out.println("SELECTOR GATHERER ENABLED");
	}
	
	public void addParentSelector(ParentSelector c){
		if(c instanceof SelectorGatherer)
			return;
		
		String name = c.getClass().getCanonicalName();
		System.out.println("GATHERER OBTAINED PARENT SELECTOR "+name);
		this.parentSelectors.put(name,c);
		this.actualParentSelector = name;
		System.out.println("ITERAMOS");
		for(String s:this.parentSelectors.keySet())
			System.out.println(s);
		
	}
	
	public void removeParentSelector(ParentSelector c){
		this.parentSelectors.values().remove(c);
		//TODO checkthis
		System.out.println("HEMOS QUITADO UNO!");
	}
	
	public Set<String> getKeys(){
		return this.parentSelectors.keySet();
	}

	public void setCurrentParentSelector(String id){
		this.actualParentSelector = id;
	}
	@Override
	public ArrayList<Individual> select(Population pop) {
		System.out.println("Using "+actualParentSelector);
		System.out.println("Hay "+this.parentSelectors.size());
		return this.parentSelectors.get(actualParentSelector).select(pop);
		
	}
	
}
