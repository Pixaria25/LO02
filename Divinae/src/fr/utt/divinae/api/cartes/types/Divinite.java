package fr.utt.divinae.api.cartes.types;

public abstract class Divinite extends Carte {

	private Dogme[] dogme;
	private String description;
	private boolean capaciteActivee;
	
	public Divinite (String nom, Origine origine, String capacite,Dogme[] dogme, String description, int id) {
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		capaciteActivee = true;
		
	}
	
	@Override
	public String toString() {
		return"Divinite "+super.toString()+ "\n Dogme: " + dogmeToString(dogme) +"\n Description: " + description;
	}
}
