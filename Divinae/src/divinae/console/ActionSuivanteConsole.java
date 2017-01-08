package divinae.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import divinae.api.cartes.types.ActionSuivante;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public class ActionSuivanteConsole implements ActionSuivante
{
	@Override
	public Joueur choisirJoueurCible(Partie partie)
	{
		System.out.println("Veuillez sélectionner le Joueur à cibler par cette compétence :"+ "\n");
		int choix = 0;
		int indice=0;
		do {
			System.out.println(indice +" : " + partie.getJoueurs().get(indice).getNom());
			indice++;
		} while (indice <=  partie.getJoueurs().size());
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("(Entrez le nombre compris entre 1 et " + partie.getJoueurs().size() + "nombre correspondant à votre choix) ");

			choix = sc.nextInt();

		} while (choix < 0 | choix >  partie.getJoueurs().size());
		sc.close();

		Joueur joueur = partie.getJoueurs().get(choix);
		System.out.println("Vous avez ciblé " + joueur.getNom());

		while (joueur == null) {
			joueur = choisirJoueurCible(partie);
			if(joueur.getGuides().isEmpty()) {
				System.out.println("Veuillez choisir un autre joueur, celui-ci n'a pas de Guides Spirituels");
				joueur = null;
			}
		}
		return joueur;
	}

	@Override
	public GuideSpirituel choisirGuideSpirituelCible(Joueur joueur, String vise)
	{
		System.out.println("Quel " + vise + "voulez vous cibler ?");
		for (int i = 0; i < joueur.getGuides().size(); i++ ) {
			System.out.println(i + " : " + joueur.getGuide(i).getNom() + "\n");
		}
		int choix;
		do {
			System.out.println("Entrez un nombre compris entre 0 et " + (joueur.getGuides().size() - 1) + ", nombre correspondant à votre choix \n");
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			sc.close();

		} while (choix < 0 || choix > joueur.getGuides().size());

		return joueur.getGuide(choix);
	}

	@Override
	public Croyant choisirCroyantCible(Joueur joueur, String vise)
	{
		int compteurCroyants=0;
		List<Croyant> croyantCiblable = new ArrayList<Croyant>();
		System.out.println("Quel " + vise + "voulez vous cibler ?");
		for (int i = 0; i < joueur.getGuides().size(); i++) {
			for (int j = 0; j < joueur.getGuide(i).getCroyantLie().size(); i++) {
				System.out.println(i + " : " + joueur.getGuide(i).getCroyantLie(j).getNom() + "\n");
				croyantCiblable.add(joueur.getGuide(i).getCroyantLie(j));
				compteurCroyants++;
			}
		}
		int choix;
		do {
			System.out.println("Entrez un nombre compris entre 0 et " + compteurCroyants + ", nombre correspondant à votre choix \n");
			Scanner sc = new Scanner(System.in);
			choix = sc.nextInt();
			sc.close();

		} while (choix < 0 || choix > joueur.getGuides().size());

		return croyantCiblable.get(choix);
	}
}
