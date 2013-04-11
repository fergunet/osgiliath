/*
 * BooleanFlipListMutation.java
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
package es.ugr.osgiliath.evolutionary.basiccomponents.operators;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BooleanGene;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.evolutionary.elements.Mutation;
import es.ugr.osgiliath.evolutionary.individual.Gene;
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
		
		//A number
		/*int genesize = lg.getGeneList().size();
		int genesToMutate = (int) (genesize*rate); 
				
		for(int i = 0; i<genesToMutate;i++){
			int gene = (int) (Math.random()*genesize);
				BooleanGene g = (BooleanGene) lg.getGeneList().get(gene);
				BooleanGene newg = new BooleanGene(!g.getValue());
				lg.getGeneList().set(gene, newg);
			
		}*/
		
		
		//One to one
		for(int i = 0; i<lg.getGeneList().size();i++){
			if(Math.random()<rate){
				BooleanGene bg = (BooleanGene) lg.getGeneList().get(i);
				BooleanGene newg = new BooleanGene(!bg.getValue());
				lg.getGeneList().set(i, newg);
				
			}
			
			
		}
		
		return lg;
	}

}
