package divinae.swing.controleur;

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
	public Joueur choisirJoueurCible(List<Joueur> liste) {

		return null;
	}

	@Override
	public GuideSpirituel choisirGsp(Joueur joueur, Partie partie) {

		return null;
	}

	
	@Override
	public Divinite choisirDiviniteOuDogme(Dogme dogme1, Dogme dogme2, Partie partie) {

		return null;
	}

	@Override
	public GuideSpirituel choisirSonGsp(Joueur joueur, Partie partie) {

		return null;
	}

	@Override
	public Croyant choisirCroyant(Joueur joueur, Partie partie) {

		return null;
	}

	@Override
	public Origine choisirOrigine() {
	
		return null;
	}

	@Override
	public GuideSpirituel choisirDiviniteOuGspNonDogme(Dogme dogme, Partie partie) {

		return null;
	}

	@Override
	public void choisirFaceDe(Carte carte, Partie partie) {

		
	}

	@Override
	public boolean choixMultiples(String cible) {

		return false;
	}

	@Override
	public int gspOuCroyant() {

		return 0;
	}
	
/**
	@Override
	public void messageListe(String message) {

	}
**/		
	

	@Override
	public void messageRecap(String message) {

		
	}

	@Override
	public Croyant choisirTasCroyant(Joueur joueur, Partie partie) {

		return null;
	}

	@Override
	public GuideSpirituel choisirGspRenvoye(List<GuideSpirituel> gspCiblable) {

		return null;
	}

	@Override
	public void demanderInterruption() {

	}

	@Override
	public void interruption() {

		
	}



}
