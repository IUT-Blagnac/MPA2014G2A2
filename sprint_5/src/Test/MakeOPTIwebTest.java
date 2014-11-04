package Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import OptiWeb.MakeOPTIweb;

public class MakeOPTIwebTest extends TestCase {

	static int nbTestsOk=0;
	static int nbTestsTotal=0;
	
	public void test_exist(){
		nbTestsTotal+=1;
		String fichier="OPTIweb.html";
		BufferedReader In=null;
		
		//Suppression du fichier
		File opti = new File("OPTIweb.html");
	    opti.delete();

		//Generation du fichier
		MakeOPTIweb.generator();
		
		
		//Test si le fichier existe
		try{
		    In = new BufferedReader(new FileReader(fichier));
		    nbTestsOk++;
		} catch (FileNotFoundException fnfe) {
		   System.out.println("Erreur : Le fichier n'existe pas");
		}
		
	}
	
	public static void main(String[] args) {

		junit.textui.TestRunner.run(new TestSuite(MakeOPTIwebTest.class));
		System.out.println(nbTestsOk + "/" + nbTestsTotal + " tests valides.");
	}

}