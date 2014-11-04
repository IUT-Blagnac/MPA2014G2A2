package Modele;

import java.util.ArrayList;
import java.util.Hashtable;

public class Etudiants {
	private static String[] attributs = {"groupe", "nom", "prenom"};
	private Hashtable<String, String> donnees = new Hashtable();
	
	public Etudiants(Hashtable<String, String> hashtable) {
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
}
