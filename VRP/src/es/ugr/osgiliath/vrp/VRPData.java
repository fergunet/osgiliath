/*
 * VRPData.java
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


import java.io.*;


//import java.util.*;


import java.util.ArrayList;

import es.ugr.osgiliath.problem.InputData;
import es.ugr.osgiliath.vrp.individual.Route;
import es.ugr.osgiliath.vrp.individual.Shop;

public class VRPData implements TransportData{
	private static final long serialVersionUID = 1L;
	public ArrayList <Shop> shopList;
	public double speed;
	public double costPerKm;
	public double downloadTime;
	public double vehicleCapacity;
	public double maximumWorkTime;
	public double[][] distanceTable;
	public int nShops;
	public double [] currentFrequency;
	public double [] currentDeliverySize;
	public int [] admissiblePatterns;
	

	public static final String P_PATDATAFILE = "patternsdatafile";
	public static final String P_DATAFILE = "datafile";
	public static final String P_GEODATAFILE = "geodatafile";
	
    /** Given a file name, return a BufferedReader object for that file
	or null if the file could not be found or there is some other
        error.
    */
    public static BufferedReader getBufferedReader( String fileName )
    {
	BufferedReader bufReader = null;
	try {
	    FileReader reader = new FileReader( fileName );
	    bufReader = new BufferedReader( reader );
	}
	catch (FileNotFoundException e1) {
	    System.out.println("File " + fileName + " not found");
	}
	return bufReader;
    } // getBufferedReader

    /**
       Read a list of admissible patterns from a file. 
       The data values are integers separated
       by at least one blank line.
     */
    public void readPatterns( BufferedReader reader )
    {
	try {
		ArrayList<Integer> tempArray = new ArrayList<Integer>();
	    // read any blank lines before the actual data
	    String line = null;
	    do {
		line = reader.readLine();
	    } while (line != null && line.trim().equals(""));

	    while (line != null && (!line.trim().equals(""))) {
	    	tempArray.add(Integer.valueOf( line ).intValue());
	    	line = reader.readLine();
	    } 
// Now turn this into an int[]
		admissiblePatterns = new int[tempArray.size()];
		for (int i= 0; i < tempArray.size(); i++)
			admissiblePatterns[i] =  tempArray.get(i);
	}
	catch (IOException e) {
	    System.out.println("readMatrix I/O error: " + e );
	    }
    } // read
    
	/**
    Read a table of shops' data from a file. The data values are separated
    by commas and shops are separated by at least one blank line.
    This function is using 32-bit floating point.
*/
    public void readShopData( BufferedReader reader )
    {
    	try {
    		shopList = new ArrayList<Shop>();
    		// read any blank lines before the actual data
    		String line = null;
    		do {
    			line = reader.readLine();
    		} while (line != null && line.trim().equals(""));

    		while (line != null && (!line.trim().equals(""))) {
    			Shop s = new Shop();
    			s.readFromLine(line);
    			s.calculateCurrentValues(5); //Let's assume the used freq for all shops is 5
    			shopList.add( s );
    			line = reader.readLine();
    		} 
    		nShops = shopList.size();
    	}
    	catch (IOException e) {
	    System.out.println("readMatrix I/O error: " + e );
    	}
    } // read
 
    /**
     Read a table of shops' X,Y coordinates from a file. The data values are separated
     by commas and shops are separated by at least one blank line.
     This function is using 32-bit floating point.
     */
    public void readGeoCoordinates( BufferedReader reader )
    {	
    	try {
	   	// read any blank lines before the actual data
    		String line = null;
    		do {
    			line = reader.readLine();
    		} while (line != null && line.trim().equals(""));

    		int i = 0;
    		while (line != null && (!line.trim().equals(""))) {
    			shopList.get(i).readXYcoordinatesFromLine( line );
    			line = reader.readLine();
    			i++;
    		} 
    		buildDistanceTable();
    	}
    	catch (IOException e) {
    		System.out.println("readMatrix I/O error: " + e );
	    	}
    } 


	public void buildDistanceTable(){
	distanceTable = new double[nShops][nShops];
	    
	for (int i = 0; i < nShops; i++)
		for (int j = 0; j < nShops ; j++){
	    		//not very efficient, since we calculate each distance twice
	    		// but what the hell...
	    		if (i != j)
	    			distanceTable[i][j] = shopList.get(i).distanceTo(shopList.get(j));
	    		else distanceTable[i][j] = 0.0;
	    }
    }
    public void buildDeliverySizeTable(){
    	//for the time being, let's assume all shops have frequency 5
    	currentFrequency = new double[nShops];
    	currentDeliverySize= new double[nShops];
	    for (int i = 0; i < nShops; i++){
	    	currentFrequency[i] = 5;
	    	currentDeliverySize[i] = shopList.get(i).deliverySize[(int) currentFrequency[i]-1];
	    }	
    }
	public void printShopList(){
		for (int i=0; i< shopList.size(); i++)
	    		shopList.get(i).printShopData();
	}
	public void printDistanceTable(){
		for (int i=0; i< nShops; i++){
			for (int j = 0; j < nShops ; j++)
//		    	System.out.print(distanceTable[i][j]+ " ");
		    	System.out.printf("%4.2f  ", distanceTable[i][j]);
		    	System.out.println();
	    }
	}
	/**
	* Calculate the frequency for a given pattern
	* Does it need to be static?
	* @param pat
	* @return
	*/ 
	public static int calculateFrequencyForPattern(int pat)
	{
		int sum=0;
	    while (pat != 0)
	    {
	    	if ((pat & 1)!= 0) ++sum;
	    	pat >>= 1;
	    }
	    return sum;
	 }
