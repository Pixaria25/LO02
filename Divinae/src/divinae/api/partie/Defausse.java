package divinae.api.partie;

import java.util.ArrayList;
import java.util.List;

import divinae.api.cartes.types.Carte;

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
