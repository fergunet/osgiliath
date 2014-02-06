package es.ugr.osgiliath.manager.intelligent;


import java.util.ArrayList;

import org.osgi.service.component.ComponentContext;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.Crossover;
import es.ugr.osgiliath.evolutionary.elements.Recombinator;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class CrossoverGatherer extends OsgiliathService implements Crossover{

	ArrayList<Crossover> crossovers = new ArrayList<Crossover>();
	
	int actualCrossover;
	

	public void addCrossover(Crossover c){
		this.crossovers.add(c);
	}
	
	public void removeCrossover(Crossover c){
		this.crossovers.remove(c);
	}
	
	public void setCrossover(int id){
		this.actualCrossover = id;
	}

	@Override
	public ArrayList<Genome> cross(Genome mother, Genome father) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	
	

}
