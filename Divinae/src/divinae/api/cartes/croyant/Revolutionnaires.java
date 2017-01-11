package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Revolutionnaires extends Croyant {

 
  public Revolutionnaires () {
		super("R�volutionnaires", Origine.Neant, "Imposez le sacrifice d'une carte de Croyants � autant de Divinit�s"
				+ " que vous le voulez. Chaque Divinit� choisit la carte � sacrifier. Les capacit�s sp�ciales sont jou�es.",
				new Dogme [] {Dogme.Symboles, Dogme.Humain, Dogme.Chaos}, 2, 36);

		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
	  boolean stop = false;
	  do {
		Capacite.imposerSacrifice("Croyant",  getJoueurLie(),  getJoueurLie().getPartie());
	  	stop =  getJoueurLie().choixMultiples("Divinit�");
	  } while (!stop);
  }
}
