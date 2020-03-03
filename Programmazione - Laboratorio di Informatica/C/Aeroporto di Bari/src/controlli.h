/**
 * @file controlli.h
 * Prototipi di funzione per i controlli degli input.
 *
 * Questo header file contiene tutti i prototipi di funzione relativi
 * ai controlli per la programmazione difensiva, contro eventuali
 * input errati inseriti dall'utente.
 *
 * @version 1
 * @date 13/05/2018
 * @authors Gianmarco Scarano
 */

#ifndef CONTROLLI_H_
#define CONTROLLI_H_

/// Includo le librerie necessarie al corretto funzionamento del programma.
#include <stdio.h>
#include "volo.h"
#include "passeggero.h"

// ------------- CONTROLLI UTENTE --------------- //

/**
 * Controlla il corretto input del mese immesso durante la ricerca dell'utente
 * tramite data di nascita.
 *
 * @param temp Vettore temporaneo dove sar� memorizzato il mese immesso
 * @param MAX_MESE_TEMP Lunghezza massima del vettore mese
 * @return 1 se il mese � giusto, 0 se il mese � errato.
 */
int ControlloMeseTemporaneo (char *temp, int MAX_MESE_TEMP);

/**
 * Controlla il corretto input del giorno immesso durante la ricerca dell'utente
 * tramite data di nascita.
 *
 * @param temp Vettore temporaneo dove sar� memorizzato il giorno immesso
 * @param temp_mese Vettore temporaneo del mese di nascita, necessario per effettuare
 * 	      i controlli sul giorno.
 * @param MAX_GIORNO_TEMP Lunghezza massima del vettore giorno
 * @return 1 se il giorno � giusto, 0 se il giorno � errato.
 */
int ControlloGiornoTemporaneo (char *temp, char *temp_mese, int MAX_GIORNO_TEMP);

/**
 * Controlla il corretto input dell'anno immesso durante la ricerca dell'utente
 * tramite data di nascita.
 *
 * @param temp Vettore temporaneo dove sar� memorizzato l'anno immesso
 * @param MAX_ANNO_TEMP Lunghezza massima dell'anno
 * @return 1 se l'anno � giusto, 0 se l'anno � errato.
 */
int ControlloAnnoTemporaneo (char *temp, int MAX_ANNO_TEMP);

/**
 * Controlla il corretto input del nome utente.
 *
 * @param utenti1 La struct del passeggero su cui controllare il relativo nome.
 * @return 1 se il nome � giusto, 0 se il nome � errato.
 */
int ControlloNomeUtente (passeggero utenti1);

/**
 * Controlla il corretto input del cognome utente.
 *
 * @param utenti1 La struct del passeggero su cui controllare il relativo cognome.
 * @return 1 se il cognome � giusto, 0 se il cognome � errato.
 */
int ControlloCognomeUtente (passeggero utenti1);

/**
 * Controlla il corretto input del mese di nascita dell'utente.
 * Ha le stesse funzionalit� di ControlloMese(), solo che la struct passata
 * � quella dell'utente.
 *
 * @param utenti1 La struct del passeggero su cui controllare il suddetto mese.
 * @return 1 se il mese � giusto, 0 se il mese � errato.
 */
int ControlloMeseUtente (passeggero utenti1);

/**
 * Controlla il corretto input del giorno di nascita dell'utente.
 * Ha le stesse funzionalit� di ControlloGiorno(), solo che la struct passata
 * � quella dell'utente.
 *
 * @param utenti1 La struct del passeggero su cui controllare il suddetto giorno.
 * @return 1 se il giorno � giusto, 0 se il giorno � errato.
 */
int ControlloGiornoUtente (passeggero utenti1);

/**
 * Controlla il corretto input dell'anno di nascita dell'utente.
 * Ha le stesse funzionalit� di ControlloAnno(), solo che la struct passata
 * � quella dell'utente.
 *
 * @param utenti1 La struct del passeggero su cui controllare il suddetto anno.
 * @return 1 se l'anno � giusto, 0 se l'anno � errato.
 */
int ControlloAnnoUtente (passeggero utenti1);

/**
 * Controlla il corretto input del luogo di nascita dell'utente.
 *
 * @param utenti1 La struct del passeggero su cui controllare il suddetto luogo di nascita.
 * @return 1 se il luogo di nascita � giusto, 0 se � errato.
 */
int ControlloLuogoDiNascita (passeggero utenti1);

/**
 * Controlla il corretto input del numero passaporto/carta d'identit� dell'utente.
 *
 * @param passaporto_temp E' il vettore temporaneo dove immagazzinare il numero passaporto
 * 						  immesso dall'utente
 * @param MAX_PASSAPORTO_TEMP Lunghezza massima del un passaporto
 *
 * @return 1 se il documento � giusto, 0 se � errato.
 */
