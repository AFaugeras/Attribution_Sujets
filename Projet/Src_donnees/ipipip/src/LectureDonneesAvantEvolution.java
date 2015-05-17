import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LectureDonneesAvantEvolution implements LectureDonnees{
	int nbEleves;
	int nbSujets;
	int effectifMin;
	int effectifMax;
	int nbMinSujets;
	Eleve[] listeEleves;
	int[] nbMinGroupes;
	int[] nbMaxGroupes;
	int nbPenalites;
	int[] valeurPenalite;


	public  LectureDonneesAvantEvolution(String nomFichier) {
		/*
		 * format du fichier de donnees :
		 * %% description de la ligne suivante : nb eleves
		 * nombre d'eleves
		 * %% description de la ligne suivante : nb sujets
		 * nombre de sujets : nbs
		 * %% description des nbs lignes suivantes nombres de groupes possibles par sujet
		 * sujet 1 : nb Min de groupes		nbMax de groupes
		 * sujet 2 : idem
		 * ...
		 * sujet nbs : idem
		 * %% description de la ligne suivante : effectifs min et max de chaque groupe
		 * effectif min		effectif max
		 * %% description ligne suivante : nb min de sujets effectivement affectés
		 * nbMinSujets
		 * %% description de la ligne suivante : nombre puis valeurs des pénalités
		 * k (nombre de pénalités) penalitéRang1 .. pénalitéRangk
		 * description des nbEleves lignes suivantes :  nom, nb Choix, rang sujet 1 .. rang sujet ns
		 * eleve 1 : nom_prénom nbChoixEffectués rangSujet1 rangSUjet2 ... rangSujetNs
		 * eleve 2 : idem
		 * ...
		 * eleve n : idem
		 */
		String ligne;
		String[] ligneDecomposee;
		int[] rangsSujets;
		String nom;
		int nbChoix;


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

			// effectifs min et max des groupes (communs à tous les groupes)
			ligne = f.readLine(); // ligne de description
			ligne = f.readLine(); 
			ligneDecomposee = ligne.split("\t");
			effectifMin = Integer.parseInt(ligneDecomposee[0]);
			effectifMax = Integer.parseInt(ligneDecomposee[1]);

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

	public Eleve getEleve(int e) {
		return listeEleves[e];
	}

	public int[] getValeurPenalite() {
		return valeurPenalite;
	}
	
	@Override
	public int getEffectifMinPourSujet(int numSujet) {
	    // TODO Auto-generated method stub
	    return getEffectifMin();
	}

	@Override
	public int getEffectifMaxPourSujet(int numSujet) {
	    // TODO Auto-generated method stub
	    return getEffectifMax();
	}


	@Override
	public int getMultiplicite() {
	    // TODO Auto-generated method stub
	    return 1;
	}

	public String toString() {
		String s = "";

		for (int e = 0 ; e < nbEleves ; e++) {
			s += (e+1) + " : \t" + listeEleves[e] + "\n";
		}

		return s;
	}

	public static void main(String [] args) {
		System.out.println("Debut lecture fichier");
		LectureDonnees ld; 
		//	ld = new LectureDonnees("data-cb1-2012.txt");
		ld = new LectureDonneesAvantEvolution("data-annee1-2012.txt");
		System.out.println(ld);
		System.out.println("*****************");
	}


}
