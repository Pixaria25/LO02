package divinae.api.partie;

import java.util.Collection;
import java.util.Stack;

import divinae.api.cartes.types.Carte;

public class Pioche {

	private Stack<Carte> pioche;
	
	public Pioche() {
		pioche = new Stack<Carte>();
	}
	
	public Carte sortirUneCarte() {
		return pioche.pop();
	}

	public void setPioche(Collection<Carte> tasDeCarte) {
		this.pioche.addAll(tasDeCarte);
	}
}
