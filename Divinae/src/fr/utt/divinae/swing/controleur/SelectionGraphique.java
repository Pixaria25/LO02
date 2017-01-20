package fr.utt.divinae.swing.controleur;

import java.util.ArrayList;
import java.util.List;

import fr.utt.divinae.api.cartes.types.Carte;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Divinite;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;
import fr.utt.divinae.api.cartes.types.Utilitaire;
import fr.utt.divinae.api.joueur.Joueur;
import fr.utt.divinae.api.joueur.Selection;
import fr.utt.divinae.api.partie.Partie;
import fr.utt.divinae.swing.vue.SelectionnerCarteDialog;
import fr.utt.divinae.swing.vue.SelectionnerJoueurDialog;
import fr.utt.divinae.swing.vue.SelectionnerOuiNonDialog;
import fr.utt.divinae.swing.vue.SelectionnerSymboleDialog;
import fr.utt.divinae.swing.vue.VueJeu;

public class SelectionGraphique implements Selection
{
	private VueJeu vueJeu;
	public SelectionGraphique(VueJeu vueJeu) {
		this.vueJeu = vueJeu;
	}
	
	@Override
	public void demanderInterruption(Joueur joueurCourant) {
		SelectionnerOuiNonDialog dialog = new SelectionnerOuiNonDialog(null, "Voulez-vous intervenir ?");
		dialog.setVisible(true);
		if (dialog.getChoix()) {
			interruption(joueurCourant);
		}
	}

	@Override
	public void interruption(Joueur joueurCourant) {
		List<Carte> liste = new ArrayList<>();
		for(int i = 0; i < joueurCourant.getMain().size(); i++) {
			if(joueurCourant.getMain().get(i).getOrigine() == Origine.Aucune) {
				liste.add(joueurCourant.getMain().get(i));
			}
		}
		liste.add(joueurCourant.getDivinite());
		int choix = selectionnerCarte("Jouer 1 carte ou activer la divinité", liste);
		if (choix == liste.size()-1) {
			joueurCourant.getDivinite().activerCapacite();
		} else {
			int choixCarte = -1;
			int index = -1;
			for(int i = 0; i < joueurCourant.getMain().size(); i++) {
				if(joueurCourant.getMain().get(i).getOrigine() == Origine.Aucune) {
					index++;
				}
				if (index == choix) {
					choixCarte = i;
					break;
				}
			}
			if (choixCarte == -1) {
				vueJeu.alert("Choix d'interruption invalide");
			} else {
				joueurCourant.poserCarteAction(choixCarte);
			}
		}
	}

	@Override
	public Joueur choisirJoueurCible(List<Joueur> liste) {
		Joueur result = null;
		if (liste.size() > 0) {
			if (liste.size() == 1) {
				result = liste.get(0);
			} else {
				SelectionnerJoueurDialog dialog = new SelectionnerJoueurDialog(null, "Choisir la carte à cibler par cette compétence", liste);
				dialog.setVisible(true);
				result = dialog.getChoixJoueur();
			}
		}
		return result;
	}

	@Override
	public GuideSpirituel choisirGsp(Joueur joueur, Partie partie) {
		List<GuideSpirituel> gspCiblable = Utilitaire.getGspCiblables(joueur, partie);
		return (gspCiblable.size() > 0) ? gspCiblable.get(selectionnerCarte("Sélectionner le guide à cibler", gspCiblable)) : null;
	}

	
	@Override
	public Divinite choisirDiviniteOuDogme(Dogme dogme1, Dogme dogme2, Partie partie) {
		List<Divinite> diviniteCiblable = Utilitaire.getDiviniteOuDogme(dogme1, dogme2, partie);
		return (diviniteCiblable.size() > 0) ? diviniteCiblable.get(selectionnerCarte("Sélectionner la carte à cibler", diviniteCiblable)) : null;
	}

	@Override
	public GuideSpirituel choisirSonGsp(Joueur joueur, Partie partie) {
		List<GuideSpirituel> gspCiblable = Utilitaire.getSonGsp(joueur, partie);
		return (gspCiblable.size() > 0) ? gspCiblable.get(selectionnerCarte("Sélectionner le guide à cibler", gspCiblable)) : null;
	}

	@Override
	public Croyant choisirCroyant(Joueur joueur, Partie partie) {
		List<Croyant> croyantCiblable = Utilitaire.getCroyant(joueur, partie);
		return (croyantCiblable.size() > 0) ? croyantCiblable.get(selectionnerCarte("Sélectionner le croyant à cibler", croyantCiblable)) : null;
	}

	@Override
	public Origine choisirOrigine() {
		return Origine.values()[selectionnerSymbole("Choisir l'origine des point d'action à gagner", "de", 3)];
	}

	@Override
	public GuideSpirituel choisirDiviniteOuGspNonDogme(Dogme dogme, Partie partie) {
		List<GuideSpirituel> gspCiblable = Utilitaire.getDiviniteOuGspNonDogme(dogme, partie);
		return (gspCiblable.size() > 0) ? gspCiblable.get(selectionnerCarte("Sélectionner le guide à cibler", gspCiblable)) : null;
	}

	@Override
	public boolean choixMultiples(String cible) {
		SelectionnerOuiNonDialog dialog = new SelectionnerOuiNonDialog(null, "Choisir cibler un(e) autre " + cible);
		dialog.setVisible(true);
		return dialog.getChoix(); 
	}

	@Override
	public int gspOuCroyant() {
		return selectionnerSymbole("Choisir Croyant ou Guide spirituel", "croyantgsp", 2);
	}

	@Override
	public void messageRecap(String message) {
		vueJeu.afficherMessage(message);
	}

	@Override
	public Croyant choisirTasCroyant(Joueur joueur, Partie partie) {
		return (partie.getTasDeCroyants().size() > 0) ? partie.getTasDeCroyants().get(selectionnerCarte("Sélectionner le croyant à cibler", partie.getTasDeCroyants())) : null;
	}

	@Override
	public GuideSpirituel choisirGspRetire(List<GuideSpirituel> gspCiblable) {
		return (gspCiblable.size() > 0) ? gspCiblable.get(selectionnerCarte("Sélectionner le guide à cibler", gspCiblable)) : null;
	}

	@Override
	public int choisirFaceDe(Joueur joueur, Partie partie) {
		return selectionnerSymbole("Choisir la face de dé", "de", 3);
	}

	private int selectionnerSymbole(String titre, String symbolePrefixe, int max) {
		SelectionnerSymboleDialog dialog = new SelectionnerSymboleDialog(null, "Choisir la face de dé", "de", max);
		dialog.setVisible(true);
		int choix = dialog.getChoix(); 
		return choix;
	}
	
	private int selectionnerCarte(String titre, List<? extends Carte> listeCartes) {
		if (listeCartes.size() < 1) {
			throw new RuntimeException("Ne peut pas sacrifier car aucune carte n'est disponible.");
		}
		SelectionnerCarteDialog dialog = new SelectionnerCarteDialog(null, titre, listeCartes);
		dialog.setVisible(true);
		int choixCarte = dialog.getChoixCarte(); 
		return choixCarte;
	}

	@Override
	public void menu(Joueur joueurCourant) {
		
	}
}
