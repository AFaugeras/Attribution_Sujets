import java.util.ArrayList;

import org.chocosolver.solver.ResolutionPolicy;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.IntConstraintFactory;
import org.chocosolver.solver.objective.ObjectiveStrategy;
import org.chocosolver.solver.objective.OptimizationPolicy;
import org.chocosolver.solver.search.limits.FailCounter;
import org.chocosolver.solver.search.loop.monitors.SMF;
import org.chocosolver.solver.search.strategy.IntStrategyFactory;
import org.chocosolver.solver.search.strategy.strategy.AbstractStrategy;
import org.chocosolver.solver.trace.Chatterbox;
import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.variables.VariableFactory;



public class Ipipip {
    public static final int RANG_MAX_AUTORISE = 2;
    public static final int NOMBRE_MAX_SUJETS_CLASSES = 5;
    public static final int NOMBRE_TOTAL_RANGS = NOMBRE_MAX_SUJETS_CLASSES + 1; // + 1 pour le rang des sujets non classés

    public static final int TOUS_SUJETS  = 0;
    public static final int RANG_MINIMAL = 1;
    public static final int MINIMISER_RANG_MAX = 2; 
    public static final int OPTIMISATION = 3;   
    public static final int OPTIMISATION_PAR_ETAPES = 4;   
    // optimisation par étapes :
    // 1ère étape  : on minimise d'abord le rang max, on utilise le coût associé à la solution obtenue
    //  puis 
    // 2e étape : initialiser la borne sup du coût (recherche plus efficace) 
    //   pour l'optimisation "classique" du coût

    public static final int OPTIMISATION_DICHOTOMIQUE = 5;
    public static final int OPTIMISATION_AVEC_RANG_MAX_AUTORISE = 6;

    public static final int BORNE_SUP_COUT_AFFECTATION = 500;
    // optimum = 133 (trouvé) pour le fichier data-annee1-2012.txt
    // pour data-A1-2015, meilleure solution trouvée = 234 (en cours)

    public static final int BORNE_INF_COUT_AFFECTATION = 0;
    // pour data-A1-2015, min = nbEleves = 194, mais pas de solution de cout <= 210

    LectureDonnees ld;
    LectureDonneesApresEvolution ldEv;
    int niveauResolution;
    int rangMaxChoixEleve;

    Solver solver;

    IntVar[] sujetsAffectesAuxEleves;
    IntVar[] nbGroupesParSujet;
    IntVar[] effectifs;

    // pour l'optimisation par cout
    IntVar[] listeCouts;
    IntVar[] coutAffectationEleve;
    IntVar coutTotalAffectations;

    // pour la minimisation du rang max
    IntVar[] listeRangs;
    IntVar rangMaxSujetAffecte;

    public Ipipip(LectureDonnees ld, int niveauResolution, int rangMaxChoixEleve) {
	this.ld = ld;
	this.niveauResolution = niveauResolution;
	this.rangMaxChoixEleve = rangMaxChoixEleve;
    }

    public int getNbSujets() {
	return ld.getNbSujets();
    }

    public int getNbEleves() {
	return ld.getNbEleves();
    }

    public int getNbMinGroupePourSujet(int numSujet) {
	return ld.getNbMinGroupesPourSujet(numSujet);
    }

    public int getNbMaxGroupePourSujet(int numSujet) {
	return ld.getNbMaxGroupesPourSujet(numSujet);
    }

    public int getNbMinSujetsPris() {
	return ld.getNbMinSujetsPris();
    }

    public int getEffectifMin() {
	return ld.getEffectifMin();
    }

    public int getEffectifMax() {
	return ld.getEffectifMax();
    }

    public int getEffectifMin(int sujet) {
	return ld.getEffectifMinPourSujet(sujet);
    }

    public int getEffectifMax(int sujet) {
	return ld.getEffectifMaxPourSujet(sujet);
    }


