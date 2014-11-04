package Lib;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.Hashtable;


public class Optilib {
	
	public static Hashtable<String, String>[] reader(String adresse_csv) {
		
		// Creation du tableau
		Hashtable<String, String>[] tableau;
		String fichier = "";
		
		try {
			
			// Lecture du fichier
			File fichier_csv = new File(adresse_csv);
			fichier = new String(Files.readAllBytes(fichier_csv.toPath()), "UTF-8");
		
		} catch (Exception e) {
			try {
				PrintWriter creation = new PrintWriter(new BufferedWriter(new FileWriter(adresse_csv)));
			} catch (IOException e1) {
			}
		}

		// Decoupe du fichier en lignes
		String retour = "\n";
		if(fichier.contains("\r\n")) {
			retour = "\r\n";
		}
		String[] lignes = fichier.split(retour);
		tableau = new Hashtable[lignes.length-1];

		// Recuperation des attributs (premiere ligne du tableau)
		String separation = ",";
		if(fichier.contains(";")) {
			separation = ";";
		}
		String[] attributs = lignes[0].split(separation);
		
		for(int i=1;i<lignes.length;i++) {
			String[] colonnes = lignes[i].split(separation);
			
			// Creation de la ligne
			Hashtable<String, String> ligne = new Hashtable();
			for(int j=0;j<colonnes.length;j++) {
				ligne.put(attributs[j], colonnes[j]);
			}
			
			tableau[i-1] = ligne;
		}

		return tableau;
	}
	
	public static void saver(Hashtable<String, String>[] tableau, String nom_fichier) {
		PrintWriter sauvegarde;
		try {
			sauvegarde = new PrintWriter(new BufferedWriter(new FileWriter(nom_fichier)));
			if(tableau.length>0) {
				// Ligne 1 : Les attributs
				Enumeration<String> attributs = tableau[0].keys();
				String attribut;
				String ligne = "";
				
				while(attributs.hasMoreElements()) {
					attribut = attributs.nextElement();
					ligne += attribut + ",";
				}
				
				ligne = ligne.substring(0, ligne.length()-1)+"\n";
				sauvegarde.print(ligne);
				
				for(int i=0;i<tableau.length;i++) {
					Enumeration<String> attributs2 = tableau[0].keys();
					String attribut2;
					String ligne2 = "";
	
					while(attributs2.hasMoreElements()) {
						attribut2 = attributs2.nextElement();
						ligne2 += tableau[i].get(attribut2) + ",";
					}
					ligne2 = ligne2.substring(0, ligne2.length()-1)+"\n";
					sauvegarde.print(ligne2);
				}
			}
			sauvegarde.close();
		
		} catch (IOException e) {
		}
	}
	
}
