/******************************************************
Cours:  LOG121
Projet: laboratoire #1
Nom du fichier: CreateurFormes.java
Date créé: 2013-09-29
*******************************************************
Historique des modifications
*******************************************************
*@author Maxime Bédard
2013-09-29 Version initiale
*******************************************************/  

public final class CreateurFormes
{

	/**
     * Crée une nouvelle forme. Cette méthode reçoit la chaîne de
     * caractéres provenant du serveur de formes, elle détermine de quelle
     * forme il s'agit et applique l'opérateur new sur le constructeur de
     * la forme désirée.
     *
     * @param chaineForme un objet String contenant la chaîne de caractéres
     *                    qui décrit une forme et provenant du serveur de
     *                    formes.
     *
     * @return une instance d'une des sous-classes de la classe abstraite
     *         Forme avec les paramétres passés par la chaîne d'entrée.
     */
    public static Forme creerForme(String chaineForme) 
    {
			DecodeurFormes df = new DecodeurFormes(chaineForme);

			Forme f = null;
			int[] coords = df.getCoordonnes();
			switch(df.getType())
			{
			case CARRE:
				f = new Carre(df.getNoSeq(), coords[0], coords[1], coords[2], coords[3]);
				break;
			case CERCLE:
				f = new Cercle(df.getNoSeq(),coords[0], coords[1], coords[2]);
				break;
			case LIGNE:
				f = new Ligne(df.getNoSeq(), coords[0], coords[1], coords[2], coords[3]);
				break;
			case OVALE:
				f = new Ovale(df.getNoSeq(), coords[0], coords[1], coords[2], coords[3]);
				break;
			case RECTANGLE:
				f = new Rectangle(df.getNoSeq(), coords[0], coords[1], coords[2], coords[3]);
				break;
			default:
				break;
			}
    	
			return f;
    }
}
