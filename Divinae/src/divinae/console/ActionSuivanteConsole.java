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
import divinae.api.partie.Partie;

public class ActionSuivanteConsole implements ActionSuivante {

	@Override

	public Joueur choisirJoueurCible(List<Joueur> liste) {
		afficherListeJoueur(liste);
		Joueur joueur = selectionnerElementListeJoueur(liste);
		
		return joueur;
	}

	@Override
	public GuideSpirituel choisirGsp(Joueur joueur, Partie partie) {
		List<GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>();
		int indexGsp = 0;
		int indexJoueur = 0;
		
		while (indexJoueur < partie.getJoueurs().size()) {
			
			if (!(partie.getJoueurs().get(indexJoueur) == joueur)) {
				while (indexGsp < partie.getJoueurs().get(indexJoueur).getGuides().size()) {
					gspCiblable.add(partie.getJoueurs().get(indexJoueur).getGuide(indexGsp));
					indexGsp++;
				} 
			}
			indexGsp = 0;
			indexJoueur++;
		}

		for (int i = 0; i < gspCiblable.size(); i++) {
			if (gspCiblable.get(i).isProtectionCiblage()) {
				gspCiblable.remove(i);
			}
		}

		
		afficherListeGuide(gspCiblable);
		GuideSpirituel Gsp = selectionnerElementListeGuide(gspCiblable);
		return Gsp;

	}

