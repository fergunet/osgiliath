package es.ugr.osgiliath.planetwars;

import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.GenericTreeNode;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.basiccomponents.individuals.BasicIndividual;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.elements.Population;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;
import es.ugr.osgiliath.evolutionary.individual.Initializer;
import es.ugr.osgiliath.planetwars.fitness.PlanetWarsHierarchicalFitnessMaximization;

public class PlanetWarsCoevolutionRandomInitializer extends OsgiliathService implements Initializer{

	FitnessCalculator fc;
	@Override
	public ArrayList<Individual> initializeIndividuals(int size) {
		ArrayList<Individual> pop = new ArrayList<Individual>();
		
		for(int i = 0; i< size;i++){
			Individual ind = new BasicIndividual();
			ind.setGenome(generateRandomTree());
			pop.add(ind);
		}
				
		Debug _d = new Debug();
		
		PlanetWarsHierarchicalFitnessMaximization f = new PlanetWarsHierarchicalFitnessMaximization(0,0);
		f.setAsWorstValue();
		
		for(Individual in : pop){
			in.setFitness(f);
			//System.out.print(in.toString());
			
			System.out.println(_d.resumeTree(in));
		}
		
		return pop;
	}
	
	private TreeGenome generateRandomTree(){
		TreeGenome arbol = new TreeGenome();
		
		//root
		Decission dr = Decission.generateRandomDecission();
		GenericTreeNode raiz = new GenericTreeNode();
		raiz.setData(dr);
		
		arbol.setRoot(raiz);
		int deep = (Integer) this.getAlgorithmParameters().getParameter(PlanetWarsParameters.INITIAL_DEPTH);
		addRandomChilds(raiz,deep-1);  //the root is created
		
		return arbol;
	}
	
	
	private void addRandomChilds(GenericTreeNode node, int depth){
		if(depth <= 1){
			
			GenericTreeNode a1 = new GenericTreeNode(Action.generateRandomAction());
			GenericTreeNode a2 = new GenericTreeNode(Action.generateRandomAction());
			node.addChild(a1);
			node.addChild(a2);
			
		}else{
			GenericTreeNode a1 = new GenericTreeNode(Decission.generateRandomDecission());
			GenericTreeNode a2 = new GenericTreeNode(Decission.generateRandomDecission());
			node.addChild(a1);
			node.addChild(a2);
			
			addRandomChilds(a1, depth-1);
			addRandomChilds(a2, depth-1);
		}
			
	}

	public void setFitnessCalculator(FitnessCalculator fitnessCalculator) {
		this.fc = fitnessCalculator;
		
	}

}
