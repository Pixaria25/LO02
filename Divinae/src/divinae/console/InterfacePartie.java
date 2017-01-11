package divinae.console;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.CarteAction;

import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;
import divinae.api.joueur.JoueurVirtuel;
import divinae.api.partie.Partie;
import divinae.api.partie.TypeStrategie;

public class InterfacePartie {

	private static Partie partie;
	private Scanner scanner = new Scanner(System.in);
	
	public InterfacePartie() {
		partie = new Partie();
	}
	
	public void lancerUnePartie() {
		System.out.println("Lancement d'une partie.");
		
		boolean quitterJeu = false;
		int choix = 0;
		do{
			try{
				System.out.println("1-Ajout de joueurs\n2-Retirer un joueur\n3-Commencer a jouer\n4-Quitter");
				
				System.out.println("Entrer un nombre valide.");
				choix = scanner.nextInt();
		
				switch(choix) {
				case 1:
					ajouterUnJoueur();
					break;
				case 2:
					supprimerJoueur();
					break;
				case 3:
					if(partie.getJoueurs().size() < 2) {
						System.out.println("Il n'y a pas assez de joueurs dans cette partie. Allez en creer d'autres !");
					} else {
						jouer();
						quitterJeu = true;
					}
					break;
				case 4:
					quitterJeu = true;
					break;
				default:
					System.out.println("Ce nombre est invalide.");
				}
			} catch(InputMismatchException e) {
					System.out.println("Cette entree est invalide.");
					choix = 0;
					scanner.next();
			}
		} while(!quitterJeu);
		scanner.close();
	}

	public void ajouterUnJoueur() {
		System.out.println("Entrez le nom du joueur :");
		String nom = scanner.next();
		System.out.println("Choisissez le type de joueur :\n"
				+ "	1 : Reel\n"
				+ "	2 : Virtuel\n"
				+ "	3 : Annuler");
		boolean operationTerminee = false;
		do {
			int optionChoisie = scanner.nextInt();
			switch(optionChoisie) {
				case 1:
					partie.ajouterUnJoueurReel(nom);
					operationTerminee = true;
					break;
				case 2:
					System.out.println("Choisissez la strategie du joueur :\n"
							+ "	0 : Offensive\n"
							+ "	1 : Defensive\n"
							+ "	2 : Equiblibree\n"
							+ "	3 : Aleatoire");
					int choixStrat = -1;
					do{
						choixStrat = scanner.nextInt();
						if(choixStrat < 0 || choixStrat > 3) {
							System.out.println("Le nombre "+choixStrat+" n'est pas valide.");
						}
						
					} while(choixStrat < 0 || choixStrat > 3);
					partie.ajouterUnJoueurVirtuel(nom, TypeStrategie.values()[choixStrat]);
					operationTerminee = true;
					break;
				case 3:
					System.out.println("Operation annulee.");
					return;
				default:
					System.out.println("Choix invalide");
			}
			
		} while(!operationTerminee);
	}
	
	public void supprimerJoueur() {

		if(partie.getJoueurs().isEmpty()) {
			System.out.println("Vous n'avez pas ajoute de joueurs !");
		} else {
			System.out.println("Choisissez le joueur a supprimer.");
			for(int i = 0; i < partie.getJoueurs().size(); i++) {
				System.out.println(i+" - "+partie.getJoueurs().get(i).getNom());
			}
			System.out.println(partie.getJoueurs().size() + " - Annuler");
			int indexJoueur = 0;
			do{
				System.out.println("Entrer un nombre valide.");
				indexJoueur = scanner.nextInt();
			}  while (indexJoueur < 0  || indexJoueur >= partie.getJoueurs().size());
			if (indexJoueur==partie.getJoueurs().size()) {
				System.out.println("Suppression de joueur annuler.");
			} else {
			  System.out.println("Le joueur "+partie.getJoueurs().get(indexJoueur)+" a été supprimé.");
			  partie.retirerUnJoueur(indexJoueur);

		  }
		}

	}
	
	public void jouer() {
		partie.distribuerLesDivinites();
		partie.remplirPioche();
		partie.distribuerCartes();
		do{
			jouerUnTour();
			partie.preparerTourProchain();
		} while(!partie.isPartieFinie());
		System.out.println("Le gagnant est "+partie.getJoueurs().get(partie.getIndexGagnant()).getNom());
	}
	
	public void jouerUnTour() {
		partie.debuterUnTour();
		int indexCourant = partie.getIndexJoueur1();
		for(int i = 0; i <  partie.getJoueurs().size(); i++) {
			Joueur joueurCourant = partie.getJoueurs().get(indexCourant);
			System.out.println(joueurCourant.getNom()+" joue.");
			
			if(joueurCourant instanceof JoueurVirtuel) {
				//Joueur virtuel
				joueurCourant.jouer();
			} else {
				jouerTourJoueurReel(joueurCourant);
			}
			
			indexCourant = (indexCourant+1) % partie.getJoueurs().size();
			partie.setCroyantsRattachables();
		}
	}
	
