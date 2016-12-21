package cartes.deuxex;

import cartes.Action;
import cartes.Carte;
import cartes.Origine;

public class Diversion extends Carte implements Action {

	public Diversion() {
		super("Diversion", Origine.Nuit, "Prenez 3 cartes dans la main d'un autre joueur "
				+ "et incluez-les à votre main");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void poserCarteAction() {
		// TODO Auto-generated method stub
		activerCapacite();
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		
	}

}
