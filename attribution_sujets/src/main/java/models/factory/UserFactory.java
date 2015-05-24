package models.factory;

import models.bean.Person;

public class UserFactory {

	/**
	 * Retourne un objet Person grace a un tableau de string Format du tableau
	 * de string: Nom, Pr�nom, M�l (EMN), Compte d'acc�s
	 * 
	 * @param data
	 * @return Person
	 */
	public static Person createUser(String[] data) {
		Person person = new Person();
		person.setFamilyName(data[0]);
		person.setFirstName(data[1]);
		person.setIDcampus(data[3]);

		return person;
	}
}