package cartes.divinite;

import cartes.Divinite;
import cartes.Dogme;
import cartes.Origine;

public class Killinstred extends Divinite {

	public Killinstred() {
		super("Killinstred", Origine.Nuit, "Peut obliger un joueur � poser une carte Apocalypse "
				+ "s'il en poss�de une.", new Dogme[]{Dogme.Nature, Dogme.Mystique, Dogme.Chaos}, 
				"Divinit� machiav�lique et manipulatrice, "
				+ "Killinstred cherche � influencer et contr�ler ses ennemis.");
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub

	}

}
