package partie;

import java.util.ArrayList;
import java.util.List;

import cartes.Carte;

public class Defausse {

	private List<Carte> defausse;
	
	public Defausse() {
		defausse = new ArrayList<Carte>();
	}
	
	public void ajoutCarte(Carte carte) {
		defausse.add(carte);
	}
	
	public void remettrePioche(Pioche pioche) {
		java.util.Collections.shuffle(defausse);
		pioche.setPioche(defausse);
	}
}
