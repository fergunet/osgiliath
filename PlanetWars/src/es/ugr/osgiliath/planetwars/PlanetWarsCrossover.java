package es.ugr.osgiliath.planetwars;

import java.util.ArrayList;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.GenericTreeNode;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.elements.Crossover;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class PlanetWarsCrossover implements Crossover{

	@Override
	public ArrayList<Genome> cross(Genome mother, Genome father) {
		TreeGenome<TreeElement> motherTree = (TreeGenome<TreeElement>) mother;
		TreeGenome<TreeElement> fatherTree = (TreeGenome<TreeElement>) father;
		
		TreeGenome<TreeElement> childA = (TreeGenome<TreeElement>) motherTree.clone();
		TreeGenome<TreeElement> childB = (TreeGenome<TreeElement>) fatherTree.clone();
		
		GenericTreeNode<TreeElement> branchA = childA.getRandomBranch();
		GenericTreeNode<TreeElement> branchB = childB.getRandomBranch();
		
		GenericTreeNode<TreeElement> parentA = branchA.getParent();
		GenericTreeNode<TreeElement> parentB = branchB.getParent();
		
		
		int indexA = parentA.getChildren().indexOf(branchA);
		int indexB = parentB.getChildren().indexOf(branchB);
		
		parentA.addChildAt(indexA, branchB);
		parentB.addChildAt(indexB, branchA);
				
		ArrayList<Genome> gens = new ArrayList<Genome>();
		gens.add(childA);
		gens.add(childB);
		
		return gens;
	}

}
