package models.solver.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;

public class SolutionReaderGlpk {

	/**
	 * Lecture d'un fichier correspondant a la solution venant du Solveur Glpk.
	 * @param filename Nom de fichier d'entree.
	 * @param data Model a completer via la lecture du fichier.
	 * @throws ReaderException Probleme de lecture du fichier.
	 * @throws NotFoundSolutionException Absence de solution.
	 */
	public static void read(String filename, Model data) throws ReaderException, NotFoundSolutionException{
		
		List<Person> persons = data.getPersons();
		List<Subject> subjects = data.getSubjects();
		int nbPersons = persons.size();
		int nbSubjects = subjects.size();
		
		int nbUselessLine = 3;
		
		int read = 0;
		
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			
			for(int i = 0; i < nbUselessLine; i++){
				read ++;
				br.readLine();
			}
			
			for(Person p : persons){
				line = br.readLine();
				read ++;
				if(Integer.parseInt(line) == 0){
					throw new NotFoundSolutionException("Aucune solution d'après le solveur Glpk");
				}
			}
			
			nbUselessLine = 5 * nbSubjects + 1;
			
			for(int i = 0; i < nbUselessLine; i++){
				br.readLine();
				read ++;
			}

			for(Subject currentSubj : subjects){
				for(Person currentPers : persons){
					line = br.readLine();
					
					if(Integer.parseInt(line) == 1){
						currentPers.setAssigned(currentSubj);
					}
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
