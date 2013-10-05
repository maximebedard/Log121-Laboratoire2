/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: FenetrePrincipale.java
Date créé: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale

*@author Maxime Bedard
*******************************************************/  

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.swing.*;

import ca.etsmtl.log.util.IDLogger;

/**
 * Cette classe représente la fenêtre principale de l'application 
 * @author Patrice Boucher
 */
public class FenetrePrincipale extends JFrame implements PropertyChangeListener{
	
	private static final long serialVersionUID = -1210804336046370508L;
	
	private final CommBase comm = new CommBase();

	private final ListeChaine<Forme> formes = new ListeChaine<Forme>();
	
    /**
	 * Constructeur
	 */
	public FenetrePrincipale(){
		
        MenuFenetre menu = new MenuFenetre(comm, formes);
		this.setLayout(new BorderLayout());
		this.add(menu, BorderLayout.NORTH);
        FenetreFormes fenetreFormes = new FenetreFormes(formes);
		this.add(fenetreFormes, BorderLayout.CENTER); // Ajoute la fenêtre de forme à la fenètre principale
		this.pack(); // Ajuste la dimension de la fenêtre principale selon celle de ses composants
		this.setVisible(true); // Rend la fenêtre principale visible.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        comm.setPropertyChangeListener(this);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    comm.stop();
                } catch (IOException e1) {
                    // suppress
                }
            }
		});
	}


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("FORME"))
        {
            System.out.println(evt.getNewValue());
            Forme f = CreateurFormes.creerForme((String) evt.getNewValue());
            IDLogger.getInstance().logID(f.getNoSeq());
            formes.addLast(f);
            repaint();
        }
        else if(evt.getPropertyName().equals("ERREUR"))
        {
            Exception ex = (Exception) evt.getNewValue();
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, String.format("Une erreur est survenue : %s", ex.getMessage()));
        }
    }
}
