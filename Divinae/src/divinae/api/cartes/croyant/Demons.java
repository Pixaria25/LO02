package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public  class Demons extends Croyant {
  
  public Demons (String nom, Dogme[] dogme) {
	 	super(nom, Origine.Nuit, "Donne un point d'Action d'Origine Nuit.", dogme, 2);
		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
		Capacite.donnerPointAction(1, Origine.Nuit, this.getJoueurLie());
	}
}
