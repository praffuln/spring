package com.fissionlabs.fileoperation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author Prafful
 *
 * This class is a helper class to write Stream<String> in file.
 */
public class EmployeeFileWriter {

	/**
	 * @param filePath
	 * @param strStream
	 * @throws IOException
	 */
	public static synchronized void writeInFile(String filePath, Stream<String> strStream) throws IOException {
		Files.write(Paths.get(filePath), (Iterable<String>) strStream::iterator);
	}
}
