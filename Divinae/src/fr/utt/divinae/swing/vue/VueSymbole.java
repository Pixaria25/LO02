package fr.utt.divinae.swing.vue;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.utt.divinae.api.cartes.types.Carte;
import fr.utt.divinae.api.cartes.types.CarteAction;
import fr.utt.divinae.api.partie.De;

public class VueSymbole extends JButton
{
	public static int DE_LARGEUR = 40;
	public static int DE_LONGUEUR = 40;

	private int id;

	public VueSymbole(String symbolePrefixe, int id)
	{
		this.id = id;
		this.setMinimumSize(new Dimension(DE_LARGEUR, DE_LONGUEUR));
		this.setMaximumSize(new Dimension(DE_LARGEUR, DE_LONGUEUR));
		this.setPreferredSize(new Dimension(DE_LARGEUR, DE_LONGUEUR));
		String imagePath = "/images/" + symbolePrefixe + "-" + id +".png";
		try {
		    Image img = ImageIO.read(getClass().getResource(imagePath));
		    this.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
		    throw new RuntimeException("Image " + imagePath + " est inexistant.", ex);
		}
	}

	public int getId() {
		return id;
	}
}
