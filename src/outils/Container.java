package outils;

import java.io.Serializable;

/**
 * <b> La Classe Container permettant d'encapsuler en un unique paquet toutes les donn�es n�cessaires au serveur et au client.</b>
 * On cr�e ici une sorte de "Format Universel" envoyable via des Streams d'Objets, plus simple � initialiser et envoyer qu'un tableau de bytes � chaque cas.
 * On fait ainsi abstraction du type de donn�es qu'on envoie, toutes les possibilit�s sont g�r�es.
 * <p>
 * Elle est caract�ris�e par les informations suivantes, tous utilis�s dans la v�rification de format :
 * <ul>
 * <li>content, un tableau de byte n�cessaire � l'upload et au download </li>
 * <li>nomCommande, une chaine de caract�res permettant d'isoler la commande utilisateur </li>
 * <li>nomFichier, une chaine de caract�res permettant d'isoler le nom du fichier concern� </li>
 * <li>size, un entier permettant d'avoir la taille du tableau de bytes </li>
 * <li>servermessage, permettant au client d'afficher un message pr�par� par le serveur apr�s l'ex�cution de la commande. </li>
 * <li>lsarray, tableau de chaines de caract�res permettant d'envoyer la description du contenu du dossier courant </li>
 * </ul>
 * <p>
 * @author Sandratra RASENDRASOA et Antonia FRANCIS
 */
public class Container implements Serializable {
	/**
	 * Un tableau de byte n�cessaire � l'upload et au download
	 */
	private byte[] content;
	/**
	 * Une chaine de caract�res permettant d'isoler la commande utilisateur
	 */
	private String nomCommande;
	/**
	 * Une chaine de caract�res permettant d'isoler le nom du fichier concern�
	 */
	private String nomFichier;
	/**
	 * Un entier permettant d'avoir la taille du tableau de bytes 
	 */
	private Integer size;
	/**
	 * Une chaine de caract�res permettant au client d'afficher un message pr�par� par le serveur apr�s l'ex�cution de la commande.  
	 */
	private String servermessage;
	/**
	 * Tableau de chaines de caract�res permettant d'envoyer la description du contenu du dossier courant
	 */
	private String[] lsarray;
	
	/**
	 * Constructeur permettant d'instancier un container vide.
	 */
	public Container() {
		String[] inutile = {"message","inutile"};
		this.content = inutile[1].getBytes();
		this.nomFichier = "inutile";
		this.size = this.content.length;
		this.nomCommande = "inutile";
		this.servermessage = "inutile";
		this.lsarray = inutile;
	}
	
	/**
	 * Constructeur permettant d'instancier un container � partir de multiple param�tres.
	 * @param nomCommande, commande � stocker
	 * @param nomFichier, nom du fichier concern� � stocker
	 * @param content, tableau de bytes � stocker
	 * @param servermessage, message du serveur � stocker
	 * @param lsarray, tableau de chaine de caractr�res � stocker
	 */
	public Container(String nomCommande, String nomFichier, byte[] content,String servermessage,String[] lsarray) {
		this.content = content;
		this.nomFichier = nomFichier;
		this.size = this.content.length;
		this.nomCommande = nomCommande;
		this.servermessage = servermessage;
		this.lsarray = lsarray;
	}
	
	public byte[] getContent() {
		return this.content;
	}
	
	public String getNomFichier() {
		return this.nomFichier;
	}
	
	public Integer getSize() {
		return this.size;
	}
	
	public String getNomCommande() {
		return this.nomCommande;
	}
	
	public String getservermessage() {
		return this.servermessage;
	}
	
	public String[] getlsarray() {
		return this.lsarray;
	}
	
	public void setNomFichier(String nameFichier) {
		this.nomFichier = nameFichier;
	}
	
	public void setNomCommande(String nomCommande) {
		this.nomCommande = nomCommande;
	}
	
	public void setlsarray(String[] array) {
		this.lsarray = array;
	}
	public void setservermessage(String message) {
		this.servermessage = message;
	}
	public void setcontent(byte[] array) {
		this.content = array;
	}
	
	/**
	 * Methode permettant de v�rifier le contenu du paquet envoy�.
	 */
	public String toString() {
		System.out.println(" C�t� Client :");
		System.out.println(" nomCommande :" + this.nomCommande);
		System.out.println(" nomFichier :" + this.nomFichier);
		System.out.println(" size :" + this.size);
		System.out.println(" C�t� Serveur :");
		System.out.println(" servermessage:" + this.servermessage);
		System.out.println(" taille du lsarray :" + this.lsarray.length);
		return "fin du print";
	}
}
