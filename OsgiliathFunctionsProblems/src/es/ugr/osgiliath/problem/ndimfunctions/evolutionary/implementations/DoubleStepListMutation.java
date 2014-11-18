/*
 * DoubleStepListMutation.java
 * 
 * Copyright (c) 2013, Pablo Garcia-Sanchez. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 * 
 * Contributors:
 */
package es.ugr.osgiliath.problem.ndimfunctions.evolutionary.implementations;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleGene;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.Mutation;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.problem.ndimfunctions.NdimFunctionProblemParameters;

public class DoubleStepListMutation extends OsgiliathService implements Mutation{

	@Override
	public Genome mutate(Genome genome) {
		ListGenome lg = (ListGenome) genome;
		
		int first = (int) (Math.random()*lg.getGeneList().size());
		
		DoubleGene dg = (DoubleGene) lg.getGeneList().get(first);
		double v = dg.getValue();
		
		double stepSize = (Double) this.getAlgorithmParameters().getParameter(NdimFunctionProblemParameters.STEPSIZE_PROP);
		
		
		
		
		double step = Math.random()*stepSize; //TODO ojo con esto!!
		if(Math.random()>0.5)
			step *=-1;
		dg.setValue(v+step);
		
		return lg;
		
	}

}
