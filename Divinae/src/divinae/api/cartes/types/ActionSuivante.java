package divinae.api.cartes.types;

import java.util.List;

import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public interface ActionSuivante
{
	Joueur choisirJoueurCible(List<Joueur> liste);
	
	GuideSpirituel choisirGsp (Joueur joueur, Partie partie);
	
	Divinite choisirDiviniteOuDogme (Dogme dogme1, Dogme dogme2, Partie partie);
	
	GuideSpirituel choisirSonGsp (Joueur joueur, Partie partie);
	
	Croyant choisirCroyant (Joueur joueur, Partie partie);
	
	Origine choisirOrigine ();
	
	GuideSpirituel choisirDiviniteOuGspNonDogme (Dogme dogme, Partie partie);
	
	void choisirFaceDe (Carte carte,Partie partie);
	
	boolean choixMultiples (String cible);
	
	int gspOuCroyant ();
	
	void messageRecap (String message);

	Croyant choisirTasCroyant(Joueur joueur, Partie partie);
	
	GuideSpirituel choisirGspRenvoye (List <GuideSpirituel> gspCiblable);
	
}
