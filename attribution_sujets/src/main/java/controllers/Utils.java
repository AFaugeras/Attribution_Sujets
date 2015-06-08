package controllers;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.List;

/**
 * Collection de m�thodes utilitaires pour les contr�leurs. Elle ne comporte que des m�thodes statiques.
 */
public class Utils {

	/**
	 * Cette m�thode permet de r�cup�rer un fichier (s'il existe) depuis un Transferable.
	 *  
	 * @param transferable Le transferable.
	 * @return Le fichier ou null.
	 */
	@SuppressWarnings("unchecked")
	public static File getFileFromTransferable(Transferable transferable) {
		File ret = null;
		
		DataFlavor[] flavors = transferable.getTransferDataFlavors();
		
		// Recherche du data flavor de type FlavorJavaFileListType.
		for (DataFlavor flavor : flavors) {

		    try {
		    	
		        if (flavor.isFlavorJavaFileListType()) {
					List<File> files = (List<File>) transferable.getTransferData(flavor);

					// On n'accepte uniquement que seul un fichier soit drop�.
		            if(files.size() == 1) {
		            	ret = files.get(0);
		            }
		        }

		    } catch (Exception e) {
		    }
		}
		
		return ret;
	}
}
