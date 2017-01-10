package divinae.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import divinae.api.cartes.types.ActionSuivante;
import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;
import divinae.api.joueur.JoueurVirtuel;
import divinae.api.partie.Partie;

public class ActionSuivanteConsole implements ActionSuivante
{
	@Override
	
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

		System.out.println("Veuillez sélectionner un Guide Spirituel :"+ "\n");
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
	
	public void convertirCroyant (Partie partie, GuideSpirituel carte) {
		while (carte.getNombreCroyantLiable()  >  carte.getCroyantLie().size() && partie.getTasDeCroyants().size() > 0) { 
			
			System.out.println("Veuillez choisir une carte à prendre du tas.");
			for (int i=0; i < partie.getTasDeCroyants().size(); i++){
				System.out.println(i + " : " +  partie.getTasDeCroyants(i).getNom() +"\n");
			}
			int choix;
			Scanner sc = new Scanner(System.in);
			do { 
				choix = sc.nextInt();
				if (choix < 0 || choix >= partie.getTasDeCroyants().size() ) {
					System.out.println("Choix invalide ! Veuillez rentrer un nombre valide.");
				}
			} while (choix < 0 || choix >= partie.getTasDeCroyants().size());
			
			carte.getCroyantLie().add(partie.getTasDeCroyants(choix));
			partie.getTasDeCroyants().remove(choix);
			sc.close();
		}
	}

	
	public int entreeUser (Joueur joueur, int max) {
		int choixUser = 0;
		if (joueur instanceof JoueurVirtuel) {
			switch (((JoueurVirtuel) joueur).getStrategie().toString()) {
			case "StrategieDefensive" :
				// Methode test carte la plus defensive pouvant être jouer
				break;
			case "StrategieOffensive" :
				// Methode test carte la plus offensive pouvant être jouer
				break;
			case "StrategieEquilibre" :
				// Methode test carte la plus equilibre pouvant être jouer
				break;
			case "StrategieAleatoire" :
				choixUser = (int)(Math.random()*max);
			}
		} else {
			Scanner sc = new Scanner(System.in);
			choixUser = sc.nextInt();
				}
		return choixUser;
	}
	
	public void messageListe (Joueur joueur, String message) {
		if (joueur instanceof JoueurVirtuel) {

		} else {
			System.out.println(message);
		}
	}
	
	public void messageRecap (String message) {
		System.out.println(message);
	}


}
