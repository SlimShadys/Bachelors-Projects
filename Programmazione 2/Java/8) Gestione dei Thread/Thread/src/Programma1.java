public class Programma1 extends Thread{

	int x = 0;
	
	/*
	 * Sar� l'attesa del thread, espressa in millisecondi
	 * 2000 = 2 secondi
	 */
	int attesa = 2000;

	@Override
	public void run() {
		
		while(true) {
			
			System.out.println("X: " +x++); //Incrementa X e stampa
			
			try { //Try-Catch poich� � richiesto dalla classe Thread.
				Thread.sleep(attesa); //Metto in attesa il thread per 2 secondi, poi ricomincia.
									  //Altrimenti avrei una stampa di numeri infinita e velocissima.
			}
			catch (InterruptedException e) { //Lancio una eccezione quando il thread � interrotto
											 //Sia prima che dopo la sua attivit�.
											 //Si fa per prassi
				e.printStackTrace();
			}
		}

	}

}
