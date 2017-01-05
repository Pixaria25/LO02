package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class GuerriersSaints extends Croyant {

	public GuerriersSaints() {
		super("Guerriers Saints", Origine.Jour, "Un Guide Spirituel revient dans la main de sa Divinité."
				+ " Ses Croyants reviennent au centre de la table.",new Dogme[]{Dogme.Symboles,Dogme.Nature,Dogme.Mystique}, 4);
		// TODO Auto-generated constructor stub
	}

	 public void activerCapacite() {
		  	Capacite.renvoyerGsp(this.getJoueurLie().getPartie());
	  }
}
