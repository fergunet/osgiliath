package es.ugr.osgiliath.planetwars;

import java.util.ArrayList;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.GenericTreeNode;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.elements.Crossover;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class PlanetWarsCrossover implements Crossover{

	@Override
	public ArrayList<Genome> cross(Genome mother, Genome father) {
		TreeGenome motherTree = (TreeGenome) mother;
		TreeGenome fatherTree = (TreeGenome) father;
		
		TreeGenome childA = (TreeGenome) motherTree.clone();
		TreeGenome childB = (TreeGenome) fatherTree.clone();
		
		
		
		GenericTreeNode branchA = childA.getRandomBranch();
		GenericTreeNode branchB = childB.getRandomBranch();
		System.out.println("BRANCH A: "+branchA);
		System.out.println("BRANCH B: "+branchB);
		
		GenericTreeNode parentA = branchA.getParent();
		GenericTreeNode parentB = branchB.getParent();
		System.out.println("PARENT of A: "+parentA);
		System.out.println("PARENT of B: "+parentB);
		
		
		int indexA = parentA.getChildren().indexOf(branchA);
		int indexB = parentB.getChildren().indexOf(branchB);
		System.out.println("A is in position "+indexA);
		System.out.println("B is in position "+indexB);
		
		parentA.addChildAt(indexA, branchB);
		parentA.removeChildAt(indexA+1);
		
		parentB.addChildAt(indexB, branchA);
		parentB.removeChildAt(indexB+1);
				
		ArrayList<Genome> gens = new ArrayList<Genome>();
		gens.add(childA);
		gens.add(childB);
		
		return gens;
	}

}
