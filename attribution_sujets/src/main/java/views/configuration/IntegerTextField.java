package views.configuration;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * Champ de texte n'acceptant que des chiffres.
 */
public class IntegerTextField extends JTextField {

	// Constantes :
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par défaut.
	 */
	public IntegerTextField() {
		super();
	}

	/**
	 * Constructeur.
	 * 
	 * @param cols Nombre de colonnes.
	 */
	public IntegerTextField(int cols) {
		super(cols);
	}

	@Override
	protected Document createDefaultModel() {
		return new UpperCaseDocument();
	}

	/**
	 * Permet de filtrer les saisies.
	 */
	private class UpperCaseDocument extends PlainDocument {

		private static final long serialVersionUID = 1L;

		@Override
		public void insertString(int offs, String str, AttributeSet a)
				throws BadLocationException {

			if (str != null) {

				char[] chars = str.toCharArray();
				boolean ok = true;

				for (int i = 0; i < chars.length; i++) {

					try {
						Integer.parseInt(String.valueOf(chars[i]));
					} catch (NumberFormatException exc) {
						ok = false;
						break;
					}

				}

				if (ok) {
					super.insertString(offs, new String(chars), a);
				}
			}
		}
	}
}
