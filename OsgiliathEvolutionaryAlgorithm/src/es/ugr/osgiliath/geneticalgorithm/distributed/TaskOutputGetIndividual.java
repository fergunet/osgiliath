package es.ugr.osgiliath.geneticalgorithm.distributed;

import java.util.List;

import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.network.TaskOutput;

public class TaskOutputGetIndividual implements TaskOutput{
	
	private List<Individual> list;
	
	TaskOutputGetIndividual(List<Individual> list) {
		this.list = list;
	}
	
	public List<Individual> getList(){ 
		return this.list;
	}
	
	
	
	

}
