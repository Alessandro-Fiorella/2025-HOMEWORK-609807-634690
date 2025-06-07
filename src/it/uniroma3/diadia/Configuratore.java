package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuratore {

    private static final String NOME_FILE_PROPERTIES = "diadia.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Configuratore.class.getClassLoader().getResourceAsStream(NOME_FILE_PROPERTIES)) {
            if (input == null) {
                throw new RuntimeException("File di configurazione " + NOME_FILE_PROPERTIES + " non trovato.");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Errore nel caricamento del file di configurazione.", e);
        }
    }

    public static int getCfuIniziali() {
        return Integer.parseInt(properties.getProperty("cfu", "10"));
    }

    public static int getPesoBorsaMax() {
        return Integer.parseInt(properties.getProperty("pesoBorsaMax", "10"));
    }
}
