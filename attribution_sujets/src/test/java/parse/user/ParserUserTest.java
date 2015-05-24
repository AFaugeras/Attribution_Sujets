package parse.user;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;
import models.bean.Person;

import org.junit.Test;

import factory.UserFactory;
import parse.helper.CsvHelper;

public class ParserUserTest extends TestCase {

	
	@Test
	public void testParsingUser() throws IOException{
		File f = new File("");
		f = CsvHelper.getRessource(f.getAbsolutePath()+f.separator+"donnees"+f.separator+"UnitTest"+f.separator+"liste_1eleve.csv");
		ParserCsvUserList parser = new ParserCsvUserList();
		parser.ParseUserList(f);
		List<Person> personList = parser.getUserList();
		Person UserOfFile = personList.get(0);
		Person UserHeavy = createUser();
		assertEquals(UserOfFile, UserHeavy);
		
		
	}

	@Test
	public void testParsing195Users() throws IOException{
		File f = new File("");
		f = CsvHelper.getRessource(f.getAbsolutePath()+f.separator+"donnees"+f.separator+"UnitTest"+f.separator+"liste_195eleves.csv");
		ParserCsvUserList parser = new ParserCsvUserList();
		parser.ParseUserList(f);
		List<Person> personList = parser.getUserList();
		int nbUser = personList.size();
		assertEquals(nbUser, 195);
		
		
	}
	@Test
	public void testParsing195UsersPlus1Doublon() throws IOException{
		File f = new File("");
		f = CsvHelper.getRessource(f.getAbsolutePath()+f.separator+"donnees"+f.separator+"UnitTest"+f.separator+"liste_195eleves_plus_1doublon.csv");
		ParserCsvUserList parser = new ParserCsvUserList();
		parser.ParseUserList(f);
		List<Person> personList = parser.getUserList();
		int nbUser = personList.size();
		assertEquals(nbUser, 195);
		
		
	}
	
	private Person createUser(){
		String[] data = {"ABOUTARIK","Sara","Sara.ABOUTARIK@etudiant.mines-nantes.fr","sabout14"};
		
		return UserFactory.createUser(data);
	}
}
