/*
	Prova di Laboratorio di PROGETTAZIONE DI BASI DI DATI
	C.d.S. in Informatica e Tecnologie per la Produzione del Software (3 anni)
	Docente: dott.ssa Francesca A. Lisi

	26 Febbraio 2015

	-------------------------------------
	Cognome e Nome	: Scarano Gianmarco
	Matricola	: 705627
	-------------------------------------
 */

import java.sql.*;

public class ProvaDB20150226 {

	public static void main(String[] args) {
		// sezione dichiarazione variabili locali

		Connection connessione;

		int num;
		int ok;

		int numeroLibri;
		double prezzoMedio;

		String nomeEditore;
		String titoloLibro;

		String nomeAutore;
		String cognomeAutore;

		// es. 1: creazione stringa contenente comando SQL
		String stringa1 = "ALTER TABLE Libri "
				+ "ADD FOREIGN KEY (Codice_editore) "
				+ "REFERENCES Editori(Codice_editore);";

		// es. 2: creazione stringa contenente comando SQL
		String stringa2 = "DELETE FROM Editori "
				+ "WHERE Nome_editore = 'Arcade Publishing';";

		// es. 3: creazione stringa contenente comando SQL
		String stringa3 = "CREATE VIEW LibriPubblicati_MA(Titolo_libro,Nome_editore) AS "
				+ "SELECT Titolo_libro, Nome_editore "
				+ "FROM Libri NATURAL JOIN Editori "
				+ "WHERE Stato_editore = 'MA';";

		// es. 4: creazione stringa contenente comando SQL
		String stringa4 = "SELECT * FROM LibriPubblicati_MA;";

		// es. 5: creazione stringa contenente comando SQL
		String stringa5 = "SELECT DISTINCT Cognome_autore, Nome_autore "
				+ "FROM Autori NATURAL JOIN LibriAutori NATURAL JOIN LibriPubblicati_MA "
				+ "ORDER BY Cognome_autore, Nome_autore;";

		// es. 6: creazione stringa contenente comando SQL
		String stringa6 = "SELECT Cognome_autore, Nome_autore, Titolo_libro, Nome_editore FROM Autori "
				+ "NATURAL LEFT JOIN LibriAutori "
				+ "NATURAL LEFT JOIN Libri "
				+ "NATURAL LEFT JOIN Editori "
				+ "ORDER BY Cognome_autore;";

		// es. 7: creazione stringa contenente comando SQL
		String stringa7 = "SELECT Nome_editore, COUNT(Codice_libro) AS NumeroLibri, AVG(Prezzo_libro) AS PrezzoMedio "
				+ "FROM Libri NATURAL JOIN Editori "
				+ "GROUP BY Codice_editore;";

		try {
			// caricamento del driver
			new com.mysql.jdbc.Driver();
			/*
			  creazione di una connessione al database HenrysBooksDB20150226
			  con credenziali di accesso appropriate
			 */
			connessione = DriverManager.getConnection("jdbc:mysql://localhost/HenrysBooksDB20150226", "root", "password");

			// es. 1: esecuzione comando SQL
			Statement istruzione1 = connessione.createStatement();
			ok = istruzione1.executeUpdate(stringa1);

			System.out.println("\n1) Il numero di tuple modificate nella tabella Libri è: " + ok + " ");

			System.out.println("\n||--------------------------------||");

			// es. 2: esecuzione comando SQL
			Statement istruzione2 = connessione.createStatement();
			num = istruzione2.executeUpdate(stringa2);

			System.out.println("\n2) Il numero di tuple eliminate dalla tabella Editori è: " + num + " ");

			System.out.println("\n||--------------------------------||");

			// es. 3: esecuzione comando SQL
			Statement istruzione3 = connessione.createStatement();
			istruzione3.execute(stringa3);

			System.out.println("\n3) Vista LibriPubblicati_MA creata con successo");

			System.out.println("\n||--------------------------------||");

			// es. 4: esecuzione comando SQL
			Statement istruzione4 = connessione.createStatement();
			ResultSet risultato4 = istruzione4.executeQuery(stringa4);

			System.out.println("\n4) Il contenuto della vista LibriPubblicati_MA è:");
			while (risultato4.next()) {

				titoloLibro = risultato4.getString("Titolo_libro");
				nomeEditore = risultato4.getString("Nome_editore");

				System.out.println("Titolo libro: " + titoloLibro);
				System.out.println("Nome editore: " + nomeEditore);
				System.out.println("----------------------------");
			}

			System.out.println("\n||--------------------------------||");

			// es. 5: esecuzione comando SQL
			Statement istruzione5 = connessione.createStatement();
			ResultSet risultato5 = istruzione5.executeQuery(stringa5);

			System.out.println("\n5) Gli autori di libri pubblicati da editori dello stato del Massachusetts sono:");
			while (risultato5.next()) {

				cognomeAutore = risultato5.getString("Cognome_autore");
				nomeAutore = risultato5.getString("Nome_autore");

				System.out.println("Cognome autore : " + cognomeAutore);
				System.out.println("Nome autore    : " + nomeAutore);
				System.out.println("----------------------------");

			}

			System.out.println("\n||--------------------------------||");

			// es. 6: esecuzione comando SQL
			Statement istruzione6 = connessione.createStatement();
			ResultSet risultato6 = istruzione6.executeQuery(stringa6);

			System.out.println("\n6) I Gli autori in ordine alfabetico per cognome sono:");
			while (risultato6.next()) {

				cognomeAutore = risultato6.getString("Cognome_autore");
				nomeAutore = risultato6.getString("Nome_autore");
				titoloLibro = risultato6.getString("Titolo_libro");
				nomeEditore = risultato6.getString("Nome_editore");

				System.out.println("Cognome autore : " + cognomeAutore);
				System.out.println("Nome autore    : " + nomeAutore);
				System.out.println("Titolo libro   : " + titoloLibro);
				System.out.println("Nome editore   : " + nomeEditore);				
				System.out.println("----------------------------");

			}

			System.out.println("\n||--------------------------------||");

			// es. 7: esecuzione comando SQL
			Statement istruzione7 = connessione.createStatement();
			ResultSet risultato7 = istruzione7.executeQuery(stringa7);

			System.out.println("\n7) Il numero ed il prezzo medio dei libri pubblicati da ciascun editore:");
			while (risultato7.next()) {

				nomeEditore = risultato7.getString("Nome_editore");
				numeroLibri = risultato7.getInt("NumeroLibri");
				prezzoMedio = risultato7.getDouble("PrezzoMedio");

				System.out.println("Nome editore   : " + nomeEditore);
				System.out.println("Numero libri   : " + numeroLibri);
				System.out.println("Prezzo medio   : " + prezzoMedio);
				System.out.println("----------------------------");

			}
		} 
		catch (SQLException e) {
			e.printStackTrace();

		}

	}
}
