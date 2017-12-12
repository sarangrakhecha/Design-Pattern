package wordCount.io;

/**
 * <p>
 * This interface declares all methods required to read input from an input
 * source.
 * </p>
 * 
 * @author Anand kulkarni
 *
 */
public interface OutputWriterI {

	/**
	 * <p>
	 * This method writes the contents to the output file.
	 * </p>
	 * 
	 * @param content
	 *            Text to be written to the file.
	 */
	public void write(String content);

	/**
	 * <p>
	 * This method is used to close the output resource.
	 * </p>
	 */
	public void close();
}
