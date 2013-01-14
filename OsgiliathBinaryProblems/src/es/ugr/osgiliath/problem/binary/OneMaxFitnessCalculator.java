package es.ugr.osgiliath.problem.binary;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.events.EventCreator;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BooleanGene;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.IntegerFitness;

public class OneMaxFitnessCalculator extends OsgiliathService implements FitnessCalculator {

	@Override
	public Fitness calculateFitness(Individual ind) {
		//this.getEventAdmin().sendEvent(EventCreator.createEvaluationsEvent(1, true));
		return calculate(ind);
	}

	@Override
	public ArrayList<Fitness> calculateFitnessForAll(ArrayList<Individual> inds) {
		ArrayList<Fitness> fits = new ArrayList<Fitness>();
		for(Individual ind:inds){
			Fitness f = calculate(ind);
			fits.add(f);
		}
		
		//this.getEventAdmin().sendEvent(EventCreator.createEvaluationsEvent(fits.size(), true));
		return fits;
	}

	private Fitness calculate(Individual ind){
		//System.out.println("WTF");
		
		List<Gene> genelist = ((ListGenome)ind.getGenome()).getGeneList();
		
		int total = 0;
		for(Gene g:genelist){
			BooleanGene bg = (BooleanGene) g;
			if(bg.getValue()==true)
				total++;
		}
			
		
	
		//MAXIMIZE THE NUMER OF 1
		Fitness f = new IntegerFitness(total,true);
		//this.getEventAdmin().sendEvent(EventCreator.createEvaluationsEvent(1, true));
		return f;
	}
}
