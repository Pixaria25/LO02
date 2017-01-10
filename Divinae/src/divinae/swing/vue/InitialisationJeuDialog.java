package divinae.swing.vue;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InitialisationJeuDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel fenetre;
	private JTextArea nomJoueurReel;
	private JButton ok;
	private JTextArea nombreJoueurVirtuel;
	
	public InitialisationJeuDialog(JFrame parent, String titre, boolean modal) {
		super(parent, titre, modal);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		init();
	}
	private void init() {
		fenetre = new JPanel();
		fenetre.setLayout(new GridLayout(0, 2, 10, 10));
		
		JPanel pane = new JPanel();
		
		//Nom du joueur
		JLabel libelleNom = new JLabel("Nom du joueur réel");
		nomJoueurReel = new JTextArea();
		nomJoueurReel.setEditable(true);
		
		//nombre de joueurs virtuels
		JLabel libelleNombre = new JLabel("Nombre de joueurs virtuels");
		nombreJoueurVirtuel = new JTextArea();
		nombreJoueurVirtuel.setEditable(true);
		
		ok = new JButton("Ok");
		
		ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				setVisible(false);
			}
		});
		
		fenetre.add(libelleNom);
		fenetre.add(nomJoueurReel);
		fenetre.add(libelleNombre);
		fenetre.add(nombreJoueurVirtuel);
		fenetre.add(ok);
		
		pane.add(fenetre);
		
		
		this.getContentPane().add(pane);
		this.pack();
	}
	
	public JTextArea getNomJoueurReel() {
		return nomJoueurReel;
	}
	
	public JTextArea getNombreJoueurVirtuel() {
		return nombreJoueurVirtuel;
	}	
}
