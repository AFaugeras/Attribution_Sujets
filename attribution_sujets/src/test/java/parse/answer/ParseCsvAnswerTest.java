package parse.answer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;
import models.exception.fileformatexception.FileFormatException;
import models.factory.AnswerFactory;
import models.interfaces.I_Answer;
import models.parser.answer.ParserCsvAnswer;
import models.parser.helper.CsvHelper;

import org.junit.Test;

public class ParseCsvAnswerTest extends TestCase {

	@Test
	public void testSuppressionDoublon() throws IOException {
		File f = new File("");
		f = CsvHelper.getRessource(f.getAbsolutePath()  + f.separator + "src"
				+ f.separator + "test"+ f.separator+ "resources"
				+ f.separator+ "Choix_avec_doublon.csv");
		ParserCsvAnswer parser = new ParserCsvAnswer();
		try {
			parser.parseAnswer(f);
		} catch (FileFormatException e) {
			fail("Probleme de format d'entrée");
			e.printStackTrace();
		}
		List<I_Answer> answer = parser.getCleanedData();
		System.out.println(answer);
		assertEquals(answer.get(0).getDateSoumission(),createKeptAnswerDoublon().getDateSoumission());
		assertEquals(answer.get(0).getIdRepondant(), createKeptAnswerDoublon().getIdRepondant());
	}

	private I_Answer createKeptAnswerDoublon() {
		String[] data = { "28773", "03/02/2015 21:38:21", "A1", "eleve",
				"IPIPIP", "	", "4161", "ADAM François", "fadam14",
				"Labyrinthes", "Space landing", "Poker", "Bataille",
				"", "Ruzzle",
				"Si possible, j'aimerais bien me mettre avec Maxime Ansquer." };

		return AnswerFactory.createAnswer(data);
	}
}
