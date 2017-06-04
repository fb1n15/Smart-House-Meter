import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/*
 * Opens and reads the input file
 */
public class FileHandling {
	
	BufferedReader reader;
	
	public FileHandling(String file) {
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (IOException e) {
			System.out.println("I failed to read the file.");
		}
	}
	/**
	 * Reads the next line
	 */
	public String getLine() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			return "I failed to read the file.";

		}

	}

	public boolean isReady() {
		try {
			return reader.ready();
		} catch (IOException e) {
			System.out.println("I failed to read the file.");
			return false;
		}
	}
}
