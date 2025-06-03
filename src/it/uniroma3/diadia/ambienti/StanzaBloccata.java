package it.uniroma3.diadia.ambienti;

/**
 * Stanza con le direzioni bloccate almeno che non si abbia lo strumento giusto
 */
public class StanzaBloccata extends Stanza {
	
	private String direzioneBloccata;
	private String attrezzoSblocco;
	
	public StanzaBloccata(String nome, String attrezzoSblocco, String direzioneBloccata, int massimoAttrezzi){
		super(nome, massimoAttrezzi);	// Uso il costruttore della stanza
		this.direzioneBloccata = direzioneBloccata; //direzione bloccata dalla stanza
		this.attrezzoSblocco = attrezzoSblocco; //attrezzio con cui sbloccare
	}
	
	public StanzaBloccata(String nome, String attrezzoSblocco, String direzioneBloccata) {
		this(nome, attrezzoSblocco, direzioneBloccata, NUMERO_MASSIMO_ATTREZZI);
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione){
		if(!this.hasAttrezzo(attrezzoSblocco)&&direzione.equals(direzioneBloccata)) return this;
	
		return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione() {
		return toString()+"\n"+"La direzione "+direzioneBloccata+" è bloccata";
	}
	
}
