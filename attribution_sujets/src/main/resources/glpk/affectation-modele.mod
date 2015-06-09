/* fichier initial : ipipip-modele.mod */

/* 2 modifications :
/***********************************************************************************************/
/* 1)                                                                                          */
/* adaptation pour les ipipip 2015 : travail en binome (par extension en groupes)              */
/* (ils étaient individuels avant)                                                             */
/* on impose que                                                                               */
/* - chaque groupe                                                                             */
/* - et donc l’effectif total (redondant)                                                      */
/* soient des multiple de la taille des groupes (1 en individuel, 2 en binômes, etc.)          */
/* ne fonctionne que si l’effectif total est un multiple de la taille des groupes              */
/***********************************************************************************************/
/* 2) adaptation initialement pour les intersemestres : effectifs éventuellement               */
/*    différents pour chaque projet                                                            */
/* (ils étaient identiques pour ipipip)                                                        */
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
/* Modif 2 : effectifs variables : taille minimum d'un groupe d'eleves PAR PROJET */
param nMin{projets} integer;

/* taille maximum d'un groupe d'eleves */
/* param nMax integer; */
/* Modif 2 : effectifs variables : taille maximum d'un groupe d'eleves PAR PROJET */
param nMax{projets} integer;

/* Modif 1 : projets par équipes d’élèves (par ex. tailleEquipe = 2 : par binômes, 1 : individuellement) */
param tailleEquipe integer; 

/* Modif 1 : nombre d’équipes par projet (eleves seuls si travail individuel, binômes par 2, etc) */
var nbEquipes{projets} integer; 

/* Modif 1 : nb total d’équipes (nb eleves / taille equipes) */
var nbTotalEquipes integer; 

/* nombre minimum de sujets differents devant etre affectes */
param nbMiniSujets integer; 

var nb{projets} integer; 

var pris{projets} binary;


/*************************************************************************************/
/* objectif                                                                          */
/*************************************************************************************/
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
/* MODIF 2 : effectifs variables : nbMin pour chaque projet */
s.t. nbProjetsMin{p in projets}: sum{e in eleves} isAllocated[p,e] >= nb[p] * nMin[p];

/* borne max de l'intervalle : nb Groupes * nb maxi par groupe */
/* s.t. nbProjetsMax{p in projets}: sum{e in eleves} isAllocated[p,e] <= nb[p] * nMax; */
/* MODIF 2 : effectifs variables : nbMax pour chaque projet */
s.t. nbProjetsMax{p in projets}: sum{e in eleves} isAllocated[p,e] <= nb[p] * nMax[p];

/***************************/
/* Modif 1 : multiplicité  */
/***************************/

/* multiplicité : le nb d’élèves dans chaque projet est un multiple du paramètre de multiplicité (tailleEquipe) */
 s.t. nbProjetsMult{p in projets}: sum{e in eleves} isAllocated[p,e] = nbEquipes[p] * tailleEquipe; 

/* multiplicité : nb total d’eleves forcement multiple de tailleEquipe 
 s.t. nbProjetsTotalMult: sum{e in eleves} 1 = nbTotalEquipes * tailleEquipe; 


/***********************
* nb total de groupes = nb total de projets
* (ou autant de groupes que d'encadrants)
***********************/
s.t. nbEncadrants: sum{p in projets} nb[p] = sum{p in projets} 1;

/* taille minimum du groupe pour chaque projet */
s.t. nbMinParProjet{p in projets}: nb[p] >= nbMinGpes[p];

/* taille maximum du groupe pour chaque projet */
s.t. nbMaxParProjet{p in projets}: nb[p] <= nbMaxGpes[p];

/* pris[p] = 0 si nb[p] = 0 */
s.t. nonPrisSiNbNul{p in projets}: pris[p] <= nb[p];

s.t. nombreMiniSujetsPris: sum{p in projets} pris[p] >= nbMiniSujets;

/*
solve;
display{e in eleves, p in projets}: e, p, isAllocated[p,e], c[p,e], isAllocated[p,e] * c[p,e];
*/

end;
