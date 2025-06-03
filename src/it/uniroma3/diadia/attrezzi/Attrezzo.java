//lallo
package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */
public class Attrezzo {

	private String nome;
	private int peso;

	/**
	 * Crea un attrezzo
	 * @param nome il nome che identifica l'attrezzo
	 * @param peso il peso dell'attrezzo
	 */
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}
	
	public Attrezzo() {
		this.peso = 1;
		this.nome = "Null";
	}

	/**
	 * Restituisce il nome identificatore dell'attrezzo
	 * @return il nome identificatore dell'attrezzo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce il peso dell'attrezzo
	 * @return il peso dell'attrezzo
	 */
	public int getPeso() {
		return this.peso;
	}

	/**
	 * Restituisce una rappresentazione stringa di questo attrezzo
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		
		Attrezzo that = (Attrezzo) o;
		return this.getNome().equals(that.getNome()) &&
				this.getPeso() == that.getPeso();
	}
	
	@Override
	public int hashCode() {
		return this.getNome().hashCode() + this.getPeso();
	}
	
	public static class ComparatorePerNome implements Comparator<Attrezzo> {
		@Override
        public int compare(Attrezzo a1, Attrezzo a2) {
            return a1.nome.compareTo(a2.nome);
        }
    }

    public static class ComparatorePerPeso implements Comparator<Attrezzo> {
    	@Override
        public int compare(Attrezzo a1, Attrezzo a2) {
            return Integer.compare(a1.peso, a2.peso);
        }
    }
    
    public static class ComparatorePerPesoPoiPerNome implements Comparator<Attrezzo> {
    	@Override
        public int compare(Attrezzo a1, Attrezzo a2) {
            int output = Integer.compare(a1.peso, a2.peso);
            if (output == 0) {
            	return a1.nome.compareTo(a2.nome);
            }
            return output;
        }
    }

}