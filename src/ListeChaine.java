import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListeChaine<T> implements Ensemble<T> {

	/**
	 * Nombre d'éléments dans la file
	 */
	private int taille;

	/**
	 * Noeud au début de la file
	 */
	private Noeud<T> debut;

	/**
	 * Noeud à la fin de la file
	 */
	private Noeud<T> fin;

	/**
	 * Construit une liste d'éléments vide
	 */
	public ListeChaine() {
		debut = fin = null;
		taille = 0;
	}

	/**
	 * Ajoute une forme é la fin de la file Opération en O(1)
	 * 
	 * @param elem
	 * @throws IllegalArgumentException
	 */
	@Override
	public void ajouterFin(T elem) {
		if (elem == null)
			throw new IllegalArgumentException("elem");

		// on ajoute le premier noeud
		if (debut == null) {
			debut = new Noeud<T>(elem);
			fin = debut;
		}
		// on ajoute le noeud é la fin
		else {
			Noeud<T> a = fin;
			fin.next = new Noeud<T>(elem, null, a);
			fin = fin.next;
		}

		taille++;
	}

	@Override
	public void ajouterFin(Ensemble<T> col) {
		for (T elem : col)
			ajouterFin(elem);
	}

	/**
	 * Ajoute un forme au debut de la file Opération en O(1)
	 * 
	 * @param elem
	 * 
	 * @throws IllegalArgumentException
	 */
	@Override
	public void ajouterDebut(T elem) {
		if (elem == null)
			throw new IllegalArgumentException("elem");

		// on ajoute le premier noeud
		if (debut == null) {
			debut = new Noeud<T>(elem);
			fin = debut;
		} else {
			Noeud<T> temp = debut;
			debut = new Noeud<T>(elem, temp, null);
			temp.previous = debut;
		}

		taille++;
	}

	@Override
	public void ajouterDebut(Ensemble<T> col) {
		for (T elem : col)
			ajouterDebut(elem);
	}

	/**
	 * Ajoute un élément à la suite de l'index s Opération en O(n)
	 * 
	 * @param index
	 * @param elem
	 * @throws ArrayIndexOutOfBoundsException
	 * @throws IllegalArgumentException
	 */
	@Override
	public void ajouter(int index, T elem) {
		if (index < 0 || index > taille)
			throw new ArrayIndexOutOfBoundsException("index");

		if (elem == null)
			throw new IllegalArgumentException("elem");

		if (index == 0)
			ajouterDebut(elem);
		else if (index == taille)
			ajouterFin(elem);
		else {
			Noeud<T> current = trouveNoeud(index);
			Noeud<T> nouveau = new Noeud<T>(elem, current, current.previous);
			current.previous.next = nouveau;
			current.next.previous = nouveau;

			taille++;
		}
	}

	/**
	 * Trouve l'élément situé à l'index demandé Opération en O(n)
	 * 
	 * @param index
	 * @return Noeud<T> dans la chaine
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 */
	@Override
	public T trouve(int index) {
		return trouveNoeud(index).elem;
	}

	/**
	 * Trouve le noeud dans la liste chainé et le retourne
	 * 
	 * @param index
	 *            position du noeud dans la chaine
	 * @return Noeud<T> dans la chaine
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 */
	private Noeud<T> trouveNoeud(int index) {
		if (index < 0 || index > taille)
			throw new ArrayIndexOutOfBoundsException("index");

		Noeud<T> found = debut;
		int i = 0;

		while (found.hasNext() && i++ != index) {
			found = found.next;
		}

		return found;
	}

	/**
	 * Retire un element à l'index donné
	 * 
	 * @throws NoSuchElementException
	 *             si la file est vide
	 */
	@Override
	public void retirer(int index) {
		if (index < 0 || index > taille)
			throw new ArrayIndexOutOfBoundsException("index");

		if (index == 0)
			retirerDebut();
		else if (index == taille)
			retirerFin();
		else {
			Noeud<T> supprimer = trouveNoeud(index);
			supprimer.previous.next = supprimer.next;
			supprimer.next.previous = supprimer.previous;
			taille--;
		}
	}

	/**
	 * Retire un element au debut de la liste
	 */
	@Override
	public void retirerDebut() {
		// la pile est vide
		if (debut == null)
			throw new NoSuchElementException();

		// on deplace le noeud du debut s'il y a plus d'un element
		if (debut.hasNext()) {
			debut = debut.next;
		} else {
			debut = null;
			fin = null;
		}
		taille--;
	}

	/**
	 * Retire un element a la fin de la liste
	 */
	@Override
	public void retirerFin() {
		if (fin == null)
			throw new NoSuchElementException();

		if (fin.hasPrevious()) {
			fin = fin.previous;
		} else {
			fin = null;
			debut = null;
		}
		taille--;
	}

	/**
	 * Retoune un iterateur pour parcourir les elements dans la liste
	 */
	@Override
	public Iterator<T> iterator() {
		return new IteratorImplementation(debut, true);
	}

	/**
	 * Retourne un iterateur pour parcourir les éléments dans la liste en sens
	 * inverse
	 */
	@Override
	public Iterator<T> reverseIterator() {
		return new IteratorImplementation(fin, false);
	}

	/**
	 * Retourne le nombre d'element dans la liste
	 * 
	 * @return nombre de formes
	 */
	@Override
	public int getNbElements() {
		return taille;
	}

	private final class IteratorImplementation implements Iterator<T> {
		private Noeud<T> prev;
		private Noeud<T> current;
		private boolean ascending;

		public IteratorImplementation(Noeud<T> current, boolean ascending) {
			this.prev = null;
			this.current = current;
			this.ascending = ascending;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (current == null) {
				throw new NoSuchElementException();
			}

			prev = current;
			if (ascending)
				current = current.next;
			else
				current = current.previous;
			return prev.elem;
		}

		@Override
		public void remove() {
			throw new IllegalArgumentException("Not implemented");
		}
	}

	private final class Noeud<U> {
		public Noeud(U value) {
			this(value, null, null);
		}

		public Noeud(U value, Noeud<U> next, Noeud<U> previous) {
			this.elem = value;
			this.next = next;
			this.previous = previous;
		}

		public boolean hasNext() {
			return next != null;
		}

		public boolean hasPrevious() {
			return previous != null;
		}

		public U elem;
		public Noeud<U> next;
		public Noeud<U> previous;
	}

	/**
	 * Retire tous les éléments de la liste
	 */
	@Override
	public void vider() {
		for (@SuppressWarnings("unused")
		T elem : this)
			retirerFin();
	}

	/**
	 * Retoure vrai si la liste est vide
	 */
	@Override
	public boolean estVide() {
		return taille == 0;
	}

}
