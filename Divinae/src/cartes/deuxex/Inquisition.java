package cartes.deuxex;

import cartes.Action;
import cartes.Carte;
import cartes.Origine;
import partie.De;

public class Inquisition extends Carte implements Action {

	public Inquisition() {
		super("Inquisition", Origine.Aucune, "Choisissez un des Guides Spirituels d'un autre joueur, et l'un des votres. "
				+ "Lancez le d� de Cosmogonie. Sur Jour, le Guide adverse est sacrifi�, sur Nuit le votre est sacrifi�, sur N�ant rien ne se passe.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void poserCarteAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		De de = new De();
		de.lancerDe();
		
	}

}
