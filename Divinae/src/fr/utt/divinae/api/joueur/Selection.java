package fr.utt.divinae.api.joueur;

import java.util.List;

import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Divinite;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;
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
	/**
	 * Affiche les actions qu'un joueur va pouvoir faire durant son tour.
	 * @param joueurCourant le joueur qui agit
	 */
	public void menu(Joueur joueurCourant);
	
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
	public Joueur choisirJoueurCible(List<Joueur> liste);
	
	/**
	 * 
	 * @param joueur
	 * @param partie
	 * @return
	 */
	public GuideSpirituel choisirGsp (Joueur joueur, Partie partie);
	
	public Divinite choisirDiviniteOuDogme (Dogme dogme1, Dogme dogme2, Partie partie);
	
	public GuideSpirituel choisirSonGsp (Joueur joueur, Partie partie);
	
	public Croyant choisirCroyant (Joueur joueur, Partie partie);
	
	public Origine choisirOrigine ();
	
	public GuideSpirituel choisirDiviniteOuGspNonDogme (Dogme dogme, Partie partie);
	
	public int choisirFaceDe (Joueur joueur,Partie partie);
	
	public boolean choixMultiples (String cible);
	
	public int gspOuCroyant ();
	
	/**
	 * Affiche un message
	 * @param message le message a afficher
	 */
	public void messageRecap (String message);

	/**
	 * Choisit un croyant dans le tas de croyants.
	 * @param joueur le joueur devant choisir un croyant
	 * @param partie la partie en cours
	 * @return le croyant choisi
	 */
	public Croyant choisirTasCroyant(Joueur joueur, Partie partie);
	
	/**
	 * Choisit un guide spirituel a retirer.
	 * @param gspCiblable une liste contenant les guides pouvant etre cibles
	 * @return le guide spirituel a retirer
	 */
	public GuideSpirituel choisirGspRetire (List <GuideSpirituel> gspCiblable);
}
