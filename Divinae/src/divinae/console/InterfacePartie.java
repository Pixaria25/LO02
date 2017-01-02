package divinae.console;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;
import divinae.api.partie.TypeStrategie;

public class InterfacePartie {

	private Partie partie;
	private Scanner scanner = new Scanner(System.in);
	
	public InterfacePartie() {
		this.partie = new Partie();
	}
	
	public void lancerUnePartie() {
		System.out.println("Lancement d'une partie.");
		
		boolean quitterJeu = false;
		int choix = 0;
		do{
			System.out.println("1-Ajout de joueurs\n2-Retirer un Joueur\n3-Commencer a jouer\n4-Quitter");
			
			
			System.out.println("Entrer un nombre valide.");
			choix = scanner.nextInt();
			
	
			switch(choix) {
			case 1: 
				ajouterUnJoueur();
				break;
			case 2:
				System.out.println("Choisissez le joueur a supprimer.");
				for(int i = 0; i < partie.getJoueurs().size(); i++) {
					System.out.println(i+"-"+partie.getJoueurs().get(i).getNom());
				}
				int indexJoueur = 0;
				do{
					System.out.println("Entrer un nombre valide.");
					indexJoueur = scanner.nextInt();
				}  while (indexJoueur < 0  || indexJoueur > partie.getJoueurs().size());
				System.out.println("Le joueur "+partie.getJoueurs().get(indexJoueur)+" a �t� supprim�.");
				partie.retirerUnJoueur(indexJoueur);
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
		} while(!quitterJeu);
		scanner.close();
	}

	public void ajouterUnJoueur() {
		System.out.println("Entrez le nom du joueur :");
		String nom = scanner.next();
		System.out.println("Choisissez le type de joueur :\n"
				+ "	1.Reel\n"
				+ "	2.Virtuel\n"
				+ "	3.Annuler");
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
							+ "	0.Offensive\n"
							+ "	1.Defensive\n"
							+ "	2.Equiblibree\n"
							+ "	3.Aleatoire");
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
	
	public void jouer() {
		do{
			jouerUnTour();
			partie.preparerTourProchain();
		} while(!partie.isPartieFinie());
	}
	
	public void jouerUnTour() {
		partie.debuterUnTour();
		int indexCourant = partie.getIndexJoueur1();
		for(int i = 0; i <  partie.getJoueurs().size(); i++) {
			Joueur joueurCourant = partie.getJoueurs().get(indexCourant);
			System.out.println(joueurCourant.getNom()+" joue.");
			System.out.println("Voulez-vous defausser des cartes ?");
			boolean reponse = scanner.nextBoolean();
			if(reponse) {
				System.out.println("Combien de cartes voulez-vous defausser ?");
				int nombreCartes = 0;
				boolean aDefausse = false;
				do{
					nombreCartes = scanner.nextInt();
					if(nombreCartes >= 0 || nombreCartes <= joueurCourant.getMain().size()) {
						aDefausse = true;
					} else {
						System.out.println("Ce nombre de cartes est invalide.");
					}
				} while(!aDefausse);
				joueurCourant.defausser(nombreCartes);
			}
			joueurCourant.completerMain();
			
			int choixAction = 0;
			boolean tourJoueurFini = false;
			do{
				if(partie.isPartieFinie()) {
					return;
				}
				System.out.println("1-Jouer une carte");
				System.out.println("2-Sacrifier un croyant ou un guide spirituel");
				System.out.println("3-Activer la capacite de la Divinite");
				System.out.println("4-Finir le tour");
				choixAction = scanner.nextInt();
				switch(choixAction) {
					case 1:
						joueurCourant.poserCarteAction();
						demanderInterruption();
						break;
					case 2:
						if(joueurCourant.isAutorisationcr() && joueurCourant.isAutorisationgsp()) {
							System.out.println("Vous ne pouvez pas sacrifier de cartes.");
						} else {
							demanderInterruption();
							ArrayList<Carte> listeCartesSacrifiables = new ArrayList<Carte>();
							listeCartesSacrifiables.addAll(joueurCourant.getGuides());
							for(int j = 0; j < joueurCourant.getGuides().size(); j++) {
								listeCartesSacrifiables.addAll(joueurCourant.getGuide(j).getCroyantLie());
							}
							System.out.println("Quelle carte voulez-vous sacrifier ?");
							for(int j = 0; j < listeCartesSacrifiables.size(); j++) {
								System.out.println(j+" - "+listeCartesSacrifiables.get(j).getNom());
							}
						}
						break;
					case 3:
						joueurCourant.activerCapaciteCarte(joueurCourant.getDivinite());
						break;
					case 4:
						tourJoueurFini = true;
						break;
					default:
						System.out.println("Ce nombre est invalide.");
				}
			} while(!tourJoueurFini);
			indexCourant = (indexCourant+1) % partie.getJoueurs().size();
		}
	}
	
	private void demanderInterruption() {
		String interruption = "";
		do{
			System.out.println("Est-ce qu'un joueur veut intervenir ? (y/n)");
			interruption = scanner.nextLine();
			if(interruption == "y") {
				interruption();
			}
			if(interruption != "n" && interruption != "y") {
				System.out.println("Reponse invalide.");
			}
		} while(interruption != "n");
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
		if(joueurChoisi.getDivinite().capaciteActivee()) {
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
				partie.getTable().add(joueurChoisi.getMain().remove(carteChoisie));
			case 2:
				joueurChoisi.getDivinite().activerCapacite();
		}
	}
}
