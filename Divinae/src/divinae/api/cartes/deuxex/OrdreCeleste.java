package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;

public class OrdreCeleste extends DeusEx {

	public OrdreCeleste() {
		super("Ordre Celeste", Origine.Jour, "Vous récupérez un des Guides Spirituels posés devant "
				+ "une autre Divinité et le placez devant vous avec les Croyants qui y sont attachés.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		GuideSpirituel GpVole = Capacite.getActionSuivante().choisirGsp(this.getJoueurLie().getPartie());
		while (GpVole.getCroyantLie() == this.getJoueurLie()){
			GpVole = Capacite.getActionSuivante().choisirGsp(this.getJoueurLie().getPartie());
		}
		GpVole.setJoueurLie(this.getJoueurLie());
	}

}