    public void makeModel() {

	this.solver = new Solver();

	int nbSujets = getNbSujets();
	int nbEleves = getNbEleves();
	/*
	 * définition des variables associées aux élèves
	 * domaines initiaux : tous les sujets
	 */
	sujetsAffectesAuxEleves = VariableFactory.enumeratedArray("eleves",nbEleves,1,nbSujets, solver);

	/*
	 * nombre de groupes par sujet
	 */
	nbGroupesParSujet = new IntVar[nbSujets];
	for (int numSujet = 0 ; numSujet < nbSujets; numSujet++) {
	    nbGroupesParSujet[numSujet] = VariableFactory.enumerated("nombre de groupes pour sujet " + (numSujet+1), 
	                                                             getNbMinGroupePourSujet(numSujet),
	                                                             getNbMaxGroupePourSujet(numSujet),
	                                                             solver);
	}

	/* **************************
	 * Les Contraintes
	 * *************************/

	/*
	 * nombre total de groupes = nb de sujets
	 */
	IntVar intVarNbSujet = VariableFactory.fixed(nbSujets, solver);
	solver.post(IntConstraintFactory.sum(nbGroupesParSujet, intVarNbSujet));

	/*
	 * nombre minimum de sujets pris
	 *   nb de sujets p tq nbGroupesParSujet(p) > 0 >= nbMiniSujets
	 * ou (en inversant l'inégalité)
	 * nombre maximum de sujets non pris
	 *   nb de sujets p tq nbGroupesParSujet(p) = 0 <= nbTotalSujets - nbMiniSujets
	 *   -> occurrenceMax, en introduisant une variable 
	 *      dont le domaine est 0 .. nbTotalSujets - nbMiniSujets
	 *      
	 *      Choco 3 : plus de contrainte occurrence max, seulement occurrence, renommée count
	 *      nb de sujets non pris <= nbMax sujets Non pris (défini par nbTotal sujets - nbMinSujets)
	 *      nb de sujets p tq nbGroupesParSujet(p) = 0 inclus dans [0 .. nbTotalSujets - nbMiniSujets]
	 */
	int nbMinSujets = getNbMinSujetsPris();
	int nbMaxSujetsNonPris = nbSujets - nbMinSujets;
	IntVar nbSujetsNonPris = VariableFactory.bounded("nb sujets non pris", 0, nbMaxSujetsNonPris, solver);
	solver.post(IntConstraintFactory.count(0, nbGroupesParSujet, nbSujetsNonPris));


	/*
	 * Niveau de résolution
	 *  - si niveauResolution == TOUS_SUJETS (pas de prise en compte des préférences ) 
	 *    domaines : tous les sujets, pas de changement par rapport aux domaines initiaux
	 *    tous les sujets doivent être pris, donc le nombre max de sujets non pris est 0
	 *  - si niveauResolution == RANG_MINIMAL (on n'affecte  à chaque élève que ses sujets de rang <= k)
	 *    il faut donc "nettoyer" les domaines en supprimant les sujets de rang supérieur
	 *    certains sujets peuvent ne pas être pris (si aucun élève n'en veut) : pas de changement
	 */

	switch (this.niveauResolution) {
	    case TOUS_SUJETS : 
		solver.post(IntConstraintFactory.arithm(nbSujetsNonPris, "=", 0));
		break;

	    case RANG_MINIMAL :
		/*
		for (int e = 0 ; e < nbEleves ; e++) {
		    ArrayList<Integer> listeSujets = new ArrayList<Integer>();
		    Eleve eleve = ld.getEleve(e);
		    for (int s = 0 ; s < nbSujets ; s++) {
			if (eleve.getRang(s+1) <= this.rangMaxChoixEleve) {
			    listeSujets.add(s+1);
			}
		    }
		    // transfert vers un int[]
		    int taille = listeSujets.size();
		    int[] sujetsDeRangOk = new int[taille];
		    for (int i = 0 ; i < taille; i++) {
			sujetsDeRangOk[i] = listeSujets.get(i);
		    }
		    // on restreint le domaine de chaque eleve à ses sujets de rang <= au rang maximum autorisé
		    // -> member(x, tab) <=> dom(x) inclus (au sens large) dans tab
		    solver.post(IntConstraintFactory.member(sujetsAffectesAuxEleves[e], sujetsDeRangOk));
		}			
		 */
		//break;


	    case MINIMISER_RANG_MAX : 
	    case OPTIMISATION_PAR_ETAPES :
	    case OPTIMISATION_AVEC_RANG_MAX_AUTORISE :
		listeRangs = VariableFactory.enumeratedArray("rang du sujet", nbEleves, 1, NOMBRE_TOTAL_RANGS, solver);
		rangMaxSujetAffecte = VariableFactory.bounded("Rang max affecté", 0, NOMBRE_TOTAL_RANGS, solver);
		solver.post(IntConstraintFactory.maximum(rangMaxSujetAffecte, listeRangs));

		for (int e = 0 ; e < nbEleves ; e++) {
		    int[] rangsSujets = new int[nbSujets];
		    for (int p = 0 ; p < nbSujets ; p++) {
			rangsSujets[p] = ld.getEleve(e).getRang(p+1);
		    }
		    // rang sujet : création similaire avec les coûts des sujets 
		    // element : 
		    // lien entre le rang et le sujet : listeRangs[e] = rangsSujets[sujetsAffectesAuxEleves[e]-1]
		    solver.post(IntConstraintFactory.element(listeRangs[e], 
		                                             rangsSujets, 
		                                             sujetsAffectesAuxEleves[e], 
		                                             1, 
			    "none"));
		}

		break;


	}
	if (niveauResolution == RANG_MINIMAL) {
	    solver.post(IntConstraintFactory.arithm(rangMaxSujetAffecte, "<=", this.rangMaxChoixEleve));
	}

	// Dans tous les cas, on veut le cout de l'affectation
	listeCouts = VariableFactory.enumeratedArray("cout du sujet", nbEleves, ld.getValeurPenalite(), solver);
	//  coutAffectationEleve = new IntegerVariable[nbEleves];
	// coutAffectationEleve jamais utilisé

	coutTotalAffectations = VariableFactory.bounded("Cout total de l'affectation", 
	                                                BORNE_INF_COUT_AFFECTATION, 
	                                                BORNE_SUP_COUT_AFFECTATION, 
	                                                solver);
	solver.post(IntConstraintFactory.sum(listeCouts, coutTotalAffectations));

	for (int e = 0 ; e < nbEleves ; e++) {
	    int[] coutsSujets = new int[nbSujets];
	    for (int p = 0 ; p < nbSujets ; p++) {
		coutsSujets[p] = ld.getValeurPenalite()[ld.getEleve(e).getRang(p+1)-1];
	    }
	    // element : 
	    // lien entre le cout et le sujet : listeCouts[e] = coutsSujets[sujetsAffectesAuxEleves[e]-1]
	    solver.post(IntConstraintFactory.element(listeCouts[e], 
	                                             coutsSujets, 
	                                             sujetsAffectesAuxEleves[e], 
	                                             1,
		    				     "none"));
	}


	/*
	 * equilibrage des nombres d'élèves par sujet (groupe)
	 */
	effectifs = new IntVar[nbSujets];
	int effectifMin = getEffectifMin();
	int effectifMax = getEffectifMax();
	for (int p=0 ; p< nbSujets ; p++) {
	    effectifs[p] = VariableFactory.bounded("effectifs du sujet "+ (p+1), 0, getNbEleves(), solver);
	    solver.post(IntConstraintFactory.scalar(new IntVar[]{nbGroupesParSujet[p]},
	                                            new int[]{getEffectifMax(p)},
	                                            ">=",
	                                            effectifs[p]));
	    solver.post(IntConstraintFactory.scalar(new IntVar[]{nbGroupesParSujet[p]},
	                                            new int[]{getEffectifMin(p)},
	                                            "<=",
	                                            effectifs[p]));
	    solver.post(IntConstraintFactory.count(p+1, sujetsAffectesAuxEleves, effectifs[p]));			
	}

	/*
	 * Contrainte redondante : somme des effectifs = nombre total d'élèves
	 */
	solver.post(IntConstraintFactory.sum(effectifs, VariableFactory.fixed(nbEleves, solver)));
	
	/*
	 * Suppression des valeurs refusees par les eleves
	 */
	supprimerSujetsRefuses();
	forcerMultiplicite(-1);
    }
    
