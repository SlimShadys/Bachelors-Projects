/*

Prova di Laboratorio di PROGETTAZIONE DI BASI DI DATI
C.d.S. in Informatica e Tecnologie per la Produzione del Software (3 anni)
Docente: dott.ssa Francesca A. Lisi

4 Febbraio 2015

-------------------------------------
Cognome e Nome	:
Matricola	:
-------------------------------------

 */


package application;

import java.sql.*;

public class ProvaDB20200218 {

	private static String titoloLibro;
	private static String copieDisponibili;
	private static String nomeFiliale;
	private static double prezzoLibro;
	private static String nomeEditore;

	public static void main(String[] args) {
		// sezione dichiarazione variabili locali

		Connection connessione = null;

		int num, ok;

		// es. 1: creazione stringa contenente comando SQL
		String stringa1 = "ALTER TABLE Scorte ADD FOREIGN KEY (Nro_filiale) REFERENCES Filiali(Nro_filiale);";

		// es. 2: creazione stringa contenente comando SQL
		String stringa2 = "INSERT INTO Filiali(Nro_filiale, Nome_filiale, Sede_filiale) VALUES ('5','Henrys Manhattan','Broadway');";

		// es. 3: creazione stringa contenente comando SQL
		String stringa3 = "CREATE VIEW LibriDisponibili_HB(Titolo_libro, nro_copie_disponibili) AS "
				+ "SELECT Titolo_libro, nro_copie_disponibili FROM Libri NATURAL JOIN Scorte NATURAL JOIN Filiali "
				+ "WHERE Nome_filiale = 'Henrys Brentwood' AND Nro_copie_disponibili >= 1;";

		// es. 4: creazione stringa contenente comando SQL
		String stringa4 = "SELECT * FROM LibriDisponibili_HB ORDER BY Titolo_libro ASC;";

		// es. 5: creazione stringa contenente comando SQL
		String stringa5 = "SELECT Titolo_libro, Nome_filiale, Nro_copie_disponibili "
				+ "FROM Libri NATURAL JOIN Scorte NATURAL JOIN Filiali "
				+ "ORDER BY Nome_filiale, Titolo_libro;";

		// es. 6: creazione stringa contenente comando SQL
		String stringa6 = "SELECT Titolo_libro, Prezzo_libro "
				+ "FROM Libri NATURAL JOIN LibriDisponibili_HB "
				+ "WHERE Prezzo_libro = (SELECT MIN(Prezzo_libro) FROM LibriDisponibili_HB NATURAL JOIN Libri);";

		// es. 7: creazione stringa contenente comando SQL
		String stringa7 = "SELECT Titolo_libro, Nome_editore, Prezzo_libro "
				+ "FROM Libri NATURAL JOIN Scorte NATURAL JOIN Editori NATURAL JOIN Filiali "
				+ "WHERE Nome_filiale = 'Henrys Brentwood' AND "
				+ "Titolo_libro NOT IN (SELECT Titolo_libro FROM LibriDisponibili_HB);";

		String stringa8 = "SELECT Titolo_libro, Nome_editore, Prezzo_libro "
				+ "FROM Libri NATURAL JOIN Scorte NATURAL JOIN Editori NATURAL JOIN Filiali "
				+ "WHERE Nome_filiale = 'Henrys Brentwood' AND Nro_copie_disponibili = 0;";

		try {
			// caricamento del driver
			new com.mysql.jdbc.Driver();
			/*
			  creazione di una connessione al database HenrysBooksDB20200218
			  con credenziali di accesso appropriate
			 */
			connessione = DriverManager.getConnection("jdbc:mysql://localhost/HenrysBooksDB20200218", "root", "password");

			// es. 1: esecuzione comando SQL
			Statement istruzione1 = connessione.createStatement();
			ok = istruzione1.executeUpdate(stringa1);

			System.out.println("\n1) Tabelle Scorte e Filiali opportunamente collegate. " + ok + " righe modificate");

			// es. 2: esecuzione comando SQL
			Statement istruzione2 = connessione.createStatement();
			num = istruzione2.executeUpdate(stringa2);

			System.out.println("\n2) Il numero di inserimenti fatti nella tabella Filiali è: " + num + " ");

			// es. 3: esecuzione comando SQL
			Statement istruzione3 = connessione.createStatement();
			istruzione3.execute(stringa3);

			System.out.println("\n3) Vista creata correttamente");

			// es. 4: esecuzione comando SQL
			Statement istruzione4 = connessione.createStatement();
			ResultSet risultato4 = istruzione4.executeQuery(stringa4);

			System.out.println("\n4) Il contenuto della vista LibriDisponibili_HB è:");
			while (risultato4.next()) {

				titoloLibro = risultato4.getString("Titolo_libro");
				copieDisponibili = risultato4.getString("Nro_copie_disponibili");

				System.out.println("Titolo libro      : " + titoloLibro);
				System.out.println("Copie disponibili : " + copieDisponibili);
				System.out.println("-----------------------------");

			}

			// es. 5: esecuzione comando SQL
			Statement istruzione5 = connessione.createStatement();
			ResultSet risultato5 = istruzione5.executeQuery(stringa5);

			System.out.println("\n5) I titoli ed il numero di copie disponibili dei libri in vendita presso ciascuna filiale sono:");
			while (risultato5.next()) {

				titoloLibro = risultato5.getString("Titolo_libro");
				nomeFiliale = risultato5.getString("Nome_filiale");
				copieDisponibili = risultato5.getString("Nro_copie_disponibili");

				System.out.println("Titolo libro      : " + titoloLibro);
				System.out.println("Nome filiale      : " + nomeFiliale);
				System.out.println("Copie disponibili : " + copieDisponibili);
				System.out.println("-----------------------------");

			}

			// es. 6: esecuzione comando SQL
			Statement istruzione6 = connessione.createStatement();
			ResultSet risultato6 = istruzione6.executeQuery(stringa6);

			System.out.println("\n6) I titoli dei libri meno costosi fra quelli disponibili presso la filiale 'Henrys Brentwood' è:");
			while (risultato6.next()) {

				titoloLibro = risultato6.getString("Titolo_libro");
				prezzoLibro = risultato6.getDouble("Prezzo_libro");

				System.out.println("Titolo libro : " + titoloLibro);
				System.out.println("Prezzo libro : " + prezzoLibro);
				System.out.println("-----------------------------");

			}

			// es. 7: esecuzione comando SQL
			Statement istruzione7 = connessione.createStatement();
			ResultSet risultato7 = istruzione7.executeQuery(stringa7);

			System.out.println("\n7) I libri che non sono disponibili presso la filiale 'Henrys Brentwood' sono:");
			while (risultato7.next()) {

				titoloLibro = risultato7.getString("Titolo_libro");
				nomeEditore = risultato7.getString("Nome_editore");
				prezzoLibro = risultato7.getDouble("Prezzo_libro");

				System.out.println("Titolo libro : " + titoloLibro);
				System.out.println("Nome filiale : " + nomeEditore);
				System.out.println("Prezzo libro : " + prezzoLibro);
				System.out.println("-----------------------------");

			}

			// es. 8: esecuzione comando SQL
			Statement istruzione8 = connessione.createStatement();
			ResultSet risultato8 = istruzione8.executeQuery(stringa8);

			System.out.println("\n8) I libri che non sono disponibili presso la filiale 'Henrys Brentwood' sono:");
			while (risultato8.next()) {

				titoloLibro = risultato8.getString("Titolo_libro");
				nomeEditore = risultato8.getString("Nome_editore");
				prezzoLibro = risultato8.getDouble("Prezzo_libro");

				System.out.println("Titolo libro : " + titoloLibro);
				System.out.println("Nome filiale : " + nomeEditore);
				System.out.println("Prezzo libro : " + prezzoLibro);
				System.out.println("-----------------------------");

			}
		} 
		catch (SQLException e) {
			e.printStackTrace();

		}

	}
}
