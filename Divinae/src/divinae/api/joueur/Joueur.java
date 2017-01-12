package divinae.api.joueur;

import java.util.ArrayList;
import java.util.List;

import divinae.api.cartes.types.CarteAction;
import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.partie.Partie;

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
	
	public Joueur(String nom, Partie partie) {
		this.nom = nom;
		this.divinite = null;
		this.main = new ArrayList<CarteAction>();
		this.nombreCroyant = 0;
		this.guides = new ArrayList<GuideSpirituel>();
		this.partie = partie;
	}
	
	public boolean poserCarteAction(int choixCarte) {
		if(carteJouable(choixCarte)) {
			if(!(main.get(choixCarte).getOrigine() == Origine.Aucune)) {
				pointsAction[main.get(choixCarte).getOrigine().ordinal()]--;
			}
			CarteAction carteAction = main.remove(choixCarte);
			partie.getTable().add(carteAction);
			return true;
		} else {
			return false;
		}
	}
	
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
	
	public void jouer() {
		
	}
	 
	public void activerCapaciteCarte(Carte carte) {
		carte.activerCapacite();
	}
	
	public String afficherMain() {
		String retour = "Main:	";
		for(int i = 0; i < main.size(); i++) {
			retour += i+" - "+main.get(i).getCategorieEtNom()+"	";
		}
		return retour;
	}
	
	public String afficherMainDetails() {
		String retour = "";
		for(int i = 0; i < main.size(); i++) {
			retour += i+" - "+main.get(i)+"\n";
		}
		return retour;
	}
	
	public List<Carte> lireCartes() {
		List<Carte> cartes = new ArrayList<Carte>();
		for(int i = 0; i < main.size(); i++) {
			cartes.add(main.get(i));
		}
		return cartes;
	}
	
	public String afficherPoints() {
		String retour = "Points :	";
		for(int i = 0; i < pointsAction.length; i++) {
			retour += Origine.values()[i]+": "+pointsAction[i]+",	"; 
		}
		return retour;
	}
	
	public void defausser(List<CarteAction> carteADefausser) {
		main.removeAll(carteADefausser);
	}
		
	public void completerMain() {
		
		int nbreCartes = TAILLE_MAIN_MAX-main.size();
		for(int i = 0; i < nbreCartes; i++) {
			CarteAction carte = partie.getPioche().sortirUneCarte();
			carte.setJoueurLie(this);
			main.add(carte);
		}
	}
	
	public List<CarteAction> recupererCartesSacrifiables() {
		List<CarteAction> listeCartesSacrifiables = new ArrayList<CarteAction>();
		listeCartesSacrifiables.addAll(getGuides());
		for(int j = 0; j < getGuides().size(); j++) {
			listeCartesSacrifiables.addAll(getGuide(j).getCroyantLie());
		}
		return listeCartesSacrifiables;
	}
	
	public void sacrifierCarte(CarteAction carte) {
		carte.activerCapacite();
		tuerCarte(carte);
	}

	public void tuerCarte(CarteAction carte) {
		if(carte instanceof GuideSpirituel) {
			this.partie.getTasDeCroyants().addAll((((GuideSpirituel) carte).getCroyantLie()));
			((GuideSpirituel) carte).getCroyantLie().clear();
		}
		if(carte instanceof Croyant) {
			((Croyant) carte).setGuideLie(null);
			((Croyant) carte).setRattachable(false);
		}
		carte.setJoueurLie(null);
		partie.getDefausse().ajoutCarte(carte);
	}
	
	//Appel des methodes de ActionSuivante
	public Joueur choisirJoueurCible() {
		return Capacite.getActionSuivante().choisirJoueurCible(getPartie());
	}
	
	public GuideSpirituel choisirGsp() {
		return Capacite.getActionSuivante().choisirGsp(getPartie());
	}
	
	public Divinite choisirDiviniteOuDogme (Dogme dogme1, Dogme dogme2) {
		return Capacite.getActionSuivante().choisirDiviniteOuDogme(dogme1, dogme2, getPartie());
	}
	
	public GuideSpirituel choisirSonGsp () {
		return Capacite.getActionSuivante().choisirSonGsp(this, getPartie());
	}
	
	public Croyant choisirCroyant (Joueur joueur) {
		return Capacite.getActionSuivante().choisirCroyant(joueur, getPartie());
	}
	
	public Origine choisirOrigine () {
		return Capacite.getActionSuivante().choisirOrigine();
	}
	
	public GuideSpirituel choisirDiviniteOuGspNonDogme (Dogme dogme) {
		return Capacite.getActionSuivante().choisirDiviniteOuGspNonDogme(dogme, getPartie());
	}
	
	public void choisirFaceDe (Carte carte) {
		Capacite.getActionSuivante().choisirFaceDe(carte, getPartie());
	}
	
	public boolean choixMultiples (String cible) {
		return Capacite.getActionSuivante().choixMultiples(cible);
	}
	
	public int gspOuCroyant () {
		return Capacite.getActionSuivante().gspOuCroyant();
	}
	
	public Croyant choisirTasCroyant() {
		return Capacite.getActionSuivante().choisirTasCroyant(this, getPartie());
	}
	
	public GuideSpirituel choisirGspRenvoye(List<GuideSpirituel> gspCiblable) {
		return Capacite.getActionSuivante().choisirGspRenvoye(gspCiblable);
	}

	public void messageListe (String message) {
			Capacite.getActionSuivante().messageRecap(message);
	}
	
	
	//Getters et setters
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
