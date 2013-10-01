package es.ugr.osgiliath.planetwars;

import java.util.ArrayList;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.GenericTreeNode;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.Initializer;

public class PlanetWarsRandomInitializer implements Initializer{

	@Override
	public ArrayList<Individual> initializeIndividuals(int size) {
		ArrayList<Individual> pop = new ArrayList<Individual>();
		
		for(int i = 0; i< size;i++){
			Individual ind = new BasicIndividual();
			ind.setGenome(generateRandomTree());
			pop.add(ind);
		}
		return pop;
	}
	
	private TreeGenome<TreeElement> generateRandomTree(){
		TreeGenome<TreeElement> arbol = new TreeGenome<TreeElement>();
		
		//root
		Decission dr = Decission.generateRandomDecission();
		GenericTreeNode<TreeElement> raiz = new GenericTreeNode<TreeElement>();
		raiz.setData(dr);
		
		arbol.setRoot(raiz);
		int deep = 4;
		addRandomChilds(raiz,deep-1);  //the root is created
		
		return arbol;
	}
	
	
	private void addRandomChilds(GenericTreeNode<TreeElement> node, int depth){
		if(depth <= 1){
			
			GenericTreeNode<TreeElement> a1 = new GenericTreeNode<TreeElement>(Action.generateRandomAction());
			GenericTreeNode<TreeElement> a2 = new GenericTreeNode<TreeElement>(Action.generateRandomAction());
			node.addChild(a1);
			node.addChild(a2);
			
		}else{
			GenericTreeNode<TreeElement> a1 = new GenericTreeNode<TreeElement>(Decission.generateRandomDecission());
			GenericTreeNode<TreeElement> a2 = new GenericTreeNode<TreeElement>(Decission.generateRandomDecission());
			node.addChild(a1);
			node.addChild(a2);
			
			addRandomChilds(a1, depth-1);
			addRandomChilds(a2, depth-1);
		}
			
	}

}
