package Interface;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

public class Recherche extends JDialog {
	
	private JPanel contenu = new JPanel();
	private JPanel champs = new JPanel();
	private ArrayList<String> attributs = new ArrayList<String>();
	private JDialog jd;
	
	/**
	 * Cree et affiche une fenetre de recherche restreinte par attributs selectionne attributs .
	 * @param sortie Le tableau de sorties.
	 * @param entete Un tableau d'entetes.
	 * @param parent La fenetre mere.
	 */
	Recherche(final Hashtable<String, String> sortie, String[] entete, JFrame parent) {
		super(parent, "Recherche", true);
		this.setMinimumSize(new Dimension(450, 400));
		this.setLocationRelativeTo(null);
		
		this.jd = this;
				
		/// *** Liste des attributs *** \\\
		Iterator<String> it = sortie.keySet().iterator();
		while(it.hasNext()) {
			attributs.add(it.next());
		}
		
		this.add(contenu);
		contenu.add(champs);
		
		contenu.setLayout(new FlowLayout());
		champs.setLayout(new GridLayout(6,1));
		
		Font police = new Font("Arial", Font.PLAIN, 16);
		
		/* Liste deroulante des attributs */
		JLabel text_attribut = new JLabel("Attribut sur lequel porte la recherche :");
		text_attribut.setFont(police);
		text_attribut.setPreferredSize(new Dimension(300, 50));
		champs.add(text_attribut);
		
		final JComboBox<String> attribut = new JComboBox<String>();
		for(int i=0;i<entete.length;i++) {
			attribut.addItem(entete[i]);
		}
		champs.add(attribut);
	
		/* Valeur */
		JLabel text_valeur = new JLabel("Mot clef à rechercher :");
		text_valeur.setFont(police);
		text_valeur.setPreferredSize(new Dimension(300, 50));
		champs.add(text_valeur);
		
		final JTextField champ_valeur = new JTextField();
		champs.add(champ_valeur);
		champs.add(new JLabel());
		
		/* Bouton valider */
		JButton valider = new JButton("Valider");
		champs.add(valider);
		
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sortie.put("attribut", attribut.getItemAt(attribut.getSelectedIndex()));
				sortie.put("valeur", champ_valeur.getText());
				
				FenetrePrincipale.Maj();
				jd.dispose();
			}
		});
	
		this.setVisible(true);
		this.revalidate();
		this.pack();
	}
	
}
