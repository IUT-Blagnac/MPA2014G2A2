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
import Modele.Sujets;

public class AffichageEtu extends DefaultTableCellRenderer{
	private static JFrame parent;
	private static ArrayList<Etudiants> liste;
	private static Hashtable<String, String> recherche = new Hashtable<>();
	/**
	 * Permet de creer un JPanel comportant des infos sur les etudiants et l'accès aux differentes fonctionalites sur les etudiants.
	 * @param pliste La liste comportant les Etudiants à afficher.
	 * @param entetes La liste comportant les entetes à afficher.
	 * @param pparent La fenetre mère du JPanel qui sera retourne.
	 * @return Le JPanel comportant les infos sur les etudiants et l'accès aux differentes fonctionalites.
	 */
	public static JPanel affiJPanel(ArrayList<Etudiants> pliste, final String[] entetes, JFrame pparent){
		parent = pparent;
		liste = pliste;
		
		// Filtrage
		if(recherche.containsKey("attribut")) {
			String attribut = recherche.get("attribut");
			String valeur = recherche.get("valeur");
			ArrayList<Etudiants> temp = new ArrayList<Etudiants>();
			
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
		
		//mise en place des gestionnaire de placements
		panComplet.setLayout(new BorderLayout());
		panTable.setLayout(new BorderLayout());
		panEntete.setLayout(new BorderLayout());
		panEntete.setLayout(new BorderLayout());
		panBouton.setLayout(new FlowLayout());
		
		//creation des boutons ajout et supprimer
		final JButton buttonRemRow = new JButton("Supprimer l'etudiant");
		final JButton buttonAddRow = new JButton("Ajouter un etudiant");
		final JButton buttonEditRow = new JButton("Modifier l'etudiant");
		final JButton afficherGroupe = new JButton("Afficher le groupe");
		final JButton rechercher = new JButton("Rechercher");
		final JButton supRechercher = new JButton("Annuler la recherche");
		
		//stocke les info sur les etudiant dans un tableau d'object
		Object [][]date= new Object[liste.size()][entetes.length+1];
		String temp="";
		for(int i=0;i<liste.size();i++){
			for(int j=0;j<entetes.length;j++){
				temp=liste.get(i).getDonnees().get(entetes[j]);
				date[i][j]=temp;
			}
		}
		//creation d'un table contenants les infos sur les etudiant ( groupe,nom,prenom)
		final DefaultTableModel model= new DefaultTableModel(date,entetes);
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
	                																"</FONT> de la liste.<br>Continuer ?</html>","Suppression",JOptionPane.YES_NO_OPTION)){
	                		model.removeRow(index);
	                		model.fireTableDataChanged();
	                		Controleur.supprimerEtudiant(liste.get(index));	
	                	}
	                	//modifier le fichier csv
	                }else{ //sinon, message d'erreur
	                	JOptionPane.showMessageDialog(null, "Veuillez selectionner un etudiant svp");	
	                }  
	            }
	        });
		
		afficherGroupe.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                int index= table.getSelectedRow();
                if ( index != -1 ){ //si une ligne est selectionner :                	
                	FenetrePrincipale.getOnglet().setSelectedIndex(4);
                	AffichageGroupe.getJBouton()[indiceGroupe(table)].doClick();
                		  	
                }else{ //sinon, message d'erreur
                	JOptionPane.showMessageDialog(null, "Veuillez selectionner un etudiant svp");	
                }  
            }
        });
		
		//ajout d'un etudiant --> a implementer
		buttonAddRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Hashtable<String, String> donnees = new Hashtable<String, String>();
				
				String[] attributs = Controleur.attributEtudiant();
				for(int k=0;k<attributs.length;k++) {
					donnees.put(attributs[k], "");
				}
								
				Enregistrement nouveauEtudiant = new Enregistrement(donnees, parent, "Ajouter un etudiant", "etudiant");
			}
		});
		
		buttonEditRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
                int index= table.getSelectedRow();
                if ( index != -1 ){ //si une ligne est selectionner :
                	Enregistrement modifEtudiant = new Enregistrement(liste.get(index).getDonnees(), parent, "Modifier un etudiant");
                }else{ //sinon, message d'erreur
                	JOptionPane.showMessageDialog(null, "Veuillez selectionner un etudiant svp");	
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
		
		//mise en place des bouton ajout et suppression
		panBouton.add(buttonAddRow);
		panBouton.add(buttonEditRow);
		panBouton.add(buttonRemRow);
		
		panBouton.add(new JLabel(" | "));
		
		panBouton.add(afficherGroupe);
		
		panBouton.add(new JLabel(" | "));
		
		panBouton.add(rechercher);
		panBouton.add(supRechercher);
		
		panComplet.add(panBouton,BorderLayout.SOUTH);
		
		return panComplet;
	}
	/**
	 * Permet d'obtenir l'indice d'un groupe en fonction de l'etudiant selectionne.
	 * @param table La JTable comportant les infos sur un etudiant.
	 * @return
	 */
	public static int indiceGroupe(JTable table){
		int indice=0;
		for(int i=0; i<AffichageGroupe.getJBouton().length; i++){
			if(AffichageGroupe.getJBouton()[i].getText().equals(table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()))){
			indice=i;
			}
		}
		return indice;
	}
}
