package fr.utt.divinae.api.partie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import fr.utt.divinae.api.cartes.croyant.*;
import fr.utt.divinae.api.cartes.deuxex.*;
import fr.utt.divinae.api.cartes.divinite.*;
import fr.utt.divinae.api.cartes.guide.*;
import fr.utt.divinae.api.cartes.types.*;
import fr.utt.divinae.api.joueur.Joueur;
import fr.utt.divinae.api.joueur.JoueurVirtuel;
import fr.utt.divinae.api.joueur.StrategieAleatoire;


/**
 * La classe Partie represente une partie du jeu.S
 * @author Thomas, Abraham
 *
 */
public final class Partie {
	
	private static volatile Partie instance = null;
	
	private List<Joueur> joueurs;
	private List<CarteAction> table;
	private List<Croyant> tasDeCroyants;
	
	private int indexJoueur1;
	private int indexJoueurTour;
	private Pioche pioche;
	private Defausse defausse;
	private De de;
	private boolean partieFinie;
	private int indexGagnant = -1;
	private int indexPerdant = -1;
	
	
	private Partie() {
		this.indexJoueur1 = 0;
		this.joueurs = new ArrayList<Joueur>();
		this.table = new ArrayList<CarteAction>();
		this.tasDeCroyants = new ArrayList<Croyant>();
		this.indexJoueurTour = 0;
		this.pioche = new Pioche();
		this.defausse = new Defausse();
		this.de = new De();
		this.partieFinie = false;
	}
	
	public final static Partie getInstance() {
		if(Partie.instance == null) {
			synchronized(Partie.class) {
				if(Partie.instance == null) {
					instance = new Partie();
				}
			}
		}
		return Partie.instance;
	}
	
	/**
	 * Effectue les operations de debut de partie c'est-a-dire lancer le de de Cosmogonie 
	 * et ajouter des points aux joueurs en fonction de l'Origine de leur Divinite 
	 * et de la face du de.
	 */
	public void debuterUnTour() {
		de.lancerDe();
		ajoutPoint();
	}
	
	public void ajouterUnJoueurReel(String nom) {
		joueurs.add(new Joueur(nom, this));
	}
	
	public void ajouterUnJoueurVirtuel(String nom, TypeStrategie typeStrategie) {
		joueurs.add(new JoueurVirtuel(nom, this, new StrategieAleatoire()));
	}
	
	public void distribuerLesDivinites() {
		ArrayList<Divinite> piocheDivinite = new ArrayList<Divinite>();
		Collections.addAll(piocheDivinite, 
				new Brewalen(), new Drinded(), new Gorpa(), new Gwenghelen(), 
				new Killinstred(), new Llewella(), new PuiTara(), 
				new Romtec(), new Shingua(), new Yarstur());
		Collections.shuffle(piocheDivinite);
		Random rd=new Random();
        for(int i=0;i<this.joueurs.size();i++) {
        	Divinite div = (piocheDivinite.remove(rd.nextInt(piocheDivinite.size())));
        	div.setJoueurLie(this.joueurs.get(i));
            this.joueurs.get(i).setDivinite(div);
        }
	}
	
