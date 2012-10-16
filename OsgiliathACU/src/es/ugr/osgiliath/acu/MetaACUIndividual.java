package es.ugr.osgiliath.acu;

import java.util.ArrayList;

import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;


public class MetaACUIndividual extends BasicIndividual{
	
	int numACUs;
	double migrationProb;
	String islandId;
	
	ArrayList<MetaACUIndividual> fathers;
	
	
	public MetaACUIndividual() {
		numACUs = 0;
	}
	
	public int getACUs(){
		return this.numACUs;
	}
	
	public void increaseACUs(int numACUs){
		this.numACUs += numACUs;
	}
	
	public void decreaseACUs(int numACUs){
		this.numACUs -= numACUs;
	}

	public double getMigrationProb() {
		return migrationProb;
	}
	
	public void setMigrationProb(double migrationProb) {
		this.migrationProb = migrationProb;
	}
	
	public ArrayList<MetaACUIndividual> getFathers() {
		return fathers;
	}
	
	public void setFathers(ArrayList<MetaACUIndividual> fathers) {
		this.fathers = fathers;
	}
	
	public String getIslandId(){
		return this.islandId;
	}
	
	public void setIslandId(String id){
		this.islandId = id;
	}
	
	public String toString(){
		return "["+this.getFitness().toString()+"] "+this.getACUs()+" ACUs. Prob:"+this.migrationProb;
	}
}
