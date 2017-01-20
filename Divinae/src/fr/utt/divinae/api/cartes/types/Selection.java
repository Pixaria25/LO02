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
	 * Donne le choix d'une interruption aux joueurs excepté celui placé en paramètre.
	 * @param joueurCourant Le joueur courant.
	 */
	public void demanderInterruption(Joueur joueurCourant);
	
	/**
	 * Effectue l'interuption, poser un carte ou activer la capacité de la divinité.
	 * @param joueurCourant Le joueur courant.
	 */
	public void interruption(Joueur joueurCourant);
	
	/**
	 * Renvoit le joueur choisit dans la liste placé en paramètre.
	 * @param liste La liste de joueur ciblables.
	 * @return joueur Le joueur ciblé.
	 */
	Joueur choisirJoueurCible(List<Joueur> liste);
	
	/**
	 * Renvoit un guide lié au joueur placé en paramètre.
	 * @param joueur Le joueur ciblé.
	 * @param partie La partie jouée actuellement.
	 * @return Le guide choisit.
	 */
	GuideSpirituel choisirGsp (Joueur joueur, Partie partie);
	
	/**
	 * Renvoit la divinité choisit de la liste retournée par Utilitaire.getDiviniteOuDogme(dogme1, dogme2, partie).
	 * @param partie La partie jouée actuellement.
	 * @return Le choix de la divinité.
	 */
	Divinite choisirDiviniteOuDogme (Dogme dogme1, Dogme dogme2, Partie partie);
	
	/**
	 * Renvoit le guide choisit de la liste retournée par Utilitaire.getSonGsp(joueur, partie).
	 * @param joueur Le joueur ciblé.
	 * @param partie la partie jouée actuellement.
	 * @return Le guide choisit.
	 */
	GuideSpirituel choisirSonGsp (Joueur joueur, Partie partie);

	/**
	 * Renvoit le croyant de la liste retournée par  Utilitaire.getCroyant(joueur, partie).
	 * @param joueur Le joueur ciblé.
	 * @param partie Lapartie jouée actuellement.
	 * @return le croyant choisit.
	 */
	Croyant choisirCroyant (Joueur joueur, Partie partie);
	
	/**
	 * Choisit une origine (Nuit, Jour, Néant).
	 * @return L'origine choisit.
	 */
	Origine choisirOrigine ();
	
	/**
	 * Renvoit un guide choisit dans la liste retournée par Utilitaire.getDiviniteOuGspNonDogme(dogme, partie).
	 * @param dogme Le dogme ciblé.
	 * @param partie La partie jouéee actuellement.
	 * @return Le guide choisit.
	 */
	GuideSpirituel choisirDiviniteOuGspNonDogme (Dogme dogme, Partie partie);
	
	/**
	 * Renvoit le choix de l'influence. 
	 * @param joueur Le joueur qui joue cette capacité.
	 * @param partie La partie jouée  actuellement.
	 * @return L'influence choisit.
	 */
	int choisirFaceDe (Joueur joueur,Partie partie);
	
	/**
	 * Donne la possible de faire plusieur sélections.
	 * @param cible Le type de carte ciblé.
	 * @return true si on veut continuer la sélection.
	 */
	boolean choixMultiples (String cible);
	
	/**
	 * Renvoit notre choix de ciblage entre guide ou croyant.
	 * @return Le choix fait entre utiliser la capacité d'un guide ou d'un croyant.
	 */
	int gspOuCroyant ();
	
	void messageRecap (String message);

	Croyant choisirTasCroyant(Joueur joueur, Partie partie);
	
	GuideSpirituel choisirGspRetire (List <GuideSpirituel> gspCiblable);
}
