package outilsServeur;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

/**
 * <b> AffichageServeur est une classe permettant de g�rer, analyser les informations re�ues de l'utilisateur via le client.</b>
 * <p>
 * Elle est caract�ris�e par les informations suivantes:
 * <ul>
 * <li>Une chaine de caract�re permettant d'afficher le LOG du serveur sur sa console (date, heure..) </li>
 * <li>Une chaine de caract�re permettant de r�cup�rer le premier �l�ment du message (g�n�ralement une commande) </li>
 * <li>Une chaine de caract�re permettant de r�cup�rer le deuxi�me �l�ment du message (g�n�ralement un nom de fichier) </li>
 * </ul>
 * Elles sont mises � jour grace � la m�thode messageSplitter.
 * <p>
 * @author Sandratra RASENDRASOA et Antonia FRANCIS
 */
public class AffichageServeur {
	/**
     * Chaine de caract�re permettant d'afficher le log serveur.
     */
	private String affichage;
	/**
     * Chaine de caract�re permettant de r�cup�rer le premier �l�ment du message.
     */
	private String firstmessage;
	/**
     * Chaine de caract�re permettant de r�cup�rer le second �l�ment du message.
     */
	private String secondmessage;
	
	/**
     * Constructeur d'AffichageServeur, qui attribue une chaine de caract�re vide � chaque attribut, en attente d'un messageSplitter.
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
     * M�thode permettant de mettre � jour un message appartenant au LOG Serveur.
     * 
     * @param ipclient, adresse ip du client 
     * @param portclient, port de connexion du client
     * @param command, nom de la commande envoy�e par le client
     * @param nom, nom du fichier sujet � la commande (s'il existe)
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
     * M�thode permettant de s�parer le message exactement en deux : une commande et potentiellement un nom de fichier.
     * 
     * @param message, le texte � s�parer
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
