package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;



public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
	
	public Comando costruisciComando(String istruzione, IOConsole ioConsole) {
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
				
		String[] parole = istruzione.trim().split("\\s+"); // Dividiamo il comando nelle sue parole

	    if (parole.length > 0)
	        nomeComando = parole[0];

	    if (parole.length > 1)
	        parametro = parole[1];		
			
		if (nomeComando.equals("vai")) 
			comando = new ComandoVai();
			
		else if (nomeComando.equals("prendi"))
			comando = new ComandoPrendi();
			
		else if (nomeComando.equals("posa"))
			comando = new ComandoPosa();
		
		else if (nomeComando.equals("guarda"))
			comando = new ComandoGuarda();
			
		else if (nomeComando.equals("fine"))
			comando = new ComandoFine();
			
		else if (nomeComando.equals("aiuto"))
			comando = new ComandoAiuto();
		
		else {
			comando = new ComandoNonValido();
		}
		
		comando.setParametro(parametro);
		return comando;
	}
	
}