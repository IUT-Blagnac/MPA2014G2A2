package Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Hashtable;

import Controleur.Controleur;
import Lib.Optilib;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestsOptilib extends TestCase {

	static int nbTestsOk = 0;
	static int nbTestsTotal = 0;

	public void test_Reader() {
		nbTestsTotal = nbTestsTotal + 2;
		
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(new FileWriter("test_data/test.csv")));
			
			fichier_test.println("Atr1,Atr2,Atr3,Atr4");
			fichier_test.println("Val1,Val2,Val3,Val4");
			
			fichier_test.close();
		} catch (IOException e) {}
		
		Hashtable<String, String>[] tableau = Optilib.reader("test_data/test.csv");
		
		if(tableau.length == 1) {
			nbTestsOk++;
		} else {
			System.err.println("Erreur : Reader ne lit pas toutes les lignes");
		}

		if(tableau[0].get("Atr4").equals("Val4")) {
			nbTestsOk++;
		} else {
			System.err.println("Erreur : Reader ne lit pas tous les éléments");
		}
	}
	
	public void test_Saver() {
		nbTestsTotal = nbTestsTotal + 1;
		
		Hashtable<String, String> etudiant = new Hashtable<String, String>();
		etudiant.put("nom", "Dupont");
		etudiant.put("groupe", "A");
		etudiant.put("prenom", "Balthazar");
		
		Controleur.initCsv();
		Controleur.entrés.put("Etudiants", "test_data/test4.csv");
		Controleur.importer();
		Controleur.sorties.put("Etudiants", "test_data/test3.csv");
		Controleur.ajouterEtudiant(etudiant);
		Controleur.exporter('e');
		
		try {
			
			File fichier_csv = new File("test_data/test3.csv");
			String fichier = new String(Files.readAllBytes(fichier_csv.toPath()), "UTF-8");

			if(fichier.equals("nom,groupe,prenom\nDupont,A,Balthazar\n")) {
				nbTestsOk++;
			} else {
				System.err.println("La sauvegarde ne s'effectue pas correctement");
			}
		} catch (Exception e) {}
	}

	public static void main(String[] args) {
	    junit.textui.TestRunner.run(new TestSuite(TestsOptilib.class));
	    System.out.println(nbTestsOk+"/"+nbTestsTotal+" tests validés.");
	}

}
