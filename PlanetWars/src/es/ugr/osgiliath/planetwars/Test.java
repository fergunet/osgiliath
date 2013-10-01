package es.ugr.osgiliath.planetwars;

import java.util.ArrayList;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.GenericTreeNode;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class Test {

	public static void main(String[] args) {
		TreeGenome<TreeElement> arbol = new TreeGenome<TreeElement>();
		
		//root
		Decission dr = new Decission("a", 1);
		GenericTreeNode<TreeElement> raiz = new GenericTreeNode<TreeElement>();
		raiz.setData(dr);
		
		arbol.setRoot(raiz);
		
		//childs
		GenericTreeNode<TreeElement> b1 = new GenericTreeNode<TreeElement>(new Decission("b1", 2));
		GenericTreeNode<TreeElement> b2 = new GenericTreeNode<TreeElement>(new Decission("b2", 2));
		GenericTreeNode<TreeElement> c22 = new GenericTreeNode<TreeElement>(new Decission("c22", 2));
		
		//child leafs
		GenericTreeNode<TreeElement> c11 = new GenericTreeNode<TreeElement>(new Action("c11_",0.1));
		GenericTreeNode<TreeElement> c12 = new GenericTreeNode<TreeElement>(new Action("c12_",0.1));
		GenericTreeNode<TreeElement> c21 = new GenericTreeNode<TreeElement>(new Action("c21_",0.1));
		GenericTreeNode<TreeElement> d221 = new GenericTreeNode<TreeElement>(new Action("c221_",0.1));
		GenericTreeNode<TreeElement> d222 = new GenericTreeNode<TreeElement>(new Action("c222_",0.1));
		
		raiz.addChild(b1);
		raiz.addChild(b2);
		
		b1.addChild(c11);
		b1.addChild(c12);
		
		b2.addChild(c21);
		b2.addChild(c22);
		
		c22.addChild(d221);
		c22.addChild(d222);
		
		System.out.println(arbol.toString());
		
		PlanetWarsFitnessCalculator fc = new PlanetWarsFitnessCalculator();
		
		System.out.println(fc.writePlanetWarsTree(arbol));
		
		
		PlanetWarsRandomInitializer init = new PlanetWarsRandomInitializer();
		
		ArrayList<Individual> inits =init.initializeIndividuals(3);
		for(Individual i:inits){
			BasicIndividual bi = (BasicIndividual)i;
			TreeGenome<TreeElement> t = (TreeGenome<TreeElement>) bi.getGenome();
			System.out.println(fc.writePlanetWarsTree(t)+"\n\n\n\n");
			System.out.println(t.getDepth());
			
			
		}

	}

}
