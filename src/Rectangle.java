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
	
	private int x2;
	
	private int y2;

	public Rectangle(int noSeq, int x, int y, int x2, int y2) {
		super(noSeq, x, y);
        setCouleur(Color.MAGENTA);
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	protected void dessinerForme(Graphics g) {
		g.fillRect(getX(), getY(), x2 - getX(), y2 - getY());
	}

}
