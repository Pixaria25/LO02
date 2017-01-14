package divinae.swing.controleur;

import java.util.List;
import divinae.api.cartes.types.ActionSuivante;
import divinae.api.cartes.types.CarteAction;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Divinite;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.GuideSpirituel;
import divinae.api.cartes.types.Origine;
import divinae.api.joueur.Joueur;
import divinae.api.partie.Partie;
import divinae.swing.vue.SelectionnerCarteDialog;

public class ActionSuivanteGraphique implements ActionSuivante
{

	@Override
	public Joueur choisirJoueurCible(List<Joueur> liste) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuideSpirituel choisirGsp(Joueur joueur, Partie partie) {
		// TODO Auto-generated method stub
		return null;
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
	public int choisirFaceDe(Joueur joueur, Partie partie) {
		// TODO Auto-generated method stub
		return 0;
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
	
/**
	@Override
	public void messageListe(String message) {
		// TODO Auto-generated method stub
	}
**/		
	

	@Override
	public void messageRecap(String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Croyant choisirTasCroyant(Joueur joueur, Partie partie) {
		List<? extends CarteAction> listeCartes = partie.getTasDeCroyants();
		SelectionnerCarteDialog dialog = new SelectionnerCarteDialog(null, "Sacrifier une carte", listeCartes);
		dialog.setVisible(true);
		int choixCarte = dialog.getChoixCarte(); 
		return partie.getTasDeCroyants().get(choixCarte);
	}

	@Override
	public GuideSpirituel choisirGspRenvoye(List<GuideSpirituel> gspCiblable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void demanderInterruption() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interruption() {
		// TODO Auto-generated method stub
		
	}

	



}
