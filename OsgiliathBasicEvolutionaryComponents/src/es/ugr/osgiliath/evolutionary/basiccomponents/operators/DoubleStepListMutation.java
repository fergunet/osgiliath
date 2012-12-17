package es.ugr.osgiliath.evolutionary.basiccomponents.operators;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleGene;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.Mutation;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class DoubleStepListMutation extends OsgiliathService implements Mutation{

	@Override
	public Genome mutate(Genome genome) {
		ListGenome lg = (ListGenome) genome;
		
		int first = (int) (Math.random()*lg.getGeneList().size());
		
		DoubleGene dg = (DoubleGene) lg.getGeneList().get(first);
		double v = dg.getValue();
		
		double stepSize = (Double) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.MUTATOR_STEPSIZE);
		
		double step = Math.random()*stepSize; //TODO ojo con esto!!
		if(Math.random()>0.5)
			step *=-1;
		dg.setValue(v+step);
		
		return lg;
		
	}

}