	private void jouerTourJoueurReel(Joueur joueurCourant) {
		System.out.println(joueurCourant.afficherMain());
		phaseDefausse(joueurCourant);
		int nombreCartes = 7-joueurCourant.getMain().size();
		System.out.println("Pioche de "+nombreCartes+" cartes.");
		joueurCourant.completerMain();
		
		int choixAction = 0;
		boolean tourJoueurFini = false;
		do{
			if(partie.isPartieFinie()) {
				return;
			}
			
			System.out.println(partie.afficherTable());
			System.out.println(partie.afficherTasCroyants());
			System.out.println(joueurCourant.afficherMain());
			System.out.println(joueurCourant.afficherPoints());
			System.out.println("1 - Jouer une carte");
			System.out.println("2 - Sacrifier un croyant ou un guide spirituel");
			System.out.println("3 - Activer la capacite de la Divinite");
			System.out.println("4 - Voir les cartes de sa main en detail");
			System.out.println("5 - Voir les cartes sur la table en detail");
			System.out.println("6 - Voir les cartes du tas de croyants en detail");
			System.out.println("7 - Voir les details de sa divinite");
			System.out.println("8 - Finir le tour");
			choixAction = scanner.nextInt();
			switch(choixAction) {
				case 1:
					System.out.println(joueurCourant.afficherMain());
					int choixCarte = -1;
					do {
						System.out.println("Entrez le numero de la carte que vous voulez jouer.");
						choixCarte = scanner.nextInt();
					} while(choixCarte < 0 || choixCarte > joueurCourant.getMain().size()+1);

					
					boolean poserCarte = carteJouable(choixCarte, joueurCourant);
					
					if(poserCarte) {
						joueurCourant.poserCarteAction(choixCarte);
						demanderInterruption();
						// Solution ?
						Capacite.setCarteInterupt(joueurCourant.getMain().get(choixCarte));
						CarteAction cartePosee = Capacite.getCarteInterupt();
						if (cartePosee.isCapaciteBloqué() && ( !(cartePosee instanceof Croyant) ||  !(cartePosee instanceof GuideSpirituel) )) {
							System.out.println(cartePosee.getNom() + " a été bloqué !");
						} else {
							partie.activerCartes();
						}
					
					} else {
						System.out.println("Vous ne pouvez pas jouer cette carte.");
					}
					break;
	
				case 2:
					if(joueurCourant.isAutorisationcr() && joueurCourant.isAutorisationgsp()) {
						System.out.println("Vous ne pouvez pas sacrifier de cartes.");
					} else {
						demanderInterruption();
						List<CarteAction> listeCartesSacrifiables = joueurCourant.recupererCartesSacrifiables();
						System.out.println("Quelle carte voulez-vous sacrifier ?");
						for(int j = 0; j < listeCartesSacrifiables.size(); j++) {
							System.out.println(j+" - "+listeCartesSacrifiables.get(j).getNom());
						}
						int choixSacrifice = -1;
						do{
							choixSacrifice = scanner.nextInt();
						} while(choixSacrifice < 0 || choixSacrifice >= listeCartesSacrifiables.size());
						if ((joueurCourant.isAutorisationgsp() == false && listeCartesSacrifiables.get(choixSacrifice) instanceof GuideSpirituel) | (joueurCourant.isAutorisationcr() == false && listeCartesSacrifiables.get(choixSacrifice) instanceof Croyant)) {
							System.out.println("Vous ne pouvez pas sacrifier cette carte ce tour ci. (Utilisation d'une capacite contre vous)");
						} else {
									Capacite.setCarteInterupt(listeCartesSacrifiables.get(choixSacrifice));
									demanderInterruption();
									if (listeCartesSacrifiables.get(choixSacrifice).isCapaciteBloqué()) {
										System.out.println(joueurCourant.getMain().get(choixSacrifice).getNom() + " a été bloqué !");
									} else {
										joueurCourant.sacrifierCarte(listeCartesSacrifiables.get(choixSacrifice));
										
										break;
									}
									joueurCourant.tuerCarte(listeCartesSacrifiables.get(choixSacrifice));
						}
					}
					break;
					
				case 3:
					if(!joueurCourant.getDivinite().capaciteActivee()) {
						System.out.println(joueurCourant.getNom()+" active la capacite de "+joueurCourant.getDivinite().getNom());
						joueurCourant.activerCapaciteCarte(joueurCourant.getDivinite());
					} else {
						System.out.println("Vous ne pouvez pas activer la capacite de votre divinite.");
					}
					break;
					
				case 4:
					System.out.println(joueurCourant.afficherMain());
					break;
					
				case 5:
					partie.afficherDetailsTable();
					break;
					
				case 6:
					partie.afficherDetailsTasCroyants();
					break;
					
				case 7:
					System.out.println(joueurCourant.getDivinite());
					break;
					
				case 8:
					tourJoueurFini = true;
					break;
					
				default:
					System.out.println("Ce nombre est invalide.");
					break;
			}
		} while(!tourJoueurFini);
	}
	
