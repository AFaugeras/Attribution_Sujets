package parse;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;
import models.interfaces.I_Answer;

import org.junit.Test;

import static org.junit.Assert.*;
import factory.AnswerFactory;

public class ParseCsvAnswerTest extends TestCase {
	
	
@Test
public void testSuppressionDoublon() throws IOException{
	File f = new File("");
	f = CsvHelper.getRessource(f.getAbsolutePath()+f.separator+"donnees"+f.separator+"Choix_avec_doublon.csv");
	ParserCsvAnswer parser = new ParserCsvAnswer();
	 parser.parseAnswer(f);
	 List<I_Answer> answer = parser.getCleanedData();
	 assertEquals(answer.get(0).getDateSoumission(), createKeptAnswerDoublon().getDateSoumission());
	 assertEquals(answer.get(0).getIdRepondant(), createKeptAnswerDoublon().getIdRepondant());
}


private I_Answer createKeptAnswerDoublon(){
	String[] data = {"28773","03/02/2015 21:38:21","A1","eleve","IPIPIP","	","4161","ADAM François","fadam14","Labyrinthes",	"Space landing","Poker",	"Bataille",	"Souris de bibliothèque",	"Ruzzle",	"Si possible, j'aimerais bien me mettre avec Maxime Ansquer."};
	
	return AnswerFactory.createResponse(data);
}
}
