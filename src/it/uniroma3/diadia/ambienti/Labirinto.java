package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	public Labirinto(IOConsole ioConsole) {
		creaStanze(ioConsole);
	}
	
	/**
    * Crea tutte le stanze e le porte di collegamento
    */
	protected void creaStanze(IOConsole ioConsole) {

		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
    	
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio", ioConsole);
		Stanza aulaN11 = new Stanza("Aula N11", ioConsole);
		Stanza aulaN10 = new Stanza("Aula N10", ioConsole);
		Stanza laboratorio = new Stanza("Laboratorio Campus", ioConsole);
		Stanza biblioteca = new Stanza("Biblioteca", ioConsole);
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
        setStanzaIniziale(atrio);  
		setStanzaVincente(biblioteca);
    }
	
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	
	public void setStanzaIniziale(Stanza stanza) {
		stanzaIniziale = stanza;
	}
	
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	
	public void setStanzaVincente(Stanza stanza) {
		stanzaVincente = stanza;
	}
	
}
