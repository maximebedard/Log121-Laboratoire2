/******************************************************
Cours:  LOG121
Projet: laboratoire #1
Nom du fichier: DecodeurForme.java
Date créé: 2013-09-29
*******************************************************
Historique des modifications
*******************************************************
*@author Maxime Bédard
2013-09-29 Version initiale
*******************************************************/  
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DecodeurForme 
{

    /**
     * Numéro séquentiel
     */
	private int noSeq;

    /**
     * Type de la forme
     */
	private TypeForme type;

    /**
     * Coordonnées de la forme
     */
	private int[] coordonnes;

    /**
     * Constructeur du décodeur de formes
     * @param input forme au format de chaîne de caractères
     */
	public DecodeurForme(String input)
	{
		if(input == null) 
			throw new IllegalArgumentException("input");
		
		if(!isValidInput(input)) 
			throw new IllegalArgumentException(String.format("Format attendu : %s", validRegex));
		
		noSeq = parseNoSeq(input);
		type = parseTypeForme(input);
		coordonnes = parseCoordonnes(input, type);
		
	}

    /**
     * Expression régulière qui permet de déterminer si la chaine reçu par le décodeur adopte le bon format
     */
	private static final String validRegex = "\\d+ <(.*)>(.*)</\\1>";

    /**
     * Expression régulière qui permet de décortiquer les balises XML
     */
	private static final String tagRegex = "<(.*)>(.*)</\\1>";

    /**
     * Pattern de l'expression réguìère qui permet de décortiquer les balises XML
     */
	private static final Pattern tagPattern = Pattern.compile(tagRegex);


    /**
     * Vérifie si la chaine de caratère est conforme à la l'expression régulière
     * @param input chaîne à valider
     * @return true si la chaine est valide
     */
	private boolean isValidInput(String input)
	{
		return input.matches(validRegex);
	}

    /**
     * Obtient le numéro séquentiel de la chaîne
     * @param input chaîne de caratères qui compose la forme
     * @return le numéro séquentiel de la forme
     */
	private static int parseNoSeq(String input)
	{
		return Integer.parseInt(input.substring(0, input.indexOf(" ")));
	}

    /**
     * Obtien le numéro séquentiel de la chaîne caratères
     * @param input chaîne de caratères qui compose la forme
     * @return le type de la forme
     * @see TypeForme
     */
	private static TypeForme parseTypeForme(String input)
	{
        String tag = findFormeXml(input);
		return TypeForme.valueOf(tag.substring(tag.indexOf("<") + 1, tag.indexOf(">")));
	}

    /**
     * Trouve la balise XML dans la chaîne de caratères
     * @param input chaîne de caratères qui compose la forme
     * @return la balise XML contenu dans la chaîne
     */
    private static String findFormeXml(String input)
    {
        Matcher m = tagPattern.matcher(input);
        m.find();
        return m.group(0);
    }


    /**
     * Trouve les coordonées en X,Y, rayon et autres entiers contenus dans la balise XML
     * @param input chaîne de caratères qui compose la forme
     * @param type le type de la forme
     * @return un tableau d'entiers
     */
	private static int[] parseCoordonnes(String input, TypeForme type)
	{
		String begin = "<" + type.toString() + ">";
		String end = "</" + type.toString() + ">";

        String tag = findFormeXml(input);
        String data = tag.substring(begin.length(), tag.length() - end.length());

		String[] cStrs = data.trim().split(" ");
		int[] cInts = new int[cStrs.length];
		for(int i = 0; i < cStrs.length; i++)
		{
			cInts[i] = Integer.parseInt(cStrs[i]);
		}
		
		return cInts;
	}

    /**
     * Retourne le numéro séquentiel de la forme décodé
     * @return numéro séquentiel de la forme
     */
	public int getNoSeq()
	{
		return noSeq;
	}

    /**
     * Retourne le type de la forme décodé
     * @return type de la forme
     */
	public TypeForme getType()
	{
		return type;
	}

    /**
     * coordonées en X,Y, rayon et autres entiers contenus dans la balise XML
     * @return un tableau d'entiers
     */
	public int[] getCoordonnes()
	{
		return coordonnes;
	}

}
