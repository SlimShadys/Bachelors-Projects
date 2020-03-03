import java.io.Serializable;

public class Persona implements Serializable {

	/**
	 * Elimina warning di Eclipse
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String cognome;
	private int anni;
	/*
	 * Sar� transient -> Non si potr� serializzare questa variabile
	 * Infatti nell'output ci sar� "null".
	 * Se togliessimo "transient", nell'output ci sar� "GS20", come da costruttore.
	 */
	private transient String codice;
									

	public Persona(String nome, String cognome, int anni, String codice) {

		this.nome = nome;
		this.cognome = cognome;
		this.anni = anni;
		this.codice = codice;

	}
	
	/*
	 * Metodo per stampare a video l'identit� di una persona.
	 */
	@Override
	public String toString() {
		return "Nome: " + nome + "\nCognome: " + cognome + "\nEt�: " + anni + "\nCodice: " + codice + "\n";
	}

}
