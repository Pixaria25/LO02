package divinae.api.joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.partie.Partie;

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
	private Scanner sc = new Scanner(System.in);
	
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
		
		afficherMain();
		System.out.println("Entrez le numero de la carte que vous voulez jouer.");
		int choixCarte = -1;
		do {
			choixCarte = sc.nextInt();
		} while(choixCarte < 0 || choixCarte >= main.size());
		partie.getTable().add(main.remove(choixCarte));
	}
	 
	public void activerCapaciteCarte(Carte carte) {
		carte.activerCapacite();
	}
	
	public void afficherMain() {
		for(int i = 0; i < main.size(); i++) {
			System.out.println(i+" - "+main.get(i).getNom());
		}
	}
	
	public void defausser(int nombreCartes) {
		System.out.println("Quelles cartes voulez-vous défausser ?");		
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
	}
		
	public void completerMain() {
		System.out.println("Remplissage de la main jusqu'a "+TAILLE_MAIN_MAX+" cartes.");
		int nbreCartes = TAILLE_MAIN_MAX-main.size();
		for(int i = 0; i < nbreCartes; i++) {
			main.add(partie.getPioche().sortirUneCarte());
		}
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

	public boolean isAutorisationgsp() {
		return autorisationgsp;
	}

	public boolean isAutorisationcr() {
		return autorisationcr;
	}

	public Partie getPartie() {
		return partie;
	}

	public void sacrificeNormal(Carte carte) {
		if ((autorisationgsp == false && carte instanceof GuideSpirituel) | (autorisationcr == false && carte instanceof Croyant)) {
			System.out.println("Vous ne pouvez pas sacrifier de carte ce tour ci ! (utilisation d'une capacite contre vous)");
		} else {
			sacrificeNormal(carte);
		}
	}
	
	public void sacrifierCarte(Carte carte) {
		carte.activerCapacite();
		tuerCarte(carte);
	}

	public void tuerCarte(Carte carte) {
		if(carte instanceof GuideSpirituel) {
			this.partie.getTasDeCroyants().addAll((((GuideSpirituel) carte).getCroyantLie()));
			((GuideSpirituel) carte).getCroyantLie().clear();
		}
		if(carte instanceof Croyant) {
			((Croyant) carte).setGuideLie(null);
		}
		carte.setJoueurLie(null);
		partie.getDefausse().ajoutCarte(carte);
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
