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
		
		
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			
			for(int i = 0; i < nbUselessLine; i++){
				br.readLine();
			}
			
			for(Person p : persons){
				line = br.readLine();
				
				if(Integer.parseInt(line) == 0){
					throw new NotFoundSolutionException();
				}
			}
			
			nbUselessLine = 4 * nbSubjects + 1;
			
			for(int i = 0; i < nbUselessLine; i++){
				br.readLine();
			}
			
			for(Person current : persons){
				for(Subject subj : subjects){
					line = br.readLine();
					
					if(Integer.parseInt(line) == 1){
						current.setAssigned(subj);
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
