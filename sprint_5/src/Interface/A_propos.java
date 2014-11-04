package Interface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;




@SuppressWarnings("serial")
public class A_propos extends JDialog {

/**
 * Cree et affiche la fenetre A Propos
 * @param parent est la fenetre mère de la fenetre A Propos
 * @param titre est le titre de la fenetre A Propos
 */
	public A_propos(JFrame parent,String titre){
		 super(parent,titre,true);
		 this.setTitle(titre);
		 this.setSize(500,300);
	     this.setContentPane(panneau());
	     this.setLocationRelativeTo(null);
	     this.pack();
	     this.setVisible(true);
	}
	
	/**
	 * cree le panneau principal de la fenetre A Propos
	 * @return retourne le panneau principale de la fenetre A propos
	 */
	public JPanel panneau(){
		JPanel conteneur = new JPanel(new BorderLayout());
		// Creation de 3 panneaux de base
		
		JPanel GrandNord = new JPanel() ;
		JPanel GrandCentre = new JPanel() ;
		JPanel GrandSud = new JPanel() ;
		
		GrandNord.setPreferredSize(new Dimension(500,50));
		GrandCentre.setPreferredSize(new Dimension(500,50));
		GrandSud.setPreferredSize(new Dimension(500,200));
	
		conteneur.add(GrandNord, BorderLayout.NORTH) ;
		conteneur.add(GrandCentre, BorderLayout.CENTER ) ;
		conteneur.add(GrandSud, BorderLayout.SOUTH ) ;
		
		// On ajoute pan1 dans GrandNord
		
		JPanel pan1 = new JPanel() ; // panneau correspondant au nom du projet : OPTI
		
		
		GrandNord.add(pan1);
		{
			JLabel nomP = new JLabel("Opti");
			nomP.setFont(new Font("Opti", Font.PLAIN, 20)); 
			pan1.add(nomP);
			pan1.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED), new EmptyBorder(0,0,0,0)));
		}	
				
		
		// On ajoute pan2 pan3  dans GrandCentre
		
		JPanel pan2 = new JPanel() ; // panneau correspondant à Universite Toulouse 2
		
		JPanel pan3 = new JPanel() ; // panneau correspondant à IUT de Blagnac
		
		
		GridLayout grid = new GridLayout(1,2);
		GrandCentre.setLayout(grid);
		
		GrandCentre.add(pan2);
		GrandCentre.add(pan3);
		{
			pan2.add(new JLabel("UT2"));
			//pan2.setBorder(new BevelBorder(BevelBorder.LOWERED));
			pan3.add(new JLabel("IUT Blagnac"));
		}
		
		
		
		// On ajoute pan4 et pan5  dans GrandSud
		
		
		JPanel pan4 = new JPanel() ; // panneau correspondant à DUT INFO S3/Module MPA
		JPanel pan5 = new JPanel() ; // panneau correspondant à la liste des membres de l'equipe et numero de groupe
	
		GrandSud.setLayout(new BorderLayout());
		
		GrandSud.add(pan4,BorderLayout.NORTH);
		GrandSud.add(pan5);
		
		{
			pan4.add(new JLabel("DUT Info S3 / Module MPA"));
			
			Border lol = BorderFactory.createRaisedBevelBorder();
			pan4.setBorder(lol);
						
			TitledBorder title = BorderFactory.createTitledBorder("Membre du groupe2A2");
			pan5.setBorder(title);
			pan5.add(new JLabel("<html><body>COLLIGNON Benjamin <br> ERB Alexandre <br> LOPEZ Nathan <br> PERE Jean-Phllipe<br> Santacana Nathan <br> VERON Vimel</body></html>"));
					
		}
		
		return conteneur ;
	}
	
	
}
