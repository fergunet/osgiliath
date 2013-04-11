/*
 * VRPRandomInitializer.java
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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;



import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.Initializer;
import es.ugr.osgiliath.vrp.individual.Route;
import es.ugr.osgiliath.vrp.individual.Shop;
import es.ugr.osgiliath.vrp.individual.VRPGenome;
import es.ugr.osgiliath.vrp.individual.VRPIndividual;

public class VRPRandomInitializer implements Initializer{

	VRPData data;

	FitnessCalculator fc;
	
    private int MAX_ITERATIONS = 100000; //To create an initial random solution
	
	public Individual initeIndividual() {
		VRPIndividual ind = new VRPIndividual();
		
		VRPGenome gen = new VRPGenome();
		//gen.setData(data);
		ArrayList<Shop> shops = new ArrayList<Shop>();
		Shop theDepot = data.shopList.get(0);
		for(int i = 1; i<data.shopList.size();i++)
			shops.add(data.shopList.get(i));
		try {
			gen = this.setAsRandomInitialSolution(theDepot, shops);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double dCost = gen.getCost();
		DoubleFitness cost = new DoubleFitness(dCost,true);
		
		ind.setGenome(gen);
		ind.setFitness(cost);
		//ind.data = data;
		return ind;
	}


	/*public void setInputData(InputData data) {
		this.data = (ITPdata)data;
		
	}*/


	@Override
	public ArrayList<Individual> initializeIndividuals(int size) {
		ArrayList<Individual> inds = new ArrayList<Individual>();
		
		for(int i = 0; i<size;i++){
			Individual ind = this.initeIndividual();
			inds.add(ind);
		}
		
		return inds;
	}
	
	public void setFitnessCalculator(FitnessCalculator fc){
		this.fc = fc;
	}
	
	public void unsetFitnessCalculator(FitnessCalculator fc){
		this.fc = null;
	}

	public void initializeSolution() {
		ArrayList<Shop> shops = new ArrayList<Shop>();
		Shop theDepot = data.shopList.get(0);
		for(int i = 1; i<data.shopList.size();i++)
			shops.add(data.shopList.get(i));
		//this.setAsClarkeWrightInitialSolution(shops);
		//this.setAsDaisyInitialSolution(theDepot,shops);
		try {
			
			//this.setAsRandomInitialSolution(theDepot,shops);
		} catch (Exception e) {
			System.out.println("ERROR AL INICIAR");
			e.printStackTrace();
		}
	}
	
	  /**
     * Creates an initial solution 
     * @param data
     */
    public VRPGenome setAsRandomInitialSolution(Shop theDepot, List<Shop> theShops) throws Exception {
        // Shop theStore = data.shopList.get(0);
    	VRPGenome gen = new VRPGenome();
    	
        Shop theStore = theDepot;
        boolean solutionInvalid = true;
        for (int i = 1; i < theShops.size(); i++) {
            Shop shop = theShops.get(i);
            Route r = new Route();
            r.shopsVisited.add(theStore);
            r.shopsVisited.add(shop);
            r.shopsVisited.add(theStore);
            gen.getRoutes().add(r);
        }

        /* List<Shop> theShops = new ArrayList<Shop>();
        for (int i = 1; i < data.shopList.size(); i++) {
        theShops.add(data.shopList.get(i));
        }*/


        ArrayList<Route> newRoutes = new ArrayList<Route>();


        int iterations = 0;
        do {
        	gen.getRoutes().clear();
            newRoutes.clear();

            for (int i = 0; i < theShops.size(); i++) {
                Route r = new Route();
                newRoutes.add(r);
            }
            Random randmon = new Random();
            //randmon.setSeed(1234);
            solutionInvalid = false;
            //Mejor habría que clonar antes!
            Collections.shuffle(theShops,randmon);
           
            for (Shop s : theShops) {
               // int numRoute = (int) (Math.random() * theShops.size());
            	int numRoute = (int) (randmon.nextInt(theShops.size()));
                newRoutes.get(numRoute).shopsVisited.add(s);
            }

            double cost = 0.0;
            double time = 0.0;
            double outatime = 0.0;
            for (Route r : newRoutes) {
                outatime = 0.0;
                r.shopsVisited.add(0, theStore);
                r.shopsVisited.add(theStore);

                if (r.shopsVisited.size() != 2) {

                    double distance = data.calculateDistance(r);

                    cost += data.calculateCost(r);
                    outatime += data.calculateTime(r);
                    time += outatime;
                    if (r.demand > data.vehicleCapacity || outatime > data.maximumWorkTime) {
                        solutionInvalid = true;
                    }
                    gen.getRoutes().add(r);
                }
            }

            Route emptyRoute = new Route();
            emptyRoute.shopsVisited.add(theStore);
            emptyRoute.shopsVisited.add(theStore);
            gen.getRoutes().add(0, emptyRoute);

            //this.routes = newRoutes;

            iterations++;

            if (iterations > MAX_ITERATIONS) {
                throw new Exception("Could not create an " +
                        "initial solution in " + MAX_ITERATIONS + " iterations");
            }

        } while (solutionInvalid);

    //System.out.println("INITIAL: "+this.toString());
        return gen;
    }

    ////////////////////////CLARKE WRIGHT///////////////////////
    

    public void setAsClarkeWrightInitialSolution(List<Shop> theShops){
   /*     SimpleCW simpleCW = new SimpleCW(theShops, data);
        simpleCW.findRoutes();
        this.routes = simpleCW.bestSolution;
        Route emptyRoute = new Route();
        emptyRoute.shopsVisited.add(data.shopList.get(0));
        emptyRoute.shopsVisited.add(data.shopList.get(0));
        this.routes.add(0, emptyRoute);*/
    }
    /////////////////////////DAISY//////////////////////////////
    public VRPGenome setAsDaisyInitialSolution(Shop theDepot,List<Shop> theShops) {

    	VRPGenome gen = new VRPGenome();

        SortedMap<Double, Integer> map;
      
        map = new TreeMap();
        ArrayList<ArrayList<Route>> groupedRoutes = new ArrayList<ArrayList<Route>>();
        for (Shop s:theShops) {
        	
            double angle = Math.atan2(s.coordY - theDepot.coordY, s.coordX - theDepot.coordX);

            map.put(new Double(angle), Integer.parseInt(s.shopID));
           // System.out.println("SHOP " + s.shopID + "\tAngle " + angle + "\tMap size " + map.size() + "\n");
        }

        Double[] orderedAngles = new Double[theShops.size()];
        Iterator<Double> it = map.keySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            orderedAngles[i] = it.next();
            i++;
        }

