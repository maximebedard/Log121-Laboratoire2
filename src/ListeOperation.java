import java.util.Comparator;

public final class ListeOperation {

	public static <T extends Comparable<? super T>> Liste<T> sort(Liste<T> liste)
	{
		return sort(liste, new Comparator<T>(){
			@Override
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
		});
	}
	
	/**
	 * Implémentation du QuickSort
	 * Source : http://rosettacode.org/wiki/Sorting_algorithms/Quicksort#Java
	 * @param liste
	 * @param comp
	 * @return
	 */
	public static <T> Liste<T> sort(Liste<T> liste,
			Comparator<? super T> comp) {

		if (liste.getSize() <= 1)
			return liste;
		
		

		T pivot = liste.trouve(liste.getSize() / 2);

		Liste<T> less, more, even;
		less = new Liste<T>();
		more = new Liste<T>();
		even = new Liste<T>();

		for (T elem : liste) {
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
