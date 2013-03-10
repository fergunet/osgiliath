package es.ugr.osgiliart;

import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.problem.SolutionValue;

public class ArtisticIndividual implements Individual{

	protected ArtisticGenome genome;
	protected Fitness fitness;
	
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub		
		return 0;
	}

	@Override
	public void setSolutionValue(SolutionValue sValue) {
		// TODO Auto-generated method stub		
	}

	@Override
	public SolutionValue getSolutionValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFitness(Fitness cost) {
		this.fitness = cost;
	}

	@Override
	public Fitness getFitness() {
		return this.fitness;
	}

	@Override
	public void setGenome(Genome genome) {
		this.genome = (ArtisticGenome) genome;	
	}

	@Override
	public Genome getGenome() {		
		return this.genome;
	}

}
