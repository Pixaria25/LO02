package fr.utt.divinae.console;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import fr.utt.divinae.api.cartes.types.Selection;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Divinite;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;
import fr.utt.divinae.api.cartes.types.Utilitaire;
import fr.utt.divinae.api.joueur.Joueur;
import fr.utt.divinae.api.partie.Partie;

public class SelectionConsole implements Selection {

	Partie partie = InterfacePartie.getPartie();
	private Scanner scanner = new Scanner(System.in);


	public void demanderInterruption(Joueur joueurCourant) {
		String interruption = "";
		do {
			System.out.println(partie.afficherTable());
			System.out.println("Voulez vous intervenir ? (y/n)");
			interruption = scanner.next();
			if (interruption.equals("y")) {
				interruption(joueurCourant);
			}

			if (!(interruption.equals("n") || interruption.equals("y"))) {
				System.out.println("Reponse invalide.");
			}
		} while (!interruption.equals("n"));
		
	}
	
	public void interruption(Joueur joueurCourant) {
		HashSet<Integer> actionsValides = new HashSet<Integer>();
		if(joueurCourant.aDesCartesSansOrigine()) {
			System.out.println("1 - Jouer une carte sans Origine");
			actionsValides.add(1);
		}
		if(!joueurCourant.getDivinite().capaciteActivee()) {
			System.out.println("2 - Active la capacite de la divinite");
			actionsValides.add(2);
		}
		int choixAction = 0;
		do{
			System.out.println("Entrez le nombre de l'action voulue.");
			choixAction = scanner.nextInt();
		} while(!actionsValides.contains(choixAction));
		
		switch(choixAction) {
			case 1:
				HashSet<Integer> cartesValides = new HashSet<Integer>();
				for(int i = 0; i < joueurCourant.getMain().size(); i++) {
					if(joueurCourant.getMain().get(i).getOrigine() == Origine.Aucune) {
						System.out.println(i+" - "+joueurCourant.getMain().get(i).getNom());
						cartesValides.add(i);
					}
				}
				int carteChoisie = -1;
				do{
					System.out.println("Choisissez la carte que vous voulez jouer.");
					carteChoisie = scanner.nextInt();
				} while(!cartesValides.contains(carteChoisie));
				joueurCourant.poserCarteAction(carteChoisie);

			case 2:
				joueurCourant.getDivinite().activerCapacite();
			default:
				System.out.println("Choix d'interruption invalide");
		}
	}
	

	@Override
	public Joueur choisirJoueurCible(List<Joueur> liste) {
		if (!liste.isEmpty()) {
			afficherListeJoueur(liste);
			Joueur joueur = selectionnerElementListeJoueur(liste);
			return joueur;
		} 
		return null;
	}

	@Override
	public GuideSpirituel choisirGsp(Joueur joueur, Partie partie) {
		List<GuideSpirituel> gspCiblable = Utilitaire.getGspCiblables(joueur, partie);
		
		if (!gspCiblable.isEmpty()) {
			afficherListeGuide(gspCiblable);
			GuideSpirituel Gsp = selectionnerElementListeGuide(gspCiblable);
			return Gsp;
		}
		return null;
	}

	public Divinite choisirDiviniteOuDogme(Dogme dogme1, Dogme dogme2, Partie partie) {
		List<Divinite> diviniteCiblable = Utilitaire.getDiviniteOuDogme(dogme1, dogme2, partie);
		if (!diviniteCiblable.isEmpty()) {
			afficherListeDivinite(diviniteCiblable);
			Divinite divinite = selectionnerElementListeDivinite(diviniteCiblable);
			return divinite;
		}
		return null;
	}

	public GuideSpirituel choisirSonGsp(Joueur joueur, Partie partie) {
		List<GuideSpirituel> gspCiblable = Utilitaire.getSonGsp(joueur, partie);

		if (!gspCiblable.isEmpty()) {
			afficherListeGuide(gspCiblable);
			GuideSpirituel Gsp = selectionnerElementListeGuide(gspCiblable);
			return Gsp;
		}
		
		return null;
	}

