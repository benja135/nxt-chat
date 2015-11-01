/**
 * TER Lego 2015 - Université Paul Sabatier
 * @author LACHERAY Benjamin
 * 
 */

package PC;

import java.io.IOException;


public class ThreadMaster extends Thread {
			
		private NXTConnection myNXT;
		private String myAddress;
		private LetterBox myLetterBox;
		private Postman postman;
		
		/**
		 * Classe interne : Thread de lecture (car le read est bloquant)
		 * Interne car plus simple à faire... les variables sont déjà connues
		 *
		 */
		public class ThreadMasterReceive extends Thread {
			
			public void run() {
				while (true) {
					try {			
						String message = myNXT.receive();
						String[] cut = message.split("-");
						// Si on a pas précisé de destinataire, on envoie à tout le monde
						if (cut.length == 1) {
							postman.sendLetterToAll(new Letter(myAddress, message));
						} else if (cut.length == 2) {
							// Si l'adresse BT du destinataire n'est pas bonne
							if (cut[0].length() != 12) {
								System.out.println("Message reçu mal formé");
							} else {
								postman.sendLetterTo(cut[0], new Letter(myAddress, cut[1]));
							}
						} else {
							System.out.println("Message reçu mal formé");
						}
						
						
					} catch (IOException | InterruptedException e) {
						// Exceptions déjà traitées dans NXTConnection
						System.out.println("Problème lors de la lecture");
						e.printStackTrace();
					}
				}
			}
		}
				
		/**
		 * Constructeur
		 * @param adresse	adresse du robot
		 * @param messages	liste partagée entre les threads où les messages sont contenus 
		 */
		public ThreadMaster (String adresse, LetterBox letterBox, Postman postman) {
			myNXT = new NXTConnection(adresse);
			this.myAddress = adresse;		
			this.myLetterBox = letterBox;
			this.postman = postman;
		}

		public void run() {
				
			myNXT.connexion();

			ThreadMasterReceive receive = new ThreadMasterReceive();
			receive.setDaemon(true);
			receive.start();
					
			while (true) {				
											
				// Si on a un nouveau message
				if (myLetterBox.newLetter()) {
					Letter lettre = myLetterBox.getLetter();
					System.out.println("De " + lettre.expediteur + " vers " + myAddress + " : "  + lettre.message);
					myNXT.send(lettre.expediteur + "-"  + lettre.message);
				}
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("Probléme d'interruption...");
					e.printStackTrace();
				}		
			}
			
		}

	}