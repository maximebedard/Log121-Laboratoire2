import java.util.Comparator;

public final class Ensembles {

	/**
	 * Tri la collection avec un comparateur par défaut
	 * Source : http://docs.oracle.com/javase/6/docs/api/java/util/Collections.html
	 * @param col
	 * @return Nouvelle collection trié
	 */
	public static <T extends Comparable<? super T>> Ensemble<T> sort(
			final Ensemble<T> col) {
		return sort(col, new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
		});
	}

	/**
	 * Retourne l'inverse d'un comparateur (utilisé pour trier en ordre déscendant)
	 * @param comp
	 * @return comparateur inverse
	 */
	public static <T> Comparator<T> reverseOrder(final Comparator<T> comp) {
		return new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				return comp.compare(o1, o2) * -1;
			}
		};
	}

	/**
	 * Implémentation du QuickSort Source :
	 * http://rosettacode.org/wiki/Sorting_algorithms/Quicksort#Java
	 * 
	 * @param col
	 * @param comp
	 * @return Nouvelle collection trié
	 */
	public static <T> Ensemble<T> sort(final Ensemble<T> col,
			Comparator<? super T> comp) {
		
		if(comp == null)
			return col;

		if (col.getNbElements() <= 1)
			return col;

		T pivot = col.trouve(col.getNbElements() / 2);

		Ensemble<T> less, more, even;
		less = new ListeChaine<T>();
		more = new ListeChaine<T>();
		even = new ListeChaine<T>();

		for (T elem : col) {
			if (comp.compare(elem, pivot) < 0)
				less.ajouterFin(elem);
			else if (comp.compare(elem, pivot) > 0)
				more.ajouterFin(elem);
			else
				even.ajouterFin(elem);
		}

		less = sort(less, comp);
		more = sort(more, comp);

		less.ajouterFin(even);
		less.ajouterFin(more);

		return less;

	}

}
