package Interface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controleur.Controleur;
import Modele.Intervenants;
import Modele.Projets;
import Modele.Sujets;

public class Enregistrement extends JDialog {
	
	private Hashtable<String, String> table;
	private JPanel contenu = new JPanel();
	private JPanel panel_champs = new JPanel();
	private ArrayList<String> attributs = new ArrayList<String>();
	private Object[] champs;
	private JDialog jd;
	
	Enregistrement(Hashtable<String, String> sortie, JFrame parent, String titre, final String type) {
		super(parent, titre, true);
		this.setMinimumSize(new Dimension(300, 500));
		this.setLocationRelativeTo(null);
		
		this.jd = this;
		this.table = sortie;
		
		/// *** Liste des attributs *** \\\
		Iterator<String> it = sortie.keySet().iterator();
		while(it.hasNext()) {
			attributs.add(it.next());
		}
		
		this.champs = new Object[attributs.size()];
		this.add(contenu, BorderLayout.CENTER);
		
		contenu.add(panel_champs);
		
		contenu.setLayout(new FlowLayout());
		panel_champs.setLayout(new GridLayout(attributs.size()*2,1));
		
		for(int i=0;i<attributs.size();i++) {
			String attribut = attributs.get(i);
			if(attribut.equals("projets[]")) {
			} else if(attribut.equals("roles[]")) {
			} else if(attribut.equals("id")) {
			} else if(attribut.equals("sujet")) {
				JLabel label = new JLabel(attribut);
				panel_champs.add(label);

				final JComboBox<String> listeDeroulante = new JComboBox<String>();
				listeDeroulante.setPreferredSize(new Dimension(200,30));
				ArrayList<Sujets> listeSujets = Controleur.getSujets();
				for(int j=0;j<listeSujets.size();j++) {
					listeDeroulante.addItem(listeSujets.get(j).getDonnees().get("nom"));
					
					if(listeSujets.get(j).getDonnees().get("id").equals(sortie.get(attribut))) {
						listeDeroulante.setSelectedItem(listeSujets.get(j).getDonnees().get("nom"));
					}
				}
				panel_champs.add(listeDeroulante);
				champs[i] = listeDeroulante;
			} else if(attribut.equals("projet")) {
				JLabel label = new JLabel(attribut);
				panel_champs.add(label);

				final JComboBox<String> listeDeroulante = new JComboBox<String>();
				listeDeroulante.setPreferredSize(new Dimension(200,30));
				ArrayList<Projets> listeProjets = Controleur.getProjets();
				for(int j=0;j<listeProjets.size();j++) {
					listeDeroulante.addItem(listeProjets.get(j).getDonnees().get("id"));
					
					if(listeProjets.get(j).getDonnees().get("id").equals(sortie.get(attribut))) {
						listeDeroulante.setSelectedItem(listeProjets.get(j).getDonnees().get("nom"));
					}
				}
				panel_champs.add(listeDeroulante);
				champs[i] = listeDeroulante;
			} else if(attribut.equals("client") || attribut.equals("superviseur") || attribut.equals("support_technique")) {
				JLabel label = new JLabel(attribut);
				panel_champs.add(label);

				final JComboBox<String> listeDeroulante = new JComboBox<String>();
				listeDeroulante.setPreferredSize(new Dimension(200,30));
				ArrayList<Intervenants> listeProjets = Controleur.getIntervenants();
				
				listeDeroulante.addItem("");
				for(int j=0;j<listeProjets.size();j++) {
					listeDeroulante.addItem(listeProjets.get(j).getDonnees().get("nom") + " " + listeProjets.get(j).getDonnees().get("prenom"));
					
					if(listeProjets.get(j).getDonnees().get("id").equals(sortie.get(attribut))) {
						listeDeroulante.setSelectedItem(listeProjets.get(j).getDonnees().get("nom") + " " + listeProjets.get(j).getDonnees().get("prenom"));
					}
				}
				
				panel_champs.add(listeDeroulante);
				champs[i] = listeDeroulante;
			} else if(attribut.equals("groupe")) {
				JLabel label = new JLabel(attribut);
				panel_champs.add(label);

				final JComboBox<String> listeDeroulante = new JComboBox<String>();
				listeDeroulante.setPreferredSize(new Dimension(200,30));
				String[][] listeGroupes = Controleur.getGroupes();
				for(int j=0;j<listeGroupes[0].length;j++) {
					listeDeroulante.addItem(listeGroupes[0][j]);
					
					if(listeGroupes[0][j].equals(sortie.get(attribut))) {
						listeDeroulante.setSelectedItem(listeGroupes[0][j]);
					}
				}
				panel_champs.add(listeDeroulante);
				champs[i] = listeDeroulante;
			} else if(attribut.equals("role")) {
				JLabel label = new JLabel(attribut);
				panel_champs.add(label);

				final JComboBox<String> listeDeroulante = new JComboBox<String>();
				listeDeroulante.addItem("Client");
				listeDeroulante.addItem("Superviseur");
				listeDeroulante.addItem("Support Technique");

				listeDeroulante.setSelectedItem(sortie.get(attribut));
				
				panel_champs.add(listeDeroulante);
				champs[i] = listeDeroulante;
			} else if(attribut.equals("position")) {
				JLabel label = new JLabel(attribut);
				panel_champs.add(label);

				final JComboBox<String> listeDeroulante = new JComboBox<String>();
				listeDeroulante.setPreferredSize(new Dimension(200,30));

				for(int j=1;j<=Controleur.getSujets().size();j++) {
					listeDeroulante.addItem(j+"");
				}
				
				listeDeroulante.setSelectedItem(sortie.get(attribut));
				
				panel_champs.add(listeDeroulante);
				champs[i] = listeDeroulante;
			} else {
				JLabel label = new JLabel(attribut);
				panel_champs.add(label);
				
				champs[i] = new JTextField(sortie.get(attribut));
				((JTextField)champs[i]).setPreferredSize(new Dimension(200,30));
				panel_champs.add(((JTextField)champs[i]));
			}
		}
	
		JButton valider = new JButton("Valider");
		contenu.add(valider);
		
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i=0;i<attributs.size();i++) {
					String attribut = attributs.get(i);
					if(attribut.equals("projets[]")) {
					} else if(attribut.equals("roles[]")) {
					} else if(attribut.equals("id")) {
					} else if(attribut.equals("client") || attribut.equals("superviseur") || attribut.equals("support_technique")) {
						String nomInter = ((JComboBox<String>)champs[i]).getItemAt(((JComboBox<String>)champs[i]).getSelectedIndex());
						
						if(!nomInter.equals("")) {
							String idInter = Controleur.searchIntervenant("nom", nomInter.split(" ")[0]).get(0).getDonnees().get("id");
							table.put(attributs.get(i), idInter);
						}
					} else if(attribut.equals("sujet")) {
						String nomSujet = ((JComboBox<String>)champs[i]).getItemAt(((JComboBox<String>)champs[i]).getSelectedIndex());
						String idSujet = Controleur.searchSujet("nom", nomSujet).get(0).getDonnees().get("id");
						table.put(attributs.get(i), idSujet);
					} else if(attribut.equals("groupe")) {
						String groupe = ((JComboBox<String>)champs[i]).getItemAt(((JComboBox<String>)champs[i]).getSelectedIndex());
						table.put(attributs.get(i), groupe);
					} else if(attribut.equals("position")) {
						String position = ((JComboBox<String>)champs[i]).getItemAt(((JComboBox<String>)champs[i]).getSelectedIndex());
						table.put(attributs.get(i), position);
					} else {
						table.put(attributs.get(i), ((JTextField)champs[i]).getText());
					}
				}

				if(type.equals("sujet")) {
					Controleur.ajouterSujet(table);
				} else if(type.equals("etudiant")) {
					Controleur.ajouterEtudiant(table);
				} else if(type.equals("intervenant")) {
					Controleur.ajouterIntervenant(table);
				} else if(type.equals("projet")) {
					Controleur.ajouterProjet(table);
				} else if(type.equals("voeu")) {
					Controleur.ajouterVoeux(table);
				}
				
				FenetrePrincipale.Maj();
				jd.dispose();
			}
		});
	
		this.setVisible(true);
		this.revalidate();
		this.pack();
	}
	
	Enregistrement(Hashtable<String, String> sortie, JFrame parent, String titre) {
		this(sortie, parent, titre, "");
	}
	
}
