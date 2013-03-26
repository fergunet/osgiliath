package es.ugr.osgiliath.vrp.individual;

/*import java.io.*;
import ec.*;
import ec.util.*;*/

import java.io.Serializable;
import java.util.ArrayList;



public class Route implements Serializable {
	public ArrayList <Shop> shopsVisited;
	public double distanceTravelled;
	public double time;
	public double cost;
	public double demand;

	public Route(){
		shopsVisited = new ArrayList <Shop> ();
		distanceTravelled = 0.0;
		time = 0.0;
		cost = 0.0;
		demand = 0.0;
	}
	public Route (Route r){
		shopsVisited = new ArrayList <Shop> (r.shopsVisited);
		distanceTravelled = r.distanceTravelled;
		time = r.time;
		cost = r.cost;
		demand = r.demand;
	}
/** 
  Calculate the route length. We must ensure the warehouse appears at the begining
  and the end of the route 
 * */
	public double calculateDistance(ITPdata data){
	
/*		distanceTravelled = 0.0;
		demand = data.shopList.get(0).currentDeliverySize;
		for (int i= 1; i <= shopsVisited.size(); i++){
			distanceTravelled += data.distanceTable[i-1][i];
			demand += data.shopList.get(i).currentDeliverySize;
		}
		return distanceTravelled;*/
		
		distanceTravelled = 0.0;
		demand = data.shopList.get(0).currentDeliverySize;
		for (int i= 1; i < shopsVisited.size(); i++){
                    Shop current = shopsVisited.get(i);
                    Shop previous = shopsVisited.get(i-1);
                    int idCurrent = Integer.parseInt(current.shopID);
                    int idPrevious = Integer.parseInt(previous.shopID);
			//distanceTravelled += data.distanceTable[i-1][i];
			//demand += data.shopList.get(i).currentDeliverySize;
                    distanceTravelled += data.distanceTable[idPrevious][idCurrent];
                    demand += data.shopList.get(idCurrent).currentDeliverySize;
		}
		return distanceTravelled;
	}
/**
 * Calculate the time of the route, including time spent at stops
 * excluding the warehouse. This depends on the vehicle used
 * @param data
 * @return
 */	
	public double calculateTime(ITPdata data){
		time = distanceTravelled/data.speed + (shopsVisited.size()-2)*data.downloadTime; 
		return time;
	}
	/**
	 * Calculate the cost of the route. This depends on the vehicle used
	 * @param data
	 * @return
	 */
	public double calculateCost(ITPdata data){
		cost = distanceTravelled*data.costPerKm;
		return cost;
	}
	public void clear(){
		shopsVisited.clear();
		distanceTravelled =0.0;
		time =0.0;
		cost = 0.0;
		demand = 0.0;
	}
	public void printRoute(){
		if (shopsVisited.size() > 0){
			System.out.print("(");			
			for (int i = 0; i < shopsVisited.size() -1; i++)
				System.out.print(shopsVisited.get(i).shopID + ", ");
			System.out.print(shopsVisited.get(shopsVisited.size() -1).shopID  + ") ");
			System.out.printf("[Dist: %4.2f Cost: %4.2f Time: %4.2f Demand %2.0f] ", distanceTravelled ,cost , time, demand );
		}
	}

	public void printRouteBig(){
		if (shopsVisited.size() > 0){
			System.out.print("(");			
			for (int i = 0; i < shopsVisited.size() -1; i++)
				System.out.print(shopsVisited.get(i).shopID + ", ");
			System.out.print(shopsVisited.get(shopsVisited.size() -1).shopID  + ") ");

			System.out.print("\tDist: "+ distanceTravelled+ " \tCost:"
					+ cost+ " \tTime:" +time+ " \tDemand:" + demand);
/*			System.out.printf("[Dist: %4.2f Cost: %4.2f Time: %4.2f Demand %2.0f] ", 
					distanceTravelled ,cost , time, demand );*/
		}
	}
	
	public void printRouteShort(){
		if (shopsVisited.size() > 0){
			System.out.print("(");			
			for (int i = 0; i < shopsVisited.size() -1; i++)
				System.out.print(shopsVisited.get(i).shopID + ", ");
			System.out.print(shopsVisited.get(shopsVisited.size() -1).shopID + ") ");
		}
	}
    public String toString(){
        String outP ="";
        for (int i= 0; i < shopsVisited.size(); i++){
		
		outP += this.shopsVisited.get(i).shopID+" ";
	}
        return outP;
    }
}
