import java.util.Comparator;

public final class Collections {

	public static <T extends Comparable<? super T>> Collection<T> sort(Collection<T> col)
	{
		return sort(col, new Comparator<T>(){
			@Override
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
		});
	}
	
	/**
	 * Implémentation du QuickSort
	 * Source : http://rosettacode.org/wiki/Sorting_algorithms/Quicksort#Java
	 * @param col
	 * @param comp
	 * @return
	 */
	public static <T> Collection<T> sort(Collection<T> col,
			Comparator<? super T> comp) {

		if (col.count() <= 1)
			return col;

		T pivot = col.find(col.count() / 2);

		Collection<T> less, more, even;
		less = new ListeChaine<T>();
		more = new ListeChaine<T>();
		even = new ListeChaine<T>();

		for (T elem : col) {
			if (comp.compare(elem, pivot) < 0)
				less.addLast(elem);
			else if (comp.compare(elem, pivot) > 0)
				more.addLast(elem);
			else
				even.addLast(elem);
		}
		
		less = sort(less, comp);
		more = sort(more, comp);
		
		less.addLast(even);
		less.addLast(more);
		
		return less;

	}

}