    public void supprimerSujetsRefuses() {
	/*
	 * pour chaque élèves, on supprime de son domaine (contrainte !=)
	 * tous les sujets qu'il a refusés
	 */
	int nbEleves = getNbEleves();
	for (int e = 0 ; e < nbEleves ; e++) {
	    for (int valeurRefusee : ld.getEleve(e).getListeRefus()) {
		solver.post(IntConstraintFactory.arithm(sujetsAffectesAuxEleves[e], "!=", valeurRefusee));
	    }
	}
    }
    
    public void forcerMultiplicite(int sujet) {
	/*
	 * on force tous les sujets (sauf un : sujet) à avoir un effectif
	 * multiple de la valeur imposée (m=ld.getMultiplicite())
	 * 
	 * Sauf un sujet : pour traiter le cas où le nombre total d'élèves
	 * n'est pas un multiple de m
	 * Si le nombre total est un multiple, ce dernier sujet sera nécessairement
	 * aussi un multiple de m (par effet de bord)
	 */
	int nbSujets = getNbSujets();
	int m = ld.getMultiplicite();
	IntVar zero = VariableFactory.fixed(0, solver);
	IntVar multi = VariableFactory.fixed(m, solver);
	int sujetNonTraite;
	// si on passe -1 comme parametre, on prend le dernier sujet
	if (sujet == -1) {
	    sujetNonTraite = nbSujets -1;
	}
	else {
	    sujetNonTraite = sujet;
	}
	for (int numSujet = 0 ; numSujet < sujetNonTraite ; numSujet++) {
	    solver.post(IntConstraintFactory.mod(effectifs[numSujet], multi, zero));
	}
	// on evite le sujet numero "sujetNonTraite"
	for (int numSujet = sujetNonTraite+1 ; numSujet < nbSujets ; numSujet++) {
	    solver.post(IntConstraintFactory.mod(effectifs[numSujet], multi, zero));
	}
	// si le nombre total est un mulipe de m,
	// on pose la contrainte sur ce dernier sujet
	// pas indispensable (l'effectif serait forcément multiple de m)
	// mais pour uen propagation plus efficace (?)
	if (getNbEleves() % m == 0) {
	    solver.post(IntConstraintFactory.mod(effectifs[sujetNonTraite], multi, zero));
	}
    }


