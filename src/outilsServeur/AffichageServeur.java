package outilsServeur;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

/**
 * <b> AffichageServeur est une classe permettant de gérer, analyser les informations reçues de l'utilisateur via le client.</b>
 * <p>
 * Elle est caractérisée par les informations suivantes:
 * <ul>
 * <li>Une chaine de caractère permettant d'afficher le LOG du serveur sur sa console (date, heure..) </li>
 * <li>Une chaine de caractère permettant de récupérer le premier élément du message (généralement une commande) </li>
 * <li>Une chaine de caractère permettant de récupérer le deuxième élément du message (généralement un nom de fichier) </li>
 * </ul>
 * Elles sont mises à jour grace à la méthode messageSplitter.
 * <p>
 * @author Sandratra RASENDRASOA et Antonia FRANCIS
 */
public class AffichageServeur {
	/**
     * Chaine de caractère permettant d'afficher le log serveur.
     */
	private String affichage;
	/**
     * Chaine de caractère permettant de récupérer le premier élément du message.
     */
	private String firstmessage;
	/**
     * Chaine de caractère permettant de récupérer le second élément du message.
     */
	private String secondmessage;
	
	/**
     * Constructeur d'AffichageServeur, qui attribue une chaine de caractère vide à chaque attribut, en attente d'un messageSplitter.
     */
	public AffichageServeur() {
		this.affichage = "";
		this.firstmessage = "";
		this.secondmessage = "";
	}
	
	public String getfirstmessage() {
		return this.firstmessage;
	}
	public String getsecondmessage() {
		return this.secondmessage;
	}
	
	/**
     * Méthode permettant de mettre à jour un message appartenant au LOG Serveur.
     * 
     * @param ipclient, adresse ip du client 
     * @param portclient, port de connexion du client
     * @param command, nom de la commande envoyée par le client
     * @param nom, nom du fichier sujet à la commande (s'il existe)
     * 
     * @return une ligne du log
     */
	public String giveInformations(String ipclient, String portclient, String command, String nom){
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    LocalDateTime now = LocalDateTime.now(); 
	    
	    this.affichage = "[" + ipclient + ":" + portclient + " - " + dtf.format(now)+ "]: " + command + " " + nom;
		return this.affichage;
	}
	
	/**
     * Méthode permettant de séparer le message exactement en deux : une commande et potentiellement un nom de fichier.
     * 
     * @param message, le texte à séparer
     */
	public void messageSplitter(String message) {
		String[] splited = message.split("\\s+");
		if (splited.length == 1) {
			this.firstmessage = splited[0];
			this.secondmessage = "";
		}
		else if (splited.length == 2) {
			this.firstmessage = splited[0];
			this.secondmessage = splited[1];
		}
		else {
			System.out.println("Le message est vide ou trop long : ");
			this.firstmessage = "";
			this.secondmessage = "";	
		}
	}
	
}
