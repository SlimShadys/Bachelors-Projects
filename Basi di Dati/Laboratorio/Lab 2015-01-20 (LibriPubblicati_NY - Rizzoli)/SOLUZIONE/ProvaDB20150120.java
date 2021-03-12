/*
	Prova di Laboratorio di PROGETTAZIONE DI BASI DI DATI
	C.d.S. in Informatica e Tecnologie per la Produzione del Software (3 anni)
	Docente: dott.ssa Francesca A. Lisi

	20 Gennaio 2015

	-------------------------------------
	Cognome e Nome	: Scarano Gianmarco
	Matricola	: 705627
	-------------------------------------
 */

import java.sql.*;

public class ProvaDB20150120 {

	public static void main(String[] args) {
		// sezione dichiarazione variabili locali

		Connection connessione = null;
		int num;
		int ok;
		int numeroAutori;
		int numeroLibri;
		double prezzoMedio;
		String titoloLibro;
		String nomeEditore;
		String nomeAutore;
		String cognomeAutore;
		String cittaEditore;

		// es. 1: creazione stringa contenente comando SQL
		String stringa1 = "ALTER TABLE Libri "
				+ "ADD FOREIGN KEY (Codice_editore) "
				+ "REFERENCES Editori(Codice_editore);";

		// es. 2: creazione stringa contenente comando SQL
		String stringa2 = "DELETE FROM Editori "
				+ "WHERE Nome_editore = 'Rizzoli';";

		// es. 3: creazione stringa contenente comando SQL
		String stringa3 = "CREATE VIEW LibriPubblicati_NY(Titolo_libro, Nome_editore) AS "
				+ "SELECT Titolo_libro, Nome_editore "
				+ "FROM Libri NATURAL JOIN Editori "
				+ "WHERE Stato_editore = 'NY';";

		// es. 4: creazione stringa contenente comando SQL
		String stringa4 = "SELECT * FROM LibriPubblicati_NY;";

		// es. 5: creazione stringa contenente comando SQL
		String stringa5 = "SELECT DISTINCT Cognome_autore, Nome_autore "
				+ "FROM Autori NATURAL JOIN LibriAutori NATURAL JOIN Libri NATURAL JOIN LibriPubblicati_NY "
				+ "ORDER BY Cognome_autore, Nome_autore;";

		// es. 6: creazione stringa contenente comando SQL
		String stringa6 = "SELECT Titolo_libro, COUNT(Nro_autore) AS NumeroAutori, Nome_editore, Citta_editore "
				+ "FROM LibriAutori NATURAL JOIN Libri NATURAL JOIN Editori "
				+ "GROUP BY Titolo_libro "
				+ "ORDER BY Titolo_libro;";

		// es. 7: creazione stringa contenente comando SQL
		String stringa7 = "SELECT Nome_editore, COUNT(Codice_editore) AS NumeroLibri, CAST(AVG(Prezzo_libro) AS DECIMAL(6,2)) AS PrezzoMedio "
				+ "FROM Editori NATURAL JOIN Libri "
				+ "GROUP BY Codice_editore;";

		try {
			// caricamento del driver
			new com.mysql.jdbc.Driver();
			/*
				  creazione di una connessione al database HenrysBooksDB20140120
				  con credenziali di accesso appropriate
			 */
			connessione = DriverManager.getConnection("jdbc:mysql://localhost/henrysbooksdb20150120", "root", "password");

			// es. 1: esecuzione comando SQL
			Statement istruzione1 = connessione.createStatement();
			ok = istruzione1.executeUpdate(stringa1);

			System.out.println("\n1) Correttamente inserito il vincolo di integrità referenziale tra le tabelle Libri ed Editori");
			System.out.println("\nNumero di tuple modificate: " + ok);

			System.out.println("\n\\-----------------------------//");

			// es. 2: esecuzione comando SQL
			Statement istruzione2 = connessione.createStatement();
			num = istruzione2.executeUpdate(stringa2);

			System.out.println("\n2) Il numero di tuple eliminate dalla tabella Editori è: " + num + " ");

			System.out.println("\n\\-----------------------------//");

			// es. 3: esecuzione comando SQL
			Statement istruzione3 = connessione.createStatement();
			istruzione3.execute(stringa3);

			System.out.println("\n3) Vista LibriPubblicati_NY creata con successo");

			System.out.println("\n\\-----------------------------//");

			// es. 4: esecuzione comando SQL
			Statement istruzione4 = connessione.createStatement();
			ResultSet risultato4 = istruzione4.executeQuery(stringa4);

			System.out.println("\n4) Il contenuto della vista LibriPubblicati_NY è:");
			while (risultato4.next()) {
				titoloLibro = risultato4.getString("Titolo_libro");
				nomeEditore = risultato4.getString("Nome_editore");

				System.out.println("Titolo libro: " + titoloLibro);
				System.out.println("Nome editore: " + nomeEditore);
				System.out.println("-----------------------------");

			}

			System.out.println("\n\\-----------------------------//");

			// es. 5: esecuzione comando SQL
			Statement istruzione5 = connessione.createStatement();
			ResultSet risultato5 = istruzione5.executeQuery(stringa5);

			System.out.println("\n5) Gli autori di libri pubblicati da editori dello stato di New York sono:");
			while (risultato5.next()) {
				cognomeAutore = risultato5.getString("Cognome_autore");
				nomeAutore = risultato5.getString("Nome_autore");

				System.out.println("Nome autore: " + nomeAutore + "\tCognome autore: " + cognomeAutore);
				System.out.println("-----------------------------");
			}

			System.out.println("\n\\-----------------------------//");

			// es. 6: esecuzione comando SQL
			Statement istruzione6 = connessione.createStatement();
			ResultSet risultato6 = istruzione6.executeQuery(stringa6);

			System.out.println("\n6) I libri in ordine alfabetico di titolo sono:");
			while (risultato6.next()) {
				titoloLibro = risultato6.getString("Titolo_libro");
				numeroAutori = risultato6.getInt("NumeroAutori");
				nomeEditore = risultato6.getString("Nome_editore");
				cittaEditore = risultato6.getString("Citta_editore");

				System.out.println("Titolo libro  : " + titoloLibro);
				System.out.println("Numero autori : " + numeroAutori);
				System.out.println("Nome editore  : " + nomeEditore);
				System.out.println("Città editore : " + cittaEditore);
				System.out.println("-----------------------------");
			}

			System.out.println("\n\\-----------------------------//");

			// es. 7: esecuzione comando SQL
			Statement istruzione7 = connessione.createStatement();
			ResultSet risultato7 = istruzione7.executeQuery(stringa7);

			System.out.println("\n7) Il numero ed il prezzo medio dei libri pubblicati da ciascun editore:");
			while (risultato7.next()) {
				nomeEditore = risultato7.getString("Nome_editore");
				numeroLibri = risultato7.getInt("NumeroLibri");
				prezzoMedio = risultato7.getDouble("PrezzoMedio");

				System.out.println("Nome editore : " + nomeEditore);
				System.out.println("Numero libri : " + numeroLibri);
				System.out.println("Prezzo medio : " + prezzoMedio);
				System.out.println("-----------------------------");

			}
		} 
		catch (SQLException e) {
			e.printStackTrace();

		}

	}
}
