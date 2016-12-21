package partie;

import java.util.Scanner;

public class Divinae {

	public static void main(String[] args) {
		System.out.println("Bienvenue dans Divinae !");
		System.out.println("1-Creer une partie\n2-Quitter");
		Scanner scanner = new Scanner(System.in);
		int choix = 0;
		do {
			System.out.println("Entrer un nombre valide.");
			choix = scanner.nextInt();
			
		} while (choix < 1 || choix > 2);

		switch(choix) {
		case 1: 
			Partie partie = new Partie();
			partie.lancerUnePartie();
		case 2:
			System.out.println("Au revoir !");
		}
		
		scanner.close();
	}

}
