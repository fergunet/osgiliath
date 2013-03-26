package es.ugr.geneticalgorithmVRP.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.ugr.osgiliath.evolutionary.elements.Recombinator;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.vrp.individual.VRPGenome;
import es.ugr.osgiliath.vrp.individual.VRPIndividual;
import es.ugr.geneticalgorithmVRP.problem.Shop;
import es.ugr.geneticalgorithmVRP.problem.Route;

public class VRPCrossover implements Recombinator{

	public List<Individual> cross(Individual mother, Individual father) {
		Random randmon = new Random();
		
		//System.out.println("MOTHER:"+mother.toString());
		//System.out.println("FATHER:"+father.toString());
		
		
		VRPIndividual a = this.createSon((VRPIndividual)mother, (VRPIndividual)father);
		VRPIndividual b = this.createSon((VRPIndividual)father, (VRPIndividual)mother);
		//System.out.println("CHILDA:"+a.toString());
		//System.out.println("CHILDB:"+b.toString());
		List<Individual> childs = new ArrayList<Individual>();
		childs.add(a);
		childs.add(b);
		
		return childs;
	}
	
	
	
	public List<Individual> maaaal(Individual mother, Individual father) {
		Random randmon = new Random();
		
		System.out.println("MOTHER:"+mother.toString());
		System.out.println("FATHER:"+father.toString());
		
		VRPIndividual m = (VRPIndividual) mother;
		VRPGenome mg = (VRPGenome) m.getGenome();
		VRPIndividual p = (VRPIndividual) father;
		VRPGenome pg = (VRPGenome) p.getGenome();
		
		int dadCut = (int) (randmon.nextInt(p.genome.getRoutes().size()));
		int momCut = (int) (randmon.nextInt(m.genome.getRoutes().size()));
		
		VRPIndividual a = new VRPIndividual();
		VRPGenome ga = new VRPGenome();
		ga.routes = new ArrayList<Route>();
		a.setInputData(m.data);
		ga.setData(m.data);
		
		
		VRPIndividual b = new VRPIndividual();
		VRPGenome gb = new VRPGenome();
		gb.routes = new ArrayList<Route>();
		b.setInputData(m.data);
		gb.setData(m.data);
		
		//CHILD A
		for(int i = 0; i<dadCut; i++){			
			Route r = new Route(pg.routes.get(i));
            r.shopsVisited.clear();
            r.shopsVisited = (ArrayList<Shop>) pg.routes.get(i).shopsVisited.clone();
            ga.routes.add(r);
		}
		
		for(int i = momCut; i<mg.routes.size(); i++){			
			Route r = new Route(mg.routes.get(i));
            r.shopsVisited.clear();
            r.shopsVisited = (ArrayList<Shop>) mg.routes.get(i).shopsVisited.clone();
            ga.routes.add(r);
		}
		a.setGenome(ga);
		//CHILD B
		for(int i = 0; i<momCut; i++){			
			Route r = new Route(mg.routes.get(i));
            r.shopsVisited.clear();
            r.shopsVisited = (ArrayList<Shop>) mg.routes.get(i).shopsVisited.clone();
            gb.routes.add(r);
		}
		
		for(int i = dadCut; i<pg.routes.size(); i++){			
			Route r = new Route(pg.routes.get(i));
            r.shopsVisited.clear();
            r.shopsVisited = (ArrayList<Shop>) pg.routes.get(i).shopsVisited.clone();
            gb.routes.add(r);
		}
		
		b.setGenome(gb);
		
		System.out.println("CHILDA:"+a.toString());
		System.out.println("CHILDB:"+b.toString());
		List<Individual> childs = new ArrayList<Individual>();
		childs.add(a);
		childs.add(b);
		
		return childs;
		
		
		
		
	}
	
	VRPIndividual createSon(VRPIndividual first, VRPIndividual second){
		VRPIndividual m = (VRPIndividual) first;
		VRPGenome mg = (VRPGenome) m.getGenome();
		VRPIndividual p = (VRPIndividual) second;
		VRPGenome pg = (VRPGenome) p.getGenome();
		
		//int dadCut = (int) (randmon.nextInt(p.genome.getRoutes().size()));
		//int momCut = (int) (randmon.nextInt(m.genome.getRoutes().size()));
		
		VRPIndividual a = new VRPIndividual();
		VRPGenome ga = new VRPGenome();
		ga.routes = new ArrayList<Route>();
		a.setInputData(m.data);
		ga.setData(m.data);
		
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
