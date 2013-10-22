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
		TreeGenome tree = (TreeGenome) genome;
		//TODO clonar?
		
		GenericTreeNode branch = tree.getRandomBranch();
		
		if(branch.getData() instanceof Action && branch.getChildren().size()!=0)
			System.out.println("NO DEBERIA TENER HIJOS!!!!");
		
		if(branch.getData() instanceof Action){
			Action action = (Action) branch.getData();
			
			if(Math.random()<0.5){
				//Change action
				int val = (int) (GPAgent.actionList.size()*Math.random());
				String newAction = GPAgent.actionList.get(val);
				action.setAction(newAction);
			}else{
				//Change percentage
				double stepSize = (Double)this.getAlgorithmParameters().getParameter(PlanetWarsParameters.PERC_STEP_SIZE);
				stepSize = Math.random()*stepSize;
			
				if(Math.random()>0.5)
					stepSize *=-1;
				double newValue = action.getPerc()+stepSize;
				if(newValue>1)
					newValue = 1;
				if(newValue<0)
					newValue = 0;
			
				action.setPerc(newValue);
			}
		
		}
		
		if(branch.getData() instanceof Decission && branch.getChildren().size()!=2)
			System.out.println("DECISION TIENE "+branch.getChildren().size()+"!!!!");
		if(branch.getData() instanceof Decission){
			Decission decission = (Decission) branch.getData();
			if(Math.random()<0.5){
				//Change decission
				int val = (int) (GPAgent.decissionList.size()*Math.random());
				String newDecission =  GPAgent.decissionList.get(val);
				decission.setVariable(newDecission);
			}else{
				//Change percentage
				double stepSize = (Double)this.getAlgorithmParameters().getParameter(PlanetWarsParameters.PERC_STEP_SIZE);
				stepSize = Math.random()*stepSize;
			
				if(Math.random()>0.5)
					stepSize *=-1;
				double newValue = decission.getValue()+stepSize;
				if(newValue>1)
					newValue = 1;
				if(newValue<0)
					newValue = 0;
			
				decission.setValue(newValue);
			}
			
		}
		
		return tree;
		
	}

}
