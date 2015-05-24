package models.parser.helper;

import java.io.File;

/**
 * classe de chargement d'un fichier CSV
 * 
 * @author Cédric
 *
 */
public class CsvHelper {
	/**
	 * renvoie un objet file correspondant au chemin passé en paramétre
	 * 
	 * @param Path
	 * @return
	 */
	public static File getRessource(String Path) {
		// File f = new File("");
		// on prend toujour le meme pour l'instant
		// Path = f.getAbsolutePath()+f.separator+"donnees"+f.separator+Path;
		return new File(Path);
	}
}