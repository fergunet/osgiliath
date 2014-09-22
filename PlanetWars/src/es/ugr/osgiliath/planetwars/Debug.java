package es.ugr.osgiliath.planetwars;

import java.util.ArrayList;

import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.GenericTreeNode;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class Debug {
	public String resumeTree(String tree){
		String res = tree;
		
		res= res.replace("else if", "");
		res= res.replace("if", "");
		res= res.replace("else","");
		res= res.replace("attackNearestEnemyPlanet","A");
		res= res.replace("attackNearestNeutralPlanet","B");
		res= res.replace("attackNearestPlanet","C");
		res= res.replace("attackWeakestEnemyPlanet","D");
		res= res.replace("attackWeakestNeutralPlanet","E");
		res= res.replace("attackWeakestPlanet","F");
		res= res.replace("attackEnemyBase","G");
		res= res.replace("attackNeutralBase","H");
		res= res.replace("attackBase","I");
		res= res.replace("attackRandomPlanet","J");
		res= res.replace("attackWealthestEnemyPlanet","K");
		res= res.replace("attackWealthestNeutralPlanet","L");
		res= res.replace("attackWealthestPlanet","N");
		res= res.replace("attackBeneficiousEnemyPlanet","O");
		res= res.replace("attackBeneficiousNeutralPlanet","P");
		res= res.replace("attackBeneficiousPlanet","Q");
		res= res.replace("attackQuickEnemyPlanet","R");
		res= res.replace("attackQuickNeutralPlanet","S");
		res= res.replace("attackQuickPlanet","T");
		res= res.replace("reinforceNearestPlanet","U");
		res= res.replace("reinforceBase","V");
		res= res.replace("reinforceWealthest","W");
		res= res.replace("reinforceWeakest","X");
		res= res.replace("doNothing","Z");
		
		
		res= res.replace("actualMyShipsRatio","a");
		res= res.replace("actualLandedFlyingRatio","b");		
		res= res.replace("myShipsEnemyRatio","c");
		res= res.replace("myShipsLandedFlyingRatio","d");
		res= res.replace("myPlanetsEnemyRatio","e");
		res= res.replace("myPlanetsTotalRatio","f");
		
		res= res.replace(" ", "");
		res= res.replace(";", "");
		res= res.replace("<","");
		res= res.replace("(","");
		res= res.replace(")","");
		res= res.replace("@","");
		
		res= res.replace("0.","");
		res= res.replace("1.000","1000");
		
		
		return res;
	}
	
	public String resumeTree(Individual ind){
		
		return resumeTree(writePlanetWarsTree((TreeGenome) ind.getGenome()));
}



public String writePlanetWarsTree(TreeGenome tree) {
	String theTree = "";

	// theTree = writePlanetWarsTreeAux(tree.getRoot(),1); //INDENTED
	theTree = writePlanetWarsTreeAux(tree.getRoot());

	return theTree;
}

public String writePlanetWarsTreeIndented(TreeGenome tree) {
	String theTree = "";

	theTree = writePlanetWarsTreeAux(tree.getRoot(), 1); // INDENTED
	// theTree = writePlanetWarsTreeAux(tree.getRoot());

	return theTree;

}

private String writePlanetWarsTreeAux(GenericTreeNode node, int indent) {
	String str = "";
	String indentStr = "";
	for (int i = 0; i < indent; i++) {
		indentStr = indentStr + " ";
	}
	str = indentStr + node.getData().toString() + "\n";

	if (node.getChildren().size() == 2) {
		str = str + writePlanetWarsTreeAux(node.getChildAt(0), indent + 1);
		str = str + indentStr + "else \n"
				+ writePlanetWarsTreeAux(node.getChildAt(1), indent + 1);
	}
	if (node.getChildren().size() == 1 || node.getChildren().size() > 2) {
		System.out.println("ERROR: NUMERO DE HIJOS ERRONEO!!! "
				+ node.getNumberOfChildren());
	}
	return str;

}

private String writePlanetWarsTreeAux(GenericTreeNode node) {
	String str = "";
	String indentStr = "";
	str = indentStr + node.getData().toString();

	if (node.getChildren().size() == 2) {
		str = str + writePlanetWarsTreeAux(node.getChildAt(0));
		str = str + indentStr + "else "
				+ writePlanetWarsTreeAux(node.getChildAt(1));
	}
	if (node.getChildren().size() == 1 || node.getChildren().size() > 2) {
		System.out.println("ERROR: NUMERO DE HIJOS ERRONEO!!! "
				+ node.getNumberOfChildren());
	}
	return str;

}



}