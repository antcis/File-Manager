package outilsServeur;
import java.io.File;


/**
 * <b> CommandesConsole est une classe permettant d'effectuer au niveau du serveur de stockage les instructions utilisateurs transmises par le client.</b>
 * <p>
 * Elle est caractérisée par les informations suivantes:
 * <ul>
 * <li>Un file f, utilisé dans le cadre des commandes ls et mkdir. </li>
 * <li>Une chaine de caractères correspondant au dossier courant. </li>
 * </ul>
 * <p>
 * @author Sandratra RASENDRASOA et Antonia FRANCIS
 */
public class CommandesConsole {
	/**
	 * File f, utilisé dans le cadre des commandes ls et mkdir.
	 */
	//private File f;
	/**
	 * Chaine de caractères correspondant au dossier courant, mise à jour dans le cas d'un déplacement.
	 */
	//private String currentDirectory;
	
	private File f = null;
	private String currentDirectory = null;
	/**
	 * Constructeur de CommandesConsole qui met simplement à jour le dossier courant.
	 */
	public CommandesConsole() {
		this.currentDirectory = System.getProperty("user.dir");
	}

	/**
	 * Permet d'exécuter la commande mkdir.
	 * 
	 * @param path, un nom de fichier
	 * @return le message du serveur (chaine de caractère) qui sera transmis au client et affiché par la suite sur la console client.
	 */
	public String mkdirCommand(String path) {
		try {  
	         this.f = new File(this.currentDirectory + "\\" + path);
	         boolean res = this.f.mkdirs();
	         String ans = "Le dossier "+ path +" a été créé";
	         return ans;
	         
	      } catch(Exception e) {
	    	  e.printStackTrace();
	    	  String ans = "Exception : Le dossier "+ path +" n'a pas été créé ";
		      return ans;
	      }
	}
	
	/**
	 * Permet d'exécuter la commande ls.
	 * 
	 * @return un tableau de chaine de caractères décrivant le contenu du dossier courant.
	 */
	public String[] lsCommand() {
		String[] paths ;
		this.f = new File(this.currentDirectory);
        paths = this.f.list();
        int j = 0;
		String[] folderlist = new String[paths.length];
		try {
	         if (paths.length == 0) {
	        	 System.out.println("It is empty");    	 
	         }
	         else {
	        	 for(String name:paths) {
	        		 
	 	        	if (new File(this.currentDirectory + "\\" + name).isDirectory()) {
	 	        		// On inscrit le nom du dossier
	 		           folderlist[j] = ("[Folder] "+name);
	 		           j++;
	 	        	}
	 	        	else if (new File(this.currentDirectory  + "\\" + name).isFile()){
	 	        		// On inscrit le nom du fichier 
	 	        		folderlist[j] = ("[File] "+name);
	 			        j++;
	 	        	}    
	 	         }  
	         }
	         return folderlist;
		}
		
		catch(Exception e) {
			// if any error occurs
	         e.printStackTrace();
	         //test return null
	         folderlist[0] = "Exception : La liste d'éléments n'a pas été créée";
			 return folderlist;
		}
	}
	
	/**
	 * Permet d'exécuter la commande cd
	 * 
	 * @param path, qui peut indiquer un nom de dossier à explorer ou ../ qui indique un retour en arrière dans l'arborescence.
	 * @return le message à renvoyer au client
	 */
	public String cdCommand(String path) {
		try {
			StringSplitter splitter = new StringSplitter(this.currentDirectory);
			String ans = "Vous êtes dans le dossier";
			if (path.equals("..")) {
				this.currentDirectory = splitter.split(this.currentDirectory);
				ans += " PARENT "+ this.currentDirectory;
			}
			else {
				this.currentDirectory = this.currentDirectory + "\\" + path;
				ans += " FILS "+ this.currentDirectory;
			}
	        return ans;

		} catch (Exception e) {
			e.printStackTrace();
	    	String ans = "Exception : Je ne sais pas où vous etes";
		    return ans;
		}
	}
	



}
	
