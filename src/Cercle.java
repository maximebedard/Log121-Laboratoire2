/******************************************************
Cours:  LOG121
Projet: laboratoire #1
Nom du fichier: Cercle.java
Date créé: 2013-09-29
*******************************************************
Historique des modifications
*******************************************************
*@author Maxime Bédard
2013-09-29 Version initiale
*******************************************************/  
import java.awt.Color;


public class Cercle extends Ovale
{

	public Cercle(int noSeq, int x, int y, int rayon) {
		super(noSeq, x, y, rayon, rayon);
		setCouleur(Color.ORANGE);
	}

}
