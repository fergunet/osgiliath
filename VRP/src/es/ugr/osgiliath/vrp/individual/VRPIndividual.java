package es.ugr.osgiliath.vrp.individual;

import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.problem.InputData;
import es.ugr.osgiliath.problem.SolutionValue;
import es.ugr.osgiliath.vrp.VRPCost;

public class VRPIndividual implements Individual{

	VRPGenome genome;
	VRPCost cost;
	
	
	
	public Fitness getFitness() {
		return this.cost;
	}


	public Genome getGenome() {
		return this.genome;
		
	}


	public void setFitness(Fitness cost) {
		this.cost = (VRPCost) cost;
		
	}


	public void setGenome(Genome genome) {
		this.genome = (VRPGenome) genome;
		/*if(this.data!=null){
			this.genome.setData(this.data);
		}*/
		
	}


	/*public int isBetterThan(Individual o) {
		return cost.isBetterThan( ((VRPIndividual)o).getFitness() );
	}*/


	/*public int compareTo(Object o) {
		return cost.compareTo( ((VRPIndividual)o).getFitness() );
	}*/
	
	public String toString(){
		if(this.cost==null)
			return new String("NULLCOST0000000000"+":"+this.genome.toString());
		else
		return new String(this.cost.toString()+":"+this.genome.toString());
	}


	public void setInputData(InputData data) {
		//this.data=data;
		if(this.genome!=null){
			this.genome.setData(data);
		}
		
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

	
}
