package cartes.deuxex;

import cartes.DeusEx;
import cartes.Origine;

public class InfluenceNulle extends DeusEx {

	public InfluenceNulle() {
		super("Influence Nulle", Origine.Aucune, "Annule la capacité spéciale d'une autre carte d'Action.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		//Mettre dans le processuce de poserune carte un if "si annulation = true alors pas d'activation de capa et suppr last carte of Table" et ici setAnnulation = true" 

	}

}
