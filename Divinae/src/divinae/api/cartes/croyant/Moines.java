package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Moines extends Croyant {
  
  public Moines (Dogme [] dogme, int id) {
	 	super("Moines", Origine.Jour, "Donne un point d'action d'Origine jour", dogme, 2, id);
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		Capacite.donnerPointAction(1, Origine.Jour, this.getJoueurLie());
	}
	
	
}
