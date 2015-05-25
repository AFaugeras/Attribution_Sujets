package models.parser.user;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.bean.Person;
import models.factory.UserFactory;
import models.parser.AbstractParser;
import models.parser.DataCleaner;


/**
 * classe permettant de parser un fichier csv representant la liste des utilisateurs
 * @author Cédric
 *
 */
public class ParserCsvUserList extends AbstractParser {

	private List<Person> UserList;
	
	public ParserCsvUserList() {
		this.UserList= new ArrayList<Person>();
	}
	public void ParseUserList(File sourceFile) throws IOException{
		List<String> datas = this.readfile(sourceFile);
		
		int size = datas.size(); 						// compte le nombre total de ligne dans le fichier
		int index; 										// pour se deplacer dans le tableau de données source nombre de champ maximum d'une réponse
		String[] line = new String[datas.get(0).split("	").length]; // donne la taille max du tableau
		// on va parcourir chaque lignes et creer un objet Person contenant les informations adéquates
		for (index = 1; index < size; index++) {
			String data = datas.get(index);
			line = data.split("	");
			Person person = UserFactory.createUser(line);// on creer un objet User
			UserList.add(person); 
		}
		

	}
	/**
	 * renvoi la liste des user sans doublon
	 * @return
	 */
	public List<Person> getUserList() {
		return DataCleaner.cleanPerson(UserList);
	}
	
	/**
	 * donne le nombre d'utilisateur 
	 */
	public int getNbUser(){
		return UserList.size();
	}
	
	
	
}
