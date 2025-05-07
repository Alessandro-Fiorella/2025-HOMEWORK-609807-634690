package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	
	public Labirinto(IO ioConsole) {
		creaStanze(ioConsole);
	}
	
	/**
    * Crea tutte le stanze e le porte di collegamento
    */
	protected void creaStanze(IO ioConsole) {

	    /* crea gli attrezzi */
	    Attrezzo chiave = new Attrezzo("chiave", 1);
	    Attrezzo libro = new Attrezzo("libro", 2);

	    /* crea stanze del labirinto */
	    Stanza cortile = new Stanza("Cortile", ioConsole);
	    Stanza mensa = new Stanza("Mensa", ioConsole);
	    Stanza segreteria = new Stanza("Segreteria", ioConsole);
	    Stanza aulaStudio = new Stanza("Aula Studio", ioConsole);
	    Stanza aulaMagna = new StanzaBloccata("Aula Magna", "est", "chiave", ioConsole); // bloccata ad est

	    /* collega le stanze */
	    cortile.impostaStanzaAdiacente("nord", mensa);
	    mensa.impostaStanzaAdiacente("sud", cortile);
	    mensa.impostaStanzaAdiacente("est", segreteria);
	    segreteria.impostaStanzaAdiacente("ovest", mensa);
	    segreteria.impostaStanzaAdiacente("est", aulaStudio);
	    aulaStudio.impostaStanzaAdiacente("ovest", segreteria);
	    aulaStudio.impostaStanzaAdiacente("est", aulaMagna);
	    aulaMagna.impostaStanzaAdiacente("ovest", aulaStudio);

	    /* pone gli attrezzi nelle stanze */
	    cortile.addAttrezzo(chiave);
	    mensa.addAttrezzo(libro);

	    /* imposta inizio e fine */
	    setStanzaIniziale(cortile);
	    setStanzaVincente(aulaMagna);
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
