package divinae.api.cartes.guide;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;

public class Martyr extends GuideSpirituel {

	

	public Martyr(String nom, Origine origine, Dogme[] dogme) {
		super(nom, origine, "Equivalent � la pose d'une carte Apocalypse.", dogme, 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		Capacite.lancerApocalypse(this.getJoueurLie().getPartie());
		this.getJoueurLie().getPartie().setIndexJoueur1(this.getJoueurLie().getPartie().getJoueurs().indexOf(this.getJoueurLie()));
	}

}
