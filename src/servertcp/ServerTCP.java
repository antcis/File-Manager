package servertcp;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;


import outilsServeur.*;
import outils.Container;
import outils.IPAddressValidator;

/**
 * <b>ServerTCP est la classe représentant un serveur de l'application.</b>
 * <p>
 * Le serveur peut supporter plusieurs threads clients.
 * A chaque nouvelle requête de connexion d'un client, 
 * on instancie un nouvel objet ClientHandlerTCP, lié à un thread particulier.
 * 
 * Nous avons choisi de faire uniquement un main car les attributs nécessaires
 * à l'établissement de la connexion sont inclus dans la classe ClientHandlerTCP.
 * </p>
 * <p>
 * @author Sandratra RASENDRASOA and Antonia FRANCIS
 */
public class ServerTCP{
	/**
     *<b>Main Côté Serveur.</b>
     * @throws IOException
     */
	public static void main(String[] args) throws IOException {
		//Récupération de l'IP du serveur
		Scanner sc = new Scanner(System.in);
		System.out.println("Je suis le serveur de l'application : Saisir IP serveur :");
		String ip = sc.nextLine();
		//Récupération du Port du serveur
		System.out.println("Saisir Port d`ecoute:");
		int port = sc.nextInt();
		System.out.println("Vous avez saisi en IP du serveur : " + ip + "\n");
		System.out.println("Vous avez saisi en Port du serveur : " + port + "\n");
				
		//Verification du format et du nombre d'octet de l'adresse saisie
		IPAddressValidator validator = new IPAddressValidator();
		while((!validator.validateIP(ip)) || (!validator.validateFORMAT(ip))) {
			System.out.println("Vous avez saisi une IP erronée !\n");
			System.out.println("De nouveau, saisir IP Serveur:");
			ip = sc.nextLine();
			System.out.println("Vous avez saisi en IP serveur : " + ip + "\n");
			continue;
		}

		//Verification de la validité du numéro du port
		while((port > 5050) || (port < 5000)) {
			System.out.println("Vous avez saisi un Port erroné !\n");
			System.out.println("De nouveau, saisir Port Serveur: :");
			port = sc.nextInt();
			System.out.println("Vous avez saisi en Port du serveur : " + port + "\n");
			continue;
		}		
		
		ServerSocket ss = new ServerSocket(port);
		Socket soc;
		
		while(true) {
			try {
				//A chaque nouvelle requête de connexion d'un client, 
				soc = ss.accept();
				System.out.println("Connected on port :" + port);
				//on instancie un nouvel objet ClientHandler qui va servir à supporter un nouveau thread.
				ClientHandlerTCP ch = new ClientHandlerTCP(soc);
				Thread t = new Thread(ch); 
				t.start();
			}
			catch(Exception e){
				System.err.println("Le port " + port + " est peut-etre déjà utilisé ! ");
				e.printStackTrace();
			}
		}
	}	
}

/**
 * <b>ClientHandlerTCP est une classe interne permettant au serveur de supporter les multiples clients.</b>
 * <p>
 * Elle est liée à un thread client particulier et permet les échanges d'instructions, fichiers et messages.
 * Un ClientHandlerTCP est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une socket lui permettant de se connecter au client ayant demandé une connexion </li>
 * <li>Un object input stream qui lui permet de recevoir les données du client </li>
 * <li>Un object output stream qui lui permet d'envoyer des données au client</li>
 * <li>Un object CommandesConsoles qui permet d'exécuter les commandes entrées en console par l'utilisateur, et transmises au serveur via le client.
 * </ul>
 * </p>
 * <p>
 * @author Sandratra RASENDRASOA & Antonia FRANCIS
 */
class ClientHandlerTCP implements Runnable{
	/**
     * Socket, qui lui permet de se connecter au client de ce thread.
     */
	private Socket socket;
	/**
     * Stream entrant, permettant de recevoir les fichiers et/ou messages du client.
     */
	private ObjectInputStream ois;
	/**
     * Stream entrant, permettant d'envoyer des fichiers et/ou messages du client.
     */
	private ObjectOutputStream oos;
	/**
     * Objet permettant d'appliquer des methodes correspondant aux instructions entrées en console.
     */
	private CommandesConsole cc;
	
