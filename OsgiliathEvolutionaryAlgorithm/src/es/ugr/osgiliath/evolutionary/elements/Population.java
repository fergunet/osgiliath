package es.ugr.osgiliath.evolutionary.elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public interface Population {
	
	//List<Individual> pop;
	
	public void addIndividual(Individual ind);
	
	public void addIndividuals(ArrayList<Individual> inds);
	
	public void removeIndividual(Individual ind);
	
	public void removeIndividuals(ArrayList<Individual> inds);
	/*{
		this.pop.remove(ind);
	}*/
	public void removeAllIndividuals();
	
	public Individual getIndividual(Individual ind);
	
	public Individual getRandomIndividual();
	
	public ArrayList<Individual> getNBestIndividuals(int n);
	
	public ArrayList<Individual> getNWorstIndividuals(int n);
	
	public int getSize();/*{
		return pop.size();
	}*/
	
	//public void sort()
	
	/*public List<Individual> getList(){
		return pop;
	}*/
	
	public void initializePopulation();

	
	//TODO una REFERENCIA O LA REAL???
	public ArrayList<Individual> getAllIndividuals();

}
