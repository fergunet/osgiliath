package es.osgiliath.evolutionary.basicimplementations.combinators;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;
import es.ugr.osgiliath.evolutionary.basiccomponents.simpleoperators.BasicSPXList;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.Recombinator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class BasicSPXListRecombinator extends OsgiliathService implements Recombinator{

	private FitnessCalculator fitnessCalculator;
	/*
	 * This recombinator recombines the parents in order (given by Selector) with the Single Point Crossover (SPX). 
	 * If the parents are an odd number, the last
	 * is not recombined. Returns a new offspring (in this implementation, all new).
	 * 
	 * @see es.ugr.osgiliath.evolutionary.elements.Recombinator#recombine(java.util.List)
	 */
	@Override
	
	public List<Individual> recombine(List<Individual> parents) {
		System.out.println("SPX");
		List<Individual> offspring = new ArrayList<Individual>();
		//Collections.shuffle(parents); //TODO ojo!!!
		for(int i=0;i<parents.size()-1;i=i+2){
			Individual father = parents.get(i);
			Individual mother = parents.get(i+1);			
		    int fatherSize = father.getGenome().getGeneList().size(); //TODO quitar el getGeneList de Genome
		    int motherSize = mother.getGenome().getGeneList().size();
		    
			BasicSPXList rec = new BasicSPXList();
	
			int maxPoint;
			int crossPoint;
			
			maxPoint = (fatherSize<motherSize)?fatherSize:motherSize;
			maxPoint--; //The point is inclusive, so, to avoid children equal to parents the maxpoint must be size-2
			crossPoint = (int) (Math.random()*maxPoint);
			//System.out.println("CRUZANDO EN "+crossPoint);
			//System.out.println("MADRE "+mother);
			//System.out.println("PADRE "+father);
			
			List<ListGenome> childs = rec.cross((ListGenome)father.getGenome(), (ListGenome)mother.getGenome(), crossPoint);
			for(ListGenome chG:childs){
				
				Individual ind = new BasicIndividual();
				ind.setGenome(chG);
				Fitness fit = this.fitnessCalculator.calculateFitness(ind);
				ind.setFitness(fit);
				offspring.add(ind);
				//System.out.println("HIJO  "+ind);
			}
			
		}
		return offspring;
	}
	/**
	 * Bind function to pick the FitnessCalculator service
	 * @param fitnessCalculator FitnessCalculator implementation
	 */
	public void setFitnessCalculator(FitnessCalculator fitnessCalculator){
		this.fitnessCalculator = fitnessCalculator;
	}
	/**
	 * Unbind function to release the FitnessCalculator service
	 * @param fitnessCalculator the implementation to release
	 */
	public void unsetFitnessCalculator(FitnessCalculator fitnessCalculator){
		this.fitnessCalculator = null;
	}
	

}
