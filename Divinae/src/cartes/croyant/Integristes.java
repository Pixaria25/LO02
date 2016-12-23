package cartes.croyant;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

public class Integristes extends Croyant {
	
  public Integristes (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
	  super("Int�griste",Origine.Jour, "Impose le sacrifice d'un Guide Spirituel d'un autre joueur, qui choisit"
				+ " la carte. La capacite sp�ciale du sacrifice est jou�e.", new Dogme[]{Dogme.Humain,Dogme.Nature,Dogme.Chaos} , 1);
		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
	  	Capacite.imposerSacrifice("Croyant", this.getJoueurLie().getPartie());
  }
}
