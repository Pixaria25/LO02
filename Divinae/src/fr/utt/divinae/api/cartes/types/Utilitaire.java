package fr.utt.divinae.api.cartes.types;

import java.util.ArrayList;
import java.util.List;

import fr.utt.divinae.api.joueur.Joueur;
import fr.utt.divinae.api.partie.Partie;

/**
 * La classe Utilitaire contient toutes les méthodes juguées comme des "utilitaires", qui ne sont ni des effets ni du ciblage.
 * @author Thomas, Abraham
 *
 */
public class Utilitaire {

	/**
	 * Renvoit une liste de guides ciblables choisit sur le critère suivant : correspondance des deux dogmes avec les dogmes de les divinités correspondantes !
	 * @param dogme1 Premier dogme qui doit correspondre aux guides.
	 * @param dogme2 Deuxième dogme qui doit correspondre aux guides.
	 * @param partie La partie qui se joue actuellement.
	 * @return gspCiblable La liste de guide ciblable.
	 */
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
	
	/**
	 * Renvoit une liste de guides ciblables choisit sur le critère suivant : correspondance de l'origine ciblée avec l'origine de la divinité correspondante !
	 * @param origine L'origine ciblée.
	 * @param partie La partie qui se joue actuellement.
	 * @return gspCiblable La liste de guide ciblable.
	 */
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
	
	/**
	 * Compare les dogmes d'une carte au dogme ciblée.
	 * @param dogme1 Le tableau des dogmes de la carte ciblée.
	 * @param dogme2 Le dogme ciblé.
	 * @param partie La partie jouée actuellement.
	 * @return true Si au moins un des dogmes corresponds.
	 */
	public static boolean comparerDogme (Dogme[] dogme1, Dogme dogme2, Partie partie) {
		boolean egal = false; 
		
		for (int i =0; i < dogme1.length; i++) { // on parcours le tableau de dogme
			
			if (dogme1 [i] == dogme2) { // un des dogme correspond on renvoit VRAI sinon FAUX
				egal = true;
			} else { egal = false; }
		}
		
		return egal;
	}

	/**
	 * Renvoit une liste de croyant choisit sur le critère suivant : correspondance avec la première origine ou la deuxième ET le dogme.
	 * @param origine1 La première origine qui peut correspondre (condition suffisante).
	 * @param origine2 La deuxième origine qui peut correspondre (condition non suffisante à associer avec le dogme).
	 * @param dogme	Le dogme qui peut correspondre (condition non suffisante à associer avec la deuxième origine).
	 * @param partie La partie jouée actuellement.
	 * @return croyantCiblable La liste de croyant ciblable.
	 */
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

	/**
	 * Remets tous les boolean de type autorisation.
	 * @param partie La partie jouée actuellement
	 */
	public static void resetAutorisations (Partie partie) {
		
		for (int i=0; i < partie.getJoueurs().size(); i++) {
				
				for (int j = 0; j < partie.getJoueurs().get(i).getGuides().size(); j++) {
					
					for (int k = 0; k < partie.getJoueurs().get(i).getGuide(j).getCroyantLie().size(); k++) {
						
						partie.getJoueurs().get(i).getGuide(j).getCroyantLie().get(k).setProtectionCiblage(false);
						partie.getJoueurs().get(i).getGuide(j).getCroyantLie().get(k).setAutorisationSacrifice(true);
						Capacite.setAutorisationApocalypse(true);
						Capacite.setAutorisationPointAction(true);
					}
					partie.getJoueurs().get(i).getGuide(j).setProtectionCiblage(false);
					partie.getJoueurs().get(i).getGuide(j).setAutorisationSacrifice(true);
				}
			
		}
	}

	/**
	 * Mets à jour le nombre de croyant des joueurs (sacrifice et conversion des croyants).
	 * @param carte	La carte croyant sacrifiée ou lié au guide.
	 * @param nbCroyantAjoute Le nombre de croyants que rapporte cette carte.
	 */
	public static void majPointsCroyant (Croyant  carte, int nbCroyantAjoute) {
		int nbCroyantAvant = carte.getJoueurLie().getNombreCroyant();
				
		carte.getJoueurLie().setNombreCroyant(nbCroyantAvant+nbCroyantAjoute);
	}

