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
import Modele.Sujets;

public class AffichageSuj {
	private static JFrame parent;
	private static ArrayList<Sujets> liste;
	
	private static JLabel labelsujet;
	private static JTable tableauSuj;
	private static JTable tableauVoeux;
	private static JButton bouton[];
	private static JPanel VoeuxTable;
	private static JPanel SujTable;
	private static JLabel titreSujet = new JLabel("");
	private static Sujets selected = null;
	private static Hashtable<String, String> recherche = new Hashtable<>();
	
	/**
	 * Permet de créer un JPanel comportant des infos sur les sujets et l'accès aux différentes fonctionalités sur les sujets.
	 * @param pliste La liste comportant les sujets à afficher.
	 * @param entetes La liste comportant les entetes à afficher.
	 * @param pparent La fenetre mère du JPannel qui sera retourné.
	 * @return Un JPanel comportant des infos sur les sujets et l'accès aux différentes fonctionalités sur les sujets.
	 */
	public static JPanel affiJPanel(final ArrayList<Sujets> pliste,final String[]entetes, JFrame pparent) {
		parent = pparent;
		liste = pliste;
		
		// Filtrage
		if(recherche.containsKey("attribut")) {
			String attribut = recherche.get("attribut");
			String valeur = recherche.get("valeur");
			ArrayList<Sujets> temp = new ArrayList<Sujets>();
			
			for(int i=0;i<liste.size();i++){
				if(liste.get(i).getDonnées().get(attribut).toLowerCase().contains(valeur.toLowerCase())) {
					temp.add(liste.get(i));
				}	
			}
			liste = temp;
		}
		
		//creation des boutons ajout et supprimer
		final JButton suprimer= new JButton("Supprimer le sujet");
		final JButton ajout = new JButton("Ajouter un Sujet");
		final JButton modifier = new JButton("Modifier le Sujet");
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
		JPanel sujet = new JPanel();
		JPanel Voeux = new JPanel(new BorderLayout());
		JPanel Suj = new JPanel(new BorderLayout());

		VoeuxTable = new JPanel();
		SujTable = new JPanel();

		panTables.add(sujet, BorderLayout.NORTH);
		panTables.add(petitCentre, BorderLayout.CENTER);
		
		//mise en place des boutons
		panBouton.add(ajout);
		panBouton.add(modifier);
		panBouton.add(suprimer);
		
		panBouton.add(new JLabel(" | "));
		
		panBouton.add(rechercher);
		panBouton.add(supRechercher);

		// Ajout des panel Suj et Voeux dans petitCentre
		petitCentre.add(Suj, BorderLayout.EAST);
		petitCentre.add(Voeux, BorderLayout.WEST);

		// Suj.setBackground(Color.CYAN);
		// Voeux.setBackground(Color.black);

		// Creation et ajout de panels dans les panel Suj et Voeux

		JPanel dataSuj = new JPanel();
		JPanel titleVoeux = new JPanel();

		Suj.add(dataSuj, BorderLayout.NORTH);
		Voeux.add(titleVoeux, BorderLayout.NORTH);
		
		Suj.add(SujTable, BorderLayout.CENTER);
		Voeux.add(VoeuxTable, BorderLayout.CENTER);

		SujTable.setLayout(new BorderLayout());
		VoeuxTable.setLayout(new BorderLayout());

		dataSuj.add(titreSujet);
		titleVoeux.add(new JLabel("Classement des voeux pour ce sujet"));
		titleVoeux.setBackground(new Color(200,221,242));

		// Creation d'une liste de boutons dans panGroupe

		bouton = new JButton[liste.size()];

		
		bouton = new JButton[liste.size()];
		GridLayout gdlay=new GridLayout(liste.size(),1);
		panGroupe.setLayout(gdlay);
		
		
		for (int i = 0; i < liste.size(); i++) {
			
			bouton[i] = new JButton(liste.get(i).getDonnées().get("nom"));
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
					
					select_sujet(liste.get(selected));
				}
			});
			
			JPanel instapan=new JPanel();
			
			panGroupe.add(instapan);
			instapan.add(bouton[i]);
		}

		// Creation de Jtable dans VoeuxTable

		Object[][] data3 = {};
		// Les titres des colonnes
		String titreVoeux[] = { "" };

		final DefaultTableModel model3 = new DefaultTableModel(data3,
				titreVoeux);
		tableauVoeux = new JTable(model3) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		VoeuxTable.add(new JScrollPane(tableauVoeux));

		// On travail sur petitCentre
		petitCentre.setLayout(new BoxLayout(petitCentre, BoxLayout.PAGE_AXIS));

		sujet.setBackground(new Color(200,221,242));
		labelsujet = new JLabel("Sélectionnez un sujet");
		labelsujet.setFont(new Font("Arial", Font.PLAIN, 16));
		sujet.add(labelsujet);
		
		conteneur.add(scrollPane, BorderLayout.WEST);
		// panGroupe.setPreferredSize(new Dimension(50, 0));
		conteneur.add(panTables, BorderLayout.CENTER);
		conteneur.add(panBouton,BorderLayout.SOUTH);

		///////////////////////////////////////
		///////// * ACTIONLISTENER * //////////
		///////////////////////////////////////
		
		ajout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Hashtable<String, String> données = new Hashtable<String, String>();
				
				String[] attributs = Controleur.attributSujet();
				for(int k=0;k<attributs.length;k++) {
					données.put(attributs[k], "");
				}
				
				Enregistrement nouveauSujet = new Enregistrement(données, parent, "Nouveau sujet", "sujet");
			}
		});
		
		suprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                if (selected != null){ //si un sujet est selectionner :
                	if(JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(null,"<html>Vous êtes sur le point de supprimer le sujets  <FONT COLOR=\"red\">"
                			+ selected.getDonnées().get("nom")
                			+ "</FONT>.<br>Continuer ?</html>","Suppression",JOptionPane.YES_NO_OPTION)){
                		Controleur.supprimerSujet(selected);
                		FenetrePrincipale.Maj();
                	}
                }else{ //sinon, message d'erreur
                	JOptionPane.showMessageDialog(null, "Veuillez selectionner un sujet");
                }
			}
		});
		
		modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
                if (selected != null){ //si un sujet est selectionner :
                	Enregistrement modifInt = new Enregistrement(selected.getDonnées(), parent, "Modifier un sujet");
                }else{ //sinon, message d'erreur
                	JOptionPane.showMessageDialog(null, "Veuillez selestionner un sujet svp");	
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
			select_sujet(selected);
		}
		
		return conteneur;
	}
	/**
	 * Permet de restreindre les donnés selectionnés en fonction du sujet selectioné.
	 * @param sujet Le sujet selectionné.
	 */
	public static void select_sujet(Sujets sujet) {
		labelsujet.setText("#" + sujet.getDonnées().get("id") + " " + sujet.getDonnées().get("nom"));
		titreSujet.setText(sujet.getDonnées().get("titre"));
		
		selected = sujet;
		
		/* VOEUX */
		
		String[][] nb_voeux = Controleur.compterVoeuxParPosition(sujet.getDonnées().get("id"));
		
		String[] entetes = {"position", "nombre de voeux voeux"};
		Object[][] sujData = new Object[nb_voeux.length][entetes.length+1];

		for(int j=0;j<nb_voeux.length;j++) {
			for(int i=0;i<entetes.length;i++){
				sujData[j][i] = nb_voeux[j][i];
			}
		}
			
		final DefaultTableModel sujModel = new DefaultTableModel(sujData, entetes);
		tableauSuj = new JTable(sujModel){
			public boolean isCellEditable(int row, int column) {
					return false;
			} 
		};
		
		VoeuxTable.removeAll();
		VoeuxTable.add(new JScrollPane(tableauSuj));
	}

}
