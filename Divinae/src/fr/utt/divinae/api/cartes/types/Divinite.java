package fr.utt.divinae.api.cartes.types;

/**
 * La classe Divinite represente les cartes Divinite.
 * @author Thomas, Abraham
 *
 */
public abstract class Divinite extends Carte {

	private Dogme[] dogme;
	private String description;
	private boolean capaciteActivee;
	
	public Divinite (String nom, Origine origine, String capacite,Dogme[] dogme, String description, int id) {
		super(nom, "Divinite", origine, capacite, id);
		this.dogme = dogme;
		this.description = description;
		this.capaciteActivee = false;
		
	}
	
	public Dogme[] getDogme() {
		return dogme;
	}

	public boolean capaciteActivee() {
		return capaciteActivee;
	}

	@Override
	public void activerCapacite() {
		capaciteActivee = true;
		
	}
	
	@Override
	public String toString() {
		return"Divinite "+super.toString()+ "\n Dogme: " + dogmeToString(dogme) +"\n Description: " + description;
	}
}
