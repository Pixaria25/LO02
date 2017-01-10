package divinae.api.cartes.divinite;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;

public class Gorpa extends Divinite {

	public Gorpa() {
		super("Gorpa", Origine.Crepuscule, "Peut récupérer les points d'Action d'une autre Divinité en plus des siens. "
				+ "L'autre Divinité ne reçoit aucun point d'Action ce tour-ci.", 
				new Dogme[]{Dogme.Humain, Dogme.Symboles, Dogme.Chaos}, 
				"Divinité joueuse et espiègle, "
				+ "Gorpa aime gêner ses consœurs dans leur recherche de puissance.", 89);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		super.activerCapacite();
		Joueur joueurCible =  getJoueurLie().choisirJoueurCible();
		this.getJoueurLie().ajoutPointsAction(joueurCible.getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
		this.getJoueurLie().ajoutPointsAction(joueurCible.getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit );
		this.getJoueurLie().ajoutPointsAction(joueurCible.getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);

		joueurCible.soustrPointsAction(joueurCible.getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
		joueurCible.soustrPointsAction(joueurCible.getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit);
		joueurCible.soustrPointsAction(joueurCible.getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);
	}

}
