package Interface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import Controleur.Controleur;
import Modele.Etudiants;
import Modele.Intervenants;
import Modele.Projets;
import Modele.Sujets;
/**
 * Permet à l'utilisateur d'utiliser pleinement l'application en lui donnant acces à toutes les fonctionnalites et donnes.
 * @author groupe2A2
 *
 */
public class FenetrePrincipale extends JFrame {
	private JMenuBar barre = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier");
	private JMenu aide=new JMenu("Aide");
	private JMenuItem Ouvrir = new JMenuItem("Ouvrir");
	private JMenuItem Enregistrer = new JMenuItem("Enregistrer");
	private JMenuItem Enregistrer_sous = new JMenuItem("Enregistrer sous");
	private JMenuItem quitter1=new JMenuItem("Quitter");
	private JMenuItem aPropos=new JMenuItem("A propos");
	private JButton creerSujet = new JButton("Ajouter un Sujet");
	private JPanel panfenetre= new JPanel();
	private boolean pouvoirEnregistrer=false;
	
	private static JFrame fenêtre;
	private static JTabbedPane onglets;
	private static int onglet_index = -1;
	
	/**
	 * Cree et affiche l'interface principale de l'application
	 */
	public FenetrePrincipale (){
		super("Opti");
		
		this.setVisible(true);
		this.setMinimumSize(new Dimension(1280, 720));
		this.setLocationRelativeTo(null);

		fenêtre = this;

		// Positionnement
		this.setLayout(new BorderLayout());
		panfenetre.setLayout(new BorderLayout());
		
		// Sous menu
		barre.add(fichier);
		barre.add(aide);
		fichier.add(Ouvrir);
		fichier.add(Enregistrer);
		fichier.add(Enregistrer_sous);
		fichier.add(quitter1);
		aide.add(aPropos);
		
		//racourci
		
		Ouvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
		Enregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		quitter1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,InputEvent.CTRL_MASK));
		
		// Import des donnees
		Controleur.initCsv();
		Controleur.importer();
		
		// Onglets
		onglets= new JTabbedPane();
		onglets.setPreferredSize(new Dimension(300, 200));

		Maj();
		
		panfenetre.add(onglets,BorderLayout.CENTER);
		
		///////////////////////////////////////
		///////// * ACTIONLISTENER * //////////
		///////////////////////////////////////

		aPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A_propos fenAP=new A_propos(fenêtre,"A Propos");
			}
		});
		
		quitter1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rep =JOptionPane.showConfirmDialog(null,"Etes vous sur de vouloir quitter","Quitter",JOptionPane.YES_NO_OPTION);
				if(rep==JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
		
		Enregistrer_sous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					OuvrirEnregistrer sauvegarder=new OuvrirEnregistrer(Controleur.sorties,"Enregistrer");
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		
		Enregistrer.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				majPouvoirEnregistrer();
				if(pouvoirEnregistrer){
					Controleur.exporter('a');
				}else{
					try {
						OuvrirEnregistrer sauvegarder=new OuvrirEnregistrer(Controleur.sorties,"Enregistrer");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		Ouvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					OuvrirEnregistrer sauvegarder=new OuvrirEnregistrer(Controleur.entres,"Ouvrir");
					
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		
		// Composants
		this.add(barre,BorderLayout.NORTH);
		this.add(panfenetre);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
		this.revalidate();
	}
	/**
	 * Verifie l'utilisation de enregistrer sous et met à jour pouvoirEnregistrer. Ainsi l'utilisateur ne pourra pas enregistrer avant d'avoir enregistrer_sous.
	 */
	public void majPouvoirEnregistrer(){
		if(Controleur.sorties.get("Etudiants").equals("")){
			pouvoirEnregistrer=false;
		}else if(Controleur.sorties.get("Sujets").equals("")){
			pouvoirEnregistrer=false;
		}else if(Controleur.sorties.get("Projets").equals("")){
			pouvoirEnregistrer=false;
		}else if(Controleur.sorties.get("Intervenants").equals("")){
			pouvoirEnregistrer=false;
		}else{
			pouvoirEnregistrer=true;
		}
	}
	/**
	 * Met à jour les differants onglets contenu dans FenetrePrincipale.
	 */
	public static void Maj() {
		onglet_index = onglets.getSelectedIndex();
		
		onglets.removeAll();
		
		onglets.add(AffichageSuj.affiJPanel(Controleur.getSujets(),Sujets.getAttributs(), fenêtre),"Sujets");
		onglets.add(AffichageProj.affiJPanel(Controleur.getProjets(), Projets.getAttributs(), fenêtre),"Projets");
		onglets.add(AffichageEtu.affiJPanel(Controleur.getEtudiants(),Etudiants.getAttributs(), fenêtre),"Etudiants");
		onglets.add(AffichageInt.affiJPanel(Controleur.getIntervenants(), Intervenants.getAttributs(), fenêtre),"Intervenants");
		onglets.add(AffichageGroupe.affiJPanel(fenêtre, Controleur.getGroupes()),"Groupes");
		onglets.add(AffichageVoeux.affiJPanel(Controleur.getVoeux(), fenêtre), "Voeux");
		
		if(onglet_index != -1) {
			onglets.setSelectedIndex(onglet_index);
		}
		
		fenêtre.revalidate();
	}
	
	public static JTabbedPane getOnglet(){
		return onglets;
	}
	
	public static JFrame getFenetre(){
		return fenêtre;
	}
	

}
