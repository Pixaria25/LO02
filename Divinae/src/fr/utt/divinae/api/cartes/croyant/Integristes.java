package fr.utt.divinae.api.cartes.croyant;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.Origine;

public class Integristes extends Croyant {
	
  public Integristes () {
	  super("Int�griste",Origine.Jour, "Impose le sacrifice d'un Guide Spirituel d'un autre joueur, qui choisit"
				+ " la carte. La capacite sp�ciale du sacrifice est jou�e.", new Dogme[]{Dogme.Humain,Dogme.Nature,Dogme.Chaos} , 1, 11);
	}
  
  public void activerCapacite() {
	  	Capacite.imposerSacrifice("GuideSpirituel", getJoueurLie(), getJoueurLie().getPartie());

  }
}
