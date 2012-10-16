package es.ugr.osgiliath.acu;

public interface ACUBank {

	int getCrossoverPrize();
	int getMutationPrize();
	int getMigrationPrize();
	
	int getActualACUs();
	
	void setCrossoverPrize(int crossoverPrize);
	void setMutationPrize(int mutationPrize);
	void setMigrationPrize(int migrationPrize);
	
	void increaseACUs(int add);
	void decreaseACUs(int remove);
	
	public void initialize();
}
