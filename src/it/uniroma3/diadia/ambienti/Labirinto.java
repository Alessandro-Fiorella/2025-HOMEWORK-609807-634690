package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class Labirinto {

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;

	public Labirinto() {

	}

	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanza) {
		this.stanzaIniziale = stanza;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaVincente(Stanza stanza) {
		this.stanzaVincente = stanza;
	}

	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private Stanza ultimaStanzaAggiunta;
		private Map<String, Stanza> nome2stanza;

		public LabirintoBuilder() {
			this.labirinto = new Labirinto();
			this.nome2stanza = new HashMap<>();
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public LabirintoBuilder addStanzaIniziale(String nome) {
			Stanza stanza = new Stanza(nome);
			this.labirinto.setStanzaIniziale(stanza);
			this.ultimaStanzaAggiunta = stanza;
			this.nome2stanza.put(nome, stanza);
			return this;
		}

		public LabirintoBuilder addStanzaIniziale(Stanza stanza) {
			this.labirinto.setStanzaIniziale(stanza);
			this.ultimaStanzaAggiunta = stanza;
			this.nome2stanza.put(stanza.getNome(), stanza);
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nome) {
			Stanza stanza = this.nome2stanza.get(nome);
			if (stanza == null) {
				stanza = new Stanza(nome);
				this.nome2stanza.put(nome, stanza);
			}
			this.labirinto.setStanzaVincente(stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}

		public LabirintoBuilder addStanza(String nome, int numeroMassimoAttrezzi) {
			Stanza stanza = new Stanza(nome, numeroMassimoAttrezzi);
			this.nome2stanza.put(nome, stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}

		public LabirintoBuilder addStanza(String nome) {
			Stanza stanza = new Stanza(nome);
			this.nome2stanza.put(nome, stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nome, String attrezzoSblocco, int numeroMassimoAttrezzi) {
			Stanza stanza = new StanzaBuia(nome, attrezzoSblocco, numeroMassimoAttrezzi);
			this.nome2stanza.put(nome, stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nome, String attrezzoSblocco) {
			Stanza stanza = new StanzaBuia(nome, attrezzoSblocco);
			this.nome2stanza.put(nome, stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nome, int sogliaMagica, int numeroMassimoAttrezzi) {
			Stanza stanza = new StanzaMagica(nome, sogliaMagica, numeroMassimoAttrezzi);
			this.nome2stanza.put(nome, stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nome, int sogliaMagica) {
			Stanza stanza = new StanzaMagica(nome, sogliaMagica);
			this.nome2stanza.put(nome, stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}

		public LabirintoBuilder addStanzaMagica(String nome) {
			Stanza stanza = new StanzaMagica(nome);
			this.nome2stanza.put(nome, stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nome, String attrezzoSblocco, String direzioneBloccata,
				int numeroMassimoAttrezzi) {
			Stanza stanza = new StanzaBloccata(nome, attrezzoSblocco, direzioneBloccata, numeroMassimoAttrezzi);
			this.nome2stanza.put(nome, stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nome, String attrezzoSblocco, String direzioneBloccata) {
			Stanza stanza = new StanzaBloccata(nome, attrezzoSblocco, direzioneBloccata);
			this.nome2stanza.put(nome, stanza);
			this.ultimaStanzaAggiunta = stanza;
			return this;
		}

		public LabirintoBuilder addAdiacenza(String nomeStanza1, String nomeStanza2, String direzione) {
			Stanza stanza1 = this.nome2stanza.get(nomeStanza1);
			Stanza stanza2 = this.nome2stanza.get(nomeStanza2);

			if (stanza1 != null && stanza2 != null) {
				stanza1.impostaStanzaAdiacente(direzione, stanza2);
			}
			return this;
		}

		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			if (this.ultimaStanzaAggiunta != null) {
				this.ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nome, peso));
			}
			return this;
		}

		public LabirintoBuilder addAttrezzo(Attrezzo attrezzo) {
			if (this.ultimaStanzaAggiunta != null) {
				this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
			}
			return this;
		}

		public LabirintoBuilder addPersonaggio(AbstractPersonaggio personaggio) {
			if (this.ultimaStanzaAggiunta != null) {
				this.ultimaStanzaAggiunta.setPersonaggio(personaggio);
			}
			return this;
		}

		public Map<String, Stanza> getMappaNome2Stanza() {
			return this.nome2stanza;
		}
	}
}
