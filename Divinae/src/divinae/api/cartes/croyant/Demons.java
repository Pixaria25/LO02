package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public  class Demons extends Croyant {
  
  public Demons (Dogme[] dogme, int id) {
	 	super("Demons", Origine.Nuit, "Donne un point d'Action d'Origine Nuit.", dogme, 2, id);
	}
  
  public void activerCapacite() {
		Capacite.donnerPointAction(1, Origine.Nuit, this.getJoueurLie());
	}
}
