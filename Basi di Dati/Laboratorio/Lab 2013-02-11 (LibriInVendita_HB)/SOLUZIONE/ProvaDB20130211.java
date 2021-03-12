/*
	Prova di Laboratorio di PROGETTAZIONE DI BASI DI DATI
	C.d.S. in Informatica e Tecnologie per la Produzione del Software (3 anni)
	Docente: dott.ssa Francesca A. Lisi

	11 Febbraio 2013

	-------------------------------------
	Cognome e Nome	: Scarano Gianmarco
	Matricola	: 705627
	-------------------------------------
 */

import java.sql.*;

public class ProvaDB20130211 {

	public static void main(String[] args) {
		
		// sezione dichiarazione variabili locali
		String titolo;
		String nome;
		String codice_libro;
		String tipo;
		String codice_editore;
		int numero;

		Connection connessione = null;

		// es. 1: creazione stringa contenente comando SQL
		
		String stringa1 = "ALTER TABLE Scorte "
				        + "ADD FOREIGN KEY (Nro_Filiale) "
				        + "REFERENCES Filiali(Nro_filiale) ";

		// es. 2: creazione stringa contenente comando SQL
		//String stringa02 = "DELETE FROM Filiali"
		 //               + "WHERE Nro_Filiale = '5'";
		String stringa2 = "INSERT INTO Filiali (Nro_filiale, Nome_filiale, Sede_filiale) "
				        + "VALUES ('5','Henrys Manhattan','Broadway')";

		// es. 3: creazione stringa contenente comando SQL
		String stringa3 = "CREATE VIEW LibriInVendita_HB(Titolo_libro) AS "
				        + "SELECT Titolo_libro "
			          	+ "FROM Libri NATURAL JOIN Scorte NATURAL JOIN Filiali "
				        + "WHERE Nome_Filiale = 'Henrys Brentwood'";

		// es. 4: creazione stringa contenente comando SQL
		String stringa4 = "SELECT * FROM LibriInVendita_HB";

		// es. 5: creazione stringa contenente comando SQL
		String stringa5 = "SELECT Nome_filiale, Titolo_libro, Nro_copie_disponibili "
			          	+ "FROM Filiali NATURAL JOIN scorte NATURAL JOIN libri "
			         	+ "ORDER BY Nome_filiale, Titolo_libro";

		// es. 6: creazione stringa contenente comando SQL
		String stringa6 = "SELECT Titolo_libro "
				        + "FROM libri NATURAL JOIN LibriInVendita_HB "
				        + "WHERE prezzo_libro = (SELECT MIN(Prezzo_libro) "
				        + "FROM Libri NATURAL JOIN LibriInVendita_HB)";

		// es. 7: creazione stringa contenente comando SQL
		String stringa7 = "SELECT Codice_libro, Titolo_libro, Tipo_libro, Prezzo_libro, Codice_editore, Nome_editore "
			           	+ "FROM Libri NATURAL JOIN Editori "
			         	+ "WHERE titolo_libro NOT IN "
			           	+ "(SELECT titolo_libro "
			          	+ "FROM LibriInVendita_HB) ";
			          	+ "ORDER BY Titolo_libro";

		try {
			// caricamento del driver
			new com.mysql.jdbc.Driver();
			/*
			 * creazione di una connessione al database HenrysBooksDB20130222
			 * con credenziali di accesso appropriate
			 */
			connessione = DriverManager.getConnection("jdbc:mysql://localhost/henrysbooksdb20130211","root","password");

			// es. 1: esecuzione comando SQL
			Statement istruzione1 = connessione.createStatement();
			int ok = istruzione1.executeUpdate(stringa1);

			System.out.println();

			// es. 2: esecuzione comando SQL
			Statement istruzione2 = connessione.createStatement();
			//int delete = istruzione2.executeUpdate(stringa02);
			int num = istruzione2.executeUpdate(stringa2);
           
			//System.out.println("\n La tupla eliminata �: " + delete + ".");
			System.out.println("\nIl numero di linee modificate �: " + num + ".");

			// es. 3: esecuzione comando SQL
			Statement istruzione3 = connessione.createStatement();
			istruzione3.execute(stringa3);

			System.out.println();

			// es. 4: esecuzione comando SQL
			Statement istruzione4 = connessione.createStatement();
			ResultSet risultato4 = istruzione4.executeQuery(stringa4);

			System.out
					.println("\n4)Il contenuto della vista LibriInVendita_HB �:");
			while (risultato4.next()) {
				
				titolo = risultato4.getString("Titolo_libro");
				
				System.out.println("Titolo libro: " + titolo);
				System.out.println("-------------");

			}

			// es. 5: esecuzione comando SQL
			Statement istruzione5 = connessione.createStatement();
			ResultSet risultato5 = istruzione5.executeQuery(stringa5);
			
			System.out.println("\n\\-----------------------------//");

			System.out.println("\n5)Il titolo dei libri per filiale e il numero copie �: ");
			while (risultato5.next()) {
				
				nome = risultato5.getString("Nome_filiale");
				titolo = risultato5.getString("Titolo_libro");
				numero = risultato5.getInt("Nro_copie_disponibili");
				
				System.out.println("Nome filiale     : " + nome);
				System.out.println("Titolo libro     : " + titolo);
				System.out.println("Copie disponibili: " + numero);
				System.out.println("-------------");

			}

			// es. 6: esecuzione comando SQL
			Statement istruzione6 = connessione.createStatement();
			ResultSet risultato6 = istruzione6.executeQuery(stringa6);
			
			System.out.println("\n\\-----------------------------//");

			System.out.println("\n6)Il titolo del libro pi� economico �: ");
			while (risultato6.next()) {
				
				titolo = risultato6.getString("Titolo_libro");
				
				System.out.println("Titolo libro: " + titolo);

			}

			// es. 7: esecuzione comando SQL
			Statement istruzione7 = connessione.createStatement();
			ResultSet risultato7 = istruzione7.executeQuery(stringa7);
			
			System.out.println("\n\\-----------------------------//");

			System.out.println("\n7)I titoli dei libri, i nomi degli editori e il prezzo dei libri non presenti sulla vista LibriInVendita_HB sono: ");
			while (risultato7.next()) {
				
				codice_libro = risultato7.getString("Codice_libro");
				titolo = risultato7.getString("Titolo_libro");
				tipo = risultato7.getString("Tipo_libro");
				double prezzo = risultato7.getDouble("Prezzo_libro");
				codice_editore = risultato7.getString("Codice_editore");
				nome = risultato7.getString("Nome_editore");

				
				System.out.println("Codice libro  : " + codice_libro);
				System.out.println("Titolo libro  : " + titolo);
				System.out.println("Tipo libro    : " + tipo);
				System.out.println("Prezzo libro  : " + prezzo);
				System.out.println("Codice editore: " + codice_editore);
				System.out.println("Nome editore  : " + nome);
				System.out.println("-------------");

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
}