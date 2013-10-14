package es.ugr.osgiliath.planetwars;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.GenericTreeNode;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.elements.FitnessCalculator;
import es.ugr.osgiliath.evolutionary.individual.Fitness;
import es.ugr.osgiliath.evolutionary.individual.Individual;

public class PlanetWarsFitnessCalculator extends OsgiliathService implements FitnessCalculator{

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
		
			//theTree = writePlanetWarsTreeAux(tree.getRoot(),1); //INDENTED
			theTree = writePlanetWarsTreeAux(tree.getRoot());
		
		return theTree;
		
	}
	
	public String writePlanetWarsTreeIndented(TreeGenome tree){
		String theTree = ""; 
		
			theTree = writePlanetWarsTreeAux(tree.getRoot(),1); //INDENTED
			//theTree = writePlanetWarsTreeAux(tree.getRoot());
		
		return theTree;
		
	}
	
	
	private String writePlanetWarsTreeAux(GenericTreeNode node, int indent){
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
			System.out.println("ERROR: NUMERO DE HIJOS ERRONEO!!! "+node.getNumberOfChildren() );
		}
		return str;
		
	}

	private String writePlanetWarsTreeAux(GenericTreeNode node){
		String str = "";
		String indentStr = "";
		str = indentStr+node.getData().toString();
		
		if(node.getChildren().size()==2){
			str = str + writePlanetWarsTreeAux(node.getChildAt(0));
			str = str + indentStr+"else "+writePlanetWarsTreeAux(node.getChildAt(1));
		}
		if(node.getChildren().size()==1 || node.getChildren().size()>2){
			System.out.println("ERROR: NUMERO DE HIJOS ERRONEO!!! "+node.getNumberOfChildren() );
		}
		return str;
		
	}
	
	public String generateLaunchScript(String tree){
		
		String folder = (String)this.getAlgorithmParameters().getParameter(PlanetWarsParameters.ENVIRONMENT_FOLDER);
		String simulator = (String)this.getAlgorithmParameters().getParameter(PlanetWarsParameters.ENVIRONMENT_SIMULATOR);		
		String turns = (String)this.getAlgorithmParameters().getParameter(PlanetWarsParameters.ENVIRONMENT_TURNS);
		String time = (String)this.getAlgorithmParameters().getParameter(PlanetWarsParameters.ENVIRONMENT_TIME);
		
		String agentJar = (String)this.getAlgorithmParameters().getParameter(PlanetWarsParameters.ENVIRONMENT_AGENT_JAR);
		String enemyJar = (String)this.getAlgorithmParameters().getParameter(PlanetWarsParameters.ENVIRONMENT_ENEMY_JAR);
		
		String agent = "java -jar "+folder+agentJar+" "+"\'"+tree+"\'";
		String enemy = "java -jar "+folder+enemyJar;
		String complete = folder+simulator +" "+
		 time +" "+
		 turns + " " +
		"\""+agent+"\""+
		"\""+enemy+"\"";
		
		return complete;
	}
	public void executeScript(String tree, String map) throws IOException, InterruptedException {

		String line = this.generateLaunchScript(tree);
		Process _p = Runtime.getRuntime().exec(line);
		
        //System.out.println(_p.getOutputStream().toString());

        BufferedReader in = new BufferedReader(
                new InputStreamReader(_p.getInputStream()));
        String turn = "";
        String winer ="";
        while ((line = in.readLine()) != null) {
        
                System.out.print(line);
               /* turn = winer;
                if (winer != "Draw!") {
                    winer = line;
                } else {
                    br2.readLine();
                    winer = line;
                }*/

            
        }
        in.close();


        _p.waitFor();
	}
}
