package cartes.deuxex;

import cartes.DeusEx;
import cartes.Origine;

public class ColereDivine extends DeusEx {

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
	public void activerCapacite() {
		if(this.getOrigine() == Origine.Jour) {
			
		} else {
			
		}
	}

}
