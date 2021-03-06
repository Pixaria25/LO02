package fr.utt.divinae.api.joueur;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import fr.utt.divinae.api.cartes.guide.CarteSacrifiable;
import fr.utt.divinae.api.cartes.types.Carte;
import fr.utt.divinae.api.cartes.types.CarteAction;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Divinite;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.GuideSpirituel;
import fr.utt.divinae.api.cartes.types.Origine;
import fr.utt.divinae.api.cartes.types.Utilitaire;
import fr.utt.divinae.api.partie.Partie;


public class StrategieAleatoire implements Strategie {

	private Random random = new Random();
	
	@Override
	public int jouer(Joueur joueurCourant) {

		int choix = random.nextInt(4);
		return choix;
	}
	
	@Override
	public void demanderInterruption(Joueur joueurCourant) {
		interruption(joueurCourant);
	}

	private void interruption(Joueur joueurCourant) {
		HashSet<Integer> actionsValides = new HashSet<Integer>();

		if(joueurCourant.aDesCartesSansOrigine()) {
			actionsValides.add(1);
		}
		
		if(!joueurCourant.getDivinite().capaciteActivee()) {
			actionsValides.add(2);
		}
		
		int choixInterruption = random.nextInt(10);
		int choixAction = -1;
		
		if (actionsValides.isEmpty()){
			actionsValides.add(0);
		} else if (choixInterruption == 0) {
				choixAction = random.nextInt(actionsValides.size());
		}
		
		switch(choixAction) {
			case 0:
				List<Integer> cartesValides = new ArrayList<Integer>();
				for(int i = 0; i < joueurCourant.getMain().size(); i++) {
					if(joueurCourant.getMain().get(i).getOrigine() == Origine.Aucune) {
						cartesValides.add(i);
					}
				}
				if(!cartesValides.isEmpty()) {
					int carteChoisie = random.nextInt(cartesValides.size());
					joueurCourant.poserCarteAction(cartesValides.get(carteChoisie));
				}
			
			case 1:
				joueurCourant.getDivinite().activerCapacite();
			default:
		}
	}

	@Override
	public List<CarteAction> defausser(List<CarteAction> main) {

		int nombreCartes = random.nextInt(main.size()+1);
		List<CarteAction> cartesADefausser = new ArrayList<CarteAction>();
		for(int i = 0; i < nombreCartes; i++) {
			int numCarte = random.nextInt(main.size());
			cartesADefausser.add(main.get(numCarte));
		}
		return cartesADefausser;

	}

	@Override
	public int choixCarteAction(List<CarteAction> main) {
		int numCarte = random.nextInt(main.size());
		return numCarte;
	}

	@Override
	public CarteSacrifiable choixSacrifice(List<CarteSacrifiable> cartesValides) {
		if (!cartesValides.isEmpty()) {
			int numCarte = random.nextInt(cartesValides.size());
			return cartesValides.get(numCarte);
		}
		
		return null;
	}

	public Joueur choisirJoueurCible(List<Joueur> liste) {
		int choix = random.nextInt(liste.size());
		return liste.get(choix);
	}
	
	public GuideSpirituel choisirGsp (Partie partie){
		List<GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>();
		int indexGsp = 0;
		int indexJoueur = 0;
		while (indexJoueur < partie.getJoueurs().size()) {
			while (indexGsp < partie.getJoueurs().get(indexJoueur).getGuides().size()) {
				gspCiblable.add(partie.getJoueurs().get(indexJoueur).getGuide(indexGsp));
				indexGsp++;
			}
			indexGsp = 0;
			indexJoueur++;
		}

		for (int i = 0; i < gspCiblable.size(); i++) {
			if (gspCiblable.get(i).isProtectionCiblage()) {
				gspCiblable.remove(i);
			}
		}
		
		if (!gspCiblable.isEmpty()) {
			int choix = random.nextInt(gspCiblable.size());
			return gspCiblable.get(choix);
		}
		
		return null;
	}
	
