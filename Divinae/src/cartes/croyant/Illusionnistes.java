package cartes.croyant;

import cartes.Capacite;
import cartes.Croyant;
import cartes.Dogme;
import cartes.Origine;
import joueur.Joueur;
import partie.Partie;

public class Illusionnistes extends Croyant {

	public Illusionnistes() {
		super("Illusionnistes", Origine.Nuit, "Vous bénéficiez de la capacité spéciale de sacrifice d'une carte de Croyant "
				+ "appartenant à une autre Divinité. La carte en question reste en jeu.", new Dogme [] {Dogme.Chaos, Dogme.Humain, Dogme.Symboles}, 4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Partie partie = this.getJoueurLie().getPartie();
		Joueur joueur = Capacite.choisirJoueurCible(partie);
		if (joueur.getNombreCroyant() == 0) {
			System.out.println("Ce Joueur n'a pas de croyants.");
			joueur = Capacite.choisirJoueurCible(partie);
		}
		Capacite.copierCapacite(Capacite.choisirCroyant(joueur, partie), partie);
		Capacite.defausser(this, this.getJoueurLie().getPartie());
	}
}
