package cartes.deuxex;

import cartes.Capacite;
import cartes.DeusEx;
import cartes.GuideSpirituel;
import cartes.Origine;

public class Concentration extends DeusEx {

	public Concentration() {
		super("Concentration", Origine.Neant, "Vous r�cup�rez un des Guides Spirituels pos�s devant une autre Divinit� "
				+ "et le placez devant vous avec les Croyants qui y sont attach�s.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		GuideSpirituel GpCible = Capacite.choisirGsp(this.getJoueurLie().getPartie());
		while (GpCible.getJoueurLie() == this.getJoueurLie()) {
			System.out.println("Ce guide spirituel vous appartient d�j�, choisissez en un autre !");
			GpCible = Capacite.choisirGsp(this.getJoueurLie().getPartie());
		}
		GpCible.setJoueurLie(this.getJoueurLie());
		System.out.println("Vous avez r�cup�rer le guide spirituel suivant : " + GpCible.getNom());
	}

}
