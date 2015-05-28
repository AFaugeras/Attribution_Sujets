package views.result;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Classe prennant en charge la génération du pdf d'export de l'affichage des résultats
 * 
 *
 */
public class ResultPdfGenerator {

	private Document document = new Document();

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);

	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	private static String[] header;
	private static Object[][] data;
	
	public ResultPdfGenerator(String[] header, Object[][] data) {
		ResultPdfGenerator.header = header;
		ResultPdfGenerator.data = data;
	}

	/**
	 * Fonction permettant l'enregistrement du pdf
	 * 
	 * @param filename le nom de fichier de l'export (absolutePath)
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public void buildPDF(String filename) throws FileNotFoundException, DocumentException {
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.open();
		addMetaData(document);
		addTitlePage(document);
		addContent(document);
		document.close();
	}

	/** ajoute des metadata au PDF qui seront vues par Adobe Reader
	 *  sous File -> Properties
	 * @param document le document pour lequel on souhaite ajouter des métadonnées
	 */
	private static void addMetaData(Document document) {
		document.addTitle("Résultat d'attribution des sujets");
		document.addSubject("Attribution des sujets");
		// document.addKeywords("Java, PDF, iText");
		document.addAuthor("Ecole des Mines de Nantes");
		document.addCreator("EMN");
	}

	/**
	 * Ajoute la zone de titre prédéfinie au document
	 * 
	 * @param document le document pour lequel on souhaite ajouter le titre
	 * @throws DocumentException
	 */
	private static void addTitlePage(Document document)
			throws DocumentException {
		Paragraph preface = new Paragraph();
		
		// Ecriture du titre du document
		preface.add(new Paragraph("Résultat de l'attribution de sujets", catFont));

		addEmptyLine(preface, 1);
		// Sous-titre comportant la date de l'attribution
		preface.add(new Paragraph(
				"Attribution réalisée le : " + dateFormat.format(new Date()),
				smallBold));

		addEmptyLine(preface, 2);

		document.add(preface);
		// Démarrer une nouvelle page
		//document.newPage();
	}
	
	/**
	 * Ajoute le contenu du document : le tableau des résultats
	 * 
	 * @param document le document pour lequel on souhaite ajouter le contenu
	 * @throws DocumentException
	 */
	private static void addContent(Document document) throws DocumentException {
	    PdfPTable table = new PdfPTable(header.length);

	    // t.setBorderColor(BaseColor.GRAY);
	    // t.setPadding(4);
	    // t.setSpacing(4);
	    // t.setBorderWidth(1);

	    //Déclaration de l'entête du tableau
	    PdfPCell c1;
	    for (String head : header) {
	    	c1 = new PdfPCell(new Phrase(head));
	    	c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    	table.addCell(c1);
		}
	    table.setHeaderRows(1);

	    // Remplissement du tableau avec les données
	    for (Object[] objects : data) {
			for (Object object : objects) {
				String content = object == null ? "" : object.toString();
				table.addCell(content);
			}
		}

	    document.add(table);
	}

	/**
	 * Génère une line vide pour un paragraphe
	 * 
	 * @param paragraph le paragraphe auquel on veut ajouter la ligne vide
	 * @param number
	 */
	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}
