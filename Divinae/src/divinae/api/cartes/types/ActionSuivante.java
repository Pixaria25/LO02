package divinae.api.cartes.types;

import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public interface ActionSuivante
{
	Joueur choisirJoueurCible (Partie partie);

	GuideSpirituel choisirGuideSpirituelCible (Joueur joueur, String vise);

	Croyant choisirCroyantCible (Joueur joueur, String vise);
}
