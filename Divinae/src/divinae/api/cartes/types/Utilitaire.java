package divinae.api.cartes.types;

import java.util.ArrayList;
import java.util.List;

import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public class Utilitaire {

	public static List <GuideSpirituel> choisirDiviniteEtDogme (Dogme dogme1, Dogme dogme2, Partie partie) {
		List <GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>(); // tableau des guides que l'on peut cibler par cette capacitée
		
		for (int choixDivinite = 0; choixDivinite < partie.getJoueurs().size(); choixDivinite++) { // on parcours le tableau des joueurs 
			Dogme[] dogmeDivinite = partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(); // tableau des dogmes de la divinitée ciblée
			if (comparerDogme(dogmeDivinite, dogme1, partie) && comparerDogme(dogmeDivinite, dogme2, partie)) { // les dogmes correspondent on ajoute tout les guides à la liste
				gspCiblable.addAll(partie.getJoueurs().get(choixDivinite).getGuides());
				
				if (gspCiblable.get(choixDivinite).isProtectionCiblage()) { // le guide est protégé on l'enlève de la liste
					gspCiblable.remove(choixDivinite);
					choixDivinite--;
				}
			}
		}
		
		return gspCiblable;
	}
	
	public static List<GuideSpirituel> choisirGuideLieADiviniteOrigine (Origine origine, Partie partie) {
		List <GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>(); // tableau des guides que l'on peut cibler par cette capacitée
		
		for (int choixDivinite = 0; choixDivinite < partie.getJoueurs().size(); choixDivinite++) { // on parcours le tableau des joueurs 
						
			if (partie.getJoueurs().get(choixDivinite).getDivinite().getOrigine()== (origine)) { // les origines correspondent on ajoute tout les guides à la liste
				gspCiblable.addAll(partie.getJoueurs().get(choixDivinite).getGuides());
				
				if (gspCiblable.get(choixDivinite).isProtectionCiblage()) { // le guide est protégé on l'enlève de la liste
					gspCiblable.remove(choixDivinite);
					choixDivinite--;
				}
			}
		}
		
		return gspCiblable;
	}
	
	public static boolean comparerDogme (Dogme[] dogme1, Dogme dogme2, Partie partie) {
		boolean egal = false; 
		
		for (int i =0; i < dogme1.length; i++) { // on parcours le tableau de dogme
			
			if (dogme1 [i] == dogme2) { // un des dogme correspond on renvoit VRAI sinon FAUX
				egal = true;
			} else { egal = false; }
		}
		
		return egal;
	}

	public static List<Croyant> trierCroyantDogme (Origine origine1, Origine origine2, Dogme dogme, Partie partie){
		List <Croyant> croyantCiblable = new ArrayList <Croyant> (); // tableau des croyants que l'on peut cibler par cette capacitée
		
		for (int i = 0; i < partie.getTasDeCroyants().size(); i++) { // on parcours le tas de croyants
			Origine origineCroyantTas = partie.getTasDeCroyants(i).getOrigine(); 
			Dogme [] dogmeCroyant = partie.getTasDeCroyants(i).getDogme();
			
			if ((origineCroyantTas==(origine1) || (origineCroyantTas==(origine2))) && comparerDogme(dogmeCroyant, dogme, partie)) { // le dogme et au moins une des deux origines correspondent le croyant est ajouté à la liste
				croyantCiblable.add(partie.getTasDeCroyants(i));
			}
		}

		for (int indiceJoueur = 0; indiceJoueur < partie.getJoueurs().size(); indiceJoueur++){
			
			for (int indiceGuide = 0; indiceGuide < partie.getJoueurs().get(indiceJoueur).getGuides().size(); indiceGuide++) {
				
				for (int indiceCroyant = 0; indiceCroyant < partie.getJoueurs().get(indiceJoueur).getGuide(indiceGuide).getCroyantLie().size(); indiceCroyant++){ // on parcours les croyant lies de tout les joueurs
					Origine origineCroyantLie = partie.getJoueurs().get(indiceJoueur).getGuide(indiceGuide).getCroyantLie(indiceCroyant).getOrigine();
					Dogme [] dogmeCroyantLie = partie.getJoueurs().get(indiceJoueur).getGuide(indiceGuide).getCroyantLie(indiceCroyant).getDogme();
					
					if ((origineCroyantLie==(origine1) || (origineCroyantLie==(origine2))&& (comparerDogme(dogmeCroyantLie, dogme, partie)))) { // le dogme et au moins une des deux origines correspondent le croyant est ajouté à la liste
						croyantCiblable.add(partie.getJoueurs().get(indiceJoueur).getGuide(indiceGuide).getCroyantLie(indiceCroyant));
						
						if (croyantCiblable.get(croyantCiblable.size()-1).isProtectionCiblage()) {
							croyantCiblable.remove(croyantCiblable.size()-1);
							indiceCroyant--;
						}
					}

				}
			}
		}

		return croyantCiblable;
	}

	public static void resetAutorisations (Partie partie) {
		
		for (int i=0; i < partie.getTable().size(); i++) {
			
			if (partie.getTable(i).isProtectionCiblage()) {
				partie.getTable(i).setProtectionCiblage(false);
				partie.getTable(i).setAutorisationSacrifice(true);
				Capacite.setAutorisationApocalypse(true);
				Capacite.setAutorisationPointAction(true);
			}
		}
	}
	
	public static void majPointAction (Croyant  carte, int nbCroyantAjoute) {
		int nbCroyantAvant = carte.getJoueurLie().getNombreCroyant();
				
		carte.getJoueurLie().setNombreCroyant(nbCroyantAvant+nbCroyantAjoute);
	}
	
	public static void retirerTousCroyantLies (Carte carteJouee, Partie partie) {
		GuideSpirituel gsp = carteJouee.getJoueurLie().choisirGsp();
		partie.getTasDeCroyants().addAll(gsp.getCroyantLie());
		partie.getDefausse().ajoutCarte(gsp);
	}
	
	public static List<Joueur> extraireListeJoueurRestrainte (Partie partie, Joueur joueur) {
		List<Joueur> liste = partie.getJoueurs();
		liste.remove(liste.indexOf(joueur));

		return liste;
	}
	public static List<GuideSpirituel> getGspCiblables(Joueur joueur, Partie partie) {
		List<GuideSpirituel> gspCiblables = new ArrayList<GuideSpirituel>();
		int indexGsp = 0;
		int indexJoueur = 0;
		
		while (indexJoueur < partie.getJoueurs().size()) {
			
			if (!(partie.getJoueurs().get(indexJoueur) == joueur)) {
				while (indexGsp < partie.getJoueurs().get(indexJoueur).getGuides().size()) {
					gspCiblables.add(partie.getJoueurs().get(indexJoueur).getGuide(indexGsp));
					indexGsp++;
				} 
			}
			indexGsp = 0;
			indexJoueur++;
		}
	

		for (int i = 0; i < gspCiblables.size(); i++) {
			if (gspCiblables.get(i).isProtectionCiblage()) {
				gspCiblables.remove(i);
			}
		}
		return gspCiblables;
	}
	
	public static List<Divinite> getDiviniteOuDogme(Dogme dogme1, Dogme dogme2, Partie partie) {
		int choixDivinite = 0;
		List<Divinite> diviniteCiblable = new ArrayList<Divinite>();

		if (!(dogme1 == null) || !(dogme2 == null)) {
			while (choixDivinite < partie.getJoueurs().size()) {
				if (Utilitaire.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(), dogme1,
						partie)
						|| Utilitaire.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(),
								dogme2, partie)) {
					diviniteCiblable.add(partie.getJoueurs().get(choixDivinite).getDivinite());
				}
				choixDivinite++;
			} 
		} else {
			while (choixDivinite < partie.getJoueurs().size()) {
				diviniteCiblable.add(partie.getJoueurs().get(choixDivinite).getDivinite());
				choixDivinite++;
			}
		}
		return diviniteCiblable;
	}
	
	public static List<GuideSpirituel> getSonGsp(Joueur joueur, Partie partie) {
		List<GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>();
		int indexJoueur = partie.getJoueurs().indexOf(joueur);

		for (int i = 0; i < partie.getJoueurs().get(indexJoueur).getGuides().size(); i++) {
			gspCiblable.add(partie.getJoueurs().get(indexJoueur).getGuide(i));
		}

		for (int i = 0; i < gspCiblable.size(); i++) {
			if (gspCiblable.get(i).isProtectionCiblage()) {
				gspCiblable.remove(i);
			}
		}
		return gspCiblable;
	}
	
	public static List<Croyant> getCroyant(Joueur joueur, Partie partie) {
		List<Croyant> croyantCiblable = new ArrayList<Croyant>();
		for (int i = 0; i < joueur.getGuides().size(); i++) {
			for (int j = 0; j < joueur.getGuide(i).getCroyantLie().size(); j++) {
				croyantCiblable.add(joueur.getGuide(i).getCroyantLie(j));
			}
		}
		return croyantCiblable;
	}
	
	public static List<GuideSpirituel> getDiviniteOuGspNonDogme(Dogme dogme, Partie partie) {
		int choixDivinite = 0;
		List<GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>();

		while (choixDivinite < partie.getJoueurs().size()) {
			Dogme[] dogmeDivinite = partie.getJoueurs().get(choixDivinite).getDivinite().getDogme();
			
			if (!(Utilitaire.comparerDogme(dogmeDivinite, dogme,partie))) {
				gspCiblable.addAll(partie.getJoueurs().get(choixDivinite).getGuides());
			} else {
			
				for (int choixGuide = 0; choixGuide < partie.getJoueurs().get(choixDivinite).getGuides().size(); choixGuide++) {
					GuideSpirituel Gsp = partie.getJoueurs().get(choixDivinite).getGuide(choixGuide);
					
					if (!(Utilitaire.comparerDogme(dogmeDivinite, dogme,partie))) {
						gspCiblable.add(Gsp);
						if (gspCiblable.get(gspCiblable.indexOf(Gsp)).isProtectionCiblage()) {
							gspCiblable.remove(gspCiblable.indexOf(Gsp));
						}
						
					}
				}
			}
			choixDivinite++;
		}
		return gspCiblable;
	}
}
