package models.solver.adaptor;

/**
 * Adaptateur pour le solveur Glpk.
 * Celui-ci permet à partir d'un modele de donnes de generer des chaines de caracteres 
 * representants les donnees essentielles au bon fonctionnement de Glpk.
 */
public interface AdaptorGlpk extends Adaptor{

	/**
	 * Accesseur des partcipants.
	 * @return partcipants sous forme de chaines de caracteres
	 */
	public StringBuilder getPersons();
	
	/**
	 * Accesseur des sujets.
	 * @return sujets sous forme de chaines de caracteres
	 */
	public StringBuilder getSubjects();
	
	/**
	 * Accesseur des bornes inferieures des cardinalites des sujets.
	 * @return Chaines de caractere representant les bornes inferieures des cardinalites.
	 */
	public StringBuilder getMinCardSubjects();
	
	/**
	 * Accesseur des bornes superieures des cardinalites des sujets.
	 * @return Chaines de caractere representant les bornes superieures des cardinalites.
	 */
	public StringBuilder getMaxCardSubjects();
	
	/**
	 * Accesseur des bornes inferieures des effectifs des sujets.
	 * @return Chaines de caractere representant les bornes inferieures des effectifs.
	 */
	public StringBuilder getMinSizeSubjects();
	
	/**
	 * Accesseur des bornes superieures des effectifs des sujets.
	 * @return Chaines de caractere representant les bornes superieures effectifs.
	 */
	public StringBuilder getMaxSizeSubjects();
	
	/**
	 * Accesseur du nombre minimum de sujets assignes. 1 signifie par exemple qu'un sujet peut ne pas etre assigne au profit d'un autre.
	 * @return Nombre minimum de sujets assignes sous forme de chaines de caracteres
	 */
	public StringBuilder getMinimumAssignedSubject();
		
	/**
	 * Accesseur des choix des partcipants. Les sujets choisis via Campus ont des couts inferieurs. Tous les autres sujets ont le meme cout
	 * @return chaine de caracteres representant les choix des participants.
	 * Une ligne representant un sujet. Elle est constitue de son identifiant et du cout d'attribution pour chaque participant (id cout eleve 1 puis cout eleve 2, ...)
	 * L'ordre etant celui d'apparation dans la liste des participants du Model.
	 */
	public StringBuilder getChoices();
}
