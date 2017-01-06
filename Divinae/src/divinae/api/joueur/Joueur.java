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
	
	public void poserCarteAction(int choixCarte) {
		partie.getTable().add(main.remove(choixCarte));
	}
	 
	public void activerCapaciteCarte(Carte carte) {
		carte.activerCapacite();
	}
	
	public String afficherMain() {
		String retour = "";
		for(int i = 0; i < main.size(); i++) {
			retour += i+" - "+main.get(i).getNom();
		}
		return retour;
	}
	
	public void defausser(int nombreCartes) {
		for(int i = 0; i < nombreCartes; i++) {
			boolean carteDefaussee = false;
			do{
				System.out.println(afficherMain());
				int carte = sc.nextInt();
				if(carte >= main.size()) {
					System.out.println("Choix invalide.");
				} else {
					System.out.println("La carte "+main.get(carte).getNom()+" a ete retiree.");
					partie.getDefausse().ajoutCarte(main.remove(carte));
					carteDefaussee = true;
				}
			} while(!carteDefaussee);
		}
	}
		
	public void completerMain() {
		
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
