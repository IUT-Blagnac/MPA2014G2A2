package Modele;

import java.util.ArrayList;
import java.util.Hashtable;

public class Voeux {
	private static String[] attributs = {"groupe", "sujet", "position"};
	private Hashtable<String, String> donn�es = new Hashtable();
	
	public Voeux(Hashtable<String, String> hashtable) {
		for(int i=0;i<attributs.length;i++) {
			if(!hashtable.containsKey(attributs[i])) {
				donn�es.put(attributs[i], "");
			} else {
				donn�es.put(attributs[i], hashtable.get(attributs[i]));
			}
		}
	}

	public static String[] getAttributs() {
		return attributs;
	}
	
	public Hashtable<String, String> getDonn�es() {
		return donn�es;
	}

	public void setDonn�es(Hashtable<String, String> donn�es2) {
		this.donn�es = donn�es2;
	}
}
