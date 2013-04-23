/*
 * EventCreator.java
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
package es.ugr.osgiliath.events;

import java.util.Dictionary;
import java.util.Properties;

import org.osgi.service.event.Event;

public class EventCreator{
	
	public static final String TOPIC_ROOT = "OSGILIATH";
	public static final String TOPIC_EVALUATIONS = TOPIC_ROOT+"/EVALUATION";
	public static final String TOPIC_EVALUATIONS_MAX = TOPIC_ROOT+"/EVALUATION/MAX";
	public static final String TOPIC_ITERATION_NEW = TOPIC_ROOT+"/ITERATION";
	public static final String TOPIC_ITERATION_MAX = TOPIC_ROOT+"/ITERATION/MAX";
	public static final String TOPIC_MIGRATION_SEND = TOPIC_ROOT+"/MIGRATION/SEND";
	public static final String TOPIC_MIGRATION_RECEIVED = TOPIC_ROOT+"/MIGRATION/RECEIVED";
	public static final String TOPIC_ALGORITHM_TERMINATE = TOPIC_ROOT+"/ALGORITHM/TERMINATE";
	public static final String TOPIC_ALGORITHM_FINISHED = TOPIC_ROOT+"/ALGORITHM/FINISHED";
	public static final String TOPIC_FITNESS_AVERAGE = TOPIC_ROOT+"/FITNESS/AVERAGE";
	public static final String TOPIC_FITNESS_MAX = TOPIC_ROOT+"/FITNESS/MAX";
	public static final String TOPIC_RESET = TOPIC_ROOT+"/RESET";
	public static final String TOPIC_GENERIC = TOPIC_ROOT+"/GENERIC";
	
	public static final String PROP_ID = "id";
	public static final String PROP_EVALUATIONS_NUMBER = "evaluations_number";
	public static final String PROP_MIGRATION_SEND = "migration_send";
	public static final String PROP_MIGRATION_RECEIVED = "migration_received";
	public static final String PROP_FITNESS_AVERAGE = "fitness_average";
	public static final String PROP_FITNESS_MAX = "fitness_max";
	public static final String PROP_LOCAL = "local";
	public static final String PROP_INFO = "info";
	
	public static String IDFRAMEWORK = System.getProperty("frameworkID");
	public static Event createEvaluationsEvent(int newEvaluations, boolean local){
		Dictionary dict = new Properties();
		dict.put(PROP_EVALUATIONS_NUMBER, newEvaluations);
		dict.put(PROP_ID, IDFRAMEWORK); //TODO ARREGLAR ESTO
		dict.put(PROP_LOCAL,new Boolean(true));
		return new Event(TOPIC_EVALUATIONS, dict);
	}
	
	public static Event createMaxEvaluationsEvent(int newEvaluations, boolean local){
		Dictionary dict = new Properties();
		dict.put(PROP_EVALUATIONS_NUMBER, newEvaluations);
		dict.put(PROP_ID, IDFRAMEWORK); //TODO ARREGLAR ESTO
		dict.put(PROP_LOCAL,new Boolean(true));
		return new Event(TOPIC_EVALUATIONS_MAX, dict);
	}
	
	public static Event createNewIterationEvent(boolean local){
		Dictionary dict = new Properties();
		dict.put(PROP_ID, IDFRAMEWORK); //TODO ARREGLAR ESTO
		dict.put(PROP_LOCAL,new Boolean(true));
		return new Event(TOPIC_ITERATION_NEW, dict);
	}
	
	public static Event createMaxIterationEvent(boolean local){
		Dictionary dict = new Properties();
		dict.put(PROP_ID, IDFRAMEWORK); //TODO ARREGLAR ESTO
		dict.put(PROP_LOCAL,new Boolean(true));
		return new Event(TOPIC_ITERATION_MAX, dict);
	}
	
	public static Event createNewMigrationSendEvent(int numSend, String bestFitness, String averageFitness, boolean local){
		Dictionary dict = new Properties();
		dict.put(PROP_MIGRATION_SEND, numSend);
		dict.put(PROP_FITNESS_AVERAGE, averageFitness);
		dict.put(PROP_FITNESS_MAX, bestFitness);
		dict.put(PROP_ID, IDFRAMEWORK); //TODO ARREGLAR ESTO
		dict.put(PROP_LOCAL,new Boolean(true));
		return new Event(TOPIC_MIGRATION_SEND, dict);
	}
	
	public static Event createNewMigrationReceiveEvent(int numReceived, String bestFitness, String averageFitness, boolean local){
		Dictionary dict = new Properties();
		dict.put(PROP_MIGRATION_RECEIVED, numReceived);
		dict.put(PROP_ID, IDFRAMEWORK); //TODO ARREGLAR ESTO
		dict.put(PROP_FITNESS_AVERAGE, averageFitness);
		dict.put(PROP_FITNESS_MAX, bestFitness);
		dict.put(PROP_LOCAL,new Boolean(true));
		return new Event(TOPIC_MIGRATION_RECEIVED, dict);
	}
	
	public static Event createTerminateAlgorithm(int numReceived, boolean local){
		Dictionary dict = new Properties();
		dict.put(PROP_ID, IDFRAMEWORK); //TODO ARREGLAR ESTO
		dict.put(PROP_LOCAL,new Boolean(true));
		return new Event(TOPIC_ALGORITHM_TERMINATE, dict);
	}
	
	public static Event createAverageFitnessEvent(String fitness, boolean local){
		Dictionary dict = new Properties();
		dict.put(PROP_FITNESS_AVERAGE, fitness);
		dict.put(PROP_ID, IDFRAMEWORK); //TODO ARREGLAR ESTO
		dict.put(PROP_LOCAL,new Boolean(true));
		return new Event(TOPIC_FITNESS_AVERAGE, dict);
		
	}
	
	public static Event createMaxFitnessEvent(String fitness, boolean local){
		Dictionary dict = new Properties();
		dict.put(PROP_FITNESS_MAX, fitness);
		dict.put(PROP_ID, IDFRAMEWORK); //TODO ARREGLAR ESTO
		dict.put(PROP_LOCAL,new Boolean(true));
		return new Event(TOPIC_FITNESS_MAX, dict);
		
	}
	
	public static Event createResetEvent(boolean local){
		Dictionary dict = new Properties();
		dict.put(PROP_ID, IDFRAMEWORK); //TODO ARREGLAR ESTO
		dict.put(PROP_LOCAL,new Boolean(true));
		return new Event(TOPIC_RESET, dict);
		
	}
	
	public static Event createGenericEvent(String info, boolean local){
		Dictionary dict = new Properties();
		dict.put(PROP_ID, IDFRAMEWORK); //TODO ARREGLAR ESTO
		dict.put(PROP_LOCAL,new Boolean(true));
		dict.put(PROP_INFO, info);
		return new Event(TOPIC_GENERIC, dict);		
	}

}
