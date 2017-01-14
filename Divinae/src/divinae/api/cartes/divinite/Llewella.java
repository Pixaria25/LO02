package divinae.api.cartes.divinite;

import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Llewella extends Divinite {

	public Llewella() {
		super("Llewella", Origine.Nuit, "Peut obliger un joueur � poser une carte Apocalypse s'il en poss�de une.", 
				new Dogme[]{Dogme.Chaos, Dogme.Mystique, Dogme.Nature}, 
				"Divinit� machiav�lique et manipulatrice, Killinstred cherche � influencer et contr�ler ses ennemis.", 85);
	}

	@Override
	public void activerCapacite() {
		super.activerCapacite();
	}

}
