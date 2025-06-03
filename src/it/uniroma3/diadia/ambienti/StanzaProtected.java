package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.List;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class StanzaProtected {
	
	static final protected int NUMERO_MASSIMO_DIREZIONI = 4;
	static final protected int NUMERO_MASSIMO_ATTREZZI = 10;
	
	protected String nome;
	
    protected List<Attrezzo> attrezzi;
    protected int massimoAttrezzi;
    
    protected Stanza[] stanzeAdiacenti;
    protected int numeroStanzeAdiacenti;
    
	protected String[] direzioni;
	
    protected IO ioConsole;
	
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public StanzaProtected(String nome, int massimoAttrezzi, IO ioConsole) {
    	this.nome = nome;
    	this.massimoAttrezzi = massimoAttrezzi;
    	this.ioConsole = ioConsole;
        this.numeroStanzeAdiacenti = 0;
        this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
        this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
        this.attrezzi = new ArrayList<>();
    }
    
    public StanzaProtected(String nome, IO ioConsole) {
    	this(nome, NUMERO_MASSIMO_ATTREZZI, ioConsole);
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
        boolean aggiornato = false;
    	for(int i=0; i<this.direzioni.length; i++)
        	if (direzione.equals(this.direzioni[i])) {
        		this.stanzeAdiacenti[i] = stanza;
        		aggiornato = true;
        	}
    	if (!aggiornato)
    		if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
    			this.direzioni[numeroStanzeAdiacenti] = direzione;
    			this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
    		    this.numeroStanzeAdiacenti++;
    		}
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
        Stanza stanza = null;
		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
        	if (this.direzioni[i].equals(direzione))
        		stanza = this.stanzeAdiacenti[i];
        return stanza;
	}

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if (attrezzi.size() < massimoAttrezzi) {
    		this.attrezzi.add(attrezzo);
        	return true;
    	}
    	return false;
    }
    
    /**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo output = null;

		// controlla se la stanza è vuota
		if (this.isEmpty()) {
			ioConsole.mostraMessaggio("La stanza è vuota.");
			return output;
		}

		output = this.getAttrezzo(nomeAttrezzo); // Se lo ha lo prende
		if (output != null) {
			attrezzi.remove(output); // Le rimozione è semplice grazie alla lista
		}
		// a questo punto la borsa non ha attrezzi
		else
			this.ioConsole.mostraMessaggio("Non possiedi questo attrezzo.");

		return output;
	}


    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo != null) {
				if (attrezzo.getNome().equals(nomeAttrezzo))
					trovato = true;
			}
		}
		return trovato;
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
     * @param In overload: String nomeAttrezzo, Attrezzo attrezzo, Int (indice)
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo.getNome().equals(nomeAttrezzo))
				return attrezzo;
		}
		return null;
	}
	
	public Attrezzo getAttrezzo(Attrezzo attrezzo) {
		for (Attrezzo a : this.attrezzi) {
			if (a.equals(attrezzo))
				return a;
		}
		return null;	
	}
	
	public Attrezzo getAttrezzo(int indice) {
		if (attrezzi.size() > indice) {	// Evito l'exception
			return attrezzi.get(indice);
		}
		return null;	
	}

	public String[] getDirezioni() {
		String[] direzioni = new String[this.numeroStanzeAdiacenti];
	    for(int i=0; i<this.numeroStanzeAdiacenti; i++)
	    	direzioni[i] = this.direzioni[i];
	    return direzioni;
    }
	
	public boolean isEmpty() {
		return attrezzi.size() == 0;
	}
	
	/**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public List<Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }
    
    /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (String direzione : this.direzioni)
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo attrezzo : this.attrezzi) {
    		if(attrezzo!=null) {
    			risultato.append(attrezzo.toString()+" ");
    		}
    	}
    	return risultato.toString();
    }

}