	public Divinite choisirDiviniteOuDogme(Dogme dogme1, Dogme dogme2, Partie partie) {
		int choixDivinite = 0;
		List<Divinite> diviniteCiblable = new ArrayList<Divinite>();

		if (!(dogme1 == null) || !(dogme2 == null)) {
			while (choixDivinite < partie.getJoueurs().size()) {
				if (Capacite.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(), dogme1,
						partie)
						|| Capacite.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(),
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
		afficherListeDivinite(diviniteCiblable);
		Divinite divinite = selectionnerElementListeDivinite(diviniteCiblable);
		return divinite;
	}

	public GuideSpirituel choisirSonGsp(Joueur joueur, Partie partie) {
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

		afficherListeGuide(gspCiblable);
		GuideSpirituel Gsp = selectionnerElementListeGuide(gspCiblable);
		return Gsp;
	}

	public Croyant choisirCroyant(Joueur joueur, Partie partie) {
		List<Croyant> croyantCiblable = new ArrayList<Croyant>();
		for (int i = 0; i < joueur.getGuides().size(); i++) {
			for (int j = 0; j < joueur.getGuide(i).getCroyantLie().size(); j++) {
				croyantCiblable.add(joueur.getGuide(i).getCroyantLie(j));
			}
		}
		
		afficherListeCroyant(croyantCiblable);
		Croyant croyant = selectionnerElementListeCroyant(croyantCiblable);
		return croyant;
	}
	
	public Origine choisirOrigine() {
		Origine origine = null;
		boolean choixValide = false;
		System.out.println("Veuillez choisir l'origine des point d'action à gagner : " + "\n 1 : Jour" + "\n 2 : Nuit"
				+ "\n 3 : Neant");
		do {
			Scanner sc = new Scanner(System.in);
			int choix = sc.nextInt();
			sc.close();
			switch (choix) {
			case 1:
				origine = Origine.Jour;
				choixValide = true;
				break;
			case 2:
				origine = Origine.Nuit;
				choixValide = true;
				break;
			case 3:
				origine = Origine.Neant;
				choixValide = true;
				break;
			default:
				System.out.println("Vous devez choisir soit 1 pour Jour, 2 pour Nuit et 3 pour Neant");
				break;
			}
		} while (!choixValide);
		return origine;
	}

	public GuideSpirituel choisirDiviniteOuGspNonDogme(Dogme dogme, Partie partie) {
		int choixDivinite = 0;
		List<GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>();

		while (choixDivinite < partie.getJoueurs().size()) {
			Dogme[] dogmeDivinite = partie.getJoueurs().get(choixDivinite).getDivinite().getDogme();
			
			if (!(Capacite.comparerDogme(dogmeDivinite, dogme,partie))) {
				gspCiblable.addAll(partie.getJoueurs().get(choixDivinite).getGuides());
			} else {
			
				for (int choixGuide = 0; choixGuide < partie.getJoueurs().get(choixDivinite).getGuides().size(); choixGuide++) {
					GuideSpirituel Gsp = partie.getJoueurs().get(choixDivinite).getGuide(choixGuide);
					
					if (!(Capacite.comparerDogme(dogmeDivinite, dogme,partie))) {
						gspCiblable.add(Gsp);
						if (gspCiblable.get(gspCiblable.indexOf(Gsp)).isProtectionCiblage()) {
							gspCiblable.remove(gspCiblable.indexOf(Gsp));
						}
						
					}
				}
			}
			choixDivinite++;
		}

		afficherListeGuide(gspCiblable);
		GuideSpirituel Gsp = selectionnerElementListeGuide(gspCiblable);
		return Gsp;

	}

	public void choisirFaceDe(Carte carte, Partie partie) {
		int choix;
		System.out.println("Quel choix de face ? " + "1 : Jour \n" + "2 : Nuit \n" + "3 : Neant\n");
		System.out.println("(Entrez le nombre compris entre 1 et 3 correspondant à votre choix) ");
		do {
			System.out.println("(Entrez le nombre compris entre 1 et 3 correspondant à votre choix) ");
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			sc.close();

		} while (choix < 1 || choix > 3);
		switch (choix) {
		case 1:
			partie.getDe().setInfluence(Origine.Jour);
			break;
		case 2:
			partie.getDe().setInfluence(Origine.Nuit);
			break;
		case 3:
			partie.getDe().setInfluence(Origine.Neant);
			break;
		}
		
		partie.setIndexJoueur1(carte.getJoueurLie().getPartie().getJoueurs().indexOf(carte.getJoueurLie()));
		System.out.println("La nouvelle influence est " + partie.getDe().getInfluence());
	}

	public boolean choixMultiples(String cible) {
		System.out.println("Voulez vous cibler un(e) autre " + cible + " ?" + "\n 1 : Oui " + "\n 2 : Non ");
		boolean valChoix = false;
		int choix = 0;
		while (!valChoix) {
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			sc.close();

			switch (choix) {
			case 1:
				valChoix = true;
				break;
			case 2:
				valChoix = true;
				break;
			default:
				valChoix = false;
				throw new RuntimeException("Choix invalide, veuillez choisir 1 : OUI ou 2 : NON ");

			}
		}

		if (choix == 1) {
			return false;
		} else {
			return true;
		}

	}

	public int gspOuCroyant() {
		System.out.println(
				"Utiliser la capacité d'un croyant ou d'un guide ? \n" + "1 : croyant \n" + "2 : guide spirituel \n");
		int choix = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Entrez 1 ou 2 ");
			choix = sc.nextInt();
		} while (!(choix == 1) | !(choix == 2));
		sc.close();
		return choix;
	}

	public void messageRecap(String message) {
		System.out.println(message);
	}

	@Override
	public Croyant choisirTasCroyant(Joueur joueur, Partie partie) {
		afficherListeCroyant(partie.getTasDeCroyants());
		Croyant croyant = selectionnerElementListeCroyant(partie.getTasDeCroyants());
		return croyant;
	}
	
	public GuideSpirituel choisirGspRenvoye(List<GuideSpirituel> gspCiblable) {
		afficherListeGuide(gspCiblable);
		GuideSpirituel Gsp = selectionnerElementListeGuide(gspCiblable);
		return Gsp;

	}
	
	public void afficherListeDivinite (List<Divinite> liste) {
		System.out.println("Veuillez la carte à cibler par cette compétence :" + "\n");
		int indice = 0;
		do {
			System.out.println(indice + " : " + liste.get(indice).getNom());
			indice++;
		} while (indice <= liste.size());
	}
	
	public void afficherListeGuide (List<GuideSpirituel> liste) {
		System.out.println("Veuillez sélectionner le guide à cibler par cette compétence :" + "\n");
		int indice = 0;
		do {
			System.out.println(indice + " : " + liste.get(indice).getNom());
			indice++;
		} while (indice <= liste.size());
	}
	
	public void afficherListeCroyant (List<Croyant> liste) {
		System.out.println("Veuillez sélectionner le croyant à cibler par cette compétence :" + "\n");
		int indice = 0;
		do {
			System.out.println(indice + " : " + liste.get(indice).getNom());
			indice++;
		} while (indice <= liste.size());
	}
	
	public void afficherListeJoueur (List<Joueur> liste) {
		System.out.println("Veuillez la carte à cibler par cette compétence :" + "\n");
		int indice = 0;
		do {
			System.out.println(indice + " : " + liste.get(indice).getNom());
			indice++;
		} while (indice <= liste.size());
	}
	
	public Joueur selectionnerElementListeJoueur (List<Joueur> liste) {
		int indexJoueurCible = 0;
			
		System.out.println("Veuillez sélectionner un joueur :" + "\n");
		do {
			System.out.println("(Entrez le nombre compris entre 0 et " + (liste.size()-1)
					+ ",nombre correspondant à votre choix) ");
			Scanner sc = new Scanner(System.in);
			indexJoueurCible = sc.nextInt();
			sc.close();
		} while (indexJoueurCible < 0 | indexJoueurCible >= liste.size());
		
		
		System.out.println("Vous avez ciblé " + liste.get(indexJoueurCible).getNom());
		Joueur joueur = choisirJoueurCible(liste);
		while (joueur == null) {
			joueur = choisirJoueurCible(liste);
			if (joueur.getGuides().isEmpty()) {
				System.out.println("Veuillez choisir un autre joueur, celui-ci n'a pas de Guides Spirituels");
				joueur = null;
			}
		}
		
		System.out.println("Vous avez ciblé " + liste.get(indexJoueurCible).getNom() + "appartenant à "
				+ liste.get(indexJoueurCible).getNom());
		return liste.get(indexJoueurCible);
	}

	public Divinite selectionnerElementListeDivinite (List<Divinite> liste) {
		int indexDiviniteCible = 0;
		
		System.out.println("Veuillez sélectionner une divinité :" + "\n");
		do {
			System.out.println("(Entrez le nombre compris entre 0 et " + (liste.size()-1)
					+ ",nombre correspondant à votre choix) ");
			Scanner sc = new Scanner(System.in);
			indexDiviniteCible = sc.nextInt();
			sc.close();
		} while (indexDiviniteCible < 0 || indexDiviniteCible >= liste.size());

		System.out.println("Vous avez ciblé " + liste.get(indexDiviniteCible).getNom() + "appartenant à "
				+ liste.get(indexDiviniteCible).getJoueurLie().getNom());
		return liste.get(indexDiviniteCible);
	}
	
	public GuideSpirituel selectionnerElementListeGuide (List<GuideSpirituel> liste) {
		int indice = 0;
		int choix = 0;
		System.out.println("Veuillez sélectionner un Guide Spirituel :" + "\n");
		do {
			System.out.println(indice + " : " + liste.get(indice).getNom());
			indice++;
		} while (indice < liste.size());
		
		do {
			System.out.println("(Entrez le nombre compris entre 0 et " + (liste.size()-1)
					+ ",nombre correspondant à votre choix) ");
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			sc.close();

		} while (choix < 0 || choix >= liste.size());
		System.out.println("Vous avez ciblé " + liste.get(choix).getNom());
		return liste.get(choix);
	}
	
	public Croyant selectionnerElementListeCroyant(List<Croyant> liste) {
		System.out.println("Veuillez sélectionner un croyant :" + "\n");
		int indice = 0;
		int choix = 0;
		do {
			System.out.println(indice + " : " + liste.get(indice).getNom());
			indice++;
		} while (indice < liste.size());
		do {
			System.out.println("(Entrez le nombre compris entre 0 et " + (liste.size()-1)
					+ ",nombre correspondant à votre choix) ");
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			sc.close();

		} while (choix < 0 || choix >= liste.size());
		System.out.println("Vous avez ciblé " + liste.get(choix).getNom());
		return liste.get(choix);
	}

}
