package divinae.api.cartes.types;

import java.util.List;

import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public interface ActionSuivante
{
	Joueur choisirJoueurCible(Partie partie);
	
	GuideSpirituel choisirGsp (Partie partie);
	
	void renvoyerGsp (List <GuideSpirituel> gspCiblable, Partie partie);
	
	Divinite choisirDiviniteOuDogme (Dogme dogme1, Dogme dogme2, Partie partie);
	
	GuideSpirituel choisirSonGsp (Joueur joueur, Partie partie);
	
	Croyant choisirCroyant (Joueur joueur, Partie partie);
	
	Origine choisirOrigine ();
	
	GuideSpirituel choisirDiviniteOuGspNonDogme (Dogme dogme, Partie partie);
	
	void choisirFaceDe (Carte carte,Partie partie);
	
	boolean choixMultiples (String cible);
	
	int gspOuCroyant ();
	
	void convertirCroyant (Partie partie, GuideSpirituel carte);
	
	void commentaireMethode (String message);

	
}
