/**
 * TER Lego 2015 - Université Paul Sabatier
 * @author LACHERAY Benjamin
 * 
 */

package PC;

public class Master {
	
	/* Cette variable sera partagée par tout les threads
	 * Il s'agit du "facteur" qui déposera les lettres 
	 * dans les bonnes boites aux lettres.
	 */
	public static Postman postman;
	
	/**
	 * @param ad adresse des NXT à faire communiquer (pas de limite en nombre !)
	 */
	public Master(String... ad) {
		if (ad.length < 2) {
			System.out.println("Il y a moins de 2 robots. Le programme va quitter.");
			System.exit(1);
		} else {
			postman = new Postman();
			// On lance un thread pour chaque NXT passé en paramétre !
			for (int i = 0; i < ad.length; i++) {	
				// On ajoute une nouvelle boite aux lettres au facteur !
				LetterBox letterBox = new LetterBox();
				postman.addLetterBox(ad[i], letterBox);
				// On cré le thread i en lui donnant son adresse, sa boite, et le facteur
				ThreadMaster threadMaster = new ThreadMaster(ad[i], letterBox, postman);
				threadMaster.setDaemon(true);
				threadMaster.start();
			}		
		}
	}
		
	
	/**
	 * Méthode main !
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		// TODO A CHANGER SELON VOS ROBOTS !
		new Master("00165318EB71", "001653161388");	
		
		int min = 0;
		while (true) {
			Thread.sleep(60000);
			min++;
			System.out.println("Serveur en marche depuis " + min + " minute(s).");
		}
	}

}
