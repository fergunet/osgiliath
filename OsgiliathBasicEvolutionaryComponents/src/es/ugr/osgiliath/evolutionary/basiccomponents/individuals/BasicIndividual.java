package es.ugr.osgiliath.evolutionary.basiccomponents.individuals;

import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class BasicIndividual implements Individual{

	Fitness f;
	Genome g;

	
	@Override
	public int compareTo(Object arg0) {
		Individual other = (Individual) arg0;
		return f.compareTo(other.getFitness());
	}

	@Override
	public void setFitness(Fitness cost) {
		f = cost;
		
	}

	@Override
	public Fitness getFitness() {
	
		return this.f;
	}

	@Override
	public void setGenome(Genome genome) {
		this.g = genome;
		
	}

	@Override
	public Genome getGenome() {
		
		return g;
	}
	
	public String toString(){
		return f+":"+g;
	}



}
