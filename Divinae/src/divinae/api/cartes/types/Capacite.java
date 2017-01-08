package divinae.api.cartes.types;

import java.util.ArrayList;
import java.util.List;
import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public class Capacite {

	private static boolean autorisationPointAction = true;
	private static boolean autorisationApocalypse = true;
	private static ActionSuivante actionSuivante = null;
	private static boolean annulationEffetCapa = false;


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




	public static  void defausser (CarteAction carte, Partie partie) {
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
				GuideSpirituel guideSpirituel = actionSuivante.choisirGuideSpirituelCible(joueur, vise);
				joueur.activerCapaciteCarte(guideSpirituel);
				partie.getTasDeCroyants().addAll(guideSpirituel.getCroyantLie());
				partie.getDefausse().ajoutCarte(guideSpirituel);
			break;

			case "Croyant" :
				Croyant croyant = actionSuivante.choisirCroyantCible(joueur, vise);
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
						GuideSpirituel guideSpirituel = actionSuivante.choisirGuideSpirituelCible(joueur, vise);
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
	

	














}
