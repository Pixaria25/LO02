package cartes.guide;

import cartes.Capacite;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;

public class Anarchiste extends GuideSpirituel {
  
  public Anarchiste () {
	 	super("Anarchiste", Origine.Neant,  "Sacrifie un Guide Spirituel, si lui ou sa Divinit� ne croit pas au Dogme Chaos."
	 			+ "Les capacit�s sp�ciales sont jou�es normalement.", new Dogme [] {Dogme.Humain,Dogme.Chaos}, 3);
		
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		GuideSpirituel Gsp = Capacite.choisirDiviniteOuGspNonDogme(Dogme.Chaos, this.getJoueurLie().getPartie());
		Gsp.activerCapacite();
		Capacite.defausser(Gsp, this.getJoueurLie().getPartie());
	}
	
	  
}
