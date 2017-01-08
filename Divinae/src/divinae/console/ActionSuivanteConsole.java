package divinae.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import divinae.api.cartes.deuxex.Transe.ClasseName;
import divinae.api.cartes.types.ActionSuivante;
import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public class ActionSuivanteConsole implements ActionSuivante
{
	@Override
	public Joueur choisirJoueurCible(Partie partie)
	{
		System.out.println("Veuillez sÃ©lectionner le Joueur Ã  cibler par cette compÃ©tence :"+ "\n");
		int choix = 0;
		int indice=0;
		do {
			System.out.println(indice +" : " + partie.getJoueurs().get(indice).getNom());
			indice++;
		} while (indice <=  partie.getJoueurs().size());
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("(Entrez le nombre compris entre 1 et " + partie.getJoueurs().size() + "nombre correspondant Ã  votre choix) ");

			choix = sc.nextInt();

		} while (choix < 0 | choix >  partie.getJoueurs().size());
		sc.close();

		Joueur joueur = partie.getJoueurs().get(choix);
		System.out.println("Vous avez ciblÃ© " + joueur.getNom());

		while (joueur == null) {
			joueur = choisirJoueurCible(partie);
			if(joueur.getGuides().isEmpty()) {
				System.out.println("Veuillez choisir un autre joueur, celui-ci n'a pas de Guides Spirituels");
				joueur = null;
			}
		}
		return joueur;
	}

	@Override
	public GuideSpirituel choisirGuideSpirituelCible(Joueur joueur, String vise)
	{
		System.out.println("Quel " + vise + "voulez vous cibler ?");
		for (int i = 0; i < joueur.getGuides().size(); i++ ) {
			System.out.println(i + " : " + joueur.getGuide(i).getNom() + "\n");
		}
		int choix;
		do {
			System.out.println("Entrez un nombre compris entre 0 et " + (joueur.getGuides().size() - 1) + ", nombre correspondant Ã  votre choix \n");
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			sc.close();

		} while (choix < 0 || choix > joueur.getGuides().size());

		return joueur.getGuide(choix);
	}

	@Override
	public Croyant choisirCroyantCible(Joueur joueur, String vise)
	{
		int compteurCroyants=0;
		List<Croyant> croyantCiblable = new ArrayList<Croyant>();
		System.out.println("Quel " + vise + "voulez vous cibler ?");
		for (int i = 0; i < joueur.getGuides().size(); i++) {
			for (int j = 0; j < joueur.getGuide(i).getCroyantLie().size(); i++) {
				System.out.println(i + " : " + joueur.getGuide(i).getCroyantLie(j).getNom() + "\n");
				croyantCiblable.add(joueur.getGuide(i).getCroyantLie(j));
				compteurCroyants++;
			}
		}
		int choix;
		do {
			System.out.println("Entrez un nombre compris entre 0 et " + compteurCroyants + ", nombre correspondant Ã  votre choix \n");
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			sc.close();

		} while (choix < 0 || choix > joueur.getGuides().size());

		return croyantCiblable.get(choix);
	}

	public void donnerPointAction (int point, Origine origine, Joueur joueur) {
		if (Capacite.isAutorisationApocalypse()==true) {
			joueur.ajoutPointsAction(point, origine);
		} else {
			System.out.println("On ne peut pas gagner de point d'action jusqu'à la fin du tour (competence nihilistes)");
		}
	}
	
	public GuideSpirituel choisirGsp (Partie partie) {
		List <GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>();
		int indexGsp = 0;
		int indexJoueur = 0;
		int choix;
		while (indexJoueur < partie.getJoueurs().size()) {
			while (indexGsp < partie.getJoueurs().get(indexJoueur).getGuides().size()) {
				gspCiblable.add(partie.getJoueurs().get(indexJoueur).getGuide(indexGsp));
				indexGsp++;
			}
			indexGsp = 0;
			indexJoueur++;
		}

		for (int i = 0; i < gspCiblable.size(); i++) {
			if (gspCiblable.get(i).isProtectionCiblage()) {
				gspCiblable.remove(i);
			}
		}

		System.out.println("Veuillez sélectionner le Guide Spirituel à cibler par cette compétence :"+ "\n");
		int indice= 0;
		do {
				System.out.println(indice +" : " + gspCiblable.get(indice).getNom());
				indice++;
		} while (indice < gspCiblable.size());
		do {
			System.out.println("(Entrez le nombre compris entre 1 et " + gspCiblable.size() + ",nombre correspondant à votre choix) ");
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			sc.close();

		} while (choix < 0 || choix > gspCiblable.size());
		System.out.println("Vous avez ciblé " + gspCiblable.get(choix).getNom() + "appartenant à "+ gspCiblable.get(choix).getJoueurLie().getNom() + "/n"
				+"Ps: Certaines compétences ne peuvent pas cibler ses propres guides.");
		return gspCiblable.get(choix);

	}

	public void renvoyerGsp (List <GuideSpirituel> gspCiblable, Partie partie) {
		System.out.println("Veuillez choisir le Guide Spirituel a supprimer :\n");
		int choix;
		for (int i = 0; i < gspCiblable.size(); i++ ) {
			System.out.println(i + " : " + gspCiblable.get(i).getNom());
		}
		do {
			System.out.println("(Entrez un nombre compris entre 0 et " + gspCiblable.size() + ", nombre correspondant à votre choix) ");
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			sc.close();

		} while (choix < 0 || choix > gspCiblable.size());
		for (int j =0 ; j < gspCiblable.get(choix).getCroyantLie().size(); j++) {
			Capacite.defausser(gspCiblable.get(choix).getCroyantLie(j), partie);
		}
		gspCiblable.get(choix).getJoueurLie().getMain().add(gspCiblable.get(choix));
	}

	public Divinite choisirDiviniteOuDogme (Dogme dogme1, Dogme dogme2, Partie partie) {
		int choixDivinite = 0;
		List <Divinite> diviniteCiblable = new ArrayList<Divinite>();
		int choix = 0;

		while (choixDivinite < partie.getJoueurs().size()) {
			if (Capacite.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(), dogme1, partie) || Capacite.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(), dogme2, partie)) {
				diviniteCiblable.add(partie.getJoueurs().get(choixDivinite).getDivinite());
			}
			choixDivinite++;
		}
		System.out.println("Veuillez sélectionner la divinité à cibler par cette compétence :"+ "\n");
		int indice=0;
		do {
			System.out.println(indice +" : " + diviniteCiblable.get(indice).getNom());
			indice++;
		} while (indice < diviniteCiblable.size());
		do {
			System.out.println("(Entrez le nombre compris entre 1 et " + diviniteCiblable.size() + "nombre correspondant à votre choix) ");
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			sc.close();

		} while (choix < 0 || choix > diviniteCiblable.size());

		System.out.println("Vous avez ciblé " + diviniteCiblable.get(choix).getNom() + "appartenant à "+ diviniteCiblable.get(choix).getJoueurLie().getNom());
		return diviniteCiblable.get(choix);
	}

	public void relancerDe (Partie partie) {
		partie.getDe().lancerDe();
		partie.getDe().getValeur();
		System.out.println("La nouvelle influence est " + partie.getDe().getInfluence());
	}
	
	public void lancerApocalypse (Partie partie) {
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
			System.out.println("Impossible de lancer une Apocalyspe ce tour-ci veuillez attendre le tour prochain");
		}
	}

	public GuideSpirituel choisirSonGsp (Joueur joueur, Partie partie) {
		List <GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>();
		int indexJoueur = partie.getJoueurs().indexOf(joueur);
		int choix;

		for (int i =0; i < partie.getJoueurs().get(indexJoueur).getGuides().size(); i++ ) {
			gspCiblable.add(partie.getJoueurs().get(indexJoueur).getGuide(i));
		}

		for (int i = 0; i < gspCiblable.size(); i++) {
			if (gspCiblable.get(i).isProtectionCiblage()) {
				gspCiblable.remove(i);
			}
		}

		System.out.println("Veuillez sélectionner le Guide Spirituel à échanger par cette compétence :"+ "\n");
		int indice= 0;
		do {
			System.out.println(indice +" : " + gspCiblable.get(indice).getNom());
			indice++;
		} while (indice < gspCiblable.size());
		do {
			System.out.println("(Entrez le nombre compris entre 1 et " + gspCiblable.size() + ",nombre correspondant à votre choix) ");
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			sc.close();

		} while (choix < 0 || choix > gspCiblable.size());
		System.out.println("Vous avez ciblé " + gspCiblable.get(choix).getNom());
		return gspCiblable.get(choix);

	}

	public Croyant choisirCroyant (Joueur joueur, Partie partie) {
		int choix = 0;
		int indice=0;
		List <Croyant> croyantCiblable = new ArrayList <Croyant> ();
		System.out.println("Choisissez un croyant à cibler.");
		for (int i = 0; i < joueur.getGuides().size(); i++) {
			for(int j =0; j < joueur.getGuide(i).getCroyantLie().size(); j++){
					System.out.println(indice + " : " + joueur.getGuide(i).getCroyantLie(j).getNom());
					croyantCiblable.add(joueur.getGuide(i).getCroyantLie(j));
					indice++;
			}
		}
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("(Entrez le nombre compris entre 1 et " + partie.getJoueurs().size() + "nombre correspondant à votre choix) ");
			choix = sc.nextInt();
		} while (choix < 0 | choix >= joueur.getNombreCroyant());
		sc.close();
		return croyantCiblable.get(choix);
	}

	public Origine choisirOrigine () {
		Origine origine = null;
		boolean choixValide = false;
		System.out.println("Veuillez choisir l'origine des point d'action à gagner : "
					+ "\n 1 : Jour"
					+ "\n 2 : Nuit"
					+ "\n 3 : Neant");
		do {
			Scanner sc = new Scanner(System.in);
			int choix = sc.nextInt();
			sc.close();
			switch (choix) {
			case 1 : origine = Origine.Jour;
					 choixValide = true;
			break;
			case 2 : origine = Origine.Nuit;
				  	 choixValide = true;
			break;
			case 3 : origine = Origine.Neant;
					 choixValide = true;
			break;
			default : System.out.println("Vous devez choisir soit 1 pour Jour, 2 pour Nuit et 3 pour Neant");
			break;
			}
		} while (!choixValide);
		return origine;
	}
	
	public GuideSpirituel choisirDiviniteOuGspNonDogme (Dogme dogme, Partie partie) {
		int choixDivinite = 0;
		List <GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>();
		int choix = 0;

		while (choixDivinite < partie.getJoueurs().size()) {
			if (!(Capacite.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(), dogme, partie))){
					gspCiblable.addAll(partie.getJoueurs().get(choixDivinite).getGuides());
			} else {
				for (int choixGuide = 0; choixGuide < partie.getJoueurs().get(choixDivinite).getGuides().size(); choixGuide++){
					if (!(Capacite.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(), dogme, partie))) {
						gspCiblable.add(partie.getJoueurs().get(choixDivinite).getGuide(choixGuide));
					}
				}
			}
			choixDivinite++;
		}

		for (int i = 0; i < gspCiblable.size(); i++) {
			if (gspCiblable.get(i).isProtectionCiblage()) {
				gspCiblable.remove(i);
			}
		}

		System.out.println("Veuillez sélectionner le Guide Spirituel à cibler par cette compétence :"+ "\n");
		int indice=0;
		do {
			System.out.println(indice +" : " + gspCiblable.get(indice).getNom());
			indice++;
		} while (indice < gspCiblable.size());
		do {
			System.out.println("(Entrez le nombre compris entre 1 et " + gspCiblable.size() + "nombre correspondant à votre choix) ");
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			sc.close();

		} while (choix < 0 || choix > gspCiblable.size());

		System.out.println("Vous avez ciblé " + gspCiblable.get(choix).getNom() + "appartenant à "+ gspCiblable.get(choix).getJoueurLie().getNom());
		return gspCiblable.get(choix);

	}

	public void choisirFaceDe (Carte carte,Partie partie) {
		int choix;
		System.out.println("Quel choix de face ? "
				+ "1 : Jour \n"
				+ "2 : Nuit \n"
				+ "3 : Neant\n");
		System.out.println("(Entrez le nombre compris entre 1 et 3 correspondant à votre choix) ");
		do {
			System.out.println("(Entrez le nombre compris entre 1 et 3 correspondant à votre choix) ");
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			sc.close();

		} while (choix < 1 || choix > 3);
		switch (choix) {
		case 1 : partie.getDe().setInfluence(Origine.Jour);
		break;
		case 2 : partie.getDe().setInfluence(Origine.Nuit);
		break;
		case 3 : partie.getDe().setInfluence(Origine.Neant);
		break;
		}
		partie.setIndexJoueur1(carte.getJoueurLie().getPartie().getJoueurs().indexOf(carte.getJoueurLie()));
		System.out.println("La nouvelle influence est " + partie.getDe().getInfluence());
	}
	
	public boolean choixMultiples (String cible) {
		System.out.println("Voulez vous cibler un(e) autre " + cible +" ?" 
	  			+ "\n 1 : Oui "
	  			+ "\n 2 : Non ");
		boolean valChoix = false;
		int choix = 0;
		while (!valChoix) {
		  	Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();	
			sc.close();
		  	
			switch (choix) {
			case 1 : valChoix = true;
			break;
			case 2 : valChoix = true;
			break;
			default :  valChoix = false;
				throw new RuntimeException("Choix invalide, veuillez choisir 1 : OUI ou 2 : NON ");
					 
			}
		}
		
		if (choix == 1) {
			return false;
		} else { return true; }
		
	}

	public void recupererUnGsp (Carte carte) {
		GuideSpirituel GpCible = Capacite.getActionSuivante().choisirGsp(carte.getJoueurLie().getPartie());
		while (GpCible.getJoueurLie() == carte.getJoueurLie()) {
			System.out.println("Ce guide spirituel vous appartient déjà, choisissez en un autre !");
			GpCible = Capacite.getActionSuivante().choisirGsp(carte.getJoueurLie().getPartie());
		}
		GpCible.setJoueurLie(carte.getJoueurLie());
		System.out.println("Vous avez récupérer le guide spirituel suivant : " + GpCible.getNom());
	}

	public void recupererEffetBenef (Carte carte, Joueur joueur, Partie partie) {
		ClasseName z = ClasseName.valueOf(carte.getClass().getSimpleName());
   
	    switch (z) {
	    case Croyant:
		    int croyantLiable = 0;
		    for (int i = 0; i < joueur.getGuides().size(); i++) {
				for(int j =0; j < joueur.getGuide(i).getCroyantLie().size(); j++){
						croyantLiable++;
				} 
			}
	    	if (joueur.getNombreCroyant() < croyantLiable) {
	    		for (int i = 0; i < joueur.getGuides().size(); i++) {
	    			if (joueur.getGuide(i).getCroyantLie().size() < joueur.getGuide(i).getNombreCroyantLiable()) {
	    				((Croyant) carte).setGuideLie(joueur.getGuide(i));
	    			} 
	    		}
	    	} else { System.out.println("Pas de place disponible pour lier ce croyant"); }
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
	        case "Phoenix" : Capacite.copierCapacite(carte, partie);
	     	break;
	    
	        default : System.out.println("Cette carte n'a aucun effet bénéfique dire pour vous.");
	       	break;
	        }
	    break;
	    
	    default : System.out.println("Aucun effet bénéfique pour vous.");
	    break;
	    }
	    
	}

	public int gspOuCroyant () {
		System.out.println("Utiliser la capacité d'un croyant ou d'un guide ? \n"
				+ "1 : croyant \n"
				+ "2 : guide spirituel \n");
		int choix = 0;
		Scanner sc = new Scanner (System.in);
		do {
			System.out.println("Entrez 1 ou 2 ");
			choix = sc.nextInt();	
		} while (!(choix == 1) | !(choix == 2));
		sc.close();
		return choix;
	}
	
	
}
