	function changer_page(id) {
		div = document.getElementById('accueil').style.display = 'none';
		div = document.getElementById('credits').style.display = 'none';
		div = document.getElementById('projets').style.display = 'none';
		div = document.getElementById('sujets').style.display = 'none';
		div = document.getElementById('etudiants').style.display = 'none';
		div = document.getElementById('intervenants').style.display = 'none';
	
		div = document.getElementById(id);

		if(div.style.display == 'none') {
			div.style.display = 'block';
		} else {
			div.style.display = 'none';
		}
	}
	
	function projet(recherche) {
		changer_page("projets");
		
		document.getElementById('autocomplete-input-projet').value = recherche;
		cherche_projet();
	}
	
	function cherche_projet() {
		recherche = document.getElementById("autocomplete-input-projet").value;
		nb_element = document.getElementById("nb_projets").innerHTML;
		
		for(i=0;i<nb_element;i++) {
			li = document.getElementById("p" + i);
			
			if(li.innerHTML.toLowerCase().indexOf(recherche.toLowerCase()) > -1) {
				li.style.display = "block";
			} else {
				li.style.display = "none";
			}
		}
	}
	
	function cherche_sujet() {
		recherche = document.getElementById("autocomplete-input-sujet").value;
		nb_element = document.getElementById("nb_sujets").innerHTML;
		
		for(i=0;i<nb_element;i++) {
			li = document.getElementById("s" + i);
			
			if(li.innerHTML.toLowerCase().indexOf(recherche.toLowerCase()) > -1) {
				li.style.display = "block";
			} else {
				li.style.display = "none";
			}
		}
	}
	
	function cherche_etudiant() {
		recherche = document.getElementById("autocomplete-input-etudiant").value;
		nb_element = document.getElementById("nb_etudiants").innerHTML;
		
		for(i=0;i<nb_element;i++) {
			li = document.getElementById("e" + i);
			
			if(li.innerHTML.toLowerCase().indexOf(recherche.toLowerCase()) > -1) {
				li.style.display = "block";
			} else {
				li.style.display = "none";
			}
		}
	}
	
	function cherche_intervenant() {
		recherche = document.getElementById("autocomplete-input-intervenant").value;
		nb_element = document.getElementById("nb_intervenants").innerHTML;
		
		for(i=0;i<nb_element;i++) {
			li = document.getElementById("i" + i);
			
			if(li.innerHTML.toLowerCase().indexOf(recherche.toLowerCase()) > -1) {
				li.style.display = "block";
			} else {
				li.style.display = "none";
			}
		}
	}