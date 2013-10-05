/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: FenetreFormes.java
Date créé: 2013-05-03
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Patrice Boucher
2013-05-03 Version initiale
 *******************************************************/

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Cette fenêtre gère l'affichage des formes
 * 
 * @author Patrice Boucher
 */
public class FenetreFormes extends JComponent {

	private static final long serialVersionUID = -2262235643903749505L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final Dimension dimension = new Dimension(500, 500);

	private Collection<Forme> formes;

	/**
	 * Constructeur
	 */
	public FenetreFormes(Collection<Forme> formes) {
		this.formes = formes;
	}

	/*
	 * Affiche la liste de formes
	 */
	@Override
	public void paintComponent(Graphics g) {
		for (Forme f : formes)
			f.dessiner(g);
	}

	/*
	 * Le Layout qui utilise (contient) FenetreFormes doit réserver l'espace
	 * nécessaire à son affichage
	 */
	@Override
	public Dimension getPreferredSize() {
		return dimension;
	}

}