	/**
	 * Retire tous les croyants liés à un guide.
	 * @param carteJouee Le guide qui est ciblé.
	 * @param partie La partie jouée actuellement.
	 */
	public static void retirerTousCroyantLies (Carte carteJouee, Partie partie) {
		GuideSpirituel gsp = carteJouee.getJoueurLie().choisirGsp();
		partie.getTasDeCroyants().addAll(gsp.getCroyantLie());
		partie.getDefausse().ajoutCarte(gsp);
	}
	
	/**
	 * Renvoit la liste des joueurs de la partie excepté le joueur placé en paramète.
	 * @param partie La partie jouée actuellement.
	 * @param joueur Le joueur exclut de la liste.
	 * @return liste La liste restrainte des joueurs.
	 */
	public static List<Joueur> extraireListeJoueurRestrainte (Partie partie, Joueur joueur) {
		List<Joueur> liste = partie.getJoueurs();
		liste.remove(liste.indexOf(joueur));

		return liste;
	}
	
	/**
	 * Renvoit la liste de tous les guides en jeu et ciblable excepté ceux du joueur placé en paramètre.
	 * @param joueur Le joueur exclut du ciblage.
	 * @param partie La partie jouée actuellement.
	 * @return gspCiblable La liste de guide ciblable.
	 */
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

	/**
	 * Renvoit une liste de Divinités sur ce critère : correspondance d'au moins l'un des deux dogmes.
	 * @param dogme1 Le premier dogme ciblé.
	 * @param dogme2 Le deuxième dogme ciblé.
	 * @param partie La partie jouée actuellement
	 * @return diviniteCiblable La liste des divinité jouable.
	 */
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
	
	/**
	 * Renvoit la liste des guides spirituels ciblable parmet ceux du joueur placé en paramètre.
	 * @param joueur Le joueur  ciblé.
	 * @param partie La partie jouée actuellement.
	 * @return La liste de guide ciblable.
	 */
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
	
	/**
	 * Renvoit la liste de croyants lié à un joueur
	 * @param joueur Le joueur ciblé.
	 * @param partie La partie jouée actuellement.
	 * @return croyantCiblable La liste de croyant ciblable.
	 */
	public static List<Croyant> getCroyant(Joueur joueur, Partie partie) {
		List<Croyant> croyantCiblable = new ArrayList<Croyant>();
		for (int i = 0; i < joueur.getGuides().size(); i++) {
			for (int j = 0; j < joueur.getGuide(i).getCroyantLie().size(); j++) {
				croyantCiblable.add(joueur.getGuide(i).getCroyantLie(j));
			}
		}
		return croyantCiblable;
	}
	
	/**
	 * Renvoit une liste de guide sur le critère suivant : correspondance du dogme visé à la divinité ou au guide en question.
	 * @param dogme Le dogme visé.
	 * @param partie La partie jouée actuellement.
	 * @return
	 */
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
	
	/**
	 * Renvoit une liste de guide sur le critère suivant : correspondance de l'origine visé avec le guide.
	 * @param origine l'origine visé.
	 * @param carte	La carte action jouée.
	 * @param partie La partie jouée actuellement.
	 * @return gspCiblable La liste de guide ciblable.
	 */
	public static List<GuideSpirituel> getGspOrigine(Origine origine, CarteAction carte, Partie partie) {
		List<GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>();
		gspCiblable.addAll(getGspCiblables(carte.getJoueurLie(), partie));
		
		for (int i = 0; i < gspCiblable.size(); i++) {
			if(gspCiblable.get(i).getJoueurLie() == carte.getJoueurLie() || carte.getOrigine() != origine) {
				gspCiblable.remove(i);
				i--;
			}
		}
		
		return gspCiblable;
	}
	
	/**
	 * Redonne les points d'actions (en cas de reprise d'une carte).
	 * @param carteJouee La carte qui est reprise.
	 */
	public static void rendrePointActionEtCarte(CarteAction carteJouee) {
		if(!(carteJouee.getOrigine() == Origine.Aucune)) {
			Capacite.donnerPointAction(1, carteJouee.getOrigine(), carteJouee.getJoueurLie());
			carteJouee.getJoueurLie().getMain().add(carteJouee);
		}
	}
	
}
