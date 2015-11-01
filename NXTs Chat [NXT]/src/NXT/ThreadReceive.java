/**
 * TER Lego 2015 - Université Paul Sabatier
 * @author LACHERAY Benjamin
 * 
 */

package NXT;

import java.io.DataInputStream;
import java.io.IOException;


import lejos.nxt.comm.BTConnection;

	public class ThreadReceive extends Thread {
		
		private DataInputStream dos;
		
		public ThreadReceive(BTConnection btc) {
			dos = btc.openDataInputStream();
		}
		
		public void run() {
			while (true) {
				try {
					String lettre = dos.readUTF();
					String expediteur = lettre.substring(0, 12);	// L'adresse est sur 12 char
					String message = lettre.substring(13);
					
					System.out.println(expediteur + " dit " + message);			
					
				} catch (IOException e) {
					// Survient en général quand la communication coté PC a été coupée
					
					e.printStackTrace();
					System.out.println("Read error");
					System.out.println("System will exit");
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						System.exit(1);
					}
					System.exit(1);
				}
			}
		}
	}