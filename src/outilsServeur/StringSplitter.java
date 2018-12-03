package outilsServeur;

/**
 * <b> StringSplitter est une classe permettant de gérer les chemins des fichiers.</b>
 * Attention, elle est différente de messageSplitter qui sépare la Commande du Nom du fichier concerné.
 * <p>
 * Elle est caractérisée par les informations suivantes:
 * <ul>
 * <li>un entier lastBackslash, le dernier "\" obtenu, utile pour remonter au dossier précédent</li>
 * <li>un StringBuilder sb, gestionnaire de la chaine de caractère dont on veut isoler un morceau </li>
 * </ul>
 * <p>
 * @author Sandratra RASENDRASOA et Antonia FRANCIS
 */
public class StringSplitter {
	/**
	 * Entier permettant d'obtenir le dernier backslash obtenu
	 */
	private int lastBackslash;
	
	/**
	 * StringBuilder, qui permet la gestion de la chaine de caractères à travailler.
	 * 
	 */
	private StringBuilder sb;
	
	/**
	 * Contructeur de StringSplitter
	 * 
	 * @param path, la chaine de caractères à travailler, correspondant au chemin d'un objet quelconque.
	 */
	public StringSplitter(String path) {
		this.lastBackslash = path.lastIndexOf("\\");
		this.sb = new StringBuilder();
	}
	
	/**
	 * Méthode permettant de récupérer uniquement tout ce qui se situe avant le dernier backslash, et supprimer le reste.
	 * 
	 * @param path, la chaine de caractères à travailler, correspondant au chemin d'un objet.
	 * 
	 * @return le texte se situant avant le dernier backslash
	 */
	public String split(String path) {
		this.sb.append(path);
		this.sb.delete(this.lastBackslash, this.sb.length());
		return sb.toString();
	}

	
}
