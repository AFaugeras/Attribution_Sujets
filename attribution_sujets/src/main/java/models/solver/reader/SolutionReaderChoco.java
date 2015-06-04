package models.solver.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;

/**
 * Classe de lecture du fichiers de sorties de Choco.
 *
 */
public class SolutionReaderChoco{

	/**
	 * Lecture d'un fichier correspondant a la solution venant du Solveur Choco.
	 * Celui-ci doit suivre le format suivant :
	 * 0 (Si pas de solution) ou 1 (Sinon)
	 * 2 (Sujet attribue au premier eleve)
	 * 4 (Sujet attribue au second eleve)
	 * ...
	 * 
	 * @param filename Nom de fichier d'entree.
	 * @param data Model a completer via la lecture du fichier.
	 * @throws ReaderException Probleme de lecture du fichier.
	 * @throws NotFoundSolutionException Absence de solution.
	 */
	public static void read(String filename, Model data) throws ReaderException, NotFoundSolutionException{
		
		List<Person> persons = data.getPersons();
		List<Subject> subjects = data.getSubjects();
		
		int nbPersons = persons.size();
		
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			
			line = br.readLine();
			
			if(line.equals("0")){
				br.close();
				fr.close();
				throw new NotFoundSolutionException();
			}
			else{
				line = br.readLine();
				int i = 0;
				while(i < nbPersons && line != null){
					persons.get(i).setAssigned(subjects.get(Integer.parseInt(line) - 1));
					
					i++;
					line = br.readLine();
				}
			}
			
			br.close();
			fr.close();
		}
		catch(IOException e){
			throw new ReaderException("Erreur de lecture du fichier : " + filename);
		}
	}
}
