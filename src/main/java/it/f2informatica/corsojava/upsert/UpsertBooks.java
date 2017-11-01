package it.f2informatica.corsojava.upsert;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.LegacyDocument;
import com.google.gson.Gson;

import it.f2informatica.corsojava.database.DBManager;
import it.f2informatica.corsojava.util.BooksFactory;

public class UpsertBooks {

	final static Logger logger = Logger.getLogger(UpsertBooks.class);
	static Bucket bucket;
	
	public static void main(String[] args) {
		logger.debug("Apro il bucket");
		bucket = DBManager.getBucket();
		logger.debug("Memorizzo gli oggetti nel bucket");
		//La classe Gson di Google mappa un qualsiasi oggetto in una stringa JSON
		Gson gson = new Gson();
		try {
			BooksFactory
			.getBooks()
			.stream()
			.map(
					b -> LegacyDocument.create("" + b.getIsbn(), gson.toJson(b))
			)
			.forEach(bucket::upsert);
			logger.debug("Operazione eseguita con successo");
		} catch (IOException e) {
			logger.error("Errore: " + e.toString());
		}
	}
}
