package es.ugr.osgiliath.evolutionary.distances;

import java.util.List;

import es.ugr.osgiliath.evolutionary.individual.Individual;

public interface CrowdingDistanceAssignator {
	
	public void assignCrowdingDistance(List<Individual> individuals);

}
