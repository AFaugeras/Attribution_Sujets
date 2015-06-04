/* ipipip-modele.mod */

/***********************************************************************************************/
/* modif le 15/12/2014                                                                         */
/*                                                                                             */
/* adaptation pour les intersemestres : effectifs éventuellement différents pour chaque projet */
/* (ils étaient identiques pour ipipip  )                                                       */
/***********************************************************************************************/



/* ensemble des projets a affecter (20 en 2008-2009) */
set projets;

/* ensemble des eleves */
set eleves;

/* isAllocated[p,e] = 1 si le projet p est affecte a l'eleve e, 0 sinon */
var isAllocated{projets,eleves} binary;

/* cout de l'affectation du projet p a l'eleve e (fonction du rang de p dans les preferences de e) */
param c{projets,eleves} integer;

/* nb minimum de groupes d'eleves par projet */
param nbMinGpes{projets} integer;

/* nb maximum de groupes d'eleves par projet */
param nbMaxGpes{projets} integer;

/* taille minimum d'un groupe d'eleves */
/* param nMin integer; */
/* Modif INTERSEMESTRE : taille minimum d'un groupe d'eleves PAR PROJET */
param nMin{projets} integer;

/* taille maximum d'un groupe d'eleves */
/* param nMax integer; */
/* Modif INTERSEMESTRE : taille maximum d'un groupe d'eleves PAR PROJET */
param nMax{projets} integer;

/* nombre minimum de sujets differents devant etre affectes */
param nbMiniSujets integer; 

var nb{projets} integer; 

var pris{projets} binary;

/*******************************************************************************************************************/
/* ************** PhD ESSAI *************** */
/* var cout{eleves} integer; */


/*******************************************************************************************************************/


minimize cost: sum{p in projets, e in eleves} c[p,e] * isAllocated[p,e];

/***********************
* 1 projet exactement par eleve 
***********************/
s.t. tousAffectes{e in eleves}: sum{p in projets} isAllocated[p,e] = 1;

/***********************
* un groupe contient entre nMin et nMax eleves (inclus)
* le nombre d'eleves auxquels est affecte un projet est donc egal au nombre de groupes multiplie par cet intervalle 
***********************/

/* borne min de l'intervalle : nb Groupes * nb mini par groupe */
/* s.t. nbProjetsMin{p in projets}: sum{e in eleves} isAllocated[p,e] >= nb[p] * nMin; */
/* MODIF INTERSEMESTRES */
s.t. nbProjetsMin{p in projets}: sum{e in eleves} isAllocated[p,e] >= nb[p] * nMin[p];

/* borne max de l'intervalle : nb Groupes * nb maxi par groupe */
/* s.t. nbProjetsMax{p in projets}: sum{e in eleves} isAllocated[p,e] <= nb[p] * nMax; */
/* MODIF INTERSEMESTRES */
s.t. nbProjetsMax{p in projets}: sum{e in eleves} isAllocated[p,e] <= nb[p] * nMax[p];



/***********************
* nb total de groupes = nb total de projets
* (ou autant de groupes que d'encadrants)
***********************/
/*  s.t. nbEncadrants: sum{p in projets} nb[p] = sum{p in projets} 1; */

/* nb minimum de groupes pour chaque projet */
s.t. nbMinParProjet{p in projets}: nb[p] >= nbMinGpes[p];

/* nb maximum de groupes pour chaque projet */
s.t. nbMaxParProjet{p in projets}: nb[p] <= nbMaxGpes[p];

/* pris[p] = 0 si nb[p] = 0 */
s.t. nonPrisSiNbNul{p in projets}: pris[p] <= nb[p];

s.t. nombreMiniSujetsPris: sum{p in projets} pris[p] >= nbMiniSujets;


/* ************** PhD ESSAI *************** */
/* s.t. couts{e in eleves}: cout[e] = sum{p in projets} c[p,e] * isAllocated[p,e]; */

/* ************** PhD ESSAI *************** */
/* display{e in eleves}: sum{p in projets} c[p,e] * isAllocated[p,e]; */
solve;
/* display{e in eleves, p in projets}: e, p, isAllocated[p,e], c[p,e], isAllocated[p,e] * c[p,e];*/

end;