	/**
	 * Remplit la pioche avec les cartes du jeu.
	 */
	public void remplirPioche() {
		ArrayList<CarteAction> piocheCartes = new ArrayList<CarteAction>();
		Collections.addAll(piocheCartes, 
				new Moines(new Dogme[]{Dogme.Humain, Dogme.Nature, Dogme.Mystique}, 1),
				new Moines(new Dogme[]{Dogme.Mystique, Dogme.Humain, Dogme.Chaos}, 2),
				new Moines(new Dogme[]{Dogme.Symboles, Dogme.Mystique, Dogme.Chaos}, 3),
				new Moines(new Dogme[]{Dogme.Mystique, Dogme.Nature, Dogme.Symboles}, 4),
				new Moines(new Dogme[]{Dogme.Mystique, Dogme.Nature, Dogme.Chaos}, 5),
				new Travailleurs(new Dogme[]{Dogme.Symboles, Dogme.Humain, Dogme.Chaos}, 1, 6),
				new Travailleurs(new Dogme[]{Dogme.Humain, Dogme.Nature, Dogme.Symboles}, 2, 7),
				new Travailleurs(new Dogme[]{Dogme.Mystique, Dogme.Humain, Dogme.Chaos}, 3, 8),
				new Ermite(new Dogme[]{Dogme.Mystique, Dogme.Nature, Dogme.Chaos},9),
				new Ermite(new Dogme[]{Dogme.Mystique, Dogme.Nature, Dogme.Symboles},10),
				new Integristes(),
				new GuerriersSaints(),
				new Diplomates(),
				new Demons(new Dogme[]{Dogme.Humain, Dogme.Nature, Dogme.Mystique}, 14),
				new Demons(new Dogme[]{Dogme.Mystique, Dogme.Humain, Dogme.Chaos}, 15),
				new Demons(new Dogme[]{Dogme.Symboles, Dogme.Mystique, Dogme.Chaos}, 16),
				new Demons(new Dogme[]{Dogme.Mystique, Dogme.Nature, Dogme.Symboles}, 17),
				new Demons(new Dogme[]{Dogme.Mystique, Dogme.Nature, Dogme.Chaos}, 18),
				new Alchimistes(new Dogme[]{Dogme.Symboles, Dogme.Nature, Dogme.Chaos}, 1, 19),
				new Alchimistes(new Dogme[]{Dogme.Mystique, Dogme.Nature, Dogme.Chaos}, 2, 20),
				new Alchimistes(new Dogme[]{Dogme.Symboles, Dogme.Nature, Dogme.Chaos}, 3, 21),
				new Vampire(new Dogme[]{Dogme.Humain, Dogme.Nature, Dogme.Symboles}, 22),
				new Vampire(new Dogme[]{Dogme.Mystique, Dogme.Humain, Dogme.Chaos}, 23),
				new Lycanthropes(),
				new Pillards(),
				new Illusionnistes(),
				new Esprits(new Dogme[]{Dogme.Humain, Dogme.Nature, Dogme.Mystique}, 27),
				new Esprits(new Dogme[]{Dogme.Mystique, Dogme.Humain, Dogme.Chaos}, 28),
				new Esprits(new Dogme[]{Dogme.Symboles, Dogme.Mystique, Dogme.Chaos}, 29),
				new Esprits(new Dogme[]{Dogme.Mystique, Dogme.Nature, Dogme.Symboles}, 30),
				new Esprits(new Dogme[]{Dogme.Mystique, Dogme.Nature, Dogme.Chaos}, 31),
				new Alienies(new Dogme[]{Dogme.Mystique, Dogme.Humain, Dogme.Chaos}, 1, 32),
				new Alienies(new Dogme[]{Dogme.Mystique, Dogme.Humain, Dogme.Chaos}, 2, 33),
				new Alienies(new Dogme[]{Dogme.Mystique, Dogme.Humain, Dogme.Chaos}, 3, 34),
				new Revenant(),
				new Revolutionnaires(),
				new Nihillistes(),
				new Martyr(Origine.Jour, new Dogme[]{Dogme.Nature, Dogme.Humain}, 38),
				new Martyr(Origine.Nuit, new Dogme[]{Dogme.Humain, Dogme.Symboles}, 39),
				new Martyr(Origine.Neant, new Dogme[]{Dogme.Nature, Dogme.Chaos}, 40),
				new Clerc(Origine.Jour, new Dogme[]{Dogme.Humain, Dogme.Chaos}, 41),
				new Clerc(Origine.Nuit, new Dogme[]{Dogme.Nature, Dogme.Symboles}, 42),
				new Clerc(Origine.Neant, new Dogme[]{Dogme.Nature, Dogme.Humain}, 43),
				new Clerc(Origine.Jour, new Dogme[]{Dogme.Nature, Dogme.Chaos}, 44),
				new Clerc(Origine.Nuit, new Dogme[]{Dogme.Mystique, Dogme.Symboles}, 45),
				new Clerc(Origine.Neant, new Dogme[]{Dogme.Symboles, Dogme.Chaos}, 46),
				new Clerc(Origine.Jour, new Dogme[]{Dogme.Mystique, Dogme.Chaos}, 47),
				new Clerc(Origine.Nuit, new Dogme[]{Dogme.Nature, Dogme.Humain}, 48),
				new Shaman(),
				new Anarchiste(),
				new Paladin(),
				new Ascete(),
				new Devin(),
				new Exorciste(),
				new Sorcier(),
				new Tyran(),
				new Messie(),
				new ColereDivine(Origine.Jour, 58),
				new ColereDivine(Origine.Nuit, 59),
				new Stase(),
				new OrdreCeleste(),
				new Fourberie(),
				new Diversion(),
				new Concentration(),
				new TrouNoir(),
				new Phoenix(),
				new InfluenceJour(),
				new InfluenceNuit(),
				new InfluenceNeant(),
				new InfluenceNulle(70),
				new InfluenceNulle(71),
				new Transe(),
				new Miroir(),
				new Bouleversement(),
				new Inquisition(),
				new Apocalypse(Origine.Jour, 76),
				new Apocalypse(Origine.Nuit, 77),
				new Apocalypse(Origine.Neant, 78),
				new Apocalypse(Origine.Aucune, 79),
				new Apocalypse(Origine.Aucune, 80));
		Collections.shuffle(piocheCartes);
		pioche.remplirPioche(piocheCartes);
	}

	/**
	 * Distribue des cartes aux joueurs.
	 */
	public void distribuerCartes() {
		for(int i = 0; i < this.joueurs.size();i++)
        {
            this.joueurs.get(i).completerMain();
        }
	}
	
