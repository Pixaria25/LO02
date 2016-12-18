package cartes.croyant;

import java.util.Scanner;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

public class Revolutionnaires extends Croyant {

 
  public Revolutionnaires (String nom, Origine origine, String capacite, Dogme[] dogme, int nombreCroyant) {
		super(nom, origine, capacite, dogme, nombreCroyant);

		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
	  boolean stop = false;
	  boolean valChoix;
	  do {
		valChoix = false;  
	  	Capacite.imposerSacrifice("Croyant", this.getJoueurLie().getPartie());
	  	System.out.println("Voulez vous cibler une autre Divinit� ? "
	  			+ "\n 1 : Oui "
	  			+ "\n 2 : Non ");
	  	while (!valChoix) {
		  	Scanner sc = new Scanner(System.in);
			int choix = sc.nextInt();	
			sc.close();
		  	
			switch (choix) {
			case 1 : stop = false;
			break;
			case 2 : stop = true;
					 valChoix = true;
			break;
			default : System.out.println("Choix invalide, veuillez choisir 1 : OUI ou 2 : NON ");
					  valChoix = false;
			}
	  	}
	  } while (!stop);
  }
}