//        for (i = 0; i < orderedAngles.length; i++) {
//            System.out.println(i + " " + orderedAngles[i]);
//        }
        for (i = 0; i < orderedAngles.length; i++) {
            ArrayList<Route> partialRoutes = getPetals(gen, orderedAngles, i, map, theShops, theDepot);
            groupedRoutes.add(partialRoutes);
        }
        
        Double[] reverseOrderedAngles = new Double[theShops.size()];
        
        for(int j = orderedAngles.length-1; j>=0;j--)
            reverseOrderedAngles[j]=orderedAngles[j];
        for (i = 0; i < orderedAngles.length; i++) {
            ArrayList<Route> partialRoutes = getPetals(gen, reverseOrderedAngles, i, map, theShops, theDepot);
            groupedRoutes.add(partialRoutes);
        }
        
        ArrayList<Route> bestRoutes = groupedRoutes.get(0);
        double bestCost = this.getCostOfGroupedRoutes(bestRoutes);
        for(int j=0; j<groupedRoutes.size();j++){
            ArrayList<Route> actuals = groupedRoutes.get(j);
            double actualcost = getCostOfGroupedRoutes(actuals);
            if(actualcost<bestCost)
                bestRoutes = actuals;
        }
            
        gen.setRoutes(bestRoutes);

//        for (Route r : this.routes) {
//            System.out.println("Ruta:" + r);
//        }
        return gen;

    }

    private ArrayList<Route> getPetals(VRPGenome gen, Double[] orderedAngles, int initialShop,
            SortedMap<Double, Integer> map, List<Shop> theShops, Shop theDepot) {
        ArrayList<Route> finalRoutes = new ArrayList<Route>();

    
        Route newRoute = new Route();
        newRoute.shopsVisited.add(theDepot);
        for (int i = 0; i < orderedAngles.length; i++) {


            Double angle = orderedAngles[(i + initialShop) % orderedAngles.length];

            Shop s = getShopById(theShops, map.get(angle));
            newRoute.shopsVisited.add(s);
            newRoute.shopsVisited.add(theDepot);

            double distance = data.calculateDistance(newRoute);

            double cost = data.calculateCost(newRoute);
            double time = data.calculateTime(newRoute);

            if (newRoute.demand > data.vehicleCapacity || time > data.maximumWorkTime) {
                newRoute.shopsVisited.remove(s);
                finalRoutes.add(newRoute);
                newRoute = new Route();
                newRoute.shopsVisited.add(theDepot);
                newRoute.shopsVisited.add(s);
            } else {
                newRoute.shopsVisited.remove(newRoute.shopsVisited.size() - 1);
            }

        }
        newRoute.shopsVisited.add(theDepot);
        finalRoutes.add(newRoute);

        //Always add the 0-0 route
        Route emptyRoute = new Route();
        emptyRoute.shopsVisited.add(theDepot);
        emptyRoute.shopsVisited.add(theDepot);
        finalRoutes.add(0, emptyRoute);

        return finalRoutes;
    }
    
    private double getCostOfGroupedRoutes(ArrayList<Route> routes){
        double cost = 0.0;
        for(Route r:routes){
            data.calculateDistance(r);
            data.calculateCost(r);
            cost+=r.cost;
        }
        return cost;
    }
    
    private Shop getShopById(List<Shop> theShops, Integer id) {
        for (Shop s : theShops) {
            if (Integer.parseInt(s.shopID) == id) {
                return s;
            }
        }
        return null;
    }
	 
	 

}
