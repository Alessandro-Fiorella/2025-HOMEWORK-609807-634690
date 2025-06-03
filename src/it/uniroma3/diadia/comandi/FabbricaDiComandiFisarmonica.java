package it.uniroma3.diadia.comandi;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
	
	public AbstractComando costruisciComando(String istruzione) {
		String nomeComando = null;
		String parametro = null;
		AbstractComando comando = null;
				
		String[] parole = istruzione.trim().split("\\s+"); // Dividiamo il comando nelle sue parole

	    if (parole.length > 0)
	        nomeComando = parole[0];

	    if (parole.length > 1)
	        parametro = parole[1];		
		
	    // Settiamo il tipo di comando
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
		
		else if (nomeComando.equals("interagisci"))
			comando = new ComandoInteragisci();
		
		else if (nomeComando.equals("saluta"))
			comando = new ComandoSaluta();
		
		else if (nomeComando.equals("regala"))
			comando = new ComandoRegala();
		
		else {
			comando = new ComandoNonValido();
		}
		
		// Solo alla fine settiamo il parametro
		comando.setParametro(parametro);
		return comando;
	}
	
}