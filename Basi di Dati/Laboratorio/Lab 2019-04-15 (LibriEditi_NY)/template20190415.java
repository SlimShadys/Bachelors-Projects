/*

Prova pratica di PROGETTAZIONE DI BASI DI DATI
C.d.S. in Informatica e Tecnologie per la Produzione del Software (3 anni)
Docente: Prof.ssa Francesca A. Lisi

15 Aprile 2019 ore 15

-------------------------------------
Cognome e Nome	:
Matricola	:
-------------------------------------

*/


package application;

import java.sql.*;

public class <cognome-nome-matricola> {

	public static void main(String[] args) {
		// sezione dichiarazione variabili locali
		
		Connection connessione = null;

		

		// es. 1: creazione stringa contenente comando SQL
		String stringa1 = "";

		// es. 2: creazione stringa contenente comando SQL
		String stringa2 = "";

		// es. 3: creazione stringa contenente comando SQL
		String stringa3 = "";

		// es. 4: creazione stringa contenente comando SQL
		String stringa4 = "";

		// es. 5: creazione stringa contenente comando SQL
		String stringa5 = "";

		// es. 6: creazione stringa contenente comando SQL
		String stringa6 = "";

		// es. 7: creazione stringa contenente comando SQL
		String stringa7 = "";

		// es. 8: creazione stringa contenente comando SQL
		String stringa8 = "";
			
		try {
			// caricamento del driver
			new com.mysql.jdbc.Driver();
			/*
			  creazione di una connessione al database HenrysBooksDB20190415
			  con credenziali di accesso appropriate
			 */
			connessione = DriverManager.getConnection();

			// es. 1: esecuzione comando SQL
			Statement istruzione1 = connessione.createStatement();
			ok = istruzione1.executeUpdate(stringa1);
			
			System.out.println();

			// es. 2: esecuzione comando SQL
			Statement istruzione2 = connessione.createStatement();
			num = istruzione2.executeUpdate(stringa2);
			
			System.out.println("\n Il numero di tuple eliminate dalla tabella Editori è: " + num + " ");
			
			
			// es. 3: esecuzione comando SQL
			Statement istruzione3 = connessione.createStatement();
			istruzione3.execute(stringa3);

			System.out.println(...);

			
			// es. 4: esecuzione comando SQL
			Statement istruzione4 = connessione.createStatement();
			ResultSet risultato4 = istruzione4.executeQuery(stringa4);
			
			System.out.println("\n Il contenuto della vista LibriEditi_NY è:");
			while (risultato4.next()) {

			}
			
			// es. 5: esecuzione comando SQL
			Statement istruzione5 = connessione.createStatement();
			ResultSet risultato5 = istruzione5.executeQuery(stringa5);
			
			System.out.println("\n Le filiali in cui si vendono libri editi dello stato di New York sono:");
			while (risultato5.next()) {

			}
			
			// es. 6: esecuzione comando SQL
			Statement istruzione6 = connessione.createStatement();
			ResultSet risultato6 = istruzione6.executeQuery(stringa6);
			
			System.out.println("\n Titolo, tipo e filiale dei libri editi dello stato di New York:");
			while (risultato6.next()) {

			}
			
			// es. 7: esecuzione comando SQL
			Statement istruzione7 = connessione.createStatement();
			ResultSet risultato7 = istruzione7.executeQuery(stringa7);
			
			System.out.println("\n Il numero ed il prezzo medio dei libri pubblicati da ciascun autore:");
			while (risultato7.next()) {

			}

			// es. 8: esecuzione comando SQL
			Statement istruzione8 = connessione.createStatement();
			ResultSet risultato8 = istruzione8.executeQuery(stringa8);
			
			System.out.println("\n Le filiali in cui si vendono libri editi dello stato di New York sono:");
			while (risultato8.next()) {

			}
		} 
		catch (SQLException e) {
			e.printStackTrace();

		}

	}
}
