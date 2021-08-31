/*

Prova di Laboratorio di PROGETTAZIONE DI BASI DI DATI
C.d.S. in Informatica e Tecnologie per la Produzione del Software (3 anni)
Docente: dott.ssa Francesca A. Lisi

1 Marzo 2017

-------------------------------------
Cognome e Nome	: Scarano Gianmarco
Matricola	: 705627
-------------------------------------

 */

import java.sql.*;

public class ProvaDB20170301 {

	public static void main(String[] args) {
		// sezione dichiarazione variabili locali

		Connection connessione = null;

		int num;
		int ok;

		String codiceLibro;
		String nomeFiliale;
		String titoloLibro;
		String tipoLibro;
		double prezzoLibro;
		int copieDisponibili;

		// es. 1: creazione stringa contenente comando SQL
		String stringa1 = "ALTER TABLE Scorte ADD FOREIGN KEY (Nro_filiale) REFERENCES Filiali(Nro_filiale);";

		// es. 2: creazione stringa contenente comando SQL
		String stringa2 = "UPDATE Scorte NATURAL JOIN Libri "
				+ "SET Nro_copie_disponibili = (Nro_copie_disponibili - 1) "
				+ "WHERE Codice_libro = '6128' AND Nro_filiale = '3';";

		// es. 3: creazione stringa contenente comando SQL
		String stringa3 = "CREATE VIEW LibriSottoScorta_HB(Codice_libro) AS "
				+ "SELECT Codice_libro FROM Libri NATURAL JOIN Scorte NATURAL JOIN Filiali "
				+ "WHERE Nro_copie_disponibili < 3 AND Nome_filiale = 'Henrys Brentwood';";

		// es. 4: creazione stringa contenente comando SQL
		String stringa4 = "SELECT * FROM LibriSottoScorta_HB ORDER BY Codice_libro DESC;";

		// es. 5: creazione stringa contenente comando SQL
		String stringa5 = "SELECT Nome_filiale, Titolo_libro, Nro_copie_disponibili "
				+ "FROM Libri NATURAL JOIN Scorte NATURAL JOIN Filiali "
				+ "ORDER BY Nome_filiale, Titolo_libro;";

		// es. 6: creazione stringa contenente comando SQL
		String stringa6 = "SELECT Codice_libro, Titolo_libro, Nro_copie_disponibili FROM Libri NATURAL JOIN Scorte NATURAL JOIN LibriSottoScorta_HB "
				+ "WHERE Nro_copie_disponibili = (SELECT MIN(Nro_copie_disponibili) FROM LibriSottoScorta_HB NATURAL JOIN Scorte);";

		// es. 7: creazione stringa contenente comando SQL
		String stringa7 = "SELECT Titolo_libro, Tipo_libro, Prezzo_libro FROM Libri NATURAL JOIN Scorte NATURAL JOIN Filiali "
				+ "WHERE Codice_libro NOT IN (SELECT Codice_libro FROM LibriSottoScorta_HB) "
				+ "ORDER BY Titolo_libro;";

		try {
			// caricamento del driver
			new com.mysql.jdbc.Driver();
			/*
			  creazione di una connessione al database HenrysBooksDB20170301
			  con credenziali di accesso appropriate
			 */
			connessione = DriverManager.getConnection("jdbc:mysql://localhost/HenrysBooksDB20170301", "root", "password");

			// es. 1: esecuzione comando SQL
			Statement istruzione1 = connessione.createStatement();
			ok = istruzione1.executeUpdate(stringa1);

			System.out.println("\n1) Aggiunto vincolo referenziale. Modificate " + ok + " righe");

			// es. 2: esecuzione comando SQL
			Statement istruzione2 = connessione.createStatement();
			num = istruzione2.executeUpdate(stringa2);

			System.out.println("\n2) Aggiornate copie disponibili. Righe modificate: " + num);

			// es. 3: esecuzione comando SQL
			Statement istruzione3 = connessione.createStatement();
			istruzione3.execute(stringa3);

			System.out.println("\n3) Vista LibriSottoScorta_HB(Codice_libro) creata con successo");

			// es. 4: esecuzione comando SQL
			Statement istruzione4 = connessione.createStatement();
			ResultSet risultato4 = istruzione4.executeQuery(stringa4);

			System.out.println("\n4) Il contenuto della vista LibriSottoScorta_HB è:");
			while (risultato4.next()) {
				codiceLibro = risultato4.getString("Codice_libro");

				System.out.println("Codice libro: " + codiceLibro);
				System.out.println("--------------------------");

			}

			// es. 5: esecuzione comando SQL
			Statement istruzione5 = connessione.createStatement();
			ResultSet risultato5 = istruzione5.executeQuery(stringa5);

			System.out.println("\n5) I titoli ed il numero di copie disponibili dei libri in vendita presso ciascuna filiale sono:");
			while (risultato5.next()) {

				nomeFiliale = risultato5.getString("Nome_filiale");
				titoloLibro = risultato5.getString("Titolo_libro");
				copieDisponibili = risultato5.getInt("Nro_copie_disponibili");

				System.out.println("Nome filiale: " + nomeFiliale);
				System.out.println("Titolo libro: " + titoloLibro);
				System.out.println("Copie disponibili: " + copieDisponibili);
				System.out.println("--------------------------");	

			}

			// es. 6: esecuzione comando SQL
			Statement istruzione6 = connessione.createStatement();
			ResultSet risultato6 = istruzione6.executeQuery(stringa6);

			System.out.println("\n6) Il titolo del libro con il numero più basso di copie disponibili fra quelli sotto scorta presso la filiale di nome 'Henrys Brentwood' è:"); 
			while (risultato6.next()) {

				codiceLibro = risultato6.getString("Codice_libro");
				titoloLibro = risultato6.getString("Titolo_libro");
				copieDisponibili = risultato6.getInt("Nro_copie_disponibili");

				System.out.println("Codice libro: " + codiceLibro);
				System.out.println("Titolo libro: " + titoloLibro);
				System.out.println("Copie disponibili: " + copieDisponibili);
				System.out.println("--------------------------");	

			}

			// es. 7: esecuzione comando SQL
			Statement istruzione7 = connessione.createStatement();
			ResultSet risultato7 = istruzione7.executeQuery(stringa7);

			System.out.println("\n7) I libri che non sono sotto scorta presso la filiale di nome 'Henrys Brentwood' sono:");
			while (risultato7.next()) {

				titoloLibro = risultato7.getString("Titolo_libro");
				tipoLibro = risultato7.getString("Tipo_libro");
				prezzoLibro = risultato7.getDouble("Prezzo_libro");

				System.out.println("Titolo libro: " + titoloLibro);
				System.out.println("Tipo libro: " + tipoLibro);
				System.out.println("Prezzo libro: " + prezzoLibro);
				System.out.println("--------------------------");	
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();

		}

	}
}
