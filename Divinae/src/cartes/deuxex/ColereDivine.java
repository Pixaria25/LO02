package cartes.deuxex;

import cartes.DeusEx;
import cartes.Origine;

public class ColereDivine extends DeusEx {

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
	public void activerCapacite() {
		if(this.getOrigine() == Origine.Jour) {
			
		} else {
			
		}
	}

}
