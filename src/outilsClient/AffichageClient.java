package outilsClient;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

/**
 * <b> AffichageClient est une classe permettant de gérer (analyser, séparer, afficher) les messages saisies dans la console du Client ou qui arrivent du serveur.</b>
 * <p>
 * Elle est caractérisée par les informations suivantes:
 * <ul>
 * <li>Une chaine de caractère permettant de récupérer le premier élément du message (généralement une commande) </li>
 * <li>Une chaine de caractère permettant de récupérer le deuxième élément du message (généralement un nom de fichier) </li>
 * </ul>
 * Elles sont mises à jour grace à la méthode messageSplitter.
 * <p>
 * @author Sandratra RASENDRASOA and Antonia FRANCIS
 */
public class AffichageClient {
	/**
     * Chaine de caractère permettant de récupérer le premier élément du message.
     */
	private String firstmessage;
	/**
     * Chaine de caractère permettant de récupérer le second élément du message.
     */
	private String secondmessage;
	
	/**
     * Constructeur d'AffichageClient, qui attribue une chaine de caractère vide à chaque message, en attente d'un messageSplitter.
     */
	public AffichageClient() {
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
     * Méthode permettant de séparer le message exactement en deux : une commande et potentiellement un nom de fichier.
     * 
     * @param message, le texte à séparer
     */
	public void messageSplitter(String message) {
		String[] splited = message.split("\\s+");
		if (splited.length == 1) {
			this.firstmessage = splited[0];
		}
		else if(splited.length == 2) {
			this.firstmessage = splited[0];
			this.secondmessage = splited[1];
		}
		else {
			System.out.println("Le message est vide ou trop long : ");	
		}
	}
	
	/**
     * Méthode permettant d'afficher en console le tableau de string reçu suite à la commande ls.
     * 
     * @param lsArray, le tableau à afficher.
     */
	public void arrayPrinter(String[] lsArray) {
        for(String name:lsArray) {
	         System.out.println(name);
        } 
	}
}
