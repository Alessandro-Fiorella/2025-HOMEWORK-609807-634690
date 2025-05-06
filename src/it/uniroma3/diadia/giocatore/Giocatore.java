package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.IO;

public class Giocatore {
	
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;
	
	
	public Giocatore (IO ioConsole) {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa(ioConsole);
	}
	
	public Giocatore (int peso, IO ioConsole) {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa(peso, ioConsole);
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
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa; 
	}
	

}
