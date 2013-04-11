/*
 * VRPMoveShopMutation.java
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
import java.util.Random;


import es.ugr.osgiliath.evolutionary.elements.Mutation;
import es.ugr.osgiliath.evolutionary.elements.Mutator;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.vrp.individual.Route;
import es.ugr.osgiliath.vrp.individual.Shop;
import es.ugr.osgiliath.vrp.individual.VRPGenome;
import es.ugr.osgiliath.vrp.individual.VRPIndividual;



/**
 *
 * @author ferguson
 */
public class VRPMoveShopMutation implements Mutation {

    


    public boolean mutate(Individual indiv) {
    	//System.out.println("MUTATING "+indiv.toString());
    	String shopId;
        int newRoute;
        int newPos;
        int oldPos;
        int oldRoute;
        boolean removed;
        double oldCost;

        double firstDistance;
        double secondDistance;
    
        
    	VRPIndividual ind = (VRPIndividual)indiv;
    	VRPGenome gen = (VRPGenome) ind.getGenome();
    	//System.out.println("MOVIMIENTO "+this.toString());
    	removed = false;
        oldCost = gen.getCost();
        
        //RANDOM
        //Get the shop to move
        Random randmon = new Random();
        int sRoute = (int) (randmon.nextInt(((VRPGenome)ind.getGenome()).getRoutes().size()));
        int sShop  = (int) (randmon.nextInt(((VRPGenome)ind.getGenome()).getRoutes().get(sRoute).shopsVisited.size()));
        shopId = ((VRPGenome)ind.getGenome()).getRoutes().get(sRoute).shopsVisited.get(sShop).shopID;
        
        //SELECT THE new ROUTE AND POSITION
        newRoute = (int) (randmon.nextInt(((VRPGenome)ind.getGenome()).getRoutes().size()));
        newPos = (int) (randmon.nextInt(((VRPGenome)ind.getGenome()).getRoutes().get(newRoute).shopsVisited.size()));
        if(newPos == 0) newPos++;
        
        if((sRoute == newRoute && (sShop == newPos || sShop == (newPos-1))))
        return false;
        
        if(sShop==0)
        	return false;
        if(shopId.equals("0"))
        	return false;
        	

        ArrayList<Route> routes = ((VRPGenome)ind.getGenome()).getRoutes();

        //ITPdata data = (ITPdata) gen.getData();
        Route initialRoute = null;
        Route finalRoute = null;
        Shop theShop = null;
        int beforePre;
        int beforePost = 1;
        int afterPre;
        int afterPost = 1;

        int i = 0;
        int routeId = 0;
        int shopPosition = 0;
        int p = 0;

        //Obtains the shop to move
        for (Route r : routes) {
            p = 0; 
            for (Shop s : r.shopsVisited) {

                if (s.shopID.equals(shopId)) {
                    initialRoute = r;
                    theShop = s;
                    routeId = i;
                    oldRoute = i;
                    shopPosition = p;
                    oldPos = p;
                }
                p++;
            }
            i++;

        }

        //
        
        finalRoute = routes.get(newRoute);
        //System.out.println("Shop: " + shopId + " to route " + newRoute + " and pos " + newPos);
        beforePre = Integer.parseInt(initialRoute.shopsVisited.get(shopPosition - 1).shopID);
        afterPre = Integer.parseInt(initialRoute.shopsVisited.get(shopPosition + 1).shopID);

        
        if (routeId != newRoute) { //The routes are different


            finalRoute.shopsVisited.add(newPos, theShop);
            finalRoute.demand += theShop.currentDeliverySize;

            initialRoute.shopsVisited.remove(theShop);
            initialRoute.demand -= theShop.currentDeliverySize;




        } else { //The routes are the same
            if (newPos < shopPosition) {
                finalRoute.shopsVisited.add(newPos, theShop);
                finalRoute.shopsVisited.remove(shopPosition + 1);

            } else if (newPos > shopPosition) {
                finalRoute.shopsVisited.add(newPos, theShop);
                finalRoute.shopsVisited.remove(shopPosition);

                newPos--;
            }

        }

        //Update the cost
        //DELETED, FITNESS CALCULATED APPART
       /* beforePost = Integer.parseInt(finalRoute.shopsVisited.get(newPos - 1).shopID);
        afterPost = Integer.parseInt(finalRoute.shopsVisited.get(newPos + 1).shopID);

        int shopIdInt = Integer.parseInt(shopId);
        double initialCost = gen.getCost();

         double firstRouteDistance =
                data.distanceTable[beforePre][afterPre] -
                data.distanceTable[shopIdInt][afterPre] - data.distanceTable[beforePre][shopIdInt];

         double secondRouteDistance =
                -data.distanceTable[beforePost][afterPost] + data.distanceTable[shopIdInt][afterPost] + data.distanceTable[beforePost][shopIdInt];

        firstDistance = initialRoute.distanceTravelled;
        secondDistance = finalRoute.distanceTravelled;
        
        initialRoute.distanceTravelled += firstRouteDistance;
        finalRoute.distanceTravelled += secondRouteDistance;
        
        initialRoute.calculateTime(data);
        finalRoute.calculateTime(data);
        

        //Finally we set the cost
        if (finalRoute.demand < data.vehicleCapacity && finalRoute.time < data.maximumWorkTime) {
            gen.setCost(initialCost +
                    firstRouteDistance * data.costPerKm + secondRouteDistance * data.costPerKm);
        } else {
            gen.setCost(Double.MAX_VALUE);
        }*/
        ///     
        if (initialRoute.shopsVisited.size() == 2) {
            routes.remove(initialRoute);

            
            removed = true;
        }
        
        if (routes.get(0).shopsVisited.size() != 2) {
                Shop theStore = routes.get(0).shopsVisited.get(0);

                Route r = new Route();
                r.shopsVisited.add(theStore);
                r.shopsVisited.add(theStore);

                routes.add(0, r);
            }


   return true;


    }

	@Override
	public Genome mutate(Genome genome) {
		// TODO Auto-generated method stub
		return null;
	}


    


    


}
