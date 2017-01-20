package fr.utt.divinae.api.cartes.types;

import fr.utt.divinae.api.cartes.guide.CarteSacrifiable;

/**
 * La classe Croyant modelise les cartes Croyant.
 * @author Thomas, Abraham
 *
 */
public abstract class Croyant extends CarteSacrifiable {
	
	private Dogme[] dogme;
	private int nombrePrieres;
	private GuideSpirituel guideLie;
	private boolean rattachable;
	
	public Croyant(String nom, Origine origine, String capacite, Dogme[] dogme, int nombrePrieres, int id) {
		super(nom, "Croyant", origine, capacite, id);
	    this.dogme = dogme;
		this.nombrePrieres = nombrePrieres;
		this.guideLie = null;
		this.rattachable = false;
	}

	@Override
	public void poserCarteAction() {
		getJoueurLie().messageRecap(getJoueurLie().getNom() + " joue " + getNom());
		getJoueurLie().getMain().remove(this);
		getJoueurLie().getPartie().ajoutTasDCroyants(this);
		setJoueurLie(null);
	}
	
	@Override
	public void sacrifice() {
		Utilitaire.majPointsCroyant(this, -nombrePrieres);
		super.sacrifice();
	}
	
	@Override
	public void mort() {
		setGuideLie(null);
		setRattachable(false);
		super.mort();
	}
	
	//Getters et setters
	public GuideSpirituel getGuideLie() {
		return guideLie;
	}
	
	public void setGuideLie(GuideSpirituel guideLie) {
		this.guideLie = guideLie;
	}

	public Dogme[] getDogme() {
		return dogme;
	}

	public int getNombrePrieres() {
		return nombrePrieres;
	}
	
	public boolean isRattachable() {
		return rattachable;
	}

	public void setRattachable(boolean rattachable) {
		this.rattachable = rattachable;
	}

	@Override
	public String toString() {
		return "Croyant " +super.toString()+ "\n Dogme: " + dogmeToString(dogme) + "\n Prieres: "+nombrePrieres;
	}
	
}
