import java.util.Iterator;



public interface Ensemble<T> extends Iterable<T> {

	void ajouterDebut(T element);
	void ajouterDebut(Ensemble<T> col);
	
	void ajouterFin(T element);
	void ajouterFin(Ensemble<T> col);
	
	void ajouter(int index, T element);
	
	void retirerDebut();	
	void retirerFin();
	
	void retirer(int index);
	
	void vider();
	
	boolean estVide();
	
	int getNbElements();
	
	T trouve(int index);
	
	Iterator<T> reverseIterator();
	
}
