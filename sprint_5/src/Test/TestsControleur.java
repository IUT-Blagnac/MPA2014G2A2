package Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Hashtable;

import org.omg.CORBA.SystemException;



import Controleur.Controleur;
import Lib.Optilib;
import Modele.Etudiants;
import Modele.Intervenants;
import Modele.Projets;
import Modele.Sujets;
import Modele.Voeux;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestsControleur extends TestCase {

	static int nbTestsOk = 0;
	static int nbTestsTotal = 0;

	public void test_Importer() {
		nbTestsTotal = nbTestsTotal + 3;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/test.csv")));
			
			fichier_test.println("groupe,nom,prenom");
			fichier_test.println("Val1,Val2,Val3");

			fichier_test.close();
		} catch (IOException e) {
		}

		Controleur.initCsv();
		Controleur.entrés.put("Etudiants", "test_data/test.csv");
		Controleur.importer();

		if (Controleur.getEtudiants().size() == 1) {
			nbTestsOk++;
		} else {
			System.err
					.println("Erreur : Importer ne récupère pas tous les enregistrements");
		}

		if (Controleur.getEtudiants().get(0).getDonnées().get("nom")
				.equals("Val2")) {
			nbTestsOk++;
		} else {
			System.err
					.println("Erreur : Importer ne récupère pas les bonnes valeurs");
		}

		Controleur.entrés.put("Etudiants", "test_data/test2.csv");
		Controleur.importer();

		if (Controleur.getEtudiants().size() == 71) {
			nbTestsOk++;
		} else {
			System.err
					.println("Erreur : Importer ne récupère pas tous les enregistrements");
		}

	}

	// TEST DES FONCTIONS DE SUPPRESSION

	public void test_supprimer_Sujet() {
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testSuppSujet.csv")));

			fichier_test.println("groupe,nom,prenom");
			fichier_test.println("Val0,Val1,Val2");
			fichier_test.close();
		} catch (IOException e) {
		}

		Controleur.initCsv();
		Controleur.entrés.put("Sujets", "test_data/testSuppSujet.csv");
		Controleur.importer();
			
		Hashtable<String, String> données = new Hashtable();
		données.put("id", "Val0");
		données.put("nom", "Val1");
		données.put("titre", "Val2");
		
		ArrayList<Sujets>ListSujet = Controleur.getSujets();
		Sujets S = new Sujets(données);
		
		ListSujet.add(S);
		Controleur.supprimerSujet(S);
		if(Controleur.getSujets().size()==1){
			nbTestsOk++;
		}else{
			System.err
			.println("Erreur : supprimerSujet ne supprime pas le sujet");
		}	
	}
	public void test_supprimer_Proget() {
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testSuppProjets.csv")));

			fichier_test.println("groupe,sujet");
			fichier_test.println("Val0,Val1");
			fichier_test.close();
		} catch (IOException e) {
		}

		Controleur.initCsv();
		Controleur.entrés.put("Projets", "test_data/testSuppProjets.csv");
		Controleur.importer();
			
		Hashtable<String, String> données = new Hashtable();
		données.put("groupe", "Val0");
		données.put("sujet", "Val1");
		
		ArrayList<Projets>ListProjets = Controleur.getProjets();
		Projets P = new Projets(données);
		
		ListProjets.add(P);
		Controleur.supprimerProjet(P);
		if(Controleur.getProjets().size()==1){
			nbTestsOk++;
		}else{
			System.err
			.println("Erreur : supprimerProjets ne supprime pas le proget");
		}	
	}
	public void test_supprimer_Intervenant() {
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testSuppIntervenants.csv")));

			fichier_test.println("nom,prenom");
			fichier_test.println("Val0,Val1");
			fichier_test.close();
		} catch (IOException e) {
		}

		Controleur.initCsv();
		Controleur.entrés.put("Intervenants", "test_data/testSuppIntervenants.csv");
		Controleur.importer();
			
		Hashtable<String, String> données = new Hashtable();
		données.put("nom", "prenom");
		données.put("sujet", "Val1");
		
		ArrayList<Intervenants>ListIntervenants = Controleur.getIntervenants();
		Intervenants P = new Intervenants(données);
		
		ListIntervenants.add(P);
		Controleur.supprimerIntervenant(P);
		if(Controleur.getIntervenants().size()==1){
			nbTestsOk++;
		}else{
			System.err
			.println("Erreur : supprimerIntervenant ne supprime pas le Intervenants");
		}	
	}
	public void test_supprimer_Etudiant() {
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testSuppEtudiants.csv")));

			fichier_test.println("groupe,nom,prenom");
			fichier_test.println("Val0,Val1,Val2");
			fichier_test.close();
		} catch (IOException e) {
		}

		Controleur.initCsv();
		Controleur.entrés.put("Etudiants", "test_data/testSuppEtudiants.csv");
		Controleur.importer();
			
		Hashtable<String, String> données = new Hashtable();
		données.put("groupe", "val0");
		données.put("nom", "Val1");
		données.put("prenom", "Val2");
		
		ArrayList<Etudiants>ListEtudiants = Controleur.getEtudiants();
		Etudiants P = new Etudiants(données);
		
		ListEtudiants.add(P);
		Controleur.supprimerEtudiant(P);
		if(Controleur.getEtudiants().size()==1){
			nbTestsOk++;
		}else{
			System.err
			.println("Erreur : supprimerEtudiant ne supprime pas le Etudiants");
		}	
	}

	// TEST DES FONCTIONS D'AJOUT

	public void test_ajout_Sujet() {
		nbTestsTotal++;

		Controleur.initCsv();
		Controleur.entrés.put("Sujets", "test_data/testAjouSujet.csv");
		Controleur.importer();
			
		Hashtable<String, String> données = new Hashtable();
		données.put("id", "Val0");
		données.put("nom", "Val1");
		données.put("titre", "Val2");
		
		ArrayList<Sujets>ListSujet = Controleur.getSujets();
		Sujets S = new Sujets(données);
		
		
		Controleur.ajouterSujet(données);
		if(Controleur.getSujets().size()==1){
			nbTestsOk++;
		}else{
			System.err
			.println("Erreur : ajouter n'ajoute pas le sujet");
		}	
	}
	public void test_ajout_Etudiant() {
		nbTestsTotal++;
		Controleur.initCsv();
		Controleur.entrés.put("Etudiants", "test_data/testAjouEtudiants.csv");
		Controleur.importer();
		Hashtable<String, String> données = new Hashtable();
		données.put("groupe", "val0");
		données.put("nom", "Val1");
		données.put("prenom", "Val2");
		ArrayList<Etudiants>listEtudiants = Controleur.getEtudiants();
		Etudiants E = new Etudiants(données);
		Controleur.ajouterEtudiant(données);
		if(Controleur.getEtudiants().size()==1){
			nbTestsOk++;
		}else{
			System.err
			.println("Erreur : ajouterEtudiants n'ajoute pas léétudiant");
		}
	}
	public void test_ajout_Proget() {
		nbTestsTotal++;
		Controleur.initCsv();
		Controleur.entrés.put("Projets", "test_data/testAjoutProjets.csv");
		Controleur.importer();
		Hashtable<String, String> données = new Hashtable();
		données.put("groupe", "val0");
		données.put("sujet", "Val1");
		ArrayList<Projets>listIntervenants = Controleur.getProjets();
		Projets I = new Projets(données);
		Controleur.ajouterProjet(données);
		if(Controleur.getProjets().size()==1){
			nbTestsOk++;
		}else{
			System.err
			.println("Erreur : ajouterProjet n'ajoute pas le projet");
		}
	}
	public void test_ajout_Intervenant() {
		nbTestsTotal++;
		Controleur.initCsv();
		Controleur.entrés.put("Intervenants", "test_data/testAjouIntervenants.csv");
		Controleur.importer();
		Hashtable<String, String> données = new Hashtable();
		données.put("groupe", "val0");
		données.put("nom", "Val1");
		données.put("prenom", "Val2");
		ArrayList<Intervenants>listIntervenants = Controleur.getIntervenants();
		Intervenants I = new Intervenants(données);
		Controleur.ajouterIntervenant(données);
		if(Controleur.getIntervenants().size()==1){
			nbTestsOk++;
		}else{
			System.err
			.println("Erreur : ajouterIntervenant n'ajoute pas l'intervenant");
		}
		
	}

	//TEST DES OBSERVATEUR :
	
	public void test_getEtu(){
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testGetEtu.csv")));

			fichier_test.println("groupe,nom,prenom");
			fichier_test.println("Val1,Val2,Val3");
			fichier_test.close();
		} catch (IOException e) {
		}
		Controleur.initCsv();
		Controleur.entrés.put("Etudiants", "test_data/testGetEtu.csv");
		Controleur.importer();
			
		ArrayList<Etudiants>ListEtu_Fichier = Controleur.getEtudiants();
		ArrayList<Etudiants> ListEtu = new ArrayList<Etudiants>();
		
		Hashtable<String, String> données = new Hashtable();
		données.put("groupe", "Val1");
		données.put("nom", "Val2");
		données.put("prenom", "Val3");
		Etudiants E = new Etudiants(données);
		ListEtu.add(E);
		
		if(ListEtu.get(0).getDonnées().get("groupe").equals(ListEtu_Fichier.get(0).getDonnées().get("groupe")) && 
				(ListEtu.get(0).getDonnées().get("nom").equals(ListEtu_Fichier.get(0).getDonnées().get("nom")) &&
						(ListEtu.get(0).getDonnées().get("prenom").equals(ListEtu_Fichier.get(0).getDonnées().get("prenom")) ))){
			nbTestsOk++;
			
		}else{
			System.err
			.println("Erreur : getEtudiants n'accede pas a tous les etudiants");
		}
	}
	public void test_getSujets(){
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testGetSuj.csv")));

			fichier_test.println("id,nom,titre");
			fichier_test.println("1,Val2,Val3");
			fichier_test.close();
		} catch (IOException e) {
		}
		Controleur.initCsv();
		Controleur.entrés.put("Sujets", "test_data/testGetSuj.csv");
		Controleur.importer();
		ArrayList<Sujets>ListSuj_Fichier = Controleur.getSujets();
		ArrayList<Sujets> ListSuj = new ArrayList<Sujets>();
		Hashtable<String, String> données = new Hashtable();
		données.put("id", "1");
		données.put("nom", "Val2");
		données.put("titre", "Val3");
		Sujets S = new Sujets(données);
		ListSuj.add(S);
		if(ListSuj.get(0).getDonnées().get("id").equals(ListSuj_Fichier.get(0).getDonnées().get("id") )&&
				(ListSuj.get(0).getDonnées().get("nom").equals(ListSuj_Fichier.get(0).getDonnées().get("nom")) &&
						(ListSuj.get(0).getDonnées().get("titre").equals(ListSuj_Fichier.get(0).getDonnées().get("titre")) ))){
			nbTestsOk++;
		}else{
			System.err
			.println("Erreur : getSujet n'accede pas a tous les sujets");
		}
	}
	public void test_getProjets(){
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/test_getProjets.csv")));

			fichier_test.println("groupe,sujet");
			fichier_test.println("Val0,1");
			fichier_test.close();
		} catch (IOException e) {
		}
		Controleur.initCsv();
		Controleur.entrés.put("Projets", "test_data/test_getProjets.csv");
		Controleur.importer();
		ArrayList<Projets>ListPro_Fichier = Controleur.getProjets();
		ArrayList<Projets> ListPro = new ArrayList<Projets>();
		Hashtable<String, String> données = new Hashtable();
		données.put("groupe", "Val0");
		données.put("sujet", "1");

		Projets P = new Projets(données);
		ListPro.add(P);
		if(ListPro.get(0).getDonnées().get("groupe").equals(ListPro_Fichier.get(0).getDonnées().get("groupe") )&&
				(ListPro.get(0).getDonnées().get("sujet").equals(ListPro_Fichier.get(0).getDonnées().get("sujet"))
			)){
			nbTestsOk++;
		}else{
			System.err
			.println("Erreur : getProjet n'accede pas a tous les projets");
		}
	}
	public void test_getIntervenants(){
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/test_getIntervenants.csv")));

			fichier_test.println("nom,prenom");
			fichier_test.println("Val0,Val1");
			fichier_test.close();
		} catch (IOException e) {
		}
		Controleur.initCsv();
		Controleur.entrés.put("Intervenants", "test_data/test_getIntervenants.csv");
		Controleur.importer();
		ArrayList<Intervenants>ListInt_Fichier = Controleur.getIntervenants();
		ArrayList<Intervenants> ListInt = new ArrayList<Intervenants>();
		Hashtable<String, String> données = new Hashtable();
		données.put("nom", "Val0");
		données.put("prenom", "Val1");

		Intervenants I = new Intervenants(données);
		ListInt.add(I);
		if(ListInt.get(0).getDonnées().get("nom").equals(ListInt_Fichier.get(0).getDonnées().get("nom") )&&
				(ListInt.get(0).getDonnées().get("prenom").equals(ListInt_Fichier.get(0).getDonnées().get("prenom"))
			)){
			nbTestsOk++;
		}else{
			System.err
			.println("Erreur : getProjet n'accede pas a tous les projets");
		}
	}
	
	
	
	public void test_attributSujet(){
		nbTestsTotal++;
		String []atttribuSujet_test = Controleur.attributSujet();
		String []atttribuSujet={"id","nom","titre"};
		int i=0;
		boolean ok= true;
		if (atttribuSujet.length==atttribuSujet_test.length){
			while(i<atttribuSujet.length && ok){
				if(! atttribuSujet[i].equals(atttribuSujet_test[i])){
					ok=false;
					
				}
				i++;	
			}
			if(!ok){
				System.err.println("la fonction attributSujet ne retourne pas les bons attributs");
			}else{
				nbTestsOk++;
			}
		}else{
			System.err.println("la fonction attributSujet ne retourne pas le bon nombre d'attribut");
		}
		
	}
	public void test_attributEtudiant(){
		nbTestsTotal++;
		String []attribuEtu_test = Controleur.attributEtudiant();
		String []attribuEtu={"groupe","nom","prenom"};
		int i=0;
		boolean ok= true;
		if (attribuEtu_test.length==attribuEtu.length){
			while(i<attribuEtu.length && ok){
				if(! attribuEtu_test[i].equals(attribuEtu[i])){
					ok=false;
					
				}
				i++;	
			}
			if(!ok){
				System.err.println("la fonction attributEtudiant ne retourne pas les bons attributs");
			}else{
				nbTestsOk++;
			}
		}else{
			System.err.println("la fonction attributEtudiant ne retourne pas le bon nombre d'attribut");
		}
		
	}
	public void test_attributProget(){
		nbTestsTotal++;
		String []attribuProjet_test = Controleur.attributProjet();
		String []attribuProjet={"id", "groupe", "sujet", "client", "superviseur", "support_technique"};
		int i=0;
		boolean ok= true;
		if (attribuProjet_test.length==attribuProjet.length){
			while(i<attribuProjet.length && ok){
				if(! attribuProjet[i].equals(attribuProjet_test[i])){
					ok=false;
					
				}
				i++;	
			}
			if(!ok){
				System.err.println("la fonction attributProjet ne retourne pas les bons attributs");
			}else{
				nbTestsOk++;
			}
		}else{
			System.err.println("la fonction attributProjet ne retourne pas le bon nombre d'attribut");
		}
		
	}
	public void test_attributIntervenant(){
		nbTestsTotal++;
		String []attribuIntervenant_test = Controleur.attributIntervenant();
		String []attribuIntervenant={"id", "nom", "prenom"};
		int i=0;
		boolean ok= true;
		if (attribuIntervenant_test.length==attribuIntervenant.length){
			while(i<attribuIntervenant_test.length && ok){
				if(! attribuIntervenant_test[i].equals(attribuIntervenant[i])){
					ok=false;
					
				}
				i++;	
			}
			if(!ok){
				System.err.println("la fonction attributIntervenant ne retourne pas les bons attributs");
			}else{
				nbTestsOk++;
			}
		}else{
			System.err.println("la fonction attributIntervenant ne retourne pas le bon nombre d'attribut");
		}
		
	}

	
	
	//TEST DES MODIFICATIONS
	public void test_modifSujet(){
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testModifSujet.csv")));

			fichier_test.println("id,nom,titre");
			fichier_test.println("Val0,Val1,Val2");
			fichier_test.close();
		} catch (IOException e) {
		}

		Controleur.initCsv();
		Controleur.entrés.put("Sujets", "test_data/testModifSujet.csv");
		Controleur.importer();

		Hashtable<String, String> données = new Hashtable();
		données.put("id", "Val3");
		données.put("nom", "Val4");
		données.put("titre", "Val5");
		
		Controleur.modifierSujet(Controleur.getSujets().get(0), données);
		
		String []tab_attribut=Controleur.attributSujet();
		
		String[] tabValModif= new String[données.size()];
		
		//creation automatique du tableau contenant les donnée modifié
		for(int i=0;i<tabValModif.length;i++){
			tabValModif[i]=données.get(tab_attribut[i]);
		}
		
		boolean ok=true;
		int i=0;
		while(i<tab_attribut.length && ok){
			if(! Controleur.getSujets().get(0).getDonnées().get(tab_attribut[i]).equals(tabValModif[i])){
				ok=false;
			}
			i++;
		}
		if(ok){
			nbTestsOk++;
		}else{
			System.err.println("la fonction modifierSujet ne modifie pas le sujet ");
		}
	}
	public void test_modifEtudiant(){
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testModifEtu.csv")));

			fichier_test.println("groupe,nom,prenom");
			fichier_test.println("Val0,Val1,Val2");
			fichier_test.close();
		} catch (IOException e) {
		}

		Controleur.initCsv();
		Controleur.entrés.put("Etudiants", "test_data/testModifEtu.csv");
		Controleur.importer();

		Hashtable<String, String> données = new Hashtable();
		données.put("groupe", "Val3");
		données.put("nom", "Val4");
		données.put("prenom", "Val5");
		
		Controleur.modifierEtudiant(Controleur.getEtudiants().get(0), données);
		
		String []tab_attribut=Controleur.attributEtudiant();
	

		String[] tabValModif= new String[données.size()];
		for(int i=0;i<tabValModif.length;i++){
			tabValModif[i]=données.get(tab_attribut[i]);
		}
		boolean ok=true;
		int i=0;
		while(i<tab_attribut.length && ok){
			if(! Controleur.getEtudiants().get(0).getDonnées().get(tab_attribut[i]).equals(tabValModif[i])){
				ok=false;
			}
			i++;
		}
		if(ok){
			nbTestsOk++;
		}else{
			System.err.println("la fonction modifierEtudiant ne modifie pas le sujet ");
		}
	}
	public void test_modifProget(){
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testModifproj.csv")));

			fichier_test.println("groupe,sujet");
			fichier_test.println("Val0,Val1");
			fichier_test.close();
		} catch (IOException e) {
		}

		Controleur.initCsv();
		Controleur.entrés.put("Projets", "test_data/testModifproj.csv");
		Controleur.importer();

		Hashtable<String, String> données = new Hashtable();
		données.put("groupe", "Val2");
		données.put("sujet", "Val3");
		
		
		Controleur.modifierProjet(Controleur.getProjets().get(0), données);
		
		String []tab_attribut={"groupe","sujet"};
		String[] tabValModif= new String[données.size()];
		for(int i=0;i<tabValModif.length;i++){
			tabValModif[i]=données.get(tab_attribut[i]);
		}
		
		boolean ok=true;
		int i=0;
		while(i<tab_attribut.length && ok){
			if(! Controleur.getProjets().get(0).getDonnées().get(tab_attribut[i]).equals(tabValModif[i])){
				ok=false;
			}
			i++;
		}
		if(ok){
			nbTestsOk++;
		}else{
			System.err.println("la fonction modifierProjet ne modifie pas le sujet ");
		}
	}
	
	public void test_modifIntervenant(){
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testModifInt.csv")));

			fichier_test.println("id,nom,prenom");
			fichier_test.println("1,BOULLe,remi");
			fichier_test.close();
		} catch (IOException e) {
		}

		Controleur.initCsv();
		Controleur.entrés.put("Intervenants", "test_data/testModifInt.csv");
		Controleur.importer();

		Hashtable<String, String> données = new Hashtable();
		données.put("id", "2");
		données.put("nom", "CHANCOGNE");
		données.put("prenom", "laurent");
		
		Controleur.modifierIntervenant(Controleur.getIntervenants().get(0), données);
		
		String []tab_attribut=Controleur.attributIntervenant();
		
		String[] tabValModif= new String[données.size()];
		for(int i=0;i<tab_attribut.length;i++){
			tabValModif[i]=données.get(tab_attribut[i]);
		}
		
		boolean ok=true;
		int i=0;
		while(i<tab_attribut.length && ok){
			if(! Controleur.getIntervenants().get(0).getDonnées().get(tab_attribut[i]).equals(tabValModif[i])){
				ok=false;
			}
			i++;
		}
		if(ok){
			nbTestsOk++;
		}else{
			System.err.println("la fonction modifierIntervenant ne modifie pas le sujet ");
		}
	}


	// TEST RECHERCHE :
	public void test_findSujet(){
		nbTestsTotal++;
		int i=0;
		boolean ok=true;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testFindSujet.csv")));

			fichier_test.println("id,nom,titre");
			fichier_test.println("6,Val1,Val2");
			fichier_test.close();
		} catch (IOException e) {
		}

		Controleur.initCsv();
		Controleur.entrés.put("Sujets", "test_data/testFindSujet.csv");
		Controleur.importer();
		
		Hashtable<String, String> données = new Hashtable();
		données.put("id", "6");
		données.put("nom", "Val1");
		données.put("titre", "Val2");
		Sujets S = new Sujets(données);
		String []attribut = Controleur.attributSujet();
		Sujets S_test = Controleur.findSujet("6");
		if (S_test==null){
			System.err.println("Aucun sujet pour cette identifiant");
		}else{
			while(i<S.getDonnées().size() && ok){
				if(! S.getDonnées().get(attribut[i]).equals(S_test.getDonnées().get(attribut[i]))){
					ok=false;
				}
				i++;
			}
			if(ok){
				nbTestsOk++;
			}else{
				System.err.println("la fonction findSujet trouve un sujet différent");
			}
		}
	}	
	public void test_rech_etu_group() {
		nbTestsTotal++;
		String groupeRechercher = "A";
		boolean ok;
		Controleur.initCsv();
		Controleur.entrés.put("Etudiants", "data/etudiants.csv");
		Controleur.importer();	
		ArrayList<Etudiants> resultat = new ArrayList<Etudiants>();	
		for(int i=0;i<Controleur.getEtudiants().size();i++){
			//si on trouve un etudiant qui corespond au groupe rechercher on le stocke dans la list resultat
			if(Controleur.getEtudiants().get(i).getDonnées().get("groupe").equals(groupeRechercher)){
				resultat.add(Controleur.getEtudiants().get(i));
			}
		}	
		ArrayList<Etudiants> test = Controleur.getEtudiantsGroupe(groupeRechercher);	
		ok = (resultat.size() == test.size());
		//si la taille est corect
		if(ok){
			int i=0;
			boolean ok2=true;
			//on va verifier chaque nom des etudiants contenue dans les listes
			while(i<resultat.size()){
				if(!(resultat.get(i).getDonnées().get("nom").equals(test.get(i).getDonnées().get("nom")))){
					ok=false;
				}
				i++;		
			}
			if(ok2){
				nbTestsOk++;
			}else{
				System.err.println("la fonction getEtudiantsGroupe ne retourne pas les étudiant assigné au groupe : "+groupeRechercher);
			}
		}else{
			System.err.println("la fonction getEtudiantsGroupe ne retourne pas le bon nombre d'étudiants");
		}
	}

	
	

	public void test_colnerProjet(){
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testClonerProjet.csv")));

			fichier_test.println("groupe,sujet");
			fichier_test.println("A,2");
			fichier_test.close();
		} catch (IOException e) {
		}
		Controleur.initCsv();
		Controleur.entrés.put("Projets", "test_data/testClonerProjet.csv");
		Controleur.importer();
		
		Projets P=Controleur.getProjets().get(0);
		Projets PTest = Controleur.clonerProjet(P);
		
		String [] atribut=Controleur.attributProjet();

		int i=0;
		boolean ok=true;
		if(P.getDonnées().get("groupe").equals(PTest.getDonnées().get("groupe")) && 
				P.getDonnées().get("sujet").equals(PTest.getDonnées().get("sujet")) ){
			nbTestsOk++;
		}else{
			System.err.println("erreur , la fonction clonerProjet ne clone pas le projet");
		}

	}

	public void test_getEtuGroupe(){
		nbTestsTotal++;
	
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testgetEtuGroupe.csv")));
			fichier_test.println("groupe,nom,prenom");
			fichier_test.println("A,lui,lui");
			fichier_test.println("A,toi,toi");
		
			fichier_test.println("B,elle,elle");
			fichier_test.println("C,il,il");
			fichier_test.close();
		} catch (IOException e) {
		}
		Controleur.initCsv();
		Controleur.entrés.put("Etudiants", "test_data/testgetEtuGroupe.csv");
		Controleur.importer();
		
		Hashtable<String, String> données = new Hashtable();
		données.put("groupe", "A");
		données.put("nom", "lui");
		données.put("prenom", "lui");
		
		Hashtable<String, String> données2 = new Hashtable();
		données2.put("groupe", "A");
		données2.put("nom", "toi");
		données2.put("prenom", "toi");
		
		ArrayList<Etudiants> List=new ArrayList<>();
		List.add(new Etudiants(données));
		List.add(new Etudiants(données2));
		
		String groupeRech="A";
		
		ArrayList<Etudiants> ListTest=Controleur.getEtudiantsGroupe(groupeRech);
		int j=0;
		String [] attribut=Controleur.attributEtudiant();
		boolean ok=true;

		if(List.size()==ListTest.size()){
			for(int i=0;i<List.size();i++){
				while(j<attribut.length){
					if(! List.get(i).getDonnées().get(attribut[j]).equals(ListTest.get(i).getDonnées().get(attribut[j]))){
						ok=false;
					}
					
					j++;
				}
			}
			if(ok){
				nbTestsOk++;
			}else{
				System.err.println("la fonction getEtudiantsGroupe ne retourne les etudiant corespondant au groupe rechercher");
			}
		}else{
			System.err.println("la fontion getEtudiantsGroupe ne retourne pas le bon nombre d'etudiant");
		}	
	}

	public void test_getIntervenantGroupe(){
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testgetInteGrProjet.csv")));
			fichier_test.println("id,groupe,sujet,client,superviseur,support_technique");
			fichier_test.println("1,A,0,1,5,8");
	
			fichier_test.close();
			
			PrintWriter fichier_test2 = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testgetInteGrInt.csv")));
			fichier_test2.println("id,nom,prenom");
			fichier_test2.println("1,BOULLE,Remi");
			
			fichier_test2.close();	
		} catch (IOException e) {
		}
		Controleur.initCsv();
		Controleur.entrés.put("Projets", "test_data/testgetInteGrProjet.csv");
		Controleur.entrés.put("Intervenants", "test_data/testgetInteGrInt.csv");
		Controleur.importer();
	
		String []tab_resultat={"1","5","8"};
		String []tab_attribut={"id","nom","prenom"};
		
		String[] List= Controleur.getIntervenantsGroupe("A");
		
		boolean ok = true;
		int i=0;
		
		while(i<tab_attribut.length && ok ){

			if(!List[i].equals(tab_resultat[i])){
				ok=false;
			}
			i++;
		}
		if(ok){
			nbTestsOk++;
		}else{
			System.err.println("la fonction getIntervenantsGroupe ne trouve pas le bon Intervenant assigner au groupe");
		}
	
		
		
		
	}
	
	public void test_getGroupe(){ 
		nbTestsTotal++;
		
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testgetGroupeProjet.csv")));
			fichier_test.println("groupe,sujet");
			fichier_test.println("A,0");
			fichier_test.println("B,1");
			fichier_test.println("C,2");
			fichier_test.close();
			
			PrintWriter fichier_test2 = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testgetGroupeSujet.csv")));
			fichier_test2.println("id,nom,titre");
			fichier_test2.println("0,sujet1,titre");
			fichier_test2.println("1,sujet2,titre");
			fichier_test2.println("2,sujet3,titre");
			fichier_test2.close();
			
		} catch (IOException e) {
		}
		
		Controleur.initCsv();
		Controleur.entrés.put("Projets", "test_data/testgetGroupeProjet.csv");
		Controleur.entrés.put("Sujets", "test_data/testgetGroupeSujet.csv");
		Controleur.importer();

		String [] tab_corect={"A","B","C","sujet1","sujet2","sujet3"};
		String [][] tab_test=Controleur.getGroupes();
		int i = 0,j = 0,k=0;
		boolean ok=true;
		while(i<Controleur.attributProjet().length && ok){
			while(j<Controleur.attributSujet().length && ok){
				if(! tab_test[i][j].equals(tab_corect[k])){
					ok=false;
				}
				j++;
				k++;
			}
			i++;
		}
		if(ok){
			nbTestsOk++;
		}else{
			System.err.println("erreur, la fonction getGroupes ne retourne pas les bons sujets assignés au groupe ");
		}
		
		
	}
	
	public void test_compterVoeuxParPosition(){
nbTestsTotal++;
		
		try {
			PrintWriter fichier_testSujet = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testCptV_Sujets.csv")));
			fichier_testSujet.println("id,nom");
			fichier_testSujet.println("1,AmexEComm");
			fichier_testSujet.println("2,Archeologie");
			fichier_testSujet.println("3,Architekt");
			fichier_testSujet.close();
			
			PrintWriter fichier_testVoeuxt = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testCptV_Voeux.csv")));
			fichier_testVoeuxt.println("groupe,sujet,position");
			fichier_testVoeuxt.println("A,1,1");
			fichier_testVoeuxt.println("B,2,2");
			
			fichier_testVoeuxt.println("D,1,1");
			fichier_testVoeuxt.println("E,2,3");
			
			
			fichier_testVoeuxt.close();
	
		}catch (IOException e) {
		}
		Controleur.initCsv();
		Controleur.entrés.put("Sujets", "test_data/testCptV_Sujets.csv");
		Controleur.entrés.put("Voeux", "test_data/testCptV_Voeux.csv");
		Controleur.importer();
		
	
		String[][] résultat_test_id1= Controleur.compterVoeuxParPosition("1");
		String[][] résultat_test_id2= Controleur.compterVoeuxParPosition("2");
		String[][] résultat_test_id3= Controleur.compterVoeuxParPosition("3");
		String[][] résultat_corect_id1={{"1","2"},{"2","0"},{"3","0"}};
		String[][] résultat_corect_id2={{"1","0"},{"2","1"},{"3","1"}};
		String[][] résultat_corect_id3={{"1","0"},{"2","0"},{"3","0"}};
		
		int i=0;
		int j=0;
		boolean ok=true;
		while(i<Controleur.getSujets().size() && ok){
			while(j<2 && ok){
				if(! résultat_test_id1[i][j].equals(résultat_corect_id1[i][j])){
					ok=false;
				}
				j++;
			}
			i++;
		}
		while(i<Controleur.getSujets().size() && ok){
			while(j<2 && ok){
				if(! résultat_test_id2[i][j].equals(résultat_corect_id2[i][j])){
					ok=false;
				}
				j++;
			}
			i++;
		}
		while(i<Controleur.getSujets().size() && ok){
			while(j<2 && ok){
				if(! résultat_test_id3[i][j].equals(résultat_corect_id3[i][j])){
					ok=false;
				}
				j++;
			}
			i++;
		}
		if(ok){
			nbTestsOk++;
		}else{
			System.err.println("la fonction compterVoeuxParPosition ne retourne pas le bon nombre de voeux par position ");
		}
		
		
	}
	//TEST des VOEUX :
	public void test_getAttributVoeux(){
		nbTestsTotal++;
		String [] attribut={"groupe","sujet","position"};
		
		String [] attribut_Test= Controleur.attributVoeux();
		int i=0;
		boolean ok =true;
		if(attribut.length==attribut_Test.length){
			while(i<attribut.length && ok){
				if(! attribut[i].equals(attribut_Test[i])){
					ok=false;
				}
				i++;
			}
			if(ok){
				nbTestsOk++;
			}else{
				System.err.println("la fonction attributVoeux ne retourne pas les bons d'attributs");
			}
		}else{
			System.err.println("la fonction attributVoeux ne retourne pas le bon nombre d'attributs");
		}
		
	}
	public void test_getVoeux(){
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testGetVoeux.csv")));

			fichier_test.println("groupe,sujet,position");
			fichier_test.println("val0,val1,val2");
			fichier_test.close();
		} catch (IOException e) {
		}
		Controleur.initCsv();
		Controleur.entrés.put("Voeux", "test_data/testGetVoeux.csv");
		Controleur.importer();
		
		ArrayList<Voeux>ListVoeux_Test = Controleur.getVoeux();
		ArrayList<Voeux> ListVoeux = new ArrayList<Voeux>();
		
		Hashtable<String, String> données = new Hashtable();
		données.put("groupe", "val0");
		données.put("sujet", "val1");
		données.put("position", "val2");
		Voeux V = new Voeux(données);
		ListVoeux.add(V);
		
		int i=0;
		boolean ok=true;
		String []attribut= Controleur.attributVoeux();
		while(i<attribut.length){
			if(! ListVoeux.get(0).getDonnées().get(attribut[i]).equals(ListVoeux_Test.get(0).getDonnées().get(attribut[i]))){
				ok=false;
			}
			i++;
		}
		if(ok){
			nbTestsOk++;
		}else{
			System.err.println("erreur, la fonction getVoeux retourne des voeux incorects");
		}
		
	}
	public void test_supVoeux(){
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testSuppVoeux.csv")));

			fichier_test.println("groupe,sujet,position");
			fichier_test.println("val0,val1,val2");
			fichier_test.close();
		} catch (IOException e) {
		}
		Controleur.initCsv();
		Controleur.entrés.put("Voeux", "test_data/testSuppVoeux.csv");
		Controleur.importer();
		
		ArrayList<Voeux>ListVoeux_Test = Controleur.getVoeux();
		Controleur.supprimerVoeux(ListVoeux_Test.get(0));
		if( ListVoeux_Test.size()==0){
			nbTestsOk++;
		}else{
			System.err.println("la focntion supprimerVoeux ne supprime pas le voeux");
		}
		
		
	}
	public void test_ajoutVoeux(){
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testAjjVoeux.csv")));

			fichier_test.println("groupe,sujet,position");
			fichier_test.println("val0,val1,val2");
			fichier_test.close();
		} catch (IOException e) {
		}
		Controleur.initCsv();
		Controleur.entrés.put("Voeux", "test_data/testAjjVoeux.csv");
		Controleur.importer();
		
		
		Hashtable<String, String> données = new Hashtable();
		données.put("groupe", "Val0");
		données.put("sujet", "Val1");
		données.put("position", "Val2");
		ArrayList<Voeux>ListVoeux = Controleur.getVoeux();
		Voeux V = new Voeux(données);
		Controleur.ajouterVoeux(données);
		if(Controleur.getVoeux().size()==2){
			nbTestsOk++;
		}else{
			System.err.println("Erreur : ajouter n'ajoute pas le Voeux");
		}	
	}
	public void test_modifVoeux(){
		nbTestsTotal++;
		try {
			PrintWriter fichier_test = new PrintWriter(new BufferedWriter(
					new FileWriter("test_data/testModifVoeux.csv")));

			fichier_test.println("groupe,sujet,position");
			fichier_test.println("val0,val1,val2");
			fichier_test.close();
		} catch (IOException e) {
		}

		Controleur.initCsv();
		Controleur.entrés.put("Voeux", "test_data/testModifVoeux.csv");
		Controleur.importer();

		Hashtable<String, String> données = new Hashtable();
		données.put("groupe", "Val0");
		données.put("sujet", "Val1");
		données.put("position", "Val2");
		
		Controleur.modifierVoeux(Controleur.getVoeux().get(0), données);
		
		String []tab_attribut=Controleur.attributVoeux();
		
		String[] tabValModif= new String[données.size()];
		
		//creation automatique du tableau contenant les donnée modifié
		for(int i=0;i<tabValModif.length;i++){
			tabValModif[i]=données.get(tab_attribut[i]);
		}
		
		boolean ok=true;
		int i=0;
		while(i<tab_attribut.length && ok){
			if(! Controleur.getVoeux().get(0).getDonnées().get(tab_attribut[i]).equals(tabValModif[i])){
				ok=false;
			}
			i++;
		}
		if(ok){
			nbTestsOk++;
		}else{
			System.err.println("la fonction modifierVoeux ne modifie pas le sujet ");
		}
	}
	
	
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(new TestSuite(TestsControleur.class));
		System.out.println(nbTestsOk + "/" + nbTestsTotal + " tests validés.");
	}

}
