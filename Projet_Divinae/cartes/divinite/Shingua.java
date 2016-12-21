package cartes.divinite;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

public class Shingua extends Divinite {

	public Shingua() {
		super("Shingua", Origine.Aube, "R�cup�re autant de points d'Action suppl�mentaires d'Origine N�ant "
				+ "que le nombre de Guides Spirituels que la Divinit� poss�de.", 
				new Dogme[]{Dogme.Humain, Dogme.Mystique, Dogme.Chaos}, 
				"Perverse et retorse, Shingva est une Divinit� que toutes les autres d�testent.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		super.activerCapacite();
		Capacite.imposerSacrifice(joueur, carte, partie);
	}

}
