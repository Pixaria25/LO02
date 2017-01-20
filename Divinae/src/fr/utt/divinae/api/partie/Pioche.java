package fr.utt.divinae.api.partie;

import java.util.Collection;
import java.util.Stack;

import fr.utt.divinae.api.cartes.types.CarteAction;

/**
 * La classe Pioche represente la pioche de cartes Action d'une partie.
 * @author Thomas, Abraham
 *
 */
public class Pioche {

	private Stack<CarteAction> pioche;
	
	public Pioche() {
		pioche = new Stack<CarteAction>();
	}
	
	/**
	 * Sort une carte de la pioche si celle-ci n'est pas vide.
	 * @return une carte Action
	 */
	public CarteAction sortirUneCarte() {
		if(pioche.isEmpty()) {
			return null;
		} else {
			return pioche.pop();
		}
	}

	/**
	 * Remplit la pioche avec les cartes donnees.
	 * @param tasDeCarte les cartes qui vont alimenter la pioche
	 */
	public void remplirPioche(Collection<CarteAction> tasDeCarte) {
		this.pioche.addAll(tasDeCarte);
	}
	
	public boolean isEmpty() {
		return pioche.isEmpty();
	}
}
