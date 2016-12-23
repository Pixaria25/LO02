package cartes.croyant;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

public class Illusionnistes extends Croyant {

	public Illusionnistes(String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
		super("Illusionnistes", Origine.Nuit, "Vous b�n�ficiez de la capacit� sp�ciale de sacrifice d'une carte de Croyant "
				+ "appartenant � une autre Divinit�. La carte en question reste en jeu.", new Dogme [] {Dogme.Chaos, Dogme.Humain, Dogme.Symboles}, 4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		Capacite.copierCapacite(this, this.getJoueurLie().getPartie());
		Capacite.defausser(this, this.getJoueurLie().getPartie());
	}

}
