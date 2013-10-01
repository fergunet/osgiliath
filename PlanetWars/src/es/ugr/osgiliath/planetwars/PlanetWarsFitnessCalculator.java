package es.ugr.osgiliath.planetwars;

import java.util.ArrayList;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.GenericTreeNode;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class PlanetWarsFitnessCalculator implements FitnessCalculator{

	@Override
	public Fitness calculateFitness(Individual ind) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Fitness> calculateFitnessForAll(ArrayList<Individual> inds) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String writePlanetWarsTree(TreeGenome tree){
		String theTree = ""; 
		
			theTree = writePlanetWarsTreeAux(tree.getRoot(),1);
		
		
		return theTree;
		
	}
	
	private String writePlanetWarsTreeAux(GenericTreeNode<TreeElement> node, int indent){
		String str = "";
		String indentStr = "";
		for(int i = 0; i<indent;i++){
			indentStr = indentStr+" ";
		}
		str = indentStr+node.getData().toString()+"\n";
		
		if(node.getChildren().size()==2){
			str = str + writePlanetWarsTreeAux(node.getChildAt(0),indent+1);
			str = str + indentStr+"else \n"+writePlanetWarsTreeAux(node.getChildAt(1),indent+1);
		}
		if(node.getChildren().size()==1 || node.getChildren().size()>2){
			System.out.println("ERROR: NUMERO DE HIJOS ERRONEO!!!");
		}
		return str;
		
	}

}
