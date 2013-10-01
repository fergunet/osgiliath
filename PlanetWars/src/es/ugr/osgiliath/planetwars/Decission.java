package es.ugr.osgiliath.planetwars;

import es.ugr.osgiliath.planetwars.agent.GPAgent;

public class Decission extends TreeElement{
	
	
	//Planet information
	public static final String THIS_PLANET_GROWN_RATE = "thisPlanetGrownRate";
	public static final String THIS_PLANET_SHIPS = "thisPlanetShips";
	public static final String PLANET_GROWN_RATE = "planetGrownRate";
	public static final String PLANET_NUM_SHIPS = "planetNumShips";
	
	//Flying fleets information
	public static final String ENEMY_FLEETS_ON_AIR = "enemyFleetsOnAir";
	public static final String PLAYER_FLEETS_ON_AIR = "playerFleetsOnAir";
	
	//Get planet Ids
	public static final String CLOSER_ENEMY_PLANET = "closerEnemyPlanet";
	public static final String FAREST_ENEMY_PLANET = "farestEnemyPlanet";
	public static final String CLOSER_NEUTRAL_PLANET = "closerNeutralPlanet";
	public static final String PLAYER_BASE = "playerBase";
	public static final String ENEMY_BASE = "enemyBase";
	
	//Action variables
	private String variable;
	private double value;
	
	//ACTION TYPES
	//DISTANCE <>
	//NUMBER OF SHIPS <>
	//GROWN RATE <>
	//PLANET ID
	

	public Decission(String variable, double value) {
		this.variable = variable;
		this.value = value;
	}

	@Override
	public String toString() {
		return "if("+variable+"<"+value+")";
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
	
	
	
	

}
