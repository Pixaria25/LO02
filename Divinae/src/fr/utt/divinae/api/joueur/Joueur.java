package fr.utt.divinae.api.joueur;

import java.util.ArrayList;
import java.util.List;

import fr.utt.divinae.api.cartes.types.Carte;
import fr.utt.divinae.api.cartes.types.CarteAction;
import fr.utt.divinae.api.cartes.types.CarteSacrifiable;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Divinite;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;
import fr.utt.divinae.api.partie.Partie;

/**
 * La classe Joueur represente un joueur, c'est-a-dire un participant a une partie.
 * @author Thomas, Abraham
 *
 */
public class Joueur {

	private String nom;
	private Divinite divinite;
	private List<CarteAction> main;
	private int nombreCroyant;
	private int[] pointsAction = {0, 0, 0};
	private List<GuideSpirituel> guides;
	private boolean autorisationgsp = true;
	private boolean autorisationcr = true;
	private Partie partie;
	
	public static final int TAILLE_MAIN_MAX = 7;
	
	private static Selection selection = null;
	
	/**
	 * Met en place le type de selection adapte a l'interface choisie.
	 * @param typeSelection le type de selection souhaite
	 */
	public static void setSelection (Selection typeSelection)
	{
		selection = typeSelection;
	}
	
	public Joueur(String nom, Partie partie) {
		this.nom = nom;
		this.divinite = null;
		this.main = new ArrayList<CarteAction>();
		this.nombreCroyant = 0;
		this.guides = new ArrayList<GuideSpirituel>();
		this.partie = partie;
	}
	
	/**
	 * 
	 */
	public void jouer() {
		selection.menu(this);
	}
	
