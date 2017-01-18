package fr.utt.divinae.api.cartes.croyant;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.Origine;

public class Revolutionnaires extends Croyant {

 
  public Revolutionnaires () {
		super("R�volutionnaires", Origine.Neant, "Imposez le sacrifice d'une carte de Croyants � autant de Divinit�s"
				+ " que vous le voulez. Chaque Divinit� choisit la carte � sacrifier. Les capacit�s sp�ciales sont jou�es.",
				new Dogme [] {Dogme.Symboles, Dogme.Humain, Dogme.Chaos}, 2, 36);

	}
  
  public void activerCapacite() {
	  boolean stop = false;
	  do {
		Capacite.imposerSacrifice("Croyant",  getJoueurLie(),  getJoueurLie().getPartie());
	  	stop =  getJoueurLie().choixMultiples("Divinit�");
	  } while (!stop);
  }
}
