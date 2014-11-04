package OptiWeb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Hashtable;

import Lib.Optilib;

public class MakeOPTIwebJQuery {

	/**
	 * @param args
	 */
	public static void generator(String projets, String sujets, String etudiants, String intervenants) {
		Hashtable<String, String>[] liste_projets = Optilib.reader(projets);
		Hashtable<String, String>[] liste_sujets = Optilib.reader(sujets);
		Hashtable<String, String>[] liste_etudiants = Optilib.reader(etudiants);
		Hashtable<String, String>[] liste_intervenants = Optilib.reader(intervenants);
		
		String adresse_fichier = "OPTIwebJQuerry.html";
		
		
		try {
			Writer sortie = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(adresse_fichier), "UTF-8"));
			sortie.write("<!DOCTYPE html>\n ");
			sortie.write("<html>\n ");
			sortie.write("<head>\n ");
			sortie.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> \n ");
			sortie.write(" <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> \n ");
			sortie.write(" <meta name=\"generator\" content=\"OPTIweb VOPTIweb\" /> \n ");
			sortie.write(" <title>OPTIweb - V0.1</title> \n ");
			sortie.write("  <link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css\" /> \n ");
			sortie.write("  <link rel=\"stylesheet\" href=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css\" /> \n ");
			sortie.write("<script src=\"http://code.jquery.com/jquery-1.9.1.min.js\"></script>\n ");
			sortie.write("<script src=\"http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js\"></script>\n ");
			sortie.write(" <style type='text/css'>   \n ");
			sortie.write(" @media all and (orientation:portrait) { .landscape {display: none;} }  \n ");
			sortie.write(" @media all and (orientation:landscape) { .landscape {display: inline;} } \n ");
			sortie.write(" </style> \n ");
			sortie.write(" </head><body> \n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			
			
			sortie.write(" <!-- DEBUT page accueil --> \n ");
			sortie.write(" <div data-role=\"page\" id=\"accueil\" data-title=\"OPTIweb - V0.1\">  \n ");
			sortie.write("  <div data-role=\"header\" data-add-back-btn=\"true\">  \n ");
			sortie.write("  <h1>P<span class=\"landscape\">rojets </span>tut<span class=\"landscape\">ores</span> 2014-2015<br/>Departement INFO<span class=\"landscape\">RMATIQUE</span><br/>IUT de Blagnac</h1> \n ");
			sortie.write("  <a href=\"#credits\" data-theme=\"b\" class=\"ui-btn-right\">Credits</a> \n ");
			sortie.write("  </div> \n ");
			sortie.write(" <div data-role=\"content\"> \n ");
			sortie.write("  <ul data-role=\"listview\" data-inset=\"true\" id=\"listeSources\">\n ");
			sortie.write("  <li><a href=\"#projets\"><i class=\"fa fa-tasks\"></i> Projets</a></li> \n ");
			sortie.write("  <li><a href=\"#sujets\"><i class=\"fa fa-copy\"></i> Sujets</a></li> \n ");
			sortie.write("    <li><a href=\"#etudiants\"><i class=\"fa fa-group\"></i> Etudiants</a></li> \n ");
			sortie.write(" <li><a href=\"#intervenants\"><i class=\"fa fa-group\"></i> Intervenants</a></li> \n ");
			sortie.write(" </ul> \n ");
			sortie.write("</div>  \n ");
			sortie.write("<div data-role=\"footer\">  \n ");
			sortie.write(" <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4> \n ");
			sortie.write(" </div> \n ");
			sortie.write("  </div>\n ");
			sortie.write("  <!-- FIN page accueil -->\n ");
			
			
			sortie.write("<!-- DEBUT page credits --> \n ");
			sortie.write(" <div data-role=\"page\" id=\"credits\" data-title=\"OPTIweb - V0.1 - Credits\"> \n ");
			sortie.write(" <div data-role=\"header\" data-add-back-btn=\"true\"> \n ");
			sortie.write(" <h1>Credits</h1> \n ");
			sortie.write(" </div> \n ");
			sortie.write(" <div data-role=\"content\"> \n ");
			sortie.write("  <p>Cette application a ete realisee dans le cadre du module M3301/MPA du DUT Informatique à l'IUT de Blagnac.</p> " );
			sortie.write(" <ul data-role=\"listview\" data-inset=\"true\" id=\"contacts\" data-theme=\"a\" data-divider-theme=\"b\"> \n ");
			sortie.write(" <li data-role=\"list-divider\">Product Owner</li> \n ");
			sortie.write(" <li>Andre PeNINOU</li>\n ");
			sortie.write(" <li>Universite Toulouse 2 - IUT de Blagnac\n ");
			sortie.write(" <br/>Departement INFORMATIQUE</li> \n ");
			sortie.write(" </ul> \n ");
			sortie.write(" <ul data-role=\"listview\" data-inset=\"true\" id=\"listecredits\" data-theme=\"a\" data-divider-theme=\"b\">\n ");
			sortie.write(" <li data-role=\"list-divider\">Membres de l'equipe enseignante</li> \n ");
			sortie.write(" <li>Jean-Michel BRUEL</li><li>Jean-Michel INGLEBERT</li><li>Andre PeNINOU</li><li>Olivier ROQUES</li>\n ");
			sortie.write(" </ul> \n ");
			sortie.write(" <ul data-role=\"listview\" data-inset=\"true\" id=\"listepowered\" data-theme=\"a\" data-divider-theme=\"b\"> \n ");
			sortie.write("  <li data-role=\"list-divider\">Propulse par</li>\n ");
			sortie.write(" <li><a href=\"http://jquerymobile.com/\" target=\"autrePage\">http://jquerymobile.com/</a></li>\n ");
			sortie.write("  <li><a href=\"http://fortawesome.github.io/Font-Awesome/\" target=\"autrePage\">http://fortawesome.github.io/Font-Awesome/</a></li>\n ");
			sortie.write("</ul> \n ");
			
			sortie.write("</div> \n ");
			sortie.write("<div data-role=\"footer\">  \n ");
			sortie.write("<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa- fa-2x\"></i></h4>  \n ");
			sortie.write("</div>\n ");
			sortie.write("</div>\n ");
			sortie.write("<!-- FIN page credits -->\n ");
			
			
			sortie.write("<!-- DEBUT page projets --> \n ");
			sortie.write("<div data-role=\"page\" id=\"projets\" data-title=\"OPTIweb - V0.1\">\n ");
			sortie.write("<div data-role=\"header\" data-add-back-btn=\"true\">\n ");
			sortie.write("<h1>Projets 2014-2015</h1>\n ");
			sortie.write("</div>\n ");
			sortie.write("<div data-role=\"content\">\n ");
			sortie.write("\n ");	// AFICHAGE WTF
			
	////////////////////////////////////////
			
			
			
			sortie.write("<div id=\"projet\"> </div> \n ");
			
			
			
			
		//////////////////////////
			sortie.write("</div>\n ");
			sortie.write("<div data-role=\"footer\"> \n ");
			sortie.write(" <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-tasks fa-2x\"></i></h4> \n ");
			sortie.write("</div>\n ");
			sortie.write("</div>\n ");
			sortie.write("<!-- FIN page projets -->\n ");
			
			
			
			sortie.write("<!-- DEBUT page sujets --> \n ");
			sortie.write("<div data-role=\"page\" id=\"sujets\" data-title=\"OPTIweb - V0.1\">\n ");
			sortie.write("<div data-role=\"header\" data-add-back-btn=\"true\">\n ");
			sortie.write("<h1>Sujets 2014-2015</h1>\n ");
			sortie.write("</div>\n ");
			sortie.write("<div data-role=\"content\">\n ");
			sortie.write("\n ");//AFFICHAGE
			sortie.write("</div>\n ");
			sortie.write("<div data-role=\"footer\"> \n ");
			sortie.write("<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-copy fa-2x\"></i></h4> \n ");
			sortie.write("</div>\n ");
			sortie.write("</div>\n ");
			sortie.write("<!-- FIN page sujets --> \n ");
			
			
			sortie.write("<!-- DEBUT page etudiants -->\n ");
			sortie.write("<div data-role=\"page\" id=\"etudiants\" data-title=\"OPTIweb - V0.1\">\n ");
			sortie.write("<div data-role=\"header\" data-add-back-btn=\"true\">\n ");
			sortie.write("<h1>Etudiants 2014-2015</h1>\n ");
			sortie.write("</div>\n ");
			sortie.write("<div data-role=\"content\">\n ");
			sortie.write("\n ");//AFFICHAGE
			sortie.write("</div>\n ");
			sortie.write("<div data-role=\"footer\"> \n ");
			sortie.write("<h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4> \n ");
			sortie.write("</div>\n ");
			sortie.write("</div>\n ");
			sortie.write("<!-- FIN page etudiants -->\n ");
			
			
			sortie.write("<!-- DEBUT page intervenants -->\n ");
			sortie.write("<div data-role=\"page\" id=\"intervenants\" data-title=\"OPTIweb - V0.1\">\n ");
			sortie.write("<div data-role=\"header\" data-add-back-btn=\"true\">\n ");
			sortie.write("<h1>Intervenants 2014-2015</h1>\n ");
			sortie.write("</div>\n ");
			sortie.write("<div data-role=\"content\">\n ");
			sortie.write("\n ");//AFFICHAGE
			sortie.write("</div>\n ");
			sortie.write("<div data-role=\"footer\"> \n ");
			sortie.write(" <h4>OPTIweb V<span class=\"landscape\">ersion </span>0.1 <i class=\"fa fa-group fa-2x\"></i></h4> \n ");
			sortie.write("</div>\n ");
			sortie.write("</div>\n ");
			sortie.write("<!-- FIN page intervenants -->\n ");
			
			
			
			sortie.write("<script>\n ");
			
			// http://pckult.developpez.com/tutoriels/javascript/frameworks/jquery/lecture-fichier-xml/
			//String curDir = System.getProperty("user.dir");
			
			sortie.write("var file = \"C:\\Users\\Etudiant\\Documents\\mpa\\sprint4\\data\";\n ");
			 
			
			
			
			sortie.write("$(document).ready(  \n ");
			sortie.write(" function()\n ");
			sortie.write(" {\n ");
			sortie.write(" $.ajax( {\n ");
			sortie.write(" type: \"GET\",\n ");
			sortie.write("url: \"sites.xml\",\n ");
			sortie.write("dataType: \"xml\",\n ");
			sortie.write("success: function(xml) \n ");
			sortie.write(" {\n ");
			sortie.write("$(xml).find('site').each(   \n ");
			sortie.write("function()\n ");
			sortie.write(" {\n ");
			sortie.write(" var id = $(this).attr('id');\n ");
			sortie.write("  var title = $(this).find('title').text();\n ");
			sortie.write("  var url = $(this).find('url').text();\n ");
			sortie.write(" $('<div class=\"items\" id=\"link_' + id + '\"></div>').html('<a href=\"' + url + '\">' + title + '</a>').appendTo('#Div_XML');\n ");
			sortie.write("$(this).find('desc').each(\n ");
			sortie.write(" function()\n ");
			sortie.write("{\n ");
			sortie.write(" var brief = $(this).find('brief').text();\n ");
			sortie.write(" var long = $(this).find('long').text();\n ");
			sortie.write(" $('<div class=\"brief\"></div>').html(brief).appendTo('#link_'+id);\n ");
			sortie.write(" $('<div class=\"long\"></div>').html(long).appendTo('#link_'+id);\n ");
			sortie.write("     });\n ");
			sortie.write(" });\n ");
			sortie.write("}\n ");
			sortie.write("});\n ");
			sortie.write(" }\n ");
			sortie.write(");\n ");
			
			
			
			
			
			
			
			sortie.write("// li click handler which fills the projects search bar \n ");
			sortie.write(" // with the value of the current data-find attribute\n ");
			sortie.write(" $( 'li[data-find]' ).on( 'click',function(event){\n ");
			sortie.write("$(\"#autocomplete-input-projet\").val($(this).attr('data-find')).trigger('change');\n ");
			sortie.write("});\n ");
		
			
			sortie.write("<script type=\"text/javascript\" src=\"jquery.js\"></script>\n ");
			sortie.write(" <script type=\"text/javascript\">\n ");
			sortie.write(" $('#projet').html('Hello World');\n ");
			sortie.write(" </script>\n ");
			
			
			
			
			sortie.write("</script>\n ");
			sortie.write("</body>\n ")
			;sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("</html>\n ");
			
			
			
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			sortie.write("\n ");
			
			sortie.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static void main(String arg[]) {
		generator("data/projets2014_2015.csv", "data/sujets2014_2015.csv", "data/etudiants2014_2015.csv", "data/intervenants2014_2015.csv\n ");
		System.out.println("TERMINER :D\n ");
	}

}
