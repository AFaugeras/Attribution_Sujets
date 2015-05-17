import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LectureDonneesApresEvolution implements LectureDonnees {
    int nbEleves;
    int nbSujets;
    int effectifMin=-1;
    int effectifMax=-1;
    int nbMinSujets;
    Eleve[] listeEleves;
    int[] nbMinGroupes;
    int[] nbMaxGroupes;
    int nbPenalites;
    int[] valeurPenalite;
    int[] effectifMinDuSujet;
    int[] effectifMaxDuSujet;
    int multiplicite;


    public  LectureDonneesApresEvolution(String nomFichier) {
	/* ************************************************************
	 * format du fichier de donnees :
	 * ******************************
	 * 
	 * Lecture Données (version initiale)
	 * ******************************
	 * 
	 * %% description de la ligne suivante : nb eleves
	 * nombre d'eleves
	 * %% description de la ligne suivante : nb sujets
	 * nombre de sujets : nbs
	 * %% description des nbs lignes suivantes : nombres de groupes possibles par sujet
	 * sujet 1 : nb Min de groupes		nbMax de groupes
	 * sujet 2 : idem
	 * ...
	 * sujet nbs : idem
	 * 
	 * ****************************
	 * Modification :
	 *   les effectifs min et max étaient communs à tous les groupes
	 *   changement : effectif min et max spécifique à chauqe groupe
	 *   ** ancienne version
	 * %% description de la ligne suivante : effectifs min et max de chaque groupe
	 * effectif min		effectif max
	 *   ** nouvelle version
	 * %% description des nbs lignes suivantes : effectifs min et max par sujet
	 * sujet 1 : effectif Min 		effectif Max
	 * sujet 2 : idem
	 * ...
	 * sujet nbs : idem
	 * ***************************** fin modification
	 * 
	 * %% description ligne suivante : nb min de sujets effectivement affectés
	 * nbMinSujets
	 * %% description de la ligne suivante : nombre puis valeurs des pénalités
	 * k (nombre de pénalités) penalitéRang1 .. pénalitéRangk
	 * description des nbEleves lignes suivantes :  nom, nb Choix, rang sujet 1 .. rang sujet ns
	 * eleve 1 : nom_prénom nbChoixEffectués rangSujet1 rangSUjet2 ... rangSujetNs
	 * eleve 2 : idem
	 * ...
	 * eleve n : idem
	 * 
	 * Evolution des données : ajouts pour être plus générique
	 * ******************************
	 * 
	 * %% description des lignes suivantes : sujets à éviter (par élève)
	 * eleve 1 : R=nbSujetsRefusés num1erSujetRefusé num2eSujetRefusé ... numRiemeSujetRefusé
	 * eleve 2 : idem
	 * ...
	 * eleve n : idem
	 * %% description de la ligne suivante : multiplicité des effectifs des groupes
	 * multiplicité
	 * 

	 */


	String ligne;
	String[] ligneDecomposee;
	int[] rangsSujets;
	String nom;
	int nbChoix;
	int nbRefus;
	int[] listeRefus;


	try {
	    BufferedReader f = new BufferedReader(new FileReader(nomFichier));

	    // nombre d'eleves
	    ligne = f.readLine(); // ligne de descritpion : on la passe
	    ligne = f.readLine();
	    nbEleves = Integer.parseInt(ligne);

	    // nombre de sujets
	    ligne = f.readLine(); // ligne de description : on la passe
	    ligne = f.readLine();
	    nbSujets = Integer.parseInt(ligne);

	    // les nombres min et max de groupes pour chaque sujet
	    nbMinGroupes = new int[nbSujets];
	    nbMaxGroupes = new int[nbSujets];
	    ligne = f.readLine(); // ligne de description 
	    for (int numSujet = 0 ; numSujet < nbSujets ; numSujet++) {// pour chaque sujet
		ligne = f.readLine(); 
		ligneDecomposee = ligne.split("\t");
		nbMinGroupes[numSujet] = Integer.parseInt(ligneDecomposee[0]);
		nbMaxGroupes[numSujet] = Integer.parseInt(ligneDecomposee[1]);
	    }

	    /* Modification : effectifs min et max plus communs mais spécifiques à chaque groupe
			// effectifs min et max des groupes (communs à tous les groupes)
			ligne = f.readLine(); // ligne de description
			ligne = f.readLine(); 
			ligneDecomposee = ligne.split("\t");
			effectifMin = Integer.parseInt(ligneDecomposee[0]);
			effectifMax = Integer.parseInt(ligneDecomposee[1]);
	     */
	    // effectifs min et max des groupes (par sujet)
	    effectifMinDuSujet = new int[nbSujets];
	    effectifMaxDuSujet = new int[nbSujets];
	    ligne = f.readLine(); // ligne de description 
	    for (int numSujet = 0 ; numSujet < nbSujets ; numSujet++) {// pour chaque sujet
		ligne = f.readLine(); 
		ligneDecomposee = ligne.split("\t");
		effectifMinDuSujet[numSujet] = Integer.parseInt(ligneDecomposee[0]);
		effectifMaxDuSujet[numSujet] = Integer.parseInt(ligneDecomposee[1]);
	    }


	    // nombre Minimum de sujets effectivement affectés
	    ligne = f.readLine(); // ligne de description
	    ligne = f.readLine(); 
	    nbMinSujets = Integer.parseInt(ligne);

	    // penalites
	    ligne = f.readLine(); // ligne de description 
	    ligne = f.readLine(); 
	    ligneDecomposee = ligne.split("\t");
	    nbPenalites = Integer.parseInt(ligneDecomposee[0]);
	    valeurPenalite = new int[nbPenalites];
	    for (int p = 0 ; p < nbPenalites ; p++) { // lecture de chaque pénalité
		valeurPenalite[p] = Integer.parseInt(ligneDecomposee[p+1]);
	    }

	    // les élèves et leurs préférences
	    listeEleves = new Eleve[nbEleves];
	    ligne = f.readLine(); // ligne de description 
	    for (int e = 0 ; e < nbEleves ; e++) { // pour chaque élève
		ligne = f.readLine(); 
		ligneDecomposee = ligne.split("\t");
		nom = ligneDecomposee[0];
		nbChoix = Integer.parseInt(ligneDecomposee[1]);
		rangsSujets = new int[nbSujets];
		for (int s = 0 ; s < nbSujets ; s++) {
		    rangsSujets[s] = Integer.parseInt(ligneDecomposee[2+s]);
		}
		listeEleves[e] = new Eleve(nom, nbChoix, rangsSujets);
	    }

	    // les sujets refusés par les élèves
	    // la liste des élèves existe déjà : elle a été créée ci-dessus pour les préférences
	    ligne = f.readLine(); // ligne de description 
	    for (int e = 0 ; e < nbEleves ; e++) { // pour chaque élève
		ligne = f.readLine(); 
		ligneDecomposee = ligne.split("\t");
		nbRefus = Integer.parseInt(ligneDecomposee[0]);
		listeRefus = new int[nbRefus];
		for (int s = 0 ; s < nbRefus ; s++) {
		    listeRefus[s] = Integer.parseInt(ligneDecomposee[1+s]);
		}
		listeEleves[e].addListeRefus(listeRefus);
	    }

	    // multiplicite (commune à tous les groupes)
	    ligne = f.readLine(); // ligne de description
	    ligne = f.readLine(); 
	    // si la multiplicite est <= 0, on impose 1 (de fait, pas de multiplicite)
	    multiplicite = Math.max(1, Integer.parseInt(ligne));


	    f.close();
	}
	catch (IOException e) {
	    System.out.println("Erreur lors de la lecture du fichier "+ nomFichier + ": "+ e);
	}
    }

    public int getNbSujets() {
	return this.nbSujets;
    }

    public int getNbEleves() {
	return this.nbEleves;
    }

    public int getNbMinGroupesPourSujet(int numSujet) {
	return this.nbMinGroupes[numSujet];
    }

    public int getNbMaxGroupesPourSujet(int numSujet) {
	return this.nbMaxGroupes[numSujet];
    }

    public int getNbMinSujetsPris() {
	return nbMinSujets;
    }

    public int getEffectifMin() {
	return effectifMin;
    }

    public int getEffectifMax() {
	return effectifMax;
    }

    public int getEffectifMinPourSujet(int numSujet) {
	return effectifMinDuSujet[numSujet];
    }

    public int getEffectifMaxPourSujet(int numSujet) {
	return effectifMaxDuSujet[numSujet];
    }

    public Eleve getEleve(int e) {
	return listeEleves[e];
    }

    public int[] getValeurPenalite() {
	return valeurPenalite;
    }
    
    public int getMultiplicite() {
	return multiplicite;
    }

    public String toString() {
	String s = nbEleves + " élèves : \n";

	for (int e = 0 ; e < nbEleves ; e++) {
	    s += (e+1) + " : \t" + listeEleves[e] + "\n";
	}
	
	s += "Nombre de sujets : " + nbSujets + "\n";
	for (int numSujet = 0 ; numSujet < nbSujets ; numSujet++) {
	    s += "  sujet " + numSujet + "\n";
	    s += "    nb Min groupes = " + nbMinGroupes[numSujet] + ", nb Max groupes = " + nbMaxGroupes[numSujet] + "\n";
	    s += "    effectif Min sujet = " + effectifMinDuSujet[numSujet] + ", effectif Max sujet = " + effectifMaxDuSujet[numSujet] + "\n";
	}

	s += "Multiplicité = " + multiplicite + "\n";
	
	return s;
    }

    public static void main(String [] args) {
	System.out.println("Debut lecture fichier");
	LectureDonnees ld; 
	//	ld = new LectureDonnees("data-cb1-2012.txt");
	ld = new LectureDonneesApresEvolution("data-annee1-2012.txt");
	System.out.println(ld);
	System.out.println("*****************");
    }

}
