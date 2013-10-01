package es.ugr.osgiliath.planetwars;

import es.ugr.osgiliath.OsgiliathService;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.GenericTreeNode;
import es.ugr.osgiliath.evolutionary.basiccomponents.genomes.TreeGenome;
import es.ugr.osgiliath.evolutionary.elements.Mutation;
import es.ugr.osgiliath.evolutionary.individual.Genome;
import es.ugr.osgiliath.planetwars.agent.GPAgent;


public class PlanetWarsMutation  extends OsgiliathService implements Mutation {

	@Override
	public Genome mutate(Genome genome) {
		TreeGenome<TreeElement> tree = (TreeGenome<TreeElement>) genome;
		//TODO clonar?
		
		GenericTreeNode<TreeElement> branch = tree.getRandomBranch();
		
		if(branch.getData() instanceof Action && branch.getChildren().size()!=0)
			System.out.println("NO DEBERIA TENER HIJOS!!!!");
		
		if(branch.getData() instanceof Action){
			Action action = (Action) branch.getData();
			
			//Change action
			int val = (int) (GPAgent.actionList.size()*Math.random());
			String newAction = GPAgent.actionList.get(val);
			action.setAction(newAction);
			
			//Change percentage
			double stepSize = (Double)this.getAlgorithmParameters().getParameter(PlanetWarsParameters.PERC_STEP_SIZE);
			stepSize = Math.random()*stepSize;
			if(Math.random()>0.5)
				stepSize *=-1;
			action.setPerc(action.getPerc()*stepSize);
		
		}
		
		if(branch.getData() instanceof Decission && branch.getChildren().size()==0)
			System.out.println("DEBERIA TENER HIJOS!!!!");
		if(branch.getData() instanceof Decission){
			Decission decission = (Decission) branch.getData();
			
			//Change decission
			int val = (int) (GPAgent.decissionList.size()*Math.random());
			String newDecission =  GPAgent.decissionList.get(val);
			decission.setVariable(newDecission);
			
		}
		
		return tree;
		
	}

}
