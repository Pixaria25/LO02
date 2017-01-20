package fr.utt.divinae.api.cartes.guide;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;

public class Anarchiste extends GuideSpirituel {

  public Anarchiste () {

	 	super("Anarchiste", Origine.Neant,  "Sacrifie un Guide Spirituel, si lui ou sa Divinité ne croit pas au Dogme Chaos."
	 			+ "Les capacités spéciales sont jouées normalement.", new Dogme [] {Dogme.Humain,Dogme.Chaos}, 3, 50);
		

		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		GuideSpirituel Gsp =  getJoueurLie().choisirDiviniteOuGspNonDogme(Dogme.Chaos);
		Gsp.activerCapacite();
		Capacite.defausser(Gsp, this.getJoueurLie().getPartie());
	}


}
