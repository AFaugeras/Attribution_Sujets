package parse.helper;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * classe de chargement d'un fichier CSV
 * @author C�dric
 *
 */
public class CsvHelper {
/**
 * renvoie un objet file correspondant au chemin pass� en param�tre
 * @param Path
 * @return
 */
	public static File getRessource( String Path){
		//File f = new File("");
		// on prend toujour le meme pour l'instant
		//Path = f.getAbsolutePath()+f.separator+"donnees"+f.separator+Path;
		return new File(Path);
	}
}
