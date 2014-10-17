package es.ugr.osgiliath.planetwars.agent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

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
		
		actionList.add("attackNearestEnemyPlanet");
		actionList.add("attackNearestNeutralPlanet");
		actionList.add("attackNearestPlanet");
		actionList.add("attackWeakestEnemyPlanet");
		actionList.add("attackWeakestNeutralPlanet");
		actionList.add("attackWeakestPlanet");
		actionList.add("attackEnemyBase");
		actionList.add("attackNeutralBase");
		actionList.add("attackBase");
		actionList.add("attackRandomPlanet");
		actionList.add("attackWealthestEnemyPlanet");
		actionList.add("attackWealthestNeutralPlanet");
		actionList.add("attackWealthestPlanet");
		actionList.add("attackBeneficiousEnemyPlanet");
		actionList.add("attackBeneficiousNeutralPlanet");
		actionList.add("attackBeneficiousPlanet");
		actionList.add("attackQuickEnemyPlanet");
		actionList.add("attackQuickNeutralPlanet");
		actionList.add("attackQuickPlanet");
		actionList.add("reinforceNearestPlanet");
		actionList.add("reinforceBase");
		actionList.add("reinforceWealthest");
		actionList.add("reinforceWeakest");
		actionList.add("doNothing");

		decissionList = new ArrayList<String>();
		
		decissionList.add("actualMyShipsRatio");
		decissionList.add("actualLandedFlyingRatio");
		
		decissionList.add("myShipsEnemyRatio");
		decissionList.add("myShipsLandedFlyingRatio");
		decissionList.add("myPlanetsEnemyRatio");
		decissionList.add("myPlanetsTotalRatio");
		//Random
		decissionList.add("randomValue");
	}

	
	PlanetWars data;


	//COMMON TO ALL PLANETS
	Planet enemyBase;		
	Planet enemyWeakest;
	Planet enemyBeneficious;
	Planet enemyWealthest;
	double enemyBenefice;
	
	Planet neutralBase;		
	Planet neutralWeakest;
	Planet neutralBeneficious;
	Planet neutralWealthest;
	double neutralBenefice;
	
	Planet myBase;		
	Planet myWeakest;
	Planet myWealthest;
	
	Planet notMyPlanetBase;	
	Planet notMyPlanetWeakest; 
	Planet notMyPlanetBeneficious;
	Planet notMyPlanetWealthest;
	
	//ACTUAL PLANET
	Planet actual;

	//RATIOS
	double actualMyShipsRatio = 0;
	double actualLandedFlyingRatio = 0; //
	
	//global
	double myShipsEnemyRatio = 0;
	double myShipsLandedFlyingRatio = 0;
	double myPlanetsEnemyRatio = 0;
	double myPlanetsTotalRatio = 0;
	
	double randomValue;
	
	boolean debug = true;

	public GPAgent(){

	}

	public void setPlanetWarsData(PlanetWars data){
		this.data = data;
	}
	
	public void setActualPlanet(Planet p){
		this.actual = p;
	}
	
	private int percToShips(Planet p, double perc){
		int val = (int) ((int) p.NumShips()*perc);//TODO CHECK
		return val;
	}

	//ATTACK
	//NEAREST
	public void attackNearestEnemyPlanet(double perc){
		if(data.EnemyPlanets().size()==0)
			return;
		Planet closer = data.EnemyPlanets().get(0);
		int distance = data.Distance(actual,closer);
		
		for(Planet p:data.EnemyPlanets()){
			int distanceAct = data.Distance(actual, p);
			if(distanceAct < distance){
				closer = p;
				distance = distanceAct;
			}
				
		}
		data.IssueOrder(actual, closer, percToShips(actual,perc));
		this.debug(debug, "attackNearestEnemyPlanet", perc);
	}

	public void attackNearestNeutralPlanet(double perc){
		if(data.NeutralPlanets().size()==0)
			return;
		Planet closer = data.NeutralPlanets().get(0);
		int distance = data.Distance(actual,closer);
		
		for(Planet p:data.NeutralPlanets()){
			int distanceAct = data.Distance(actual, p);
			if(distanceAct < distance){
				closer = p;
				distance = distanceAct;
			}
				
		}
		data.IssueOrder(actual, closer, percToShips(actual,perc));
		this.debug(debug, "attackNearestNeutralPlanet", perc);

	}

	public void attackNearestPlanet(double perc){
		if(data.NotMyPlanets().size()==0)
			return;
		Planet closer = data.NotMyPlanets().get(0);
		int distance = data.Distance(actual,closer);
		
		for(Planet p:data.NotMyPlanets()){
			int distanceAct = data.Distance(actual, p);
			if(distanceAct < distance){
				closer = p;
				distance = distanceAct;
			}
				
		}
		data.IssueOrder(actual, closer, percToShips(actual,perc));
		this.debug(debug, "attackNearestPlanet", perc);

	}

	//WEAKEST
	public void attackWeakestEnemyPlanet(double perc){
		if(data.EnemyPlanets().size()==0)
			return;
		data.IssueOrder(actual, enemyWeakest, percToShips(actual,perc));
		this.debug(debug, "attackWeakestEnemyPlanet", perc);
	}

	public void attackWeakestNeutralPlanet(double perc){
		if(data.NeutralPlanets().size()==0)
			return;
		data.IssueOrder(actual, neutralWeakest, percToShips(actual,perc));
		this.debug(debug, "attackWeakestNeutralPlanet", perc);
	}

	public void attackWeakestPlanet(double perc){
		if(data.NotMyPlanets().size()==0)
			return;
		data.IssueOrder(actual, notMyPlanetWeakest, percToShips(actual,perc));
		this.debug(debug, "attackWeakestPlanet", perc);
	}

	//BASES
	public void attackEnemyBase(double perc){
		if(data.EnemyPlanets().size()==0)
			return;
		data.IssueOrder(actual, enemyBase, percToShips(actual,perc));
		this.debug(debug, "attackEnemyBase", perc);
	}

	public void attackNeutralBase(double perc){
		if(data.NeutralPlanets().size()==0)
			return;
		data.IssueOrder(actual, neutralBase, percToShips(actual,perc));
		this.debug(debug, "attackNeutralBase", perc);
	}

	public void attackBase(double perc){
		if(data.NotMyPlanets().size()==0)
			return;
		data.IssueOrder(actual, notMyPlanetBase, percToShips(actual,perc));
		this.debug(debug, "attackBase", perc);
	}
	//RANDOM
	public void attackRandomPlanet(double perc){
		if(data.NotMyPlanets().size()==0)
			return;
		
		double value = Math.random()*data.NotMyPlanets().size();
		int i = (int) value;
		Planet rnd = data.NotMyPlanets().get(i);
		data.IssueOrder(actual, rnd, percToShips(actual,perc));
		this.debug(debug, "attackRandomPlanet", perc);

	}

	//WEALTHEST (THE ONES WITH MORE GROWN RATE)
	public void attackWealthestEnemyPlanet(double perc){
		if(data.EnemyPlanets().size()==0)
			return;
		data.IssueOrder(actual, enemyWealthest, percToShips(actual,perc));
		this.debug(debug, "attackWealthestEnemyPlanet", perc);
		
	}

	public void attackWealthestNeutralPlanet(double perc){
		if(data.NeutralPlanets().size()==0)
			return;
		data.IssueOrder(actual, neutralWealthest, percToShips(actual,perc));
		this.debug(debug, "attackWealthestNeutralPlanet", perc);

	}

	public void attackWealthestPlanet(double perc){
		if(data.NotMyPlanets().size()==0)
			return;
		data.IssueOrder(actual, notMyPlanetWealthest, percToShips(actual,perc));
		this.debug(debug, "attackWealthestPlanet", perc);

	}

	//BENEFICIOUS (GROWNRATE / SHIPS)
	public void attackBeneficiousEnemyPlanet(double perc){
		if(data.EnemyPlanets().size()==0)
			return;
		data.IssueOrder(actual, enemyBeneficious, percToShips(actual,perc));
		this.debug(debug, "attackBeneficiousEnemyPlanet", perc);
	}

	public void attackBeneficiousNeutralPlanet(double perc){
		if(data.NeutralPlanets().size()==0)
			return;
		data.IssueOrder(actual, neutralBeneficious, percToShips(actual,perc));
		this.debug(debug, "attackBeneficiousNeutralPlanet", perc);
	}

	public void attackBeneficiousPlanet(double perc){
		if(data.NotMyPlanets().size()==0)
			return;
		data.IssueOrder(actual, notMyPlanetBeneficious, percToShips(actual,perc));
		this.debug(debug, "attackBeneficiousPlanet", perc);
	}

	//QUICK (distance and number of ships)
	public void attackQuickEnemyPlanet(double perc){
		if(data.EnemyPlanets().size()==0)
			return;
		
		double easyness = Double.MAX_VALUE; //lower is better

		Planet easiest = data.EnemyPlanets().get(0);
		for(Planet p:this.data.EnemyPlanets()){
			double actualEasiness = data.Distance(actual, p)*p.NumShips();
			if(actualEasiness < easyness ){
				easiest = p;
				easyness = actualEasiness;
			}
		}
		data.IssueOrder(actual, easiest, percToShips(actual,perc));
		this.debug(debug, "attackQuickEnemyPlanet", perc);
	}

	public void attackQuickNeutralPlanet(double perc){
		if(data.NeutralPlanets().size()==0)
			return;
		
		
		double easyness = Double.MAX_VALUE; //lower is better

		Planet easiest = data.NeutralPlanets().get(0);
		for(Planet p:this.data.NeutralPlanets()){
			double actualEasiness = data.Distance(actual, p)*p.NumShips();
			if(actualEasiness < easyness ){
				easiest = p;
				easyness = actualEasiness;
			}
		}
		data.IssueOrder(actual, easiest, percToShips(actual,perc));
		this.debug(debug, "attackQuickNeutralPlanet", perc);
	}

	public void attackQuickPlanet(double perc){
		if(data.NotMyPlanets().size()==0)
			return;
		double easyness = Double.MAX_VALUE; //lower is better

		Planet easiest = data.NotMyPlanets().get(0);
		for(Planet p:this.data.NotMyPlanets()){
			double actualEasiness = data.Distance(actual, p)*p.NumShips();
			if(actualEasiness < easyness ){
				easiest = p;
				easyness = actualEasiness;
			}
		}
		data.IssueOrder(actual, easiest, percToShips(actual,perc));
		this.debug(debug, "attackQuickPlanet", perc);

	}



	//REINFORCE
	public void reinforceNearestPlanet(double perc){
		if(data.MyPlanets().size()==0)
			return;
		Planet closer = data.MyPlanets().get(0);
		int distance = data.Distance(actual,closer);
		
		for(Planet p:data.MyPlanets()){
			int distanceAct = data.Distance(actual, p);
			if(distanceAct < distance){
				closer = p;
				distance = distanceAct;
			}
				
		}
		data.IssueOrder(actual, closer, percToShips(actual,perc));
		this.debug(debug, "reinforceNearestPlanet", perc);

	}

	public void reinforceBase(double perc){
		if(data.MyPlanets().size()==0)
			return;
		data.IssueOrder(actual, myBase, percToShips(actual,perc));
		this.debug(debug, "reinforceBase", perc);
	}

	public void reinforceWealthest(double perc){
		if(data.MyPlanets().size()==0)
			return;
		data.IssueOrder(actual, myWealthest, percToShips(actual,perc));
		this.debug(debug, "reinforceWealthest", perc);
	}

	public void reinforceWeakest(double perc){
		if(data.MyPlanets().size()==0)
			return;
		data.IssueOrder(actual, myWeakest, percToShips(actual,perc));
		this.debug(debug, "reinforceWeakest", perc);
	}
	

	//DO NOTHING
	public void doNothing(double perc){
		this.debug(debug, "doNothing", perc);
	}



	public void calculateGlobalRatios(){
		
		myShipsEnemyRatio = 0;
		myShipsLandedFlyingRatio=0;
		myPlanetsEnemyRatio=0;
		myPlanetsTotalRatio=0;
		randomValue = Math.random();
		
		int myShips = 0;
		int enemyShips = 0;
		
		for(Planet p:data.MyPlanets())
			myShips += p.NumShips();
		
		for(Planet p:data.EnemyPlanets())
			enemyShips += p.NumShips();
		
		int denom = myShips+enemyShips;
		
		if(denom != 0)
			myShipsEnemyRatio = (double) myShips/denom*1.0;
		
		int shipsFlying = 0;
		for(Fleet f:data.MyFleets())
			shipsFlying += f.NumShips();
		
		denom = myShips+shipsFlying;
		if(denom != 0)
			myShipsLandedFlyingRatio = (double) myShips/denom;
		
		
		
		denom = data.MyPlanets().size()+data.EnemyPlanets().size();
		if(denom != 0)
			myPlanetsEnemyRatio = (double) data.MyPlanets().size()/denom*1.0;
		
		denom = data.MyPlanets().size()+data.NotMyPlanets().size();
		if(denom != 0)
			myPlanetsTotalRatio = (float) data.MyPlanets().size()/denom*1.0;
		
		if(debug){
			System.err.println("myShipsEnemyRatio "+myShipsEnemyRatio);
			System.err.println("myShipsLandedFlyingRatio "+myShipsLandedFlyingRatio);
			System.err.println("myPlanetsEnemyRatio "+myPlanetsEnemyRatio);
			System.err.println("myPlanetsTotalRatio "+myPlanetsTotalRatio);
		}
		
		
		
	}
	
	public void calculateActualPlanetRatio(){
		actualMyShipsRatio = 0;
		actualLandedFlyingRatio = 0;
		
		if(actual==null)
			return;
		
		int totalMyPlanets = 0;
		for(Planet p:data.MyPlanets()){
			totalMyPlanets += p.NumShips();
		}
		
		int denom = totalMyPlanets+actual.NumShips();
		if(denom != 0)
			actualMyShipsRatio =  (double) actual.NumShips()/(denom*1.0);
				
		for(Fleet f:data.MyFleets()){
			if(f.SourcePlanet() == actual.PlanetID()){
				int denom2 = f.NumShips()+actual.NumShips();
				if(denom2!=0)
					actualLandedFlyingRatio = (double) actual.NumShips()/(denom2*1.0);
			}
		}
		
		
	}
	public void calculatePlanets(){
		enemyBase = null;
		enemyWeakest = null;
		enemyBeneficious = null;
		enemyBenefice = -1;
		
		neutralBase = null;
		neutralWeakest = null;
		neutralBeneficious = null;
		neutralBenefice = -1;
		
		notMyPlanetBase = null;
		notMyPlanetWeakest = null;
		
		
		//Calculate generic
		if(data.EnemyPlanets().size()>0){
			//ENEMY
			enemyBase = data.EnemyPlanets().get(0);		
			enemyWeakest = data.EnemyPlanets().get(0);
			enemyBeneficious = data.EnemyPlanets().get(0);
			enemyWealthest = data.EnemyPlanets().get(0);
			enemyBenefice = (double) enemyBeneficious.GrowthRate()/(enemyBeneficious.NumShips()*1.0);

			for(Planet p:data.EnemyPlanets()){
				if(p.NumShips() > enemyBase.NumShips())
					enemyBase = p;
				if(p.NumShips() < enemyWeakest.NumShips())
					enemyWeakest = p;
				if(p.GrowthRate() > enemyWealthest.GrowthRate())
					enemyWealthest = p;

				double actualBenefice = (double) p.GrowthRate()/(p.NumShips()*1.0);
				if(enemyBenefice < actualBenefice){
					enemyBenefice = actualBenefice;
					enemyBeneficious = p;
					//System.out.println("ENEMY BENEFICE" + enemyBenefice);
				}

			}
		}

		//NEUTRAL
		if(data.NeutralPlanets().size()>0){
			neutralBase = data.NeutralPlanets().get(0);		
			neutralWeakest = data.NeutralPlanets().get(0);
			neutralBeneficious = data.NeutralPlanets().get(0);
			neutralBenefice = (double) neutralBeneficious.GrowthRate()/(neutralBeneficious.NumShips()*1.0);
			neutralWealthest = data.NeutralPlanets().get(0);

			for(Planet p:data.NeutralPlanets()){
				if(p.NumShips() > neutralBase.NumShips())
					neutralBase = p;
				if(p.NumShips() < neutralWeakest.NumShips())
					neutralWeakest = p;
				if(p.GrowthRate() > neutralWealthest.GrowthRate())
					neutralWealthest = p;

				double actualBenefice = (double) p.GrowthRate()/(p.NumShips()*1.0);
				if(neutralBenefice < actualBenefice){
					neutralBenefice = actualBenefice;
					neutralBeneficious = p;
				}

			}
		}

		//NOT MY PLANETS


		if(enemyBase == null)
			notMyPlanetBase = neutralBase;
		if(neutralBase == null)
			notMyPlanetBase = enemyBase;
		
		if(enemyBase != null && neutralBase != null)
			if(enemyBase.NumShips() > neutralBase.NumShips())
				notMyPlanetBase = enemyBase;
			else
				notMyPlanetBase = neutralBase;
		
		
		if(enemyWeakest == null)
			notMyPlanetWeakest = neutralWeakest;
		if(neutralWeakest == null)
			notMyPlanetWeakest = enemyWeakest;
		
		if(enemyWeakest != null && neutralWeakest != null)
			if(enemyWeakest.NumShips() > neutralWeakest.NumShips())
				notMyPlanetWeakest = enemyWeakest;
			else
				notMyPlanetWeakest = neutralWeakest;
		
		
		if(enemyWealthest == null)
			notMyPlanetWealthest = neutralWealthest; 
		if(neutralWealthest == null)
			notMyPlanetWealthest = enemyWealthest;
		
		if(enemyWealthest != null && neutralWealthest != null)
			if(enemyWealthest.NumShips() > neutralWealthest.NumShips())
				notMyPlanetWealthest = enemyWealthest;
			else
				notMyPlanetWealthest = neutralWealthest;
		
		
		if(enemyBeneficious == null)
			notMyPlanetBeneficious = neutralBeneficious; 
		if(neutralBeneficious == null)
			notMyPlanetBeneficious = enemyBeneficious;
		
		if(enemyBeneficious != null && neutralBeneficious != null)
			if(enemyBeneficious.NumShips() > neutralBeneficious.NumShips())
				notMyPlanetBeneficious = enemyBeneficious;
			else
				notMyPlanetBeneficious = neutralBeneficious;
		
		
	

		//MY PLANETS
		if(data.MyPlanets().size() > 0){
			myBase = data.MyPlanets().get(0);		
			myWeakest = data.MyPlanets().get(0);
			myWealthest = data.MyPlanets().get(0);
			
			for(Planet p:data.MyPlanets()){
				if(p.NumShips() > myBase.NumShips())
					myBase = p;
				if(p.NumShips() < myWeakest.NumShips())
					myWeakest = p;
				if(p.GrowthRate() > myWealthest.GrowthRate())
					myWealthest = p;

			}
			/*System.out.println("ENEMY BASE "+enemyBase.PlanetID());
			System.out.println("ENEMY WEAKEST "+enemyWeakest.PlanetID());
			System.out.println("ENEMY BENEFICIOUS "+enemyBeneficious.PlanetID());
			System.out.println("ENEMY BENEFICE "+enemyBenefice);
			
			System.out.println("NEUTRAL BASE "+neutralBase.PlanetID());
			System.out.println("NEUTRAL WEAKEST "+neutralWeakest.PlanetID());
			System.out.println("NEUTRAL BENEFICIOUS "+neutralBeneficious.PlanetID());
			System.out.println("NEUTRAL BENEFICE "+neutralBenefice);
			
			System.out.println("NOY MY BASE "+notMyPlanetBase.PlanetID());
			System.out.println("NOT MY WEAKEST "+notMyPlanetWeakest.PlanetID());*/
		}

	}

	/////////////////////////////////////////




	/*public void createMethod(String tree) throws NotFoundException, CannotCompileException, SecurityException, NoSuchMethodException, IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		ClassPool pool = ClassPool.getDefault();
		String className = "es.ugr.osgiliath.planetwars.agent.GPAgent";
		CtClass ctclass = pool.get(className);
		 CtMethod newmethod = CtNewMethod.make("public void testPrint() { System.out.println(\"test ok\"); }",ctclass);
         ctclass.addMethod(newmethod);
         ctclass.writeFile();

         for(Method me: ctclass.toClass().getDeclaredMethods()) //test print, ok
             System.out.println(me.getName());
		System.out.println();
		StringExecutor car = new StringExecutor();
		 Method method = car.getClass().getMethod("testPrint", new Class[]{});
		 method.invoke(car,new Object[]{});





	}*/



	public void debug(boolean muestra, String method, double perc){
		if(muestra)
			System.err.println(method+" "+perc);
	}
	
	
}
