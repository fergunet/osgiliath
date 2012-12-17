package es.ugr.osgiliath.evolutionary.basiccomponents.operators;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BooleanGene;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.Mutation;
import es.ugr.osgiliath.evolutionary.individual.Genome;


public class BooleanFlipListMutation extends OsgiliathService implements Mutation {

	@Override
	public Genome mutate(Genome genome) {
		//ListGenome lg = (ListGenome) genome.clone(); //TODO esto esta bien?
		ListGenome lg = (ListGenome) genome;
		
		//SOLO 1
		/*int first = (int) (Math.random()*lg.getGeneList().size());
		
		BooleanGene g = (BooleanGene) lg.getGeneList().get(first);
		BooleanGene newg = new BooleanGene(!g.getValue());
		lg.getGeneList().set(first, newg);
		*/
		
		
		double rate = (Double) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.MUTATOR_PROB);
		int genesize = lg.getGeneList().size();
		int genesToMutate = (int) (genesize*rate); 
				
		for(int i = 0; i<genesToMutate;i++){
			int gene = (int) (Math.random()*genesize);
				BooleanGene g = (BooleanGene) lg.getGeneList().get(gene);
				BooleanGene newg = new BooleanGene(!g.getValue());
				lg.getGeneList().set(gene, newg);
			
		}
				
		return lg;
	}

}