	/**
	 * Calcule quel est le joueur ayant le plus grand nombre de prieres
	 * et celui ayant le moins grand nombre.
	 */
	public void finirUnePartie() {
		int maxCroyants = 0;
		int minCroyants = 0;
		indexGagnant = 0;
		indexPerdant = 0;
		for(int i = 0; i < joueurs.size(); i++) {
			if(joueurs.get(i).getNombreCroyant() > maxCroyants) {
				maxCroyants = joueurs.get(i).getNombreCroyant();
				indexGagnant = i;
			}
			if (joueurs.get(i).getNombreCroyant() < minCroyants) {
				minCroyants = joueurs.get(i).getNombreCroyant();
				indexPerdant = i;
			}
		}
	}
	
	/**
	 * Realise les operations necessaire pour commencer le tour suivant.
	 */
	public void preparerTourProchain() {
		Utilitaire.resetAutorisations(this);
	}
	
	/**
	 * Ajoute des points aux joueurs en fonction de l'Origine de leur Divinite 
	 * et de la face du de de Cosmogonie.
	 */
	private void ajoutPoint() {
		for(int i = 0; i < joueurs.size(); i++) {
			if(de.getInfluence()== Origine.Jour) {
				if(joueurs.get(i).getDivinite().getOrigine() == Origine.Jour) {
					joueurs.get(i).ajoutPointsAction(2, Origine.Jour);
				} else if(joueurs.get(i).getDivinite().getOrigine() == Origine.Aube) {
					joueurs.get(i).ajoutPointsAction(1, Origine.Jour);
				}
			} else if(de.getInfluence() == Origine.Nuit) {
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
	
	/**
	 * Indique qu'un Croyant pose peut etre rattache a un Guide Spirituel.
	 */
	public void setCroyantsRattachables() {
		for(int i = 0; i < tasDeCroyants.size(); i ++) {
			tasDeCroyants.get(i).setRattachable(true);
		}
	}
	
	/**
	 * Active les cartes qui ont ete posees sur la table.
	 */
	public void activerCartes() {
		for(int i = table.size()-1; i >= 0; i--) {
			table.remove(i).poserCarteAction();
		}
	}
	
	/**
	 * Retourne une chaine contenant le nom et le type des cartes places sur la table.
	 * @return une chaine contenant le nom et le type des cartes places sur la table
	 */
	public String afficherTable() {
		String retour = "Table: ";
		for(int i = 0; i < table.size(); i++) {
			retour += table.get(i).getCategorieEtNom()+"\n";
		}
		return retour;
	}
	
	/**
	 * Retourne une chaine contenant le nom des Croyants dans le tas de Croyants.
	 * @return une chaine contenant le nom des Croyants dans le tas de Croyants
	 */
	public String afficherTasCroyants() {
		String retour = "Tas des croyants: ";
		for(int i = 0; i < tasDeCroyants.size(); i++) {
			retour += i + " - " + tasDeCroyants.get(i).getNom()+"   ";
		}
		return retour;
	}
	
	/**
	 * Retourne une chaine contenant les details des Guides Spirituel 
	 * et des Croyants qui leurs sont lies d'un joueur.
	 * @param joueur le joueur dont on souhaite voir les croyants et les guides
	 * @return les details des Guides Spirituel et des Croyants d'un joueur
	 */
	public String afficherDetailsGuidesCroyants(Joueur joueur) {
		String retour = "Joueur "+joueur.getNom()+"\n \n";
		for(int i = 0; i < joueur.getGuides().size(); i++) {
			retour += joueur.getGuide(i)+"\n \n";
			for(int j = 0; j < joueur.getGuide(i).getCroyantLie().size(); j++) {
				retour += joueur.getGuide(i).getCroyantLie(j)+"\n";
			}
			retour += "\n";
		}
		return retour;
	}
	
	/**
	 * Retourne une chaine contenant les details des cartes du tas de Croyant
	 * @return une chaine contenant les details des cartes du tas de Croyant
	 */
	public String afficherDetailsTasCroyants() {
		String retour = "";
		for(int i = 0; i < tasDeCroyants.size(); i++) {
			retour += tasDeCroyants.get(i)+"\n";
		}
		return retour;
	}
	
	//Getter et setters
	public List<Croyant> getTasDeCroyants() {
		return tasDeCroyants;
	}
	
	public Croyant getTasDeCroyants(int i) {
		return tasDeCroyants.get(i);
	}

	public void ajoutTasDCroyants(Croyant croyant) {
		tasDeCroyants.add(croyant);
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
		return indexJoueurTour;
	}

	public De getDe() {
		return de;
	}
	
	public List<CarteAction> getTable() {
		return table;
	}
	
	public int getIndexJoueur1() {
		return indexJoueur1;
	}

	public void setIndexJoueur1(int indexJoueur1) {
		this.indexJoueur1 = indexJoueur1;
	}

	public boolean isPartieFinie() {
		return partieFinie;
	}

	public int getIndexGagnant() {
		return indexGagnant;
	}

	public int getIndexPerdant() {
		return indexPerdant;
	}
}
