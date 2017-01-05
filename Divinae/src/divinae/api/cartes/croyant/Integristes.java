package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Integristes extends Croyant {
	
  public Integristes () {
	  super("Intégriste",Origine.Jour, "Impose le sacrifice d'un Guide Spirituel d'un autre joueur, qui choisit"
				+ " la carte. La capacite spéciale du sacrifice est jouée.", new Dogme[]{Dogme.Humain,Dogme.Nature,Dogme.Chaos} , 1);
		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
	  	Capacite.imposerSacrifice("Croyant", this.getJoueurLie().getPartie());
  }
}
