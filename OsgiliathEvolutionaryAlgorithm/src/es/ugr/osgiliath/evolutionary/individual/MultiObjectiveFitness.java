package es.ugr.osgiliath.evolutionary.individual;


public interface MultiObjectiveFitness extends Fitness {
	
	//For MO 
	public boolean dominates(Fitness another);

	public void setParetoLevel(int deep);
	
	public int getParetoLevel();
	
	public boolean[] getMaximizations();
	
	public void setMaximizations(boolean [] maximizations);
	
	public int compareObjective(MultiObjectiveFitness another, int nObj);
	


}
