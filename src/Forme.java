/******************************************************
Cours:  LOG121
Projet: laboratoire #1
Nom du fichier: Forme.java
Date créé: 2013-09-12
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Patrice Boucher
2013-09-12 Version initiale

 *@author Nicolas Jiménez-Dumont
2013-09-17 Ajout de la variable couleur

 *@author Maxime Bédard
2013-09-18 Modification de l'application de la couleur
           Ajout de la JavaDoc
 *******************************************************/

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * Classe abstraite qui définit les attributs et les méthodes partagés entre les
 * différentes formes
 * 
 * @author Maxime Bédard
 */
public abstract class Forme implements Cloneable {

	/**
	 * Numéro séquentiel unique obtenu lors de la création de la forme
	 */
	private int noSeq;

	/**
	 * Position en X
	 */
	private int x;

	/**
	 * Position en Y
	 */
	private int y;

	/**
	 * Position en X2
	 */
	private int width;

	/**
	 * Position en Y2
	 */
	private int height;

	/**
	 * Couleur de la forme
	 */
	private Color couleur;

	/**
	 * Retourne le numéro séquentiel obtenu lors de la création de la forme.
	 * 
	 * @return le numéro séquentiel unique
	 */
	public int getNoSeq() {
		return noSeq;
	}

	/**
	 * Retourne le type de la fome
	 * 
	 * @return type
	 */
	public TypeForme getType() {
		return TypeForme.valueOf(this.getClass().getName().toUpperCase());
	}

	/**
	 * Retourne la position en X de la forme
	 * 
	 * @return position en x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Assigne la position en X de la forme
	 * 
	 * @param x
	 *            Position en X de la forme sur le canevas
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Retourne la position en Y de la forme
	 * 
	 * @return position en y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Assigne la position en Y de la forme
	 * 
	 * @param y
	 *            Position en Y de la forme sur le canevas
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Retourne la largeur
	 * 
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Retourne la largeur de la forme
	 * 
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Retourne la couleur de la forme
	 * 
	 * @return couleur de la forme
	 */
	public Color getCouleur() {
		return couleur;
	}

	/**
	 * Assigne la couleur utilisé lorsque la forme est dessiné
	 * 
	 * @param couleur
	 *            Couleur de la forme
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	
	@Override
	public String toString()
	{
		return String.valueOf(noSeq);
	}

	/**
	 * Construit une nouvelle forme a partir d'un numéro séquentiel ainsi ainsi
	 * que ses coordonnées (x,y). Lors de la création d'une forme, la couleur
	 * Color.BLACK est initialisé par défaut.
	 * 
	 * @param noSeq
	 *            Numéro séquentielle unique obtenu lors des échanges avec le
	 *            serveur.
	 * @param x
	 *            Position initiale de la forme en X sur le canevas
	 * @param y
	 *            Position initiale de la forme en Y sur le canevas
	 */
	public Forme(int noSeq, int x, int y, int width, int height) {
		this.noSeq = noSeq;

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		this.couleur = Color.BLACK;
	}

	final static BasicStroke dashed = new BasicStroke(1.0f,
			BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f,
			new float[] { 1.0f }, 0.0f);

	/**
	 * Dessine la forme sur le canevas en appelant la fonction dessinerForme des
	 * classes qui héritent de Forme. Cette fonction applique la couleur avant
	 * de dessiner la forme.
	 * 
	 * @param g
	 *            Context graphique sur lequel dessiner la forme
	 */
	public void dessiner(Graphics g) {
		g.setColor(couleur);
		dessinerForme(g);
		dessinerBoundingBox(g);
	}

	protected void dessinerBoundingBox(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Stroke oldStroke = g2.getStroke();
		Color oldColor = g2.getColor();

		g2.setColor(Color.BLACK);
		g2.setStroke(dashed);
		g2.drawRect(x, y, width, height);

		g2.setStroke(oldStroke);
		g2.setColor(oldColor);
	}

	/**
	 * Retourne une copie de l'objet
	 */
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	/**
	 * Dessine la forme sur le canevas
	 * 
	 * @param g
	 *            Context graphique sur lequel dessiner la forme
	 */
	protected abstract void dessinerForme(Graphics g);

	/**
	 * Retourne l'aire de la forme
	 * @return aire
	 */
	protected abstract double getAire();

	/**
	 * Retourne la distance maximale de la forme
	 * @return distance max
	 */
	protected abstract double getDistanceMax();

}
