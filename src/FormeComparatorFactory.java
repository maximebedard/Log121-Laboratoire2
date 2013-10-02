import java.util.Comparator;


public class FormeComparatorFactory {
	
	public Comparator<Forme> getComprator(FormeComparatorType type)
	{
		Comparator<Forme> comparator = null;
		
		switch(type)
		{
		case AIRE_ASC:
			break;
		case AIRE_DESC:
			break;
		case DISTANCE_MAXIMALE_ASC:
			break;
		case DISTANCE_MAXIMALE_DESC:
			break;
		case NO_SEQUENTIEL_ASC:
			break;
		case NO_SEQUENTIEL_DESC:
			break;
		case TYPE_FORME_ASC:
			comparator = new TypeComparator(true);
			break;
		case TYPE_FORME_DESC:
			comparator = new 
			break;
		default:
			comparator = new NoSequentielComparator(true);
			break;
		}
		
		return comparator;	
	}

	private abstract class FormeComparator implements Comparator<Forme>
	{
		private boolean ascending;
		public FormeComparator(boolean ascending)
		{
			this.ascending = ascending;
		}
		
		public boolean isAscending(){
			return ascending;
		}
	}
	
	
	private class NoSequentielComparator extends FormeComparator {
	
		public NoSequentielComparator(boolean ascending)
		{
			super(ascending);
		}
		
		@Override
		public int compare(Forme lhs, Forme rhs) {		
			int result = Integer.compare(lhs.getNoSeq(), rhs.getNoSeq());
			return isAscending() ? result : result * -1;
		}
	}
	
	private class AireComparator extends FormeComparator
	{

		public AireComparator(boolean ascending) {
			super(ascending);
		}

		@Override
		public int compare(Forme lhs, Forme rhs) {
			int result = Integer.compare(lhs.getAire(), rhs.getAire());
			
			return isAscending() ? result : result * -1;
		}
		
	}
	
	private class TypeComparator extends FormeComparator
	{
		public TypeComparator(boolean ascending) {
			super(ascending);
		}

		@Override
		public int compare(Forme lhs, Forme rhs) {
			int result = Integer.compare(lhs.getType().ordinal(), rhs.getType().ordinal());
			return isAscending() ? result : result * -1;
		}	
	}
	
	
	
}