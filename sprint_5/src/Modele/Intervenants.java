package Modele;

import java.util.Hashtable;

public class Intervenants {
	private static String[] attributs = {"id", "nom", "prenom"};
	private Hashtable<String, String> donnees = new Hashtable();
	private static int nbreInter = 1;
	
	public Intervenants(Hashtable<String, String> hashtable) {
		try {
			if(hashtable.containsKey("id")) {
				if(Integer.parseInt(hashtable.get("id"))+1 > nbreInter) {
					nbreInter = Integer.parseInt(hashtable.get("id"))+1;
				}
			} else {
				donnees.put("id", nbreInter+"");
				nbreInter++;
			}
		} catch(Exception e) {
			donnees.put("id", nbreInter+"");
			nbreInter++;
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
