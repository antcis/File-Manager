package outils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b>Classe permettant de valider une IP (en terme de format, et d'octets) .</b>
 * <p>
 * Elle est caractérisée par les informations suivantes, tous utilisés dans la vérification de format :
 * <ul>
 * <li>Un Pattern </li>
 * <li>Un Matcher </li>
 * <li>Un Pattern classique non modifiable (référence) </li>
 * </ul>
 * <p>
 * @author Sandratra RASENDRASOA et Antonia FRANCIS
 */
public class IPAddressValidator {
	/**
	 * Pattern nécessaire pour effectuer la verification d'expression pour une IP
	 */
	private Pattern pattern;
	/**
	 * Matcher nécessaire pour effectuer la verification d'expression pour une IP
	 */
    private Matcher matcher;
	/**
	 * Pattern définissant l'expression correcte d'une ip utilisée dans cette application
	 */
    private static final String IPADDRESS_PATTERN = 
    		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
    				"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
    				"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
    				"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	
    
    public IPAddressValidator(){
	  pattern = Pattern.compile(IPADDRESS_PATTERN);
    }
	  
    /////////////////////////////////////////////////////////FINAL
   /**
    * Valide l'expression de l'adresse IP saisie
    * @param ip la chaine de caractères correspondant à l'IP à valider.
    * @return true pour une ip valide, false pour une ip invalide
    */
    public boolean validateIP(final String ip){		  
	  matcher = pattern.matcher(ip);
	  return matcher.matches();	    	    
    }
    
    /**
     * Valide le format de l'adresse IP saisie
     * @param ip la chaine de caractères correspondant à l'IP à valider.
     * @return true pour une ip valide, false pour une ip invalide
     */
	public boolean validateFORMAT(final String ip) {
	    try {
	        String[] parts = ip.split( "\\." );
	        if ( parts.length != 4 ) {
	            return false;
	        }
	        return true;
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	}
}
