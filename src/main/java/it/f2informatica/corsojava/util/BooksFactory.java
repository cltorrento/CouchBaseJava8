package it.f2informatica.corsojava.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import it.f2informatica.corsojava.domain.Libro;

public class BooksFactory {
	static String separatore = ";";

	static String fileName = "libri.txt";

	public static List<Libro> getBooks() throws IOException {
		
		try {
			
		return Files
				.lines(Paths.get(fileName))
				.map(mapToBook)
				.collect(Collectors.toList());
		} catch (IOException e) {
			throw e;
		}
		

	}

	private static Function<String, Libro> mapToBook = (line) -> {
		String[] p = line.split(separatore);
		Libro book = new Libro(p[0], p[1], p[2], p[3], p[4], p[5], Double.parseDouble(p[6]), Integer.parseInt(p[7]), p[8], p[9]);
		return book;
	};
}
