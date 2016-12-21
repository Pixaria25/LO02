package cartes.deuxex;

import cartes.Action;
import cartes.Carte;
import cartes.Origine;

public class InfluenceNulle extends Carte implements Action {

	public InfluenceNulle() {
		super("Influence Nulle", Origine.Aucune, "Annule la capacité spéciale d'une autre carte d'Action.");
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
