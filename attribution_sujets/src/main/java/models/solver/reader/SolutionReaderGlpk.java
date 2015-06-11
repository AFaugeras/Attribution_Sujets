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
		int nbSubjects = subjects.size();
		
		//Les 3 premieres lignes sont pour le moment pas utilisees. Elle contiennent notamment le cout de la solution trouvee
		int nbUselessLine = 3;
				
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			
			//Lecture des lignes inutiles
			for(int i = 0; i < nbUselessLine; i++){
				br.readLine();
			}
			
			//Verification que tous les eleves ont un sujet assigne
			for(Person p : persons){
				line = br.readLine();
				
				//0 Signifie qu'aucun sujet n'est attribue a cet eleve
				if(Integer.parseInt(line) == 0){
					br.close();
					fr.close();
					throw new NotFoundSolutionException("Aucune solution d'après le solveur Glpk");
				}
			}
			
			//Ligne de description des sujets : minSize, maxSize, ...
			nbUselessLine = 6 * nbSubjects + 2;
			
			for(int i = 0; i < nbUselessLine; i++){
				br.readLine();
			}
			
			/* Lecture de la solution, celle-ci est ecrite de la facon suivante :
			 * Produit cartesien entre les sujets et les eleves. 
			 * La premiere ligne correspond au premier et second eleve. Celle-ci est a 1 si l'eleve 1 est assigne au sujet 1, 0 sinon.
			 * De la meme facon, la deuxieme ligne correspond au premier sujet et second eleve, ...
			*/
			for(Subject currentSubj : subjects){
				for(Person currentPers : persons){
					line = br.readLine();
					
					//1 Signifie que le sujet est assigne a cet eleve.
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
