package outils;

import java.io.Serializable;

/**
 * <b> La Classe Container permettant d'encapsuler en un unique paquet toutes les données nécessaires au serveur et au client.</b>
 * On crée ici une sorte de "Format Universel" envoyable via des Streams d'Objets, plus simple à initialiser et envoyer qu'un tableau de bytes à chaque cas.
 * On fait ainsi abstraction du type de données qu'on envoie, toutes les possibilités sont gérées.
 * <p>
 * Elle est caractérisée par les informations suivantes, tous utilisés dans la vérification de format :
 * <ul>
 * <li>content, un tableau de byte nécessaire à l'upload et au download </li>
 * <li>nomCommande, une chaine de caractères permettant d'isoler la commande utilisateur </li>
 * <li>nomFichier, une chaine de caractères permettant d'isoler le nom du fichier concerné </li>
 * <li>size, un entier permettant d'avoir la taille du tableau de bytes </li>
 * <li>servermessage, permettant au client d'afficher un message préparé par le serveur après l'exécution de la commande. </li>
 * <li>lsarray, tableau de chaines de caractères permettant d'envoyer la description du contenu du dossier courant </li>
 * </ul>
 * <p>
 * @author Sandratra RASENDRASOA et Antonia FRANCIS
 */
public class Container implements Serializable {
	/**
	 * Un tableau de byte nécessaire à l'upload et au download
	 */
	private byte[] content;
	/**
	 * Une chaine de caractères permettant d'isoler la commande utilisateur
	 */
	private String nomCommande;
	/**
	 * Une chaine de caractères permettant d'isoler le nom du fichier concerné
	 */
	private String nomFichier;
	/**
	 * Un entier permettant d'avoir la taille du tableau de bytes 
	 */
	private Integer size;
	/**
	 * Une chaine de caractères permettant au client d'afficher un message préparé par le serveur après l'exécution de la commande.  
	 */
	private String servermessage;
	/**
	 * Tableau de chaines de caractères permettant d'envoyer la description du contenu du dossier courant
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
	 * Constructeur permettant d'instancier un container à partir de multiple paramètres.
	 * @param nomCommande, commande à stocker
	 * @param nomFichier, nom du fichier concerné à stocker
	 * @param content, tableau de bytes à stocker
	 * @param servermessage, message du serveur à stocker
	 * @param lsarray, tableau de chaine de caractrères à stocker
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
	 * Methode permettant de vérifier le contenu du paquet envoyé.
	 */
	public String toString() {
		System.out.println(" Côté Client :");
		System.out.println(" nomCommande :" + this.nomCommande);
		System.out.println(" nomFichier :" + this.nomFichier);
		System.out.println(" size :" + this.size);
		System.out.println(" Côté Serveur :");
		System.out.println(" servermessage:" + this.servermessage);
		System.out.println(" taille du lsarray :" + this.lsarray.length);
		return "fin du print";
	}
}
