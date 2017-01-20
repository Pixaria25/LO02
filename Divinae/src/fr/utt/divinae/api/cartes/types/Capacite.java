package fr.utt.divinae.api.cartes.types;

import java.util.List;

import fr.utt.divinae.api.cartes.deuxex.Transe.ClasseName;
import fr.utt.divinae.api.joueur.Joueur;
import fr.utt.divinae.api.partie.Partie;

/**
 * La classe Capacite contient les differentes methodes modelisant les capacites des cartes.
 * Lorsqu'une carte active sa capacite, elle va appeler une des methodes de Capacite.
 * @author Thomas, Abraham
 *
 */
public class Capacite {

	private static boolean autorisationPointAction = true;
	private static boolean autorisationApocalypse = true;
	

	static CarteAction carteInterupt;

	
	/**
    Donne des points d'actions
    @param point Le nombre de point d'action à donner
    @param origine Origine du point d'action
    @param joueur Le joueur ciblé
	*/
	public static void donnerPointAction (int point, Origine origine, Joueur joueur) {
		
		if (Capacite.isAutorisationPointAction()) { // l'autorisation de modifier les points d'action est vérifié, on ajoute les points sinon c'est bloqué
			joueur.ajoutPointsAction(point, origine);
		} else {
			joueur.messageRecap("On ne peut pas gagner de point d'action jusqu'à la fin du tour (competence nihilistes)");
		}
	}
	
	/**
    Relance le dé et affiche la nouvelle influence
    @param partie La partie jouée actuellement
	 */
	public static void relancerDe (Partie partie) {
		partie.getDe().lancerDe();
		partie.getDe().getValeur();
		
		int indexCarteJouee = partie.getTable().size()-1;
		if (indexCarteJouee >= 0) {
			Joueur joueurCourant = partie.getTable().get(indexCarteJouee).getJoueurLie(); 
			joueurCourant.messageRecap("La nouvelle influence est " + partie.getDe().getInfluence());
		}
	}
	
	/**
	Lance une apocalypse
	    @param partie La partie jouée actuellement
	 */
	public static void lancerApocalypse (Partie partie) {
		boolean egalite = false;
		partie.finirUnePartie();
		if (Capacite.isAutorisationApocalypse()== true) {
			
			if (partie.getJoueurs().size() < 4) {
				for (int i = 0; i < partie.getJoueurs().size(); i++) {
					if (partie.getJoueurs().get(i).getNombreCroyant() == partie.getJoueurs().get(partie.getIndexGagnant()).getNombreCroyant()){
						egalite = true;
					}
				}
				if (!egalite) {
					System.exit(0);
				}
			} else {
				for (int i = 0; i < partie.getJoueurs().size(); i++) {
					if (partie.getJoueurs().get(i).getNombreCroyant() == partie.getJoueurs().get(partie.getIndexPerdant()).getNombreCroyant()){
						egalite = true;
					}
				}
				if (!egalite) {
					partie.getJoueurs().remove(partie.getJoueurs().indexOf(partie.getIndexPerdant()));
					Utilitaire.resetAutorisations(partie);
					Capacite.setAutorisationApocalypse(false);
				}
			}
			
		} else {
			int indexCarteJouee = partie.getTable().size()-1;
			Joueur joueurCourant = partie.getTable().get(indexCarteJouee).getJoueurLie();
			joueurCourant.messageRecap("Impossible de lancer une Apocalyspe ce tour-ci veuillez attendre le tour prochain");
		}
	}
	
