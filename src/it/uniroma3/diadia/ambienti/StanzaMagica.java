package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza {
	
	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	/*** Costruttore con soglia manuale e numero massimo attrezzi manuale*/
	public StanzaMagica(String nome, int soglia, int massimoAttrezzi, IO ioConsole) {
		super(nome, massimoAttrezzi, ioConsole);	// Uso il costruttore della stanza
		this.contatoreAttrezziPosati = 0;	// Inizializzo il contatore
		this.sogliaMagica = soglia;	// E inizializzo la soglia
	}
	
	/*** Costruttore con soglia manuale e numero massimo attrezzi default*/
	public StanzaMagica(String nome, int soglia, IO ioConsole) {
		this(nome, soglia, NUMERO_MASSIMO_ATTREZZI, ioConsole);
	}
	
	/*** Costruttore con soglia default e numero massimo attrezzi default*/
	public StanzaMagica(String nome, IO ioConsole) {
		this(nome, SOGLIA_MAGICA_DEFAULT, NUMERO_MASSIMO_ATTREZZI, ioConsole);	// Uso il costruttore qui sopra
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		
		Attrezzo daAggiungere = attrezzo;	// Creiamo un attrezzo temporaneo per non modificare quello originale
		if (this.contatoreAttrezziPosati + 1 > this.sogliaMagica)	// Se supero la soglia magica
			daAggiungere = this.modificaAttrezzo(attrezzo); // modifico l'attrezzo temporaneo 

		if (super.addAttrezzo(daAggiungere)) {	// Controllo se questo nuovo attrezzo è effettivamente da aggiungere e lo faccio
			this.contatoreAttrezziPosati++;		// Nel caso aumento il contatore
			return true;
		}

		// In caso non venga aggiunto, l’attrezzo originale resta intatto e non usato
		return false;
	}
	
	
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(), pesoX2);
		return attrezzo;
	}
	
	public int getContatoreAttrezziPosati() {
		return contatoreAttrezziPosati;
	}
}
