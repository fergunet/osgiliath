package es.ugr.osgiliath.vrp;


import java.util.ArrayList;
import java.util.List;


import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.Initializer;
import es.ugr.osgiliath.problem.InputData;
import es.ugr.osgiliath.vrp.individual.ITPdata;
import es.ugr.osgiliath.vrp.individual.Shop;
import es.ugr.osgiliath.vrp.individual.VRPGenome;
import es.ugr.osgiliath.vrp.individual.VRPIndividual;

public class VRPRandomInitializer implements Initializer{

	ITPdata data;

	FitnessCalculator fc;
	
	public Individual initeIndividual() {
		VRPIndividual ind = new VRPIndividual();
		
		VRPGenome gen = new VRPGenome();
		gen.setData(data);
		ArrayList<Shop> shops = new ArrayList<Shop>();
		Shop theDepot = data.shopList.get(0);
		for(int i = 1; i<data.shopList.size();i++)
			shops.add(data.shopList.get(i));
		try {
			gen.setAsRandomInitialSolution(theDepot, shops);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double dCost = gen.getCost();
		VRPCost cost = new VRPCost(dCost);
		
		ind.setGenome(gen);
		ind.setFitness(cost);
		//ind.data = data;
		return ind;
	}


	/*public void setInputData(InputData data) {
		this.data = (ITPdata)data;
		
	}*/


	@Override
	public ArrayList<Individual> initializeIndividuals(int size) {
		ArrayList<Individual> inds = new ArrayList<Individual>();
		
		for(int i = 0; i<size;i++){
			Individual ind = this.initeIndividual();
			inds.add(ind);
		}
		
		return inds;
	}
	
	public void setFitnessCalculator(FitnessCalculator fc){
		this.fc = fc;
	}
	
	public void unsetFitnessCalculator(FitnessCalculator fc){
		this.fc = null;
	}

	
	

}
