/**
 * TER Lego 2015 - Université Paul Sabatier
 * @author LACHERAY Benjamin
 * 
 */

package NXT;
import lejos.nxt.Button;

/**
 * Ce thread permet d'interrompre un programme
 * simplement en appuyant sur le bouton ESCAPE (bouton du bas)
 *
 */
public class ThreadStop extends Thread {
	
	
	public void run() {
	
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.out.println("Sleep error");
			e.printStackTrace();
		}
		Button.ESCAPE.waitForPress();
		System.exit(0);

	}
}
