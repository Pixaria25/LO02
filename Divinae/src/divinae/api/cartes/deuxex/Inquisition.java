package divinae.api.cartes.deuxex;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.DeusEx;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;

public class Inquisition extends DeusEx {

	public Inquisition() {
		super("Inquisition", Origine.Aucune, "Choisissez un des Guides Spirituels d'un autre joueur, et l'un des votres. "
				+ "Lancez le dé de Cosmogonie. Sur Jour, le Guide adverse est sacrifié, sur Nuit le votre est sacrifié, sur Néant rien ne se passe.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		GuideSpirituel SonGp = Capacite.choisirGsp(this.getJoueurLie().getPartie());
		while (SonGp.getJoueurLie()==this.getJoueurLie()){
			System.out.println("Votre choix n'est pas valide, veuillez choisir un guide spirituel ne vous appartenant pas dans un premier temps");
			SonGp = Capacite.choisirGsp(this.getJoueurLie().getPartie());
		}
		GuideSpirituel MonGp = Capacite.choisirSonGsp(this.getJoueurLie(), this.getJoueurLie().getPartie());
		
		this.getJoueurLie().getPartie().getDe().lancerDe();
		Origine influence = this.getJoueurLie().getPartie().getDe().getInfluence();
		
		switch (influence) {
		case Jour : SonGp.getJoueurLie().sacrifierCarte(SonGp);
					System.out.println("Le de est sur la face jour.");
					System.out.println("Sacrifice du guide spirituel adverse.");
		break;
		case Nuit : MonGp.getJoueurLie().sacrifierCarte(MonGp);
					System.out.println("Le de est sur la face nuit.");
					System.out.println("Sacrifice de votre guide spirituel.");
		break;
		default: System.out.println("Le de est sur la face est Néant.");
				 System.out.println("Rien ne se passe.");
			break;
		}
	}
}