/**
 * Reads all three datafiles and guilds the necessary data matrices
 */
    public void setup(String geodatafile, String shopdatafile){
		
	       
		/*try{
	    	patdatafile = state.parameters.getString(base.push(P_PATDATAFILE),null);
	    	if( patdatafile == null ) throw new Exception("Data file not specified.");
	    	readPatterns(getBufferedReader(patdatafile));
	    	}
		catch(Exception e){
	    	state.output.fatal(patdatafile + ": File data wrong or not found, did you call it " + P_PATDATAFILE + "?");
		}
		try{
	    	datafile = state.parameters.getString(base.push(P_DATAFILE),null);
	    	if( datafile == null ) throw new Exception("Data file not specified.");
	    	readShopData(getBufferedReader(datafile));
	 	}
		catch(Exception e){
	    	state.output.fatal(datafile + ": File data wrong or not found, did you call it " + P_DATAFILE + "?");
		}

		try{
	    	geodatafile = state.parameters.getString(base.push(P_GEODATAFILE),null);
	    	if( geodatafile == null ) throw new Exception(geodatafile + ": Geographical data file not specified.");
	    	readGeoCoordinates(getBufferedReader(geodatafile));
		}
		catch(Exception e){ 
	    	state.output.fatal(geodatafile + "File data wrong or not found, did you call it " + P_GEODATAFILE + "?");
		}*/
		try{
		
		readShopData(getBufferedReader(shopdatafile));
		readGeoCoordinates(getBufferedReader(geodatafile));
		}catch(Exception ex){
			System.out.println("Error al leer");
		}
		buildDeliverySizeTable();
	       
		// These data are exclusively for the VRP
		// Do I need them here?
		maximumWorkTime = 8.0; // Assume a working day of 8 hours
		// These values correspond to the small truck
		// Do we need to create a class Vehicle?
		speed = 60; 			// Km/h
		costPerKm = 0.6; 	// euro
		downloadTime = 0.25; // 15 minutes
		vehicleCapacity = 12;// roll containers
	}
    
    /** 
    Calculate the route length. We must ensure the warehouse appears at the begining
    and the end of the route 
    **/
  	public double calculateDistance(Route r){
  	
  /*		distanceTravelled = 0.0;
  		demand = data.shopList.get(0).currentDeliverySize;
  		for (int i= 1; i <= shopsVisited.size(); i++){
  			distanceTravelled += data.distanceTable[i-1][i];
  			demand += data.shopList.get(i).currentDeliverySize;
  		}
  		return distanceTravelled;*/
  		
  		double distanceTravelled = 0.0;
  		double demand = this.shopList.get(0).currentDeliverySize;
  		for (int i= 1; i < r.shopsVisited.size(); i++){
                      Shop current = r.shopsVisited.get(i);
                      Shop previous = r.shopsVisited.get(i-1);
                      int idCurrent = Integer.parseInt(current.shopID);
                      int idPrevious = Integer.parseInt(previous.shopID);
  			//distanceTravelled += data.distanceTable[i-1][i];
  			//demand += data.shopList.get(i).currentDeliverySize;
                      distanceTravelled += this.distanceTable[idPrevious][idCurrent];
                      demand += this.shopList.get(idCurrent).currentDeliverySize;
  		}
  		
  		//TODO añadir demand y distance travelled a Route?
  		return distanceTravelled;
  	}
  	
  	/**
  	 * Calculate the time of the route, including time spent at stops
  	 * excluding the warehouse. This depends on the vehicle used
  	 * @param data
  	 * @return
  	 */	
  		public double calculateTime(Route r){
  			/*double time = distanceTravelled/data.speed + (shopsVisited.size()-2)*data.downloadTime; 
  			return time;*/
  			return 0;
  		}
  		
  		/**
  		 * Calculate the cost of the route. This depends on the vehicle used
  		 * @param data
  		 * @return
  		 */
  		public double calculateCost(Route r){
  			/*cost = distanceTravelled*data.costPerKm;
  			return cost;*/
  			return 0;
  		}
 
}
