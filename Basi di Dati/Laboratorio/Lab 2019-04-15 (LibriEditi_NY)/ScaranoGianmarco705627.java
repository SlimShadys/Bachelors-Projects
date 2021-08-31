/*

Prova pratica di PROGETTAZIONE DI BASI DI DATI
C.d.S. in Informatica e Tecnologie per la Produzione del Software (3 anni)
Docente: Prof.ssa Francesca A. Lisi

15 Aprile 2019 ore 15

-------------------------------------
Cognome e Nome	: Scarano Gianmarco
Matricola	: 705627
-------------------------------------

 */

import java.sql.*;

public class ScaranoGianmarco705627 {

	public static void main(String[] args) {
		// sezione dichiarazione variabili locali

		Connection connessione = null;

		int num;
		int ok;

		String titoloLibro;
		String nomeEditore;
		String nomeFiliale;
		String sedeFiliale;
		String tipoLibro;

		double prezzoMedio;
		int copieDisponibili;
		int numeroLibri;

		// es. 1: creazione stringa contenente comando SQL
		String stringa1 = "ALTER TABLE Libri ADD FOREIGN KEY (Codice_editore) REFERENCES Editori(Codice_editore);";

		// es. 2: creazione stringa contenente comando SQL
		String stringa2 = "DELETE FROM Editori WHERE Nome_editore = 'Jeremy P. Tarcher';";

		// es. 3: creazione stringa contenente comando SQL
		String stringa3 = "CREATE VIEW LibriEditi_NY(Titolo_libro,Nome_editore) AS "
				+ "SELECT Titolo_libro, Nome_editore FROM Libri NATURAL JOIN Editori "
				+ "WHERE Stato_editore = 'NY';";

		// es. 4: creazione stringa contenente comando SQL
		String stringa4 = "SELECT * FROM LibriEditi_NY ORDER BY Nome_editore;";

		// es. 5: creazione stringa contenente comando SQL
		String stringa5 = "SELECT DISTINCT Nome_filiale, Sede_filiale, Titolo_libro "
				+ "FROM Filiali NATURAL JOIN Libri NATURAL JOIN Scorte NATURAL JOIN Editori "
				+ "WHERE Titolo_libro IN (SELECT Titolo_libro FROM LibriEditi_NY) AND "
				+ "Nome_editore IN (SELECT Nome_editore FROM LibriEditi_NY) "
				+ "ORDER BY Nome_filiale;";

		// es. 6: creazione stringa contenente comando SQL
		String stringa6 = "SELECT Titolo_libro, Tipo_libro, Nome_filiale, Nro_copie_disponibili "
				+ "FROM Libri NATURAL JOIN LibriEditi_NY NATURAL JOIN Scorte NATURAL JOIN Filiali "
				+ "WHERE Nro_copie_disponibili > 1 "
				+ "ORDER BY Titolo_libro;";

		// es. 7: creazione stringa contenente comando SQL
		String stringa7 = "SELECT Nome_filiale, COUNT(Nro_seq) AS NumeroLibri, AVG(Prezzo_libro) AS PrezzoMedio "
				+ "FROM LibriAutori NATURAL JOIN Libri NATURAL JOIN LibriEditi_NY NATURAL JOIN Scorte NATURAL JOIN Filiali "
				+ "WHERE Titolo_libro IN (SELECT Titolo_libro FROM LibriEditi_NY) AND "
				+ "Nome_editore IN (SELECT Nome_editore FROM LibriEditi_NY) "
				+ "GROUP BY Nome_filiale";

		// es. 8: creazione stringa contenente comando SQL
		String stringa8 = "SELECT Nome_filiale, COUNT(Nro_seq) AS NumeroLibri, AVG(Prezzo_libro) AS PrezzoMedio "
				+ "FROM LibriAutori NATURAL JOIN Libri NATURAL JOIN Editori NATURAL JOIN Scorte NATURAL JOIN Filiali "
				+ "WHERE Stato_editore = 'NY' "
				+ "GROUP BY Nome_filiale;";

		try {
			// caricamento del driver
			new com.mysql.jdbc.Driver();
			/*
			  creazione di una connessione al database HenrysBooksDB20190415
			  con credenziali di accesso appropriate
			 */
			connessione = DriverManager.getConnection("jdbc:mysql://localhost/HenrysBooksDB20190415", "root", "password");

			// es. 1: esecuzione comando SQL
			Statement istruzione1 = connessione.createStatement();
			ok = istruzione1.executeUpdate(stringa1);

			System.out.println("\n1) Aggiunto vincolo integrità referenziale tra Libri ed Editori. " + ok + " righe modificate.");

			// es. 2: esecuzione comando SQL
			Statement istruzione2 = connessione.createStatement();
			num = istruzione2.executeUpdate(stringa2);

			System.out.println("\n2) Il numero di tuple eliminate dalla tabella Editori è: " + num + " ");

			// es. 3: esecuzione comando SQL
			Statement istruzione3 = connessione.createStatement();
			istruzione3.execute(stringa3);

			System.out.println("\n3) Vista LibriEditi_NY creata correttamente");

			// es. 4: esecuzione comando SQL
			Statement istruzione4 = connessione.createStatement();
			ResultSet risultato4 = istruzione4.executeQuery(stringa4);

			System.out.println("\n4) Il contenuto della vista LibriEditi_NY è:");
			while (risultato4.next()) {
				titoloLibro = risultato4.getString("Titolo_libro");
				nomeEditore = risultato4.getString("Nome_editore");

				System.out.println("Titolo libro: " + titoloLibro);
				System.out.println("Nome editore: " + nomeEditore);
				System.out.println("--------------------------");
			}

			// es. 5: esecuzione comando SQL
			Statement istruzione5 = connessione.createStatement();
			ResultSet risultato5 = istruzione5.executeQuery(stringa5);

			System.out.println("\n5) Le filiali in cui si vendono libri editi dello stato di New York sono:");
			while (risultato5.next()) {

				nomeFiliale = risultato5.getString("Nome_filiale");
				sedeFiliale = risultato5.getString("Sede_filiale");
				titoloLibro = risultato5.getString("Titolo_libro");

				System.out.println("Nome filiale: " + nomeFiliale);
				System.out.println("Sede filiale: " + sedeFiliale);
				System.out.println("Titolo libro: " + titoloLibro);
				System.out.println("--------------------------");
			}

			// es. 6: esecuzione comando SQL
			Statement istruzione6 = connessione.createStatement();
			ResultSet risultato6 = istruzione6.executeQuery(stringa6);

			System.out.println("\n6) Titolo, tipo e filiale dei libri editi dello stato di New York:");
			while (risultato6.next()) {

				titoloLibro = risultato6.getString("Titolo_libro");
				tipoLibro = risultato6.getString("Tipo_libro");
				nomeFiliale = risultato6.getString("Nome_filiale");
				copieDisponibili = risultato6.getInt("Nro_copie_disponibili");

				System.out.println("Titolo libro: " + titoloLibro);
				System.out.println("Tipo libro: " + tipoLibro);
				System.out.println("Nome filiale: " + nomeFiliale);
				System.out.println("Copie disponibili: " + copieDisponibili);
				System.out.println("--------------------------");

			}

			// es. 7: esecuzione comando SQL
			Statement istruzione7 = connessione.createStatement();
			ResultSet risultato7 = istruzione7.executeQuery(stringa7);

			System.out.println("\n7) Il numero ed il prezzo medio dei libri pubblicati da ciascun autore:");
			while (risultato7.next()) {

				nomeFiliale = risultato7.getString("Nome_filiale");
				numeroLibri = risultato7.getInt("NumeroLibri");
				prezzoMedio = risultato7.getDouble("PrezzoMedio");

				System.out.println("Nome filiale: " + nomeFiliale);
				System.out.println("Numero libri: " + numeroLibri);
				System.out.println("Prezzo medio: " + prezzoMedio);
				System.out.println("--------------------------");

			}

			// es. 8: esecuzione comando SQL
			Statement istruzione8 = connessione.createStatement();
			ResultSet risultato8 = istruzione8.executeQuery(stringa8);

			System.out.println("\n8) Le filiali in cui si vendono libri editi dello stato di New York sono:");
			while (risultato8.next()) {

				nomeFiliale = risultato8.getString("Nome_filiale");
				numeroLibri = risultato8.getInt("NumeroLibri");
				prezzoMedio = risultato8.getDouble("PrezzoMedio");

				System.out.println("Nome filiale: " + nomeFiliale);
				System.out.println("Numero libri: " + numeroLibri);
				System.out.println("Prezzo medio: " + prezzoMedio);
				System.out.println("--------------------------");

			}
		} 
		catch (SQLException e) {
			e.printStackTrace();

		}

	}
}
