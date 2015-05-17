
public class Eleve {
    String nom;
    int nbChoix;
    int[] sujetsChoisis;
    int[] rangsSujets;
    int[] listeRefus; // version après évolution : ajout d'une liste de sujets refusés

    Eleve(String nom, int nbChoix, int[] rangsSujets) {
	int nbSujets = rangsSujets.length;
	this.nom = nom;
	this.nbChoix = nbChoix;
	this.rangsSujets = rangsSujets;
	this.sujetsChoisis = new int[nbChoix];
	for (int numSujet = 0 ; numSujet < nbSujets ; numSujet++) {
	    int rang = rangsSujets[numSujet];
	    if (rang <= nbChoix) { // le sujet numSujet+1 (décalage de 1) a été choisi par l'élève
		sujetsChoisis[rang-1] = numSujet+1;
	    }
	}
    }

    /* retourne le rang effectif du sujet numSujet, numSujet dans 1.. nbSujets*/
    int getRang(int numSujet) {
	return rangsSujets[numSujet-1];
    }

    /* retourne le sujet classe au rang donne (rang effectif, 1 pour le 1er choix) */
    int getSujetdeRang(int rang) {
	return sujetsChoisis[rang-1];
    }

    /* retourne la liste des sujets refusés */
    public int[] getListeRefus() {
	return listeRefus;
    }

    /* ajoute la liste des sujets refusés */
    public void addListeRefus(int [] liste) {
	this.listeRefus = liste;
    }

    public String toString() {
	int nbRefus = listeRefus.length;
	String s = nom + " ";
	s += nbChoix + " choix : ";
	for (int i = 0 ; i < nbChoix ; i++) {
	    s += sujetsChoisis[i] + " ";
	}
	s+="\t *** " + nbRefus + " refus : ";
	for (int i = 0 ; i < nbRefus ; i++) {
	    s += listeRefus[i] + " ";
	}
	return s;
    }
}
