package fr.utt.divinae.api.cartes.deuxex;

import fr.utt.divinae.api.cartes.types.DeusEx;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;

public class OrdreCeleste extends DeusEx {

	public OrdreCeleste() {
		super("Ordre Celeste", Origine.Jour, "Vous r�cup�rez un des Guides Spirituels pos�s devant "
				+ "une autre Divinit� et le placez devant vous avec les Croyants qui y sont attach�s.", 61);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		GuideSpirituel gpVole =  getJoueurLie().choisirGsp();
		if (gpVole != null) {
			gpVole.setJoueurLie(this.getJoueurLie());
		}
	}

}
