package cartes.deuxex;

import cartes.DeusEx;
import cartes.Origine;
import partie.De;

public class Inquisition extends DeusEx {

	public Inquisition() {
		super("Inquisition", Origine.Aucune, "Choisissez un des Guides Spirituels d'un autre joueur, et l'un des votres. "
				+ "Lancez le dé de Cosmogonie. Sur Jour, le Guide adverse est sacrifié, sur Nuit le votre est sacrifié, sur Néant rien ne se passe.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		De de = new De();
		de.lancerDe();
		
	}

}
