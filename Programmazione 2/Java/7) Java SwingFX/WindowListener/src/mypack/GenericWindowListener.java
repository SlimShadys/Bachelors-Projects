//**********************************************************
//  GenericWindowListener.java       
//
// Rappresenta un listener generico per la componente finestra.
//**********************************************************

package mypack;

import java.awt.event.*;

/*
 * Questo � appunto, un listener generico che estende WindowAdapter
 * WindowAdapter � necessario quando noi vogliamo aggiungere un listener
 * generico e vogliamo fare un "Override" di uno dei metodi dello stesso
 * WindowAdapter.
 */
public class GenericWindowListener extends WindowAdapter
{

	/*
	 * Override del metodo windowClosing contenuto in WindowAdapter.
	 * Siamo noi a decidere che vogliamo terminare il programma.
	 * Possiamo far ci� tramite ereditariet�, poich�
	 * GenericWindowListener extends WindowAdapter e quindi,
	 * possiamo ridefinire dei metodi della classe che abbiamo esteso.
	 */
	public void windowClosing (WindowEvent event)
	{
		System.exit(0); //Termina il programma quando la finestra � chiusa
	}
}
