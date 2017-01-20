package fr.utt.divinae.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.utt.divinae.api.cartes.guide.CarteSacrifiable;
import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.CarteAction;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.joueur.Joueur;
import fr.utt.divinae.api.partie.Partie;
import fr.utt.divinae.api.partie.TypeStrategie;

public class InterfaceConsole {

	private static Partie partie;
	private Scanner scanner = new Scanner(System.in);
	
	public InterfaceConsole() {
		partie = Partie.getInstance();
		Joueur.setSelection(new SelectionConsole());
	}
	
	public void lancerUnePartie() {
		System.out.println("Lancement d'une partie.");
		System.out.println("Entrez votre nom.");
		String nom = scanner.next();
		System.out.println("Entrez le nombre de joueurs (entre 1 et 5).");
		int nombreJoueurs = 0;
		do{
			nombreJoueurs = scanner.nextInt();
		} while(nombreJoueurs < 1 || nombreJoueurs > 5);
		partie.ajouterUnJoueurReel(nom);
		for(int i = 0; i < nombreJoueurs; i++) {
			partie.ajouterUnJoueurVirtuel("IA-"+(i+1), TypeStrategie.ALEATOIRE);
		}
		jouer();
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
			
			joueurCourant.jouer();
			
			System.out.println(joueurCourant.getNom()+" a fini son tour.");
			indexCourant = (indexCourant+1) % partie.getJoueurs().size();
			partie.setCroyantsRattachables();
			System.out.println("\n");
		}
	}
	
	public static void jouerTourJoueurReel(Joueur joueurCourant) {
		
		System.out.println(joueurCourant.afficherPoints());
		String affichage = joueurCourant.afficherMainJouable();
		System.out.println(affichage);
		phaseDefausse(joueurCourant);
		int nombreCartes = 7-joueurCourant.getMain().size();
		System.out.println("Pioche de "+nombreCartes+" cartes.");
		joueurCourant.completerMain();
		
		int choixAction = 0;
		boolean tourJoueurFini = false;
		
		
		do{
			Scanner scanner = new Scanner(System.in);
			if(partie.isPartieFinie()) {
				
			} else {
				
				System.out.println(partie.afficherTasCroyants());
				affichage = joueurCourant.afficherMainJouable();
				System.out.println(affichage);
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
						System.out.println(affichage);
						int choixCarte;
						do {
							System.out.println("Entrez le numero de la carte que vous voulez jouer.");
							choixCarte = scanner.nextInt();
						} while(choixCarte < 0 || choixCarte > joueurCourant.getMain().size()+1);
	
						CarteAction cartePosee = joueurCourant.getMain().get(choixCarte);
						boolean poserCarte = joueurCourant.poserCarteAction(choixCarte);
						
						if(poserCarte) {
	
							Capacite.setCarteInterupt(cartePosee);
							for (int i = 0; i < partie.getJoueurs().size(); i++) {
								if (!(partie.getJoueurs().get(i).getNom() == joueurCourant.getNom())) {
									partie.getJoueurs().get(i).demanderInterruption(partie.getJoueurs().get(i));
								}
							}
	
							partie.activerCartes();
	
	
						} else {
							System.out.println("Vous ne pouvez pas jouer cette carte.");
						}
						break;
	
					case 2:
						if((joueurCourant.isAutorisationcr() && joueurCourant.isAutorisationgsp()) || joueurCourant.getGuides().size() == 0) {
							System.out.println("Vous ne pouvez pas sacrifier de cartes.");
						} else {
							joueurCourant.demanderInterruption(joueurCourant);
							List<CarteSacrifiable> listeCartesSacrifiables = joueurCourant.recupererCartesSacrifiables();
							System.out.println("Quelle carte voulez-vous sacrifier ?");
							for(int j = 0; j < listeCartesSacrifiables.size(); j++) {
								System.out.print(j+" - "+listeCartesSacrifiables.get(j).getNom()+"	");
							}
							System.out.println();
							int choixSacrifice = -1;
							do{
								choixSacrifice = scanner.nextInt();
							} while(choixSacrifice < 0 || choixSacrifice >= listeCartesSacrifiables.size());
							if ((joueurCourant.isAutorisationgsp() == false && listeCartesSacrifiables.get(choixSacrifice) instanceof GuideSpirituel) | (joueurCourant.isAutorisationcr() == false && listeCartesSacrifiables.get(choixSacrifice) instanceof Croyant)) {
								System.out.println("Vous ne pouvez pas sacrifier cette carte ce tour ci. (Utilisation d'une capacite contre vous)");
							} else {
								Capacite.setCarteInterupt(listeCartesSacrifiables.get(choixSacrifice));

								joueurCourant.demanderInterruption(joueurCourant);
								if (listeCartesSacrifiables.get(choixSacrifice).isCapaciteBloque()) {

									System.out.println(joueurCourant.getMain().get(choixSacrifice).getNom() + " a été bloqué !");
									joueurCourant.tuerCarte(listeCartesSacrifiables.get(choixSacrifice));
								} else {
									joueurCourant.sacrifierCarte(listeCartesSacrifiables.get(choixSacrifice));
								}
										
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
			}
			System.out.println();
			scanner.close();
		} while(!tourJoueurFini);
		
		
	}
	
	
	
	private static void phaseDefausse(Joueur joueur) {
		Scanner scanner = new Scanner(System.in);
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
		scanner.close();
	}
	



	public static Partie getPartie() {
		return partie;
	}

	
	
}
