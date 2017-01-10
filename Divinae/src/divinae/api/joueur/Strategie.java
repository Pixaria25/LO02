package divinae.api.joueur;

import java.util.List;

import divinae.api.cartes.types.CarteAction;

public interface Strategie {
	
	public abstract int jouer();
	
	public abstract List<CarteAction> defausser(List<CarteAction> main);

	public abstract int choixCarteAction(List<CarteAction> main);
	
	public abstract CarteAction choixSacrifice(List<CarteAction> cartesValides);
}
