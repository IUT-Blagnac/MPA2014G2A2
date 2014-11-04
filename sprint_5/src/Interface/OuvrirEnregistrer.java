package Interface;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controleur.Controleur;


/**
 * Permet à l'utilisateur de l'application de sauvegarder ou enregistrer ses fichiers.
 * @author groupe2A2
 * 
 *
 */
public class OuvrirEnregistrer extends JDialog{
	
	private JTextField etuField,sujField,projField,intField,voeField;
	private JButton etuButton,sujButton,projButton,intButton,voeButton;
	private JLabel etuJLabel,sujJLabel,projJLabel,intJLabel,voeJLabel;
	private JPanel panTop;
	private JPanel etuJPanel,sujJPanel,projJPanel,intJPanel,voeJPanel;
	private ActionListener explorerActionListener;
	private String nomFonc;
	private Hashtable<String, String> sorties;
	private OuvrirEnregistrer cetteFen;
	
	/**
	 * Cree une fenêtre permettant de Sauvegarder ou Ouvrir les differents elements
	 * @param sortie Donner une hashtable comportant [nomAttributs] vers [chemin actuel d'ouverture/enregistrement]
	 * @param nomFon le traitement à effectuer "Ouvrir" pour ouvrir ou "Enregistrer" pour Enregistrer
	 * @throws IOException exception maggle
	 */
	public OuvrirEnregistrer(Hashtable<String, String> sortie, String nomFon) throws IOException{
		super();

		cetteFen=this;
		nomFonc=nomFon;
		sorties=sortie;
		this.setSize(400, 800);
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		panTop=new JPanel();
		this.add(panTop,BorderLayout.NORTH);
		panTop.setLayout(new GridLayout(5,1));
		this.setLocationRelativeTo(null);
		this.setTitle(nomFon);
		explorerActionListener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String rep=null;
				try {
					rep = chercher();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(rep!=null){
					switch (arg0.getActionCommand()) {
					case "etu":
						etuField.setText(rep);
						break;
					case "suj":
						sujField.setText(rep);
						break;
					case "proj":
						projField.setText(rep);
						break;

					case "int":
						intField.setText(rep);	
						break;
					case "voe":
						intField.setText(rep);	
						break;
					}
				}
			}
		};
		
		//Etudiant
		etuJPanel=new JPanel(new FlowLayout());
		panTop.add(etuJPanel);
		etuJLabel=new JLabel("Etudiant");
		etuJPanel.add(etuJLabel);
		etuJLabel.setPreferredSize(new Dimension(80, 30));
		etuField=new JTextField(sorties.get("Etudiants"));
		etuField.setPreferredSize(new Dimension(300,30));
		etuJPanel.add(etuField);
		etuButton=new JButton("Explorer");
		etuJPanel.add(etuButton);
		etuButton.addActionListener(explorerActionListener);
		etuButton.setActionCommand("etu");
		
		//Sujet
		sujJPanel=new JPanel(new FlowLayout());
		panTop.add(sujJPanel);
		sujJLabel=new JLabel("Sujet");
		sujJPanel.add(sujJLabel);
		sujJLabel.setPreferredSize(new Dimension(80, 30));
		sujField=new JTextField(sorties.get("Sujets"));
		sujField.setPreferredSize(new Dimension(300,30));
		sujJPanel.add(sujField);
		sujButton=new JButton("Explorer");
		sujJPanel.add(sujButton);
		sujButton.addActionListener(explorerActionListener);
		sujButton.setActionCommand("suj");
		
		//Projet
		projJPanel=new JPanel(new FlowLayout());
		panTop.add(projJPanel);
		projJLabel=new JLabel("Projet");
		projJPanel.add(projJLabel);
		projJLabel.setPreferredSize(new Dimension(80, 30));
		projField=new JTextField(sorties.get("Projets"));
		projField.setPreferredSize(new Dimension(300,30));
		projJPanel.add(projField);
		projButton=new JButton("Explorer");
		projJPanel.add(projButton);
		projButton.addActionListener(explorerActionListener);
		projButton.setActionCommand("proj");
		
		//Intervenants
		intJPanel=new JPanel(new FlowLayout());
		panTop.add(intJPanel);
		intJLabel=new JLabel("Intervenant");
		intJPanel.add(intJLabel);
		intJLabel.setPreferredSize(new Dimension(80, 30));
		intField=new JTextField(sorties.get("Intervenants"));
		intField.setPreferredSize(new Dimension(300,30));
		intJPanel.add(intField);
		intButton=new JButton("Explorer");
		intJPanel.add(intButton);
		intButton.addActionListener(explorerActionListener);
		intButton.setActionCommand("int");
		
		//Voeux 
		voeJPanel=new JPanel(new FlowLayout());
		panTop.add(voeJPanel);
		voeJLabel=new JLabel("Voeux");
		voeJPanel.add(voeJLabel);
		voeJLabel.setPreferredSize(new Dimension(80, 30));
		voeField=new JTextField(sorties.get("Voeux"));
		voeField.setPreferredSize(new Dimension(300,30));
		voeJPanel.add(voeField);
		voeButton=new JButton("Explorer");
		voeJPanel.add(voeButton);
		voeButton.addActionListener(explorerActionListener);
		voeButton.setActionCommand("voe");
		
		//Bouton de validation
		JPanel pan=new JPanel(new FlowLayout());
		this.add(pan,BorderLayout.SOUTH);
		JButton enregistrerButton=new JButton(nomFonc);
		pan.add(enregistrerButton);
		enregistrerButton.addActionListener(new ActionListener() {
			
			@Override
			/**
			 * Effectue la validation en enregistrent les nouveaux chemins.
			 * @see Controleur.exporter()
			 * @see Controleur.importer()
			 */
			public void actionPerformed(ActionEvent e) {
				sorties.put("Etudiants", etuField.getText());
				sorties.put("Sujets", sujField.getText());
				sorties.put("Projets", projField.getText());
				sorties.put("Intervenants", intField.getText());
				sorties.put("Voeux", voeField.getText());
				if(nomFonc.equals("Ouvrir")){
					Controleur.importer();
					FenetrePrincipale.Maj();
					Controleur.sorties.put("Etudiants", Controleur.entres.get("Etudiants"));
					Controleur.sorties.put("Sujets", Controleur.entres.get("Sujets"));
					Controleur.sorties.put("Projets", Controleur.entres.get("Projets"));
					Controleur.sorties.put("Intervenants", Controleur.entres.get("Intervenants"));
					Controleur.sorties.put("Voeux", Controleur.entres.get("Voeux"));
				}else if(nomFonc.equals("Enregistrer")){
					Controleur.exporter('a');
				}
				cetteFen.dispose();
			}
		});
	    this.pack();
	}
	/**
	 * Ouvre un explorer permettant à l'utilisateur de choisir son emplacement/fichier d'enregistrement/ouverture
	 * @return retourne le chemin choisi par l'utilisateur.
	 * @throws IOException
	 */
	private String chercher() throws IOException{
		//Browser de sauvegarde retourne null si rien est choisi
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier CSV","csv");
			    chooser.setFileFilter(filter);
			    chooser.setApproveButtonText("Selectionner");
			    int returnVal = chooser.showOpenDialog(this);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			    	String resultat=chooser.getSelectedFile().getCanonicalPath();
			    	if(!resultat.contains(".csv")){
			    		resultat=resultat+".csv";
			    	}
			    	return resultat;
			    }else{
			    	return null;
			    }
			    
	}

}
