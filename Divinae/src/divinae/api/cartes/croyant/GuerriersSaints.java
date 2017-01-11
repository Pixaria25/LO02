package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;

public class GuerriersSaints extends Croyant {

	public GuerriersSaints() {
		super("Guerriers Saints", Origine.Jour, "Un Guide Spirituel revient dans la main de sa Divinit�."
				+ " Ses Croyants reviennent au centre de la table.",new Dogme[]{Dogme.Symboles,Dogme.Nature,Dogme.Mystique}, 4, 12);
		// TODO Auto-generated constructor stub
	}

	 public void activerCapacite() {
		 Joueur joueurVise = getJoueurLie().choisirJoueurCible();
		 Capacite.renvoyerGsp(joueurVise.getGuides(), this, this.getJoueurLie().getPartie());
	  }
}
