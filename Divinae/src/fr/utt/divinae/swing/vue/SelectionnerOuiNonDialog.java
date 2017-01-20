package fr.utt.divinae.swing.vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SelectionnerOuiNonDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel fenetre;
	private JButton boutonOui = new JButton("Oui");
	private JButton boutonNon = new JButton("Non");
	private boolean choix;
	
	public SelectionnerOuiNonDialog(JFrame parent, String titre) {
		super(parent, titre, true);
		this.setMinimumSize(new Dimension(240, 80));
		this.setMaximumSize(new Dimension(240, 80));
		this.setPreferredSize(new Dimension(240, 80));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		init();
	}
	private void init() {
		fenetre = new JPanel();
		fenetre.setLayout(new GridLayout(0, 2, 10, 10));
		
		JPanel pane = new JPanel();

		boutonOui.addActionListener(this);
		boutonNon.addActionListener(this);
		fenetre.add(boutonOui);
		fenetre.add(boutonNon);
		
		pane.add(fenetre);
		this.getContentPane().add(pane);
		this.pack();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object  source=e.getSource();

		if (source == boutonOui)
		{
			choix = true;
		}
		else if (source == boutonNon)
		{
			choix = false;
		}
		setVisible(false);
	}
	
	public boolean getChoix() {
		return choix;
	}
}
