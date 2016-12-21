package cartes.deuxex;

import cartes.Action;
import cartes.Carte;
import cartes.Origine;

public class InfluenceNeant extends Carte implements Action {

	public InfluenceNeant() {
		super("Influence Neant", Origine.Neant, "Annule la capacité spéciale d'une carte d'Action d'Origine Jour ou Nuit.");
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
