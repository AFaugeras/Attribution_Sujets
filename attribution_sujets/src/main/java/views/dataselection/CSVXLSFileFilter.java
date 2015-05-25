package views.dataselection;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class CSVXLSFileFilter extends FileFilter {

	@Override
	public String getDescription() {
		return ".xls, .csv";
	}

	@Override
	public boolean accept(File f) {
		String[] extensions = { "csv", "xls" };
		if (f.isDirectory()) {
			return true;
		} else {
			String path = f.getAbsolutePath().toLowerCase();
			for (int i = 0, n = extensions.length; i < n; i++) {
				String extension = extensions[i];
				if ((path.endsWith(extension) && (path.charAt(path.length()
						- extension.length() - 1)) == '.')) {
					return true;
				}
			}
		}
		return false;
	}
}
