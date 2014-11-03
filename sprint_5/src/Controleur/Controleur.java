package Controleur;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import Lib.Optilib;
import Modele.Etudiants;
import Modele.Intervenants;
import Modele.Projets;
import Modele.Sujets;
import Modele.Voeux;

public class Controleur {
	
	///////////////////////////////////////
	///////// * DONNEES * /////////////////
	///////////////////////////////////////
	
	public static Hashtable<String, String> entr�s = new Hashtable<String, String>();
	public static Hashtable<String, String> sorties = new Hashtable<String, String>();
	
	/* Classes */
	private static ArrayList<Etudiants> listeEtudiants = new ArrayList<Etudiants>();
	private static ArrayList<Intervenants> listeIntervenants = new ArrayList<Intervenants>();
	private static ArrayList<Projets> listeProjets = new ArrayList<Projets>();
	private static ArrayList<Sujets> listeSujets = new ArrayList<Sujets>();
	private static ArrayList<Voeux> listeVoeux = new ArrayList<Voeux>();
	
	///////////////////////////////////////
	///////// * OPTILIB * /////////////////
	///////////////////////////////////////
	
	/* Initialise fichiers */
	public static void initCsv() {
		entr�s.put("Etudiants", "data/etudiants2014_2015.csv");
		entr�s.put("Intervenants", "data/intervenants2014_2015.csv");
		entr�s.put("Projets", "data/projets2014_2015.csv");
		entr�s.put("Sujets", "data/sujets2014_2015.csv");
		entr�s.put("Voeux", "data/voeux2014_2015.csv");
		
		sorties.put("Etudiants", "");
		sorties.put("Intervenants", "");
		sorties.put("Projets", "");
		sorties.put("Sujets", "");
		sorties.put("Voeux", "");
	}
	
