package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public class Illusionnistes extends Croyant {

	public Illusionnistes() {
		super("Illusionnistes", Origine.Nuit, "Vous b�n�ficiez de la capacit� sp�ciale de sacrifice d'une carte de Croyant "
				+ "appartenant � une autre Divinit�. La carte en question reste en jeu.", new Dogme [] {Dogme.Chaos, Dogme.Humain, Dogme.Symboles}, 4);
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