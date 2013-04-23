/*
 * MOP2FitnessCalculator.java
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
package es.ugr.osgiliath.problem.mofunctions;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.ListGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleGene;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.MultiObjectiveDoubleFitness;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.MultiObjectiveFitness;
import es.ugr.osgiliath.problem.ndimfunctions.NdimFunctionSolution;
import es.ugr.osgiliath.problem.ndimfunctions.NdimFunctionValue;
import org.osgi.service.component.ComponentContext;
public class MOP2FitnessCalculator extends OsgiliathService implements FitnessCalculator {

	Dictionary dict;
	
	public void activate(){}
	public void activate(ComponentContext context){
		System.out.println("SOY EL FITNESS MOP2 Y ME INICIO");
		dict = context.getProperties();
		
		for (Enumeration e = dict.keys() ; e.hasMoreElements() ;) {
			Object key = e.nextElement();
	         System.out.println(key+" "+dict.get(key));

	     }
		
			
		
	}

	/*public void activate(ComponentContext context){
		System.out.println("Con component context");
		Dictionary dict = context.getProperties();
		ServiceReference sr = context.getServiceReference();
		System.out.println(dict); //QUITA LA DEPENDENCIA DEL MANIFEST!!!
		dict.put("ecf.exported.containerfactoryargs", "mecagoenlalelche");
		
	}*/
	/*public void mierda(Map properties){
		System.out.println("INICIANDO FITNESS CALCULATOR");
		System.out.println(properties);
		String url = (String) properties.get("ecf.exported.containerfactoryargs");
		System.out.println("CHANGING URL"+url);
		properties.put("ecf.exported.containerfactoryargs","nuevaurl" );
		properties.put("ojo","yeja");
		
	}*/
	
	public void modified(){
		System.out.println("ME HAN MODIFICADO");
		for (Enumeration e = dict.keys() ; e.hasMoreElements() ;) {
			Object key = e.nextElement();
	         System.out.println(key+" "+dict.get(key));

	     }
	}
	
	@Override
	public Fitness calculateFitness(Individual ind) {
		
		ListGenome genome = (ListGenome) ind.getGenome();
		ArrayList<Gene> genes = genome.getGeneList();
		List<Double> points = new ArrayList<Double>();
		
		for(Gene g:genes){
			DoubleGene dg = (DoubleGene) g;
			points.add(new Double(dg.getValue()));
		}
		
		MOP2 mop2 = new MOP2();
		
		NdimFunctionSolution sol = new NdimFunctionSolution();
		sol.setPoints(points);
		
		NdimFunctionValue value = mop2.evaluate(sol);
		boolean[] toMaximize = new boolean[2];
		toMaximize[0]=false;
		toMaximize[1]=false;
		MultiObjectiveFitness mo = new MultiObjectiveDoubleFitness(value.getValues(),toMaximize );
		return mo;
	}

	@Override
	public ArrayList<Fitness> calculateFitnessForAll(ArrayList<Individual> inds) {
		ArrayList<Fitness> allFitness = new ArrayList<Fitness>();
		for(Individual ind:inds){
			Fitness f = calculateFitness(ind);
			allFitness.add(f);
		}
		
		return allFitness;
			
	}


}
