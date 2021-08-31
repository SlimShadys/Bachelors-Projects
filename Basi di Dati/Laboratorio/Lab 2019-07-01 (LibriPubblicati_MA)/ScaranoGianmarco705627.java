/*

Prova pratica di PROGETTAZIONE DI BASI DI DATI
C.d.S. in Informatica e Tecnologie per la Produzione del Software (3 anni)
Docente: Prof.ssa Francesca A. Lisi

01 Luglio 2019 ore 10

-------------------------------------
Cognome e Nome	: Scarano Gianmarco
Matricola	: 705627
-------------------------------------

 */

import java.sql.*;

public class ScaranoGianmarco705627 {

	private static String titoloLibro;
	private static String tipoLibro;
	private static double prezzoLibro;
	private static String nomeEditore;
	private static String cognomeAutore;
	private static String nomeAutore;
	private static int copieDisponibili;

	public static void main(String[] args) {
		// sezione dichiarazione variabili locali

		Connection connessione = null;

		int num;
		int ok;

		// es. 1: creazione stringa contenente comando SQL
		String stringa1 = "ALTER TABLE Libri ADD FOREIGN KEY (Codice_editore) REFERENCES Editori(Codice_editore);";

		// es. 2: creazione stringa contenente comando SQL
		String stringa2 = "DELETE FROM Editori WHERE Nome_Editore = 'Arcade Publishing';";

		// es. 3: creazione stringa contenente comando SQL
		String stringa3 = "CREATE VIEW LibriPubblicati_MA(Titolo_libro,Nome_editore) AS "
				+ "SELECT Titolo_libro,Nome_editore FROM Libri NATURAL JOIN Editori "
				+ "WHERE Stato_editore = 'MA';";

		// es. 4: creazione stringa contenente comando SQL
		String stringa4 = " SELECT * FROM LibriPubblicati_MA "
				+ "ORDER BY Titolo_libro;";

		// es. 5: creazione stringa contenente comando SQL
		String stringa5 = "SELECT DISTINCT Cognome_autore, Nome_autore FROM "
				+ "Autori NATURAL JOIN LibriAutori NATURAL JOIN Libri NATURAL JOIN LibriPubblicati_MA "
				+ "ORDER BY Cognome_autore, Nome_autore;";

		// es. 6: creazione stringa contenente comando SQL
		String stringa6 = "SELECT Titolo_libro, Tipo_libro, Prezzo_libro, Nome_editore "
				+ "FROM Libri NATURAL JOIN Editori "
				+ "WHERE Titolo_libro NOT IN (SELECT Titolo_libro FROM LibriPubblicati_MA) "
				+ "ORDER BY Titolo_libro;";

		// es. 7: creazione stringa contenente comando SQL
		String stringa7 = "SELECT Titolo_libro, SUM(Nro_copie_disponibili) AS CopieDisponibili "
				+ "FROM Libri NATURAL JOIN Scorte NATURAL JOIN LibriPubblicati_MA "
				+ "GROUP BY Titolo_libro;";

		// es. 8: creazione stringa contenente comando SQL
		String stringa8 = "SELECT Titolo_libro, SUM(Nro_copie_disponibili) AS CopieDisponibili "
				+ "FROM Libri NATURAL JOIN Scorte NATURAL JOIN Editori "
				+ "WHERE Stato_editore = 'MA' "
				+ "GROUP BY Titolo_libro;";

		try {
			// caricamento del driver
			new com.mysql.jdbc.Driver();
			/*
			  creazione di una connessione al database HenrysBooksDB20190701
			  con credenziali di accesso appropriate
			 */
			connessione = DriverManager.getConnection("jdbc:mysql://localhost/HenrysBooksDB20190701", "root", "password");

			// es. 1: esecuzione comando SQL
			Statement istruzione1 = connessione.createStatement();
			ok = istruzione1.executeUpdate(stringa1);

			System.out.println("\n1) Aggiunto vincolo di integrità referenziale tra la tabella Editori e Libri. Tuple modificate: " + ok);

			// es. 2: esecuzione comando SQL
			Statement istruzione2 = connessione.createStatement();
			num = istruzione2.executeUpdate(stringa2);

			System.out.println("\n2) Eliminata tupla relativa all'editore Arcade Publishing. Tuple eliminate: " + num);

			// es. 3: esecuzione comando SQL
			Statement istruzione3 = connessione.createStatement();
			istruzione3.execute(stringa3);

			System.out.println("\n3) Vista LibriPubblicati_MA creata correttamente.");

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

			// es. 5: esecuzione comando SQL
			Statement istruzione5 = connessione.createStatement();
			ResultSet risultato5 = istruzione5.executeQuery(stringa5);

			System.out.println("\n5) Gli autori che hanno pubblicato almeno un libro mediante editori con sede nello stato del Massachusetts sono:");

			while (risultato5.next()) {

				cognomeAutore = risultato5.getString("Cognome_autore");
				nomeAutore = risultato5.getString("Nome_autore");

				System.out.println("Cognome autore : " + cognomeAutore);				
				System.out.println("Nome autore    : " + nomeAutore);
				System.out.println("----------------------------");

			}

			// es. 6: esecuzione comando SQL
			Statement istruzione6 = connessione.createStatement();
			ResultSet risultato6 = istruzione6.executeQuery(stringa6);

			System.out.println("\n6) I libri che non sono stati pubblicati da editori con sede nello stato del Massachusetts sono:");

			while (risultato6.next()) {

				titoloLibro = risultato6.getString("Titolo_libro");
				tipoLibro = risultato6.getString("Tipo_libro");
				prezzoLibro = risultato6.getDouble("Prezzo_libro");
				nomeEditore = risultato6.getString("Nome_editore");

				System.out.println("Titolo libro : " + titoloLibro);
				System.out.println("Tipo libro   : " + tipoLibro);
				System.out.println("Prezzo libro : " + prezzoLibro);				
				System.out.println("Nome editore : " + nomeEditore);
				System.out.println("----------------------------");

			}

			// es. 7: esecuzione comando SQL
			Statement istruzione7 = connessione.createStatement();
			ResultSet risultato7 = istruzione7.executeQuery(stringa7);

			System.out.println("\n7) I libri e le copie disponibili presso presso l’intera catena sono: ");

			while (risultato7.next()) {

				titoloLibro = risultato7.getString("Titolo_libro");
				copieDisponibili = risultato7.getInt("CopieDisponibili");

				System.out.println("Titolo libro      : " + titoloLibro);
				System.out.println("Copie Disponibili : " + copieDisponibili);
				System.out.println("----------------------------");

			}

			// es. 8: esecuzione comando SQL
			Statement istruzione8 = connessione.createStatement();
			ResultSet risultato8 = istruzione8.executeQuery(stringa8);

			System.out.println("\n8) I libri e le copie disponibili (metodo alternativo) presso presso l’intera catena sono: ");


			while (risultato8.next()) {

				titoloLibro = risultato8.getString("Titolo_libro");
				copieDisponibili = risultato8.getInt("CopieDisponibili");

				System.out.println("Titolo libro      : " + titoloLibro);
				System.out.println("Copie Disponibili : " + copieDisponibili);
				System.out.println("----------------------------");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();

		}

	}
}
