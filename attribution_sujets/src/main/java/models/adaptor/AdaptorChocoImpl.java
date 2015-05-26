package models.adaptor;

import java.util.List;

import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;

/**
 * Implementation de l'adaptateur Choco pour le modele de donnees Model.
 */
public class AdaptorChocoImpl implements AdaptorChoco{

	/**
	 * Modele de donnees.
	 */
	private Model data;

	public AdaptorChocoImpl(Model data){
		this.data = data;
	}

	@Override
	public StringBuilder getNbPersons(){
		return new StringBuilder("" + this.data.getPersons().size());
	}
	
	@Override
	public StringBuilder getNbSubjects(){
		return new StringBuilder("" + this.data.getSubjects().size());
	}
	
	@Override
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
	
	@Override
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
	
	@Override
	public StringBuilder getMinimumAssignedSubject(){
		return new StringBuilder("" + 0);
	}
	
	@Override
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
	
	@Override
	public StringBuilder getChoices(){
		StringBuilder ret = new StringBuilder();
		
		Person current = null;
		
		List<Subject> subjects = this.data.getSubjects();
		List<Person> persons = this.data.getPersons();
		
		int nbPersons = persons.size();
		int nbSubjects = subjects.size();
		

		
		for(int i = 0; i < nbPersons; i++){
			current = persons.get(i);
			
			ret.append(current.getIDcampus());
			List<Subject> choices = current.getChoices();
			int nbChoices = choices.size();
			ret.append("\t" + nbChoices);
			int defaultRank = nbSubjects - nbChoices;
			
			Subject currentSubject = null;
			for(int j = 0; j < nbSubjects; j++){
				currentSubject = subjects.get(j);
				
				ret.append("\t");
				
				int rank = choices.indexOf(currentSubject);
				if(rank == -1){
					ret.append(defaultRank);
				}
				else{
					ret.append(rank + 1);
				}			
			}
			
			ret.append("\n");
		}
		
		ret.deleteCharAt(ret.length()-1);
		
		return ret;
	}
	
	@Override
	public StringBuilder getRejects(){
		StringBuilder ret = new StringBuilder();
		
		List<Person> persons = this.data.getPersons();
		int nbPersons = persons.size();
		List<Subject> subjects = this.data.getSubjects();
		
		Person current = null;
		
		for(int i = 0; i < nbPersons; i++){
			current = persons.get(i);
			
			
			List<Subject> rejects = current.getRejects();
			int nbRejects = rejects.size();
			ret.append(nbRejects);	
			
			Subject currentReject = null;
			
			for(int j = 0; j < nbRejects; j++){
				currentReject = rejects.get(j);

				ret.append("\t");
				
				int rank = subjects.indexOf(currentReject);
				if(rank != -1){
					ret.append(rank + 1);
				}		
			}
			
			ret.append("\n");
		}
		
		ret.deleteCharAt(ret.length()-1);
		
		return ret;
	}
	
	@Override
	public StringBuilder getMultiplicity(){
		return new StringBuilder("" + 0);
	}
	
	
	
}

