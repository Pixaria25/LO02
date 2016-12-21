package cartes.guide;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cartes.Capacite;
import cartes.Dogme;
import cartes.GuideSpirituel;
import cartes.Origine;
import cartes.croyant.Croyant;
import cartes.divinite.Divinite;

public class Ascete extends GuideSpirituel {
  
  public Ascete (String nom, Origine origine, String capacite, Dogme [] dogme, int nombreCroyant) {
	 	super(nom, origine, capacite, dogme, nombreCroyant);
		
		// TODO Auto-generated constructor stub
	}


	public void activerCapacite() {
		Divinite divinite = Capacite.choisirDiviniteOuDogme(Dogme.Humain, Dogme.Symboles, this.getJoueurLie().getPartie());
		
		int indice = 0;
		int choix;
		List<Croyant> croyantCiblable = new ArrayList<Croyant>();
		for (int nb = 0; nb < 2 ; nb++){
			System.out.println("Veuillez choisir un croyant à sacrifier : \n");
			for (int i = 0; i < divinite.getJoueurLie().getGuides().size(); i++) {
				for (int j = 0; j < divinite.getJoueurLie().getGuide(i).getCroyantLie().size(); j++) {
					System.out.println(indice + " : " + divinite.getJoueurLie().getGuide(i).getCroyantLie(j).getNom() + "\n");
					croyantCiblable.add(divinite.getJoueurLie().getGuide(i).getCroyantLie(j));
					indice++;
				}
			}
			do {
				System.out.println("(Entrez le nombre compris entre 1 et " + indice + "nombre correspondant à votre choix) ");
				Scanner sc = new Scanner(System.in);
				choix = sc.nextInt();	
				sc.close();
				
			} while (choix < 0 || choix > indice);
			croyantCiblable.get(choix).activerCapacite();
			Capacite.defausser(croyantCiblable.get(choix), this.getJoueurLie().getPartie());
		}
		System.out.println("Les deux croyants choisis ont été sacrifié");
	}
}
