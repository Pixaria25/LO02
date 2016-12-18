package partie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import cartes.Carte;
import cartes.Origine;
import cartes.croyant.Croyant;
import cartes.divinite.*;
import joueur.Joueur;
import joueur.JoueurVirtuel;
import joueur.Strategie;
import joueur.StrategieDefensive;
import joueur.StrategieEquilibre;
import joueur.StrategieOffensive;

public class Partie {
	
	private int indexJoueur1;
	private List <Joueur> joueurs;
	private List<Carte> table;
	private List<Croyant> tasDeCroyants;
	private int nombreTour;
	private Pioche pioche;
	private Defausse defausse;
	private De de;
	private boolean partieFinie;
	
	public Partie() {
		this.indexJoueur1 = 0;
		this.joueurs = new ArrayList<Joueur>();
		this.table = new ArrayList<Carte>();
		this.nombreTour = 1;
		this.pioche = new Pioche();
		this.defausse = new Defausse();
		this.de = new De();
		this.partieFinie = false;
	}
	
	Scanner scanner = new Scanner(System.in);

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
				for(int i = 0; i < joueurs.size(); i++) {
					System.out.println(i+"-"+joueurs.get(i).getNom());
				}
				int indexJoueur = 0;
				do{
					System.out.println("Entrer un nombre valide.");
					indexJoueur = scanner.nextInt();
				}  while (indexJoueur < 0  || indexJoueur > joueurs.size());
				retirerUnJoueur(indexJoueur);
				break;
			case 3:
				if(joueurs.size() < 2) {
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
	
	public void jouer() {
		do{
			jouerUnTour();
			preparerTourProchain();
		} while(!partieFinie);
	}
	
	public void jouerUnTour() {
		de.lancerDe();
		ajoutPoint();
		int indexCourant = indexJoueur1;
		for(int i = 0; i < joueurs.size(); i++) {
			Joueur joueurCourant = joueurs.get(indexCourant);
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
				if(partieFinie) {
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
						break;
					case 2:
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
			indexCourant = (indexCourant+1) % joueurs.size();
		}
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
					joueurs.add( new Joueur(nom, this));
					operationTerminee = true;
					break;
				case 2:
					System.out.println("Choisissez la strategie du joueur :\n"
							+ "	1.Offensive\n"
							+ "	2.Defensive\n"
							+ "	3.Equiblibree\n"
							+ "	4.Aleatoire");
					Strategie strat = null;
					boolean stratChoisie = false;
					do{
						int choixStrat = scanner.nextInt();
						switch(choixStrat) {
							case 4:
								System.out.println("Choix de la strategie aleatoire.");
								Random random = new Random();
								choixStrat = 1+random.nextInt(2);
							case 1:
								System.out.println("Choix de la strategie offensive.");
								strat = new StrategieOffensive();
								stratChoisie = true;
								break;
							case 2:
								System.out.println("Choix de la strategie defensive.");
								strat = new StrategieDefensive();
								stratChoisie = true;
								break;
							case 3:
								System.out.println("Choix de la strategie equilibre.");
								strat = new StrategieEquilibre();
								stratChoisie = true;
								break;
							default:
								System.out.println("Choix invalide.");
						}
					} while(!stratChoisie);
					joueurs.add(new JoueurVirtuel(nom, this, strat));
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
	
	public void retirerUnJoueur(int indexJoueur) {
		System.out.println("Le joueur "+joueurs.get(indexJoueur)+" a été supprimé.");
		joueurs.remove(indexJoueur);
	}
	
	public void distribuerLesDivinites() {
		ArrayList<Divinite> piocheDivinite = new ArrayList<Divinite>();
		Collections.addAll(piocheDivinite, new Brewalen(), new Drinded(), new Gorpa(), new Gwenghelen(), 
				new Killinstred(), new Llewella(), new PuiTara(), new Romtec(), new Shingua(), new Yarstur());
		Random rd=new Random();
        for(int i=0;i<this.joueurs.size();i++) {
            this.joueurs.get(i).setDivinite(piocheDivinite.remove(rd.nextInt(piocheDivinite.size())));
        }
	}

	public void finirUnePartie() {
		int maxCroyants = 0;
		int indexGagnant = 0;
		for(int i = 0; i < joueurs.size(); i++) {
			if(joueurs.get(i).getNombreCroyant() > maxCroyants) {
				maxCroyants = joueurs.get(i).getNombreCroyant();
				indexGagnant = i;
			}
		}
		System.out.println("Le gagnant est "+joueurs.get(indexGagnant).getNom()+".");
		partieFinie = true;
	}
	
	
	public void preparerTourProchain() {
		indexJoueur1 = (indexJoueur1 + 1) % joueurs.size();
		
	}
	
	//Ajout des points aux joueurs selon le type de leur divinite et selon la valeur du de de Cosmogonie
	public void ajoutPoint() {
		for(int i = 0; i < joueurs.size(); i++) {
			if(deCosmogonie(de.getValeur()) == Origine.Jour) {
				if(joueurs.get(i).getDivinite().getOrigine() == Origine.Jour) {
					joueurs.get(i).ajoutPointsAction(2, Origine.Jour);
				} else if(joueurs.get(i).getDivinite().getOrigine() == Origine.Aube) {
					joueurs.get(i).ajoutPointsAction(1, Origine.Jour);
				}
			} else if(deCosmogonie(de.getValeur()) == Origine.Nuit) {
				if(joueurs.get(i).getDivinite().getOrigine() == Origine.Nuit) {
					joueurs.get(i).ajoutPointsAction(2, Origine.Nuit);
				} else if(joueurs.get(i).getDivinite().getOrigine() == Origine.Crepuscule) {
					joueurs.get(i).ajoutPointsAction(1, Origine.Nuit);
				}
			} else {
				if(joueurs.get(i).getDivinite().getOrigine() == Origine.Aube || joueurs.get(i).getDivinite().getOrigine() == Origine.Crepuscule) {
					joueurs.get(i).ajoutPointsAction(1, Origine.Neant);
				}
			}
		}
	}
	
	public List<Croyant> getTasDeCroyants() {
		return tasDeCroyants;
	}
	
	public Croyant getTasDeCroyants(int i) {
		return tasDeCroyants.get(i);
	}

	public Pioche getPioche() {
		return pioche;
	}

	public Defausse getDefausse() {
		return defausse;
	}

	public List<Joueur> getJoueurs() {
		return joueurs;
	}

	public int getNombreTour() {
		return nombreTour;
	}

	public De getDe() {
		return de;
	}
	
	private Origine deCosmogonie(int nombre) {
		if(nombre <= 2) {
			return Origine.Neant;
		} else if(nombre >= 3 && nombre <= 4) {
			return Origine.Nuit;
		} else {
			return Origine.Jour;
		}
	}

	public List<Carte> getTable() {
		return table;
	}
	
	public Carte getTable(int i) {
		return table.get(i);
	}

	public int getIndexJoueur1() {
		return indexJoueur1;
	}

	public void setIndexJoueur1(int indexJoueur1) {
		this.indexJoueur1 = indexJoueur1;
	}
}
