package es.ugr.osgiliath.geneticalgorithm.distributed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import ch.ethz.iks.r_osgi.RemoteOSGiService;
import es.ugr.osgiliath.algorithms.Algorithm;
import es.ugr.osgiliath.algorithms.AlgorithmParameters;
//import es.ugr.osgiliath.algorithms.Solution;
//import es.ugr.osgiliath.evolutionary.components.GeneticParameters;
//import es.ugr.osgiliath.evolutionary.components.individual.Initer;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.Mutator;
import es.ugr.osgiliath.evolutionary.elements.ParentSelector;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.elements.Recombinator;
import es.ugr.osgiliath.evolutionary.elements.StopCriterion;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.network.Task;
import es.ugr.osgiliath.network.TaskOutput;
import es.ugr.osgiliath.problem.Problem;
import es.ugr.osgiliath.utils.Stopwatch;

public class DistributedIslandGeneticAlgorithm  {//implements Algorithm{

/*	List<Task> remoteTasks = new ArrayList<Task>();
	List<Task> exposedTasks = new ArrayList<Task>();
	
	Problem problem;
	RemoteOSGiService remote;
	//StopCriterion stopCriterion;
	
	Population pop;
	GeneticParameters params;
	Initer initer;
	FitnessCalculator objFunc;
	Recombinator cross;
	Mutator mut;
	Fitness theBestFitness;
	
	//Tournament tournament; En los siguientes
	//TournamentSelector...
	//Mutation mutation; En los siguientes
	int iterations; //En los siguientes...
	int totalIterations;
	int migratorsReceived;
	
	
	public DistributedIslandGeneticAlgorithm(){};
	
	

	public void addRemoteTask(Task t) {
		remoteTasks.add(t);
		
	}
	

	public void addExposedTask(Task t) {
		exposedTasks.add(t);
		
	}

	//GETTERS AND SETTERS
	
	
	public Initer getIniter() {
		return initer;
	}


	public void setIniter(Initer initer) {
		System.out.println("[Setting Initer]");
		this.initer = initer;
	}
	
	public void unsetIniter(Initer initier){
		this.initer = null;
	}


	public void setObjectiveFunction(FitnessCalculator objFunc) {
		System.out.println("[Setting Objective Function]");
		this.objFunc = objFunc;
	}


	public void unsetObjectiveFunction(FitnessCalculator objFunc) {
		 objFunc = null;
	}



	public AlgorithmParameters getParameters() {
		
		return null;
	}



	public void setParameters(AlgorithmParameters params) {
		System.out.println("[Setting Parameters]");
		this.params = (GeneticParameters)params;
		
	}


	public void unsetParameters(AlgorithmParameters params) {
		this.params=null;
		
	}


	//GETTERS AND SETTERS
	
	

	public Problem getProblem() {
		return this.problem;
	}



	public void setProblem(Problem problem) {
		System.out.println("[Setting Problem]");
		this.problem=problem;
		
	}


	public void unsetProblem(Problem problem) {
		System.out.println("Unsetting problem");
		problem = null;		
		
	}

	public void setCrossover(Recombinator c){
		System.out.println("[Setting crossover]");
		this.cross = c;
	}
	
	public void unsetCrossover(Recombinator c){
		System.out.println("Unsetting crossover");
		this.cross = null;
	}
	
	public void setMutator(Mutator m){
		System.out.println("[Setting mutator]");
		this.mut = m;
	}
	
	public void unsetMutator(Mutator m){
		System.out.println("Unsetting mutator");
		this.mut = null;
	}
	
	


	public void setRemote(RemoteOSGiService r) {
		this.remote = r;
		
	}


	public Solution solve(Problem p) {
		// TODO Auto-generated method stub
		return null;
	}


	public void start() {
		pop = new Population();
		Stopwatch sw = new Stopwatch();
		sw.start();
		this.initer.setInputData(this.problem.getInputData());
		this.objFunc.setInputData(this.problem.getInputData());
		iterations = 0;
		totalIterations=0;
		this.theBestFitness=null;
		System.out.println("STARTING DISTRIBUTED GENETIC ALGORITHM");
		params.setup();
		problem.setup();
		
//		for(Task t:remoteTasks){
//			System.out.println("Calling to task of the owner "+t.getOwner());
//			t.calculateTask(null);
//		}
		
		System.out.println("Initializing population");
		
		for(int i = 0; i<this.params.getPopulationSize();i++){
			Individual ind = initer.initeIndividual();
			Fitness indCost = this.objFunc.evaluate(ind);
			ind.setFitness(indCost);
			pop.addIndividual(ind);
		}
		
		
		for(Individual in:pop.getList()){
			//System.out.println("IND:"+in.toString());
		}
		
		
		do{
			
			List<Individual> offspring = crossoverPopulation(this.tournament());
			try{
			this.mutate(offspring);
			}catch(Exception ex){ex.printStackTrace();}

			pop.getList().addAll(offspring);
			pop.sort();
			//System.out.println("Size:"+pop.getSize());
			
			this.migratorsReceived = 0;
			if(totalIterations%10==0){
				this.sendMigrators();
				this.receiveMigrators();
			}
			
			//int deleteN = pop.getSize()/2;
			int deleteN = offspring.size() + this.migratorsReceived;
			for(int i=0;i<deleteN;i++){
				Individual lastElement = pop.getList().get(pop.getSize()-1);
				pop.getList().remove(lastElement);
			}
			
			//System.out.println("The Best is "+pop.getList().get(0));
			//System.out.println("The Last is "+pop.getList().get(pop.getSize()-1));
			totalIterations++;
			
			
		}while(!stopCriterion());
		
		sw.stop();
		System.out.println(this.remoteTasks.size()+":"+pop.getList().get(0).getFitness()+":"+sw.toString()+":"+totalIterations);
		this.problem.getLogger().info(this.remoteTasks.size()+":"+pop.getList().get(0).getFitness()+":"+sw.toString()+":"+totalIterations);
		this.remoteTasks = new ArrayList<Task>();//TODO esto es una cafrada
		this.exposedTasks = new ArrayList<Task>();
		
		
	}
	
	public boolean stopCriterion(){
		iterations++;
		Fitness theFirst = pop.getList().get(0).getFitness();
		if (theBestFitness == null)
			theBestFitness = theFirst;
		
		if (theFirst.isBetterThan(this.theBestFitness)==1){
			iterations=0;
			theBestFitness = theFirst;
			//System.out.println("Best founded "+theBestFitness);
			return false;
		}
		
		if(iterations == 60){
			return true;
		}
		
		return false;
	}
	
	public boolean stopCriterionTOX(){
		iterations++;
		if (iterations == 200)
			return true;
		return false;
	}

	public List<Individual> crossoverPopulation(List<Individual> parents){
		List<Individual> offspring = new ArrayList<Individual>();
		
		for(int i=0; i<parents.size();i=i+2){
			Individual mother = parents.get(i);
			Individual father = parents.get(i+1);
			List<Individual>childs = this.cross.cross(mother, father);
			childs.get(0).setFitness(this.objFunc.evaluate(childs.get(0)));
			childs.get(1).setFitness(this.objFunc.evaluate(childs.get(1)));
			offspring.add(childs.get(0));
			offspring.add(childs.get(1));
		}
		return offspring;
		
		
	}
	
	public void mutate(List<Individual> offspring){
		for(Individual ind:offspring){
			
		 double prob = (double) Math.random()*100;
		 
		 if(prob < this.params.getMutationProb()){
			 this.mut.mutate(ind);
			 ind.setFitness(this.objFunc.evaluate(ind));
		 }
		 
		}
		
			 
	}
	
//	public List<Individual> tournament(){
//		List<Individual> selected = new ArrayList<Individual>();
//		for(int i = 0; i<pop.getSize();i++)
//			selected.add(pop.getList().get(i));
//		return selected;
//	}
	
	public List<Individual> tournament(){
		List<Individual> selected = new ArrayList<Individual>();
		for(int i = 0; i<this.params.getTournamentSize();i++)
			selected.add(pop.getList().get(i));
		
		Collections.shuffle(selected);
		return selected; 
			
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
	public void receiveMigrators(){
		this.migratorsReceived = 0;
		for(Task t:remoteTasks){
			System.out.println("Calling to task of the owner "+t.getOwner());
			TaskOutputGetIndividual to = (TaskOutputGetIndividual) t.calculateTask(null);
			if(to==null || to.getList() == null || to.getList().size()==0){
			//	System.out.println("MIGRATION: NOTHING");
				return;
			}
			this.migratorsReceived+=to.getList().size();
			//System.out.println("MIGRATION: RECEIVED "+this.migratorsReceived);
			this.pop.getList().addAll(to.getList());

				
				
		}
	}
	
	public void sendMigrators(){
	//	System.out.println("Adding migrators to send");
		GetIndividualsTask t = (GetIndividualsTask) this.exposedTasks.get(0); //TODO puede haber m�s
		List<Individual> toSend = new ArrayList<Individual>();
		for(int i = 0; i< this.params.getMigrationSize();i++)
			toSend.add(pop.getList().get(i));
		t.setIndividualsToSent(toSend);
		
	}*/

}
