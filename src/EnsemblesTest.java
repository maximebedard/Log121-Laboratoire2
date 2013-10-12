import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

public class EnsemblesTest {

	private final class ComparatorImplementation implements Comparator<Forme> {
		@Override
		public int compare(Forme arg0, Forme arg1) {
			return Integer.compare(arg0.getNoSeq(), arg1.getNoSeq());
		}
	}

	Ensemble<Forme> liste;
	
	@Before
	public void setUp() throws Exception {
		liste = new ListeChaine<Forme>();
		for(int i = 0; i < 37; i++)
		{
			liste.ajouterDebut(new Carre(i,0,0,0,0));		
		}	
	}

	private void printList() {
		String output = "";
		for (Forme elem : liste)
			output += elem + ",";

		System.out.println(String.format("[%s]",
				output.substring(0, output.length() - 1)));
	}
	
	private ListeChaine<Forme> copyListe(Ensemble<Forme> ens)
	{
		ListeChaine<Forme> nouvelle = new ListeChaine<Forme>();
		for(Forme f : ens)
			nouvelle.ajouterFin(f.clone());
		
		return nouvelle;
	}

	@Test
	public void testSort() {
		liste = Ensembles.sort(liste, new ComparatorImplementation());
		liste = copyListe(liste);
		liste = Ensembles.sort(liste, new ComparatorImplementation());
		
		int i = 0;
		for(Forme j : liste)
		{
			assertEquals(i, j.getNoSeq());
			i++;
		}
		
		assertEquals(37, liste.getNbElements());
		printList();
	}
	
	

}