int ControlloNumeroPassaporto (char *passaporto_temp, int MAX_PASSAPORTO_TEMP);

// ------------- CONTROLLI VOLO --------------- //
/**
* Controlla il corretto input dell'orario di partenza.
*
* @param elenco1 La struct dei voli su cui controllare il suddetto orario.
* @return 1 se l'orario � giusto, 0 se l'orario � errato.
*/
int ControlloOrarioPartenza (volo elenco1);

/**
* Controlla il corretto input dell'orario di arrivo.
*
* @param elenco1 La struct dei voli su cui controllare il suddetto orario.
* @return 1 se l'orario � giusto, 0 se l'orario � errato.
*/
int ControlloOrarioArrivo (volo elenco1);

/**
* Controlla il corretto input della durata del volo.
*
* @param elenco1 La struct dei voli su cui controllare la durata stessa.
* @return 1 se la durata � giusta, 0 se la durata � errata.
*/
int ControlloDurata (volo elenco1);

/**
* Controlla il corretto input del mese di partenza.
*
* @param elenco1 La struct dei voli su cui controllare il suddetto mese.
* @return 1 se il mese � giusto, 0 se il mese � errato.
*/
int ControlloMese (volo elenco1);

/**
* Controlla il corretto input del giorno di partenza.
*
* @param elenco1 La struct dei voli su cui controllare il suddetto giorno.
* @return 1 se il giorno � giusto, 0 se il giorno � errato.
*/
int ControlloGiorno (volo elenco1);

/**
* Controlla il corretto input dell'anno di partenza.
*
* @param elenco1 La struct dei voli su cui controllare il suddetto anno.
* @return 1 se l'anno � giusto, 0 se l'anno � errato.
*/
int ControlloAnno (volo elenco1);

/**
 * Controlla se il codice volo (es. AA1234) � composto da due caratteri iniziali
 * e il resto da numeri.
 *
 * @param codice_volo_temp E' il vettore temporaneo dove immagazzinare il codice volo
 * 						  immesso dall'utente
 * @param MAX_CODICE_VOLO_TEMP Lunghezza massima del codice volo
 * @param immesso E' la variabile di controllo che mi permette di verificare
 * 				  se ci sono codici volo uguale. Se � 0, vuol dire che non controlla,
 * 				  se � 1, vuol dire che esegue il suddetto controllo.
 *
 * @return 1 se il codice volo � giusto, 0 se � errato.
 */
int ControlloCodiceVolo (char *codice_volo_temp, int MAX_CODICE_VOLO_TEMP, int immesso);

/**
 * Controlla se il nome compagnia � composto da caratteri validi.
 *
 * @param elenco1 La struct del volo su cui controllare il nome compagnia.
 * @return 1 se il nome � scritto correttamente, 0 se � errato.
 */
int ControlloNomeCompagnia (volo elenco1);

/**
 * Controlla se la tratta di partenza � composto da caratteri validi.
 *
 * @param elenco1 La struct del volo su cui controllare la tratta.
 * @return 1 se la tratta � scritta correttamente, 0 se � errata.
 */
int ControlloTrattaPartenza (volo elenco1);

/**
 * Controlla se la tratta di arrivo � composto da caratteri validi.
 *
 * @param elenco1 La struct del volo su cui controllare la tratta.
 * @return 1 se la tratta � scritta correttamente, 0 se � errata.
 */
int ControlloTrattaArrivo (volo elenco1);

/**
 * Controlla se il modello dell'aeroplano � composto da caratteri validi.
 *
 * @param elenco1 La struct del volo su cui controllare il modello stesso.
 * @return 1 se il modello � scritto correttamente, 0 se � errato.
 */
int ControlloModelloAereo (volo elenco1);

 // ------------- CONTROLLI VARI --------------- //
/**
 * Questa funzione ha il compito di memorizzare il passaporto immesso
 * dall'utente al momento del check-in. Inoltre effettua gli opportuni controlli.
 *
 * @param passaporto_temporaneo Vettore dove immagazzinare l'input dell'utente
 * @param MAX_PASS Dimensione massima del vettore appena indicato.
 * @param modifica_pass Variabile che mi permette di stabilire se devo modificare
 * 						un passaporto oppure ho bisogno solamente di leggerlo.
 * 						Se devo modificarlo, sar� 1, altrimenti 0.
 *
 * @return 1 se il passaporto � scritto correttamente, 0 se � errato.
 */
