package OptiWeb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Hashtable;

import Controleur.Controleur;
import Lib.Optilib;
import Modele.Etudiants;
import Modele.Intervenants;
import Modele.Projets;
import Modele.Sujets;

public class MakeOPTIweb {

	public static void generator(/*String projets, String sujets, String etudiants, String intervenants*/) {
		Controleur.initCsv();
		
		/*Controleur.entres.put("Projets", projets);
		Controleur.entres.put("Sujets", sujets);
		Controleur.entres.put("Etudiants", etudiants);
		Controleur.entres.put("Intervenants", intervenants);*/
		
		Controleur.importer();
		
		String adresse_fichier = "../../OPTIweb.html";
		String adresse_javascript = "javascript.js";
		String adresse_css = "style.css";
		
		String titre = "P<span class=\"landscape\">rojets </span>tut<span class=\"landscape\">ores</span> 2014-2015<br/>Departement INFO<span class=\"landscape\">RMATIQUE</span><br/>IUT de Blagnac";
		String version = "0.1";
		String javascript = "";
		String css = "";
		
		try {
			// Lecture du javascript
			File fichier_javascript = new File(adresse_javascript);
			javascript = new String(Files.readAllBytes(fichier_javascript.toPath()), "UTF-8");
		
			// Lecture du css
			File fichier_css = new File(adresse_css);
			css = new String(Files.readAllBytes(fichier_css.toPath()), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			
			Writer sortie_html = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(adresse_fichier), "UTF-8"));
			
			/**** HEADER ****/
			sortie_html.write(
					"<!DOCTYPE html>\n" +
					"<html>\n" +
					"<head>\n" +
					"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
					"<title>OPTIweb - V" + version + "</title>\n"
					);

			sortie_html.write(
					"<script>\n" +
					javascript +
					"</script>\n" +
					"<style type='text/css'>\n" +
					css +
					"</style>\n"
				);
					
			sortie_html.write(
					"</head>\n" +
					"<body>\n"
				);
			
			/**** ACCUEIL ****/
			sortie_html.write("<!-- DEBUT page accueil -->\n");
			
			sortie_html.write("<div class=\"page\" id=\"accueil\">\n");
				sortie_html.write("<div class=\"header\">\n");
					sortie_html.write("<h1 class=\"titre\">\n");
						sortie_html.write(titre);
					sortie_html.write("</h1>\n");
					sortie_html.write("<a href=\"javascript:changer_page('credits');\" class=\"bouton\">Credits</a>\n");
				sortie_html.write("</div>\n");
				
				sortie_html.write("<div class=\"contenu\">\n");
					sortie_html.write(
							"<ul class=\"liste\" id=\"listeSources\">\n" +
							"<li><a href=\"javascript:changer_page('projets');\"><i class=\"icon_projet\"></i> Projets</a></li>\n" +
							"<li><a href=\"javascript:changer_page('sujets');\"><i class=\"icon_sujet\"></i> Sujets</a></li>\n" +
							"<li><a href=\"javascript:changer_page('etudiants');\"><i class=\"icon_etudiant\"></i> Etudiants</a></li>\n" +
							"<li><a href=\"javascript:changer_page('intervenants');\"><i class=\"icon_intervenant\"></i> Intervenants</a></li>\n" +
							"</ul>\n"
						);
				sortie_html.write("</div>\n");
				
				sortie_html.write("<div class=\"footer\">\n");
					sortie_html.write("<h4 class=\"titre\">OPTIweb V<span class=\"landscape\">ersion </span>0.1</h4>\n");
				sortie_html.write("</div>\n");
			sortie_html.write("</div>\n");
			
			sortie_html.write("<!-- FIN page accueil -->\n");
			
			/**** CREDITS ****/
			sortie_html.write("<!-- DEBUT page credits -->\n");
			
			sortie_html.write("<div class=\"page\" id=\"credits\" style=\"display:none;\">\n");
				sortie_html.write("<div class=\"header\">\n");
					sortie_html.write("<h1 class=\"titre\">\n");
						sortie_html.write("Credits\n");
					sortie_html.write("</h1>\n");
					sortie_html.write("<a href=\"javascript:changer_page('accueil');\" class=\"bouton2\"><i class=\"icon_retour\"></i> Retour</a>\n");
				sortie_html.write("</div>\n");
				
				/* Contenu */
				sortie_html.write("<div class=\"contenu\">\n");

					sortie_html.write("<p>Cette application a ete realisee dans le cadre du module M3301/MPA du DUT Informatique a l'IUT de Blagnac.</p>\n");
				
					sortie_html.write(
							"<ul class=\"liste2\">\n" +
							"<li class=\"liste_titre\">Product Owner</li>\n" +
							"<li>Andre PeNINOU</li>\n" +
							"<li>Universite Toulouse 2 - IUT de Blagnac<br />Departement INFORMATIQUE</li>\n" +
							"</ul>\n"
							);
					
					sortie_html.write(
							"<ul class=\"liste2\">\n" +
							"<li class=\"liste_titre\">Membres du groupe</li>\n" +
							"<li>LOPEZ Nathan <i>Developpeur supreme & chef de projet</i></li>\n" +
							"<li>ERB Alexandre <i>Doc'eur en herbe</i></li>\n" +
							"<li>SANTACANA Nathan <i>Expert en test</i></li>\n" +
							"<li>COLLIGNON Benjamin <i>Interfaceur graphique</i></li>\n" +
							"<li>VERON Vimel <i>Interfaceur graphique</i></li>\n" +
							"<li>PERE Jean-Philippe <i>Testeur de la doc & ScrumMasteur</i></li>\n" +
							"</ul>\n"
							);
					
					sortie_html.write(
							"<ul class=\"liste2\">\n" +
							"<li class=\"liste_titre\">Propulse par</li>\n" +
							"<li>Nathan LOPEZ's brain</li>\n" +
							"<li>NEVER Jquery</li>\n" +
							"<li>Notre application contient 34161 caracteres, la meme application utilisant JQuery en fait 544917, la notre est donc 15,9 fois plus legere. Notre application ne necessite pas de connexion internet.</li>\n" +
							"</ul>\n"
							);
				
				sortie_html.write("</div>\n");
				/* Fin contenu */
				
				sortie_html.write("<div class=\"footer\">\n");
					sortie_html.write("<h4 class=\"titre\">OPTIweb V<span class=\"landscape\">ersion </span>0.1</h4>\n");
				sortie_html.write("</div>\n");
			sortie_html.write("</div>\n");
			
			sortie_html.write("<!-- FIN page credits -->\n");
			
			/**** PROJETS ****/
			sortie_html.write("<!-- DEBUT page projets -->\n");
			
			sortie_html.write("<div class=\"page\" id=\"projets\" style=\"display:none;\">\n");
				sortie_html.write("<div class=\"header\">\n");
					sortie_html.write("<h1 class=\"titre\">\n");
						sortie_html.write("Projets 2014-2015\n");
					sortie_html.write("</h1>\n");
					sortie_html.write("<a href=\"javascript:changer_page('accueil');\" class=\"bouton2\"><i class=\"icon_retour\"></i> Retour</a>\n");
				sortie_html.write("</div>\n");
				
				/* Contenu */
				sortie_html.write("<div class=\"contenu\">\n");

					sortie_html.write("<form><input id=\"autocomplete-input-projet\" onchange=\"cherche_projet();\" onkeyup=\"cherche_projet();\" onkeydown=\"cherche_projet();\" class=\"recherche\" name=\"projet\" placeholder=\"Vous cherchez ?...\" /></form>\n");
				
					sortie_html.write("<ul id=\"listeprojets\" style=\"border-radius:10px;\" class=\"liste3\">\n");
						
						for(int i=0;i<Controleur.getProjets().size();i++) {
							Sujets sujet = Controleur.findSujet(Controleur.getProjets().get(i).getDonnees().get("sujet"));
							Intervenants client = Controleur.findIntervenant(Controleur.getProjets().get(i).getDonnees().get("client"));
							Intervenants superviseur = Controleur.findIntervenant(Controleur.getProjets().get(i).getDonnees().get("superviseur"));
							ArrayList<Etudiants> etudiantsG = Controleur.getEtudiantsGroupe(Controleur.getProjets().get(i).getDonnees().get("groupe"));
							
							String cli_pre = "", cli_nom = "", sup_pre = "", sup_nom = "";
							
							if(client != null) {
								cli_pre = client.getDonnees().get("prenom");
								cli_nom = client.getDonnees().get("nom");
							}
							
							if(superviseur != null) {
								sup_pre = client.getDonnees().get("prenom");
								sup_nom = client.getDonnees().get("nom");
							}
							
							sortie_html.write(
									"<li id=\"p" + i + "\">\n"+
									"<p><b>[" + sujet.getDonnees().get("nom") + "]</b> " + sujet.getDonnees().get("titre") + "</p>\n"+
									"<p><b>Client :</b> " + cli_nom + " " + cli_pre + "</p><p><b>Superviseur :</b> " + sup_nom + " " + sup_pre + "</p><p>\n"+
									"<b>Groupe " + Controleur.getProjets().get(i).getDonnees().get("groupe") + " :</b> \n"
								);
							
							for(int j=0; j<etudiantsG.size();j++) {
								sortie_html.write(etudiantsG.get(j).getDonnees().get("prenom") +
										" " +
										etudiantsG.get(j).getDonnees().get("nom") +
										" - "
									);
							}
							
							sortie_html.write("</p></li>\n");
						}
					
					sortie_html.write("</ul>\n");
					
				sortie_html.write("</div>\n");
				/* Fin contenu */
				
				sortie_html.write("<div class=\"footer\">\n");
					sortie_html.write("<h4 class=\"titre\">OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"icon_projet\"></i></h4>\n");
				sortie_html.write("</div>\n");
			sortie_html.write("</div>\n");
			
			sortie_html.write("<!-- FIN page projets -->");
			
			/**** SUJETS ****/
			sortie_html.write("<!-- DEBUT page sujets -->\n");
			
			sortie_html.write("<div class=\"page\" id=\"sujets\" style=\"display:none;\">\n");
				sortie_html.write("<div class=\"header\">\n");
					sortie_html.write("<h1 class=\"titre\">\n");
						sortie_html.write("Sujets 2014-2015\n");
					sortie_html.write("</h1>\n");
					sortie_html.write("<a href=\"javascript:changer_page('accueil');\" class=\"bouton2\"><i class=\"icon_retour\"></i> Retour</a>\n");
				sortie_html.write("</div>\n");
				
				/* Contenu */
				sortie_html.write("<div class=\"contenu\">\n");

					sortie_html.write("<form><input id=\"autocomplete-input-sujet\" onchange=\"cherche_sujet();\" onkeyup=\"cherche_sujet();\" onkeydown=\"cherche_sujet();\" class=\"recherche\" name=\"projet\" placeholder=\"Vous cherchez ?...\" /></form>\n");
			
					sortie_html.write("<ul class=\"liste\" id=\"listesujets\">\n" +
							"<li class=\"liste_titre\">Sujet <span class=\"groupe\" style=\"color: #333;\">Groupe</span></li>\n");
					
					for(int j=0;j<Controleur.getSujets().size();j++) {
						Sujets s = Controleur.getSujets().get(j);
						ArrayList<Projets> p = Controleur.getProjets();
						String g = "";
						
						for(int k=0;k<p.size();k++) {
							if(p.get(k).getDonnees().get("sujet").equals(s.getDonnees().get("id"))) {
								g = p.get(k).getDonnees().get("groupe");
							}
						}
						
						if(g == null) {
							g = "N O";
						}
						
						sortie_html.write("<li id=\"s" + j + "\"><a href=\"javascript:projet('" + s.getDonnees().get("nom") + "');\">[" + s.getDonnees().get("nom") + "] <br/>" + s.getDonnees().get("titre") + "<b /><span class=\"groupe\">" + g + "</span></a></li>\n");
					}
					
					sortie_html.write("</ul>\n");
					
				sortie_html.write("</div>\n");
				/* Fin contenu */
				
				sortie_html.write("<div class=\"footer\">\n");
					sortie_html.write("<h4 class=\"titre\">OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"icon_sujet\"></i></h4>\n");
				sortie_html.write("</div>\n");
			sortie_html.write("</div>\n");
			
			sortie_html.write("<!-- FIN page sujets -->\n");
			
			/**** ETUDIANTS ****/
			sortie_html.write("<!-- DEBUT page etudiants -->\n");
			
			sortie_html.write("<div class=\"page\" id=\"etudiants\" style=\"display:none;\">\n");
				sortie_html.write("<div class=\"header\">\n");
					sortie_html.write("<h1 class=\"titre\">\n");
						sortie_html.write("Etudiants 2014-2015\n");
					sortie_html.write("</h1>\n");
					sortie_html.write("<a href=\"javascript:changer_page('accueil');\" class=\"bouton2\"><i class=\"icon_retour\"></i> Retour</a>\n");
				sortie_html.write("</div>\n");
				
				/* Contenu */
				sortie_html.write("<div class=\"contenu\">\n");

					sortie_html.write("<form><input id=\"autocomplete-input-etudiant\" onchange=\"cherche_etudiant();\" onkeyup=\"cherche_etudiant();\" onkeydown=\"cherche_etudiant();\" class=\"recherche\" name=\"projet\" placeholder=\"Vous cherchez ?...\" /></form>\n");
					 
					sortie_html.write("<ul class=\"liste\" id=\"listeetudiants\">\n" +
							"<li class=\"liste_titre\">Etudiant <span class=\"groupe\" style=\"color: #333;\">Groupe</span></li>\n");

					for(int j=0;j<Controleur.getEtudiants().size();j++) {
						Hashtable<String, String> e = Controleur.getEtudiants().get(j).getDonnees();
						
						sortie_html.write("<li id=\"e" + j + "\"><a href=\"javascript:projet('" + e.get("prenom") + " " + e.get("nom") + "');\">" + e.get("nom") + " " + e.get("prenom") + "<span class=\"groupe\">Groupe " + e.get("groupe") + "</span></a></li>\n");
					}
					
					sortie_html.write("</ul>\n");
					
				sortie_html.write("</div>\n");
				/* Fin contenu */
				
				sortie_html.write("<div class=\"footer\">\n");
					sortie_html.write("<h4 class=\"titre\">OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"icon_etudiant\"></i></h4>\n");
				sortie_html.write("</div>\n");
			sortie_html.write("</div>\n");
			
			sortie_html.write("<!-- FIN page etudiants -->\n");
			
			/**** INTERVENANTS ****/
			sortie_html.write("<!-- DEBUT page intervenants -->\n");
			
			sortie_html.write("<div class=\"page\" id=\"intervenants\" style=\"display:none;\">\n");
				sortie_html.write("<div class=\"header\">\n");
					sortie_html.write("<h1 class=\"titre\">\n");
						sortie_html.write("Intervenants 2014-2015\n");
					sortie_html.write("</h1>\n");
					sortie_html.write("<a href=\"javascript:changer_page('accueil');\" class=\"bouton2\"><i class=\"icon_retour\"></i> Retour</a>\n");
				sortie_html.write("</div>\n");
				
				/* Contenu */
				sortie_html.write("<div class=\"contenu\">\n");

					sortie_html.write("<form><input id=\"autocomplete-input-intervenant\" onchange=\"cherche_intervenant();\" onkeyup=\"cherche_intervenant();\" onkeydown=\"cherche_intervenant();\" class=\"recherche\" name=\"projet\" placeholder=\"Vous cherchez ?...\" /></form>\n");
					
					sortie_html.write("<ul class=\"liste\" id=\"listeintervenants\">\n" +
							"<li class=\"liste_titre\">Intervenant <div class=\"div_droite\"><span class=\"role\" style=\"color: #333;\">Client</span></div><div class=\"div_droite\"><span class=\"role\" style=\"color: #333;\">Superviseur</span></div></li>\n");

					for(int j=0;j<Controleur.getIntervenants().size();j++) {
						Hashtable<String, String> e = Controleur.getIntervenants().get(j).getDonnees();
						Object[][] tab_roles = Controleur.intervenantPlus(Controleur.getIntervenants().get(j));
						
						int client = 0, superviseur = 0;
						
						for(int k=0;k<tab_roles.length;k++) {
							if(tab_roles[k][3].equals("Superviseur")) {
								superviseur++;
							} else if(tab_roles[k][3].equals("Client")) {
								client++;
							}
						}
						
						sortie_html.write("<li id=\"i" + j + "\"><a href=\"javascript:projet('" + e.get("nom") + " " + e.get("prenom") + "');\">" + e.get("nom") + " " + e.get("prenom") + "<div class=\"div_droite\"><span class=\"role\" style=\"color: #333;\">" + client + "</span></div><div class=\"div_droite\"><span class=\"role\" style=\"color: #333;\">" + superviseur + "</span></div></a></li>\n");
					}
					
					sortie_html.write("</ul>\n");
					
				sortie_html.write("</div>\n");
				/* Fin contenu */
				
				sortie_html.write("<div class=\"footer\">\n");
					sortie_html.write("<h4 class=\"titre\">OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"icon_intervenant\"></i></h4>\n");
				sortie_html.write("</div>\n");
			sortie_html.write("</div>\n");
			
			sortie_html.write("<!-- FIN page intervenants -->\n");
			
			sortie_html.write("<div style=\"display:none;\" id=\"nb_projets\">" + Controleur.getProjets().size() + "</div>");
			sortie_html.write("<div style=\"display:none;\" id=\"nb_sujets\">" + Controleur.getSujets().size() + "</div>");
			sortie_html.write("<div style=\"display:none;\" id=\"nb_etudiants\">" + Controleur.getEtudiants().size() + "</div>");
			sortie_html.write("<div style=\"display:none;\" id=\"nb_intervenants\">" + Controleur.getIntervenants().size() + "</div>");
			
			/**** FOOTER ****/
			sortie_html.write(
					"</body>\n" +
					"</html>\n"
				);
			
			sortie_html.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String arg[]) {
		// generator("data/projets2014_2015.csv", "data/sujets2014_2015.csv", "data/etudiants2014_2015.csv", "data/intervenants2014_2015.csv");
		generator();
	}

}