package cartes.deuxex;

import cartes.Capacite;
import cartes.DeusEx;
import cartes.GuideSpirituel;
import cartes.Origine;

public class OrdreCeleste extends DeusEx {

	public OrdreCeleste() {
		super("Ordre Celeste", Origine.Jour, "Vous récupérez un des Guides Spirituels posés devant "
				+ "une autre Divinité et le placez devant vous avec les Croyants qui y sont attachés.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		GuideSpirituel GpVole = Capacite.choisirGsp(this.getJoueurLie().getPartie());
		while (GpVole.getCroyantLie() == this.getJoueurLie()){
			System.out.println("Veuillez choisir un autre guide spirituel que l'un des votre pour cette effet.");
			GpVole = Capacite.choisirGsp(this.getJoueurLie().getPartie());
		}
		GpVole.setJoueurLie(this.getJoueurLie());
	}

}
