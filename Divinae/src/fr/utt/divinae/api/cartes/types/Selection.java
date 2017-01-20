package fr.utt.divinae.api.cartes.types;

import java.util.List;

import fr.utt.divinae.api.joueur.Joueur;
import fr.utt.divinae.api.partie.Partie;
/**
 * L'interface Selection contient les methodes qui vont permettre a un joueur reel de 
 * donner une cible aux effets de certaines cartes. L'interface permet de creer une 
 * implementation differente selon l'interface de jeu qu'on utilise (lignes de commandes
 * ou graphique).
 * @author Thomas, Abraham
 *
 */
public interface Selection
{
	void menu(Joueur joueurCourant);
	
	/**
	 * 
	 * @param joueurCourant
	 */
	public void demanderInterruption(Joueur joueurCourant);
	
	/**
	 * 
	 * @param joueurCourant
	 */
	public void interruption(Joueur joueurCourant);
	
	/**
	 * 
	 * @param liste
	 * @return
	 */
	Joueur choisirJoueurCible(List<Joueur> liste);
	
	/**
	 * 
	 * @param joueur
	 * @param partie
	 * @return
	 */
	GuideSpirituel choisirGsp (Joueur joueur, Partie partie);
	
	Divinite choisirDiviniteOuDogme (Dogme dogme1, Dogme dogme2, Partie partie);
	
	GuideSpirituel choisirSonGsp (Joueur joueur, Partie partie);
	
	Croyant choisirCroyant (Joueur joueur, Partie partie);
	
	Origine choisirOrigine ();
	
	GuideSpirituel choisirDiviniteOuGspNonDogme (Dogme dogme, Partie partie);
	
	int choisirFaceDe (Joueur joueur,Partie partie);
	
	boolean choixMultiples (String cible);
	
	int gspOuCroyant ();
	
	void messageRecap (String message);

	Croyant choisirTasCroyant(Joueur joueur, Partie partie);
	
	GuideSpirituel choisirGspRetire (List <GuideSpirituel> gspCiblable);
}
