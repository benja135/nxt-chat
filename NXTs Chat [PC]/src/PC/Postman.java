/**
 * TER Lego 2015 - Université Paul Sabatier
 * @author LACHERAY Benjamin
 * 
 */

package PC;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Classe "facteur". Le facteur connait un ensemble d'association
 * adresse / boite aux lettres. Il sera connu de tout les threads 
 * qui pourront lui demander de poster des lettres aux bons endroits.
 * @author benja135
 *
 */
public class Postman {

	// Permet d'associer une adresse à une boite au lettre :)
	private Hashtable<String, LetterBox> letterBoxes;
	
	public Postman() {
		letterBoxes = new Hashtable<String, LetterBox>();
	}
	
	public void addLetterBox(String adresse, LetterBox letterBox) {
		letterBoxes.put(adresse, letterBox);
	}
	
	public void sendLetterTo(String adresse, Letter lettre) {
		letterBoxes.get(adresse).postLetter(lettre);
	}
		
	public void sendLetterToAll(Letter lettre) {
		Iterator<Entry<String, LetterBox>> it = letterBoxes.entrySet().iterator();
		
		while (it.hasNext()) {
			Entry<String, LetterBox> letterBox = it.next();
			if (letterBox.getKey() != lettre.expediteur) {
				letterBox.getValue().postLetter(lettre);
			}
		}
	}

}
