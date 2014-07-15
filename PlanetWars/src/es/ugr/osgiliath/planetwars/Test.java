package es.ugr.osgiliath.planetwars;

import java.util.ArrayList;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.GenericTreeNode;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.planetwars.agent.Fleet;
import es.ugr.osgiliath.planetwars.agent.GPAgent;
import es.ugr.osgiliath.planetwars.agent.GPbot;
import es.ugr.osgiliath.planetwars.agent.Planet;
import es.ugr.osgiliath.planetwars.agent.PlanetWars;
import es.ugr.osgiliath.planetwars.fitness.PlanetWarsFitnessCalculator;

public class Test {

	TreeGenome arb;
	GPbot gpbot;
	PlanetWars pw;
	
	public String testArbol(){
TreeGenome arbol = new TreeGenome();
		
		//root
		Decission dr = new Decission("a", 1);
		GenericTreeNode raiz = new GenericTreeNode();
		raiz.setData(dr);
		
		arbol.setRoot(raiz);
		
		//childs
		GenericTreeNode b1 = new GenericTreeNode(new Decission("b1", 2));
		GenericTreeNode b2 = new GenericTreeNode(new Decission("b2", 2));
		GenericTreeNode c22 = new GenericTreeNode(new Decission("c22", 2));
		
		//child leafs
		GenericTreeNode c11 = new GenericTreeNode(new Action("c11_",0.1));
		GenericTreeNode c12 = new GenericTreeNode(new Action("c12_",0.1));
		GenericTreeNode c21 = new GenericTreeNode(new Action("c21_",0.1));
		GenericTreeNode d221 = new GenericTreeNode(new Action("c221_",0.1));
		GenericTreeNode d222 = new GenericTreeNode(new Action("c222_",0.1));
		
		raiz.addChild(b1);
		raiz.addChild(b2);
		
		b1.addChild(c11);
		b1.addChild(c12);
		
		b2.addChild(c21);
		b2.addChild(c22);
		
		c22.addChild(d221);
		c22.addChild(d222);
		
		System.out.println(arbol.toString());
		
		PlanetWarsFitnessCalculator fc = new PlanetWarsFitnessCalculator(1);
		
		System.out.println(fc.writePlanetWarsTree(arbol));
		
		
		
		PlanetWarsRandomInitializer init = new PlanetWarsRandomInitializer();
		
		ArrayList<Individual> inits =init.initializeIndividuals(4);
		for(Individual i:inits){
			BasicIndividual bi = (BasicIndividual)i;
			TreeGenome t = (TreeGenome) bi.getGenome();
			System.out.println(fc.writePlanetWarsTree(t)+"\n\n\n\n");
			System.out.println(t.getDepth());
			
		}
		
		PlanetWarsCrossover cross = new PlanetWarsCrossover();
		ArrayList<Genome> offs = cross.cross(inits.get(0).getGenome(), inits.get(1).getGenome());
		printTree((TreeGenome)offs.get(0));
		System.out.println(((TreeGenome)offs.get(0)).getDepth());
		printTree((TreeGenome)offs.get(1));
		System.out.println(((TreeGenome)offs.get(1)).getDepth());
		
		PlanetWarsFitnessCalculator fit = new PlanetWarsFitnessCalculator(1);
		return fit.writePlanetWarsTree((TreeGenome)offs.get(0));
		
	}
	
	public PlanetWars createDummyPW(){
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
	
	
	public static void main(String[] args) {
	  Test t = new Test();
	  PlanetWars pew = t.createDummyPW();
	  String arbolico = t.testArbol();
	  String[] argum = {"a",arbolico};
	 // GPbot.setDummyPw(pew);
	  GPbot.main(argum);
	  
	  
		
		

	}
	
	private static void printTree(TreeGenome tree){
		PlanetWarsFitnessCalculator fc = new PlanetWarsFitnessCalculator(1);
		System.out.println(fc.writePlanetWarsTree(tree));
		System.out.println("\n\n");
	}

	private void me(){
	}
}
