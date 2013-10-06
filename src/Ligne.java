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

	private int x1;

	private int x2;

	private int y1;

	private int y2;

	/**
	 * Constructeur de la classe ligne
	 * 
	 * @param noSeq
	 *            numéro séquentiel
	 * @param x1
	 *            position en x1
	 * @param y1
	 *            position en y1
	 * @param x2
	 *            position en x2
	 * @param y2
	 *            position en y2
	 */
	public Ligne(int noSeq, int x1, int y1, int x2, int y2) {
		super(noSeq, Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
				Math.abs(y1 - y2));

		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;

		setCouleur(Color.RED);
	}

	@Override
	protected void dessinerForme(Graphics g) {
		g.drawLine(x1, y1, x2, y2);

	}

	@Override
	protected double getAire() {
		return 0;
	}

	@Override
	protected double getDistanceMax() {
		return Math.sqrt(getWidth() * getWidth() + getHeight() * getHeight());
	}

}