    public void defineStrategy() {
	// strategie 1 : basique
	if (niveauResolution == TOUS_SUJETS || niveauResolution == RANG_MINIMAL) {
	    solver.set(IntStrategyFactory.impact(sujetsAffectesAuxEleves,0));
	    return;
	}

	// strategie 2


	IntVar objectif;
	switch (niveauResolution) {
	    case OPTIMISATION_DICHOTOMIQUE :
	    case OPTIMISATION :
	    case OPTIMISATION_AVEC_RANG_MAX_AUTORISE :
		objectif = coutTotalAffectations;
		break;
	    case MINIMISER_RANG_MAX :
	    case OPTIMISATION_PAR_ETAPES :
		objectif = rangMaxSujetAffecte;
		break;
	    default :
		//objectif = rangMaxSujetAffecte;
		objectif = null;
		break;

	}

	System.out.println("strategy, objectif = "+ objectif + " res = " + niveauResolution);


	AbstractStrategy activity = IntStrategyFactory.activity(sujetsAffectesAuxEleves,0);

	if (objectif != null) {
	    solver.set(new ObjectiveStrategy(objectif, 
	                                     OptimizationPolicy.DICHOTOMIC),
	                                     IntStrategyFactory.lastConflict(solver,activity));
	}
	else {
	    solver.set(IntStrategyFactory.lastConflict(solver,activity));
	}
	SMF.geometrical(solver,
	                getNbEleves()/10,
	                1.01,
	                new FailCounter(getNbEleves()),
	                Integer.MAX_VALUE);


    }

    public void defineStrategy(IntVar objectif) {
	// strategie 2
	AbstractStrategy activity = IntStrategyFactory.activity(sujetsAffectesAuxEleves,0);

	solver.set(new ObjectiveStrategy(objectif, 
	                                 OptimizationPolicy.DICHOTOMIC),
	                                 IntStrategyFactory.lastConflict(solver,
	                                                                 activity));

	SMF.geometrical(solver,
	                getNbEleves()/10,
	                1.01,
	                new FailCounter(getNbEleves()),
	                Integer.MAX_VALUE);	
    }

    public void buildModelAndStrategy() {
	makeModel();
	System.out.println("fin modelisation");
	Chatterbox.showSolutions(solver);
	defineStrategy();	 	 
    }

    public void rebuild() {
	System.out.println("debut rebuild");
	makeModel();
	Chatterbox.showSolutions(solver);
	defineStrategy(coutTotalAffectations);	
	System.out.println("fin rebuild");
    }

    public boolean coutAcceptable(int valeurCout) {
	solver.post(IntConstraintFactory.arithm(coutTotalAffectations, "<=", valeurCout));
	return solver.findSolution();	 
    }

