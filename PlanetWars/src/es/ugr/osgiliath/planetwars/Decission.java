package es.ugr.osgiliath.planetwars;

import java.text.DecimalFormat;
import java.util.Locale;

import es.ugr.osgiliath.evolutionary.individual.Gene;
import es.ugr.osgiliath.planetwars.agent.GPAgent;

public class Decission implements Gene{
	
	//Action variables
	private String variable;
	private double value;
	

	public Decission(String variable, double value) {
		this.variable = variable;
		this.value = value;
	}

	@Override
	public String toString() {
		return "if("+variable+"<"+String.format(Locale.ENGLISH, "%.3f", value)+")";
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}
	
	public static Decission generateRandomDecission(){
		int val = (int) (GPAgent.decissionList.size()*Math.random());
		String newDecission =  GPAgent.decissionList.get(val);
		double value = Math.random();
		Decission d = new Decission(newDecission, value);
		return d;
	}
	
	public Object clone(){
		Decission c = new Decission(this.variable,this.value);
		return c;
	}
	
	
	
	

}
