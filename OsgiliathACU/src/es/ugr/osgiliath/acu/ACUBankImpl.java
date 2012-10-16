package es.ugr.osgiliath.acu;

import es.ugr.osgiliath.OsgiliathService;

public class ACUBankImpl extends OsgiliathService implements ACUBank{

	int actualACUs = -1;
	int crossoverPrize = -1;
	int mutationPrize = -1;
	int migrationPrize = -1;
	
	public void initialize(){
		this.actualACUs = (Integer) this.getAlgorithmParameters().getParameter(ACUParameters.ACU_BANK);
		
		this.crossoverPrize = (Integer) this.getAlgorithmParameters().getParameter(ACUParameters.ACU_CROSSOVER_PRIZE);
		this.mutationPrize = (Integer) this.getAlgorithmParameters().getParameter(ACUParameters.ACU_MUTATION_PRIZE);
		this.migrationPrize = (Integer) this.getAlgorithmParameters().getParameter(ACUParameters.ACU_MIGRATION_PRIZE);
	}

	@Override
	public int getCrossoverPrize() {
		return this.crossoverPrize;
	}

	@Override
	public int getMutationPrize() {
		return this.mutationPrize;
	}

	@Override
	public int getMigrationPrize() {
		return this.migrationPrize;
	}

	@Override
	public int getActualACUs() {
		return this.actualACUs;
	}

	@Override
	public void setCrossoverPrize(int crossoverPrize) {
		this.crossoverPrize = crossoverPrize;
	}

	@Override
	public void setMutationPrize(int mutationPrize) {
		this.migrationPrize = mutationPrize;
	}

	@Override
	public void setMigrationPrize(int migrationPrize) {
		this.migrationPrize = migrationPrize;
	}

	@Override
	public void increaseACUs(int add) {
		this.actualACUs+=add;
	}

	@Override
	public void decreaseACUs(int remove) {
		this.actualACUs-=remove;
	}



	
}
