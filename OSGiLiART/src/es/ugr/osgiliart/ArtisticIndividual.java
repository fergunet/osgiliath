package es.ugr.osgiliart;

import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.problem.SolutionValue;

public class ArtisticIndividual extends BasicIndividual {	
	protected String  imagePath = null;
	protected int generation = -1;
	protected int uniqId = -1;
	
	public ArtisticIndividual() {
	
	}
	
	
	
	public String getImagePath() {
		return imagePath;
	}



	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}



	public int getGeneration() {
		return generation;
	}



	public void setGeneration(int generation) {
		this.generation = generation;
	}



	public int getUniqId() {
		return uniqId;
	}



	public void setUniqId(int uniqId) {
		this.uniqId = uniqId;
	}



	public String getId () {
		return String.format("gen%03d_id%04d", generation, uniqId);
	}
		
}
