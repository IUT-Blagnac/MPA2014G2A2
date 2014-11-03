package Modele;

import java.util.ArrayList;
import java.util.Hashtable;

public class Voeux {
	private static String[] attributs = {"groupe", "sujet", "position"};
	private Hashtable<String, String> données = new Hashtable();
	
	public Voeux(Hashtable<String, String> hashtable) {
		for(int i=0;i<attributs.length;i++) {
			if(!hashtable.containsKey(attributs[i])) {
				données.put(attributs[i], "");
			} else {
				données.put(attributs[i], hashtable.get(attributs[i]));
			}
		}
	}

	public static String[] getAttributs() {
		return attributs;
	}
	
	public Hashtable<String, String> getDonnées() {
		return données;
	}

	public void setDonnées(Hashtable<String, String> données2) {
		this.données = données2;
	}
}
