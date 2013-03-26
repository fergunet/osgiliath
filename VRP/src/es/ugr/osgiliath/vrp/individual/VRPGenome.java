/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ugr.osgiliath.vrp.individual;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import es.ugr.osgiliath.algorithms.Algorithm;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.problem.InputData;



/**
 *
 * @author ferguson
 */
public class VRPGenome implements Genome{

	
    ArrayList<Route> routes;
    private int MAX_ITERATIONS = 100000; //To create an initial random solution

    private double cost = -1;
    private ITPdata data;

    public VRPGenome() {
        this.routes = new ArrayList<Route>();

    }

    public VRPGenome(VRPGenome copy) {
        this.routes = (ArrayList<Route>) copy.routes.clone();
    }

    
    public Object clone() {
        VRPGenome copy = new VRPGenome();
        copy.setCost(this.getCost());
        //copy.routes = (ArrayList<Route>)this.routes.clone();

        /* copy.routes = new ArrayList<Route>();
        for (int i = 0; i < this.routes.size(); i++) {
        Route r = new Route(this.routes.get(i));
        r.shopsVisited.clear();
        for (int j = 0; j < this.routes.get(i).shopsVisited.size(); j++) {
        r.shopsVisited.add(this.routes.get(i).shopsVisited.get(j));
        }
        copy.routes.add(r);
        }*/
        copy.routes = new ArrayList<Route>();
        for (int i = 0; i < this.routes.size(); i++) {
            Route r = new Route(this.routes.get(i));
            r.shopsVisited.clear();
            r.shopsVisited = (ArrayList<Shop>) this.routes.get(i).shopsVisited.clone();
            copy.routes.add(r);
        }
        copy.setData(this.data);
        return copy;
    }   // end clone


    /**
     * Creates an initial solution 
     * @param data
     */
    public void setAsRandomInitialSolution(Shop theDepot, List<Shop> theShops) throws Exception {
        // Shop theStore = data.shopList.get(0);
        Shop theStore = theDepot;
        boolean solutionInvalid = true;
        for (int i = 1; i < theShops.size(); i++) {
            Shop shop = theShops.get(i);
            Route r = new Route();
            r.shopsVisited.add(theStore);
            r.shopsVisited.add(shop);
            r.shopsVisited.add(theStore);
            this.routes.add(r);
        }

        /* List<Shop> theShops = new ArrayList<Shop>();
        for (int i = 1; i < data.shopList.size(); i++) {
        theShops.add(data.shopList.get(i));
        }*/


        ArrayList<Route> newRoutes = new ArrayList<Route>();


        int iterations = 0;
        do {
            this.routes.clear();
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

                    double distance = r.calculateDistance(data);

                    cost += r.calculateCost(data);
                    outatime += r.calculateTime(data);
                    time += outatime;
                    if (r.demand > data.vehicleCapacity || outatime > data.maximumWorkTime) {
                        solutionInvalid = true;
                    }
                    this.routes.add(r);
                }
            }

            Route emptyRoute = new Route();
            emptyRoute.shopsVisited.add(theStore);
            emptyRoute.shopsVisited.add(theStore);
            this.routes.add(0, emptyRoute);

            //this.routes = newRoutes;

            iterations++;

            if (iterations > MAX_ITERATIONS) {
                throw new Exception("Could not create an " +
                        "initial solution in " + MAX_ITERATIONS + " iterations");
            }

        } while (solutionInvalid);

    //System.out.println("INITIAL: "+this.toString());

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
    public void setAsDaisyInitialSolution(Shop theDepot,List<Shop> theShops) {


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
            ArrayList<Route> partialRoutes = getPetals(orderedAngles, i, map, theShops, theDepot);
            groupedRoutes.add(partialRoutes);
        }
        
        Double[] reverseOrderedAngles = new Double[theShops.size()];
        
        for(int j = orderedAngles.length-1; j>=0;j--)
            reverseOrderedAngles[j]=orderedAngles[j];
        for (i = 0; i < orderedAngles.length; i++) {
            ArrayList<Route> partialRoutes = getPetals(reverseOrderedAngles, i, map, theShops, theDepot);
            groupedRoutes.add(partialRoutes);
        }
        
        ArrayList<Route> bestRoutes = groupedRoutes.get(0);
        double bestCost = getCostOfGroupedRoutes(bestRoutes);
        for(int j=0; j<groupedRoutes.size();j++){
            ArrayList<Route> actuals = groupedRoutes.get(j);
            double actualcost = getCostOfGroupedRoutes(actuals);
            if(actualcost<bestCost)
                bestRoutes = actuals;
        }
            
        this.routes = bestRoutes;

//        for (Route r : this.routes) {
//            System.out.println("Ruta:" + r);
//        }

    }

    private ArrayList<Route> getPetals(Double[] orderedAngles, int initialShop,
            SortedMap<Double, Integer> map, List<Shop> theShops, Shop theDepot) {
        ArrayList<Route> finalRoutes = new ArrayList<Route>();

        Route newRoute = new Route();
        newRoute.shopsVisited.add(theDepot);
        for (int i = 0; i < orderedAngles.length; i++) {


            Double angle = orderedAngles[(i + initialShop) % orderedAngles.length];

            Shop s = getShopById(theShops, map.get(angle));
            newRoute.shopsVisited.add(s);
            newRoute.shopsVisited.add(theDepot);

            double distance = newRoute.calculateDistance(data);

            double cost = newRoute.calculateCost(data);
            double time = newRoute.calculateTime(data);

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

    private Shop getShopById(List<Shop> theShops, Integer id) {
        for (Shop s : theShops) {
            if (Integer.parseInt(s.shopID) == id) {
                return s;
            }
        }
        return null;
    }
    
    private double getCostOfGroupedRoutes(ArrayList<Route> routes){
        double cost = 0.0;
        for(Route r:routes){
            r.calculateDistance(data);
            r.calculateCost(data);
            cost+=r.cost;
        }
        return cost;
    }

    
    public String toString() {
        int i = 0;
        String chain = "";
        for (Route r : this.routes) {
            chain += "[" + (i++) + ": ";
            for (Shop s : r.shopsVisited) {
                chain += s.shopID + " ";
            }
            chain += "]";

        }
        return chain;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

	
	public void initializeSolution() {
		ArrayList<Shop> shops = new ArrayList<Shop>();
		Shop theDepot = data.shopList.get(0);
		for(int i = 1; i<data.shopList.size();i++)
			shops.add(data.shopList.get(i));
		//this.setAsClarkeWrightInitialSolution(shops);
		//this.setAsDaisyInitialSolution(theDepot,shops);
		try {
			
			this.setAsRandomInitialSolution(theDepot,shops);
		} catch (Exception e) {
			System.out.println("ERROR AL INICIAR");
			e.printStackTrace();
		}
		
	}

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
	
	public void setData(InputData d) {
		this.data =(ITPdata)d;
		
	}

	
	public InputData getData() {
		// TODO Auto-generated method stub
		return this.data;
	}


//	public int compareTo(Object o) {
//		Double thisCost = new Double(cost);
//		Double anotherCost = new Double(((VRPGenome)o).cost);
//		return thisCost.compareTo(anotherCost);
//		
//	}

}
