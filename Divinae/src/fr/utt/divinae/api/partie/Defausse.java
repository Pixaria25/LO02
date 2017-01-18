package fr.utt.divinae.api.partie;

import java.util.ArrayList;
import java.util.List;

import fr.utt.divinae.api.cartes.types.CarteAction;

public class Defausse {

	private List<CarteAction> defausse;
	
	public Defausse() {
		defausse = new ArrayList<CarteAction>();
	}
	
	public void ajoutCarte(CarteAction carte) {
		defausse.add(carte);
	}
	
	public void remettrePioche(Pioche pioche) {
		java.util.Collections.shuffle(defausse);
		pioche.setPioche(defausse);
		defausse = new ArrayList<CarteAction>();
	}
	
	public boolean isEmpty() {
		return defausse.isEmpty();
	}
}
