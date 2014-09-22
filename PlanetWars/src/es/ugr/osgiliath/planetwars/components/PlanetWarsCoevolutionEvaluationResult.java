package es.ugr.osgiliath.planetwars.components;

import es.ugr.osgiliath.evolutionary.individual.Individual;

public class PlanetWarsCoevolutionEvaluationResult {
	protected Individual winner;
	protected Individual loser;
	
	public PlanetWarsCoevolutionEvaluationResult(Individual winner,
			Individual loser) {
		super();
		this.winner = winner;
		this.loser = loser;
	}

	public Individual getWinner() {
		return winner;
	}

	public void setWinner(Individual winner) {
		this.winner = winner;
	}

	public Individual getLoser() {
		return loser;
	}

	public void setLoser(Individual loser) {
		this.loser = loser;
	}
	
	
	
}
