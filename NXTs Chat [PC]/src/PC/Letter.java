/**
 * TER Lego 2015 - Université Paul Sabatier
 * @author LACHERAY Benjamin
 * 
 */

package PC;

/**
 * Une lettre contient un expediteur (String) et un message (String), 
 * pas d'accesseurs car les attributs sont publics.
 *
 */
public class Letter {

	public String expediteur;
	public String message;
	
	public Letter(String expediteur, String message) {
		this.expediteur = expediteur;
		this.message = message;
	}

}