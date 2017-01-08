package divinae.api.cartes.deuxex;

import java.util.Scanner;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.partie.Partie;

public class Phoenix extends DeusEx {

	public Phoenix() {
		super("Phoenix", Origine.Neant, "Permet de bénéficier de la capacité spéciale "
				+ "de l'un de vos Croyants ou Guides Spirituels sans sacrifier la carte.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		Partie partie = this.getJoueurLie().getPartie();
		System.out.println("Utiliser la capacité d'un croyant ou d'un guide ? \n"
				+ "1 : croyant \n"
				+ "2 : guide spirituel \n");
		int choix = 0;
		Scanner sc = new Scanner (System.in);
		do {
			System.out.println("Entrez 1 ou 2 ");
			choix = sc.nextInt();	
		} while (!(choix == 1) | !(choix == 2));
		sc.close();
		
		if (choix == 1) {
			Croyant croyantCapa = Capacite.choisirCroyant(this.getJoueurLie(), partie);
			Capacite.copierCapacite(croyantCapa, partie);
		} else {
			GuideSpirituel GpCapa = Capacite.choisirSonGsp(this.getJoueurLie(), partie);
			Capacite.copierCapacite(GpCapa, partie);
		}
		
		Capacite.defausser(this, partie);
	}

}
