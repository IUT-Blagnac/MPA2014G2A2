package Interface;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Controleur.Controleur;
import Modele.Etudiants;
import Modele.Intervenants;
import Modele.Projets;

public class AffichageProj {
	private static JFrame parent;
	private static ArrayList<Projets> liste;
	private static Hashtable<String, String> recherche = new Hashtable<>();
	/**
	 * Permet de creer un JPanel comportant des infos sur les projets et l'accès aux differentes fonctionalites sur les projets.
	 * @param pliste La liste comportant les projets à afficher.
	 * @param entetes La liste comportant les entetes à afficher.
	 * @param pparent La fenetre mère du JPannel qui sera retourne.
	 * @return Un JPanel comportant des infos sur les projets et l'accès aux differentes fonctionalites sur les projets.
	 */
	public static JPanel affiJPanel(final ArrayList<Projets> pliste,final String[]entetes, JFrame pparent){
		parent = pparent;
		liste = pliste;
		
		// Filtrage
		if(recherche.containsKey("attribut")) {
			String attribut = recherche.get("attribut");
			String valeur = recherche.get("valeur");
			ArrayList<Projets> temp = new ArrayList<Projets>();
			
			for(int i=0;i<liste.size();i++){
				if(liste.get(i).getDonnees().get(attribut).toLowerCase().contains(valeur.toLowerCase())) {
					temp.add(liste.get(i));
				}	
			}
			liste = temp;
		}
		
		//creation des JPanel
		JPanel panEntete= new  JPanel();
		JPanel panTable = new JPanel();
		JPanel panBouton= new JPanel();
		JPanel panComplet = new JPanel();
		
		//mise en place des gestionnaires de placements
		panComplet.setLayout(new BorderLayout());
		panBouton.setLayout(new FlowLayout());
		panTable.setLayout(new BorderLayout());
		panEntete.setLayout(new BorderLayout());
		panEntete.setLayout(new BorderLayout());
		
		//creation des boutons 
		JButton cloner = new JButton("Cloner le Projet");
		final JButton suprimer= new JButton("Supprimer le Projet");
		final JButton modifier= new JButton("Modifier le Projet");
		final JButton ajout = new JButton("Ajouter un Projet");
		final JButton rechercher = new JButton("Rechercher");
		final JButton supRechercher = new JButton("Annuler la recherche");
		
		//stocke les info sur les projets dans un tableau d'object
		Object[][] date= new Object[liste.size()][entetes.length];

		String temp="";
		for(int i=0;i<liste.size();i++){
			for(int j=0;j<entetes.length;j++){
				temp=liste.get(i).getDonnees().get(entetes[j]);
				date[i][j]=temp;	
			}	
		}
		
		//creation d'un table contenants les infos sur les Projets ( groupe,sujet)
		final DefaultTableModel model= new DefaultTableModel(date,entetes);
		final JTable table= new JTable(model){
			//redefinition de la methode isCelleEditable pour empecher la modification des cellule
			public boolean isCellEditable(int row, int column) {
					return false;
			} 
		};
		
		//mise en place du scroll
		JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    panTable.add(scrollPane);
		
	    //mise en place de la table
		panEntete.add(panTable,BorderLayout.CENTER);
		table.setVisible(true);
		panComplet.add(panEntete);
		
		//mise en place des boutons
		panBouton.add(ajout);
		panBouton.add(modifier);
		panBouton.add(suprimer);
		panBouton.add(cloner);
		
		panBouton.add(new JLabel(" | "));
		
		panBouton.add(rechercher);
		panBouton.add(supRechercher);
		
		panComplet.add(panBouton,BorderLayout.SOUTH);
		
		//action sur le bouton cloner:
		cloner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	                int index= table.getSelectedRow();
	                if ( index != -1 ){ //si un ligne est selectionner :
	                	if(JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(null,"<html>Vous êtes sur le point de dupliquer le projets <FONT COLOR=\"red\">"+
	                			liste.get(index).getDonnees().get("groupe")+" "+
	                			liste.get(index).getDonnees().get("sujet")+
	                															"</font> de la liste.<br>Continuer ?</html>","Dupplication",JOptionPane.YES_NO_OPTION)){
	                		Object[]rowData = new Object[entetes.length];
	                		for(int i=0;i<entetes.length;i++){
	                			rowData[i]=liste.get(index).getDonnees().get(entetes[i]);
	                		}
	                		model.addRow(rowData);
	                		model.fireTableDataChanged();
	                		Controleur.clonerProjet(liste.get(index));
	                	}
	                  	//modifier le fichier csv
	                }else{ //sinon, message d'erreur
	                	JOptionPane.showMessageDialog(null, "Veuillez selectionner un projets svp");	
	                }
			}
		});
		
		//action sur le bouton ajout
		ajout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Hashtable<String, String> donnees = new Hashtable<String, String>();
				
				String[] attributs = Controleur.attributProjet();
				for(int k=0;k<attributs.length;k++) {
					donnees.put(attributs[k], "");
				}
								
				Enregistrement nouveauProj = new Enregistrement(donnees, parent, "Ajouter un projet", "projet");
			}
		});
		
		//action sur le boutton supprimer
		suprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                int index= table.getSelectedRow();
                if ( index != -1 ){ //si un ligne est selectionner :
                	if(JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(null,"<html>Vous êtes sur le point de supprimer le projets <FONT COLOR=\"red\">"+
                			liste.get(index).getDonnees().get("groupe")+" "+
                			liste.get(index).getDonnees().get("sujet")+
                															"</FONT> de la liste.<br>Continuer?</html>","Suppression",JOptionPane.YES_NO_OPTION)){
                		model.removeRow(index);
                		model.fireTableDataChanged();
                		Controleur.supprimerProjet(liste.get(index));
                		FenetrePrincipale.Maj();
                	}
                  	//modifier le fichier csv
                }else{ //sinon, message d'erreur
                	JOptionPane.showMessageDialog(null, "Veuillez selestionner un projet svp");
                }	
			}
		});
		
		modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
                int index= table.getSelectedRow();
                if ( index != -1 ){ //si une ligne est selectionner :
                	Enregistrement modifInt = new Enregistrement(liste.get(index).getDonnees(), parent, "Modifier un projet");
                }else{ //sinon, message d'erreur
                	JOptionPane.showMessageDialog(null, "Veuillez selestionner un projet svp");	
                }  
			}
		});
		
		rechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Recherche fenêtre = new Recherche(recherche, entetes, parent);
			}
		});
		
		supRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recherche.clear();
				FenetrePrincipale.Maj();
			}
		});
		
		return panComplet;
	}
}