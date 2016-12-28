package cartes.deuxex;

import cartes.Capacite;
import cartes.DeusEx;
import cartes.GuideSpirituel;
import cartes.Origine;

public class Inquisition extends DeusEx {

	public Inquisition() {
		super("Inquisition", Origine.Aucune, "Choisissez un des Guides Spirituels d'un autre joueur, et l'un des votres. "
				+ "Lancez le d� de Cosmogonie. Sur Jour, le Guide adverse est sacrifi�, sur Nuit le votre est sacrifi�, sur N�ant rien ne se passe.");
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
					System.out.println("Sacrifice du guide spirituel adverse.");
		break;
		case Nuit : MonGp.getJoueurLie().sacrifierCarte(MonGp);
					System.out.println("Sacrifice de votre guide spirituel.");
		break;
		default: System.out.println("Rien ne se passe la face est N�ant.");
			break;
		}
	}
}
