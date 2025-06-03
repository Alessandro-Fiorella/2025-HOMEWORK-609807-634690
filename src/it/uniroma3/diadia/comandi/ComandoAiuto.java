package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ComandoAiuto extends AbstractComando {

	private static final String COMANDI_PACKAGE = "it.uniroma3.diadia.comandi";

	@Override
	public void esegui(Partita partita, IO ioConsole) {
		List<String> comandiDisponibili = this.getComandiDisponibili();
		for (String nomeComando : comandiDisponibili) {
			ioConsole.mostraMessaggio(nomeComando);
		}
	}

	private List<String> getComandiDisponibili() {
		List<String> nomiComandi = new ArrayList<>();

		// Converti il nome del package in un percorso file
		String path = COMANDI_PACKAGE.replace('.', '/');

		// Ottieni la directory delle classi
		URL risorsa = this.getClass().getClassLoader().getResource(path);
		if (risorsa == null)
			return nomiComandi;

		File dir = new File(risorsa.getFile());
		if (!dir.exists() || !dir.isDirectory())
			return nomiComandi;

		for (File file : dir.listFiles()) {
			String nomeFile = file.getName();
			if (nomeFile.endsWith(".class") && nomeFile.startsWith("Comando")) {
				try {
					String nomeClasse = COMANDI_PACKAGE + "." + nomeFile.replace(".class", "");
					Class<?> clazz = Class.forName(nomeClasse);

					// Verifica che implementi la classe astratta AbstractComando e non sia astratta
					if (AbstractComando.class.isAssignableFrom(clazz) && !Modifier.isAbstract(clazz.getModifiers())) {
						String nome = ((AbstractComando) clazz.getDeclaredConstructor().newInstance()).getNome();
						nomiComandi.add(nome);
					}

				} catch (Exception e) {
					// Ignora classi non instanziabili
					e.printStackTrace();
				}
			}
		}
		return nomiComandi;
	}

	@Override
	public String getNome() {
		return "aiuto";
	}
}
