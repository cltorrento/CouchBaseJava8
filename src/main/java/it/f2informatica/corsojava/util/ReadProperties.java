package it.f2informatica.corsojava.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ReadProperties {
	final static Logger logger = Logger.getLogger(ReadProperties.class);
	public static Properties readProperties() {
		Properties prop = new Properties();

		try {
			// Carico il file di properties
			logger.debug("Carico il file di properties");
			prop.load(new FileInputStream("resources/config.properties"));
			logger.debug("file di properties caricato.");
		} catch (FileNotFoundException e) {
			logger.error("Errore: il file non esiste.");
		} catch (IOException e) {
			logger.error("Errore nella lettura del file.");
		}
		return prop;
	}

}
