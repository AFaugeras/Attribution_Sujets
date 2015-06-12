package models.solver.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;

/**
 * Classe d'implémentation de SolutionReaderChoco
 */
public class SolutionReaderChocoImpl implements SolutionReaderChoco{
	
	/**
	 * Modele de donnees a mettre a jour grace a la solution
	 */
	private Model data;
	
	public SolutionReaderChocoImpl(Model data)
	{
		this.data = data;
	}
	
	@Override
	public void read(String pathFile) throws ReaderException, NotFoundSolutionException
	{	
		List<Person> persons = this.data.getPersons();
		List<Subject> subjects = this.data.getSubjects();
		
		int nbPersons = persons.size();
		
		try {
			FileReader fr = new FileReader(pathFile);
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			
			line = br.readLine();
			
			//Absence de solution ??
//			if(line.equals("0")){
//				br.close();
//				fr.close();
//				throw new NotFoundSolutionException("Aucune solution d'après le solveur Choco");
//			}
			
			line = br.readLine();
			int i = 0;
			while(i < nbPersons){
				
				//Ligne inexistante
				if(line == null){
					br.close();
					fr.close();
					throw new NotFoundSolutionException("Aucune solution d'après le solveur Choco");
				}
				
				//Assignation du sujet.
				persons.get(i).setAssigned(subjects.get(Integer.parseInt(line) - 1));
					
				i++;
				line = br.readLine();
			}
			
			//Fermeture
			br.close();
			fr.close();
		}
		catch(IOException e){
			throw new ReaderException("Erreur de lecture du fichier : " + pathFile);
		}
	}
}
