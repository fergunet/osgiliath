package es.ugr.osgiliath.evolutionary.elements;

import es.ugr.osgiliath.evolutionary.individual.Genome;

public interface Mutation {

	public Genome mutate(Genome genome);
}
