package divinae.api.partie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

import divinae.api.cartes.divinite.*;
import divinae.api.cartes.types.*;
import divinae.api.joueur.Joueur;
import divinae.api.joueur.JoueurVirtuel;
import divinae.api.joueur.Strategie;
import divinae.api.joueur.StrategieDefensive;
import divinae.api.joueur.StrategieEquilibre;
import divinae.api.joueur.StrategieOffensive;

public class Partie {
	
	private int indexJoueur1;
	private List<Joueur> joueurs;
	private List<Carte> table;
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
		this.table = new ArrayList<Carte>();
		this.nombreTour = 1;
		this.pioche = new Pioche();
		this.defausse = new Defausse();
		this.de = new De();
		this.partieFinie = false;
	}
	
	
	public void debuterUnTour() {
		de.lancerDe();
		ajoutPoint();
	}
	
	public void ajouterUnJoueurReel(String nom) {
		joueurs.add(new Joueur(nom, this));
	}
	
	public void ajouterUnJoueurVirtuel(String nom, TypeStrategie typeStrategie) {
		TypeStrategie typeStrategieEffectif = typeStrategie;
		if(typeStrategie == TypeStrategie.ALEATOIRE) {
			Random random = new Random();
			int choixStrat = 0;
			choixStrat = random.nextInt(3);
			typeStrategieEffectif = TypeStrategie.values()[choixStrat];
		}
		switch (typeStrategieEffectif) {
		case OFFENSIVE:
			joueurs.add(new JoueurVirtuel(nom, this, new StrategieOffensive()));
			break;

		case DEFENSIVE:
			joueurs.add(new JoueurVirtuel(nom, this, new StrategieDefensive()));
			break;
			
		case EQUILIBRE:
			joueurs.add(new JoueurVirtuel(nom, this, new StrategieEquilibre()));
			break;

		default: 
			throw new RuntimeException("Type de strategie "+typeStrategie+" non prevu.");
		}
	}
	
	public void retirerUnJoueur(int indexJoueur) {
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

	public boolean isPartieFinie() {
		return partieFinie;
	}


	public int getIndexGagnant() {
		return indexGagnant;
	}
}
