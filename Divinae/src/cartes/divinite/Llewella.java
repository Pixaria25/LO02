package cartes.divinite;

import cartes.Dogme;
import cartes.Origine;

public class Llewella extends Divinite {

	public Llewella() {
		super("Llewella", Origine.Nuit, "Peut obliger un joueur � poser une carte Apocalypse s'il en poss�de une.", 
				new Dogme[]{Dogme.Chaos, Dogme.Mystique, Dogme.Nature}, 
				"Divinit� machiav�lique et manipulatrice, Killinstred cherche � influencer et contr�ler ses ennemis.");
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub

	}

}
