/*
 * Route.java
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
package es.ugr.osgiliath.vrp.individual;



import java.io.Serializable;
import java.util.ArrayList;

import es.ugr.osgiliath.vrp.VRPData;



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
