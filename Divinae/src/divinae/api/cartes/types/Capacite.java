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
	private static boolean annulationEffetCapa = false;
	static boolean interuptionAnnulationCapa = false;
	static Carte carteInterupt;


	public static void setActionSuivante (ActionSuivante action)
	{
		actionSuivante = action;
	}



	public static List <GuideSpirituel> choisirDiviniteEtDogme (Dogme dogme1, Dogme dogme2, Partie partie) {
		int choixDivinite = 0;
		List <GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>();
		while (choixDivinite < partie.getJoueurs().size()) {
			if (Capacite.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(), dogme1, partie) && Capacite.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(), dogme2, partie)) {
				gspCiblable.addAll(partie.getJoueurs().get(choixDivinite).getGuides());
			}
			choixDivinite++;
		}
		for (int i = 0; i < gspCiblable.size(); i++) {
			if (gspCiblable.get(i).isProtectionCiblage()) {
				gspCiblable.remove(i);
			}
		}
		return gspCiblable;
	}

	public static List<GuideSpirituel> choisirDiviniteOrigine (Origine origine, Partie partie) {
		int choixDivinite = 0;
		List<GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>();
		while (choixDivinite < partie.getJoueurs().size()) {
			if (partie.getJoueurs().get(choixDivinite).getDivinite().getOrigine()== (origine)) {
				gspCiblable.addAll(partie.getJoueurs().get(choixDivinite).getGuides());

				}
			choixDivinite++;
		}
		for (int i = 0; i < gspCiblable.size(); i++) {
			if (gspCiblable.get(i).isProtectionCiblage()) {
				gspCiblable.remove(i);
			}
		}
		return gspCiblable;
	}

	public static boolean comparerDogme (Dogme[] dogme1, Dogme dogme2, Partie partie) {
		boolean egal = false;
		for (int i =0; i < dogme1.length; i++) {
			if (dogme1 [i] == dogme2) {
				egal = true;
			} else { egal = false; }
		}
		return egal;

	}

	public static List<Croyant> trierCroyantDogme (Origine origine1, Origine origine2, Dogme dogme, Partie partie){
		List <Croyant> croyantCiblable = new ArrayList <Croyant> ();
		for (int i = 0; i < partie.getTasDeCroyants().size(); i++) {
			if ((partie.getTasDeCroyants(i).getOrigine()==(origine1) || (partie.getTasDeCroyants(i).getOrigine()==(origine2)))&& Capacite.comparerDogme(partie.getTasDeCroyants(i).getDogme(), dogme, partie)) {
				croyantCiblable.add(partie.getTasDeCroyants(i));
			}
		}

		for (int j = 0; j < partie.getJoueurs().size(); j++){
			for (int k = 0; k < partie.getJoueurs().get(j).getGuides().size(); k++) {
				for (int l = 0; l < partie.getJoueurs().get(j).getGuide(k).getCroyantLie().size(); l++){
					if ((partie.getJoueurs().get(j).getGuide(k).getCroyantLie(l).getOrigine()==(origine1) || (partie.getJoueurs().get(j).getGuide(k).getCroyantLie(l).getOrigine()==(origine2))&& (Capacite.comparerDogme(partie.getJoueurs().get(j).getGuide(k).getCroyantLie(l).getDogme(), dogme, partie)))) {
						croyantCiblable.add(partie.getJoueurs().get(j).getGuide(k).getCroyantLie(l));
					}

				}
			}
		}

		for (int i = 0; i < croyantCiblable.size(); i++) {
			if (croyantCiblable.get(i).isProtectionCiblage()) {
				croyantCiblable.remove(i);
			}
		}
		return croyantCiblable;

	}


	
	public static void donnerPointAction (int point, Origine origine, Joueur joueur) {
		if (Capacite.isAutorisationApocalypse()==true) {
			joueur.ajoutPointsAction(point, origine);
		} else {
			actionSuivante.commentaireMethode("On ne peut pas gagner de point d'action jusqu'à la fin du tour (competence nihilistes)");
		}
	}
	
	public static void relancerDe (Partie partie) {
		partie.getDe().lancerDe();
		partie.getDe().getValeur();
		actionSuivante.commentaireMethode("La nouvelle influence est " + partie.getDe().getInfluence());
	}

	public static void lancerApocalypse (Partie partie) {
		if (Capacite.isAutorisationApocalypse()== true) {
			partie.finirUnePartie();
			int tourFixe = partie.getNombreTour();
			int tourActuel = tourFixe;

			for (int i=0; i < partie.getTable().size(); i++) {
				if (partie.getTable(i).isProtectionCiblage()) {
					partie.getTable(i).setProtectionCiblage(false);
				}
			}

			while (tourFixe == tourActuel) {
				Capacite.setAutorisationApocalypse (false);
				tourActuel = partie.getNombreTour();
			}
			Capacite.setAutorisationApocalypse (true);
		} else {
			actionSuivante.commentaireMethode("Impossible de lancer une Apocalyspe ce tour-ci veuillez attendre le tour prochain");
		}
	}

	public static void recupererUnGsp (Carte carte) {
		GuideSpirituel GpCible = Capacite.getActionSuivante().choisirGsp(carte.getJoueurLie().getPartie());
		while (GpCible.getJoueurLie() == carte.getJoueurLie()) {
			actionSuivante.commentaireMethode("Ce guide spirituel vous appartient déjà, choisissez en un autre !");
			GpCible = Capacite.getActionSuivante().choisirGsp(carte.getJoueurLie().getPartie());
		}
		GpCible.setJoueurLie(carte.getJoueurLie());
		actionSuivante.commentaireMethode("Vous avez récupérer le guide spirituel suivant : " + GpCible.getNom());
	}

	public static boolean retirerPointAction (Carte  carte, Origine origine) {
		switch (carte.getOrigine()){
		case Jour :
			if (carte.getJoueurLie().getPointsAction()[Origine.Jour.ordinal()] >= 1) {
				carte.getJoueurLie().getPointsAction()[Origine.Jour.ordinal()]--;
				carte.getJoueurLie().setNombreCroyant(carte.getJoueurLie().getNombreCroyant()-(((Croyant) carte).getValeurCroyant()));
				return true;
			} else {
				actionSuivante.commentaireMethode("Pas de point d'origine jour.");
				return false;
			}
		case Nuit :
			if (carte.getJoueurLie().getPointsAction()[Origine.Nuit.ordinal()] >= 1) {
				carte.getJoueurLie().getPointsAction()[Origine.Nuit.ordinal()]--;
				carte.getJoueurLie().setNombreCroyant(carte.getJoueurLie().getNombreCroyant()-(((Croyant) carte).getValeurCroyant()));
				return true;
			} else {
				actionSuivante.commentaireMethode("Pas de point d'origine Nuit.");
				return false;
			}
		case Neant :
			if (carte.getJoueurLie().getPointsAction()[Origine.Neant.ordinal()] >= 1) {
				carte.getJoueurLie().getPointsAction()[Origine.Neant.ordinal()]--;
				carte.getJoueurLie().setNombreCroyant(carte.getJoueurLie().getNombreCroyant()-(((Croyant) carte).getValeurCroyant()));
				return true;
			} else {
				actionSuivante.commentaireMethode("Pas de point d'origine Neant.");
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
	    	} else { actionSuivante.commentaireMethode("Pas de place disponible pour lier ce croyant"); }
	    break;
	    
	    case GuideSpirituel:
	        carte.setJoueurLie(joueur);
	        Capacite.getActionSuivante().convertirCroyant(partie, (GuideSpirituel) carte);
	    break;
	    
	    case DeusEx:
	        switch (carte.getNom()){
	        case "Stase" : 
	        case "Ordre Celeste" :
	        case "Diversion" :
	        case "Concentration" :
	        case "Trou Noir" :
	        case "Phoenix" : Capacite.copierCapacite(carte, partie);
	     	break;
	    
	        default : actionSuivante.commentaireMethode("Cette carte n'a aucun effet bénéfique dire pour vous.");
	       	break;
	        }
	    break;
	    
	    default : actionSuivante.commentaireMethode("Aucun effet bénéfique pour vous.");
	    break;
	    }
	    
	}

	public static void defausser (CarteAction carte, Partie partie) {
		partie.getDefausse().ajoutCarte(carte);
	}

	public static void renvoyerGsp (Partie partie) {
		GuideSpirituel gsp = actionSuivante.choisirGsp(partie);
		partie.getTasDeCroyants().addAll(gsp.getCroyantLie ());
		gsp.getJoueurLie().getMain().add(gsp);
	}

	public static void empecherSacrifice ( Dogme dogme1, Dogme dogme2, String vise, Partie partie) {
		int tourFixe = partie.getNombreTour();
		int jtourActuel = tourFixe;
		Divinite divinite = actionSuivante.choisirDiviniteOuDogme (dogme1, dogme2, partie);

		while (jtourActuel==tourFixe) {
			jtourActuel = partie.getNombreTour();
			divinite.getJoueurLie().setAutorisation(vise);
		}

		divinite.getJoueurLie().setAutorisation(null);
	}

	public static void imposerSacrifice (String vise, Partie partie) {
		Joueur joueur = actionSuivante.choisirJoueurCible(partie);

		switch (vise) {
			case "GuideSpirituel" :
				GuideSpirituel guideSpirituel = actionSuivante.choisirSonGsp(joueur, partie);
				joueur.activerCapaciteCarte(guideSpirituel);
				partie.getTasDeCroyants().addAll(guideSpirituel.getCroyantLie());
				partie.getDefausse().ajoutCarte(guideSpirituel);
			break;

			case "Croyant" :
				Croyant croyant = actionSuivante.choisirCroyant(joueur, partie);
				joueur.activerCapaciteCarte(croyant);
				GuideSpirituel GpLie = croyant.getGuideLie();
				partie.getDefausse().ajoutCarte(croyant);
				if (GpLie.getCroyantLie().isEmpty()) {
					partie.getDefausse().ajoutCarte(GpLie);
				}
			break;
		}

	  }

	public static void imposerSacrifice (Divinite divinite, String vise, Partie partie) {
		Joueur joueur = divinite.getJoueurLie();
		switch (vise) {
			case "GuideSpirituel" :
						GuideSpirituel guideSpirituel = actionSuivante.choisirSonGsp(joueur, partie);
						joueur.activerCapaciteCarte(guideSpirituel);
						partie.getTasDeCroyants().addAll(guideSpirituel.getCroyantLie());
						partie.getDefausse().ajoutCarte(guideSpirituel);
				break;
		}

	  }

	public static void retirerTousCroyantLies (Partie partie) {
		GuideSpirituel gsp = actionSuivante.choisirGsp(partie);
		partie.getTasDeCroyants().addAll(gsp.getCroyantLie());
		partie.getDefausse().ajoutCarte(gsp);
	}

	public static void copierCapacite (Carte carte, Partie partie) {
		Joueur joueurOriginal = carte.getJoueurLie();
		carte.setJoueurLie(carte.getJoueurLie());
		carte.activerCapacite();
		carte.setJoueurLie(joueurOriginal);
	}

	public static void recupererPointAction (Carte carte, Partie partie){
		int indexJoueur1 = partie.getIndexJoueur1();
		int indexJoueurCourant = carte.getJoueurLie().getPartie().getJoueurs().indexOf(carte.getJoueurLie());
		if (indexJoueur1 < indexJoueurCourant){
			for (int i = indexJoueurCourant+1; i < partie.getJoueurs().size();i++){
				carte.getJoueurLie().ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
				carte.getJoueurLie().ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit );
				carte.getJoueurLie().ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);

				partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
				partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit);
				partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);
			}
			for (int i = indexJoueur1-1; i > -1 ;i--){
				carte.getJoueurLie().ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
				carte.getJoueurLie().ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit );
				carte.getJoueurLie().ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);

				partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
				partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit);
				partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);
			}
		} else if (indexJoueur1 > indexJoueurCourant){
				for (int i = indexJoueurCourant+1; i < indexJoueur1;i++){
					carte.getJoueurLie().ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
					carte.getJoueurLie().ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit );
					carte.getJoueurLie().ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);

					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit);
					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);
				}
			} else {
				for (int i = indexJoueurCourant+1; i < partie.getJoueurs().size();i++){
					carte.getJoueurLie().ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
					carte.getJoueurLie().ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit );
					carte.getJoueurLie().ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);

					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit);
					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);
				}
				for (int i = indexJoueur1-1; i > -1 ;i--){
					carte.getJoueurLie().ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
					carte.getJoueurLie().ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit );
					carte.getJoueurLie().ajoutPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);

					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Jour.ordinal()],Origine.Jour);
					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Nuit.ordinal()],Origine.Nuit);
					partie.getJoueurs().get(i).soustrPointsAction(partie.getJoueurs().get(i).getPointsAction()[Origine.Neant.ordinal()],Origine.Neant);
				}
			}

	}

	public static void prendreCartes (Carte carte, int nbCarte, Partie partie) {
		int nbCartesPrises = 0;
		int choixHasard = 0 ;
		Joueur joueur = actionSuivante.choisirJoueurCible (partie);
		while (nbCartesPrises < nbCarte | !joueur.getMain().isEmpty()) {
			choixHasard = (int)Math.random()*(joueur.getMain().size()-1);
			carte.getJoueurLie().getMain().add(joueur.getMain().get(choixHasard));
			nbCartesPrises++;
		}

	}

	public static void bloquerPointAction (Partie partie) {
		int tourFixe = partie.getNombreTour();
		int tourActuel = tourFixe;

		while (tourFixe == tourActuel) {
			setAutorisationPointAction(false);
			tourActuel = partie.getNombreTour();
		}

		setAutorisationPointAction(true);
	}

	public static void annulerEffetCarte (Origine origine, Origine [] origineCible, Partie partie) {
		int max = origineCible.length;
		for (int i=0; i < max; i++ ){
			if (origine == origineCible[i]) {
				CarteAction derniereCarte = partie.getTable().get(partie.getTable().lastIndexOf(partie.getTable()));
				partie.getDefausse().ajoutCarte(derniereCarte);
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

	public static boolean isAnnulationEffetCapa() {
		return annulationEffetCapa;
	}

	public static void setAnnulationEffetCapa(boolean annulationEffetCapa) {
		Capacite.annulationEffetCapa = annulationEffetCapa;
	}
	
	public static boolean isInteruptionAnnulationCapa() {
		return interuptionAnnulationCapa;
	}

	public static void setInteruptionAnnulationCapa(boolean AnnulationCapa) {
		interuptionAnnulationCapa = AnnulationCapa;
	}

	public static Carte getCarteInterupt() {
		return carteInterupt;
	}



	public static void setCarteInterupt(Carte carteInterupt) {
		Capacite.carteInterupt = carteInterupt;
	}
	
	














}
