package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Borsa {

	private List<Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<>(); 
	}
	
	public Borsa() {
		this(Configuratore.getPesoBorsaMax());
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.add(attrezzo);
		return true;
	}
	
	// Rimuove l'attrezzo e lo restituisce
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
	    // Controlla se ci sono attrezzi nella borsa
	    if (this.isEmpty()) {
	    	DiaDia.getIO().mostraMessaggio("La borsa è vuota.");
	        return null;
	    }
	    Attrezzo output = this.getAttrezzo(nomeAttrezzo); 	// Se lo ha lo prende
		if(output != null) {
			attrezzi.remove(output);	// Le rimozione è semplice grazie alla lista
		}
		else 
			DiaDia.getIO().mostraMessaggio("Non possiedi questo attrezzo.");
		return output;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	/**
	 * @param In overload: String nomeAttrezzo, Attrezzo attrezzo, Int (indice)
	 * @return Attrezzo con nome giusto
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo.getNome().equals(nomeAttrezzo))
				return attrezzo;
		}
		return null;
	}
	
	public Attrezzo getAttrezzo(Attrezzo attrezzo) {
		for (Attrezzo a : this.attrezzi) {
			if (a.equals(attrezzo))
				return a;
		}
		return null;	
	}
	
	public Attrezzo getAttrezzo(int indice) {
		if (attrezzi.size() > indice) {	// Evito l'exception
			return attrezzi.get(indice);
		}
		return null;
	}
	
	
	/**
	 * @return Ritorna la lista degli attrezzi in borsa ordinati in base al peso(nome
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> output = new ArrayList<>(attrezzi);
		Attrezzo.ComparatorePerPeso comparator = new Attrezzo.ComparatorePerPeso();
		Collections.sort(output, comparator);
		return output;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPesoPoiPerNome(){
		Attrezzo.ComparatorePerPesoPoiPerNome comparator = new Attrezzo.ComparatorePerPesoPoiPerNome();
		SortedSet<Attrezzo> output = new TreeSet<>(comparator);
		output.addAll(attrezzi);
		return output;
	}

	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		Attrezzo.ComparatorePerNome comparator = new Attrezzo.ComparatorePerNome();
		SortedSet<Attrezzo> output = new TreeSet<>(comparator);
		output.addAll(attrezzi);
		return output;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer,Set<Attrezzo>> map = new HashMap<>();
		for (Attrezzo a : attrezzi) {
			int key = a.getPeso();
			
			if (map.containsKey(key)) 
				map.get(key).add(a);
			else {
				Set<Attrezzo> nuovoSet = new HashSet<>();
				nuovoSet.add(a);
				map.put(key, nuovoSet);
			}
		}
		
		return map;
	}
	
	public List<Attrezzo> getAttrezzi(){
		return attrezzi;
	}
	
	// Non c'è una variabile che memorizza il peso quindi va calcolato come somma dei pesi degli attrezzi
	public int getPeso() {
		int peso = 0;
		for (Attrezzo a : attrezzi)
			peso += a.getPeso();
		return peso;
	}
	
	public boolean isEmpty() {
		return attrezzi.size() == 0;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();

	    sb.append("Contenuto ordinato per nome:\n");
	    sb.append("{ ");
	    for (Attrezzo a : this.getContenutoOrdinatoPerNome()) {
	        sb.append(a.getNome()).append(", ");
	    }
	    if (!getContenutoOrdinatoPerNome().isEmpty()) {
	        sb.setLength(sb.length() - 2); // Rimuove l'ultima virgola
	    }
	    sb.append(" }\n");

	    sb.append("Contenuto ordinato per peso:\n");
	    sb.append("[ ");
	    for (Attrezzo a : this.getContenutoOrdinatoPerPeso()) {
	        sb.append(a.getNome()).append(", ");
	    }
	    if (!getContenutoOrdinatoPerPeso().isEmpty()) {
	        sb.setLength(sb.length() - 2);
	    }
	    sb.append(" ]\n");

	    sb.append("Contenuto raggruppato per peso:\n");
	    for (Map.Entry<Integer, Set<Attrezzo>> entry : this.getContenutoRaggruppatoPerPeso().entrySet()) {
	        sb.append("( ").append(entry.getKey()).append(", { ");
	        for (Attrezzo a : entry.getValue()) {
	            sb.append(a.getNome()).append(", ");
	        }
	        if (!entry.getValue().isEmpty()) {
	            sb.setLength(sb.length() - 2);
	        }
	        sb.append(" } )\n");
	    }

	    return sb.toString();
	}
	
	/*@Override
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo a : attrezzi)
				s.append(a.toString()+" ");
		}
		else
			s.append("Borsa vuota.");
		return s.toString();
	}*/
}

