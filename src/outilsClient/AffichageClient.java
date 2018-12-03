package outilsClient;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

/**
 * <b> AffichageClient est une classe permettant de g�rer (analyser, s�parer, afficher) les messages saisies dans la console du Client ou qui arrivent du serveur.</b>
 * <p>
 * Elle est caract�ris�e par les informations suivantes:
 * <ul>
 * <li>Une chaine de caract�re permettant de r�cup�rer le premier �l�ment du message (g�n�ralement une commande) </li>
 * <li>Une chaine de caract�re permettant de r�cup�rer le deuxi�me �l�ment du message (g�n�ralement un nom de fichier) </li>
 * </ul>
 * Elles sont mises � jour grace � la m�thode messageSplitter.
 * <p>
 * @author Sandratra RASENDRASOA and Antonia FRANCIS
 */
public class AffichageClient {
	/**
     * Chaine de caract�re permettant de r�cup�rer le premier �l�ment du message.
     */
	private String firstmessage;
	/**
     * Chaine de caract�re permettant de r�cup�rer le second �l�ment du message.
     */
	private String secondmessage;
	
	/**
     * Constructeur d'AffichageClient, qui attribue une chaine de caract�re vide � chaque message, en attente d'un messageSplitter.
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
     * M�thode permettant de s�parer le message exactement en deux : une commande et potentiellement un nom de fichier.
     * 
     * @param message, le texte � s�parer
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
     * M�thode permettant d'afficher en console le tableau de string re�u suite � la commande ls.
     * 
     * @param lsArray, le tableau � afficher.
     */
	public void arrayPrinter(String[] lsArray) {
        for(String name:lsArray) {
	         System.out.println(name);
        } 
	}
}
