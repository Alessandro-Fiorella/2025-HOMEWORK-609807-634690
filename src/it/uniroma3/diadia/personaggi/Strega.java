package it.uniroma3.diadia.personaggi;

import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	
	private static final String TRASFERIMENTO_GENTILE = "Mi stai simpatico, ti porterò nella stanza con più attrezzi";
	private static final String TRASFERIMENTO_RANCOROSO = "Ciao eh! Ti porterò nella stanza con meno attrezzi";
	private static final String MESSAGGIO_CATTIVO = "EhEh! Hai appena perso un attrezzo e non lo riavrai più indietro!";
	public Strega(String nome, String descrizione) {
		super(nome, descrizione);
	}
	
	public Strega() {
		super("Strega", "Sono una strega birichina");
	}
	
	public String agisci(Partita partita) {
		List<Stanza> stanzeAdiacenti = partita.getStanzaCorrente().getListaStanzeAdiacenti();
		Stanza stanzaMin = stanzeAdiacenti.get(0);
		Stanza stanzaMax = stanzeAdiacenti.get(0);
		for (Stanza s : stanzeAdiacenti) {
			if (s.getAttrezzi().size() < stanzaMin.getAttrezzi().size())
				stanzaMin = s;
			if (s.getAttrezzi().size() > stanzaMin.getAttrezzi().size())
				stanzaMax = s;
		}
		if (super.haSalutato()) {
			partita.setStanzaCorrente(stanzaMax);
			return TRASFERIMENTO_GENTILE;
		}
		else {
			partita.setStanzaCorrente(stanzaMin);
			return TRASFERIMENTO_RANCOROSO;
		}
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return MESSAGGIO_CATTIVO;
	}
}
