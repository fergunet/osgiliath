package es.ugr.osgiliath.test;

import java.util.ArrayList;

import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class SOAPFitness implements FitnessCalculator{

	FitnessCalculator fi;
	
	public void activate(){
		System.out.println("SOAP activated");
	}

	@Override
	public DoubleFitness calculateFitness(Individual ind) {
		// TODO Auto-generated method stub
		System.out.println("ME LLAMAN CON SOAP");
		return new DoubleFitness(new Double(39),true);
	}

	@Override
	public ArrayList<Fitness> calculateFitnessForAll(ArrayList<Individual> inds) {
		// TODO Auto-generated method stub
		System.out.println("ME LLAMAN CON SOAP2");
		ArrayList<Fitness> all = new ArrayList<Fitness>();
		all.add(new DoubleFitness(new Double(39),true));
		all.add(new DoubleFitness(new Double(39),true));
		all.add(new DoubleFitness(new Double(39),true));
		return all;
	}
}
