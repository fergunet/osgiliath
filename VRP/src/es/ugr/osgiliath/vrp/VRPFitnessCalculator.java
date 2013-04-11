/*
 * VRPFitnessCalculator.java
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

import es.ugr.osgiliath.algorithms.Algorithm;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.DoubleFitness;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.problem.InputData;
import es.ugr.osgiliath.vrp.individual.Route;
import es.ugr.osgiliath.vrp.individual.VRPGenome;




import java.util.ArrayList;


/**
 *
 * @author ferguson
 */
public class VRPFitnessCalculator implements FitnessCalculator{

    private TransportData tdata;
    
    private boolean maximize = false;
    
    public VRPFitnessCalculator() 
    {   
        
    }   // end constructor
    


    public Fitness evaluate(Genome solution) {
        
        // If move is null, calculate distance from scratch
//       if( themove == null )
//        {
            
           ArrayList<Route> tour = ((VRPGenome)solution).getRoutes();
            double cost = 0.0;
            double time = 0.0;
            
            for(Route r:tour){
                if(r.shopsVisited.size()!=2){
                    
                    //double distance = r.calculateDistance(itpdata);
                    
                    cost += tdata.calculateCost(r);
                    time = tdata.calculateTime(r);
                    /*if(r.demand > itpdata.vehicleCapacity 
                            || time>itpdata.maximumWorkTime)
                        return new DoubleFitness(Double.MAX_VALUE,maximize);*/
                    //TODO add this in case
                    

                }

            }

           
            
            return  new DoubleFitness(cost,maximize);

    }


	
//	public Algorithm getFatherAlgorithm() {
//		return this.father;
//	}
//	
//	
//    public void setFatherAlgorithm(Algorithm father){
//    	this.father=father;
//    	this.itpdata = (ITPdata)father.getProblem().getInputData();
//    }




	public Fitness evaluate(Individual ind) {
		return evaluate(ind.getGenome());
	}
	

	/*public void setInputData(InputData data){
		this.itpdata =  data;
	}*/



	@Override
	public Fitness calculateFitness(Individual ind) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ArrayList<Fitness> calculateFitnessForAll(ArrayList<Individual> inds) {
		// TODO Auto-generated method stub
		return null;
	}
        

}
