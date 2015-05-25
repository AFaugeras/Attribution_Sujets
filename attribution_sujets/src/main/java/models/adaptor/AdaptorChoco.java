package models.adaptor;

import java.util.List;

import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;

/**
 * Adaptor du solver Choco.
 * Celui-ci permet à partir du modele de donnes interne(classe Model) de generer des chaines de caracteres 
 * representants les donnees essentielles au bon fonctionnement de Choco
 */
public class AdaptorChoco implements Adaptor{

	/**
	 * Modele de donnees.
	 */
	private Model data;

	/**
	 * Constructeur simple.
	 * @param data modele de donnees
	 */
	public AdaptorChoco(Model data) {
		super();
		this.data = data;
	}
	
	/**
	 * Accesseur du nombre de partcipants.
	 * @return nombre de partcipants sous forme de chaines de caracteres
	 */
	public StringBuilder getNbPersons(){
		return new StringBuilder("" + this.data.getPersons().size());
	}
	
	/**
	 * Accesseur du nombre de sujet.
	 * @return nombre de sujets sous forme de chaines de caracteres
	 */
	public StringBuilder getNbSubjects(){
		return new StringBuilder("" + this.data.getSubjects().size());
	}
	
	/**
	 * Accesseur des fourchettes d'éffectifs des sujets.
	 * @return Chaines de caractere representant les effectifs. Chaque ligne representant un sujet et contenant l'effectif minimal et maximal.
	 */
	public StringBuilder getSizeRange(){
		StringBuilder ret = new StringBuilder();
		
		List<Subject> subjects = this.data.getSubjects();
		Subject current = null;
		
		for(int i = 0; i < subjects.size(); i++){
			current = subjects.get(i);
			
			ret.append(current.getMinSize());
			ret.append("\t");
			ret.append(current.getMaxSize());
			ret.append("\n");
		}
		ret.deleteCharAt(ret.length()-1);
		
		return ret;
	}
	
	/**
	 * Accesseur des fourchettes de cardinalites des sujets. Un sujet peut par exemple etre present de 0 a 2 fois.
	 * @return Chaines de caractere representant les cardinalites. Chaque ligne representant un sujet et contenant la cardinalite minimal et maximal.
	 */
	public StringBuilder getCardRange(){
		StringBuilder ret = new StringBuilder();
		
		List<Subject> subjects = this.data.getSubjects();
		Subject current = null;
		
		for(int i = 0; i < subjects.size(); i++){
			current = subjects.get(i);
			
			ret.append(current.getCardMin());
			ret.append("\t");
			ret.append(current.getCardMax());
			ret.append("\n");
		}
		ret.deleteCharAt(ret.length()-1);
		
		return ret;
	}
	
	/**
	 * Accesseur du nombre minimum de sujets assignes. 1 signifie par exemple qu'un sujet peut ne pas etre assigne au profit d'un autre.
	 * @return Nombre minimum de sujets assignes sous forme de chaines de caracteres
	 */
	public StringBuilder getMinimumAssignedSubject(){
		return new StringBuilder("" + 0);
	}
	
	/**
	 * Accesseur des couts de repartition. En clair on precise le cout du choix 1, choix2, ...
	 * Cet information est essentiel au solver qui va chercher a obtenir le cout minimum.
	 * En general les couts de repartition sont exponentielles. Ainsi personne se verra assigne son choix 5.
	 * @return chaine de caracteres representant les couts de repartition.
	 */
	public StringBuilder getRepartitionCost(){
		StringBuilder ret = new StringBuilder();
		
		ret.append(7);
		ret.append("\t");
		ret.append(1);
		ret.append("\t");
		ret.append(5);
		ret.append("\t");
		ret.append(25);
		ret.append("\t");
		ret.append(1000);
		ret.append("\t");
		ret.append(20000);
		ret.append("\t");
		ret.append(150000);
		ret.append("\t");
		ret.append(1200000);

		return ret;
	}
	
	/**
	 * Accesseur des choix des partcipants. Les sujets choisis via Campus ont les rangs inférieurs. Tous les autres sujets ont le meme rang.
	 * @return chaine de caracteres representant les choix des participants.
	 * Une ligne representant un participant. Elle est constitue de son identifiant Campus et du rang des sujets (rang sujet 1 puis rang sujet 2, ...)
	 * L'ordre etant celui d'apparation dans la liste de sujets du Model.
	 */
	public StringBuilder getChoices(){
		StringBuilder ret = new StringBuilder();
		
		Person current = null;
		
		for(int i = 0; i < this.data.getPersons().size(); i++){
			current = this.data.getPersons().get(i);
			
			ret.append(current.getIDcampus());
			
			int nbSubjects = this.data.getSubjects().size();
			int defaultRank = nbSubjects - current.getChoices().size();
			
			Subject currentSubject = null;
			for(int j = 0; j < nbSubjects; j++){
				currentSubject = this.data.getSubjects().get(j);
				
				ret.append("\t");
				
				if(current.getChoices().indexOf(currentSubject) == -1){
					ret.append(defaultRank);
				}
				else{
					ret.append(current.getChoices().indexOf(currentSubject));
				}			
			}
			
			ret.append("\n");
		}
		
		ret.deleteCharAt(ret.length()-1);
		
		return ret;
	}
	
	/**
	 * Accesseur des refus des participants. Un participant a en effet la possibilite des sujets qu'il ne veut absolument pas avoir.
	 * @return chaine de caracteres representant les refus des participants.
	 * Une ligne representant un participant. Elle est constitue du nombre de refus puis du numero des sujets a eviter.
	 * Le numero des sujets correspondant a leur place dans la liste de sujets du Model.
	 */
	public StringBuilder getRejects(){
		StringBuilder ret = new StringBuilder();
		
		Person current = null;
		
		for(int i = 0; i < this.data.getPersons().size(); i++){
			current = this.data.getPersons().get(i);
			
			int nbRejects = current.getRejects().size();
			
			ret.append(nbRejects);
						
			Subject currentReject = null;
			for(int j = 0; j < nbRejects; j++){
				currentReject = this.data.getSubjects().get(j);
				
				ret.append("\t");
				
				if(this.data.getSubjects().indexOf(currentReject) != -1){
					ret.append(this.data.getSubjects().indexOf(currentReject));
				}		
			}
			
			ret.append("\n");
		}
		
		ret.deleteCharAt(ret.length()-1);
		
		return ret;
	}
	
	/**
	 * Accesseur de la multiplicite des groupes.
	 * Une multiplicite de deux signifie par exemple que l'effectif final des groupes doit être pair. Utile pour des activites se déroulant en Binome.
	 * @return Multiplicite des groupes sous forme de chaines de caracteres
	 */
	public StringBuilder getMultiplicity(){
		return new StringBuilder("" + 0);
	}
	
	
	
}
