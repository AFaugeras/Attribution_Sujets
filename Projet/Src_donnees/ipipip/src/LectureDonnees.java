
interface LectureDonnees {
	int getNbSujets() ;

	int getNbEleves();

	int getNbMinGroupesPourSujet(int numSujet);

	int getNbMaxGroupesPourSujet(int numSujet);

	int getNbMinSujetsPris();

	int getEffectifMin();

	int getEffectifMax();
	
	int getEffectifMinPourSujet(int numSujet);

	int getEffectifMaxPourSujet(int numSujet);

	Eleve getEleve(int e);

	int[] getValeurPenalite();
	
	int getMultiplicite();

}
