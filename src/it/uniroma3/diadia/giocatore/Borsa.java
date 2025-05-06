package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	private IO ioConsole;
	
	
	public Borsa(IO ioConsole) {
		this(DEFAULT_PESO_MAX_BORSA, ioConsole);
	}
	
	public Borsa(int pesoMax, IO ioConsole) {
		this.ioConsole = ioConsole;
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		
	    // Controlla se ci sono attrezzi nella borsa
	    if (this.isEmpty()) {
	        this.ioConsole.mostraMessaggio("La borsa è vuota.");
	        return a;
	    }
	    // controlla se la borsa contiene l'attrezzo
		if(this.hasAttrezzo(nomeAttrezzo)) {
			
			int i = 0;
			while (!attrezzi[i].getNome().equals(nomeAttrezzo)){
				i++;
			}
			a = attrezzi[i];
			for(int j = i; j < numeroAttrezzi-1; j++){
				attrezzi[j]=attrezzi[j+1];
			}
		    attrezzi[numeroAttrezzi-1] = null;
			numeroAttrezzi--;
		}
		//a questo punto la borsa non ha attrezzi
		else this.ioConsole.mostraMessaggio("Non possiedi questo attrezzo.");
		
		return a;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				return attrezzi[i];
		
		this.ioConsole.mostraMessaggio("Non hai questo attrezzo");
		return null;
	}
	
	public Attrezzo[] getAttrezzi(){
		return attrezzi;
	}
	
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();
		return peso;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota.");
		return s.toString();
	}
	
	
}

