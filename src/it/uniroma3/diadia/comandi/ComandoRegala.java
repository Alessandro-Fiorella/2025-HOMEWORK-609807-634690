package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {
	
	private String nomeAttrezzo;

	@Override	
	public void esegui(Partita partita, IO ioConsole) {
		if (nomeAttrezzo == null) {
			ioConsole.mostraMessaggio("Devi specificare l'attrezzo da regalare!");
			return;
		}
		
		// Controllo ci sia una persona
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio == null) {
			ioConsole.mostraMessaggio("Se vuoi puoi buttare l'oggetto per terra...qui non c'è nessuno...");
			return;
		}
		
		// Rimuovo l'attrezzo e controllo di averlo effettivamente posseduto. Se il personaggio non vuole tale oggetto
		// allora ho sprecato l'oggetto
		Attrezzo attrezzoDaPosare = partita.getBorsa().removeAttrezzo(nomeAttrezzo);
		if (attrezzoDaPosare != null) {
			ioConsole.mostraMessaggio(personaggio.riceviRegalo(attrezzoDaPosare, partita));
		}
	}
	
	@Override
	public void setParametro(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return "regala";
	}
}
