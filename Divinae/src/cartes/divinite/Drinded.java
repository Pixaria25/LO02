package cartes.divinite;

import cartes.Divinite;
import cartes.Dogme;
import cartes.Origine;

public class Drinded extends Divinite {

	public Drinded() {
		super("Drinded", Origine.Jour, "Peut empêcher le sacrifice d'un des Guides Spirituels de n'importe quel joueur.", 
				new Dogme[]{Dogme.Nature, Dogme.Humain, Dogme.Symboles}, 
				"Défenseur des hommes, quelles que soient leurs croyances, Drinded protège les chefs religieux");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub

	}

}
