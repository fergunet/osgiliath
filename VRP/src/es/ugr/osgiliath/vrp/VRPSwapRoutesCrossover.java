/*
 * VRPSwapRoutesCrossover.java
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
package es.ugr.osgiliath.vrp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.Crossover;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.vrp.individual.Route;
import es.ugr.osgiliath.vrp.individual.Shop;
import es.ugr.osgiliath.vrp.individual.VRPGenome;
import es.ugr.osgiliath.vrp.individual.VRPIndividual;

public class VRPSwapRoutesCrossover extends OsgiliathService implements Crossover{

	@Override
	public ArrayList<Genome> cross(Genome mother, Genome father) {
		
		VRPGenome a = this.createSon((VRPGenome)mother, (VRPGenome)father);
		VRPGenome b = this.createSon((VRPGenome)father, (VRPGenome)mother);
		ArrayList<Genome> childs = new ArrayList<Genome>();
		childs.add(a);
		childs.add(b);
		return childs;
	}
	
	VRPGenome createSon(VRPGenome first, VRPGenome second){
		
		VRPGenome mg = first;
		VRPGenome pg = second;
		
		//int dadCut = (int) (randmon.nextInt(p.genome.getRoutes().size()));
		//int momCut = (int) (randmon.nextInt(m.genome.getRoutes().size()));
		
		VRPIndividual a = new VRPIndividual();
		VRPGenome ga = new VRPGenome();
		//ga.setRoutes(new ArrayList<Route>()); creo que esto está inicializado ya en Genome... TODO
		//a.setInputData(m.data);
		//ga.setData(m.data);
		
		List<Route> routesToAdd = new ArrayList<Route>();
		Random randmon = new Random();
		
		//Adding all the mother routes
		for(int i = 0; i<mg.getRoutes().size();i++){
			Route r = new Route(mg.getRoutes().get(i));
            r.shopsVisited.clear();
            r.shopsVisited = (ArrayList<Shop>) mg.getRoutes().get(i).shopsVisited.clone();
            ga.getRoutes().add(r);			
		}
		
		//Select the father routes
		for(int i = 1; i<pg.getRoutes().size();i++){ //Not the initial
			int prob = (int) (randmon.nextInt(100));
			if(prob<50){
				Route r = new Route(pg.getRoutes().get(i));
	            r.shopsVisited.clear();
	            r.shopsVisited = (ArrayList<Shop>) pg.getRoutes().get(i).shopsVisited.clone();
	            routesToAdd.add(r);
			}			
		}
		//System.out.println("A a�adir"+routesToAdd);
		//Deleting the father shops of the genome
		for(Route r:routesToAdd){
			for(Shop s:r.shopsVisited){
				if(!s.shopID.equals("0")){ //NOT DELETE THE DEPOT
					for(Route delRoute:ga.getRoutes()){
						delRoute.shopsVisited.remove(s);
					}
				}
			}
			
		}
		//System.out.println("DELETED: "+ga.getRoutes());
		
		//Add the father routes
		for(Route r:routesToAdd)
			ga.getRoutes().add(r);
		
		//Remove the routes with 2 elements (but not the depot)		
		List<Route> toDelete = new ArrayList<Route>();
		boolean dep = true;
		for(Route r:ga.getRoutes()){
			if(dep == false && r.shopsVisited.size()==2)
				toDelete.add(r);
			dep = false;
		}
		
		for(Route r:toDelete)
			ga.getRoutes().remove(r);
		//a.setGenome(ga);
		return ga;
		
	}

}