	private boolean carteJouable(int choixCarte, Joueur joueur) {

		boolean poserCarte = false;
		switch (joueur.getMain().get(choixCarte).getOrigine()){
			
			case Jour :
				if (joueur.getPointsAction()[Origine.Jour.ordinal()] >= 1) {
					poserCarte = true;
				} else {
					System.out.println("Pas de point d'origine jour.");
				}
				break;
				
			case Nuit :
				if (joueur.getPointsAction()[Origine.Nuit.ordinal()] >= 1) {
					poserCarte = true;
				} else {
					System.out.println("Pas de point d'origine Nuit.");
				}
				break;
				
			case Neant :
				if (joueur.getPointsAction()[Origine.Neant.ordinal()] >= 1) {
					poserCarte = true;
				} else {
					System.out.println("Pas de point d'origine Neant.");
				}
				break;
				
			case Aube:
				
			case Crepuscule:
				
			case Aucune:
				poserCarte = true;
				
			default:
		}
		return poserCarte;
	}
	
	private void phaseDefausse(Joueur joueur) {
		System.out.println("Voulez-vous defausser des cartes ? (y/n)");
		String reponse = "";
		
		do{
			reponse = scanner.next();
			if(!(reponse.equals("n") || reponse.equals("y"))) {	
				System.out.println("Reponse invalide.");
			}
		} while(!(reponse.equals("n") || reponse.equals("y")));
		if(reponse.equals("y")) {

			System.out.println(joueur.afficherMain());
			System.out.println(joueur.getMain().size() + " - Terminer la selection.");
			System.out.println("Quelles cartes voulez-vous defausser ? (Entrer le numero. Choisissez une carte une deuxieme fois pour annuler la defausse.");
			int choixDefausse = -1;
			ArrayList<CarteAction> carteADefausser = new ArrayList<CarteAction>();
			do{
				choixDefausse = scanner.nextInt();
				if(choixDefausse < 0 || choixDefausse > joueur.getMain().size()) {
					System.out.println("Ce nombre est invalide.");
				} else if(choixDefausse < joueur.getMain().size()){
					if(carteADefausser.contains(joueur.getMain().get(choixDefausse))) {
						carteADefausser.remove(joueur.getMain().get(choixDefausse));
					} else {
						carteADefausser.add(joueur.getMain().get(choixDefausse));
					}
				}
			} while(choixDefausse != joueur.getMain().size() || joueur.getMain().size() == 0);
			//Donner toutes les cartes a defausser ? Donner le numero de la carte a defausser ? Donner le nombre de cartes a defausser puis donner lesquels ?
			joueur.defausser(carteADefausser);
		}
	}
	
	public void demanderInterruption() {
		
		String interruption = "";
		do{
			System.out.println("Est-ce qu'un joueur veut intervenir ? (y/n)");
			interruption = scanner.next();
			if(interruption.equals("y")) {
				interruption();
			}
      
			if(!(interruption.equals("n") || interruption.equals("y"))) {
				System.out.println("Reponse invalide.");
			}
		} while(!interruption.equals("n"));
	}
	
	public void interruption() {
		System.out.println("Quel joueur veut interrompre le tour ?");
		HashSet<Integer> joueursValides = new HashSet<Integer>();
		for(int i = 0; i < partie.getJoueurs().size(); i++) {
			if(!partie.getJoueurs().get(i).getDivinite().capaciteActivee() || partie.getJoueurs().get(i).aDesCartesSansOrigine()) {
				System.out.println(i + " - " + partie.getJoueurs().get(i).getNom());
				joueursValides.add(i);
			}
		}
		int choixJoueur = -1;
		do{
			System.out.println("Entrez le nombre du joueur voulu.");
			choixJoueur = scanner.nextInt();
		} while(!joueursValides.contains(choixJoueur));
		Joueur joueurChoisi = partie.getJoueurs().get(choixJoueur);
		System.out.println();
		
		System.out.println("Choisissez l'action.");
		HashSet<Integer> actionsValides = new HashSet<Integer>();
		if(joueurChoisi.aDesCartesSansOrigine()) {
			System.out.println("1 - Jouer une carte sans Origine");
			actionsValides.add(1);
		}
		if(!joueurChoisi.getDivinite().capaciteActivee()) {
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
				for(int i = 0; i < joueurChoisi.getMain().size(); i++) {
					if(joueurChoisi.getMain().get(i).getOrigine() == Origine.Aucune) {
						System.out.println(i+" - "+joueurChoisi.getMain().get(i).getNom());
						cartesValides.add(i);
					}
				}
				int carteChoisie = -1;
				do{
					System.out.println("Choisissez la carte que vous voulez jouer.");
					carteChoisie = scanner.nextInt();
				} while(!cartesValides.contains(carteChoisie));
				joueurChoisi.poserCarteAction(carteChoisie);
			case 2:
				joueurChoisi.getDivinite().activerCapacite();
			default:
				System.out.println("Choix d'interruption invalide");
		}
	}


	public static Partie getPartie() {
		return partie;
	}

	
	
}
