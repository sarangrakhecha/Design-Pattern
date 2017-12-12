package wordCount.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import wordCount.io.FileProcessorNIO;
import wordCount.io.InputReaderI;
import wordCount.io.OutputWriterI;
import wordCount.treesForStrings.BSTree;
import wordCount.treesForStrings.TreeI;
import wordCount.util.FileMode;
import wordCount.visitors.GrepVisitor;
import wordCount.visitors.PopulateTreeVisitor;
import wordCount.visitors.TreeProcessingVisitorI;
import wordCount.visitors.WordCountVisitor;

/**
 * <p>
 * This class contains the main method.
 * </p>
 * 
 * @author Sarang Rakhecha
 *
 */
public class Driver {

	/**
	 * <p>
	 * This is a main method.
	 * </p>
	 * 
	 * @param args
	 *            command line arguments.
	 */
	public static void main(String[] args) {
		Driver driver = new Driver();
		if (!driver.validateArguments(args)) {
			System.exit(1);
		}
		long startTime = System.currentTimeMillis();
		int iterations = Integer.parseInt(args[2]);
		for (int i = 0; i < iterations; i++) {
			// Creating file readers and writers.
			InputReaderI inputReader = new FileProcessorNIO(args[0], FileMode.READ_ONLY);
			OutputWriterI outputWriter = new FileProcessorNIO(args[1], FileMode.READ_WRITE);
			/*InputReaderI inputReader = new FileIO(args[0], args[1]);
			OutputWriterI outputWriter = (OutputWriterI) inputReader;*/
			
			// Creating Tree Element.			
			TreeI tree = new BSTree();
			
			// Creating visitor instances.
			TreeProcessingVisitorI populateVisitor = new PopulateTreeVisitor(inputReader);
			TreeProcessingVisitorI wordCountVisitor = new WordCountVisitor(outputWriter);
			TreeProcessingVisitorI grepVisitor = new GrepVisitor(outputWriter, args[3]);
			tree.accept(populateVisitor);
			tree.accept(wordCountVisitor);
			tree.accept(grepVisitor);
			
			// Closing file resources.
			inputReader.close();
			outputWriter.close();
		}
		long finishTime = System.currentTimeMillis();
		System.out.println(
				"Average Time required per iteration: " + (finishTime - startTime) / iterations + " miliseconds");
	}

	/**
	 * <p>
	 * This method validates the command line arguments. It performs following
	 * validations:
	 * </p>
	 * <li>1. Checks if number of arguments provided is valid.</li>
	 * <li>2. checks whether the given filename is valid i.e. if a stream can be
	 * created of the file with given filename.</li>
	 * <li>3. Verifies if an output file can be created.</li>
	 * <li>4. Validates if 'Number of Iterations' is of type integer.</li>
	 * 
	 * 
	 * @param args
	 *            command line arguments.
	 * @return true if all arguments are valid otherwise throws an error to
	 *         standard output and exits the application.
	 */
	private boolean validateArguments(String[] args) {
		return lengthCheck(args, 4) && validateFileName(args[0]) && validateFileExists(args[1]) && formatCheck(args[2]);
	}

	/**
	 * <p>
	 * This method validates if a given argument can be casted to an Integer
	 * Value.
	 * </p>
	 * 
	 * @param numIterations
	 *            argument to be validated.
	 * @return true if the input argument is in valid format, otherwise exits
	 *         the application.
	 */
	private boolean formatCheck(String numIterations) {
		try {
			Integer.parseInt(numIterations);
		} catch (NumberFormatException exception) {
			System.err.println("Please enter an argument in valid format." + exception);
			exception.printStackTrace();
			System.exit(1);
		}
		return true;
	}

	/**
	 * <p>
	 * This method validates if an argument is a valid file name.
	 * </p>
	 * 
	 * @param fileName
	 *            argument to be validated.
	 * @return true if the input argument is in valid, otherwise exits the
	 *         application.
	 */
	private boolean validateFileName(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.canRead()) {
			try {
				new FileInputStream(file);
			} catch (IOException exception) {
				System.err.println("File Stream could not be created. ");
				exception.printStackTrace();
				System.exit(1);
			}
		} else {
			System.err.println("Please provide a valid file.");
			System.exit(1);
		}
		return true;
	}

	/**
	 * <p>
	 * This method validates if the value of an argument has expected length.
	 * </p>
	 * 
	 * @param arg
	 *            argument to be checked for range.
	 * @param expected
	 *            expected length.
	 * @return true if the input argument lies within the specified range,
	 *         otherwise exists the application.
	 */
	private boolean lengthCheck(String[] args, int expected) {
		if (args.length != expected) {
			System.err.println("Please enter valid number of arguments.");
			System.exit(1);
		}
		return true;
	}

	/**
	 * <p>
	 * This method validates if a file already exists. If file already exists,
	 * this method will delete the existing file and create a new one.
	 * </p>
	 * 
	 * @param fileName
	 *            name of the file to validate.
	 * @returns true if no error occurs while deleting or creating a new
	 *          file,otherwise exists the application.
	 */
	private boolean validateFileExists(String fileName) {
		File file = new File(fileName);
		try {
			if (file.delete()) {
				file.createNewFile();
			}
		} catch (IOException exception) {
			System.err.println("Error While creating an output file " + exception);
			exception.printStackTrace();
			System.exit(1);
		}
		return true;
	}
}