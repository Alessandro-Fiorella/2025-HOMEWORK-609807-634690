package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IOConsole;



public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
	
	public FabbricaDiComandiFisarmonica(IOConsole ioConsole) {
	
	}
	
	
	public Comando costruisciComando(String istruzione, IOConsole ioConsole) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		
	
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
			
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
		
		/*ioConsole.mostraMessaggio(nomeComando);
		ioConsole.mostraMessaggio(parametro);*/
		//if (nomeComando == null)
			//comando = new ComandoNonValido();
			
		if (nomeComando.equals("vai")) 
			comando = new ComandoVai(ioConsole);
			
		else if (nomeComando.equals("prendi"))
			comando = new ComandoPrendi(ioConsole);
			
		else if (nomeComando.equals("posa"))
			comando = new ComandoPosa(ioConsole);
			
		//else if (nomeComando.equals("aiuto"))
			//comando = new ComandoAiuto();
			
		//else if (nomeComando.equals("fine"))
			//comando = new ComandoFine();
			
		//else if (nomeComando.equals("guarda"))
			//comando = new ComandoGuarda();
			
		//else comando = new ComandoNonValido();
			//comando.setParametro(parametro);
		else {
			comando = new ComandoNonValido(ioConsole);
		}
		
		comando.setParametro(parametro);
		return comando;
	}
	
	/* TODO subito: implementare i vari comandi. Attenti: comando posa e comando prendi non sono completi, abbiamo preso solamente la parte che era presente in DiaDia, 
	 * ma un altra parte presente in Giocatore (o forse in borsa e in stanza non ricordo) va ancora tagliata e incollata in queste classe. Anche comandoNonValido
	 * Evidentemente non è ben implementata.
	 */
}