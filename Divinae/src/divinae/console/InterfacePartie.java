package divinae.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.CarteAction;

import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.joueur.Joueur;
import divinae.api.joueur.JoueurVirtuel;
import divinae.api.partie.Partie;
import divinae.api.partie.TypeStrategie;

public class InterfacePartie {

	private static Partie partie;
	private Scanner scanner = new Scanner(System.in);
	
	public InterfacePartie() {
		partie = Partie.getInstance();
		Capacite.setActionSuivante(new ActionSuivanteConsole());
	}
	
	public void lancerUnePartie() {
		System.out.println("Lancement d'une partie.");
		/*System.out.println("Entrez votre nom.");
		String nom = scanner.next();
		System.out.println("Entrez le nombre de joueurs (entre 1 et 5).");
		int nombreJoueurs = 0;
		do{
			nombreJoueurs = scanner.nextInt();
		} while(nombreJoueurs < 1 || nombreJoueurs > 5);
		partie.ajouterUnJoueurReel(nom);
		for(int i = 0; i < nombreJoueurs; i++) {
			partie.ajouterUnJoueurVirtuel("IA-"+(i+1), TypeStrategie.ALEATOIRE);
		}*/
		jouer();
//		boolean quitterJeu = false;
//		int choix = 0;
//		do{
//			try{
//				System.out.println("1-Ajout de joueurs\n2-Retirer un joueur\n3-Commencer a jouer\n4-Quitter");
//				
//				System.out.println("Entrer un nombre valide.");
//				choix = scanner.nextInt();
//		
//				switch(choix) {
//				case 1:
//					ajouterUnJoueur();
//					break;
//				case 2:
//					supprimerJoueur();
//					break;
//				case 3:
//					if(partie.getJoueurs().size() < 2) {
//						System.out.println("Il n'y a pas assez de joueurs dans cette partie. Allez en creer d'autres !");
//					} else {
//						jouer();
//						quitterJeu = true;
//					}
//					break;
//				case 4:
//					quitterJeu = true;
//					break;
//				default:
//					System.out.println("Ce nombre est invalide.");
//				}
//			} catch(InputMismatchException e) {
//					System.out.println("Cette entree est invalide.");
//					choix = 0;
//					scanner.next();
//			}
//		} while(!quitterJeu);
//		scanner.close();
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
					partie.ajouterUnJoueurVirtuel(nom, TypeStrategie.values()[3]);
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
	}
	
	public void jouerUnTour() {
		partie.debuterUnTour();
		System.out.println("Influence de de Cosmogonie : "+partie.getDe().getInfluence());
		
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
			System.out.println(joueurCourant.getNom()+" a fini son tour.");
			indexCourant = (indexCourant+1) % partie.getJoueurs().size();
			partie.setCroyantsRattachables();
			System.out.println("\n");
		}
	}
	
	private void jouerTourJoueurReel(Joueur joueurCourant) {
		System.out.println(joueurCourant.afficherPoints());
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
			
			
			System.out.println(partie.afficherTasCroyants());
			System.out.println(joueurCourant.afficherMain());
			System.out.println(joueurCourant.afficherPoints());
			System.out.println("1 - Jouer une carte");
			System.out.println("2 - Sacrifier un croyant ou un guide spirituel");
			System.out.println("3 - Activer la capacite de la Divinite");
			System.out.println("4 - Voir les cartes de sa main en detail");
			System.out.println("5 - Voir les guides et croyants lies d'un joueur en detail");
			System.out.println("6 - Voir les cartes du tas de croyants en detail");
			System.out.println("7 - Voir les details de sa divinite");
			System.out.println("8 - Finir le tour");
			choixAction = scanner.nextInt();
			switch(choixAction) {
				case 1:
					System.out.println(joueurCourant.afficherMain());
					int choixCarte;
					do {
						System.out.println("Entrez le numero de la carte que vous voulez jouer.");
						choixCarte = scanner.nextInt();
					} while(choixCarte < 0 || choixCarte > joueurCourant.getMain().size()+1);

					CarteAction carteChoisie = joueurCourant.getMain().get(choixCarte);
					boolean poserCarte = joueurCourant.poserCarteAction(choixCarte);
					
					if(poserCarte) {
						CarteAction cartePosee = joueurCourant.getMain().get(choixCarte-1);
						Capacite.setCarteInterupt(cartePosee);
						joueurCourant.poserCarteAction(choixCarte);
						for (int i = 0; i < partie.getJoueurs().size(); i++) {
							if (!(partie.getJoueurs().get(i).getNom() == joueurCourant.getNom())) {
								partie.getJoueurs().get(i).demanderInterruption();
							}
						}
						
						// Solution ?
<<<<<<< HEAD
						Capacite.setCarteInterupt(carteChoisie);
						CarteAction cartePosee = Capacite.getCarteInterupt();
						if (cartePosee.isCapaciteBloque() && ( !(cartePosee instanceof Croyant) ||  !(cartePosee instanceof GuideSpirituel) )) {
=======
						if (cartePosee.isCapaciteBloqué() && ( !(cartePosee instanceof Croyant) ||  !(cartePosee instanceof GuideSpirituel) )) {
>>>>>>> refs/remotes/origin/Abe
							System.out.println(cartePosee.getNom() + " a été bloqué !");
							joueurCourant.tuerCarte(cartePosee);
						} else {
							cartePosee.poserCarteAction();
						}

					} else {
						System.out.println("Vous ne pouvez pas jouer cette carte.");
					}
					break;

				case 2:
					if((joueurCourant.isAutorisationcr() && joueurCourant.isAutorisationgsp()) || joueurCourant.getGuides().size() == 0) {
						System.out.println("Vous ne pouvez pas sacrifier de cartes.");
					} else {
						joueurCourant.demanderInterruption();
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
<<<<<<< HEAD
									demanderInterruption();
									if (listeCartesSacrifiables.get(choixSacrifice).isCapaciteBloque()) {
=======
									joueurCourant.demanderInterruption();
									if (listeCartesSacrifiables.get(choixSacrifice).isCapaciteBloqué()) {
>>>>>>> refs/remotes/origin/Abe
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
					System.out.println(joueurCourant.afficherMainDetails());
					break;
					
				case 5:
					for(int i = 0; i < partie.getJoueurs().size(); i++) {
						System.out.println(i+" - "+partie.getJoueurs().get(i).getNom());
					}
					int choixJoueur = -1;
					do {
						System.out.println("Choisissez un nom.");
						choixJoueur = scanner.nextInt();
					} while(choixJoueur < 0 || choixJoueur > partie.getJoueurs().size());
					partie.afficherDetailsGuidesCroyants(partie.getJoueurs().get(choixJoueur));
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
			System.out.println();
		} while(!tourJoueurFini);
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
	



	public static Partie getPartie() {
		return partie;
	}

	
	
}
