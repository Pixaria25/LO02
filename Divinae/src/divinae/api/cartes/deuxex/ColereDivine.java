package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.Origine;

public class ColereDivine extends DeusEx {

	public ColereDivine(Origine origine, int id) {
		super("Col�re Divine", origine, "", id);
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
