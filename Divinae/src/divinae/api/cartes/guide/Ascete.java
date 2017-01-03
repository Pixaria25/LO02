package divinae.api.cartes.guide;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;

public class Ascete extends GuideSpirituel {
  
  public Ascete () {
	 	super("Ascete", Origine.Nuit, "Sacrifie 2 cartes Croyants d'une Divinit� ayant le Dogme Humain ou Symboles."
	 			+ " Les capacit�s sp�ciales sont jou�es normalement.",
	 			new Dogme [] {Dogme.Humain,Dogme.Symboles}, 1);
		
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		Divinite divinite = Capacite.choisirDiviniteOuDogme(Dogme.Humain, Dogme.Symboles, this.getJoueurLie().getPartie());
		
		int indice = 0;
		int choix;
		List<Croyant> croyantCiblable = new ArrayList<Croyant>();
		for (int nb = 0; nb < 2 ; nb++){
			System.out.println("Veuillez choisir un croyant � sacrifier : \n");
			for (int i = 0; i < divinite.getJoueurLie().getGuides().size(); i++) {
				for (int j = 0; j < divinite.getJoueurLie().getGuide(i).getCroyantLie().size(); j++) {
					System.out.println(indice + " : " + divinite.getJoueurLie().getGuide(i).getCroyantLie(j).getNom() + "\n");
					croyantCiblable.add(divinite.getJoueurLie().getGuide(i).getCroyantLie(j));
					indice++;
				}
			}
			do {
				System.out.println("(Entrez le nombre compris entre 1 et " + indice + "nombre correspondant � votre choix) ");
				Scanner sc = new Scanner(System.in);
				choix = sc.nextInt();	
				sc.close();
				
			} while (choix < 0 || choix > indice);
			croyantCiblable.get(choix).activerCapacite();
			Capacite.defausser(croyantCiblable.get(choix), this.getJoueurLie().getPartie());
		}
		System.out.println("Les deux croyants choisis ont �t� sacrifi�");
	}
}