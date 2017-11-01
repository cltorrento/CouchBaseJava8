package it.f2informatica.corsojava.retrieve;

import org.apache.log4j.Logger;
import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.view.AsyncViewResult;
import com.couchbase.client.java.view.AsyncViewRow;
import com.couchbase.client.java.view.ViewQuery;

import it.f2informatica.corsojava.database.DBManager;

public class QueryViewAsync {
	final static Logger logger = Logger.getLogger(QueryViewAsync.class);
	static AsyncBucket bucket;
	
	public static void main(String[] args) {
		logger.debug("Open the Bucket");
		bucket = DBManager.getBucket().async();
		bucket.query(ViewQuery.from("test", "by_isbn")).flatMap(AsyncViewResult::rows)
		.flatMap(AsyncViewRow::document)
		.toBlocking()
		.subscribe(System.out::println);
	}
}
