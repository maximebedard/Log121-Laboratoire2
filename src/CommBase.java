/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommBase.java
Date créé: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale

*@author Maxime Bédard
2013-09-28 Ajout de la connexion au serveur
*******************************************************/  

import java.beans.PropertyChangeListener;
import java.io.IOException;
import javax.swing.*;

/**
 * Base d'une communication via un fil d'exécution parallèle.
 */
public class CommBase {

    /**
     * Délais entre les appels au serveur
     */
	private final int DELAI = 1000;

    /**
     * Thread qui communique périodiquement avec le serveur
     */
	private SwingWorker<Object, Object> threadComm = null;

    /**
     * Listener qui envoie les information des formes et les erreurs à la fenêtre principale
     */
	private PropertyChangeListener listener = null;

    /**
     * Boolean qui définit si la connexion est toujours active (utilisé dans les menus)
     */
	private boolean isActif = false;

    /**
     * Encapsulation de la connexion avec le serveur
     */
    private ConnectionServeur connexion = new ConnectionServeur();

    /**
	 * Constructeur
	 */
	public CommBase(){

	}
	
	/**
	 * Définir le récepteur de l'information reéue dans la communication avec le serveur
	 * @param listener sera alerté lors de l'appel de "firePropertyChanger" par le SwingWorker
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener){
		this.listener = listener;
	}
	
	/**
	 * Démarre la communication
	 */
	public void start(String address) throws IOException {
        connexion.connect(address);
        creerCommunication();
    }
	
	/**
	 * Arrête la communication
	 */
	public void stop() throws IOException {
		if(threadComm != null)
            threadComm.cancel(true);

        isActif = false;
        connexion.disconnect();
    }
	
	/**
	 * Créer le nécessaire pour la communication avec le serveur
	 * @throws NullPointerException si listener == null
	 */
	protected void creerCommunication() {
		
		if(listener == null)
			throw new NullPointerException("listener");
		
		// Crée un fil d'exécusion parallèle au fil courant,
		threadComm = new SwingWorker<Object, Object>(){
			@Override
			protected Object doInBackground() {
				System.out.println("Le fils d'execution parallele est lance");
				try {
			        while(connexion.isConnected()) {
			            Thread.sleep(DELAI);
			
			            String strForme = connexion.getForme();
			            
			            //La méthode suivante alerte l'observateur
			            firePropertyChangeInternal("FORME", strForme);
			        }
				}
				catch(IOException ex) {
					firePropertyChangeInternal("ERREUR", ex);
				}
				catch (InterruptedException ex) {
					// supress error
				}
				catch (Exception ex) {
					//ex.printStackTrace();
				}
		
		        isActif = false;
		        System.out.println("Le fils d'execution parallele est termine");
		        return null;
			}
			
			private void firePropertyChangeInternal(String property, Object obj)
			{
				if(listener != null)
					firePropertyChange(property, null, obj);
			}
		};
		threadComm.addPropertyChangeListener(listener); // La méthode "propertyChange" de ApplicationFormes sera donc appelée lorsque le SwinkWorker invoquera la méthode "firePropertyChanger" 		
		threadComm.execute(); // Lance le fil d'exécution parallèle.
		isActif = true;
    }


    /**
	 * @return si le fil d'exécution parallèle est actif
	 */
	public boolean isActif(){
		return isActif;
	}
}
