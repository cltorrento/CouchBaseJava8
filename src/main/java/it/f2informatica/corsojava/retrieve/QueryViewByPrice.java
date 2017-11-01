package it.f2informatica.corsojava.retrieve;

import org.apache.log4j.Logger;
import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.view.AsyncViewResult;
import com.couchbase.client.java.view.AsyncViewRow;
import com.couchbase.client.java.view.ViewQuery;

import it.f2informatica.corsojava.database.DBManager;

public class QueryViewByPrice {
	final static Logger logger = Logger.getLogger(QueryViewByPrice.class);
	static AsyncBucket bucket;
	
	public static void main(String[] args) {
		logger.debug("Open the Bucket");
		DBManager
		.getBucket()
		.async()
		.query(ViewQuery.from("test", "by_prezzo")).flatMap(AsyncViewResult::rows)
		.flatMap(AsyncViewRow::document)
		.toBlocking()
		.subscribe(System.out::println);
	}
}
