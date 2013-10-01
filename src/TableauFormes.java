/******************************************************
Cours:  LOG121
Projet: Laboratoire #1
Nom du fichier: TableauFormes.java
Date créé: 2013-09-17
 *******************************************************
Historique des modifications
 *******************************************************
 *@author Nicolas Jiménez-Dumont
2013-09-17 Version initiale

 *@author Nicolas Jiménez-Dumont
2013-09-19 Changement du fonctionnement g�n�rale pour moins ressembler � une file.
 *******************************************************/

import java.util.NoSuchElementException;

public class TableauFormes {

	/**
	 * Tableau contenants les formes
	 * */
	private Forme[] tabFormes;

	/**
	 * Nombre d'elements actuellement dans le tableau
	 */
	private int nbElements;

	/**
	 * Encapsulation du nombre d'elements
	 * 
	 * @return Nombre d'elements actuellement dans le tableau
	 */
	public int getNbElements() {
		return nbElements;
	}

	/**
	 * Constructeur: la taille du tableau est toujours = 10. Tous les autres
	 * varables sont initialisé é 0
	 */
	public TableauFormes() {
		tabFormes = new Forme[10];
		nbElements = 0;
	}

	/**
	 * Ajoute au tableau une nouvelle forme, le nombre de forme mémorisé
	 * n'excédera pas 10
	 * 
	 * @param valForme
	 *            Forme à ajouter
	 */
	public void ajouter(Forme valForme) {
		if(nbElements == 10){
			supprimerForme(0);
		}
		tabFormes[nbElements] = valForme;

		// ajuste les variable local aux changements apporté au tableau
		incrementeNbElements();
	}

	/**
	 * Obtient la forme la plus ancienne ajouté au tableau de formes
	 * 
	 * @return objet de type Forme
	 */
	public Forme retirer() {

		if (nbElements > 0) {			
			supprimerForme(0);
			return tabFormes[0];
		} else {
			// exception lancé si il n'y a aucune forme é retourner
			throw new NoSuchElementException();
		}

	}

	public Forme retirer(int index) {
		if (index < nbElements) {
			supprimerForme(index);
			return tabFormes[index];
		} else {
			// exception lancé si il n'y a aucune forme é retourner
			throw new NoSuchElementException();
		}
	}

	public Forme getForme(int index) {
		if (index < nbElements) {
			return tabFormes[index];
		} else {
			// exception lancé si il n'y a aucune forme é retourner
			throw new NoSuchElementException();
		}
	}

	/**
	 * Incremente le nombre d'éléments en boucle pour qu'il ne dépasse pas 10
	 */
	private void incrementeNbElements() {
		nbElements++;
		nbElements = (nbElements > 10) ? 10 : nbElements;
	}

	/**
	 * Incremente le nombre d'éléments en boucle pour qu'il ne soit pas sous 0
	 */
	private void decrementeNbElements() {
		nbElements--;
		nbElements = (nbElements < 0) ? 0 : nbElements;

	}

	private void supprimerForme(int index) {
		decrementeNbElements();
		if (nbElements > 0) {
            System.arraycopy(tabFormes, index + 1, tabFormes, index, nbElements - index);
		}

	}

}