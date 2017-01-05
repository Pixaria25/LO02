package divinae.api.cartes.divinite;

import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Llewella extends Divinite {

	public Llewella() {
		super("Llewella", Origine.Nuit, "Peut obliger un joueur à poser une carte Apocalypse s'il en possède une.", 
				new Dogme[]{Dogme.Chaos, Dogme.Mystique, Dogme.Nature}, 
				"Divinité machiavélique et manipulatrice, Killinstred cherche à influencer et contrôler ses ennemis.");
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub

	}

}
