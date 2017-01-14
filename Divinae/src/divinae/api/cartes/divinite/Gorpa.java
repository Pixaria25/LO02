package divinae.api.cartes.divinite;

import java.util.List;

import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;

public class Gorpa extends Divinite {

	public Gorpa() {
		super("Gorpa", Origine.Crepuscule, "Peut r�cup�rer les points d'Action d'une autre Divinit� en plus des siens. "
				+ "L'autre Divinit� ne re�oit aucun point d'Action ce tour-ci.", 
				new Dogme[]{Dogme.Humain, Dogme.Symboles, Dogme.Chaos}, 
				"Divinit� joueuse et espi�gle, "
				+ "Gorpa aime g�ner ses cons�urs dans leur recherche de puissance.", 89);
	}

	@Override
	public void activerCapacite() {
		super.activerCapacite();
		List<Joueur> liste = getJoueurLie().getPartie().getJoueurs();
		liste.remove(liste.indexOf(this.getJoueurLie()));
		Joueur joueurCible =  getJoueurLie().choisirJoueurCible(liste);
		
		this.getJoueurLie().ajoutPointsAction(joueurCible.getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
		this.getJoueurLie().ajoutPointsAction(joueurCible.getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit );
		this.getJoueurLie().ajoutPointsAction(joueurCible.getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);

		joueurCible.soustrPointsAction(joueurCible.getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
		joueurCible.soustrPointsAction(joueurCible.getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit);
		joueurCible.soustrPointsAction(joueurCible.getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);
	}

}
