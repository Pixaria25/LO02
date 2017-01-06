package divinae.api.cartes.divinite;

import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Brewalen extends Divinite {

	public Brewalen() {
		super("Brewalen", Origine.Jour, "Peut emp�cher l'utilisation d'une carte Apocalypse. "
				+ "La carte est d�fauss�e.", 
				new Dogme[]{Dogme.Nature, Dogme.Humain, Dogme.Mystique}, 
				"Premier enfant du Jour, Brewalen cherche � repr�senter une certaine image de l'harmonie, "
				+ "et tente de mettre en place un statu quo entre les Divinit�s.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		super.activerCapacite();
		
	}

}
