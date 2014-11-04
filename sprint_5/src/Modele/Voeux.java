package Modele;

import java.util.ArrayList;
import java.util.Hashtable;

public class Voeux implements Comparable<Voeux> {
	private static String[] attributs = {"groupe", "sujet", "position"};
	private Hashtable<String, String> donnees = new Hashtable();
	
	public Voeux(Hashtable<String, String> hashtable) {
		for(int i=0;i<attributs.length;i++) {
			if(!hashtable.containsKey(attributs[i])) {
				donnees.put(attributs[i], "");
			} else {
				donnees.put(attributs[i], hashtable.get(attributs[i]));
			}
		}
	}

	public static String[] getAttributs() {
		return attributs;
	}
	
	public Hashtable<String, String> getDonnees() {
		return donnees;
	}

	public void setDonnees(Hashtable<String, String> donnees2) {
		this.donnees = donnees2;
	}
	
	public int compareTo(Voeux v2) {
		if(Integer.parseInt(this.getDonnees().get("position")) < Integer.parseInt(v2.getDonnees().get("position"))) {
			return -1;
		} else {
			return 1;
		}
	}
}
