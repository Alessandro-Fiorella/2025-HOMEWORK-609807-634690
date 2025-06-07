package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configuratore;

public class Giocatore {
	
	private int cfu;
	private Borsa borsa;
	
	
	public Giocatore () {
		this.cfu = Configuratore.getCfuIniziali();
		this.borsa = new Borsa();
	}
	
	public Giocatore (int peso) {
		this.cfu = Configuratore.getCfuIniziali();
		this.borsa = new Borsa(peso);
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	public void togliCfu() {
		this.cfu--;
	}
	
	public void toglinCfu(int quantity) {
		this.cfu -= quantity;
	}
	
	public void addnCfu(int quantity) {
		this.cfu += quantity;
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa; 
	}
	

}