	public Croyant choisirCroyant(Joueur joueur, Partie partie) {
		List<Croyant> croyantCiblable = Utilitaire.getCroyant(joueur, partie);
		
		if (!croyantCiblable.isEmpty()) {
			afficherListeCroyant(croyantCiblable);
			Croyant croyant = selectionnerElementListeCroyant(croyantCiblable);
			return croyant;
		}
		return null;
	}
	
	public Origine choisirOrigine() {
		int choix = 0;
		System.out.println("Veuillez choisir l'origine des point d'action à gagner : " + "\n 1 : Jour" + "\n 2 : Nuit"
				+ "\n 3 : Neant");
		do {
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			sc.close();
			if(choix < 1 || choix > 3) {
				System.out.println("Vous devez choisir soit 1 pour Jour, 2 pour Nuit et 3 pour Neant");
			}
		} while (choix < 1 || choix > 3);
		choix--;
		return Origine.values()[choix];
	}

	public GuideSpirituel choisirDiviniteOuGspNonDogme(Dogme dogme, Partie partie) {

		List<GuideSpirituel> gspCiblable = Utilitaire.getDiviniteOuGspNonDogme(dogme, partie);

		if (!gspCiblable.isEmpty()) {
			afficherListeGuide(gspCiblable);
			GuideSpirituel Gsp = selectionnerElementListeGuide(gspCiblable);
			return Gsp;
		}
		return null;

	}

	public int choisirFaceDe(Joueur joueur, Partie partie) {
		int choix = 0;
		System.out.println("Quel choix de face ? " + "1 : Jour \n" + "2 : Nuit \n" + "3 : Neant\n");
		System.out.println("(Entrez le nombre compris entre 1 et 3 correspondant à votre choix) ");
		do {
			System.out.println("(Entrez le nombre compris entre 1 et 3 correspondant à votre choix) ");
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			sc.close();

		} while (choix < 1 || choix > 3);
		choix--;
		System.out.println("La nouvelle influence est " + Origine.values()[choix]);
		return choix;
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
	
	public GuideSpirituel choisirGspRetire(List<GuideSpirituel> gspCiblable) {
		if (!gspCiblable.isEmpty()) {
			afficherListeGuide(gspCiblable);
			GuideSpirituel gsp = selectionnerElementListeGuide(gspCiblable);
			return gsp;
		}
		
		return null;
	}
	
	public void afficherListeDivinite (List<Divinite> liste) {
		System.out.println("Veuillez sélectionner la carte à cibler par cette compétence :" + "\n");
		int indice = 0;
		do {
			System.out.println(indice + " : " + liste.get(indice).getNom());
			indice++;
		} while (indice < liste.size());
	}
	
	public void afficherListeGuide (List<GuideSpirituel> liste) {
		System.out.println("Veuillez sélectionner le guide à cibler par cette compétence :" + "\n");
		int indice = 0;
		do {
			System.out.println(indice + " : " + liste.get(indice).getNom());
			indice++;
		} while (indice < liste.size());
	}
	
	public void afficherListeCroyant (List<Croyant> liste) {
		System.out.println("Veuillez sélectionner le croyant à cibler par cette compétence :" + "\n");
		int indice = 0;
		do {
			System.out.println(indice + " : " + liste.get(indice).getNom());
			indice++;
		} while (indice < liste.size());
	}
	
	public void afficherListeJoueur (List<Joueur> liste) {
		System.out.println("Veuillez sélectionner la carte à cibler par cette compétence :" + "\n");
		int indice = 0;
		do {
			System.out.println(indice + " : " + liste.get(indice).getNom());
			indice++;
		} while (indice < liste.size());
	}
	
	public Joueur selectionnerElementListeJoueur (List<Joueur> liste) {
		int indexJoueurCible = 0;
			
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
		int choix = 0;
		
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
		int choix = 0;
		
		Scanner sc = new Scanner(System.in);
	
		do {
			System.out.println("(Entrez le nombre compris entre 0 et " + (liste.size()-1)
					+ ",nombre correspondant à votre choix) ");
			choix = sc.nextInt();
		} while (choix < 0 || choix >= liste.size());
	
		sc.close();
		System.out.println("Vous avez ciblé " + liste.get(choix).getNom());
		return liste.get(choix);
	}


}
