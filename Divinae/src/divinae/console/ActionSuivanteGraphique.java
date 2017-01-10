package divinae.console;

import java.util.List;
import divinae.api.cartes.types.ActionSuivante;
import divinae.api.cartes.types.Carte;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;

public class ActionSuivanteGraphique implements ActionSuivante
{

	@Override
	public Joueur choisirJoueurCible(Partie partie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuideSpirituel choisirGsp(Partie partie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void renvoyerGsp(List<GuideSpirituel> gspCiblable, Partie partie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Divinite choisirDiviniteOuDogme(Dogme dogme1, Dogme dogme2, Partie partie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuideSpirituel choisirSonGsp(Joueur joueur, Partie partie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Croyant choisirCroyant(Joueur joueur, Partie partie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Origine choisirOrigine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuideSpirituel choisirDiviniteOuGspNonDogme(Dogme dogme, Partie partie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void choisirFaceDe(Carte carte, Partie partie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean choixMultiples(String cible) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int gspOuCroyant() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void messageListe(Joueur joueur, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageRecap(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Croyant choisirTasCroyant(Joueur joueur, Partie partie) {
		// TODO Auto-generated method stub
		return null;
	}



}
