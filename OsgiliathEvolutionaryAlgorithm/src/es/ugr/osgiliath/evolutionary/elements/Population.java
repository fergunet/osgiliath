package es.ugr.osgiliath.evolutionary.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.ugr.osgiliath.evolutionary.individual.Individual;

public interface Population {
	
	//List<Individual> pop;
	
	public void addIndividual(Individual ind);
	
	public void addIndividuals(List<Individual> inds);
	
	public void removeIndividual(Individual ind);/*{
		this.pop.remove(ind);
	}*/
	public void removeAllIndividuals();
	
	public Individual getIndividual(Individual ind);
	
	public Individual getRandomIndividual();
	
	public List<Individual> getNBestIndividuals(int n);
	
	public List<Individual> getNWorstIndividuals(int n);
	
	public int getSize();/*{
		return pop.size();
	}*/
	
	//public void sort()
	
	/*public List<Individual> getList(){
		return pop;
	}*/
	
	public void initializePopulation();
	
	//TODO una REFERENCIA O LA REAL???
	public List<Individual> getAllIndividuals();

}
