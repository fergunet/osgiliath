package es.ugr.osgiliath.evolutionary.individual;

import java.util.ArrayList;
import java.util.List;


public interface Initializer {

	public ArrayList<Individual> initializeIndividuals(int size);
	//public void setInputData(InputData data);
}
