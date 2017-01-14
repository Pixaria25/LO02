package divinae.swing;

import divinae.swing.controleur.ControleurJeu;
import divinae.swing.modele.ModeleJeu;
import divinae.swing.vue.VueJeu;

public class DivinaeGraphique {

	public DivinaeGraphique() {
		ModeleJeu modeleJeu = new ModeleJeu();
		ControleurJeu controleurJeu = new ControleurJeu(modeleJeu);
		VueJeu vueJeu = new VueJeu(controleurJeu, modeleJeu);
		modeleJeu.addObserver(vueJeu);
		controleurJeu.initialiserJeu(vueJeu);
		controleurJeu.jouer();
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater (new Runnable () {
			public void run () {
				new DivinaeGraphique();
			}
		});
	}
}
