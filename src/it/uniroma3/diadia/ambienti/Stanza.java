package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
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

public class Stanza {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final protected int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private String nome;
	
    protected List<Attrezzo> attrezzi;
    private int massimoAttrezzi;
    
    private Map<Direzione, Stanza> direzioni2stanze;
    private int numeroStanzeAdiacenti;
	
	private AbstractPersonaggio personaggio;
		
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome, int massimoAttrezzi) {
    	this.nome = nome;
    	this.massimoAttrezzi = massimoAttrezzi;
        this.numeroStanzeAdiacenti = 0;
        this.direzioni2stanze = new HashMap<>();
        this.attrezzi = new ArrayList<>();
    }
    
    public Stanza(String nome) {
    	this(nome, NUMERO_MASSIMO_ATTREZZI);
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
    	// Aggiunge la direzione e la stanza o eventualmente aggiorna la stanza
    	if(numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
    		direzioni2stanze.put(Direzione.fromString(direzione), stanza); 	
        	numeroStanzeAdiacenti++;
    	}
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
        return direzioni2stanze.get(Direzione.fromString(direzione));
	}

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if (attrezzi.contains(attrezzo)) {
    		return false;
    	}
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
		//controlla se la stanza è vuota
		if(this.isEmpty()) {
			DiaDia.getIO().mostraMessaggio("La stanza è vuota.");
			return null;
		}
		
		Attrezzo output = this.getAttrezzo(nomeAttrezzo); 	// Se lo ha lo prende
		if(output != null) {
			attrezzi.remove(output);
		}
		else 
			DiaDia.getIO().mostraMessaggio("La stanza non possiede questo attrezzo");
		
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
	
	public boolean isEmpty() {
		return attrezzi.size() == 0;
	}
	
    public String getNome() {
        return this.nome;
    }
    
    public int getMassimoAttrezzi() {
    	return this.massimoAttrezzi;
    }
    
    public int getNumeroStanzeAdiacenti() {
    	return this.numeroStanzeAdiacenti;
    }
    
    public List<Direzione> getDirezioni() {
	    return new ArrayList<>(direzioni2stanze.keySet());
    }
    
    public List<Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }
    
    public Map<Direzione, Stanza> getMapStanzeAdiacenti(){
    	return direzioni2stanze;
    }
    
    public List<Stanza> getListaStanzeAdiacenti(){
    	return new ArrayList<>(direzioni2stanze.values());
    }
    
    
	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
  
	
    
    @Override
    public boolean equals(Object o) {
    	if (o == null || this.getClass() != o.getClass()) return false;
    	
    	Stanza that = (Stanza) o;
    	return this.getNome().equals(that.getNome()) &&
    			this.getMassimoAttrezzi() == that.getMassimoAttrezzi();
    			//this.getNumeroStanzeAdiacenti() == that.getNumeroStanzeAdiacenti();
    			// Forse da rivedere: per far funzinare i testLabirintoBuilder l'equals sembra dover essere solo tra nome 
    			// e massimoAttrezzi (o meglio, deve ignorare il numero e il tipo di stanze adiacenti)
    }
    
    @Override
    public int hashCode() {
    	return 	this.getClass().hashCode() +
    			this.getNome().hashCode() +
    			this.getMassimoAttrezzi();
    }
    
    
    public String getDescrizione() {
        return this.toString();
    }  
    
	/**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    @Override
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (Direzione direzione : this.direzioni2stanze.keySet())
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