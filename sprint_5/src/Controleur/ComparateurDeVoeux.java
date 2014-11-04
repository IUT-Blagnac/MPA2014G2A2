package Controleur;

import java.util.Comparator;

import Modele.Voeux;

public class ComparateurDeVoeux implements Comparator<Voeux> {

	public ComparateurDeVoeux() {
	}

	@Override
	public int compare(Voeux v1, Voeux v2) {
		if(Integer.parseInt(v1.getDonnees().get("position")) < Integer.parseInt(v2.getDonnees().get("position"))) {
			return -1;
		} else {
			return 1;
		}
	}

}
