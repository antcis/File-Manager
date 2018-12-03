package clienttcp;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.Scanner;


import outilsClient.AffichageClient;
import outils.Container;
import outils.IPAddressValidator;

/**
 * <b>ClientTCP est la classe représentant un client de l'application.</b>
 * <p>
 * Un Client est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une socket lui permettant de se connecter au serveur </li>
 * <li>Un object input stream qui lui permet de recevoir les données du serveur </li>
 * <li>Un object output stream qui lui permet d'envoyer des données au serveur</li>
 * </ul>
 * <p>
 * @author Sandratra RASENDRASOA et Antonia FRANCIS
 */
public class ClientTCP {
	/**
     * Socket Client, qui lui permet de se connecter au serveur.
     */
	private Socket socket;
	/**
     * Stream entrant, permettant de recevoir les fichiers et/ou messages du serveur.
     */
	private ObjectInputStream ois;
	/**
     * Stream sortant, permettant d'envoyer des fichiers et/ou des messages au serveur.
     */
	private ObjectOutputStream oos;
	
	/**
     * Constructeur ClientTCP.
     * <p>
     * A la construction d'un objet ClientTCP, on connecte la socket avec l'IP et le Port serveur indiqués par l'utilisateur.
     * Les Stream d'Objets sont alors crées.
     */
	public ClientTCP() {
		//Récupération de l'IP du serveur
		Scanner sc = new Scanner(System.in);
		System.out.println("Je suis un client : Saisir IP du serveur :");
		String ip = sc.nextLine();
		//Récupération du Port du serveur
		System.out.println("Saisir Port du serveur :");
		int port = sc.nextInt();
		System.out.println("Vous avez saisi en IP du serveur : " + ip + "\n");
		System.out.println("Vous avez saisi en Port du serveur : " + port + "\n");
		
		//Verification du format et du nombre d'octet de l'adresse saisie
		IPAddressValidator validator = new IPAddressValidator();
		while((!validator.validateIP(ip)) || (!validator.validateFORMAT(ip))) {
			System.out.println("Vous avez saisi une IP erronée !\n");
			System.out.println("De nouveau, saisir IP Serveur:");
			ip = sc.nextLine();
			System.out.println("Vous avez saisi en IP du serveur : " + ip + "\n");
			continue;
		}
		//Verification de la validité du numéro du port
		while((port > 5050) || (port < 5000)) {
			System.out.println("Vous avez saisi un Port erroné !\n");
			System.out.println("De nouveau, saisir Port Serveur :");
			port = sc.nextInt();
			System.out.println("Vous avez saisi en Port du serveur : " + port + "\n");
			continue;
		}
		
		try {
			this.socket = new Socket(InetAddress.getByName(ip), port);
			System.out.println("Tentative de connexion au Port : " + port);
			this.oos = new ObjectOutputStream(this.socket.getOutputStream());
			this.ois = new ObjectInputStream(this.socket.getInputStream());
			
	    } catch (UnknownHostException e) {
	    	System.err.println("L'adresse IP  " + ip + " n'a pas été trouvée ! ");
	         e.printStackTrace();
	    } catch (IOException e) {
	    	System.err.println("Erreur sur le port : " + port);
	         e.printStackTrace();
	    }
	}
	
	/**
     *Ferme la connexion côté Client.
     * <p>
     * La socket et les deux Streams sont fermés.
     * On ne ferme que côté Client la connexion, afin que la socket serveur soit toujours disponible pour accepter de nouveaux threads.
     */
	public void closeConnection() {
		try {
			this.socket.close();
			this.ois.close();
			this.oos.close();
			System.err.println("Vous avez été déconnecté avec succès");
		} catch (IOException e) {
			System.err.println("Can't close all connections");
			e.printStackTrace();
		}
	}

	
	/**
     *<b>Main Côté Client.</b>
     * @throws UnknownHostException
     * @throws IOException
     * @throws ClassNotFoundException
     */
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		// Creation d'un nouvel objet Client TCP et initialisation de tous les paramètres dans le constructeur.
		ClientTCP client = new ClientTCP();
		String[] inutile = {"message","inutile"};
		byte[] uselessContent = inutile[1].getBytes();

		while(true) {
			System.out.println("Saisir Commande  :");
			//Lecture des instructions de l'utilisateur
			Scanner scan = new Scanner(System.in);
			String usercommand = scan.nextLine();
			// Creation d'un objet AffichageClient qui permet d'analyser les données reçues de l'utilisateur
			AffichageClient usermessage = new AffichageClient();
			// Analyse des instructions de l'utilisateur 
			usermessage.messageSplitter(usercommand);
			
			/* ENVOI DE DONNEES */
			//Selon la commande (premier mot) entrée par l'utilisateur
			switch(usermessage.getfirstmessage()) {
			case "exit": 
				client.closeConnection();
				System.out.println("Deconnexion faite côté client");
				break;
			case "upload": 
				File uploading = new File(usermessage.getsecondmessage());
				byte[] uploadcontent = Files.readAllBytes(uploading.toPath());
				Container boxupload = new Container (usermessage.getfirstmessage(),usermessage.getsecondmessage(),uploadcontent,inutile[1] ,inutile );
				client.oos.writeObject(boxupload);
				client.oos.flush();
				break;
			default: 
				Container box = new Container (usermessage.getfirstmessage(),usermessage.getsecondmessage(),uselessContent, inutile[1] ,inutile);
				client.oos.writeObject(box);
		        client.oos.flush();
				break;
			}
			
			/* RECEPTION DE DONNEES */
			System.err.println("En attente de données Serveur ");
			Container received = (Container) client.ois.readObject();	
			// Création d'un objet lselements qui permet d'analyser les données reçues du serveur
			AffichageClient lselements = new AffichageClient();
			switch(received.getNomCommande())
			{
			case "ls":
				// on affiche les éléments du dossier courant
				lselements.arrayPrinter(received.getlsarray());			
				break;
			case "download":
				File f= new File(received.getNomFichier());
				Files.write(f.toPath(), received.getContent());
				System.out.println("Le fichier" + received.getNomFichier() +" a bien été downloaded");
				break;
			default:
				String servermessage = received.getservermessage();
				// on affiche le message obtenu du serveur après exécution de l'instruction par celui-ci
				System.out.println(servermessage);
				break;
			}	
		}
	}
}
