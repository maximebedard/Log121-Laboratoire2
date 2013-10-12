/******************************************************
Cours:  LOG121
Projet: laboratoire #1
Nom du fichier: Ovale.java
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

public class Ovale extends Forme {

	/**
	 * Constructeur de la classe Ovale
	 * 
	 * @param noSeq
	 * @param x
	 * @param y
	 * @param rayonH
	 * @param rayonV
	 */
	public Ovale(int noSeq, int x, int y, int rayonH, int rayonV) {
		super(noSeq, x, y, 2 * rayonH, 2 * rayonV);
		setCouleur(new Color(0.0f, 1.0f, 0.0f, 0.5f));

	}

	@Override
	protected void dessinerForme(Graphics g) {
		g.fillOval(getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public double getAire() {
		return Math.PI * getWidth() * getHeight();
	}

	@Override
	public double getDistanceMax() {
		return (getWidth() > getHeight()) ? getWidth() * 2 : getHeight() * 2;
	}

}
