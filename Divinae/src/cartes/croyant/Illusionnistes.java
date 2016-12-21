package cartes.croyant;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

public class Illusionnistes extends Croyant {

	public Illusionnistes(String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
		super(nom, origine, capacite, dogme, nombreCroyant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		Capacite.copierCapacite(this, this.getJoueurLie().getPartie());
		Capacite.defausser(this, this.getJoueurLie().getPartie());
	}

}
