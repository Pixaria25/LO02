package divinae.api.partie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import divinae.api.cartes.divinite.*;
import divinae.api.cartes.types.*;
import divinae.api.joueur.Joueur;
import divinae.api.joueur.JoueurVirtuel;
import divinae.api.joueur.StrategieAleatoire;
import divinae.api.cartes.croyant.*;
import divinae.api.cartes.deuxex.*;
import divinae.api.cartes.guide.*;


public class Partie {
	
	private int indexJoueur1;
	private List<Joueur> joueurs;
	private List<CarteAction> table;
	private List<Croyant> tasDeCroyants;
	private int nombreTour;
	private Pioche pioche;
	private Defausse defausse;
	private De de;
	private boolean partieFinie;
	private int indexGagnant = -1;
	
	public Partie() {
		this.indexJoueur1 = 0;
		this.joueurs = new ArrayList<Joueur>();
		this.table = new ArrayList<CarteAction>();
		this.tasDeCroyants = new ArrayList<Croyant>();
		this.nombreTour = 1;
		this.pioche = new Pioche();
		this.defausse = new Defausse();
		this.de = new De();
		this.partieFinie = false;
		//Joueurs par defaut
		joueurs.add(new Joueur("Thomas", this));
		joueurs.add(new JoueurVirtuel("Alex", this, new StrategieAleatoire()));
		joueurs.add(new JoueurVirtuel("Fabrice", this, new StrategieAleatoire()));
	}
	
	
	public void debuterUnTour() {
		de.lancerDe();
		ajoutPoint();
	}
	
	public void ajouterUnJoueurReel(String nom) {
		joueurs.add(new Joueur(nom, this));
	}
	
	public void ajouterUnJoueurVirtuel(String nom, TypeStrategie typeStrategie) {
		/*TypeStrategie typeStrategieEffectif = typeStrategie;
		if(typeStrategie == TypeStrategie.ALEATOIRE) {
			Random random = new Random();
			int choixStrat = 0;
			choixStrat = random.nextInt(3);
			typeStrategieEffectif = TypeStrategie.values()[choixStrat];
		}
		switch (typeStrategieEffectif) {
		case ALEATOIRE:
			joueurs.add(new JoueurVirtuel(nom, this, new StrategieAleatoire()));
			break;

		default: 
			throw new RuntimeException("Type de strategie "+typeStrategie+" non prevu.");
		}*/
		joueurs.add(new JoueurVirtuel(nom, this, new StrategieAleatoire()));
	}
	
	public void retirerUnJoueur(int indexJoueur) {
		joueurs.remove(indexJoueur);
	}
	
	public void distribuerLesDivinites() {
		ArrayList<Divinite> piocheDivinite = new ArrayList<Divinite>();
		Collections.addAll(piocheDivinite, new Brewalen(), new Drinded(), new Gorpa(), new Gwenghelen(), 
				new Killinstred(), new Llewella(), new PuiTara(), new Romtec(), new Shingua(), new Yarstur());
		Collections.shuffle(piocheDivinite);
		Random rd=new Random();
        for(int i=0;i<this.joueurs.size();i++) {
        	Divinite div = (piocheDivinite.remove(rd.nextInt(piocheDivinite.size())));
        	div.setJoueurLie(this.joueurs.get(i));
            this.joueurs.get(i).setDivinite(div);
        }
	}
	
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
				new Demons(new Dogme[]{Dogme.Humain, Dogme.Nature, Dogme.Mystique}, 14),
				new Demons(new Dogme[]{Dogme.Mystique, Dogme.Humain, Dogme.Chaos}, 15),
				new Demons(new Dogme[]{Dogme.Symboles, Dogme.Mystique, Dogme.Chaos}, 16),
				new Demons(new Dogme[]{Dogme.Mystique, Dogme.Nature, Dogme.Symboles}, 17),
				new Demons(new Dogme[]{Dogme.Mystique, Dogme.Nature, Dogme.Chaos}, 18),
				new Esprits(new Dogme[]{Dogme.Humain, Dogme.Nature, Dogme.Mystique}, 27),
				new Esprits(new Dogme[]{Dogme.Mystique, Dogme.Humain, Dogme.Chaos}, 28),
				new Esprits(new Dogme[]{Dogme.Symboles, Dogme.Mystique, Dogme.Chaos}, 29),
				new Esprits(new Dogme[]{Dogme.Mystique, Dogme.Nature, Dogme.Symboles}, 30),
				new Esprits(new Dogme[]{Dogme.Mystique, Dogme.Nature, Dogme.Chaos}, 31),
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
				new ColereDivine(Origine.Jour, 58),
				new ColereDivine(Origine.Nuit, 59),
				new Apocalypse(Origine.Jour, 76),
				new Apocalypse(Origine.Nuit, 77),
				new Apocalypse(Origine.Neant, 78),
				new Apocalypse(Origine.Aucune, 79),
				new Apocalypse(Origine.Aucune, 80));
		Collections.shuffle(piocheCartes);
		pioche.setPioche(piocheCartes);
	}

	public void distribuerCartes() {
		for(int i = 0; i < this.joueurs.size();i++)
        {
            this.joueurs.get(i).completerMain();;
        }
	}
	
	public void finirUnePartie() {
		int maxCroyants = 0;
		indexGagnant = 0;
		for(int i = 0; i < joueurs.size(); i++) {
			if(joueurs.get(i).getNombreCroyant() > maxCroyants) {
				maxCroyants = joueurs.get(i).getNombreCroyant();
				indexGagnant = i;
			}
		}
		
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
	
	public void setCroyantsRattachables() {
		for(int i = 0; i < tasDeCroyants.size(); i ++) {
			tasDeCroyants.get(i).setRattachable(true);
		}
	}
	
	public void activerCartes() {
		for(int i = table.size()-1; i > 0; i++) {
			table.get(i).poserCarteAction();
			table.remove(i);
		}
	}
	
	public String afficherTable() {
		String retour = "";
		for(int i = 0; i < table.size(); i++) {
			retour += table.get(i).getNom()+"\n";
		}
		return retour;
	}
	
	public String afficherTasCroyants() {
		String retour = "";
		for(int i = 0; i < tasDeCroyants.size(); i++) {
			retour += tasDeCroyants.get(i).getNom()+"\n";
		}
		return retour;
	}
	
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
	
	public String afficherDetailsTasCroyants() {
		String retour = "";
		for(int i = 0; i < tasDeCroyants.size(); i++) {
			retour += tasDeCroyants.get(i)+"\n";
		}
		return retour;
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

	public List<CarteAction> getTable() {
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

	public boolean isPartieFinie() {
		return partieFinie;
	}


	public int getIndexGagnant() {
		return indexGagnant;
	}
}
