package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Stanza che ogni tot oggetti posati modifica l'n esimo oggetto invertendo il suo nome e raddopiando il peso
 */
public class StanzaMagica extends Stanza {
	
	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	/*** Costruttore con soglia manuale e numero massimo attrezzi manuale*/
	public StanzaMagica(String nome, int soglia, int massimoAttrezzi) {
		super(nome, massimoAttrezzi);	// Uso il costruttore della stanza
		this.contatoreAttrezziPosati = 0;	// Inizializzo il contatore
		this.sogliaMagica = soglia;	// E inizializzo la soglia
	}
	
	/*** Costruttore con soglia manuale e numero massimo attrezzi default*/
	public StanzaMagica(String nome, int soglia) {
		this(nome, soglia, NUMERO_MASSIMO_ATTREZZI);
	}
	
	/*** Costruttore con soglia default e numero massimo attrezzi default*/
	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT, NUMERO_MASSIMO_ATTREZZI);	// Uso il costruttore qui sopra
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
	
	// Metodo ad hoc per testLabirintoBuilder, non saprei che caratteristica osservare
	public boolean isMagica() {
		return true;
	}
}
