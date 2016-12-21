package cartes.deuxex;

import cartes.Action;
import cartes.Carte;
import cartes.Origine;

public class InfluenceJour extends Carte implements Action {

	public InfluenceJour() {
		super("Influence Jour", Origine.Jour, "Annule la capacité spéciale d'une carte d'Action d'Origine Nuit ou Néant.");
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
