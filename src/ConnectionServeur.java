/******************************************************
Cours:  LOG121
Projet: laboratoire #1
Nom du fichier: ConnectionServeur.java
Date créé: 2013-09-29
*******************************************************
Historique des modifications
*******************************************************
*@author Maxime Bédard
2013-09-29 Version initiale
*******************************************************/  

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;


public class ConnectionServeur {

    /**
     * Socket
     */
    private Socket socket = null;

    /**
     * Flux de données sortant
     */
    private PrintWriter outStream = null;

    /**
     * Flux de données entrant
     */
    private BufferedReader inStream = null;

    /**
     * Connexion au serveur à partir d'une adresse au format nom:[port] ou le port
     * par défaut est égal à 10000
     * @param addr adresse du serveur
     * @throws IOException Exception lancé lors des erreurs de connexion
     */
    public void connect(String addr) throws IOException {
        if(isConnected())
            disconnect();

        if(addr == null || (addr != null && addr.equals("")))
        	throw new MalformedURLException("L'adresse est vide");
        
        String[] addrParts = addr.split(":");
        
        int port = 10000;
        String hostname = addrParts[0];
        
        if(addrParts.length == 0 || addrParts.length > 2)
        	throw new MalformedURLException("L'adresse contient plus d'un ':' pour spécifier le port.");
        
        if(addrParts.length > 1){
        	try {
            	port = Integer.parseInt(addrParts[1]);
			} catch (NumberFormatException ex) {
				throw new MalformedURLException("Le numéro de port n'est pas un entier");
			}
        }
        
        System.out.println("Hostname : " + hostname);
        System.out.println("Port : " + port);
        	
        socket = new Socket(hostname, port);
        outStream = new PrintWriter(socket.getOutputStream(), true);
        inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    /**
     * Vérifie si la connexion du socket est toujours active
     * @return true si la connexion est toujours active
     */
    public boolean isConnected()
    {
        return socket != null && socket.isConnected();
    }

    /**
     * Intéroge le serveur dans le but d'obtenir la définition d'une forme sous la forme d'une chaîne de caractère.
     * @return la forme sous forme de chaîne de caractères
     * @throws IOException
     */
    public String getForme() throws IOException {
        if(!isConnected())
            return null;

        inStream.readLine();
        outStream.println("GET");
        return inStream.readLine();
    }

    /**
     * Ferme la connexion du socket
     * @throws IOException
     */
    public void disconnect() throws IOException {
        if(!isConnected())
            return;

        outStream.println("END");
        socket.close();
    }


}
