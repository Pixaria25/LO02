package cartes.deuxex;

import cartes.Action;
import cartes.Carte;
import cartes.Origine;

public class InfluenceNuit extends Carte implements Action {

	public InfluenceNuit(String nom, Origine origine, String capacite) {
		super("Influence Nuit", Origine.Nuit, "Annule la capacit� sp�ciale d'une carte d'Action d'Origine Jour ou N�ant.");
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