	/**
     * Constructeur ClientHandlerTCP.
     * 
     * @param soc la socket qui permet de nous connecteur au bon client.
     */
	public ClientHandlerTCP(Socket soc) {
		try {
	            this.socket = soc;
	    		this.oos = new ObjectOutputStream(this.socket.getOutputStream());
				this.ois = new ObjectInputStream(this.socket.getInputStream());
				this.cc = new CommandesConsole();
				
	    } catch (IOException e) {
	            e.printStackTrace();
	    }
	}
	
	/**
     * Méthode qui se déclenche quand on utilise la méthode Start du Thread correspondant.
     */
	public void run() {
		try {
			String[] inutile = {"message","inutile"};
			byte[] uselessContent = inutile[1].getBytes();
			String space = " ";
			//Le booleen bool permet de voir si le client est toujours connecté.
			Boolean bool = true;
			//Tant qu'on ne remarque pas que le client correspondant a fermé sa socket de connexion.
			while(bool){
				
				/* RECEPTION DE DONNEES */
				System.err.println("En attente de données ");
				//Le container est le "format universel" qui encapsule toutes les données possiblement nécessaires à la communication.
				Container received = new Container();
				try {
					received = (Container) this.ois.readObject();
				} catch (EOFException e1) {
					//Il y a eu deconnexion du client
					System.err.println("Le client s'est déconnecté!");
					bool = false;
					//e1.printStackTrace();
					continue;
				}	
				// Création d'un objet AffichageServeur qui permet d'analyser les informations reçues de l'utilisateur via le client.
				AffichageServeur elements = new AffichageServeur();
				//Le container est le "format universel" qui encapsule toutes les données possiblement nécessaires à la communication.
				Container tosend = new Container(received.getNomCommande(),received.getNomFichier(),uselessContent, inutile[1] ,inutile);
				String ip = this.socket.getInetAddress().getHostAddress();
				String port = Integer.toString(this.socket.getPort());
				
				/* ANALYSE DES PAQUETS RECUS ET ENVOI DE DONNEES */
				switch(received.getNomCommande())
				{
				case "ls": //ok
					System.out.println(elements.giveInformations(ip, port, received.getNomCommande(), space));
					tosend.setlsarray(this.cc.lsCommand());
					break;
				case "download":
					try {
						System.out.println(elements.giveInformations(ip, port, received.getNomCommande(),received.getNomFichier()));
						File downloading = new File(received.getNomFichier());
						byte[] downloadcontent = Files.readAllBytes(downloading.toPath());
						tosend.setcontent(downloadcontent);
					} catch (NoSuchFileException e) {
						System.out.println("This file don't exist, please try again");
						e.printStackTrace();
					}
					break;
				case "upload":
					try {
						System.out.println(elements.giveInformations(ip, port, received.getNomCommande(),received.getNomFichier()));
						File f= new File(received.getNomFichier());
						Files.write(f.toPath(), received.getContent());
						tosend.setservermessage("Le fichier" + received.getNomFichier() +" a bien été upload");
						break;
					} catch (NoSuchFileException e) {
						System.out.println("This file don't exist, please try again");
						e.printStackTrace();
					}
				case "mkdir"://ok
					System.out.println(elements.giveInformations(ip, port, received.getNomCommande(),received.getNomFichier()));
					tosend.setservermessage(this.cc.mkdirCommand(received.getNomFichier()));
					break;
				case "cd": //ok
					System.out.println(elements.giveInformations(ip, port, received.getNomCommande(),received.getNomFichier()));
					tosend.setservermessage(this.cc.cdCommand(received.getNomFichier()));
					break;
				}
				this.oos.writeObject(tosend);
				this.oos.flush();	
			}
			System.err.println("Client Handler terminated!");	
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}

