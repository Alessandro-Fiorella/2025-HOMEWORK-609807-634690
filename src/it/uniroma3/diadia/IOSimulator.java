package it.uniroma3.diadia;

import java.util.List;

public class IOSimulator implements IO {

    private List<String> comandi;
    int i = 0;

    public IOSimulator(List<String> comandi) {
        this.comandi = comandi;
    }


    @Override
    public void mostraMessaggio(String messaggio) {
        System.out.println(messaggio + '\n');
    }

    @Override
    public String leggiRiga() {
        if(i<comandi.size()) {
            this.mostraMessaggio(comandi.get(i));
            return comandi.get(i++);
        }
        return null;
    }
}
