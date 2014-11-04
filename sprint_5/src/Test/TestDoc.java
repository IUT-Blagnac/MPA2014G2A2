package Test;



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.*;

import javax.swing.JFileChooser;

/**
 * Lire les proprietes d'un fichier
 * @author Fobec 2010
 */
public class TestDoc {

    private File file = null;

    /**
     * Contructeur
     * @param filename dossier+nom du fichier
     */
    public TestDoc(String filename) {
        file = new File(filename);
    }

    /**
     * Lire la date du fichier
     * @return
     */
    public long TestDoc() {
        return this.file.lastModified();
    }

    /**
     * Afficher la date du fichier
     * @return date au format dd/m/yyyy H:mm:ss
     */
    public String getFormatedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date(this.file.lastModified());
        return sdf.format(d);
    }

    /**
     * Taille du fichier
     * @return unite byte
     */
    public Long getSize() {
        return this.file.length();
    }

    /**
     * Afficher la taille
     * @return taille au format xx Ko ou xx Mo
     */
    public String getFormatedSize() {
        int size = (int) (this.file.length() / 1024) + 1;
        if (size > 1024) {
            return (size / 1024) + " Mo";
        } else {
            return size + " ko";
        }
    }

    /**
     * Type du fichier
     * @return par exemple Image bitmap
     */
    public String getType() {
        JFileChooser chooser = new JFileChooser();
        return chooser.getTypeDescription(this.file);
    }

    /**
     * Extraire l'extension du fichier
     * @param filename
     * @return format .xxx
     */
    public static String getFileExt(String filename) {
        int pos = filename.lastIndexOf(".");
        if (pos > -1) {
            return filename.substring(pos);
        } else {
            return filename;
        }
    }
    
    
    
    public static void listerRepertoire(File repertoire){ 
    	//Permet de lister toutes les images d'un repertoire
    	String [] listefichiers; 

    	int i; 
    	listefichiers=repertoire.list(); 
    	for(i=0;i<listefichiers.length;i++){ 
    		if(listefichiers[i].endsWith(".JPG")==true || listefichiers[i].endsWith(".gif")==true || listefichiers[i].endsWith(".PNG")==true  ){ 

    			System.out.println(listefichiers[i].substring(0,listefichiers[i].length()));// on choisit la sous chaine - les 5 derniers caracteres ".java" 
    		} 
    	} 
    }
    
    
    public static boolean listerRepertoireJava(String nom,File repertoire,ArrayList<String> tableau){ 
    	//Permet de lister tous les .java d'un repertoire 
    	boolean coucou=false;
    	String [] listefichiers; 
    	//String [] listefichiersInterface; 
    	//String [] listefichiersLib; 
    	//String [] listefichiersModele; 
    	//String [] listefichiersTest; 

    	int i; 
    	listefichiers=repertoire.list(); 
    	for(i=0;i<listefichiers.length;i++){ 
    		
    		if(listefichiers[i].endsWith(".java")==true ){ 
    			if (tableau.contains(nom +"/" +listefichiers[i].substring(0,listefichiers[i].length()-5) )){
    			   
    			   
    			   }
    			else {
    			coucou=true;
    			System.out.println(nom +"/" +listefichiers[i].substring(0,listefichiers[i].length()) +" n'est pas dans la doc" );
    			
    			}
    		} 
    	}
		return coucou; 
    }
    
    
    public static void afficherDateRepertoire(File repertoire){ 
    	//Permet de verifier la date de chaque image d'un repertoire , et d'afficher un message si l'image est à jour ou non
    	//Penser plus tard a un moyen de mettre a jour une image sans quitter le .java
    	String [] listefichiers; 
    	
    	String file = "";
        TestDoc fileProperty = new TestDoc(file);

    	int i; 
    	listefichiers=repertoire.list(); 
    	for(i=0;i<listefichiers.length;i++){ 
    		if(listefichiers[i].endsWith(".JPG")==true || listefichiers[i].endsWith(".gif")==true || listefichiers[i].endsWith(".PNG")==true  ){ 
    			
    			file=repertoire+ "\\"+ listefichiers[i];
    			TestDoc fileProperty1 = new TestDoc(file);
    			
    			System.out.print(fileProperty1.getFormatedDate()+"  ");
    			System.out.print(listefichiers[i].substring(0,listefichiers[i].length())+"  ");
    			
    			System.out.print("\n");
    			
    			
    			
    			
    			
    		} 
    	} 
	
    }
    
    public static void afficherDateRepertoireDocTxt(File repertoire){ 
    	//Permet de verifier la date de chaque image d'un repertoire , et d'afficher un message si l'image est à jour ou non
    	//Penser plus tard a un moyen de mettre a jour une image sans quitter le .java
    	String [] listefichiers; 
    	
    	String file = "";
        TestDoc fileProperty = new TestDoc(file);

    	int i; 
    	listefichiers=repertoire.list(); 
    	for(i=0;i<listefichiers.length;i++){ 
    		if(listefichiers[i].endsWith("Image.txt")==true ){ 
    			
    			file=repertoire+ "\\"+ listefichiers[i];
    			TestDoc fileProperty1 = new TestDoc(file);
    			
    			System.out.print(fileProperty1.getFormatedDate()+"  ");
    			System.out.print(listefichiers[i].substring(0,listefichiers[i].length())+"  ");
    			
    			System.out.print("\n");
    			
    			
    			
    			
    			
    		} 
    	} 
	
    }
    
    public static void afficherDateRepertoireDocHtml(File repertoire){ 
    	//Permet de verifier la date de chaque image d'un repertoire , et d'afficher un message si l'image est à jour ou non
    	//Penser plus tard a un moyen de mettre a jour une image sans quitter le .java
    	String [] listefichiers; 
    	
    	String file = "";
        TestDoc fileProperty = new TestDoc(file);

    	int i; 
    	listefichiers=repertoire.list(); 
    	for(i=0;i<listefichiers.length;i++){ 
    		if(listefichiers[i].endsWith("Image.html")==true ){ 
    			
    			file=repertoire+ "\\"+ listefichiers[i];
    			TestDoc fileProperty1 = new TestDoc(file);
    			
    			System.out.print(fileProperty1.getFormatedDate()+"  ");
    			System.out.print(listefichiers[i].substring(0,listefichiers[i].length())+"  ");
    			
    			System.out.print("\n");
    			
    			
    			
    			
    			
    		} 
    	} 
	
    }
    
    
    public static void verifierDateRepertoire(File repertoire){ 
    	//Permet de verifier la date de chaque image d'un repertoire , et d'afficher un message si l'image est à jour ou non
    	//Penser plus tard a un moyen de mettre a jour une image sans quitter le .java
    	String [] listefichiers; 
    	
    	String file = "";
        TestDoc fileProperty = new TestDoc(file);

    	int i; 
    	listefichiers=repertoire.list(); 
    	for(i=0;i<listefichiers.length;i++){ 
    		if(listefichiers[i].endsWith(".JPG")==true || listefichiers[i].endsWith(".gif")==true || listefichiers[i].endsWith(".PNG")==true  ){ 
    			
    			file=repertoire+ "\\"+ listefichiers[i];
    			TestDoc fileProperty1 = new TestDoc(file);
    			
    			System.out.print(listefichiers[i].substring(0,listefichiers[i].length())+"  ");
    			System.out.print(fileProperty1.getFormatedDate());
    			System.out.print("\n");
    			
    			
    			//On stocke les file dans un tableau s'ils sont trop vieux 
    			
    			
    		} 
    	} 
	
    }
    
    
    /**
    * Loads the specified file into a String representation
    * @author Stephane Nicoll - Infonet FUNDP
    * @version 0.1
    */
    public static String loadFile(File f) {
        try {
           BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
           StringWriter out = new StringWriter();
           int b;
           while ((b=in.read()) != -1)
            out.write(b);
           out.flush();
           out.close();
           in.close();
           return out.toString();
        }
        catch (IOException ie)
        {
             ie.printStackTrace(); 
        }
        return null;
    }
    
    public static String replaceCharAt(String s, int pos, char c) {
        return s.substring(0,pos) + c + s.substring(pos+1);
    }
    




    /**
     * Exemple de propriete du fichier monimage.bmp
     * @param args
     */
    public static void main(String[] args) {
    	
    	FileReader lecteur; 
        //String file = "C:\\RHDSetup.log";
        //FileProperty fileProperty = new FileProperty(file);
        //FileProperty fileProperty1 = new FileProperty(file);
        /*
        System.out.println("Propriete du fichier: " + file);
        System.out.println("Date: " + fileProperty.getFormatedDate());
        System.out.println("Taille: " + fileProperty.getFormatedSize());
        System.out.println("Type: " + fileProperty.getType());
        System.out.println("Extension: " + FileProperty.getFileExt(file));
        */
        
    	
    	
    	
    ////////////////////////////////////////////////	
    //////////////////////////////////////////////
    // PATH COMPLIQUE	
	    
    String curDir = System.getProperty("user.dir");
    System.out.println ("Bienvenue dans le programme de Test de la Documentation ! \n \n ");
    //System.out.println ("Le repertoire courant est: "+curDir +"\n");
    
    
    
    /*
    Pattern p = Pattern.compile("");
    Matcher m = p.matcher(\\);
    StringBuffer sb = new StringBuffer();
    while (m.find()) {
        m.appendReplacement(sb,"\\\\");
    }
    
    m.appendTail(sb);
    System.out.println(sb.toString());
    */
    
    //CA MARCHE LA 
   // String coucou=new String (curDir); 
   // String directoryy=coucou.replaceAll("\\\\", "\\\\\\\\"); 
    
    //On se place dans la racine
    
   // String directory=directoryy.replaceAll("\\\\src\\\\Test", "");    
   
    
    //String dossier ="C:\\Users\\JPP\\Documents\\svn_test\\sprint2\\doc\\images";
   
    //String dossier =".\\.\\doc\\images";
    String dossier =curDir +"\\doc\\images";
    
    //String dossierJava ="C:\\Users\\JPP\\Documents\\svn_test\\sprint2\\src";
    String dossierJava =curDir +"\\src";
    
    //String docTech ="C:\\Users\\JPP\\Documents\\svn\\sprint2\\srcdoc\\docTechniqueImage.txt";
    String docTech = curDir +"\\srcdoc\\docTechniqueImage.txt";
    
    
    String docUtil = curDir +"\\srcdoc\\docUtilisateurImage.txt";
    
    String docHtml = curDir +"\\doc";
    
    String docTxt = curDir +"\\srcdoc";
    
    
    
    
    
    
    
    //FIN PATH COMPLIQUEz
    //////////////////////////////////////////////////::
    ////////////////////////////////////////////////////
    
    String verif ="";
    ArrayList<String> tableau = new ArrayList<>();
    ArrayList<String> tableauJpg = new ArrayList<>();
    File File = new File(dossier);
	File File1 = new File(docTech);
	File File2 = new File(docUtil);
	File File3 = new File(dossierJava);
	File File4 = new File(docHtml);
	File File5 = new File(docTxt);
	
    boolean trouve = false;
    
    System.out.println("Date de dernière mise a jour des docs  : " +"\n");
    
    afficherDateRepertoireDocHtml(File4);
    
    afficherDateRepertoireDocTxt(File5);
    
    
    
    System.out.println("\n");
	System.out.println("Liste des images et leur dernière modification contenus dans le dossier situe à : " +dossier +"\n");	
	
	
	int i =0;
	
	
	
	afficherDateRepertoire(File);
	System.out.println("\n");
	//Verification de l'existance des fichiers .java 
	
	
	
	String test = loadFile(File1);
	String test1 = loadFile(File2);
	
	
	
	
	
	//String line = null, recherche = ".java", path = "C:\\Users\\JPP\\Documents\\svn\\sprint2\\srcdoc\\docTechniqueImage.txt";
	//C:\Users\JPP\Documents\svn\sprint2\srcdoc
	// le chemin du fichier et le mot recherche doivent etre definis
	
	/*try
	  {
	  BufferedReader br = new BufferedReader
	    (new FileReader(path));
	int i = 1; //initialisation du numero de ligne
	while ((line = br.readLine()) != null)
	  {
	    if ( line.indexOf(recherche) != -1)
	    System.out.println("Mot trouve a la ligne " + i );
	    i++;
	  }
	br.close();
	  }
	
	catch(FileNotFoundException exc) { System.out.println("File not found" );  }
	catch(IOException ioe) { System.out.println("Erreur IO" ); }
	
	
	File f1 = new File("Fichier1"); 
	File f2 = new File("Fichier 2"); 
	 
	if(f1.exists() || f2.exists()){
	    //...
	}*/
	
	
	String motif = "\\/([^.]+)\\.java";  // le . de chaine. signifie qu'on veut chaine suivie d'un caractère 
  	
    int nbreDeFois = 0;
    
    System.out.println("Recherche des fichiers .java contenus dans la doc technique " );
    System.out.print("Et verification de leur existance dans le dossier :  " +dossierJava +"\n" );
    System.out.print("(Sans prise en compte des doublons)" +"\n \n" );
    
    Pattern pattern = Pattern.compile(motif);
    Matcher matcher = pattern.matcher(test);
 	
    while(matcher.find())
    {
    	//System.out.println(matcher.group(1));
    	trouve = false;
       
       //On evite les doublons = stocker dans un tableau
       
       
     
       
       
       
       while (i<tableau.size()){
    	   
    	   if (tableau.contains(matcher.group(1)) ){trouve =true;
		   
		   
		   }
    	   
    	   i++;
       }
       
       i=0;
       if (!trouve) {tableau.add(matcher.group(1)); 
       //System.out.println(matcher.group(1));
       String kouul = matcher.group(1)+".java";
       String koul=kouul.replaceAll("/", "\\\\"); 
      // System.out.print(koul);
       verif =dossierJava +"\\" + koul;
       File testF =  new File(verif);
       
       //System.out.println( "\n" +verif);
       
       
	       
       if(testF.exists()){System.out.print("OK  " +koul  +"\n");}
       else { System.out.print("NOK " +koul +" n'existe pas dans le dossier !" +"\n"); } 
	       
       
        //System.out.println( "FIN \n" );
       }
       nbreDeFois++;
       //FIn doublons
       
       
      
      
       
       
       
       
    }
       
    System.out.println("Il existe " + nbreDeFois
       + " .java  dans la doc Technique " +"\n");
	
	
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Verification de l'existance des fichiers images dans les 2doc
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    /* Marche pas 
    String motifJpg = ".JPG";  // le . de chaine. signifie qu'on veut chaine suivie d'un caractère (dans notre cas chaine1 2 ou 3)
  	
    nbreDeFois = 0;
    
    
    System.out.println("Recherche des fichiers .JPG contenus dans la doc Utilisateur avec comme motif : " +motif  );
    System.out.print("Et verification de leur existance dans le dossier :  " +dossier +"\n" );
    System.out.print("(Sans prise en compte des doublons)" +"\n \n" );
    
    Pattern pattern1 = Pattern.compile(motifJpg);
   
    Matcher matcher1 = pattern1.matcher(test1);
 	
    while(matcher1.find())
    {
    	trouve = false;
       //System.out.println(matcher1.group(1));
       //On evite les doublons = stocker dans un tableau
       
       
     
       
       
       
       while (i<tableauJpg.size()){
    	   
    	   if (tableauJpg.contains(matcher1.group(1)) ){trouve =true;
		   
		   
		   }
    	   
    	   i++;
       }
       
       i=0;
       if (!trouve) {tableauJpg.add(matcher1.group(1)); 
       
       String kouul = matcher1.group(1)+".java";
       String koul=kouul.replaceAll("/", "\\\\"); 
       System.out.print(koul);
       verif =dossier +"\\" + koul;
       File testF =  new File(verif);
       
       //System.out.println( "\n" +verif);
       
       
	       
       if(testF.exists()){System.out.print(" existe" +"\n");}
       
       
       else { System.out.print(" n'existe pas " +"\n"); } 
	       
       
        //System.out.println( "FIN \n" );
       }
       nbreDeFois++;
       //FIn doublons
       
       
      
      
       
       
       
       
    }
       
    System.out.println("\n\"" + motifJpg + "\"" + " existe " + nbreDeFois
       + " fois dans \"" + "DocUtilisateur" + "\"\n");
       */
    
    //
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 	//Verification du fait qu'il manque des .java dans la doc 
     /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    String docSrcControleur = curDir +"\\src\\Controleur";
    File FileControleur = new File(docSrcControleur);
    
    String docSrcInterface = curDir +"\\src\\Interface";
    File FileInterface = new File(docSrcInterface);
    
    String docSrcLib = curDir +"\\src\\Lib";
    File FileLib = new File(docSrcLib);
    
    String docSrcModele = curDir +"\\src\\Modele";
    File FileModele = new File(docSrcModele);
    
    String docSrcTest = curDir +"\\src\\Test";
    File FileTest = new File(docSrcTest);
    
    
    
    
    
   System.out.println(" \n \n" +"Verification de la presence de tous les fichiers java dans la doc : \n"); 
   
   
   
   
   listerRepertoireJava("Controleur",FileControleur,tableau);
   listerRepertoireJava("Interface",FileInterface,tableau);
   listerRepertoireJava("Lib",FileLib,tableau);
   listerRepertoireJava("Modele",FileModele,tableau);
   listerRepertoireJava("Test",FileTest,tableau);
  
   if(! listerRepertoireJava("Lib",FileLib,tableau)&&!listerRepertoireJava("Interface",FileInterface,tableau)
		   &&!listerRepertoireJava("Test",FileTest,tableau)&&!listerRepertoireJava("Modele",FileModele,tableau)
		   &&!listerRepertoireJava("Controleur",FileControleur,tableau)){System.out.println("Tous les fichiers java sont dans la doc !");}
    
	int k =0;
	
	
	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
