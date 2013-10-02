import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class ListeTest {

	private static final int nbIter = 5;

	@Test
	public void testAjouterDebut() {
		Liste<Integer> liste = new Liste<Integer>();
		for (int i = 0; i < 10; i++)
			liste.ajouterDebut(i);
		assertEquals(10, liste.getSize());
		assertEquals("[9,8,7,6,5,4,3,2,1,0]", liste.toString());

	}

	@Test
	public void testAjouterFin() {
		Liste<Integer> liste = new Liste<Integer>();
		for (int i = 0; i < 10; i++)
			liste.ajouterFin(i);

		assertEquals(10, liste.getSize());
		assertEquals("[0,1,2,3,4,5,6,7,8,9]", liste.toString());
	}

	@Test
	public void testAjouter() {
		Liste<Integer> liste = new Liste<Integer>();

		liste.ajouter(0, 1);
		liste.ajouter(0, 2);
		liste.ajouter(0, 3);

		liste.ajouter(1, 1);
		liste.ajouter(1, 2);
		liste.ajouter(1, 3);

		liste.ajouter(6, 4);

		assertEquals(7, liste.getSize());
		assertEquals("[3,3,2,1,2,1,4]", liste.toString());
	}

	private Liste<Integer> buildList() {
		Liste<Integer> l = new Liste<Integer>();

		for (int i = 0; i < nbIter; i++)
			l.ajouterDebut(1);

		for (int i = 0; i < nbIter; i++)
			l.ajouterFin(2);

		for (int i = 0; i < nbIter; i++)
			l.ajouter(nbIter, 3);

		return l;
	}

	@Test
	public void testRetirerDebut() {
		Liste<Integer> liste = buildList();

		for (int i = 0; i < nbIter; i++)
			liste.retirerDebut();

		assertEquals(2 * nbIter, liste.getSize());
	}

	@Test(expected = NoSuchElementException.class)
	public void testRetirerDebutVide() {
		Liste<Integer> l = new Liste<Integer>();
		l.retirerDebut();
	}

	@Test
	public void testRetirerFin() {
		Liste<Integer> liste = buildList();

		for (int i = 0; i < nbIter; i++) {
			liste.retirerFin();
		}

		assertEquals(2 * nbIter, liste.getSize());
	}

	@Test
	public void testRetirer() {
		Liste<Integer> liste = buildList();

		for (int i = 0; i < nbIter; i++) {
			liste.retirer(nbIter - i);
		}

		assertEquals(2 * nbIter, liste.getSize());
	}

	@Test
	public void testSort() {
		Liste<Integer> liste = new Liste<Integer>();

		for (int i = 50; i >= 0; i--)
			liste.ajouterFin(i);

		assertEquals((long) 50, (long) liste.trouve(0));
		assertEquals((long) 0, (long) liste.trouve(50));

		Liste<Integer> copie = ListeOperation.sort(liste);

		assertEquals((long) 0, (long) copie.trouve(0));
		assertEquals((long) 50, (long) copie.trouve(50));

		int j = 0;
		for (int i : copie) {
			assertEquals(j, i);
			j++;
		}

	}

}
