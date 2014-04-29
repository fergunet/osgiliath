package es.ugr.osgiliath.test;

import es.ugr.osgiliath.algorithms.Algorithm;
import es.ugr.osgiliath.problem.Solution;

public class Client implements Algorithm{
	
	SpecificInitializerInterface iface;
	
	public void setSpecificInitializerInterface(SpecificInitializerInterface i){
	
		this.iface = i;
		System.out.println("OBTAINED INTERFACE");
	}
	
	public void unsetSpecificInitializerInterface(SpecificInitializerInterface i){
		this.iface = null;
		System.out.println("UNSET INTERFACE");
	}
	
	public void activate(){
		System.out.println("CLIENT ACTIVATED");
		
		
	}

	@Override
	public void start() {
		System.out.println("STARTING CALLING");
		call(250);
		call(500);
		call(1000);
		call(2000);
		System.out.println("DONE");
		
	}

	public void call(int individuals){
		System.out.println("CALLING "+individuals);
		for(int i = 0; i<100; i++){
			long init = System.currentTimeMillis();
			this.iface.initializeIndividuals(individuals);
			long end = System.currentTimeMillis();
			System.out.println(end-init);
		}
	}
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getActualIteration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Solution getObtainedSolution() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	

}
