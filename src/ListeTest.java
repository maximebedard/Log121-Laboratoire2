import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class ListeChaineeTest {

	private static final int nbIter = 5;

	@Test
	public void testAjouterDebut() {
		ListeChainee<Integer> liste = new ListeChainee<Integer>();
		for (int i = 0; i < 10; i++)
			liste.ajouterDebut(i);
		assertEquals(10, liste.getSize());
		assertEquals("[9,8,7,6,5,4,3,2,1,0]", liste.toString());

	}

	@Test
	public void testAjouterFin() {
		ListeChainee<Integer> liste = new ListeChainee<Integer>();
		for (int i = 0; i < 10; i++)
			liste.ajouterFin(i);

		assertEquals(10, liste.getSize());
		assertEquals("[0,1,2,3,4,5,6,7,8,9]", liste.toString());
	}

	@Test
	public void testAjouter() {
		ListeChainee<Integer> liste = new ListeChainee<Integer>();

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

	private ListeChainee<Integer> buildList() {
		ListeChainee<Integer> l = new ListeChainee<Integer>();

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
		ListeChainee<Integer> liste = buildList();

		for (int i = 0; i < nbIter; i++)
			liste.retirerDebut();

		assertEquals(2 * nbIter, liste.getSize());
	}

	@Test(expected = NoSuchElementException.class)
	public void testRetirerDebutVide() {
		ListeChainee<Integer> l = new ListeChainee<Integer>();
		l.retirerDebut();
	}

	@Test
	public void testRetirerFin() {
		ListeChainee<Integer> liste = buildList();

		for (int i = 0; i < nbIter; i++) {
			liste.retirerFin();
		}

		assertEquals(2 * nbIter, liste.getSize());
	}
	
	@Test
	public void testRetirer()
	{
		ListeChainee<Integer> liste = buildList();

		for (int i = 0; i < nbIter; i++) {
			liste.retirer(nbIter - i);
		}

		assertEquals(2 * nbIter, liste.getSize());		
	}
	
	

}
