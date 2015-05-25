package models.factory;
import models.bean.Subject;

public class SubjectFactory {

	
	/**
	 * permet de creer un object subject grace au tableau d'information
	 * Format du fchier : NomSujet,	maxSize, minSize, multiple,cardMin,cardMax
	 * @param Data
	 * @return
	 */
	public static Subject createSubject(String[] data, int id){
		 Subject subject= new Subject();
		 subject.setId(id);
		 subject.setLabel(data[0]);
		 subject.setMaxSize(Integer.valueOf(data[1]));
		 subject.setMinSize(Integer.valueOf(data[2]));
		 subject.setMultiple(Integer.valueOf(data[3]));
		 subject.setCardMin(Integer.valueOf(data[4]));
		 subject.setCardMax(Integer.valueOf(data[5]));
		 
		 return subject;
		
	}
}
