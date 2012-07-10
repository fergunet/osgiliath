package es.ugr.osgiliath.evolutionary.migrator;

import java.util.ArrayList;

import es.ugr.osgiliath.evolutionary.individual.Individual;

public interface Migrator {
	
	public void sendLOCAL(ArrayList<Individual> inds);
	
	public ArrayList<Individual> readLOCAL();
	
	public int getId();

	public void sendREMOTE(ArrayList<Individual> inds);
	
	public void reset();

}
