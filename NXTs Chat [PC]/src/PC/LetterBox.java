/**
 * TER Lego 2015 - Université Paul Sabatier
 * @author LACHERAY Benjamin
 * 
 */

package PC;

import java.util.ArrayList;

/**
 * Une boite aux lettres est une liste de lettre. Chaque thread
 * communiquant avec un NXT disposera de sa propre boite.
 * Les méthodes sont "synchronized" car une boite au lettre pourrait
 * être utilisée en même temps par plusieurs threads, de cette maniére 
 * on évite tout conflits.
 *
 */
public class LetterBox {

	private ArrayList<Letter> letterBox;
	
	public LetterBox() {
		letterBox = new ArrayList<Letter>();
	}
	
	public synchronized void postLetter(Letter lettre) {
		letterBox.add(lettre);
	}
	
	public synchronized boolean newLetter() {
		return letterBox.size() > 0;
	}
	
	public synchronized Letter getLetter() {
		return letterBox.remove(0);
	}
	
}