    public void solve() {
	switch (niveauResolution) {
	    case TOUS_SUJETS : 
	    case RANG_MINIMAL :
		solver.findSolution();
		break;
	    case MINIMISER_RANG_MAX :
		solver.findOptimalSolution(ResolutionPolicy.MINIMIZE, rangMaxSujetAffecte);
		break;
	    case OPTIMISATION :
		solver.findOptimalSolution(ResolutionPolicy.MINIMIZE, coutTotalAffectations);
		break;
	    case OPTIMISATION_PAR_ETAPES :
		// Etape 1 : minimisation du rang maximum, mémorisation du cout associé
		solver.findOptimalSolution(ResolutionPolicy.MINIMIZE, rangMaxSujetAffecte);
		System.out.println(prettySolution());
		int coutObtenu = coutTotalAffectations.getValue(); 
		int rangMaxObtenu = rangMaxSujetAffecte.getValue();

		// Etape 2 : optimisation par minimisation du cout
		// (on utilise le coût obtenu lors de l'étape 1 pour réduire la borne sup)

		// on réinitialise le modèle
		rebuild();

		// utilisation de la borne du cout obtenue étape 1
		solver.post(IntConstraintFactory.arithm(coutTotalAffectations, "<=", coutObtenu));
		// utilisation du rang max obtenu etape 1
		solver.post(IntConstraintFactory.arithm(rangMaxSujetAffecte, "<=", rangMaxObtenu));
		// recherche de le la solution de cout optimal
		solver.findOptimalSolution(ResolutionPolicy.MINIMIZE, coutTotalAffectations);
		break;
	    case OPTIMISATION_DICHOTOMIQUE :
		solver.findOptimalSolution(ResolutionPolicy.MINIMIZE, coutTotalAffectations);
		break;
	    case OPTIMISATION_AVEC_RANG_MAX_AUTORISE :
		solver.post(IntConstraintFactory.arithm(rangMaxSujetAffecte, "<=", this.rangMaxChoixEleve));
		solver.findOptimalSolution(ResolutionPolicy.MINIMIZE, coutTotalAffectations);
		break;
	}	
    }

    public String prettySolution() {
	int nbSujets = getNbSujets();
	int nbEleves = getNbEleves();

	String sol="Affectations sujets ipipip :\n";
	for (int p=0 ; p < nbSujets ; p++) {
	    sol += nbGroupesParSujet[p] + "\t--";
	    sol += "\t  " + effectifs[p] + "\n";
	}
	for (int numEleve = 0 ; numEleve < nbEleves ; numEleve++) {
	    int sujet = sujetsAffectesAuxEleves[numEleve].getValue();
	    int rang = ld.getEleve(numEleve).getRang(sujet);
	    sol += sujetsAffectesAuxEleves[numEleve] + " rang : " + rang + "\n"; 
	}
	sol += "cout = " + coutTotalAffectations.getValue() + "\n";
	if (niveauResolution == MINIMISER_RANG_MAX || niveauResolution == OPTIMISATION_PAR_ETAPES) {
	    sol += "rang max = " + rangMaxSujetAffecte.getValue() + "\n";
	}
	return sol; 
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	int niveauResolution;
	//niveauResolution = TOUS_SUJETS;
	niveauResolution = RANG_MINIMAL;
	//niveauResolution = MINIMISER_RANG_MAX;
	//niveauResolution = OPTIMISATION;
	//niveauResolution = OPTIMISATION_PAR_ETAPES;
	niveauResolution = OPTIMISATION_DICHOTOMIQUE;
	niveauResolution = OPTIMISATION_AVEC_RANG_MAX_AUTORISE;

	LectureDonnees ld;
	//ld = new LectureDonnees("data-cb1-2012.txt");
	//ld = new LectureDonneesAvantEvolution("data-cb1-2012-partiel.txt");
	//ld = new LectureDonnees("data-annee1-2012.txt");
	ld = new LectureDonneesApresEvolution("data-evolution-cb1-2012-partiel.txt");
	//ld = new LectureDonneesAvantEvolution("data-A1-2015.txt");
	
	
	System.out.println("Donnees : \n" + ld);

	Ipipip ip = new Ipipip(ld, niveauResolution, RANG_MAX_AUTORISE);

	ip.buildModelAndStrategy();

	ip.solve();

	System.out.println(ip.prettySolution());
	System.out.println("Fin resolution...");
	Chatterbox.printStatistics(ip.solver);
    }

}
