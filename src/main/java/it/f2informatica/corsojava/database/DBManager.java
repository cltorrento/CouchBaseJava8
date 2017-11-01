package it.f2informatica.corsojava.database;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;

import it.f2informatica.corsojava.util.Constants;
import it.f2informatica.corsojava.util.ReadProperties;

public class DBManager {

	private static String BUCKET;
	private static String HOST;

	// STEP 1: Database credentials
	private static String USER;
	private static String PASSWORD;

	private static Cluster cluster;
	private static Bucket bucket;
	static Properties prop;
	// Definisco il logger
	final static Logger logger = Logger.getLogger(DBManager.class);

	public static Bucket getBucket() {
		init();
		if (bucket == null || bucket.isClosed()) {
			// Creazione del riferimento al bucket
			// Initialize the Connection
			logger.debug("Creo un riferimento al cluster");
			cluster = CouchbaseCluster.create(HOST).authenticate(USER, PASSWORD);
			// cluster.authenticate("pier", "pierluigi");
			logger.debug("Apro il bucket " + BUCKET);
			bucket = cluster.openBucket(BUCKET);
			return bucket;
		} else {

			return bucket;
		}
	}

	public static void closeConnection() {
		if (cluster != null) {
			cluster.disconnect();
		}
	}

	private static void init() {
		if (prop == null || prop.isEmpty()) {
			logger.debug("Carico le properties....");
			prop = ReadProperties.readProperties();
			HOST = prop.getProperty(Constants.HOST);
			BUCKET = prop.getProperty(Constants.BUCKET);
			USER = prop.getProperty(Constants.USER);
			PASSWORD = prop.getProperty(Constants.PASSWORD);
			logger.debug("Properties caricate.");
		}

	}
}
