package es.ugr.osgiliath.test;

import java.util.ArrayList;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BooleanGene;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleGene;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.problem.SolutionValue;

public class BasicIndividualSpecific implements Individual{
	Fitness f;
	ListGenomeSpecific g;
	int age = 0;
	
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
		this.g = (ListGenomeSpecific) genome;
		
	}

	public ListGenomeSpecific getSpecificGenome(){
		/*ListGenomeSpecific  s = new ListGenomeSpecific();
		ArrayList<Gene> pepe = new ArrayList<Gene>();
		pepe.add(new BooleanGene(false));
		pepe.add(new BooleanGene(false));
		s.setGenes(pepe);
		return s;*/
		ListGenomeSpecific spec = (ListGenomeSpecific)this.g;
		return spec;
	}
	/*@Override
	public ListGenomeSpecific getGenome() {
		
		ListGenomeSpecific lg = new ListGenomeSpecific();
		ArrayList<Gene> list = new ArrayList<Gene>();
		list.add(new BooleanGene(true));
		list.add(new BooleanGene(false));
		lg.setGenes(list);
		return lg;
	}*/
	
	@Override
	public Genome getGenome() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String toString(){
		return f+":"+g;
	}

	@Override
	public void setSolutionValue(SolutionValue sValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SolutionValue getSolutionValue() {
		// TODO Auto-generated method stub
		return this.f;
	}

	public int getAge(){
		return this.age;
	}
	
	public void increaseAge(){
		this.age++;
	}

	
	

}
