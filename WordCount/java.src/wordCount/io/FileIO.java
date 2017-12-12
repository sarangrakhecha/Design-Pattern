package wordCount.io;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <p>
 * This class contains logic pertaining to file operations.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public class FileIO implements InputReaderI, OutputWriterI {

	/**
	 * A reader object used to read contents of a file.
	 */
	private BufferedReader fileReader = null;
	/**
	 * A writer object used to write contents to a file.
	 */
	private BufferedWriter fileWriter = null;

	public FileIO(String inFileNameIn, String outFileNameIn) {
		try {
			fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inFileNameIn))));
			fileWriter = new BufferedWriter(new FileWriter(new File(outFileNameIn)));
		} catch (FileNotFoundException exception) {
			// TODO: Error Handling.
			System.err.println("File Not Found.");
			exception.printStackTrace();
			System.exit(1);
		} catch (IOException exception) {
			// TODO: Error Handling.
			System.err.println("Error While Opening File.");
			exception.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * <p>
	 * This method reads a line from an input file. It returns a null if end of
	 * input is reached.
	 * </p>
	 * 
	 * @return next line from an input file, null if end of input is reached.
	 */
	@Override
	public List<String> read() {
		try {
			String line = fileReader.readLine();
			return (null == line) ? null : Arrays.asList(line.split(" +"));
		} catch (IOException exception) {
			System.err.println("Error occured while reading a file.");
			exception.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	
	@Override
	public void write(String content) {
		try {
			fileWriter.write(content);
		} catch (IOException exception) {
			System.err.println("Error occured while writing to a file.");
			exception.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * <p>
	 * This method is responsible for closing a file.
	 * </p>
	 * 
	 * @return none
	 */
	public void close() {
		try {
			fileReader.close();
		} catch (IOException e) {
			System.err.println("Error occured while closing a file.");
			System.exit(1);
		}
	}

	/**
	 * Overridden toString() method.
	 */
	@Override
	public String toString() {
		return "FileIO [fileReader=" + fileReader + "]";
	}

}