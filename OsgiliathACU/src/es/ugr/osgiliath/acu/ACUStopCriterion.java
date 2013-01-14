package es.ugr.osgiliath.acu;

import es.ugr.osgiliath.evolutionary.elements.StopCriterion;

public class ACUStopCriterion implements StopCriterion{

	ACUBank bank;
	boolean stopped;
	@Override
	public boolean hasFinished() {
		if(this.stopped == true)
			return true;
		
		if(bank.getActualACUs()<0){
			stopped = true;
			return true;
		}
		else 
			return false;
	}

	@Override
	public void reset() {
		
		bank.initialize();
		this.stopped = false;
	}

	@Override
	public void stop() {
		this.stopped = true;
		
	}
	
	public void setBank(ACUBank bank){
		this.bank = bank;
	}
	
	public void unsetBank(ACUBank bank){
		this.bank = null;
	}

}