int ControlloPassaportoTemporaneo (char *passaporto_temporaneo, int MAX_PASS, int modifica_pass);

/**
 * Questa funzione ha il compito di memorizzare il codice prenotazione immesso
 * dall'utente al momento del check-in. Inoltre effettua gli opportuni controlli.
 *
 * @param codice_prenotazione_temporaneo Vettore dove immagazzinare l'input dell'utente
 * @param MAX_PREN Dimensione massima del vettore appena indicato.
 *
 * @return 1 se il codice � scritto correttamente, 0 se � errato.
 */
int ControlloCodicePrenotazione (char *codice_prenotazione_temporaneo, int MAX_PREN);

/**
 * La funzione genera un codice prenotazione nel momento in cui l'utente
 * prenota un volo.
 *
 * @return Una stringa composta da 2 lettere e 3 numeri.
 */
char* GeneraCodicePrenotazione();

/**
 * Questa funzione ha il compito di controllare se la scelta durante la
 * visualizzazione menu, � stata scritta in maniera corretta.
 *
 * @param array Vettore di char, dove l'utente immetter� la sua scelta
 * @param opzione Variabile posta ad 1 se il controllo si effettua
 * 				  sul menu amministratore, posta a 0 se si effettua
 * 				  su quello utente
 *
 * @return 1 se la stringa � scritta correttamente, 0 se � errata.
 */
int ControlloMenu(char *array, int opzione);

/**
 * Questa funzione ha il compito di memorizzare il nome immesso
 * dall'amministratore nel momento in cui egli effettua una
 * ricerca tramite "Nome". Inoltre effettua gli opportuni controlli.
 *
 * @param temp Vettore dove immagazzinare l'input dell'amministratore
 * @param MAX_NOME_TEMP Dimensione massima del vettore appena indicato.
 * @return 1 se il nome � scritto correttamente, 0 se � errato.
 */
int ControlloNomeTemporaneo (char *temp, int MAX_NOME_TEMP);

/**
 * Questa funzione ha il compito di memorizzare il cognome immesso
 * dall'amministratore nel momento in cui egli effettua una
 * ricerca tramite "Cognome". Inoltre effettua gli opportuni controlli.
 *
 * @param temp Vettore dove immagazzinare l'input dell'amministratore
 * @param MAX_COGNOME_TEMP Dimensione massima del vettore appena indicato.
 * @return 1 se il cognome � scritto correttamente, 0 se � errato.
 */
int ControlloCognomeTemporaneo (char *temp, int MAX_NOME_TEMP);

/**
 * Questa funzione ha il compito di memorizzare il comune immesso
 * dall'amministratore nel momento in cui egli effettua una
 * ricerca tramite "Comune". Inoltre effettua gli opportuni controlli.
 *
 * @param temp Vettore dove immagazzinare l'input dell'amministratore
 * @param MAX_COMUNE_TEMP Dimensione massima del vettore appena indicato.
 * @return 1 se il comune � scritto correttamente, 0 se � errato.
 */
int ControlloComuneTemporaneo (char *temp, int MAX_COMUNE_TEMP);

/**
 * Controlla se il nome della compagnia aerea � composto da caratteri validi.
 *
 * @param temp Vettore dove immagazzinare l'input dell'amministratore
 * 			   e successivamente controllarlo.
 * @param MAX_NOME_COMPAGNIA_TEMP Massimo valore che il vettore temp pu� avere.
 * @return 1 se il nome della compagnia � scritto correttamente, 0 se � errato.
 */
int ControlloNomeCompagniaTemporaneo (char* temp, int MAX_NOME_COMPAGNIA_TEMP);

/**
 * Controlla se la tratta partenza � composta da caratteri validi.
 *
 * @param temp Vettore dove immagazzinare l'input dell'amministratore
 * 			   e successivamente controllarlo.
 * @param MAX_TRATTA_PARTENZA_TEMP Massimo valore che il vettore temp pu� avere.
 * @return 1 se la tratta � scritta correttamente, 0 se � errata.
 */
int ControlloTrattaPartenzaTemporaneo (char* temp, int MAX_TRATTA_PARTENZA_TEMP);

/**
 * Controlla se la tratta arrivo � composta da caratteri validi.
 *
 * @param temp Vettore dove immagazzinare l'input dell'amministratore
 * 			   e successivamente controllarlo.
 * @param MAX_TRATTA_ARRIVO_TEMP Massimo valore che il vettore temp pu� avere.
 * @return 1 se la tratta � scritta correttamente, 0 se � errata.
 */
int ControlloTrattaArrivoTemporaneo (char* temp, int MAX_TRATTA_ARRIVO_TEMP);

#endif /* CONTROLLI_H_ */
