package Modele;

import java.util.Hashtable;

public class Intervenants {
	private static String[] attributs = {"id", "nom", "prenom"};
	private Hashtable<String, String> donn�es = new Hashtable();
	private static int nbreInter = 1;
	
	public Intervenants(Hashtable<String, String> hashtable) {
		try {
			if(hashtable.containsKey("id")) {
				if(Integer.parseInt(hashtable.get("id"))+1 > nbreInter) {
					nbreInter = Integer.parseInt(hashtable.get("id"))+1;
				}
			} else {
				donn�es.put("id", nbreInter+"");
				nbreInter++;
			}
		} catch(Exception e) {
			donn�es.put("id", nbreInter+"");
			nbreInter++;
		}

		for(int i=0;i<attributs.length;i++) {
			if(!donn�es.containsKey(attributs[i])) {
				if(!hashtable.containsKey(attributs[i])) {
					donn�es.put(attributs[i], "");
				} else {
					donn�es.put(attributs[i], hashtable.get(attributs[i]));
				}
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
