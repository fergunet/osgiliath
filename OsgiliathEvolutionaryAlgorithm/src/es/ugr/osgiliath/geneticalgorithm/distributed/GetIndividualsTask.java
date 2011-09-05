package es.ugr.osgiliath.geneticalgorithm.distributed;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.network.Task;
import es.ugr.osgiliath.network.TaskInput;
import es.ugr.osgiliath.network.TaskOutput;

public class GetIndividualsTask implements Task{

	String owner;
	List<Individual> list;
	
	public GetIndividualsTask(String owner){

		this.owner = owner;
		//como sea esto 
	}
	

	public TaskOutput calculateTask(TaskInput it) {
		System.out.println("SOMEONE CALL ME!!!");
		List<Individual> toSend = new ArrayList<Individual>();
		
		if(list == null)	
			return null;
		if(list.size()==0)
			return null;
		Random randmon = new Random();
		int selected = (int) (randmon.nextInt(this.list.size()));
		
		toSend.add(list.get(selected));
		TaskOutput to = new TaskOutputGetIndividual(toSend);
		return to;
	}


	public int nada() {
		System.out.println("GA: NADA");
		return 0;
	}
	
	public void setIndividualsToSent(List<Individual> list){
		this.list = list;
		
	}


	public String getOwner() {
		return owner;
	}

}
