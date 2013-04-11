/*
 * VRPGenome.java
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



import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;


import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.vrp.VRPData;




/**
 *
 * @author ferguson
 */
public class VRPGenome implements Genome{

	
    ArrayList<Route> routes;


    private double cost = -1;
    private VRPData data;

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
       // copy.setData(this.data);
        return copy;
    }   // end clone


  

    private Shop getShopById(List<Shop> theShops, Integer id) {
        for (Shop s : theShops) {
            if (Integer.parseInt(s.shopID) == id) {
                return s;
            }
        }
        return null;
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

    public void setRoutes(ArrayList<Route> r){
    	this.routes = r;
    }
	

		
	

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
	
	/*public void setData(InputData d) {
		this.data =(ITPdata)d;
		
	}

	
	public InputData getData() {
		// TODO Auto-generated method stub
		return this.data;
	}*/


//	public int compareTo(Object o) {
//		Double thisCost = new Double(cost);
//		Double anotherCost = new Double(((VRPGenome)o).cost);
//		return thisCost.compareTo(anotherCost);
//		
//	}

}
