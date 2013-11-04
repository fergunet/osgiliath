package es.ugr.osgiliath.planetwars;

import java.util.ArrayList;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.GenericTreeNode;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.elements.Crossover;
import es.ugr.osgiliath.evolutionary.individual.Genome;

public class PlanetWarsCrossover extends OsgiliathService implements Crossover{

	@Override
	public ArrayList<Genome> cross(Genome mother, Genome father) {
		TreeGenome motherTree = (TreeGenome) mother;
		TreeGenome fatherTree = (TreeGenome) father;
		
		TreeGenome childA = (TreeGenome) motherTree.clone();
		TreeGenome childB = (TreeGenome) fatherTree.clone();
		

		GenericTreeNode branchA;
		GenericTreeNode branchB;
		
		int maxDepth = (Integer) this.getAlgorithmParameters().getParameter(PlanetWarsParameters.MAX_DEPTH);
		int sum = Integer.MAX_VALUE;
		int totalA = Integer.MAX_VALUE;
		int totalB = Integer.MAX_VALUE;
	
		do{
			branchA = childA.getRandomBranch();
			branchB = childB.getRandomBranch();
			
			TreeGenome a = new TreeGenome();
			TreeGenome b = new TreeGenome();
			a.setRoot(branchA);
			b.setRoot(branchB);
			 totalA = b.getDepth() +branchA.getAncestorsNumber();
			 totalB = a.getDepth() +branchB.getAncestorsNumber();
			
		}while((totalA > maxDepth) || (totalB >maxDepth));
		
		//System.out.println("BRANCH A: "+branchA);
		//System.out.println("BRANCH B: "+branchB);
		
		GenericTreeNode parentA = branchA.getParent();
		GenericTreeNode parentB = branchB.getParent();
		
		//System.out.println("PARENT of A: "+parentA);
		//System.out.println("PARENT of B: "+parentB);
		
		
		int indexA = parentA.getChildren().indexOf(branchA);
		int indexB = parentB.getChildren().indexOf(branchB);
		//System.out.println("A is in position "+indexA);
		//System.out.println("B is in position "+indexB);
		
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
