package cartes.divinite;

import cartes.Divinite;
import cartes.Dogme;
import cartes.Origine;

public class Killinstred extends Divinite {

	public Killinstred() {
		super("Killinstred", Origine.Nuit, "Peut obliger un joueur à poser une carte Apocalypse "
				+ "s'il en possède une.", new Dogme[]{Dogme.Nature, Dogme.Mystique, Dogme.Chaos}, 
				"Divinité machiavélique et manipulatrice, "
				+ "Killinstred cherche à influencer et contrôler ses ennemis.");
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub

	}

}
