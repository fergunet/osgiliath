package es.ugr.osgiliath.planetwars.agent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

public class GPbot {
	public static GPAgent agent;
	
	static PlanetWars generateDummyPw(){
		PlanetWars pw = new PlanetWars();
		pw.planets = new ArrayList<Planet>();
		pw.fleets = new ArrayList<Fleet>();
		
		
		//Neutral 0
		pw.planets.add(new Planet(0, 0, 10, 1, 5, 5));
		pw.planets.add(new Planet(1, 0, 1, 3, 5, 2));
		pw.planets.add(new Planet(2, 0, 100, 5, 1, 3));
		
		
				
		//Enemy 2//id,owner,ships,grow,x,y
		pw.planets.add(new Planet(6, 2, 1, 1, 6, 5));
		pw.planets.add(new Planet(7, 2, 1, 5, 10, 6));
		pw.planets.add(new Planet(8, 2, 5, 4, 1, 8));
		
		//Player 1
		pw.planets.add(new Planet(3, 1, 10, 1, 10, 5));
		pw.planets.add(new Planet(4, 1, 12, 3, 5, 0));
		pw.planets.add(new Planet(5, 1, 30,  4, 1, 4));
		
		//owner, numships, source, destination, turns, left
		pw.fleets.add(new Fleet(1,100,6,5,10,10));
		pw.fleets.add(new Fleet(1,100,3,6,10,10));
		pw.fleets.add(new Fleet(1,100,4,6,10,10));
		return pw;
		
	}
	public static void DoTurn(PlanetWars pw) {
		
		
		try{
			//pw = generateDummyPw(); ///BORRAAAAAAAAAA ESTO!!!!
			System.err.println("1");
			agent.setPlanetWarsData(pw);
			agent.calculatePlanets();
			agent.calculateGlobalRatios();
			System.err.println("2");
			Method method = agent.getClass().getMethod("executeTree", new Class[]{});
			for(Planet p:pw.MyPlanets()){
				agent.setActualPlanet(p);
				agent.calculateActualPlanetRatio();
				method.invoke(agent,new Object[]{});
				//agent.attackQuickPlanet(0.25);
			}
			System.err.println("3");
			
			 int total_Ships = pw.TotalNumShips();
             System.err.println("P1 - " + pw.IsAlive(1) + "-" + pw.NumPlanets(1).size() +"/"+ pw.NumPlanets() + "-"+ pw.NumShips(1) + "/" + total_Ships);
             System.err.println("P2 - " + pw.IsAlive(2) + "-" + pw.NumPlanets(2).size() +"/"+ pw.NumPlanets() + "-"+ pw.NumShips(2) + "/" + total_Ships);
             System.err.println("P3 - " + pw.IsAlive(3) + "-" + pw.NumPlanets(3).size() +"/"+ pw.NumPlanets() + "-"+ pw.NumShips(3) + "/" + total_Ships);
             System.err.println("P4 - " + pw.IsAlive(4) + "-" + pw.NumPlanets(4).size() +"/"+ pw.NumPlanets() + "-"+ pw.NumShips(4) + "/" + total_Ships);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.err.println(args[0]);
		try{
			System.err.println("GENERANDO METODO A PARTIR DE ARBOL");
			String arbol = args[0];
			arbol = arbol.replace("@", " ");
			System.err.println("EL ARBOL ES "+arbol);
			createMethod(arbol);
			System.err.println("ARBOL GENERADO");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		agent = new GPAgent();
		
		
		/*System.out.println("BORRA LO DE ABAJO");
		
		
		DoTurn(null);
		if(true)
			return;
		//HASTA AQUI
		 * */
		String line = "";
		String message = "";
		int c;
		try {
			while ((c = System.in.read()) >= 0) {
				switch (c) {
				case '\n':
					if (line.equals("go")) {
						PlanetWars pw = new PlanetWars(message);
						DoTurn(pw);
						pw.FinishTurn();
						message = "";
					} else {
						message += line + "\n";
					}
					line = "";
					break;
				default:
					line += (char)c;
					break;
				}
			}
		} catch (Exception e) {
			// Owned.
		}
	}
	
	public static void createMethod(String tree) throws NotFoundException, CannotCompileException, SecurityException, NoSuchMethodException, IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		CtClass ctclass = ClassPool.getDefault().get("es.ugr.osgiliath.planetwars.agent.GPAgent"); //ESTO MEJOR COMO PARAMETRO PARA EL FUTURO
        //CtMethod newmethod = CtNewMethod.make("public void testPrint() { System.out.println(\"test ok\"); }",ctclass);
		//String amano = "if(myPlanetsEnemyRatio<0.204)if(myShipsLandedFlyingRatio<0.899)if(myShipsEnemyRatio<0.592)if(myShipsLandedFlyingRatio<0.163)doNothing(0.506);else attackWeakestEnemyPlanet(0.188);else if(myPlanetsTotalRatio<0.525)attackRandomPlanet(0.684);else attackEnemyBase(0.303);else if(myShipsEnemyRatio<0.173)if(myPlanetsTotalRatio<0.436)reinforceWealthest(0.991);else attackRandomPlanet(0.916);else attackQuickPlanet(0.162);else if(myShipsEnemyRatio<0.161)if(myShipsLandedFlyingRatio<0.328)if(actualMyShipsRatio<0.661)attackQuickEnemyPlanet(0.051);else attackBeneficiousEnemyPlanet(0.982);else if(actualLandedFlyingRatio<0.999)doNothing(0.400);else attackBeneficiousEnemyPlanet(0.214);else if(myShipsLandedFlyingRatio<0.124)if(actualLandedFlyingRatio<0.094)attackRandomPlanet(0.361);else reinforceBase(0.534);else if(actualLandedFlyingRatio<0.654)attackNearestPlanet(0.025);else attackNearestNeutralPlanet(0.659);";
		//System.out.println(amano);
		//System.out.println(tree);
		CtMethod newmethod = CtNewMethod.make(" public void executeTree(){"+tree+"}",ctclass);
		ctclass.addMethod(newmethod);
        ctclass.writeFile();
        ctclass.toClass();
       /* for(Method me: ctclass.toClass().getDeclaredMethods()){ //test print, ok
            System.err.println(me.getName());
        }*/
	}

}
