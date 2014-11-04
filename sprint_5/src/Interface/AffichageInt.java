package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controleur.Controleur;
import Modele.Intervenants;
import Modele.Projets;
import Modele.Sujets;

public class AffichageInt {
	private static JFrame parent;
	private static ArrayList<Intervenants> liste;
	
	private static JLabel labelsujet;
	private static JTable tableauProj;
	private static JButton bouton[];
	private static JPanel panProjets;
	private static Intervenants selected = null;
	private static Hashtable<String, String> recherche = new Hashtable<>();
	private static DefaultTableModel projModel;
	
	public static JPanel affiJPanel(final ArrayList<Intervenants> pliste,final String[]entetes, JFrame pparent) {
		parent = pparent;
		liste = pliste;
		
		// Filtrage
		if(recherche.containsKey("attribut")) {
			String attribut = recherche.get("attribut");
			String valeur = recherche.get("valeur");
			ArrayList<Intervenants> temp = new ArrayList<Intervenants>();
			
			for(int i=0;i<liste.size();i++){
				if(liste.get(i).getDonnees().get(attribut).toLowerCase().contains(valeur.toLowerCase())) {
					temp.add(liste.get(i));
				}	
			}
			liste = temp;
		}
		
		//creation des boutons ajout et supprimer
		final JButton suprimer= new JButton("Supprimer l'intervenant");
		final JButton ajout = new JButton("Ajouter un intervenant");
		final JButton modifier = new JButton("Modifier l'intervenant");
	
		final JButton rechercher = new JButton("Rechercher");
		final JButton supRechercher = new JButton("Annuler la recherche");
		
		JPanel conteneur = new JPanel(new BorderLayout());
		JPanel panGroupe = new JPanel();
		JPanel panTables = new JPanel(new BorderLayout());

		JPanel panBouton= new JPanel();
		panBouton.setLayout(new FlowLayout());
		
		JScrollPane scrollPane = new JScrollPane(panGroupe);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	   // panGroupe.add(scrollPane);
		
		// Panel qu'on ajoutera dans panTables
		JPanel petitCentre = new JPanel();
		JPanel intervenant = new JPanel();
		panProjets = new JPanel(new BorderLayout());

		panTables.add(intervenant, BorderLayout.NORTH);
		panTables.add(petitCentre, BorderLayout.CENTER);
		
		//mise en place des boutons
		panBouton.add(ajout);
		panBouton.add(modifier);
		panBouton.add(suprimer);
		
		panBouton.add(new JLabel(" | "));
		
		panBouton.add(rechercher);
		panBouton.add(supRechercher);

		// Ajout des panel projet dans petitCentre
		petitCentre.add(panProjets, BorderLayout.CENTER);

		// Creation d'une liste de boutons dans panGroupe

		bouton = new JButton[liste.size()];
		
		
		GridLayout gdlay=new GridLayout(liste.size(),1);
		panGroupe.setLayout(gdlay);
		for (int i = 0; i < liste.size(); i++) {
			
			bouton[i] = new JButton(liste.get(i).getDonnees().get("prenom") + " " + liste.get(i).getDonnees().get("nom"));
			bouton[i].setBackground(new Color(200,221,242));
			bouton[i].setPreferredSize(new Dimension(180,30));
			
			bouton[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg) {
					for (int j = 0; j < bouton.length; j++) {
						bouton[j].setBackground(new Color(200,221,242));
					}
					JButton source=(JButton) arg.getSource();
					source.setBackground(Color.white);
					int selected = 0;
					for(int i=0;i<bouton.length;i++) {
						if(bouton[i] == arg.getSource()) {
							selected = i;
						}
					}
					
					select_inter(liste.get(selected));
				}
			});
			
			JPanel instapan=new JPanel();
			
			panGroupe.add(instapan);
			instapan.add(bouton[i]);
		}

		// On travail sur petitCentre
		petitCentre.setLayout(new BoxLayout(petitCentre, BoxLayout.PAGE_AXIS));

		intervenant.setBackground(new Color(200,221,242));
		labelsujet = new JLabel("Selectionnez un intervenant");
		labelsujet.setFont(new Font("Arial", Font.PLAIN, 16));
		intervenant.add(labelsujet);
		
		conteneur.add(scrollPane, BorderLayout.WEST);
		// panGroupe.setPreferredSize(new Dimension(50, 0));
		conteneur.add(panTables, BorderLayout.CENTER);
		conteneur.add(panBouton,BorderLayout.SOUTH);

		///////////////////////////////////////
		///////// * ACTIONLISTENER * //////////
		///////////////////////////////////////
		
		ajout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Hashtable<String, String> donnees = new Hashtable<String, String>();
				
				String[] attributs = Controleur.attributIntervenant();
				for(int k=0;k<attributs.length;k++) {
					donnees.put(attributs[k], "");
				}
				
				Enregistrement nouveauInter = new Enregistrement(donnees, parent, "Nouvel intervenant", "intervenant");
			}
		});
		
		suprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                if (selected != null){ //si un intervenant est selectionner :
                	if(JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(null,"<html>Vous êtes sur le point de supprimer l'intervenants  <FONT COLOR=\"red\">"
                			+ selected.getDonnees().get("nom")
                			+ "</FONT>.<br>Continuer ?</html>","Suppression",JOptionPane.YES_NO_OPTION)){
                		Controleur.supprimerIntervenant(selected);
                		FenetrePrincipale.Maj();
                	}
                }else{ //sinon, message d'erreur
                	JOptionPane.showMessageDialog(null, "Veuillez selectionner un intervenant");
                }
			}
		});
		
		modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
                if (selected != null){ //si un intervenant est selectionner :
                	Enregistrement modifInt = new Enregistrement(selected.getDonnees(), parent, "Modifier un intervenant");
                }else{ //sinon, message d'erreur
                	JOptionPane.showMessageDialog(null, "Veuillez selectionner un intervenant");	
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
		
		if(selected != null) {
			select_inter(selected);
		}
		
		return conteneur;
	}
	
	public static void select_inter(Intervenants inter) {
		labelsujet.setText(inter.getDonnees().get("prenom") + " " + inter.getDonnees().get("nom"));
		
		selected = inter;
		
		/* Creation des donnees pour afficher les projets */
		String[] entetes = {"id", "groupe", "sujet", "role"};
		
		Object[][] projData = Controleur.intervenantPlus(selected);
		
		/* Affichage du tableau */
		projModel = new DefaultTableModel(projData, entetes);
		tableauProj = new JTable(projModel){
			public boolean isCellEditable(int row, int column) {
					return false;
			} 
		};
		
		panProjets.removeAll();
		panProjets.add(new JScrollPane(tableauProj));
	}

}