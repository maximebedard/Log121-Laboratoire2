/******************************************************
Cours:  LOG121
Projet: Laboratoire #1
Nom du fichier: JOptionPaneExtensions.java
Date créé: 2013-09-28
*******************************************************
Historique des modifications
*******************************************************
*@author Maxime Bédard
2013-05-03 Version initiale
*******************************************************/

import javax.swing.*;
import java.awt.*;

public final class JOptionPaneExtensions {


    /**
     * Affiche un message d'erreur ayant comme titre "Erreur..."
     * @param parent composante parente au message d'erreur
     * @param msg message à afficher
     */
    public static void showErrorMessage(Component parent, String msg)
    {
        showErrorMessage(parent, msg, "Erreur...");
    }


    /**
     * Affiche une message d'erreur
     * @param parent composante parente au message d'erreur
     * @param msg message à afficher
     * @param title titre du message d'erreur
     */
    public static void showErrorMessage(Component parent, String msg, String title)
    {
        JOptionPane.showMessageDialog(parent,
                msg,
                title,
                JOptionPane.ERROR_MESSAGE);
    }

}
