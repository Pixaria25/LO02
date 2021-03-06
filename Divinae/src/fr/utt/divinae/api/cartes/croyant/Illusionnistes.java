package fr.utt.divinae.api.cartes.croyant;

import java.util.List;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.Origine;
import fr.utt.divinae.api.cartes.types.Utilitaire;
import fr.utt.divinae.api.joueur.Joueur;
import fr.utt.divinae.api.partie.Partie;

public class Illusionnistes extends Croyant {

	public Illusionnistes() {
		super("Illusionnistes", Origine.Nuit, "Vous bénéficiez de la capacité spéciale de sacrifice d'une carte de Croyant "
				+ "appartenant à une autre Divinité. La carte en question reste en jeu.", new Dogme [] {Dogme.Chaos, Dogme.Humain, Dogme.Symboles}, 4, 26);
	}

	@Override
	public void activerCapacite() {
		Partie partie = this.getJoueurLie().getPartie();
		List<Joueur> liste = Utilitaire.extraireListeJoueurRestrainte (partie, getJoueurLie());
		Joueur joueur = getJoueurLie().choisirJoueurCible(liste);

		if (joueur.getNombreCroyant() == 0) {
			joueur = getJoueurLie().choisirJoueurCible(liste);
		}
		
		Capacite.copierCapacite(getJoueurLie(), getJoueurLie().choisirCroyant(joueur), partie);
		Capacite.defausser(this, this.getJoueurLie().getPartie());

	}
}
