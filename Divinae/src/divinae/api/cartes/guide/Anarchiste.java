package divinae.api.cartes.guide;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;

public class Anarchiste extends GuideSpirituel {

  public Anarchiste () {
	 	super("Anarchiste", Origine.Neant,  "Sacrifie un Guide Spirituel, si lui ou sa Divinit� ne croit pas au Dogme Chaos."
	 			+ "Les capacit�s sp�ciales sont jou�es normalement.", new Dogme [] {Dogme.Humain,Dogme.Chaos}, 3, 50);

		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		GuideSpirituel Gsp = Capacite.getActionSuivante().choisirDiviniteOuGspNonDogme(Dogme.Chaos, this.getJoueurLie().getPartie());
		Gsp.activerCapacite();
		Capacite.defausser(Gsp, this.getJoueurLie().getPartie());
	}


}
