package joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cartes.Carte;
import cartes.Croyant;
import cartes.GuideSpirituel;
import cartes.Origine;
import cartes.divinite.Divinite;
import partie.Defausse;
import partie.Partie;

public class Joueur {

	private String nom;
	private Divinite divinite;
	private List<Carte> main;
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
		this.main = new ArrayList<Carte>();
		this.nombreCroyant = 0;
		this.guides = new ArrayList<GuideSpirituel>();
		this.partie = partie;
	}
	
	public void poserCarteAction() {
		
	}
	
	public void activerCapaciteCarte(Carte carte) {
		if ((autorisationgsp == false && carte instanceof GuideSpirituel) | (autorisationcr == false && carte instanceof Croyant)) {
			System.out.println("Vous ne pouvez pas sacrifier de carte ce tour ci ! (utilisation d'une capacité contre vous)");
		} else { 
			carte.activerCapacite();
		}
	}
	
	public void defausser(int nombreCartes) {
		System.out.println("Quelles cartes voulez-vous défausser ?");
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < nombreCartes; i++) {
			boolean carteDefaussee = false;
			do{
				int carte = sc.nextInt();
				if(carte >= nombreCartes) {
					System.out.println("Choix invalide.");
				} else {
					System.out.println("La carte "+main.get(carte).getNom()+" a été retirée.");
					partie.getDefausse().ajoutCarte(main.remove(carte));
					carteDefaussee = true;
				}
			} while(!carteDefaussee);
		}
		sc.close();
	}
		
	public void completerMain() {
		System.out.println("Remplissage de la main jusqu'a "+TAILLE_MAIN_MAX+" cartes.");
		int nbreCartes = TAILLE_MAIN_MAX-main.size();
		for(int i = 0; i < nbreCartes; i++) {
			main.add(partie.getPioche().sortirUneCarte());
		}
	}
	
	public void finirTour() {
		
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

	public List<Carte> getMain() {
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

	public Partie getPartie() {
		return partie;
	}

	
}
