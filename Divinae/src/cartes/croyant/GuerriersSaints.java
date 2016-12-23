package cartes.croyant;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

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
