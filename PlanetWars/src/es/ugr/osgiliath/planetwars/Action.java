package es.ugr.osgiliath.planetwars;

import es.ugr.osgiliath.planetwars.agent.GPAgent;

public class Action extends TreeElement{

	
	
	
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
		return this.action+"("+perc+");";
	}
	
	public static Action generateRandomAction(){
		int val = (int) (GPAgent.actionList.size()*Math.random());
		String newAction =  GPAgent.actionList.get(val);
		double value = Math.random();
		Action d = new Action(newAction, value);
		return d;
	}
	
}
