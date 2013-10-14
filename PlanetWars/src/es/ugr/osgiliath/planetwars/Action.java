package es.ugr.osgiliath.planetwars;

import java.text.DecimalFormat;
import java.util.Locale;

import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.planetwars.agent.GPAgent;

public class Action implements Gene{

	
		
	public double getPerc() {
		return perc;
	}



	public void setPerc(double perc) {
		this.perc = perc;
	}



	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}



	private double perc;
	private String action;
	
	Action(String actionName, double perc){
		this.perc = perc;
		this.action = actionName;
	}
	
	
	
	public String toString(){
		return this.action+"("+String.format(Locale.ENGLISH, "%.3f", perc) +");";
	}
	
	public static Action generateRandomAction(){
		int val = (int) (GPAgent.actionList.size()*Math.random());
		String newAction =  GPAgent.actionList.get(val);
		double value = Math.random();
		Action d = new Action(newAction, value);
		return d;
	}
	
	public Object clone(){
		Action c = new Action(this.action, this.perc);
		return c;
	}
	
}
