package models.bean;

import java.util.List;

import models.parser.AbstractParser;

/**
 * Sujet propose aux participants.
 */
public class Subject {

	/**
	 * Identifiant.
	 */
	private int id;

	/**
	 * Libelle precisant le sujet.
	 */
	private String label;

	/**
	 * Nombre de participants maximum.
	 */
	private int maxSize;

	/**
	 * Nombre de participants minimum.
	 */
	private int minSize;

	/**
	 * Cardinalite maximum. C'est a dire le nombre d'occurence maximum du sujet
	 * minimum
	 */
	private int maxCard;

	/**
	 * Cardinalite minimum. C'est a dire le nombre d'occurence minimum du sujet
	 * minimum
	 */
	private int minCard;

	/**
	 * Constructeur par défaut.
	 */
	public Subject() {
		this.label = "";
	}

	public Subject(int id, String label, int minSize, int maxSize, int cardMin,
			int cardMax) {
		super();
		this.id = id;
		this.label = label;
		this.maxSize = maxSize;
		this.minSize = minSize;
		this.minCard = cardMin;
		this.maxCard = cardMax;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the maxSize
	 */
	public int getMaxSize() {
		return maxSize;
	}

	/**
	 * @param maxSize the maxSize to set
	 */
	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	/**
	 * @return the minSize
	 */
	public int getMinSize() {
		return minSize;
	}

	/**
	 * @param minSize the minSize to set
	 */
	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	/**
	 * @return the maxCard
	 */
	public int getMaxCard() {
		return maxCard;
	}

	/**
	 * @param maxCard the maxCard to set
	 */
	public void setMaxCard(int maxCard) {
		this.maxCard = maxCard;
	}

	/**
	 * @return the minCard
	 */
	public int getMinCard() {
		return minCard;
	}

	/**
	 * @param minCard the minCard to set
	 */
	public void setMinCard(int minCard) {
		this.minCard = minCard;
	}
	
	@Override
	public String toString() {
		return "Subject [id=" + id + ", label=" + label + ", maxSize="
				+ maxSize + ", minSize=" + minSize + ", maxCard=" + maxCard
				+ ", minCard=" + minCard + "]";
	}

	public String save() {
		String split = AbstractParser.SUBJECTSPLIT;
		return this.getId() + split + this.getLabel() + split + getMinSize()
				+ split + getMaxSize() + split + getMinCard() + split
				+ getMaxCard();
	}

	public static String save(List<Subject> list) {
		String retour = "";
		int i = 0;
		for (String column : AbstractParser.SUBJECTFORMAT) {
			retour += column;
			if (i < AbstractParser.SUBJECTFORMAT.length - 1)
				retour += AbstractParser.SUBJECTSPLIT;

		}
		retour += "\n";
		for (Subject subject : list) {
			retour += subject.save() + "\n";
		}

		return retour;
	}
}
