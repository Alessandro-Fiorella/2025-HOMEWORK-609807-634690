package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	
	public static final String TI_HO_MORSO = "Roar, ti divoro!";
	public static final String CANE_SODDISFATTO = "Sto giocando col mio attrezzo, vai via";
	public static final String RICEVE_ATTREZZO_PREFERITO = "Grazie, questo attrezzo è adorabile. Ti ridò qualche CFU";
	public static final String RICEVE_ATTREZZACCIO = "Mamma mia che robaccia, ora ti mordo eheh";
	public static final int POTENZA_DI_CANE_POTENTE = 15;

	public String attrezzoPreferito;
	public int potenza; // Numero di cfu che rimuove
	public boolean morde;	// Morde se non ha ancora ricevuto il giusto regalo
	
	public Cane (String nome, String descrizione, String attrezzoPreferito, int potenza) {
		super(nome, descrizione+". Il mio attrezzo preferito è " + attrezzoPreferito);
		this.attrezzoPreferito = attrezzoPreferito;
		this.potenza = potenza;
		morde = true;
	}
	
	public Cane (int potenza) {
		super("Cane", "Un cane semplice"+". Il mio attrezzo preferito è ovviamente un osso");
		this.attrezzoPreferito = "osso";
		this.potenza = potenza;
		morde = true;
	}
	
	public Cane() {
		super("Cane", "Un cane potente"+". Il mio attrezzo preferito è ovviamente un osso");
		this.attrezzoPreferito = "osso";
		this.potenza = POTENZA_DI_CANE_POTENTE;
		morde = true;
	}
	
	@Override
	public String agisci(Partita partita) {
		if(morde) {
			partita.getGiocatore().toglinCfu(potenza);
			return TI_HO_MORSO;
		}
		else {
			return CANE_SODDISFATTO;
		}
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (morde) {	// Se non morde vuol dire che ha già ricevuto il regalo
			if(attrezzo.getNome().equals(attrezzoPreferito)) {
				partita.getGiocatore().addnCfu(potenza);
				morde = false;
				return RICEVE_ATTREZZO_PREFERITO;
			}
			else {
				partita.getGiocatore().toglinCfu(potenza);
				return RICEVE_ATTREZZACCIO;
			}
		}
		else 
			return CANE_SODDISFATTO;
	}
}
