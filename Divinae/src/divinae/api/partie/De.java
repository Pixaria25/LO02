package divinae.api.partie;

import java.util.Random;

import divinae.api.cartes.types.Origine;

public class De {

	private int valeur;
	private Origine influence;

	public De() {
		this.valeur = 1;
	}
	
	public void lancerDe() {
		Random rand = new Random();
		valeur = 1+rand.nextInt(5);
		switch (valeur) { 
			case 1 :
			case 4 :influence = Origine.Jour;
				break;
			case 2 :
			case 5 : influence = Origine.Nuit;
				break;
			default: influence = Origine.Neant;
		}
	}

	public int getValeur() {
		return valeur;
	}

	public Origine getInfluence() {
		return influence;
	}

	public void setInfluence(Origine influence) {
		this.influence = influence;
	}
	
	
}
