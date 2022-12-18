package fr.ewaux.commons.utilities.utils;

import fr.ewaux.commons.utilities.exception.FileException;
import java.io.File;

public final class FileHelper {

	public static void createDirectoryIfNotExists(final String pathname) {
		File file = new File(pathname);
		if (!file.exists()) {
			if (!file.mkdirs()) {
				throw new FileException("Can't create directory", pathname);
			}
		}
	}

}
