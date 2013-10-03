/******************************************************
Cours:  LOG121
Projet: laboratoire #1
Nom du fichier: Rectangle.java
Date créé: 2013-09-12
 *******************************************************
Historique des modifications
 *******************************************************

 *@author Nicolas Jiménez-Dumont
2013-09-17 Ajout de la variable couleur - modification dessiner()
 *******************************************************/

import java.awt.Graphics;
import java.awt.Color;

public class Rectangle extends Forme {

	public Rectangle(int noSeq, int x, int y, int x2, int y2) {
		super(noSeq, x, y, x2, y2);
		setCouleur(new Color(1.0f, 0.0f, 0.0f, 0.5f));
	}

	@Override
	protected void dessinerForme(Graphics g) {
		g.fillRect(getX(), getY(), getX2() - getX(), getY2() - getY());
	}

}
