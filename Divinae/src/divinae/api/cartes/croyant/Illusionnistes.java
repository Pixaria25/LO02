package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public class Illusionnistes extends Croyant {

	public Illusionnistes() {
		super("Illusionnistes", Origine.Nuit, "Vous bénéficiez de la capacité spéciale de sacrifice d'une carte de Croyant "
				+ "appartenant à une autre Divinité. La carte en question reste en jeu.", new Dogme [] {Dogme.Chaos, Dogme.Humain, Dogme.Symboles}, 4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Partie partie = this.getJoueurLie().getPartie();
		Joueur joueur = Capacite.getActionSuivante().choisirJoueurCible(partie);
		if (joueur.getNombreCroyant() == 0) {
			joueur = Capacite.getActionSuivante().choisirJoueurCible(partie);
		}
		Capacite.copierCapacite(Capacite.getActionSuivante().choisirCroyant(joueur, partie), partie);
		Capacite.defausser(this, this.getJoueurLie().getPartie());
	}
}
