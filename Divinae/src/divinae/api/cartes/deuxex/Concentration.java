package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.Origine;

public class Concentration extends DeusEx {

	public Concentration() {
		super("Concentration", Origine.Neant, "Vous r�cup�rez un des Guides Spirituels pos�s devant une autre Divinit� "
				+ "et le placez devant vous avec les Croyants qui y sont attach�s.", 64);
	}

	@Override
	public void activerCapacite() {
		Capacite.recupererUnGsp(getJoueurLie());
	}
}
