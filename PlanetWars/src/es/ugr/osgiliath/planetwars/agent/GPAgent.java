package es.ugr.osgiliath.planetwars.agent;

import java.util.ArrayList;

public class GPAgent {

	//Planets IDs
	public static final String ID_PLAYER_BASE = "idPlayerBase";
	public static final String ID_ENEMY_BASE = "idEnemyBase";
	public static final String ID_NEAREST_PLAYER_PLANET = "idNearestPlayerPlanet";
	public static final String ID_NEAREST_ENEMY_PLANET = "idNearestEnemyPlanet";
	public static final String ID_NEAREST_NEUTRAL_PLANET = "idNearestNeutralPlanet";
	
	//ACTIONS (methods)
	public static final String METHOD_NAME_ATTACK = "attackToPlanet";
	public static final String METHOD_NAME_REINFORCE_BASE = "reinforceBase";
	public static final String METHOD_NAME_REINFORCE_CLOSEST_PLANET = "reinforceClosestPlanet";
	public static final String METHOD_NAME_DO_NOTHING = "doNothing";
	
	//Planet information
	public static final String THIS_PLANET_GROWN_RATE = "thisPlanetGrownRate";
	public static final String THIS_PLANET_SHIPS = "thisPlanetShips";
	public static final String PLANET_GROWN_RATE = "planetGrownRate";
	public static final String PLANET_NUM_SHIPS = "planetNumShips";

	//Fleets information
	public static final String ENEMY_FLEETS_ON_AIR = "enemyFleetsOnAir";
	public static final String PLAYER_FLEETS_ON_AIR = "playerFleetsOnAir";
	public static final String TOTAL_SHIPS = "totalShips";
	public static final String TOTAL_ENEMY_SHIPS = "totalEnemyShips";
	

	public static ArrayList<String> actionList;
	public static ArrayList<String> decissionList;
	
	static{
		actionList = new ArrayList<String>();
		actionList.add(METHOD_NAME_REINFORCE_BASE);
		actionList.add(METHOD_NAME_DO_NOTHING);
		actionList.add(METHOD_NAME_ATTACK);
		
		decissionList = new ArrayList<String>();
		decissionList.add(ENEMY_FLEETS_ON_AIR);
		decissionList.add(PLAYER_FLEETS_ON_AIR);
		decissionList.add(TOTAL_SHIPS);
	}
	
	public void attackToPlanet(float perc, int destination){
	
		
	}
	//ATTACK NEAREST PLANET
	
	//ATTACK WEAKEST PLANET
	
	//ATTACK POWERPLANET
	
	//ATTACK PLANET WITH MORE GROWN RATE
	
	//ATTACK EQUILIBRIED PLANET
	
	public void attackRandomPlanet(){
		
	}
	
	public void reinforcePlanet(float perc, int destination){
		
	}
	
	public void reinforceBase(float perc){
		
	}
	
	public void doNothing(){
		
	}
	
	
}
