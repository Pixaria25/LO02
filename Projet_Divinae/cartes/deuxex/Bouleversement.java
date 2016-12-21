package cartes.deuxex;

import cartes.Action;
import cartes.Carte;
import cartes.Origine;

public class Bouleversement extends Carte implements Action {

	public Bouleversement() {
		super("Bouleversement", Origine.Aucune, "Relancez le dé de Cosmogonie. "
				+ "Le tour de jeu se terminera normalement, mais sous la nouvelle influence.");
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
