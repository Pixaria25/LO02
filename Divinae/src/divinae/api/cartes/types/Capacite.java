package divinae.api.cartes.types;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public class Capacite {
	
	private static boolean autorisationPointAction = true;
	private static boolean autorisationApocalypse = true;
	
	
	
	
	
	public static Joueur choisirJoueurCible (Partie partie) {
		System.out.println("Veuillez sélectionner le Joueur à cibler par cette compétence :"+ "\n");
		int choix = 0;
		int indice=0;
		do {	
			System.out.println(indice +" : " + partie.getJoueurs().get(indice).getNom());
			indice++;
		} while (indice <=  partie.getJoueurs().size());
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("(Entrez le nombre compris entre 1 et " + partie.getJoueurs().size() + "nombre correspondant à votre choix) ");
			
			choix = sc.nextInt();	
					
		} while (choix < 0 | choix >  partie.getJoueurs().size());
		sc.close();
		System.out.println("Vous avez ciblé " + (partie.getJoueurs().get(choix)).getNom());
		return partie.getJoueurs().get(choix);
		
	}
	
	public static Divinite choisirDiviniteOuDogme (Dogme dogme1, Dogme dogme2, Partie partie) {
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
	
	public static GuideSpirituel choisirGsp (Partie partie) {
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
		System.out.println("Vous avez ciblé " + gspCiblable.get(choix).getNom() + "appartenant à "+ gspCiblable.get(choix).getJoueurLie().getNom());
		return gspCiblable.get(choix);
	
	}

	public static GuideSpirituel choisirSonGsp (Joueur joueur, Partie partie) {
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
	
	public static Croyant choisirCroyant (Joueur joueur, Partie partie) {
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
	
	public static Origine choisirOrigine (){
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
	
	public static GuideSpirituel choisirDiviniteOuGspNonDogme (Dogme dogme, Partie partie){
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
	
	public static void choisirFaceDe (Carte carte,Partie partie) {
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

	
	
	
	
	public static  void defausser (Carte carte, Partie partie) {
		partie.getDefausse().ajoutCarte(carte);	
	}
	
	public static void donnerPointAction (int point, Origine origine, Joueur joueur) {
		if (autorisationPointAction == true) {
			joueur.ajoutPointsAction(point, origine);
		} else { 
			System.out.println("On ne peut pas gagner de point d'action jusqu'à la fin du tour (competence nihilistes)");
		}
	}
	
	public static void renvoyerGsp (Partie partie) {
		GuideSpirituel gsp = Capacite.choisirGsp(partie);
		partie.getTasDeCroyants().addAll(gsp.getCroyantLie ());
		gsp.getJoueurLie().getMain().add(gsp);
	}
	
	public static void renvoyerGsp (List <GuideSpirituel> gspCiblable, Partie partie) {
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
	
	public static void empecherSacrifice ( Dogme dogme1, Dogme dogme2, String vise, Partie partie) {
		int tourFixe = partie.getNombreTour();
		int jtourActuel = tourFixe;
		Divinite divinite = Capacite.choisirDiviniteOuDogme (dogme1, dogme2, partie);
		
		while (jtourActuel==tourFixe) {
			jtourActuel = partie.getNombreTour();
			divinite.getJoueurLie().setAutorisation(vise);
		}
		
		divinite.getJoueurLie().setAutorisation(null);
	}
  
	public static void imposerSacrifice (String vise, Partie partie) {
		boolean choixJoueur = false;
		Joueur joueur = null;
		while (!choixJoueur) {
			joueur = choisirJoueurCible(partie);
			if(joueur.getGuides().isEmpty()) {
					System.out.println("Veuillez choisir un autre joueur, celui-ci n'a pas de Guides Spirituels");
				} else { 
					choixJoueur = true; }
			}
		int choix;
		switch (vise) {
			case "GuideSpirituel" : 
				System.out.println("Quel " + vise + "voulez vous cibler ?");
				for (int i = 0; i < joueur.getGuides().size(); i++ ) {
					System.out.println(i + " : " + joueur.getGuide(i).getNom() + "\n");
				}
				do {
					System.out.println("Entrez un nombre compris entre 0 et " + (joueur.getGuides().size() - 1) + ", nombre correspondant à votre choix \n");
					Scanner sc = new Scanner(System.in);
					choix = sc.nextInt();	
					sc.close();
					
				} while (choix < 0 || choix > joueur.getGuides().size());
				
				joueur.activerCapaciteCarte(joueur.getGuide(choix));
				partie.getTasDeCroyants().addAll(joueur.getGuide(choix).getCroyantLie());
				partie.getDefausse().ajoutCarte(joueur.getGuide(choix));
			break;
			
			case "Croyant" : 
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
				do {
					System.out.println("Entrez un nombre compris entre 0 et " + compteurCroyants + ", nombre correspondant à votre choix \n");
					Scanner sc = new Scanner(System.in);
					choix = sc.nextInt();	
					sc.close();
					
				} while (choix < 0 || choix > joueur.getGuides().size());
				
				joueur.activerCapaciteCarte(croyantCiblable.get(choix));
				GuideSpirituel GpLie = croyantCiblable.get(choix).getGuideLie();
				partie.getDefausse().ajoutCarte(croyantCiblable.get(choix));
				if (GpLie.getCroyantLie().isEmpty()) {
					partie.getDefausse().ajoutCarte(GpLie);
				}
			break;
		}
	
	  }

	public static void imposerSacrifice (Divinite divinite, String vise, Partie partie) {
		Joueur joueur = divinite.getJoueurLie();
		int indice;
		switch (vise) {
			case "GuideSpirituel" : 
				System.out.println("Veuillez choisir le guide à supprimer : \n");
				for (int i = 0; i < joueur.getGuides().size(); i++) {
					System.out.println("1 : " + joueur.getGuide(i).getNom());
				}
				do {
					System.out.println("Veuillez entrer l'indice valable compris entre 0 " + (joueur.getGuides().size()-1));
					Scanner choix = new Scanner (System.in);
					indice = choix.nextInt();
					choix.close();
				} while (indice < joueur.getGuides().size());
						joueur.activerCapaciteCarte(joueur.getGuide(indice));
						partie.getTasDeCroyants().addAll(joueur.getGuide(indice).getCroyantLie());
						partie.getDefausse().ajoutCarte(joueur.getGuide(indice));
				break;
		}
	
	  }
	
	public static void relancerDe (Partie partie) {
		partie.getDe().lancerDe();
		partie.getDe().getValeur();
		System.out.println("La nouvelle influence est " + partie.getDe().getInfluence());
	}

	public static void retirerTousCroyantLies (Partie partie) {
		GuideSpirituel gsp = Capacite.choisirGsp(partie);
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
		Joueur joueur = choisirJoueurCible (partie);
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
	
	public static void lancerApocalypse (Partie partie) {
		if (autorisationApocalypse == true) {
			partie.finirUnePartie();
			int tourFixe = partie.getNombreTour();
			int tourActuel = tourFixe;
			
			for (int i=0; i < partie.getTable().size(); i++) {
				if (partie.getTable(i).isProtectionCiblage()) {
					partie.getTable(i).setProtectionCiblage(false);
				}
			}
			
			while (tourFixe == tourActuel) {
				autorisationApocalypse = false;
			}
			autorisationApocalypse = true;
		} else { 
			System.out.println("Impossible de lancer une Apocalyspe ce tour-ci veuillez attendre le tour prochain");
		} 
	}
	
	public static void setAutorisationPointAction(boolean autorisationPointAction) {
		Capacite.autorisationPointAction = autorisationPointAction;
	}
	








	







}
