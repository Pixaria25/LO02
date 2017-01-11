package divinae.api.cartes.types;

import java.util.ArrayList;
import java.util.List;
import divinae.api.cartes.deuxex.Transe.ClasseName;
import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public class Capacite {

	private static boolean autorisationPointAction = true;
	private static boolean autorisationApocalypse = true;
	private static ActionSuivante actionSuivante = null;

	static CarteAction carteInterupt;


	public static void setActionSuivante (ActionSuivante action)
	{
		actionSuivante = action;
	}



	public static List <GuideSpirituel> choisirDiviniteEtDogme (Dogme dogme1, Dogme dogme2, Partie partie) {
		List <GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>(); // tableau des guides que l'on peut cibler par cette capacitée
		
		for (int choixDivinite = 0; choixDivinite < partie.getJoueurs().size(); choixDivinite++) { // on parcours le tableau des joueurs 
			Dogme[] dogmeDivinite = partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(); // tableau des dogmes de la divinitée ciblée
			if (Capacite.comparerDogme(dogmeDivinite, dogme1, partie) && Capacite.comparerDogme(dogmeDivinite, dogme2, partie)) { // les dogmes correspondent on ajoute tout les guides à la liste
				gspCiblable.addAll(partie.getJoueurs().get(choixDivinite).getGuides());
				
				if (gspCiblable.get(choixDivinite).isProtectionCiblage()) { // le guide est protégé on l'enlève de la liste
					gspCiblable.remove(choixDivinite);
					choixDivinite--;
				}
			}
		}
		
		return gspCiblable;
	}

	public static List<GuideSpirituel> choisirDiviniteOrigine (Origine origine, Partie partie) {
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
			
			if ((origineCroyantTas==(origine1) || (origineCroyantTas==(origine2))) && Capacite.comparerDogme(dogmeCroyant, dogme, partie)) { // le dogme et au moins une des deux origines correspondent le croyant est ajouté à la liste
				croyantCiblable.add(partie.getTasDeCroyants(i));
			}
		}

		for (int indiceJoueur = 0; indiceJoueur < partie.getJoueurs().size(); indiceJoueur++){
			
			for (int indiceGuide = 0; indiceGuide < partie.getJoueurs().get(indiceJoueur).getGuides().size(); indiceGuide++) {
				
				for (int indiceCroyant = 0; indiceCroyant < partie.getJoueurs().get(indiceJoueur).getGuide(indiceGuide).getCroyantLie().size(); indiceCroyant++){ // on parcours les croyant lies de tout les joueurs
					Origine origineCroyantLie = partie.getJoueurs().get(indiceJoueur).getGuide(indiceGuide).getCroyantLie(indiceCroyant).getOrigine();
					Dogme [] dogmeCroyantLie = partie.getJoueurs().get(indiceJoueur).getGuide(indiceGuide).getCroyantLie(indiceCroyant).getDogme();
					
					if ((origineCroyantLie==(origine1) || (origineCroyantLie==(origine2))&& (Capacite.comparerDogme(dogmeCroyantLie, dogme, partie)))) { // le dogme et au moins une des deux origines correspondent le croyant est ajouté à la liste
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

	public static void donnerPointAction (int point, Origine origine, Joueur joueur) {
		
		if (Capacite.isAutorisationPointAction()) { // l'autorisation de modifier les points d'action est vérifié, on ajoute les points sinon c'est bloqué
			joueur.ajoutPointsAction(point, origine);
		} else {
			joueur.messageListe("On ne peut pas gagner de point d'action jusqu'à la fin du tour (competence nihilistes)");
		}
	}
	
	public static void relancerDe (Partie partie) {
		partie.getDe().lancerDe();
		partie.getDe().getValeur();
		
		int indexCarteJouee = partie.getTable().size()-1;
		Joueur joueurCourant = partie.getTable(indexCarteJouee).getJoueurLie(); 
		joueurCourant.messageListe("La nouvelle influence est " + partie.getDe().getInfluence());
	}

	public static void lancerApocalypse (Partie partie) {
		
		if (Capacite.isAutorisationApocalypse()== true) { // l'autorisation de lancer une Apocalypse est valide on lance l'apocalypse sinon
			
			Capacite.resetAutorisations (partie);
			Capacite.setAutorisationApocalypse (false);
			partie.finirUnePartie();
			
		} else {
			int indexCarteJouee = partie.getTable().size()-1;
			Joueur joueurCourant = partie.getTable(indexCarteJouee).getJoueurLie();
			joueurCourant.messageListe("Impossible de lancer une Apocalyspe ce tour-ci veuillez attendre le tour prochain");
		}
	}
	
	// A faire en fin de tour 
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
	
	public static void recupererUnGsp (Carte carte) {
		int indexCarteJouee = carte.getJoueurLie().getPartie().getTable().size()-1;
		Joueur joueurCourant = carte.getJoueurLie().getPartie().getTable(indexCarteJouee).getJoueurLie();
		GuideSpirituel GpCible = carte.getJoueurLie().choisirGsp();
		
		while (GpCible.getJoueurLie() == carte.getJoueurLie()) {
			joueurCourant.messageListe("Ce guide spirituel vous appartient déjà, choisissez en un autre !");
			GpCible = carte.getJoueurLie().choisirGsp();
		}
		GpCible.setJoueurLie(carte.getJoueurLie());
		actionSuivante.messageRecap(GpCible.getJoueurLie().getNom() + " récupère le guide spirituel suivant : " + GpCible.getNom());
	}

	public static boolean retirerPointAction (Carte  carte, Origine origine) {
		int nbCroyantAvant = carte.getJoueurLie().getNombreCroyant();
		int nbCroyantDeduit = (((Croyant) carte).getValeurCroyant());
		
		switch (carte.getOrigine()){
		case Jour :
			if (carte.getJoueurLie().getPointsAction()[Origine.Jour.ordinal()] >= 1) {
				carte.getJoueurLie().getPointsAction()[Origine.Jour.ordinal()]--;
				carte.getJoueurLie().setNombreCroyant(nbCroyantAvant-nbCroyantDeduit);
				return true;
			} else {
				carte.getJoueurLie().messageListe("Pas de point d'origine jour.");
				return false;
			}
		case Nuit :
			if (carte.getJoueurLie().getPointsAction()[Origine.Nuit.ordinal()] >= 1) {
				carte.getJoueurLie().getPointsAction()[Origine.Nuit.ordinal()]--;
				carte.getJoueurLie().setNombreCroyant(nbCroyantAvant-nbCroyantDeduit);
				return true;
			} else {
				carte.getJoueurLie().messageListe("Pas de point d'origine Nuit.");
				return false;
			}
		case Neant :
			if (carte.getJoueurLie().getPointsAction()[Origine.Neant.ordinal()] >= 1) {
				carte.getJoueurLie().getPointsAction()[Origine.Neant.ordinal()]--;
				carte.getJoueurLie().setNombreCroyant(nbCroyantAvant-nbCroyantDeduit);
				return true;
			} else {
				carte.getJoueurLie().messageListe("Pas de point d'origine Neant.");
				return false;
			}
			default : 
				return true;
			}
	}
	
	public static void recupererEffetBenef (Carte carte, Joueur joueur, Partie partie) {
		ClasseName z = ClasseName.valueOf(carte.getClass().getSimpleName());
   
	    switch (z) {
	    case Croyant:
		    int croyantLiable =0;
		    for (int i = 0; i < joueur.getGuides().size(); i++) {
				croyantLiable =+ joueur.getGuide(i).getNombreCroyantLiable(); 
			}
	    	if (joueur.getNombreCroyant() < croyantLiable) {
	    		for (int i = 0; i < joueur.getGuides().size(); i++) {
	    			if (joueur.getGuide(i).getCroyantLie().size() < joueur.getGuide(i).getNombreCroyantLiable()) {
	    				((Croyant) carte).setGuideLie(joueur.getGuide(i));
	    			} 
	    		}
	    	} else { carte.getJoueurLie().messageListe("Pas de place disponible pour lier ce croyant"); }
	    break;
	    
	    case GuideSpirituel:
	        carte.setJoueurLie(joueur);
	        ((GuideSpirituel) carte).convertirCroyant(partie);
	    break;
	    
	    case DeusEx:
	        switch (carte.getNom()){
	        case "Stase" : 
	        case "Ordre Celeste" :
	        case "Diversion" :
	        case "Concentration" :
	        case "Trou Noir" :
	        case "Phoenix" : Capacite.copierCapacite(null, carte, partie);
	     	break;
	    
	        default : carte.getJoueurLie().messageListe("Cette carte n'a aucun effet bénéfique dire pour vous.");
	       	break;
	        }
	    break;
	    
	    default : carte.getJoueurLie().messageListe("Aucun effet bénéfique pour vous.");
	    break;
	    }
	    
	}

	public static void defausser (CarteAction carte, Partie partie) {
		partie.getDefausse().ajoutCarte(carte);
	}

	public static void renvoyerGsp (List<GuideSpirituel> gspCiblable, Carte cartePosee, Partie partie) {
		GuideSpirituel gsp = cartePosee.getJoueurLie().choisirGspRenvoye(gspCiblable);
		partie.getTasDeCroyants().addAll(gsp.getCroyantLie ());
		gsp.getJoueurLie().tuerCarte(gsp);
	}

	public static void empecherSacrifice ( Dogme dogme1, Dogme dogme2, String vise, Carte cartePosee, Partie partie) {
		Divinite divinite = cartePosee.getJoueurLie().choisirDiviniteOuDogme (dogme1, dogme2);
		CarteAction carteCible = null;
		
		if (vise == "Croyant") {
			carteCible = divinite.getJoueurLie().choisirCroyant(divinite.getJoueurLie());
		} else if (vise == "GuideSpirituel") {
			carteCible = divinite.getJoueurLie().choisirSonGsp();
		}
		
		carteCible.setAutorisationSacrifice(false);
	}

	public static void imposerSacrifice (String vise, Joueur joueurVise, Partie partie) {
		switch (vise) {
			case "GuideSpirituel" :
				GuideSpirituel guideSpirituel =  joueurVise.choisirSonGsp();
				joueurVise.activerCapaciteCarte(guideSpirituel);
				partie.getTasDeCroyants().addAll(guideSpirituel.getCroyantLie());
				partie.getDefausse().ajoutCarte(guideSpirituel);
			break;

			case "Croyant" :
				Croyant croyant =  joueurVise.choisirCroyant(joueurVise);
				joueurVise.activerCapaciteCarte(croyant);
				GuideSpirituel GpLie = croyant.getGuideLie();
				partie.getDefausse().ajoutCarte(croyant);
				if (GpLie.getCroyantLie().isEmpty()) {
					partie.getDefausse().ajoutCarte(GpLie);
				}
			break;
		}

	  }

	public static void imposerSacrificeGuideSpirituel (Divinite divinite, Partie partie) {
		Joueur joueur = divinite.getJoueurLie();

		GuideSpirituel guideSpirituel = joueur.choisirSonGsp();
		joueur.activerCapaciteCarte(guideSpirituel);
		partie.getTasDeCroyants().addAll(guideSpirituel.getCroyantLie());
		partie.getDefausse().ajoutCarte(guideSpirituel);
	}

	public static void retirerTousCroyantLies (Carte carteJouee, Partie partie) {
		GuideSpirituel gsp = carteJouee.getJoueurLie().choisirGsp();
		partie.getTasDeCroyants().addAll(gsp.getCroyantLie());
		partie.getDefausse().ajoutCarte(gsp);
	}

	public static void copierCapacite (Joueur joueurCourant, Carte carteCible, Partie partie) {
		Joueur joueurOriginal = carteCible.getJoueurLie();
		carteCible.setJoueurLie(joueurCourant);
		carteCible.activerCapacite();
		carteCible.setJoueurLie(joueurOriginal);
	}

	public static void recupererPointAction (Joueur joueurCourant, Partie partie){
		int indexJoueur1 = partie.getIndexJoueur1();
		int indexJoueurCourant = partie.getJoueurs().indexOf(joueurCourant);
		
		if (indexJoueur1 < indexJoueurCourant){
			for (int i = indexJoueurCourant+1; i < partie.getJoueurs().size();i++){
				joueurCourant.ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
				joueurCourant.ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit );
				joueurCourant.ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);

				partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
				partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit);
				partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);
			}
			for (int i = indexJoueur1-1; i > -1 ;i--){
				joueurCourant.ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
				joueurCourant.ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit );
				joueurCourant.ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);

				partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
				partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit);
				partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);
			}
		} else if (indexJoueur1 > indexJoueurCourant){
				for (int i = indexJoueurCourant+1; i < indexJoueur1;i++){
					joueurCourant.ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
					joueurCourant.ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit );
					joueurCourant.ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);

					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit);
					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);
				}
			} else {
				for (int i = indexJoueurCourant+1; i < partie.getJoueurs().size();i++){
					joueurCourant.ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
					joueurCourant.ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit );
					joueurCourant.ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);

					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit);
					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);
				}
				for (int i = indexJoueur1-1; i > -1 ;i--){
					joueurCourant.ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
					joueurCourant.ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit );
					joueurCourant.ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);

					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit);
					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);
				}
			}

	}

	public static void prendreCartes (Carte carte, int nbCarte, Partie partie) {
		int nbCartesPrises = 0;
		int choixHasard = 0 ;
		Joueur joueur = carte.getJoueurLie().choisirJoueurCible();
		while (nbCartesPrises < nbCarte | !joueur.getMain().isEmpty()) {
			choixHasard = (int)Math.random()*(joueur.getMain().size()-1);
			carte.getJoueurLie().getMain().add(joueur.getMain().get(choixHasard));
			nbCartesPrises++;
		}
	}

	public static void bloquerPointAction (Partie partie) {

		setAutorisationPointAction(true);
	}

	public static void annulerEffetCarte (CarteAction carteCible, Origine [] origineCible, Partie partie) {
		int max = origineCible.length;
		
		for (int i=0; i < max; i++ ){
			
			if (carteCible.getOrigine() == origineCible[i]) {
				carteCible.setCapaciteBloqué(true);
				break;
			}
		}
	}


	
	
	
	public static void setAutorisationPointAction(boolean autorisationPointAction) {
		Capacite.autorisationPointAction = autorisationPointAction;
	}

	public static boolean isAutorisationApocalypse() {
		return autorisationApocalypse;
	}

	public static void setAutorisationApocalypse(boolean autorisationApocalypse) {
		Capacite.autorisationApocalypse = autorisationApocalypse;
	}

	public static boolean isAutorisationPointAction() {
		return autorisationPointAction;
	}

	public static ActionSuivante getActionSuivante() {
		return actionSuivante;
	}


	public static CarteAction getCarteInterupt() {
		return carteInterupt;
	}

	public static void setCarteInterupt(CarteAction carteInterupt) {
		Capacite.carteInterupt = carteInterupt;
	}
	
	














}
