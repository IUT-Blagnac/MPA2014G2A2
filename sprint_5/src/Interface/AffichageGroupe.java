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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controleur.Controleur;
import Modele.Etudiants;
import Modele.Intervenants;

public class AffichageGroupe {
	private static JLabel labelsujet;
	private static JTable tableauEtu;
	private static JTable tableauInter;
	private static JButton bouton[];
	private static JPanel interTable;
	private static JPanel EtuTable;
	
	/**
	 * Permet de creer un JPanel comportant des infos sur les groupes et l'acces aux differentes fonctionalites sur les groupes.
	 * @param fenêtre La fenetre mere du JPannel qui sera retourne.
	 * @param listeGroupe Le tableau de String comportant les infos sur les groupes.
	 * @return Le JPanel comportant des infos sur les groupes et l'acces aux differentes fonctionalites sur les groupes.
	 */
	public static JPanel affiJPanel(JFrame fenêtre, final String[][] listeGroupe) {

		JPanel conteneur = new JPanel(new BorderLayout());
		JPanel panGroupe = new JPanel();
		JPanel panTables = new JPanel(new BorderLayout());

		
		
		// Panel qu'on ajoutera dans panTables
		JPanel petitCentre = new JPanel();
		JPanel sujet = new JPanel();
		JPanel inter = new JPanel(new BorderLayout());
		JPanel etu = new JPanel(new BorderLayout());

		interTable = new JPanel();
		EtuTable = new JPanel();

		panTables.add(sujet, BorderLayout.NORTH);
		panTables.add(petitCentre, BorderLayout.CENTER);

		// Ajout des panel etu et inter dans petitCentre
		petitCentre.add(etu, BorderLayout.EAST);
		petitCentre.add(inter, BorderLayout.WEST);

		// etu.setBackground(Color.CYAN);
		// inter.setBackground(Color.black);

		// Creation et ajout de panels dans les panel etu et inter

		JPanel titleEtu = new JPanel();
		JPanel titleInter = new JPanel();

		etu.add(titleEtu, BorderLayout.NORTH);
		inter.add(titleInter, BorderLayout.NORTH);
		
		etu.add(EtuTable, BorderLayout.CENTER);
		inter.add(interTable, BorderLayout.CENTER);

		EtuTable.setLayout(new BorderLayout());
		interTable.setLayout(new BorderLayout());

		titleEtu.add(new JLabel("Etudiants"));
		titleInter.add(new JLabel("Intervenants"));

		// Creation d'une liste de boutons dans panGroupe

		bouton = new JButton[listeGroupe[0].length];
		GridLayout gdlay=new GridLayout(listeGroupe[0].length,1);
		panGroupe.setLayout(gdlay);
		
		//gdlay.setVgap(5);
		

		
		
	   // panGroupe.add(scrollPane);
		
		
		for (int i = 0; i < listeGroupe[0].length; i++) {
			
			bouton[i] = new JButton(listeGroupe[0][i]);
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
					
					select_groupe(listeGroupe[0][selected], listeGroupe[1][selected]);
				}
			});
			JPanel instapan=new JPanel();
			
			panGroupe.add(instapan);
			instapan.add(bouton[i]);
		}
		
		JScrollPane scrollPane = new JScrollPane(panGroupe);
		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// Creation de Jtable dans EtuTable

		Object[][] data2 = {};
		// Les titres des colonnes
		String titreEtu[] = { "" };

		final DefaultTableModel model2 = new DefaultTableModel(data2, titreEtu);
		tableauEtu = new JTable(model2) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		EtuTable.add(new JScrollPane(tableauEtu));

		// Creation de Jtable dans interTable

		Object[][] data3 = {};
		// Les titres des colonnes
		String titreInter[] = { "" };

		final DefaultTableModel model3 = new DefaultTableModel(data3,
				titreInter);
		tableauInter = new JTable(model3) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		interTable.add(new JScrollPane(tableauInter));

		// On travail sur petitCentre
		petitCentre.setLayout(new GridLayout(1, 2));

		sujet.setBackground(new Color(200,221,242));
		labelsujet = new JLabel("Selectionnez un groupe");
		labelsujet.setFont(new Font("Arial", Font.PLAIN, 16));
		sujet.add(labelsujet);
		
		conteneur.add(scrollPane, BorderLayout.WEST);
		
		// panGroupe.setPreferredSize(new Dimension(50, 0));
		conteneur.add(panTables, BorderLayout.CENTER);

		return conteneur;
	}
	
	
	
	/**
	 * Permet d'obtenir Le tableau de JButton qui contient les differents groupes.
	 * @return Le tableau de JButton qui contient les differents groupes.
	 */
	public static JButton[] getJBouton(){
		return bouton;
	}
	
	
	/**
	 * Restreind l'affichage des autres donnees en fonction du groupe
	 * @param nom Le nom du groupe selectionne.
	 * @param sujet Le sujet du groupe selectionne. 
	 */
	public static void select_groupe(String nom, String sujet) {
		
		String numVoeu = Controleur.positionSujetGroupe(nom);
		if(numVoeu != "") {
			labelsujet.setText(sujet + " (voeu numero " + numVoeu + ")");
		} else {
			labelsujet.setText(sujet);
		}
		
		ArrayList<Etudiants> etu = Controleur.getEtudiantsGroupe(nom);
		String[] inte = Controleur.getIntervenantsGroupe(nom);
		
		/* LISTE ETUDIANTS */
		
		String[] entetes = {"nom", "prenom"};
		Object[][] etuData = new Object[etu.size()][entetes.length+1];
		String temp="";
		for(int i=0;i<etu.size();i++){
			for(int j=0;j<entetes.length;j++){
				temp = etu.get(i).getDonnees().get(entetes[j]);
				etuData[i][j] = temp;
			}
		}
		
		final DefaultTableModel etuModel = new DefaultTableModel(etuData, entetes);
		tableauEtu = new JTable(etuModel){
			public boolean isCellEditable(int row, int column) {
					return false;
			} 
		};
		
		/* LISTE INTERVENANTS */
		
		String[] entetes2 = {"nom", "prenom", "role"};
		Object[][] inteData = new Object[3][entetes2.length+1];
		temp="";
		for(int i=0;i<3;i++){
			for(int j=0;j<entetes2.length-1;j++){
				if(inte[i] != "") {
					temp=Controleur.findIntervenant(inte[i]).getDonnees().get(entetes2[j]);
					inteData[i][j] = temp;
				} else {
					inteData[i][j] = "";
				}
			}
		}
		
		inteData[0][2] = "client";
		inteData[1][2] = "superviseur";
		inteData[2][2] = "support_technique";
		
		final DefaultTableModel inteModel = new DefaultTableModel(inteData, entetes2);
		tableauInter = new JTable(inteModel){
			public boolean isCellEditable(int row, int column) {
					return false;
			} 
		};
		
		interTable.removeAll();
		interTable.add(new JScrollPane(tableauInter));
		
		EtuTable.removeAll();
		EtuTable.add(new JScrollPane(tableauEtu));
	}

}
