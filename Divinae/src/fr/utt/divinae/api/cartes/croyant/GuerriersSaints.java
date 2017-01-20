package fr.utt.divinae.api.cartes.croyant;

import java.util.List;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.Origine;
import fr.utt.divinae.api.cartes.types.Utilitaire;
import fr.utt.divinae.api.joueur.Joueur;

public class GuerriersSaints extends Croyant {

	public GuerriersSaints() {
		super("Guerriers Saints", Origine.Jour, "Un Guide Spirituel revient dans la main de sa Divinit�."
				+ " Ses Croyants reviennent au centre de la table.",new Dogme[]{Dogme.Symboles,Dogme.Nature,Dogme.Mystique}, 4, 12);
	}

	 public void activerCapacite() {
		 List<Joueur> liste = Utilitaire.extraireListeJoueurRestrainte (getJoueurLie().getPartie(), getJoueurLie());
		 Joueur joueurVise = getJoueurLie().choisirJoueurCible(liste);
		 Capacite.renvoyerCroyantsGsp(joueurVise.getGuides(), this, this.getJoueurLie().getPartie());
	  }
}