	public Divinite choisirDiviniteOuDogme (Dogme dogme1, Dogme dogme2, Partie partie){
		int choixDivinite = 0;
		List<Divinite> diviniteCiblable = new ArrayList<Divinite>();
		while (choixDivinite < partie.getJoueurs().size()) {
			if (Utilitaire.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(), dogme1, partie)
					|| Utilitaire.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(), dogme2,
							partie)) {
				diviniteCiblable.add(partie.getJoueurs().get(choixDivinite).getDivinite());
			}
			choixDivinite++;
		}
		
		if (!diviniteCiblable.isEmpty()) {
			int choix = random.nextInt(diviniteCiblable.size());
			return diviniteCiblable.get(choix);
		}
		
		return null;
	}
	
	public GuideSpirituel choisirSonGsp (Joueur joueur, Partie partie){
		List<GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>();
		int indexJoueur = partie.getJoueurs().indexOf(joueur);

		for (int i = 0; i < partie.getJoueurs().get(indexJoueur).getGuides().size(); i++) {
			gspCiblable.add(partie.getJoueurs().get(indexJoueur).getGuide(i));
		}

		for (int i = 0; i < gspCiblable.size(); i++) {
			if (gspCiblable.get(i).isProtectionCiblage()) {
				gspCiblable.remove(i);
			}
		}
		
		if (!gspCiblable.isEmpty()) {
			int choix = random.nextInt(gspCiblable.size());
			return gspCiblable.get(choix);
		}
		
		return null;
	}
	
	public Croyant choisirCroyant (Joueur joueur, Partie partie){
		List<Croyant> croyantCiblable = new ArrayList<Croyant>();
		for (int i = 0; i < joueur.getGuides().size(); i++) {
			for (int j = 0; j < joueur.getGuide(i).getCroyantLie().size(); j++) {
				croyantCiblable.add(joueur.getGuide(i).getCroyantLie(j));
			}
		}
		
		if (!croyantCiblable.isEmpty()) {
			int choix = random.nextInt(croyantCiblable.size());
			return croyantCiblable.get(choix);
		}
		return null;
	}
	
	public Origine choisirOrigine (){
		int choix = random.nextInt(3);
		return Origine.values()[choix];
	}
	
	public GuideSpirituel choisirDiviniteOuGspNonDogme (Dogme dogme, Partie partie){
		int choixDivinite = 0;
		List<GuideSpirituel> gspCiblable = new ArrayList<GuideSpirituel>();

		while (choixDivinite < partie.getJoueurs().size()) {
			if (!(Utilitaire.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(), dogme,
					partie))) {
				gspCiblable.addAll(partie.getJoueurs().get(choixDivinite).getGuides());
			} else {
				for (int choixGuide = 0; choixGuide < partie.getJoueurs().get(choixDivinite).getGuides()
						.size(); choixGuide++) {
					if (!(Utilitaire.comparerDogme(partie.getJoueurs().get(choixDivinite).getDivinite().getDogme(), dogme,
							partie))) {
						gspCiblable.add(partie.getJoueurs().get(choixDivinite).getGuide(choixGuide));
					}
				}
			}
			choixDivinite++;
		}

		for (int i = 0; i < gspCiblable.size(); i++) {
			if (gspCiblable.get(i).isProtectionCiblage()) {
				gspCiblable.remove(i);
			}
		}
		if (!gspCiblable.isEmpty()) {
			int choix = random.nextInt(gspCiblable.size());
			return gspCiblable.get(choix);
		}
		
		return null;
	}
	
	public void choisirFaceDe (Carte carte,Partie partie){
		int choix = random.nextInt(3);
		partie.getDe().setInfluence(Origine.values()[choix]);
	}
	
	public boolean choixMultiples (String cible){
		boolean choix = random.nextBoolean();
		return choix;
	}
	
	public int gspOuCroyant (){
		int choix = random.nextInt(2);
		return choix;
	}
	
	public void messageRecap (String message){
		System.out.println(message);
	}

	public Croyant choisirTasCroyant(Joueur joueur, Partie partie){
		int choix = random.nextInt(partie.getTasDeCroyants().size());
		return partie.getTasDeCroyants(choix);
	}
	
	public GuideSpirituel choisirGspRenvoye (List <GuideSpirituel> gspCiblable){
		if (!gspCiblable.isEmpty()) {
			int choix = random.nextInt(gspCiblable.size() + 1);
			return gspCiblable.get(choix);
		}
		return null;
	}

	@Override
	public int choisirFaceDe(Joueur joueur) {
		int choix;
		Origine origineDV = joueur.getDivinite().getOrigine();
		switch (origineDV) {
			case Jour :choix = 1;
				break;
			case Nuit : choix = 2;
				break;	
			default : choix = 3;
		}
		return choix;
	}
}
