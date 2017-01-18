package fr.utt.divinae.api.cartes.croyant;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.Origine;

public class Nihillistes extends Croyant {
  
  public Nihillistes () {
	 	super("Nihillistes", Origine.Neant, "Jusqu'� la fin du tour, plus aucune Divinit� ne re�oit de points d'Action.",
	 			new Dogme [] {Dogme.Symboles, Dogme.Mystique, Dogme.Chaos}, 4, 37);
	}
  
  public void activerCapacite() {
	  Capacite.bloquerPointAction(this.getJoueurLie().getPartie());
	}
}