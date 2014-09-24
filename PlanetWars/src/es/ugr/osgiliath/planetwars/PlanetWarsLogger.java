package es.ugr.osgiliath.planetwars;

/*
 * PlanetWarsLogger.java
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


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;

import javax.security.auth.login.Configuration;




import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.elements.EvolutionaryBasicParameters;
import es.ugr.osgiliath.utils.Logger;
import es.ugr.osgiliath.utils.OsgiliathConfiguration;
import es.ugr.osgiliath.utils.Stopwatch;

public class PlanetWarsLogger extends OsgiliathService implements Logger{
	
	
	int run = 0;
	int iteration = 0;
	int numEvaluations = 0;
	long initTime = 0;
	String filename;

;
	public void activate(){
		System.out.println("ACU LOGGER ACTIVATED"); //NOTA: EL SETUP LO HACE EL EVENTO DEL STARTALL
		//setup(null);
		
	}
	@Override
	public void info(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void warn(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void debug(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stats(String message) {
		try{
			FileWriter fstream = new FileWriter(filename,true); //EXISTENT FILE
			BufferedWriter out = new BufferedWriter(fstream);
			long time =  (System.currentTimeMillis() -initTime) / 1000;
			out.write(iteration+","+numEvaluations+","+time+","+message);
			iteration++;
			out.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}


	@Override
	public void setup(Properties props) {
		
		
		//RESET VALUES
		run++;
		iteration = 0;
		numEvaluations = 0;
		//initTime = 0;
		
		//CREATE FILE
		Date d = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd.HH'h'mm'm'ss's'");
		String timestamp = format.format(d);
		
		// Create folder
		String folderName = "";
		String ROOT_FOLDER = (String) this.getAlgorithmParameters().getParameter(OsgiliathConfiguration.LOG_FOLDER);
		String cross = (String) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.CROSSOVER_PROB).toString();
		String mut = (String) this.getAlgorithmParameters().getParameter(EvolutionaryBasicParameters.MUTATOR_PROB).toString();
		String problemName = (String) this.getAlgorithmParameters().getParameter(OsgiliathConfiguration.PROBLEM_NAME).toString();
		folderName += ROOT_FOLDER+"/"+problemName+"_"+cross+"_"+mut;
		//filename = folderName+"/"+timestamp+"out.run."+this.run+".fwork."+this.getFrameworkId();
		
		filename = folderName+"/"+timestamp+".";
		
		
		try{
			filename = filename + InetAddress.getLocalHost().getHostName();
		}catch(UnknownHostException ex){
			
		}
		
		filename = filename + "-" + this.run;
		
		try{
			System.out.println("Creating logfile: "+filename);
			File folder = new File(folderName);
			 
			// if file doesnt exists, then create it
			boolean status = folder.mkdirs();
			
			FileWriter fstream = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(fstream);

			ArrayList<String> keys = this.getAlgorithmParameters().getKeys();
			Collections.sort(keys);

			for(String k:keys)
				out.write(k+" = "+this.getAlgorithmParameters().getParameter(k)+"\n");
			out.write(filename+"\n");
			out.write("IT,NUM_EVALUATIONS,TIME,SIM,SIZE,DEPTH,AGE,STAMP,TREE;\n");
			out.close();
			//System.out.println("CREADO ARCHIVO DE LOG "+filename);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	
		
		
		//CONTROLS THE TIME
		this.initTime = System.currentTimeMillis();
		
		

	}
	@Override
	public void statsX(String message, String appendix) {
		// TODO Auto-generated method stub
		
	}


	

	
	

}
