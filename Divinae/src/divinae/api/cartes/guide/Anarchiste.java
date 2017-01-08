package divinae.api.cartes.guide;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;

public class Anarchiste extends GuideSpirituel {
  
  public Anarchiste () {
	 	super("Anarchiste", Origine.Neant,  "Sacrifie un Guide Spirituel, si lui ou sa Divinité ne croit pas au Dogme Chaos."
	 			+ "Les capacités spéciales sont jouées normalement.", new Dogme [] {Dogme.Humain,Dogme.Chaos}, 3);
		
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		GuideSpirituel Gsp = Capacite.choisirDiviniteOuGspNonDogme(Dogme.Chaos, this.getJoueurLie().getPartie());
		Gsp.activerCapacite();
		Capacite.defausser(Gsp, this.getJoueurLie().getPartie());
	}
	
	  
}
