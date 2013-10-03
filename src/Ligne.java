/******************************************************
Cours:  LOG121
Projet: laboratoire #1
Nom du fichier: Ligne.java
Date créé: 2013-09-12
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Patrice Boucher
2013-09-12 Version initiale

 *@author Nicolas Jiménez-Dumont
2013-09-17 Ajout de la variable couleur - modification dessiner()
 *******************************************************/

import java.awt.Color;
import java.awt.Graphics;

public class Ligne extends Forme {

	/**
	 * Constructeur de la classe ligne
	 * 
	 * @param noSeq
	 *            numéro séquentiel
	 * @param x
	 *            position en x
	 * @param y
	 *            position en y
	 * @param x2
	 *            position en x2
	 * @param y2
	 *            position en y2
	 */
	public Ligne(int noSeq, int x, int y, int x2, int y2) {
		super(noSeq, x, y, x2, y2);
		setCouleur(new Color(1.0f, 0.0f, 1.0f, 0.5f));
	}

	@Override
	protected void dessinerForme(Graphics g) {
		g.drawLine(getX(), getY(), getX2(), getY2());

	}

}
