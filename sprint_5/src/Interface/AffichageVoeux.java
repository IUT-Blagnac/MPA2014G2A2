package Interface;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import Controleur.Controleur;
import Modele.Etudiants;
import Modele.Projets;
import Modele.Voeux;

public class AffichageVoeux extends DefaultTableCellRenderer{
	private static JFrame parent;
	private static ArrayList<Voeux> liste;
	private static Hashtable<String, String> recherche = new Hashtable<>();
	/**
	 * Permet de creer un JPanel comportant des infos sur les voeux et l'acces aux differentes fonctionalites sur les voeux.
	 * @param pliste La liste comportant les voeux à afficher.
	 * @param p_parent La fenetre mere du JPannel qui sera retourne.
	 * @return Un JPanel comportant des infos sur les voeux et l'acces aux differentes fonctionalites sur les voeux.
	 */
	public static JPanel affiJPanel(final ArrayList<Voeux> pliste, JFrame p_parent){
		parent = p_parent;
		liste = pliste;
		
		// Filtrage
		if(recherche.containsKey("attribut")) {
			String attribut = recherche.get("attribut");
			String valeur = recherche.get("valeur");
			ArrayList<Voeux> temp = new ArrayList<Voeux>();
			
			for(int i=0;i<liste.size();i++){
				if(liste.get(i).getDonnees().get(attribut).equals(valeur)) {
					temp.add(liste.get(i));
				}	
			}
			liste = temp;
		}
		
		final String[] entete = Controleur.attributVoeux();
		
		//creation des JPanel
		JPanel panEntete= new  JPanel();
		JPanel panTable = new JPanel();
		JPanel panBouton= new JPanel();
		JPanel panComplet = new JPanel();
		
		//mise en place des gestionnaire de placements
		panComplet.setLayout(new BorderLayout());
		panTable.setLayout(new BorderLayout());
		panEntete.setLayout(new BorderLayout());
		panEntete.setLayout(new BorderLayout());
		panBouton.setLayout(new FlowLayout());
		
		//creation des boutons ajout et supprimer
		final JButton buttonRemRow = new JButton("Supprimer le voeu");
		final JButton buttonAddRow = new JButton("Ajouter un voeu");
		final JButton buttonEditRow = new JButton("Modifier le voeu");
		final JButton rechercher = new JButton("Rechercher");
		final JButton supRechercher = new JButton("Annuler la recherche");
		final JButton classer = new JButton("Classer les voeux pour ce sujet");
		final JButton supClasser = new JButton("Supprimer le classement");
		final JButton preaffecter = new JButton("Pre-affecter les sujets");
		
		//stocke les info sur les etudiant dans un tableau d'object
		Object[][] data= new Object[liste.size()][entete.length+1];
		String temp="";
		for(int i=0;i<liste.size();i++){
			for(int j=0;j<entete.length;j++){
				temp=liste.get(i).getDonnees().get(entete[j]);
				data[i][j]=temp;
			}
		}
		//creation d'un table contenants les infos sur les etudiant ( groupe,nom,prenom)
		final DefaultTableModel model= new DefaultTableModel(data,entete);
		final JTable table= new JTable(model){
			//redefinition de la methode isCelleEditable pour empecher la modification des cellule
			public boolean isCellEditable(int row, int column) {
					return false;
			} 
		};
	
		//ajout du scroll
		JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    panTable.add(scrollPane);
		
	    //mise en place de la table
		panEntete.add(panTable,BorderLayout.CENTER);
		table.setVisible(true);
		panComplet.add(panEntete);
		
		//suppression d'un etudiant
		buttonRemRow.addActionListener(new ActionListener() {
	            public void actionPerformed(final ActionEvent e) {
	                int index= table.getSelectedRow();
	                if ( index != -1 ){ //si une ligne est selectionner :
	                	if(JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(null,"<html>Vous êtes sur le point de supprimer <FONT COLOR=\"red\">"+ 
	                																liste.get(index).getDonnees().get("nom")+" "+ 
	                																liste.get(index).getDonnees().get("prenom")+
	                																"</FONT> de la liste.<br>Continuer ?</html>","suppression",JOptionPane.YES_NO_OPTION)){
	                		model.removeRow(index);
	                		model.fireTableDataChanged();
	                		Controleur.supprimerVoeux(liste.get(index));	
	                	}
	                	//modifier le fichier csv
	                }else{ //sinon, message d'erreur
	                	JOptionPane.showMessageDialog(null, "Veuillez selectionner un voeu");	
	                }  
	            }
	        });
		
		//ajout d'un etudiant --> a implementer
		buttonAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Hashtable<String, String> donnees = new Hashtable<String, String>();
				
				String[] attributs = Controleur.attributVoeux();
				for(int k=0;k<attributs.length;k++) {
					donnees.put(attributs[k], "");
				}
								
				Enregistrement nouveauVoeu = new Enregistrement(donnees, parent, "Ajouter un voeu", "voeu");
			}
		});
		
		buttonEditRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
                int index= table.getSelectedRow();
                if ( index != -1 ){ //si une ligne est selectionner :
                	Enregistrement modifVoeu = new Enregistrement(liste.get(index).getDonnees(), parent, "Modifier un voeu");
                }else{ //sinon, message d'erreur
                	JOptionPane.showMessageDialog(null, "Veuillez selectionner un voeu");	
                }  
			}
		});
		
		classer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
                int index= table.getSelectedRow();
                if ( index != -1 ){ //si une ligne est selectionner :
                	liste=Controleur.voeuxSujetClasses(table.getValueAt(table.getSelectedRow(),1).toString());
                	FenetrePrincipale.getOnglet().remove(5);
                	FenetrePrincipale.getOnglet().add(AffichageVoeux.affiJPanel(liste, FenetrePrincipale.getFenetre()), "Voeux");
                	FenetrePrincipale.getOnglet().setSelectedIndex(5);
                }else{ //sinon, message d'erreur
                	JOptionPane.showMessageDialog(null, "Veuillez selectionner un voeu");	
                }  
			}
		});
		
		supClasser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
                FenetrePrincipale.Maj();
			}
		});
		
		preaffecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
                Controleur.preaffectationSujets();
                FenetrePrincipale.Maj();
			}
		});
		
		rechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Recherche fenêtre = new Recherche(recherche, entete, parent);
			}
		});
		
		supRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				recherche.clear();
				FenetrePrincipale.Maj();
			}
		});
		
		//mise en place des bouton ajout et suppression
		panBouton.add(buttonAddRow);
		panBouton.add(buttonEditRow);
		panBouton.add(buttonRemRow);

		panBouton.add(new JLabel(" | "));
		
		panBouton.add(classer);
		panBouton.add(supClasser);
		panBouton.add(preaffecter);
		
		panBouton.add(new JLabel(" | "));

		panBouton.add(rechercher);
		panBouton.add(supRechercher);
		
		panComplet.add(panBouton,BorderLayout.SOUTH);
		
		return panComplet;
	}


}
