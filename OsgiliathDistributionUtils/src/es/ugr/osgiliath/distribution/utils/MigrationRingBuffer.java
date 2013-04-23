/*
 * MigrationRingBuffer.java
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
package es.ugr.osgiliath.distribution.utils;

import java.util.ArrayList;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.events.EventCreator;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.migrator.Migrator;

public class MigrationRingBuffer extends OsgiliathService implements Migrator {

	ArrayList<Individual> buffer;
	ArrayList<Migrator> migrators;
	int id;
	
	/**
	 * This method sends the individuals to the migrator whose ID is immediately larger.
	 */
	@Override
	public void sendLOCAL(ArrayList<Individual> inds) {
		
		if(migrators.size() == 0)
			return;
		
		int destinationId = Integer.MAX_VALUE;
		int minId = Integer.MAX_VALUE;
		
		int destinationPos = -1;
		int minPos = -1;
		
		for(int i = 0; i<migrators.size();i++){
			Migrator m = migrators.get(i);
			int actualId = m.getId();
			
			//System.out.println("YO: "+this.id+" actual: "+actualId);
			
			if(actualId > this.id){
				if(actualId < destinationId){
					destinationId = actualId;
					destinationPos = i;
					//System.out.println("IF 1 DESTINATION "+destinationId);
				}
				
			}
			
			if(actualId < minId){
				minPos = i;
				minId = actualId;
				
			}
		}
		
		if(destinationId == Integer.MAX_VALUE){
			destinationId = minId;
			destinationPos = minPos;
			//System.out.println("IF 2 DESTINATION "+destinationId);
		}
	
		
		
		//Send the individuals
		System.out.println("ENVIADO "+inds.size()+" DESDE "+this.id+" A "+destinationId);
		migrators.get(destinationPos).sendREMOTE(inds);
		for(Individual in:inds)
			if(in==null)
				System.out.println("ESTOY ENVIANDO UNO NULL!");
		
		
		/*int numSent = inds.size();
		if(this.getEventAdmin() != null)
			this.getEventAdmin().sendEvent(EventCreator.createNewMigrationSendEvent(numSent, true));
		*/
	}
	
	public void activate(){
		this.id = Integer.parseInt(this.getFrameworkId());
		System.out.println("MIGRATOR ACTIVATED: "+this.id);
		this.buffer = new ArrayList<Individual>();
		this.migrators = new ArrayList<Migrator>();
	}

	/**
	 * This function READS the local buffer and CLEARS the buffer
	 */
	@Override
	public ArrayList<Individual> readLOCAL() {
		ArrayList<Individual> inds = new ArrayList<Individual>();
		inds.addAll(this.buffer);
		this.buffer.clear();
		return inds;
	}
	
	public void addMigrator(Migrator mig){
		System.out.println("--------------MIGRATOR ADDED FROM "+mig.getId());
		migrators.add(mig);
		
	}
	
	public void removeMigrator(Migrator mig){
		System.out.println("--------------MIGRATOR -REMOVED- FROM "+mig.getId());
		migrators.remove(mig);
		
	}
	
	public void reset(){
		this.buffer.clear();
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void sendREMOTE(ArrayList<Individual> inds) {
		//System.out.println("RECIBI "+inds.size());
		//THIS CODE IS EXECUTED LOCALLY, BUT CALLED REMOTELLY
		this.buffer.addAll(inds);
		
		
	}

	/*@Override
	public void handleEvent(Event arg0) {
		this.reset();
		System.out.println("MIGRATOR RESET");
		
	}*/

}