	/* Importation des donn�es */
	public static boolean importer() {
		try {
			listeEtudiants.clear();
			listeIntervenants.clear();
			listeProjets.clear();
			listeSujets.clear();
			listeVoeux.clear();
			
			/* Etudiants */
			Hashtable<String, String>[] etudiants = Optilib.reader(entr�s.get("Etudiants"));
			
			for(int i=0;i<etudiants.length;i++) {
				listeEtudiants.add(new Etudiants(etudiants[i]));

			}
			
			/* Intervenants */
			Hashtable<String, String>[] intervenants = Optilib.reader(entr�s.get("Intervenants"));
			for(int i=0;i<intervenants.length;i++) {
				listeIntervenants.add(new Intervenants(intervenants[i]));
			}
			
			/* Projets */
			Hashtable<String, String>[] projets = Optilib.reader(entr�s.get("Projets"));
			for(int i=0;i<projets.length;i++) {
				listeProjets.add(new Projets(projets[i]));
			}
			
			/* Sujets */
			Hashtable<String, String>[] sujets = Optilib.reader(entr�s.get("Sujets"));
			for(int i=0;i<sujets.length;i++) {
				listeSujets.add(new Sujets(sujets[i]));
			}
			
			/* Voeux */
			Hashtable<String, String>[] voeux = Optilib.reader(entr�s.get("Voeux"));
			for(int i=0;i<voeux.length;i++) {
				listeVoeux.add(new Voeux(voeux[i]));
			}
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/* Exportation des donn�es */
	public static boolean exporter(char classe) {
		switch(classe) {
		case 'a' :
			return exporterEtudiants() &&
			exporterIntervenants() &&
			exporterProjets() &&
			exporterSujets() &&
			exporterVoeux();
		case 'e' :
			return exporterEtudiants();
		case 'i' :
			return exporterIntervenants();
		case 'p' :
			return exporterProjets();
		case 's' :
			return exporterSujets();
		case 'v' :
			return exporterVoeux();
		default :
			return false;
		}
	}
	
	private static boolean exporterEtudiants() {
		try {
			/* Etudiants */
			Hashtable<String, String>[] hashEtudiants = new Hashtable[listeEtudiants.size()];
			String[] attributs = Etudiants.getAttributs();
			for(int i=0;i<listeEtudiants.size();i++) {
				hashEtudiants[i] = new Hashtable<String, String>();
				for(int j=0;j<attributs.length;j++) {
					hashEtudiants[i].put(attributs[j], listeEtudiants.get(i).getDonn�es().get(attributs[j]));
				}
			}
			Optilib.saver(hashEtudiants, sorties.get("Etudiants"));
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean exporterIntervenants() {
		try {
			/* Intervenants */
			Hashtable<String, String>[] hashIntervenants = new Hashtable[listeIntervenants.size()];
			String[] attributs = Intervenants.getAttributs();
			for(int i=0;i<listeIntervenants.size();i++) {
				hashIntervenants[i] = new Hashtable<String, String>();
				for(int j=0;j<attributs.length;j++) {
					hashIntervenants[i].put(attributs[j], listeIntervenants.get(i).getDonn�es().get(attributs[j]));
				}
			}
			Optilib.saver(hashIntervenants, sorties.get("Intervenants"));
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean exporterProjets() {
		try {
			/* Projets */
			Hashtable<String, String>[] hashProjets = new Hashtable[listeProjets.size()];
			String[] attributs = Projets.getAttributs();
			for(int i=0;i<listeProjets.size();i++) {
				hashProjets[i] = new Hashtable<String, String>();
				for(int j=0;j<attributs.length;j++) {
					hashProjets[i].put(attributs[j], listeProjets.get(i).getDonn�es().get(attributs[j]));
				}
			}
			Optilib.saver(hashProjets, sorties.get("Projets"));
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static boolean exporterSujets() {
		try {
			/* Sujets */
			Hashtable<String, String>[] hashSujets = new Hashtable[listeSujets.size()];
			String[] attributs = Sujets.getAttributs();
			for(int i=0;i<listeSujets.size();i++) {
				hashSujets[i] = new Hashtable<String, String>();
				for(int j=0;j<attributs.length;j++) {
					hashSujets[i].put(attributs[j], listeSujets.get(i).getDonn�es().get(attributs[j]));
				}
			}
			Optilib.saver(hashSujets, sorties.get("Sujets"));
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static boolean exporterVoeux() {
		try {
			Hashtable<String, String>[] hashVoeux = new Hashtable[listeVoeux.size()];
			String[] attributs = Voeux.getAttributs();
			for(int i=0;i<listeVoeux.size();i++) {
				hashVoeux[i] = new Hashtable<String, String>();
				for(int j=0;j<attributs.length;j++) {
					hashVoeux[i].put(attributs[j], listeVoeux.get(i).getDonn�es().get(attributs[j]));
				}
			}
			Optilib.saver(hashVoeux, sorties.get("Voeux"));
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	///////////////////////////////////////
	///////// * ETUDIANTS * ///////////////
	///////////////////////////////////////
	
	/* Trouve tous les �tudiants */
	public static ArrayList<Etudiants> getEtudiants() {
		return listeEtudiants;
	}

	/* Supprimer �tudiant */
	public static boolean supprimerEtudiant(Etudiants e) {
		return listeEtudiants.remove(e);
	}
	
	/* Ajouter �tudiant */
	public static void ajouterEtudiant(Hashtable<String, String> donn�es) {
		listeEtudiants.add(new Etudiants(donn�es));
	}
	
	/* Modifier �tudiant */
	public static void modifierEtudiant(Etudiants e, Hashtable<String, String> donn�es) {
		e.setDonn�es(donn�es);
	}
	
	/* Attributs �tudiant */
	public static String[] attributEtudiant() {
		return Etudiants.getAttributs();
	}
	
	/* Trouve un �tudiant � partir d'un mot clef et d'un attribut */
	public static ArrayList<Etudiants> searchEtudiant(String attribut, String valeur) {
		ArrayList<Etudiants> r�sultat = new ArrayList<Etudiants>();
		
		for(int i=0;i<listeEtudiants.size();i++) {
			if(listeEtudiants.get(i).getDonn�es().get(attribut).contains(valeur)) {
				r�sultat.add(listeEtudiants.get(i));
			}
		}
		return r�sultat;
	}
	
	///////////////////////////////////////
	///////// * INTERVENANTS * ////////////
	///////////////////////////////////////

	/* Trouve tous les intervenants */
	public static ArrayList<Intervenants> getIntervenants() {
		return listeIntervenants;
	}
	
	/* Supprimer intervenant */
	public static boolean supprimerIntervenant(Intervenants i) {
		return listeIntervenants.remove(i);
	}
	
	/* Ajouter intervenant */
	public static void ajouterIntervenant(Hashtable<String, String> donn�es) {
		listeIntervenants.add(new Intervenants(donn�es));
	}
	
	/* Attributs intervenant */
	public static String[] attributIntervenant() {
		return Intervenants.getAttributs();
	}
	
	/* Modifier intervenant */
	public static void modifierIntervenant(Intervenants i, Hashtable<String, String> donn�es) {
		i.setDonn�es(donn�es);
	}

	/* Trouve un intervenant � partir d'un mot clef et d'un attribut */
	public static ArrayList<Intervenants> searchIntervenant(String attribut, String valeur) {
		ArrayList<Intervenants> r�sultat = new ArrayList<Intervenants>();
		
		for(int i=0;i<listeIntervenants.size();i++) {
			if(listeIntervenants.get(i).getDonn�es().get(attribut).contains(valeur)) {
				r�sultat.add(listeIntervenants.get(i));
			}
		}
		return r�sultat;
	}

	/* Trouve un intervenant � partir de son id */
	public static Intervenants findIntervenant(String id) {
		for(int i=0;i<listeIntervenants.size();i++) {
			if(listeIntervenants.get(i).getDonn�es().get("id").equals(id)) {
				return listeIntervenants.get(i);
			}
		}
		return null;
	}
	
	/* Retourne l'id le groupe le sujet et le role d'un intervenant */
	public static Object[][] intervenantPlus(Intervenants i) {
		ArrayList<String> listeDesProjets = new ArrayList<String>();
		ArrayList<String> listeDesRoles = new ArrayList<String>();
		
		ArrayList<Projets> projetsClient = new ArrayList<Projets>();
		ArrayList<Projets> projetsSuper = new ArrayList<Projets>();
		ArrayList<Projets> projetsSupTech = new ArrayList<Projets>();
		
		projetsClient.addAll(Controleur.searchProjet("client", i.getDonn�es().get("id")));
		projetsSuper.addAll(Controleur.searchProjet("superviseur", i.getDonn�es().get("id")));
		projetsSupTech.addAll(Controleur.searchProjet("support_technique", i.getDonn�es().get("id")));
		
		String[] entetes = {"id", "groupe", "sujet", "role"};
		
		Object[][] projData;
		
		projData = new Object[projetsClient.size() + projetsSuper.size() + projetsSupTech.size()][entetes.length];
		
		listeDesProjets.clear();
		listeDesRoles.clear();
		
		for(int j=0;j<projetsClient.size();j++) {
			Sujets s = Controleur.findSujet(projetsClient.get(j).getDonn�es().get("sujet"));
				
			projData[j][0] = projetsClient.get(j).getDonn�es().get("id");
			projData[j][1] = projetsClient.get(j).getDonn�es().get("groupe");
			projData[j][2] = s.getDonn�es().get("nom");
			projData[j][3] = "Client";
		}
		
		for(int j=0;j<projetsSuper.size();j++) {
			Sujets s = Controleur.findSujet(projetsSuper.get(j).getDonn�es().get("sujet"));
				
			projData[projetsClient.size() + j][0] = projetsSuper.get(j).getDonn�es().get("id");
			projData[projetsClient.size() + j][1] = projetsSuper.get(j).getDonn�es().get("groupe");
			projData[projetsClient.size() + j][2] = s.getDonn�es().get("nom");
			projData[projetsClient.size() + j][3] = "Superviseur";
		}
		
		for(int j=0;j<projetsSupTech.size();j++) {
			Sujets s = Controleur.findSujet(projetsSupTech.get(j).getDonn�es().get("sujet"));
				
			projData[projetsClient.size() + projetsSuper.size() + j][0] = projetsSupTech.get(j).getDonn�es().get("id");
			projData[projetsClient.size() + projetsSuper.size() + j][1] = projetsSupTech.get(j).getDonn�es().get("groupe");
			projData[projetsClient.size() + projetsSuper.size() + j][2] = s.getDonn�es().get("nom");
			projData[projetsClient.size() + projetsSuper.size() + j][3] = "Support Technique";
		}
		
		return projData;
	}
	
	///////////////////////////////////////
	///////// * SUJETS * //////////////////
	///////////////////////////////////////

	/* Trouve tous les sujets */
	public static ArrayList<Sujets> getSujets() {
		return listeSujets;
	}
	
	/* Supprimer sujet */
	public static boolean supprimerSujet(Sujets s) {
		return listeSujets.remove(s);
	}
	
	/* Ajouter sujet */
	public static void ajouterSujet(Hashtable<String, String> donn�es) {
		listeSujets.add(new Sujets(donn�es));
	}
	
	/* Attributs sujet */
	public static String[] attributSujet() {
		return Sujets.getAttributs();
	}
	
	/* Modifier sujet */
	public static void modifierSujet(Sujets s, Hashtable<String, String> donn�es) {
		s.setDonn�es(donn�es);
	}
	
	/* Trouve un sujet � partir de son id */
	public static Sujets findSujet(String id) {
		for(int i=0;i<listeSujets.size();i++) {
			if(listeSujets.get(i).getDonn�es().get("id").equals(id)) {
				return listeSujets.get(i);
			}
		}
		return null;
	}
	
	/* Trouve un sujet � partir d'un mot clef et d'un attribut */
	public static ArrayList<Sujets> searchSujet(String attribut, String valeur) {
		ArrayList<Sujets> r�sultat = new ArrayList<Sujets>();
		
		for(int i=0;i<listeSujets.size();i++) {
			if(listeSujets.get(i).getDonn�es().get(attribut).contains(valeur)) {
				r�sultat.add(listeSujets.get(i));
			}
		}
		return r�sultat;
	}
	
	/* Compte le nombre de voeux par position pour un sujet */
	public static String[][] compterVoeuxParPosition(String idSujet) {
		String[][] r�sultat = new String[listeSujets.size()][2];
		int[] compteur = new int[listeSujets.size()];
		
		for(int i=0;i<listeVoeux.size();i++) {
			if(listeVoeux.get(i).getDonn�es().get("sujet").equals(idSujet)) {
				compteur[Integer.parseInt(listeVoeux.get(i).getDonn�es().get("position"))-1]++;
			}
		}
		
		for(int i=0;i<listeSujets.size();i++) {
			r�sultat[i][0] = Integer.toString(i+1);
			r�sultat[i][1] = Integer.toString(compteur[i]);
		}
		
		return r�sultat;
	}
	
	///////////////////////////////////////
	///////// * PROJETS * /////////////////
	///////////////////////////////////////

	/* Trouve tous les projets */
	public static ArrayList<Projets> getProjets() {
		return listeProjets;
	}
	
	/* Trouve un projet � partir de son id */
	public static Projets findProjet(String id) {
		for(int i=0;i<listeProjets.size();i++) {
			if(listeProjets.get(i).getDonn�es().get("id").equals(id)) {
				return listeProjets.get(i);
			}
		}
		return null;
	}
	
	/* Supprimer projet */
	public static boolean supprimerProjet(Projets p) {
		return listeProjets.remove(p);
	}
	
	/* Ajouter projet */
	public static void ajouterProjet(Hashtable<String, String> donn�es) {
		listeProjets.add(new Projets(donn�es));
	}

	/* Cloner un projet */
	public static Projets clonerProjet(Projets p) {
		Hashtable<String, String> clone = new Hashtable<String, String>();
		
		clone.put("groupe", p.getDonn�es().get("groupe"));
		clone.put("sujet", p.getDonn�es().get("sujet"));
		
		Projets projetClon� = new Projets(clone);
		
		listeProjets.add(projetClon�);
		return projetClon�;
	}
	
	/* Attributs projet */
	public static String[] attributProjet() {
		return Projets.getAttributs();
	}
	
	/* Modifier projet */
	public static void modifierProjet(Projets p, Hashtable<String, String> donn�es) {
		p.setDonn�es(donn�es);
	}
	
	/* Trouve un projet � partir d'un mot clef et d'un attribut */
	public static ArrayList<Projets> searchProjet(String attribut, String valeur) {
		ArrayList<Projets> r�sultat = new ArrayList<Projets>();
		
		if(attribut.equals("client") || attribut.equals("superviseur") || attribut.equals("support_technique")) {
			for(int i=0;i<listeProjets.size();i++) {
				if(listeProjets.get(i).getDonn�es().get(attribut).equals(valeur)) {
					r�sultat.add(listeProjets.get(i));
				}
			}
		} else {
			for(int i=0;i<listeProjets.size();i++) {
				if(listeProjets.get(i).getDonn�es().get(attribut).contains(valeur)) {
					r�sultat.add(listeProjets.get(i));
				}
			}
		}
		return r�sultat;
	}
	
	///////////////////////////////////////
	///////// * GROUPES * /////////////////
	///////////////////////////////////////
	
	/* Renvoie la liste des groupes avec leur sujet */
	public static String[][] getGroupes() {
		ArrayList<String> groupesTemp = new ArrayList<String>();
		String d�j�Pr�sent = "";
		
		for(int i=0;i<listeEtudiants.size();i++) {
			String groupe = listeEtudiants.get(i).getDonn�es().get("groupe");
			
			if(!d�j�Pr�sent.contains("#" + groupe + "#")) {
				d�j�Pr�sent += "#" + groupe + "#";
				groupesTemp.add(groupe);
			}
		}
		
		String[][] groupes = new String[2][groupesTemp.size()];
		
		for(int i=0;i<groupes[0].length;i++) {
			groupes[0][i] = groupesTemp.get(i);
			try {
				groupes[1][i] = findSujet(listeProjets.get(i).getDonn�es().get("sujet")).getDonn�es().get("nom");
			} catch(Exception e) {
				groupes[1][i] = "Aucun sujet";
			}
		}
		
		return groupes;
	}
	
	/* Trouve les �tudiants d'un groupe */
	public static ArrayList<Etudiants> getEtudiantsGroupe(String g) {
		ArrayList<Etudiants> r�sultat = new ArrayList<Etudiants>();
		
		for(int i=0;i<listeEtudiants.size();i++) {
			if(listeEtudiants.get(i).getDonn�es().get("groupe").equals(g)) {
				r�sultat.add(listeEtudiants.get(i));
			}
		}
		return r�sultat;
	}
	
	/* Trouve les intervenants d'un projet d'un groupe */
	public static String[] getIntervenantsGroupe(String g) {
		String[] r�sultat = new String[3];
		
		int compteur = 0;
		boolean found = false;
		
		Projets leProjet = null;
		
		while(compteur<listeProjets.size() && !found) {
			if(listeProjets.get(compteur).getDonn�es().get("groupe").equals(g)) {
				found = true;
				leProjet = listeProjets.get(compteur);
			}
			
			compteur++;
		}
		
		r�sultat[0] = leProjet.getDonn�es().get("client");
		r�sultat[1] = leProjet.getDonn�es().get("superviseur");
		r�sultat[2] = leProjet.getDonn�es().get("support_technique");
		
		return r�sultat;
	}

	/* Position du sujet affect� au groupe si existe */
	public static String positionSujetGroupe(String g) {
		String sujet = "";
		
		for(int i=0;i<listeProjets.size();i++) {
			if(listeProjets.get(i).getDonn�es().get("groupe").equals(g)) {
				sujet = listeProjets.get(i).getDonn�es().get("sujet");
				break;
			}
		}
		
		for(int i=0;i<listeVoeux.size();i++) {
			if(listeVoeux.get(i).getDonn�es().get("sujet").equals(sujet)) {
				return listeVoeux.get(i).getDonn�es().get("position");
			}
		}
		
		return "";
	}
	
	///////////////////////////////////////
	///////// * VOEUX * ///////////////////
	///////////////////////////////////////
	
	/* Trouve tous les voeux */
	public static ArrayList<Voeux> getVoeux() {
		return listeVoeux;
	}

	/* Supprimer voeu */
	public static boolean supprimerVoeux(Voeux e) {
		return listeVoeux.remove(e);
	}
	
	/* Ajouter voeu */
	public static void ajouterVoeux(Hashtable<String, String> donn�es) {
		listeVoeux.add(new Voeux(donn�es));
	}
	
	/* Modifier voeu */
	public static void modifierVoeux(Voeux e, Hashtable<String, String> donn�es) {
		e.setDonn�es(donn�es);
	}
	
	/* Attributs voeux */
	public static String[] attributVoeux() {
		return Voeux.getAttributs();
	}

	/* Voeux d'un sujet class� par position */
	public static ArrayList<Voeux> voeuxSujetClass�s(String sujet) {
		ArrayList<Voeux> r�sultat = new ArrayList<Voeux>();
		
		for(int i=0;i<listeVoeux.size();i++) {
			if(listeVoeux.get(i).getDonn�es().get("sujet").equals(sujet)) {
				r�sultat.add(listeVoeux.get(i));
			}
		}
		
		r�sultat.sort(new ComparateurDeVoeux());
		
		return r�sultat;
	}
	
	/* Pr�affectation des sujets */
	public static void pr�affectationSujets() {
		ArrayList<String> tousLesGroupes = new ArrayList<String>();
		ArrayList<String> groupesAvecSujet = new ArrayList<String>();
		ArrayList<String> sujetsDejaPris = new ArrayList<String>();
		
		ArrayList<Voeux> listeDesVoeux = (ArrayList<Voeux>)listeVoeux.clone();
		ArrayList<String> groupesSansSujet = new ArrayList<String>();
		
		/* On r�cup�re tous les groupes */
		for(int i=0;i<listeEtudiants.size();i++) {
			tousLesGroupes.add(listeEtudiants.get(i).getDonn�es().get("groupe"));
		}
		
		/* On r�cup�e les groupes ayant d�j� un sujet et les sujets d�j� pris */
		
		for(int i=0;i<listeProjets.size();i++) {
			groupesAvecSujet.add(listeProjets.get(i).getDonn�es().get("groupe"));
			sujetsDejaPris.add(listeProjets.get(i).getDonn�es().get("sujet"));
		}
		
		groupesSansSujet = tousLesGroupes;
		groupesSansSujet.removeAll(groupesAvecSujet);
		
		/* On affects */
		Set set = new HashSet() ;
        set.addAll(groupesSansSujet) ;
        groupesSansSujet = new ArrayList<String>(set) ;
		
        set = new HashSet() ;
        set.addAll(sujetsDejaPris) ;
        sujetsDejaPris = new ArrayList<String>(set) ;
        
		for(int i=0;i<groupesSansSujet.size();i++) {
			int indiceMeilleurVoeu = 0;
			String groupe = groupesSansSujet.get(i);
			
			for(int j=0;j<listeDesVoeux.size();j++) {
				Hashtable<String, String> voeu = listeDesVoeux.get(j).getDonn�es();
				
				if(voeu.get("groupe").equals(groupe)
						&& !sujetsDejaPris.contains(voeu.get("sujet"))
						&& Integer.parseInt(voeu.get("position")) > indiceMeilleurVoeu) {
					indiceMeilleurVoeu = j;
				}
			}

			String sujetPris = listeDesVoeux.get(indiceMeilleurVoeu).getDonn�es().get("sujet");
			if(indiceMeilleurVoeu != 0) {
				sujetsDejaPris.add(sujetPris);
				
				Hashtable<String, String> nouveauProjet = new Hashtable<String, String>();
				nouveauProjet.put("groupe", groupe);
				nouveauProjet.put("sujet", sujetPris);
				
				ajouterProjet(nouveauProjet);
			}
		}
	}
	
}
