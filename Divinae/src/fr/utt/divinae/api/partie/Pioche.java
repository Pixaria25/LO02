package fr.utt.divinae.api.partie;

import java.util.Collection;
import java.util.Stack;

import fr.utt.divinae.api.cartes.types.CarteAction;

/**
 * La classe Pioche represente 
 * @author pixel
 *
 */
public class Pioche {

	private Stack<CarteAction> pioche;
	
	public Pioche() {
		pioche = new Stack<CarteAction>();
	}
	
	public CarteAction sortirUneCarte() {
		if(pioche.isEmpty()) {
			return null;
		} else {
			return pioche.pop();
		}
	}

	public void remplirPioche(Collection<CarteAction> tasDeCarte) {
		this.pioche.addAll(tasDeCarte);
	}
	
	public boolean isEmpty() {
		return pioche.isEmpty();
	}
}
