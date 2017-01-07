package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Esprits extends Croyant {
  
  public Esprits (String nom, Dogme[] dogme) {
	 	super(nom, Origine.Neant, "Donne un point d'Action d'Origine Néant.", dogme, 2);
		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
		Capacite.getActionSuivante().donnerPointAction(1, Origine.Neant, this.getJoueurLie());
	}
}
