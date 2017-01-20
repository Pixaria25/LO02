package fr.utt.divinae.swing.vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.utt.divinae.api.joueur.Joueur;

public class SelectionnerJoueurDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel fenetre;
	private List<Joueur> joueurs;
	private Joueur choixJoueur;
	
	public SelectionnerJoueurDialog(JFrame parent, String titre, List<Joueur> joueurs) {
		super(parent, titre, true);
		this.setMinimumSize(new Dimension(320, 240));
		this.setMaximumSize(new Dimension(320, 240));
		this.setPreferredSize(new Dimension(320, 240));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.joueurs = joueurs;
		init(joueurs);
	}
	private void init(List<Joueur> joueurs) {
		fenetre = new JPanel();
		fenetre.setLayout(new GridLayout(0, 2, 10, 10));
		
		JPanel pane = new JPanel();

		for (Joueur joueur: joueurs) {
			JButton bouton = new JButton(joueur.getNom());
			bouton.addActionListener(this);
			fenetre.add(bouton);
		}
		
		pane.add(fenetre);
		this.getContentPane().add(pane);
		this.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object  source=e.getSource();

		if (source instanceof JButton)
		{
			String choixNom = ((JButton)source).getName();
			for (Joueur joueur: joueurs) {
				if (joueur.getNom().equals(choixNom))
				{
					choixJoueur = joueur;
					break;
				}
			}
			setVisible(false);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Aucune action prévue pour " + source);
		}
	}
	
	public Joueur getChoixJoueur() {
		return choixJoueur;
	}
}
