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
		 
		 int i=1;
		 subject.setLabel(data[i++]) ;// obligatoire
		 
		 if(notNull(data, i)) subject.setMinSize(Integer.valueOf(data[i]));else subject.setMinSize(0);
		 
		 i++;
		 if(notNull(data, i))subject.setMaxSize(Integer.valueOf(data[i]));else subject.setMaxSize(0) ;
		 
		 i++;
		 if(notNull(data, i)) subject.setMinCard(Integer.valueOf(data[i]));else subject.setMinCard(0);
		 
		 i++;
		 if(notNull(data, i)) subject.setMaxCard(Integer.valueOf(data[i]));else subject.setMaxCard(0);
		 
		 return subject;
		
	}
	
	private static boolean notNull(String[] data, int index){
		return data.length>index && (!data[index].isEmpty());
	}
}
