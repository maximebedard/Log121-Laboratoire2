/******************************************************
Cours:  LOG121
Projet: Laboratoire #1
Nom du fichier: Carre.java
Date créé: 2013-09-17
*******************************************************
Historique des modifications
*******************************************************
*@author Nicolas Jiménez-Dumont
2013-09-17 Version initiale
*******************************************************/  
import java.awt.Color;

/**
 * Cette classe représente un carré
 */
public class Carre extends Rectangle {

    /**
     * Constructeur de la classe carré
     * @param noSeq Numéro séquentiel obtenu lors de la connexion au serveur
     * @param x position en X du carré
     * @param y position en Y du carré
     * @param x2 position en X2 du carré
     * @param y2 position en Y2 du carré
     */
	public Carre(int noSeq, int x, int y, int x2, int y2) {
		super(noSeq, x,  y,  x2, y2);
		setCouleur(Color.CYAN);
	}
}
