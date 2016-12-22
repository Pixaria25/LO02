package cartes.deuxex;

import cartes.Action;
import cartes.Carte;
import cartes.Origine;

public class ColereDivine extends Carte implements Action {

	public ColereDivine(Origine origine) {
		super("Colère Divine", origine, "");
		String capacite = "Détruit une carte Guide Spirituel d'Origine ";
		if(origine == Origine.Jour) {
			capacite += "Nuit";
		} else {
			capacite += "Jour";
		}
		capacite += " ou Néant, dont la capacité spéciale n'a pas effet. Les Croyants attachés reviennent au centre de la table.";
		setCapacite(capacite);
	}

	@Override
	public void poserCarteAction() {
		activerCapacite();

	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub

	}

}
