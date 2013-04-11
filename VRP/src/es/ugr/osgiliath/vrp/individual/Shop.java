/*
 * Shop.java
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

public class Shop implements Serializable {
	public String shopID;
	public double coordX;
	public double coordY;
	public double[] inventoryCost;
	public double[] deliverySize;	
	public int[] admissibleFrequencies;
	public double currentInventoryCost;
	public double currentDeliverySize;
	public int currentFrequency;

	public  Shop()
	{
	     // Initialise things
		shopID = "No ID";
		coordX = 0.0;
		coordY = 0.0;
    	inventoryCost = new double[5];
    	deliverySize = new double[5];
    	admissibleFrequencies = new int[5];
	    for (int i = 0; i < 5; i++) {
	    	inventoryCost[i] = 0.0;
	    	deliverySize[i] = 0.0;
	    	admissibleFrequencies[i] = 1;//by default, admit all freqs
	    }
	    // Assume we're using the values corresponding to the highest freq
	    currentFrequency = 5;
	    currentDeliverySize = 0.0;
	    currentInventoryCost = 0.0;
	}
	
	public void calculateCurrentValues(int curFreq){
		currentFrequency = curFreq;
	    currentDeliverySize = deliverySize[currentFrequency - 1];
	    currentInventoryCost = inventoryCost[currentFrequency - 1];
	}
	/** Calculate Euclidean distance between two shops */
	public double distanceTo(Shop s){
		double distX, distY;
		distX = Math.abs(coordX) - Math.abs(s.coordX);
		distY = Math.abs(coordY) - Math.abs(s.coordY);
		return Math.sqrt(distX*distX + distY*distY);
	}
	
	/** Convert a string into a Shop */
	public void readFromLine(String line ){
	    String curNumber = null;
	    String[] floatStrVec = line.split(",");
	    int rowLen = floatStrVec.length;

	    if (rowLen < 16) {
	    	System.out.println("File format incorrect: Insufficient data values" );
	    	return; // bad, bad, bad
	    }
	    shopID = floatStrVec[ 0 ];
	    for (int i = 1; i <= 5; i++) {
	    	curNumber = floatStrVec[ i ];
	    	double val = Double.parseDouble( curNumber );
	    	inventoryCost[i-1] = val;
	    } 
	    for (int i = 6; i <= 10; i++) {
	    	curNumber = floatStrVec[ i ];
	    	double val = Double.parseDouble( curNumber );
	    	deliverySize[i-6] = val;
	    } 
	    for (int i = 11; i <= 15; i++) {
	    	curNumber = floatStrVec[ i ];
	    	int val = (int) Double.parseDouble( curNumber );
	    	admissibleFrequencies[i-11] = val;
	    } 
	}
	
	/** Convert a string into a Shop's xy coordinates */
	public void readXYcoordinatesFromLine(String line ){
	    String[] floatStrVec = line.split(",");
	    if ( floatStrVec.length < 2) {
	    	System.out.println("File format incorrect: Insufficient data values" );
	    	return; // bad, bad, bad
	    }
	    coordX = Float.parseFloat( floatStrVec[0] );
	    coordY = Float.parseFloat( floatStrVec[1] );    
	}
	public void setShopCoordinates(double x, double y){
		coordX = x;
		coordY = y;
	}
	/** Print Shop data*/
	public void printShopData(){
	    System.out.print("Shop " + shopID + " (" + coordX + "," + coordY +"): ");
	    
	    for (int i = 0; i < 5; i++) {
	    	System.out.print(inventoryCost[i] + " ");
	    } 
	    for (int i = 0; i < 5; i++) {
	    	System.out.print(deliverySize[i] + " ");
	    } 
	    for (int i = 0; i < 5; i++) {
	    	System.out.print(admissibleFrequencies[i] + " ");
	    }
		System.out.println(" Current values: " + currentInventoryCost + " "+ currentDeliverySize+ " " +currentFrequency);
	}
	/**
	 * Find out if a frequency is in the list of admissible frequencies for a shop
	 * @param freq
	 * @return
	 */
	public boolean isFrequencyAdmissible(int freq){		
		if ((freq < 0) || (freq > admissibleFrequencies.length)) return false;
		if (admissibleFrequencies[freq -1] == 1) return true;
		return false;
	}
	
	public String toString(){
		return("Shop " + shopID + " (" + coordX + "," + coordY +"): ");
	}
}
