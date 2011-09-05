package es.osgiliath.evolutionary.basicimplementations.populations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.Initializer;

public class ListPopulation extends OsgiliathService implements Population{

	private List<Individual> individuals;
	
	private Initializer initializer = null;
	
	public ListPopulation(){
		this.individuals = new ArrayList();
	}
	
	@Override
	public void addIndividual(Individual ind) {
		individuals.add(ind);
		
		
	}

	@Override
	public void removeIndividual(Individual ind) {
		individuals.remove(ind);
		
	}

	@Override
	public Individual getIndividual(Individual ind) {
		int index = this.individuals.indexOf(ind);
		if(index==-1)
			return null;
		else
			return this.individuals.get(index);
	}

	@Override
	public List<Individual> getNBestIndividuals(int n) {
		List<Individual> bests = new ArrayList<Individual>();
		
		Collections.sort(this.individuals); //TODO OJO! Que esto no termina de gustarme
		for(int i = 0; i<n;i++){
			bests.add(this.individuals.get(i));
		}
		
		return bests;
		
	}

	@Override
	public List<Individual> getNWorstIndividuals(int n) {
		List<Individual> worst = new ArrayList<Individual>();
		
		Collections.sort(this.individuals); //TODO OJO! Que esto no termina de gustarme
		for(int i = individuals.size()-n; i<individuals.size();i++){
			worst.add(this.individuals.get(i));
		}
		
		return worst;
	}

	@Override
	public int getSize() {
		return this.individuals.size();
	}

	@Override
	public void initializePopulation() {
		int popSize = ((EvolutionaryBasicParameters) this.getAlgorithmParameters()).getPopulationSize();
		for(int i = 0; i<popSize;i++){
			Individual ind = this.initializer.initializeIndividual();
			this.individuals.add(ind);
		}

		
	}
	
	//BIND/UNBIND FOR INITIALIZATION
	public void setInitializer(Initializer init){
		this.initializer = init;
	}
	
	public void unsetInitializer(Initializer init){
		this.initializer = null;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("P[").append(this.getSize()).append("]\n");
		for(Individual ind:this.individuals)
			sb.append(ind.toString()).append("\n");
		return sb.toString();
	}

	@Override
	public Individual getRandomIndividual() {
		double value = Math.random()*individuals.size();
		int i = (int) value;
		return this.individuals.get(i);
	}

	@Override
	public List<Individual> getAllIndividuals() {
		// TODO Auto-generated method stub
		return this.individuals; //O una copia?
	}

	@Override
	public void addIndividuals(List<Individual> inds) {
		this.individuals.addAll(inds);
		
	}

	@Override
	public void removeAllIndividuals() {
		this.individuals.clear();
		
	}



}