	/**
	 * Le joueur pose une carte Action sur la table.
	 * @param choixCarte le numero de la carte a poser
	 * @return un boolean indiquant si la carte a ete posee
	 */
	public boolean poserCarteAction(int choixCarte) {
		if(carteJouable(choixCarte)) {
			if(!(main.get(choixCarte).getOrigine() == Origine.Aucune)) {
				pointsAction[main.get(choixCarte).getOrigine().ordinal()]--;
			}
			CarteAction carteAction = main.get(choixCarte);
			partie.getTable().add(carteAction);
			selection.messageRecap(carteAction.getJoueurLie().getNom() + " pose " + carteAction.getNom());
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Verifie le joueur a suffisamment de points du bon type pour jouer la carte selectionnee.
	 * @param choixCarte le numero de la carte que le joueur souhaite jouer
	 * @return un boolean indiquant si la carte peut etre posee
	 */
	private boolean carteJouable(int choixCarte) {

		if(main.get(choixCarte).getOrigine() == Origine.Aucune) {
			return true;
		} else {
			if(pointsAction[main.get(choixCarte).getOrigine().ordinal()] >= 1) {
				return true;
			}
		}
		return false;
	}
	 
	/**
	 * Active la capacite d'une carte.
	 * @param carte la carte dont on veut activer la capacite
	 */
	public void activerCapaciteCarte(Carte carte) {
		carte.activerCapacite();
	}
	
	
	
	/**
	 * Retire une liste de cartes de la main du joueur.
	 * @param carteADefausser une liste contenant des references aux cartes qu'on veut defausser
	 */
	public void defausser(List<CarteAction> carteADefausser) {
		main.removeAll(carteADefausser);
	}
		
	public void completerMain() {
		int nbreCartes = TAILLE_MAIN_MAX-main.size();
		for(int i = 0; i < nbreCartes; i++) {
			if(partie.getPioche().isEmpty()) {
				if(partie.getDefausse().isEmpty()) {
					return;
				} else {
					partie.getDefausse().remettrePioche(partie.getPioche());
				}
			}
			CarteAction carte = partie.getPioche().sortirUneCarte();
			if (carte != null) {
				carte.setJoueurLie(this);
				main.add(carte);
			}
		}
	}
	
	/**
	 * Recupere et renvoie la liste des cartes sacrifiables du joueur.
	 * @return une liste contenant les cartes sacrifiables du joueur
	 */
	public List<CarteSacrifiable> recupererCartesSacrifiables() {
		List<CarteSacrifiable> listeCartesSacrifiables = new ArrayList<CarteSacrifiable>();
		listeCartesSacrifiables.addAll(getGuides());
		for(int j = 0; j < getGuides().size(); j++) {
			listeCartesSacrifiables.addAll(getGuide(j).getCroyantLie());
		}
		return listeCartesSacrifiables;
	}
	
	public void sacrifierCarte(CarteSacrifiable carte) {
		if (carte.isAutorisationSacrifice()) {
			carte.sacrifice();
		} else { 
			selection.messageRecap(carte.getNom() + " est protegée contre le sacrifice");
		}
	}

	public void tuerCarte(CarteAction carte) {
		carte.mort();
	}
	
	//Methodes d'affichage
	/**
	 * Retourne une chaine contenant le nom des cartes dans la main du joueur.
	 * @return une chaine contenant le nom des cartes dans la main du joueur
	 */
	public String afficherMain() {
		String retour = "Main:	";
		for(int i = 0; i < main.size(); i++) {
			retour += i+" - "+main.get(i).getCategorieEtNom()+"	";
		}
		return retour;
	}
	
	/**
	 * Retourne une chaine contenant toutes les informations de chaque carte dans la main du joueur.
	 * @return une chaine contenant toutes les informations de chaque carte dans la main du joueur
	 */
	public String afficherMainDetails() {
		String retour = "";
		for(int i = 0; i < main.size(); i++) {
			retour += i+" - "+main.get(i)+"\n";
		}
		return retour;
	}
	
	public String afficherMainJouable() {
		String affichage = "";
		for(int i = 0; i < main.size(); i++) {
			affichage += i +" - " + main.get(i).getCategorieEtNom();
			if(main.get(i).getOrigine() == Origine.Aucune || pointsAction[main.get(i).getOrigine().ordinal()] > 0) {
				affichage += "(V)";
			}
			affichage += "	";
		}
		return affichage;
	}
	
	public String afficherPoints() {
		String retour = "Points :	";
		for(int i = 0; i < pointsAction.length; i++) {
			retour += Origine.values()[i]+": "+pointsAction[i]+",	"; 
		}
		return retour;
	}

	//Appel des methodes de ActionSuivante
	public void demanderInterruption(Joueur joueurCourant) {
		selection.demanderInterruption(joueurCourant);
	}
	
	public void interruption(Joueur joueurCourant) {
		selection.interruption(joueurCourant);
	}
	
	public Joueur choisirJoueurCible(List<Joueur> liste) {
		return selection.choisirJoueurCible(liste);
	}
	
	public GuideSpirituel choisirGsp() {
		return selection.choisirGsp(this, getPartie());
	}
	
	public Divinite choisirDiviniteOuDogme (Dogme dogme1, Dogme dogme2) {
		return selection.choisirDiviniteOuDogme(dogme1, dogme2, getPartie());
	}
	
	public GuideSpirituel choisirSonGsp () {
		return selection.choisirSonGsp(this, getPartie());
	}
	
	public Croyant choisirCroyant (Joueur joueur) {
		return selection.choisirCroyant(joueur, getPartie());
	}
	
	public Origine choisirOrigine () {
		return selection.choisirOrigine();
	}
	
	public GuideSpirituel choisirDiviniteOuGspNonDogme (Dogme dogme) {
		return selection.choisirDiviniteOuGspNonDogme(dogme, getPartie());
	}
	
	public int choisirFaceDe (Joueur joueur) {
		return selection.choisirFaceDe(joueur, getPartie());
	}
	
	public boolean choixMultiples (String cible) {
		return selection.choixMultiples(cible);
	}
	
	public int gspOuCroyant () {
		return selection.gspOuCroyant();
	}
	
	public Croyant choisirTasCroyant() {
		return selection.choisirTasCroyant(this, getPartie());
	}
	
	public GuideSpirituel choisirGspRetire(List<GuideSpirituel> gspCiblable) {
		return selection.choisirGspRetire(gspCiblable);
	}

	public void messageRecap (String message) {
			selection.messageRecap(message);
	}
	
	
	//Getters et setters
	public Selection getSelection() {
		return selection;
	}
	
	public Divinite getDivinite() {
		return divinite;
	}

	public void setDivinite(Divinite divinite) {
		this.divinite = divinite;
	}

	public int getNombreCroyant() {
		return nombreCroyant;
	}

	public void setNombreCroyant(int nombreCroyant) {
		this.nombreCroyant = nombreCroyant;
	}

	public int[] getPointsAction() {
		return pointsAction;
	}

	public void ajoutPointsAction(int point, Origine origine) {
		this.pointsAction[origine.ordinal()] += point;
	}

	public void soustrPointsAction(int point, Origine origine) {
		this.pointsAction[origine.ordinal()] -= point;
	}
	
	public String getNom() {
		return nom;
	}

	public List<CarteAction> getMain() {
		return main;
	}
	
	public List<GuideSpirituel> getGuides() {
		return guides;
	}

	public GuideSpirituel getGuide(int i) {
		return guides.get(i);
	}

	public void setAutorisation(String vise) {
		switch (vise) {
			case "GuideSpirituel" :
				autorisationgsp = false;
				break;
			case "Croyant" :
				autorisationcr = false;
				break;
				default : ;
				
		}
	}

	public boolean isAutorisationgsp() {
		return autorisationgsp;
	}

	public boolean isAutorisationcr() {
		return autorisationcr;
	}

	public Partie getPartie() {
		return partie;
	}

	public boolean aDesCartesSansOrigine() {
		boolean retour = false;
		for(int i = 0; i < main.size(); i++) {
			if(main.get(i).getOrigine() == Origine.Aucune) {
				retour = true;
			}
		}
		return retour;
	}

	

	
}
