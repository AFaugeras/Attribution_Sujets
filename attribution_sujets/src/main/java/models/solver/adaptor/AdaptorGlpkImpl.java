package models.solver.adaptor;

import java.util.List;

import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;

/**
 * Implementation de l'adaptateur Glpk pour le modele de donnees Model.
 */
public class AdaptorGlpkImpl implements AdaptorGlpk {

	/**
	 * Modele de donnees.
	 */
	private Model data;

	public AdaptorGlpkImpl(Model data){
		this.data = data;
	}
	
	@Override
	public StringBuilder getPersons() {
		StringBuilder ret = new StringBuilder();
		
		List<Person> persons = this.data.getPersons();
		int nbPersons = persons.size();
		
		ret.append("set eleves :=");
		
		for(int i = 0; i < nbPersons; i++){
			ret.append(" \te" + i);
		}
		
		ret.append("\t;");
		
		return ret;
	}

	@Override
	public StringBuilder getSubjects() {
		StringBuilder ret = new StringBuilder();
		
		List<Subject> subjects = this.data.getSubjects();
		int nbSubjects = subjects.size();
		
		ret.append("set projets :=");
		
		for(int i = 0; i < nbSubjects; i++){
			ret.append(" \tp" + i);
		}
		
		ret.append("\t;");
		
		return ret;
	}

	@Override
	public StringBuilder getMinCardSubjects() {
		StringBuilder ret = new StringBuilder();
		
		List<Subject> subjects = this.data.getSubjects();
		int nbSubjects = subjects.size();
		
		ret.append("param nbMinGpes :=");
		
		for(int i = 0; i < nbSubjects; i++){
			ret.append("\tp" + i + " \t" + subjects.get(i).getMinCard());
		}
		
		ret.append("\t;");
		
		return ret;
	}

	@Override
	public StringBuilder getMaxCardSubjects() {
		StringBuilder ret = new StringBuilder();
		
		List<Subject> subjects = this.data.getSubjects();
		int nbSubjects = subjects.size();
		
		ret.append("param nbMaxGpes :=");
		
		for(int i = 0; i < nbSubjects; i++){
			ret.append("\tp" + i + " \t" + subjects.get(i).getMaxCard());
		}
		
		ret.append("\t;");
		
		return ret;
	}

	@Override
	public StringBuilder getMultiplicity() {
		return new StringBuilder("param tailleEquipe:=\t" + this.data.getConstraint().getMultiplicity() + "\t;");
	}
	
	@Override
	public StringBuilder getMinSizeSubjects() {
		StringBuilder ret = new StringBuilder();
		
		List<Subject> subjects = this.data.getSubjects();
		int nbSubjects = subjects.size();
		
		ret.append("param nMin :=");
		
		for(int i = 0; i < nbSubjects; i++){
			ret.append("\tp" + i + " \t" + subjects.get(i).getMinSize());
		}
		
		ret.append("\t;");
		
		return ret;
	}

	@Override
	public StringBuilder getMaxSizeSubjects() {
		StringBuilder ret = new StringBuilder();
		
		List<Subject> subjects = this.data.getSubjects();
		int nbSubjects = subjects.size();
		
		ret.append("param nMax :=");
		
		for(int i = 0; i < nbSubjects; i++){
			ret.append("\tp" + i + " \t" + subjects.get(i).getMaxSize());
		}
		
		ret.append("\t;");
		
		return ret;
	}

	@Override
	public StringBuilder getMinimumAssignedSubject() {
		StringBuilder ret = new StringBuilder();
				
		ret.append("param nbMiniSujets :=");
		
		ret.append("\t5");
		
		ret.append("\t;");
		
		return ret;
	}

	@Override
	public StringBuilder getChoices() {
		StringBuilder ret = new StringBuilder();
		
		List<Subject> subjects = this.data.getSubjects();
		int nbSubjects = subjects.size();
		List<Person> persons = this.data.getPersons();
		int nbPersons = persons.size();
		List<Long> costs = data.getConstraint().getWeights();
		Subject current = null;
		List<Subject> choices = null;
		
		ret.append("param c :");
		
		//Liste des eleves
		for(int i = 0; i < nbPersons; i++){
			ret.append("\te" + i);
		}
		
		ret.append("\t\t:=");
		
		//Parcours des sujets
		for(int i = 0; i < nbSubjects; i++){
			current = subjects.get(i);
			
			ret.append("\n\tp" + i);
			
			//Choix des eleves pour le sujet courant
			for(Person p : persons){
				ret.append("\t");
				
				choices = p.getChoices();
				
				long defaultCost = costs.get(choices.size());
				int rang = choices.indexOf(current);
				
				if(rang == -1){
					ret.append(defaultCost);
				}
				else{
					ret.append(costs.get(rang));
				}
			}
			
			ret.append("\t\t");
		}
		
		ret.append("\n\t;");
		
		return ret;
	}
}
