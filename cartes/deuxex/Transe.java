package cartes.deuxex;

import cartes.Action;
import cartes.Carte;
import cartes.Origine;

public class Transe extends Carte implements Action {

	public Transe() {
		super("Transe", Origine.Aucune, "Permet de r�cup�rer les effets b�n�fiques d'une carte d'Action pos�e par une autre Divinit�. "
				+ "S'il s'agit d'une carte Croyants ou Guide Spirituel, vous posez la carte devant vous.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void poserCarteAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub

	}

}