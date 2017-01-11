package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Integristes extends Croyant {
	
  public Integristes () {
	  super("Int�griste",Origine.Jour, "Impose le sacrifice d'un Guide Spirituel d'un autre joueur, qui choisit"
				+ " la carte. La capacite sp�ciale du sacrifice est jou�e.", new Dogme[]{Dogme.Humain,Dogme.Nature,Dogme.Chaos} , 1, 11);
		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
	  	Capacite.imposerSacrifice("GuideSpirituel", this, this.getJoueurLie().getPartie());
  }
}
