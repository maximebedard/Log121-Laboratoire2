/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: MenuFenetre.java
Date créé: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*******************************************************/

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

/**
 * Crée le menu de la fenêtre de l'applicationé
 */
public class MenuFenetre extends JMenuBar{
	
	private static final long serialVersionUID = 1536336192561843187L;
	private static final int  MENU_DESSIN_ARRETER_TOUCHE_MASK  = ActionEvent.CTRL_MASK;
	private static final char MENU_DESSIN_ARRETER_TOUCHE_RACC  = KeyEvent.VK_A;
	private static final int  MENU_DESSIN_DEMARRER_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_DESSIN_DEMARRER_TOUCHE_RACC = KeyEvent.VK_D;
	private static final int  MENU_FICHIER_QUITTER_TOUCHE_MASK = ActionEvent.CTRL_MASK;
	private static final char MENU_FICHIER_QUITTER_TOUCHE_RACC = KeyEvent.VK_Q;
	private static final String
			MENU_FICHIER_TITRE = "app.frame.menus.file.title",
			MENU_FICHIER_QUITTER = "app.frame.menus.file.exit",
			MENU_DESSIN_TITRE = "app.frame.menus.draw.title",
			MENU_DESSIN_DEMARRER = "app.frame.menus.draw.start",
			MENU_DESSIN_ARRETER = "app.frame.menus.draw.stop",
			MENU_AIDE_TITRE = "app.frame.menus.help.title",
			MENU_AIDE_PROPOS = "app.frame.menus.help.about",
			DIALOG_CONNEXION_TITRE = "app.frame.menus.connect.title",
			DIALOG_CONNEXION_MSG = "app.frame.menus.connect.msg",
			DIALOG_CONNEXION_ERR_UNKNOWNHOST = "app.frame.menus.connect.err.unknownhost",
			DIALOG_CONNEXION_ERR_MALFORMED_URL = "app.frame.menus.connect.err.malformedurl",
			DIALOG_CONNEXION_ERR_IO = "app.frame.menus.connect.err.io",
			DIALOG_CONNEXION_ERR = "app.frame.menus.connect.err";
					
	private static final String MESSAGE_DIALOGUE_A_PROPOS = "app.frame.dialog.about";  

	private JMenuItem arreterMenuItem, demarrerMenuItem;
	private static final int DELAI_QUITTER_MSEC = 200;
 	   
	CommBase comm; // Pour activer/désactiver la communication avec le serveur
	
	/**
	 * Constructeur
	 */
	public MenuFenetre(CommBase comm) {
		this.comm = comm;
		addMenuDessiner();
		addMenuFichier();
		addMenuAide();
	}

    /**
     * Initialise la connexion avec le serveur
     */
    private void startConnection()
    {
    	String addr = "";
        try {
            addr = JOptionPane.showInputDialog(this, "Quel est le nom d'hôte et le port du serveur de formes?", "localhost:10000");

            if(addr == null)
                return;

            comm.start(addr);
        }
        catch (UnknownHostException ex){
            JOptionPaneExtensions.showErrorMessage(this, "La machine distante ne peut pas être résolue (erreur DNS).");
            startConnection();
        }
        catch(MalformedURLException ex){
        	JOptionPaneExtensions.showErrorMessage(this, "L'url entrée est invalide."));
            startConnection();
        }
        catch (IOException ex){
            JOptionPaneExtensions.showErrorMessage(this, "La connexion au serveur à échoué, est-ce que celui-ci est bien démarré?");
            startConnection();
        }
        catch (Exception ex){
            JOptionPaneExtensions.showErrorMessage(this, "Une erreur imprévue est survenue lors de la connexion au serveur, veuillez essayer de nouveau");
            startConnection();
        }
    }

    /**
     * Termine la connexion avec le serveur
     */
    private void stopConnection()
    {
        try {
            comm.stop();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
	 *  Créer le menu "Draw". 
	 */
	protected void addMenuDessiner() {
		JMenu menu = creerMenu(MENU_DESSIN_TITRE,new String[] { MENU_DESSIN_DEMARRER, MENU_DESSIN_ARRETER });
        menu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                rafraichirMenus();
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });


		demarrerMenuItem = menu.getItem(0);
		demarrerMenuItem.addActionListener(new ActionListener(){
		  @Override
		public void actionPerformed(ActionEvent arg0) {
              startConnection();
              rafraichirMenus();
		  }
		});
		demarrerMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				MENU_DESSIN_DEMARRER_TOUCHE_RACC,
				MENU_DESSIN_DEMARRER_TOUCHE_MASK));

		arreterMenuItem = menu.getItem(1);
		arreterMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
                stopConnection();
                rafraichirMenus();
		    }
	    });
		
		arreterMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				MENU_DESSIN_ARRETER_TOUCHE_RACC,
				MENU_DESSIN_ARRETER_TOUCHE_MASK));
		add(menu);
	}

	/** 
	 * Créer le menu "File". 
	 */
	protected void addMenuFichier() {
		JMenu menu = creerMenu(MENU_FICHIER_TITRE, new String[] { MENU_FICHIER_QUITTER });
		menu.getItem(0).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
                try
                {
                    comm.stop();
                    Thread.sleep(DELAI_QUITTER_MSEC);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
				System.exit(0);
			}
		});
		menu.getItem(0).setAccelerator(
				KeyStroke.getKeyStroke(MENU_FICHIER_QUITTER_TOUCHE_RACC,
						MENU_FICHIER_QUITTER_TOUCHE_MASK));
		add(menu);
	}

	/**
	 *  Créer le menu "Help". 
	 */
	private void addMenuAide() {
		JMenu menu = creerMenu(MENU_AIDE_TITRE, new String[] { MENU_AIDE_PROPOS });
		menu.getItem(0).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,  LangueConfig.getResource(MESSAGE_DIALOGUE_A_PROPOS), 
						LangueConfig.getResource(MENU_AIDE_PROPOS), JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(menu);
	}

	/**
	 *  Activer ou désactiver les items du menu selon la sélection. 
	 */
	private void rafraichirMenus() {
		demarrerMenuItem.setEnabled(!comm.isActif());
		arreterMenuItem.setEnabled(comm.isActif());
	}
	
	/**
	 * Créer un élément de menu à partir d'un champs principal et ses éléments
	 * @param titleKey champs principal
	 * @param itemKeys éléments
	 * @return le menu
	 */
	private static JMenu creerMenu(String titleKey,String[] itemKeys) {
        JMenu menu = new JMenu(LangueConfig.getResource(titleKey));
        for (String itemKey : itemKeys) {
            menu.add(new JMenuItem(LangueConfig.getResource(itemKey)));
        }
        return menu;
   }

}
