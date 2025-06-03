package it.uniroma3.diadia.ambienti;

/**
 * Estensione di stanza all'interno della quale non è possibile vedere gli attrezzi disponibili
 * se non si disponde del giusto attrezzo.
 */
public class StanzaBuia extends Stanza{
	
	// TODO: forse dovrebbe avere un attrezzo vero e proprio e non solo una string?
	private String attrezzoSblocco;
	
	public StanzaBuia(String nome, String attrezzoSblocco,  int massimoAttrezzi) {
		super(nome, massimoAttrezzi);
		this.attrezzoSblocco = attrezzoSblocco;
	}
	
	public StanzaBuia(String nome, String attrezzoSblocco) {
		this(nome, attrezzoSblocco, NUMERO_MASSIMO_ATTREZZI);
	}
	
	@Override
	public String getDescrizione() {
		if (this.hasAttrezzo(attrezzoSblocco))
			return this.toString();
		else
			return "Qui c'è buio pesto!";
	}
	
}
