package es.ugr.osgiliath.vrp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.Crossover;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.vrp.individual.Route;
import es.ugr.osgiliath.vrp.individual.VRPGenome;
import es.ugr.osgiliath.vrp.individual.VRPIndividual;

public class VRPSwapRoutesCrossover extends OsgiliathService implements Crossover{

	@Override
	public ArrayList<Genome> cross(Genome mother, Genome father) {
		

			VRPGenome mg = (VRPGenome) mother;
			VRPGenome pg = (VRPGenome) father;
			
			//int dadCut = (int) (randmon.nextInt(p.genome.getRoutes().size()));
			//int momCut = (int) (randmon.nextInt(m.genome.getRoutes().size()));
			
			
			VRPGenome ga = new VRPGenome();
			ga.getRoutes() = new ArrayList<Route>();
			
			
			
			List<Route> routesToAdd = new ArrayList<Route>();
			Random randmon = new Random();
			
			//Adding all the mother routes
			for(int i = 0; i<mg.getRoutes().size();i++){
				Route r = new Route(mg.routes.get(i));
	            r.shopsVisited.clear();
	            r.shopsVisited = (ArrayList<Shop>) mg.routes.get(i).shopsVisited.clone();
	            ga.getRoutes().add(r);			
			}
			
			//Select the father routes
			for(int i = 1; i<pg.getRoutes().size();i++){ //Not the initial
				int prob = (int) (randmon.nextInt(100));
				if(prob<50){
					Route r = new Route(pg.routes.get(i));
		            r.shopsVisited.clear();
		            r.shopsVisited = (ArrayList<Shop>) pg.routes.get(i).shopsVisited.clone();
		            routesToAdd.add(r);
				}			
			}
			//System.out.println("A a�adir"+routesToAdd);
			//Deleting the father shops of the genome
			for(Route r:routesToAdd){
				for(Shop s:r.shopsVisited){
					if(!s.shopID.equals("0")){ //NOT DELETE THE DEPOT
						for(Route delRoute:ga.routes){
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
			a.setGenome(ga);
			return a;
			
		}
	}

}
