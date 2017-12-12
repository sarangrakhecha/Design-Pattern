package wordCount.io;

import java.util.List;

/**
 * <p>
 * This interface declares all methods required to read input from an input
 * source.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public interface InputReaderI {

	/**
	 * <p>
	 * This method reads a part of the file (a line or block depending on the
	 * implementation), processes the text read to extract words and returns a
	 * list of words.
	 * </p>
	 * 
	 * @return List List of words or null if EOF is reached.
	 */
	public List<String> read();

	/**
	 * <p>
	 * This method is used to close the input resource.
	 * </p>
	 */
	public void close();

}
