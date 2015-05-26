package models.adaptor;

/**
 * Adaptateur pour le solveur Choco.
 * Celui-ci permet à partir d'un modele de donnes de generer des chaines de caracteres 
 * representants les donnees essentielles au bon fonctionnement de Choco
 */
public interface AdaptorChoco extends Adaptor{

	/**
	 * Accesseur du nombre de partcipants.
	 * @return nombre de partcipants sous forme de chaines de caracteres
	 */
	public StringBuilder getNbPersons();
	
	/**
	 * Accesseur du nombre de sujet.
	 * @return nombre de sujets sous forme de chaines de caracteres
	 */
	public StringBuilder getNbSubjects();
	
	/**
	 * Accesseur des fourchettes d'éffectifs des sujets.
	 * @return Chaines de caractere representant les effectifs. Chaque ligne representant un sujet et contenant l'effectif minimal et maximal.
	 */
	public StringBuilder getSizeRange();
	
	/**
	 * Accesseur des fourchettes de cardinalites des sujets. Un sujet peut par exemple etre present de 0 a 2 fois.
	 * @return Chaines de caractere representant les cardinalites. Chaque ligne representant un sujet et contenant la cardinalite minimal et maximal.
	 */
	public StringBuilder getCardRange();
	
	/**
	 * Accesseur du nombre minimum de sujets assignes. 1 signifie par exemple qu'un sujet peut ne pas etre assigne au profit d'un autre.
	 * @return Nombre minimum de sujets assignes sous forme de chaines de caracteres
	 */
	public StringBuilder getMinimumAssignedSubject();
	
	/**
	 * Accesseur des couts de repartition. En clair on precise le cout du choix 1, choix2, ...
	 * Cet information est essentiel au solver qui va chercher a obtenir le cout minimum.
	 * En general les couts de repartition sont exponentielles. Ainsi personne se verra assigne son choix 5.
	 * @return chaine de caracteres representant les couts de repartition.
	 */
	public StringBuilder getRepartitionCost();
	
	/**
	 * Accesseur des choix des partcipants. Les sujets choisis via Campus ont les rangs inférieurs. Tous les autres sujets ont le meme rang.
	 * @return chaine de caracteres representant les choix des participants.
	 * Une ligne representant un participant. Elle est constitue de son identifiant Campus et du rang des sujets (rang sujet 1 puis rang sujet 2, ...)
	 * L'ordre etant celui d'apparation dans la liste de sujets du Model.
	 */
	public StringBuilder getChoices();
	
	/**
	 * Accesseur des refus des participants. Un participant a en effet la possibilite des sujets qu'il ne veut absolument pas avoir.
	 * @return chaine de caracteres representant les refus des participants.
	 * Une ligne representant un participant. Elle est constitue du nombre de refus puis du numero des sujets a eviter.
	 * Le numero des sujets correspondant a leur place dans la liste de sujets du Model.
	 */
	public StringBuilder getRejects();

	/**
	 * Accesseur de la multiplicite des groupes.
	 * Une multiplicite de deux signifie par exemple que l'effectif final des groupes doit être pair. Utile pour des activites se déroulant en Binome.
	 * @return Multiplicite des groupes sous forme de chaines de caracteres
	 */
	public StringBuilder getMultiplicity();
	
}