	/**
	Récupére les effets bénéfiques d'une carte
	    @param partie La partie jouée actuellement
	     @param joueur Le joueur qui lance cette capacité
	      @param carte La carte ciblé
	*/
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
	    	} else { 
	    		carte.getJoueurLie().messageRecap("Pas de place disponible pour lier ce croyant"); }
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
	        case "Phoenix" : Capacite.copierCapacite(carte.getJoueurLie(), carte, partie);
	     	break;
	    
	        default : carte.getJoueurLie().messageRecap("Cette carte n'a aucun effet bénéfique dire pour vous.");
	       	break;
	        }
	    break;
	    
	    default : carte.getJoueurLie().messageRecap("Aucun effet bénéfique pour vous.");
	    break;
	    }
	}

	/**
	Défausse une carte
	    @param partie La partie jouée actuellement
	     @param carte La carte ciblé
	*/
	public static void defausser (CarteAction carte, Partie partie) {
		partie.getDefausse().ajoutCarte(carte);
	}
	
	/**
	Renvoit une tous les croyants liés à un guide 
	    @param partie La partie jouée actuellement
	     @param cartePosee La carte posée
	      @param gspCiblable Liste de guides ciblables
	*/
	public static void renvoyerCroyantsGsp (List<GuideSpirituel> gspCiblable, CarteAction cartePosee, Partie partie) {
		if(!gspCiblable.isEmpty()) {
			GuideSpirituel gsp = cartePosee.getJoueurLie().choisirGspRetire(gspCiblable);
			partie.getTasDeCroyants().addAll(gsp.getCroyantLie ());
			gsp.getJoueurLie().tuerCarte(gsp);
		}
	}

	/**
	Empêche le sacrifice d'une carte d'une divinité qui correspond à un des deux dogmes
	    @param partie La partie jouée actuellement
	     @param cartePosee La carte posé pour empêcher le sacrifice
	      @param visé Le type de carte visé (guide ou croyant)
	*/
	public static void empecherSacrifice ( Dogme dogme1, Dogme dogme2, String vise, Carte cartePosee, Partie partie) {
		Divinite divinite = cartePosee.getJoueurLie().choisirDiviniteOuDogme (dogme1, dogme2);
		if(divinite != null) {
			CarteAction carteCible = null;
			
			if (vise == "Croyant") {
				carteCible = divinite.getJoueurLie().choisirCroyant(divinite.getJoueurLie());
			} else if (vise == "GuideSpirituel") {
				carteCible = divinite.getJoueurLie().choisirSonGsp();
			}
			
			carteCible.setAutorisationSacrifice(false);
		}
	}

	/**
	Impose le sacrifice d'une carte d'une divinité 
	    @param partie La partie jouée actuellement
	     @param joueurVise Le joueur qui est visé
	      @param vise Le type de carte ciblé
	*/
	public static void imposerSacrifice (String vise, Joueur joueurVise, Partie partie) {
		switch (vise) {
			case "GuideSpirituel" :
				GuideSpirituel guideSpirituel =  joueurVise.choisirSonGsp();
				if(guideSpirituel != null) {
					joueurVise.activerCapaciteCarte(guideSpirituel);
					partie.getTasDeCroyants().addAll(guideSpirituel.getCroyantLie());
					partie.getDefausse().ajoutCarte(guideSpirituel);
				}
			break;

			case "Croyant" :
				Croyant croyant =  joueurVise.choisirCroyant(joueurVise);
				if(croyant != null) {
					joueurVise.activerCapaciteCarte(croyant);
					GuideSpirituel GpLie = croyant.getGuideLie();
					partie.getDefausse().ajoutCarte(croyant);
					if (GpLie.getCroyantLie().isEmpty()) {
						partie.getDefausse().ajoutCarte(GpLie);
					}
				}
			break;
		}
	  }

	/**
	Impose le sacrifice d'un guide d'une divinité 
	    @param partie La partie jouée actuellement
	     @param divinite La divinité ciblée
	*/
	public static void imposerSacrificeGuideSpirituel (Divinite divinite, Partie partie) {
		Joueur joueur = divinite.getJoueurLie();

		GuideSpirituel guideSpirituel = joueur.choisirSonGsp();
		if(guideSpirituel != null) {
			joueur.activerCapaciteCarte(guideSpirituel);
			partie.getTasDeCroyants().addAll(guideSpirituel.getCroyantLie());
			partie.getDefausse().ajoutCarte(guideSpirituel);
		}
	}
	
	/**
	Copie la capacité d'une carte 
	    @param partie La partie jouée actuellement
	     @param carteCible La carte ciblée 
	      @param joueurCourant Le joueur qui joue cette capacité
	*/
	public static void copierCapacite (Joueur joueurCourant, Carte carteCible, Partie partie) {
		Joueur joueurOriginal = carteCible.getJoueurLie();
		carteCible.setJoueurLie(joueurCourant);
		carteCible.activerCapacite();
		carteCible.setJoueurLie(joueurOriginal);
	}
	
	/**
	Récupère les points d'actions de tous les joueurs n'ayant pas joués 
	    @param partie La partie jouée actuellement
	     @param joueurCourant Le joueur qui joue cette capacité
	*/	
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
	
	/**
	Prends des carte dans la main d'une autre divinité
	    @param partie La partie jouée actuellement
	     @param nbCartes Le nombre de cartes à prendre
	      @param carte La carte posé
	*/	
	public static void prendreCartes (Carte carte, int nbCarte, Partie partie) {
		int nbCartesPrises = 0;
		int choixHasard = 0 ;
		List<Joueur> liste = Utilitaire.extraireListeJoueurRestrainte (partie, carte.getJoueurLie());
		Joueur joueur = carte.getJoueurLie().choisirJoueurCible(liste);
		if (joueur.getMain().size() > 0) {
			while (nbCartesPrises < nbCarte | !joueur.getMain().isEmpty()) {
				choixHasard = (int)Math.random()*(joueur.getMain().size()-1);
				carte.getJoueurLie().getMain().add(joueur.getMain().get(choixHasard));
				nbCartesPrises++;
			}
		}
	}
	
	/**
	Annule l'effet d'une carte dont une origine corresponds aux origines ciblées
	    @param partie La partie jouée actuellement
	     @param origineCible Les origines ciblées
	      @param carteCible La carte ciblé
	*/	
	public static void annulerEffetCarte (CarteAction carteCible, Origine [] origineCible, Partie partie) {
		if (carteCible != null) {
			int max = origineCible.length;
			for (int i=0; i < max; i++ ){
				
				if (carteCible.getOrigine() == origineCible[i]) {
					carteCible.setCapaciteBloqué(true);
					break;
				}
			}
		}
	}

	/**
	Récupère un guide d'un autre joueur
	    @param joueur Le joueur qui joue cette capacité
	*/	
	public static void recupererUnGsp (Joueur joueur) {
		GuideSpirituel gspCible = joueur.choisirGsp();
		if (gspCible != null) {
			gspCible.setJoueurLie(joueur);
			joueur.messageRecap(gspCible.getJoueurLie().getNom() + " récupère le guide spirituel suivant : " + gspCible.getNom());
		}
	}

	/**
	Choisit la face du dé et donc la nouvelle influence 
	    @param partie La partie jouée actuellement
	     @param joueur Le joueur qui joue cette capacité
	*/	
	public static void changerFaceDe(Joueur joueur, Partie partie) {
		int choix = joueur.choisirFaceDe(joueur);
		partie.getDe().setInfluence(Origine.values()[choix]);
		partie.setIndexJoueur1(partie.getJoueurs().indexOf(joueur));
	}
	
	/**
	Empêche tous les joueurs de recevoir des points d'actions spplémentaires ce tour ci
	    @param partie La partie jouée actuellement
	*/
	public static void bloquerPointAction (Partie partie) {
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

	
	public static CarteAction getCarteInterupt() {
		return carteInterupt;
	}

	public static void setCarteInterupt(CarteAction carteInterupt) {
		Capacite.carteInterupt = carteInterupt;
	}
}
