/*
	Prova di Laboratorio di PROGETTAZIONE DI BASI DI DATI
	C.d.S. in Informatica e Tecnologie per la Produzione del Software (3 anni)
	Docente: dott.ssa Francesca A. Lisi

	04 Febbraio 2015

	-------------------------------------
	Cognome e Nome	: Scarano Gianmarco
	Matricola	: 705627
	-------------------------------------
 */

import java.sql.*;

public class ProvaDB20150204 {

	public static void main(String[] args) {
		// sezione dichiarazione variabili locali
		
		Connection connessione = null;

		int num;
		int ok;
		String titoloLibro;
		String nomeFiliale;
		String nomeEditore;
		int numeroCopieDisponibili;
		double prezzoLibro;

		// es. 1: creazione stringa contenente comando SQL
		String stringa1 = "ALTER TABLE Scorte ADD FOREIGN KEY (Nro_filiale) REFERENCES Filiali(Nro_filiale);";

		// es. 2: creazione stringa contenente comando SQL
		String stringa2 = "INSERT INTO Filiali(Nro_filiale, Nome_filiale, Sede_filiale) VALUES ('5','Henrys Manhattan', 'Broadway');";

		// es. 3: creazione stringa contenente comando SQL
		String stringa3 = "CREATE VIEW LibriDisponibili_HD(Titolo_libro) AS "
				+ "SELECT Titolo_libro FROM Libri NATURAL JOIN Scorte NATURAL JOIN Filiali "
				+ "WHERE Nome_filiale = 'Henrys Downtown' AND Nro_copie_disponibili >= 1;";

		// es. 4: creazione stringa contenente comando SQL
		String stringa4 = "SELECT * FROM LibriDisponibili_HD;";

		// es. 5: creazione stringa contenente comando SQL
		String stringa5 = "SELECT Nome_filiale, Titolo_libro, Nro_copie_disponibili AS NumeroCopieDisponibili FROM Filiali NATURAL JOIN Scorte NATURAL JOIN Libri "
				+ "ORDER BY Nome_filiale, Titolo_libro;";

		// es. 6: creazione stringa contenente comando SQL
		String stringa6 = "SELECT Titolo_libro, Prezzo_libro FROM Libri NATURAL JOIN LibriDisponibili_HD "
				+ "WHERE Prezzo_libro = (SELECT MIN(Prezzo_libro) FROM Libri NATURAL JOIN LibriDisponibili_HD);";

		// es. 7: creazione stringa contenente comando SQL
		String stringa7 = "SELECT Titolo_libro, Nome_editore, Prezzo_libro FROM Editori NATURAL JOIN Libri "
				+ "WHERE Titolo_libro NOT IN (SELECT Titolo_libro FROM LibriDisponibili_HD) "
				+ "ORDER BY Titolo_libro;";
			
		try {
			// caricamento del driver
			new com.mysql.jdbc.Driver();
			/*
			  creazione di una connessione al database HenrysBooksDB20140204
			  con credenziali di accesso appropriate
			 */
			connessione = DriverManager.getConnection("jdbc:mysql://localhost/HenrysBooksDB20150204", "root", "password");

			// es. 1: esecuzione comando SQL
			Statement istruzione1 = connessione.createStatement();
			ok = istruzione1.executeUpdate(stringa1);
			
			System.out.println("\n1) Il numero di tuple modificate è: " + ok);

			System.out.println("\n||----------------------------------------||");
			
			// es. 2: esecuzione comando SQL
			Statement istruzione2 = connessione.createStatement();
			num = istruzione2.executeUpdate(stringa2);
			
			System.out.println("\n2) Il numero di inserimenti fatti nella tabella Filiali è: " + num);
			
			System.out.println("\n||----------------------------------------||");
			
			// es. 3: esecuzione comando SQL
			Statement istruzione3 = connessione.createStatement();
			istruzione3.execute(stringa3);

			System.out.println("\n3) Vista LibriDisponibili_HD creata correttamente");

			System.out.println("\n||----------------------------------------||");
			
			// es. 4: esecuzione comando SQL
			Statement istruzione4 = connessione.createStatement();
			ResultSet risultato4 = istruzione4.executeQuery(stringa4);
			
			System.out.println("\n4) Il contenuto della vista LibriDisponibili_HD è:");
			while (risultato4.next()) {
				titoloLibro = risultato4.getString("Titolo_libro");
				
				System.out.println("Titolo libro: " + titoloLibro);
			}
	
			System.out.println("\n||----------------------------------------||");
			
			// es. 5: esecuzione comando SQL
			Statement istruzione5 = connessione.createStatement();
			ResultSet risultato5 = istruzione5.executeQuery(stringa5);
			
			System.out.println("\n5) I titoli ed il numero di copie disponibili dei libri in vendita presso ciascuna filiale sono:");
			while (risultato5.next()) {
				nomeFiliale = risultato5.getString("Nome_filiale");
				titoloLibro = risultato5.getString("Titolo_libro");
				numeroCopieDisponibili = risultato5.getInt("NumeroCopieDisponibili"); 
				
				System.out.println("Nome filiale             : " + nomeFiliale);
				System.out.println("Titolo libro             : " + titoloLibro);
				System.out.println("Numero copie disponibili : " + numeroCopieDisponibili);
				System.out.println("-----------------------------");

			}
	
			System.out.println("\n||----------------------------------------||");
			
			// es. 6: esecuzione comando SQL
			Statement istruzione6 = connessione.createStatement();
			ResultSet risultato6 = istruzione6.executeQuery(stringa6);
			
			System.out.println("\n6) Il titolo del libro meno costoso fra quelli disponibili presso la filiale 'Henrys Downtown' è:");
			while (risultato6.next()) {
				titoloLibro = risultato6.getString("Titolo_libro");
				prezzoLibro = risultato6.getDouble("Prezzo_libro");
				
				System.out.println("Titolo libro : " + titoloLibro);
				System.out.println("Prezzo libro : " + prezzoLibro);

			}

			System.out.println("\n||----------------------------------------||");
			
			// es. 7: esecuzione comando SQL
			Statement istruzione7 = connessione.createStatement();
			ResultSet risultato7 = istruzione7.executeQuery(stringa7);
			
			System.out.println("\n7) I libri che non sono disponibili presso la filiale 'Henrys Downtown' sono:");
			while (risultato7.next()) {
				titoloLibro = risultato7.getString("Titolo_libro");
				nomeEditore = risultato7.getString("Nome_editore");
				prezzoLibro = risultato7.getDouble("Prezzo_libro");
				
				System.out.println("Titolo libro : " + titoloLibro);
				System.out.println("Nome editore : " + nomeEditore);
				System.out.println("Prezzo libro : " + prezzoLibro);
				System.out.println("-----------------------------");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();

		}

	}
}
