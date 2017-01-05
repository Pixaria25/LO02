package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;

public class Concentration extends DeusEx {

	public Concentration() {
		super("Concentration", Origine.Neant, "Vous récupérez un des Guides Spirituels posés devant une autre Divinité "
				+ "et le placez devant vous avec les Croyants qui y sont attachés.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		GuideSpirituel GpCible = Capacite.choisirGsp(this.getJoueurLie().getPartie());
		while (GpCible.getJoueurLie() == this.getJoueurLie()) {
			System.out.println("Ce guide spirituel vous appartient déjà, choisissez en un autre !");
			GpCible = Capacite.choisirGsp(this.getJoueurLie().getPartie());
		}
		GpCible.setJoueurLie(this.getJoueurLie());
		System.out.println("Vous avez récupérer le guide spirituel suivant : " + GpCible.getNom());
	}

}
