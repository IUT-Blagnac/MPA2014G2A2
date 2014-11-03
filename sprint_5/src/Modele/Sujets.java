package Modele;

import java.util.Hashtable;

public class Sujets {
	private static String[] attributs = {"id", "nom", "titre"};
	private Hashtable<String, String> données = new Hashtable();
	private static int nbreSujets = 1;
	
	public Sujets(Hashtable<String, String> hashtable) {
		try {
			if(hashtable.containsKey("id")) {
				if(Integer.parseInt(hashtable.get("id"))+1 > nbreSujets) {
					nbreSujets = Integer.parseInt(hashtable.get("id"))+1;
				}
			} else {
				données.put("id", nbreSujets+"");
				nbreSujets++;
			}
		} catch(Exception e) {
			données.put("id", nbreSujets+"");
			nbreSujets++;
		}
		
		for(int i=0;i<attributs.length;i++) {
			if(!données.containsKey(attributs[i])) {
				if(!hashtable.containsKey(attributs[i])) {
					données.put(attributs[i], "");
				} else {
					données.put(attributs[i], hashtable.get(attributs[i]));
				}
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
