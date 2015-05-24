package models.bean;

public class Subject {

	private int id;
	private String label;

	private int maxSize; // Nombre de personne max par sujet
	private int minSize; // Nombre de personne min par sujet
	private int multiple; // contrainte de multiplicité par sujet
	private int cardMin;
	private int cardMax;

	/**
	 * Constructeur par défaut.
	 */
	public Subject() {
		this.label = "";
	}

	public Subject(int id, String label, int maxSize, int minSize,
			int multiple, int cardMin, int cardMax) {
		super();
		this.id = id;
		this.label = label;
		this.maxSize = maxSize;
		this.minSize = minSize;
		this.multiple = multiple;
		this.cardMin = cardMin;
		this.cardMax = cardMax;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public int getMinSize() {
		return minSize;
	}

	public void setMinSize(int minSize) {
		this.minSize = minSize;
	}

	public int getMultiple() {
		return multiple;
	}

	public void setMultiple(int multiple) {
		this.multiple = multiple;
	}

	public int getCardMin() {
		return cardMin;
	}

	public void setCardMin(int cardMin) {
		this.cardMin = cardMin;
	}

	public int getCardMax() {
		return cardMax;
	}

	public void setCardMax(int cardMax) {
		this.cardMax = cardMax;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", label=" + label + ", maxSize="
				+ maxSize + ", minSize=" + minSize + ", multiple=" + multiple
				+ ", cardMin=" + cardMin + ", cardMax=" + cardMax + "]";
	}
}
