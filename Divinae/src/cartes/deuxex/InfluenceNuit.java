package cartes.deuxex;

import cartes.DeusEx;
import cartes.Origine;

public class InfluenceNuit extends DeusEx {

	public InfluenceNuit(String nom, Origine origine, String capacite) {
		super("Influence Nuit", Origine.Nuit, "Annule la capacit� sp�ciale d'une carte d'Action d'Origine Jour ou N�ant.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activerCapacite() {
		//Mettre dans le processuce de poserune carte un if "si annulation = true alors pas d'activation de capa et suppr last carte of Table" et ici setAnnulation = true" 
	}

}
