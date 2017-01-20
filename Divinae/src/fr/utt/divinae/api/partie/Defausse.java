package fr.utt.divinae.api.partie;

import java.util.ArrayList;
import java.util.List;

import fr.utt.divinae.api.cartes.types.CarteAction;

/**
 * La classe Defausse represente la defausse d'une partie.
 * @author Thomas, Abraham
 *
 */
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
		pioche.remplirPioche(defausse);
		defausse = new ArrayList<CarteAction>();
	}
	
	public boolean isEmpty() {
		return defausse.isEmpty();
	}
}
