package cartes.deuxex;

import cartes.Action;
import cartes.Carte;
import cartes.Origine;

public class TrouNoir extends Carte implements Action {

	public TrouNoir(String nom, Origine origine, String capacite) {
		super("Trou Noir", Origine.Neant, "Aucun autre joueur ne gagne de points d'Action durant ce tour.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void poserCarteAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub

	}

}
