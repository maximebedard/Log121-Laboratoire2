import static org.junit.Assert.*;


import java.util.NoSuchElementException;

import org.junit.Test;

public class ListeChaineTest {

	private static final int nbIter = 5;

	@Test
	public void testAjouterDebut() {
		ListeChaine<Integer> liste = new ListeChaine<Integer>();
		for (int i = 0; i < 10; i++)
			liste.addFirst(i);
		assertEquals(10, liste.count());
		assertEquals("[9,8,7,6,5,4,3,2,1,0]", liste.toString());

	}

	@Test
	public void testAjouterFin() {
		ListeChaine<Integer> liste = new ListeChaine<Integer>();
		for (int i = 0; i < 10; i++)
			liste.addLast(i);

		assertEquals(10, liste.count());
		assertEquals("[0,1,2,3,4,5,6,7,8,9]", liste.toString());
	}

	@Test
	public void testAjouter() {
		ListeChaine<Integer> liste = new ListeChaine<Integer>();

		liste.add(0, 1);
		liste.add(0, 2);
		liste.add(0, 3);

		liste.add(1, 1);
		liste.add(1, 2);
		liste.add(1, 3);

		liste.add(6, 4);

		assertEquals(7, liste.count());
		assertEquals("[3,3,2,1,2,1,4]", liste.toString());
	}

	private ListeChaine<Integer> buildList() {
		ListeChaine<Integer> l = new ListeChaine<Integer>();

		for (int i = 0; i < nbIter; i++)
			l.addFirst(1);

		for (int i = 0; i < nbIter; i++)
			l.addLast(2);

		for (int i = 0; i < nbIter; i++)
			l.add(nbIter, 3);

		return l;
	}

	@Test
	public void testRetirerDebut() {
		ListeChaine<Integer> liste = buildList();

		for (int i = 0; i < nbIter; i++)
			liste.removeFirst();

		assertEquals(2 * nbIter, liste.count());
	}

	@Test(expected = NoSuchElementException.class)
	public void testRetirerDebutVide() {
		ListeChaine<Integer> l = new ListeChaine<Integer>();
		l.removeFirst();
	}

	@Test
	public void testRetirerFin() {
		ListeChaine<Integer> liste = buildList();

		for (int i = 0; i < nbIter; i++) {
			liste.removeLast();
		}

		assertEquals(2 * nbIter, liste.count());
	}

	@Test
	public void testRetirer() {
		ListeChaine<Integer> liste = buildList();

		for (int i = 0; i < nbIter; i++) {
			liste.remove(nbIter - i);
		}

		assertEquals(2 * nbIter, liste.count());
	}

	@Test
	public void testSort() {
		ListeChaine<Integer> liste = new ListeChaine<Integer>();

		for (int i = 50; i >= 0; i--)
			liste.addLast(i);

		assertEquals((long) 50, (long) liste.find(0));
		assertEquals((long) 0, (long) liste.find(50));

		Collection<Integer> copie = Collections.sort(liste);

		assertEquals((long) 0, (long) copie.find(0));
		assertEquals((long) 50, (long) copie.find(50));

		int j = 0;
		for (int i : copie) {
			assertEquals(j, i);
			j++;
		}

	}

}
