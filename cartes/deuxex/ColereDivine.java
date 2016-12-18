package cartes.deuxex;

import cartes.Action;
import cartes.Carte;
import cartes.Origine;

public class ColereDivine extends Carte implements Action {

	public ColereDivine(Origine origine) {
		super("Col�re Divine", origine, "");
		String capacite = "D�truit une carte Guide Spirituel d'Origine ";
		if(origine == Origine.Jour) {
			capacite += "Nuit";
		} else {
			capacite += "Jour";
		}
		capacite += " ou N�ant, dont la capacit� sp�ciale n'a pas effet. Les Croyants attach�s reviennent au centre de la table.";
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
