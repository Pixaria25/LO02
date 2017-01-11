package divinae.api.partie;

import java.util.Collection;
import java.util.Stack;

import divinae.api.cartes.types.CarteAction;

public class Pioche {

	private Stack<CarteAction> pioche;
	
	public Pioche() {
		pioche = new Stack<CarteAction>();
	}
	
	public CarteAction sortirUneCarte() {
		return pioche.pop();
	}

	public void setPioche(Collection<CarteAction> tasDeCarte) {
		this.pioche.addAll(tasDeCarte);
	}
}
