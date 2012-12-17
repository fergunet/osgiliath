package es.ugr.osgiliath.evolutionary.basiccomponents.operators;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.elements.Mutation;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Genome;



public class SwapListMutation extends OsgiliathService implements Mutation {

	@Override
	public Genome mutate(Genome genome) {
		ListGenome lgenome = (ListGenome)genome.clone();
		
		int first = (int) (Math.random()*lgenome.getGeneList().size());
		int second = (int) (Math.random()*lgenome.getGeneList().size());
		
		Gene firstg = lgenome.getGeneList().get(first);
		Gene secondg = lgenome.getGeneList().get(second);
		
		lgenome.getGeneList().set(first, secondg);
		lgenome.getGeneList().set(second, firstg);
		
		return lgenome;
	}

}
