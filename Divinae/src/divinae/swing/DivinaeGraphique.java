package divinae.swing;

import divinae.swing.controleur.ControleurJeu;
import divinae.swing.modele.ModeleJeu;
import divinae.swing.vue.VueJeu;

public class DivinaeGraphique {

	public DivinaeGraphique() {
		ModeleJeu modeleJeu = new ModeleJeu();
		VueJeu vueJeu = new VueJeu(modeleJeu);
		modeleJeu.addObserver(vueJeu);
		ControleurJeu controleurJeu = new ControleurJeu(modeleJeu, vueJeu);
		controleurJeu.initialiserJeu();
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
