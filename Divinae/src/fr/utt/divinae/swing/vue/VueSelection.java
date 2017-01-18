package fr.utt.divinae.swing.vue;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class VueSelection extends JPanel
{
	private boolean selected;
	public VueSelection(boolean selected) {
		this.selected = selected;
		setMinimumSize(new Dimension(VueCarte.CARTE_LARGEUR, 10));
		setMaximumSize(new Dimension(VueCarte.CARTE_LARGEUR, 10));
		setPreferredSize(new Dimension(VueCarte.CARTE_LARGEUR, 10));
		setBackground(Color.BLACK);
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		if (selected)
		{
			setBackground(Color.GREEN);
		}
		else
		{
			setBackground(Color.BLACK);
		}
	}

	public boolean isSelected() {
		return this.selected;
	}
}
