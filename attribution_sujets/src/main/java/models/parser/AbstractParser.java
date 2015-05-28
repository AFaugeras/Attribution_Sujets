package models.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Methodes communes a tout les parsers
 * 
 *
 */
public abstract class AbstractParser {
	/**
	 * permet de renvoyer les données d'un fichier ligne a ligne dans un tableau
	 * de chaine de caractéres
	 * 
	 * @param sourceFile
	 * @return
	 * @throws IOException
	 */
	protected List<String> readfile(File sourceFile) throws IOException {

		List<String> result = new ArrayList<String>();
		FileReader fr = new FileReader(sourceFile);
		BufferedReader br = new BufferedReader(fr);
		// on met chaque ligne du fichier dans un tableau
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			result.add(line);
		}

		br.close();
		fr.close();
		return result;
	}
}
