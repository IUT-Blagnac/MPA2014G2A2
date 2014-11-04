package Modele;

import java.util.Hashtable;

public class Projets {
	private static String[] attributs = {"id", "groupe", "sujet", "client", "superviseur", "support_technique"};
	private Hashtable<String, String> donnees = new Hashtable();
	private static int nbreProjets = 1;
	
	public Projets(Hashtable<String, String> hashtable) {
		try {
			if(hashtable.containsKey("id")) {
				if(Integer.parseInt(hashtable.get("id"))+1 > nbreProjets) {
					nbreProjets = Integer.parseInt(hashtable.get("id"))+1;
				}
			} else {
				donnees.put("id", nbreProjets+"");
				nbreProjets++;
			}
		} catch(Exception e) {
			donnees.put("id", nbreProjets+"");
			nbreProjets++;
		}
		
		for(int i=0;i<attributs.length;i++) {
			if(!donnees.containsKey(attributs[i])) {
				if(!hashtable.containsKey(attributs[i])) {
					donnees.put(attributs[i], "");
				} else {
					donnees.put(attributs[i], hashtable.get(attributs[i]));
				}
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
