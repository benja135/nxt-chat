/**
 * TER Lego 2015 - Université Paul Sabatier
 * @author LACHERAY Benjamin
 * 
 */

package NXT;

import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

public class Client {

	
	private static ThreadStop emergencyStop = new ThreadStop();	
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		
	    emergencyStop.setDaemon(true);
	    emergencyStop.start();
		
		
		BTConnection btc;
		DataOutputStream dos;
		
		System.out.println("Wait for BT...");
		
		btc = Bluetooth.waitForConnection();
		dos = btc.openDataOutputStream();
		
		System.out.println("Connected !");
		
		ThreadReceive threadReceive = new ThreadReceive(btc);
		threadReceive.setDaemon(true);
		threadReceive.start();
		
		//TODO A MODIFIER SELON VOS ROBOTS ! Ici on défini juste un destinataire.
		String dest;
		if (Bluetooth.getLocalAddress().equals("00165318EB71")) {
			dest = "001653161388";
		} else {
			dest = "00165318EB71";
		}
		
		while (true) {

			// On envoie un message différent suivant sur quel bouton on appuie !
			
			if (Button.ENTER.isDown()) {
				while (!Button.ENTER.isUp()) {	// On attend que le bouton soit relaché
					Thread.yield();
				}
				System.out.println("Send");
				dos.writeUTF(dest + "-Bonjour");
				dos.flush();
			} else if (Button.LEFT.isDown()) {
				while (!Button.LEFT.isUp()) {
					Thread.yield();
				}
				System.out.println("Send");
				dos.writeUTF(dest + "-Ca va ?");
				dos.flush();
			} else if (Button.RIGHT.isDown()) {
				while (!Button.RIGHT.isUp()) {
					Thread.yield();
				}
				System.out.println("Send");
				dos.writeUTF(dest + "-Oui");
				dos.flush();
			}
			
			Thread.sleep(100);			
		}
	}
			

}
