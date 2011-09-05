package es.osgiliath.evolutionary.basicimplementations.combinators;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;

import es.ugr.osgiliath.evolutionary.basiccomponents.simpleoperators.BasicUPXList;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.Recombinator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class BasicUPXRecombinator extends OsgiliathService implements Recombinator {
	
	FitnessCalculator fitnessCalculator;

	@Override
	public List<Individual> recombine(List<Individual> parents) {
		System.out.println("UPX");
		List<Individual> offspring = new ArrayList<Individual>();
		for(int i=0;i<parents.size()-1;i=i+2){
			Individual father = parents.get(i);
			Individual mother = parents.get(i+1);			
			
			int fatherSize = father.getGenome().getGeneList().size(); //TODO quitar el getGeneList de Genome
		    int motherSize = mother.getGenome().getGeneList().size();
			int minSize = (fatherSize<motherSize)?fatherSize:motherSize;
		    BasicUPXList rec = new BasicUPXList();	
		    
			List<Double> probs = new ArrayList<Double>();
			for(int p = 0;p<minSize;p++)
				probs.add(new Double(0.5));
			//System.out.println("FATHER "+father);
			//System.out.println("MOTHER "+mother);
			List<ListGenome> childs = rec.cross((ListGenome)father.getGenome(), (ListGenome)mother.getGenome(), probs);
			
			for(ListGenome chG:childs){
				
				Individual ind = new BasicIndividual();
				ind.setGenome(chG);
				Fitness fit = this.fitnessCalculator.calculateFitness(ind);
				ind.setFitness(fit);
				offspring.add(ind);
				//System.out.println("SON  "+ind);
			}
			
		}
		return offspring;
	}
	public void setFitnessCalculator(FitnessCalculator fitnessCalculator){
		this.fitnessCalculator = fitnessCalculator;
	}
	
	public void unsetFitnessCalculator(FitnessCalculator fitnessCalculator){
		this.fitnessCalculator = null;
	}

}
