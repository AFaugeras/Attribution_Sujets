package parse;

import static org.junit.Assert.*;

import java.io.File;

import junit.framework.TestCase;
import models.exception.fileformatexception.FileFormatException;
import models.parser.AbstractParser;
import models.parser.helper.CsvHelper;

import org.junit.Test;

public class ParserFormatTest extends TestCase{

	
	@Test
	public void testFormatAnswerKO(){
		boolean failed=false;
		 String[] format = {"R�ponse",	
					//"Soumis le :", // on retire une des colonnes obligatoires	
					"Institution",
					"D�partement",
					"Cours",
					"Groupe",	
					"ID",	
					"Nom complet",
					"Nom d'utilisateur"};
		try {
			AbstractParser.checkFormat(AbstractParser.ANSWER, format);
		} catch (FileFormatException e) {
			failed =true;
		}
		
		assertTrue("Le format est valid� alors qu'il ne devrait pas ", failed);
		
	}
	
	
	@Test
	public void testFormatAnswerOK(){
		boolean failed=false;
		 String[] format ={"R�ponse",	
					"Soumis le :",	
					"Institution",
					"D�partement",
					"Cours",
					"Groupe",	
					"ID",	
					"Nom complet",
					"Nom d'utilisateur"};
		try {
			AbstractParser.checkFormat(AbstractParser.ANSWER, format);
		} catch (FileFormatException e) {
			failed =true;
		
		}
		
		assertFalse("Le format n'est valid� alors qu'il le devrait ", failed);
		
	}
	@Test
	public void testFormatSubjectKO(){
		boolean failed=false;
		 String[] format ={	"NomSujet",
					"maxSize",
					"minSize",
					//"multiple",
					"cardMin",
					"cardMax"};
		try {
			AbstractParser.checkFormat(AbstractParser.SUBJECT, format);
		} catch (FileFormatException e) {
			failed =true;
			
		}
		
		assertTrue("Le format est valid� alors qu'il ne devrait pas ", failed);
	}

	
	@Test
	public void testFormatSubjectOK(){
		boolean failed=false;
		 String[] format ={	"NomSujet",
					"maxSize",
					"minSize",
					"multiple",
					"cardMin",
					"cardMax"};
		try {
			AbstractParser.checkFormat(AbstractParser.SUBJECT, format);
		} catch (FileFormatException e) {
			failed =true;
		}
		
		assertFalse("Le format n'est pas valid� alors qu'il le devrait ", failed);
		
	}

@Test
public void testFormatPersonOK(){
	boolean failed=false;
	 String[] format ={		
			 	"Nom",
				"Pr�nom 1",
				"M�l (EMN)",
				"Compte d'acc�s"};
	try {
		AbstractParser.checkFormat(AbstractParser.PERSON, format);
	} catch (FileFormatException e) {
		failed =true;
		
	}
	
	assertFalse("Le format n'est pas valid� alors qu'il le devrait ", failed);
	
}
@Test
public void testFormatPersonKO(){
	boolean failed=false;
	 String[] format ={		
			 	"Nom",
				//"Pr�nom 1",
				"M�l (EMN)",
				"Compte d'acc�s"};
	try {
		AbstractParser.checkFormat(AbstractParser.PERSON, format);
	} catch (FileFormatException e) {
		failed =true;
	}
	
	assertTrue("Le format est valid� alors qu'il ne devrait pas ", failed);
}
}
