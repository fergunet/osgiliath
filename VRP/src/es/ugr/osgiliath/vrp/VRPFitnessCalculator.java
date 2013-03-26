/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ugr.osgiliath.vrp;

import es.ugr.osgiliath.algorithms.Algorithm;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.problem.InputData;
import es.ugr.osgiliath.vrp.individual.ITPdata;
import es.ugr.osgiliath.vrp.individual.Route;
import es.ugr.osgiliath.vrp.individual.VRPGenome;




import java.util.ArrayList;


/**
 *
 * @author ferguson
 */
public class VRPFitnessCalculator implements FitnessCalculator{

    private ITPdata itpdata;
    private Algorithm father;
    
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
                    
                    double distance = r.calculateDistance(itpdata);
                    
                    cost += r.calculateCost(itpdata);
                    time = r.calculateTime(itpdata);
                    if(r.demand > itpdata.vehicleCapacity 
                            || time>itpdata.maximumWorkTime)
                        return new VRPCost(Double.MAX_VALUE);
                    

                }

            }

           
            
            return  new VRPCost(cost);

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
	

	public void setInputData(InputData data){
		this.itpdata = (ITPdata) data;
	}



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
