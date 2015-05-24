package parse.helper;

import java.io.File;

import junit.framework.TestCase;
import models.parser.helper.CsvHelper;

import org.junit.Test;

public class CsvHelperTest extends TestCase {

	@Test
	public void testOvertureFichier() {
		File f = new File("");
		CsvHelper.getRessource(f.getAbsolutePath() + f.separator + "donnees"
				+ f.separator + "Choix_des_sujets_2015.csv");
	}

	@Test
	public void testOvertureFichierFail() {
		try {
			CsvHelper.getRessource(null);
		} catch (NullPointerException e) {
			System.out.println("exception conformément trouvée");
		}
	}
}
