import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListeChainee<T> implements Iterable<T> {

	/**
	 * Nombre d'éléments dans la file
	 */
	private int size;

	/**
	 * Noeud au début de la file
	 */
	private Noeud<T> begin;

	/**
	 * Noeud à la fin de la file
	 */
	private Noeud<T> end;

	/**
	 * Construit une liste d'éléments vide
	 */
	public ListeChainee() {
		begin = end = null;
		size = 0;
	}

	/**
	 * Ajoute une forme é la fin de la file Opération en O(1)
	 * 
	 * @param elem
	 * @throws IllegalArgumentException
	 */
	public void ajouterFin(T elem) {
		if (elem == null)
			throw new IllegalArgumentException("elem");

		// on ajoute le premier noeud
		if (begin == null) {
			begin = new Noeud<T>(elem);
			end = begin;
		}
		// on ajoute le noeud é la fin
		else {
			end.next = new Noeud<T>(elem);
			end = end.next;
		}

		size++;
	}

	/**
	 * Ajoute un forme au debut de la file Opération en O(1)
	 * 
	 * @param elem
	 * 
	 * @throws IllegalArgumentException
	 */
	public void ajouterDebut(T elem) {
		if (elem == null)
			throw new IllegalArgumentException("elem");
		
		// on ajoute le premier noeud
		if (begin == null) {
			begin = new Noeud<T>(elem);
			end = begin;
		} else {
			Noeud<T> temp = begin;
			begin = new Noeud<T>(elem);
			begin.next = temp;
		}

		size++;
	}

	/**
	 * Ajoute un élément à la suite de l'index s Opération en O(n)
	 * 
	 * @param index
	 * @param elem
	 * @throws ArrayIndexOutOfBoundsException
	 * @throws IllegalArgumentException
	 */
	public void ajouter(int index, T elem) {
		if (index < 0 || index > size)
			throw new ArrayIndexOutOfBoundsException("index");

		if (elem == null)
			throw new IllegalArgumentException("elem");

		if (index == 0)
			ajouterDebut(elem);
		else if (index == size)
			ajouterFin(elem);
		else {
			Noeud<T> precedent = trouveNoeud(index - 1);
			Noeud<T> nouveau = new Noeud<T>(elem, precedent.next);
			precedent.next = nouveau;
			size++;
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
		if (index < 0 || index > size)
			throw new ArrayIndexOutOfBoundsException("index");

		Noeud<T> found = begin;
		int i = 0;
		while (found.hasNext() && i != index) {
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
	public void retirer(int index) {
		if (index < 0 || index > size)
			throw new ArrayIndexOutOfBoundsException("index");

		if (index == 0)
			retirerDebut();
		else if (index == size)
			retirerFin();
		else {
			
		}
	}

	/**
	 * Retire un element au debut de la liste
	 */
	public void retirerDebut() {
		// la pile est vide
		if (begin == null)
			throw new NoSuchElementException();

		// on deplace le noeud du debut s'il y a plus d'un element
		if (begin.hasNext()) {
			Noeud<T> temp = begin.next;
			begin = temp;
		} else {
			begin = null;
			end = null;
		}
		size--;
	}

	/**
	 * Retire un element a la fin de la liste
	 */
	public void retirerFin() {
		if (end == null)
			throw new NoSuchElementException();

		if (begin == end)
			begin = null;
		end = null;
		size--;
	}

	/**
	 * Retoune un iterateur pour parcourir les elements dans la liste
	 */
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Noeud<T> prev = null;
			private Noeud<T> current = begin;

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
				current = current.next;
				return prev.elem;
			}

			@Override
			public void remove() {
				throw new IllegalArgumentException("Not implemented");
			}

		};
	}


	@Override
	public String toString()
	{
		String output = "";
		for(T elem : this)
			output += elem.toString() + ",";
		
		return String.format("[%s]", output.substring(0, output.length() - 1));
		
	}

	/**
	 * Retourne le nombre d'element dans la liste
	 * 
	 * @return nombre de formes
	 */
	public int getSize() {
		return size;
	}

	private class Noeud<U> {
		public Noeud(U value) {
			this(value, null);
		}

		public Noeud(U value, Noeud<U> next) {
			this.elem = value;
			this.next = next;
		}

		public boolean hasNext() {
			return next != null;
		}

		public U elem;
		public Noeud<U> next;
	}

}
