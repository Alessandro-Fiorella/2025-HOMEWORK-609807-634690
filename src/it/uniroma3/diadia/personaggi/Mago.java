package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	
	private static final String MESSAGGIO_REGALO = "Ho diminuito di 1 il peso di ";
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_MAGIE_FINITE = "Ho finito le mie magie";
	private static final String MESSAGGIO_PESO_INSUFFICIENTE = "Il tuo oggetto pesa già il minimo possibile";
	
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private static final int NUMERO_MAGIE_DEFAULT = 3;
	private Attrezzo attrezzo;
	private int numeroMagie;
	
	public Mago(String nome, String descrizione, String nomeAttrezzo, int pesoAttrezzo, int numeroMagie) {
		super(nome, descrizione);
		this.attrezzo = new Attrezzo(nomeAttrezzo, pesoAttrezzo);
		this.numeroMagie = numeroMagie;
	}
	
	public Mago (String nomeAttrezzo, int pesoAttrezzo) {
		this("Mago", "Un mago di default", nomeAttrezzo, pesoAttrezzo, NUMERO_MAGIE_DEFAULT);
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo != null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		} else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (numeroMagie > 0) {
			if (attrezzo.getPeso() < 1) {
				return MESSAGGIO_PESO_INSUFFICIENTE;
			}
			attrezzo.diminuisciPeso();
			partita.getBorsa().addAttrezzo(attrezzo);
			numeroMagie--;
			return MESSAGGIO_REGALO + attrezzo.getNome();
		}
		else 
			return MESSAGGIO_MAGIE_FINITE;
	}

	public Attrezzo getAttrezzo() {
		return attrezzo;
	}
	
	public void setAttrezzo(Attrezzo attrezzo) {
		this.attrezzo = attrezzo;
	}
}
