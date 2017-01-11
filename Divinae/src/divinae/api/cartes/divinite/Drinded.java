package divinae.api.cartes.divinite;

import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Drinded extends Divinite {

	public Drinded() {
		super("Drinded", Origine.Jour, "Peut empêcher le sacrifice d'un des Guides Spirituels de n'importe quel joueur.", 
				new Dogme[]{Dogme.Nature, Dogme.Humain, Dogme.Symboles}, 
				"Défenseur des hommes, quelles que soient leurs croyances, Drinded protège les chefs religieux", 82);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		super.activerCapacite();
	}

}
