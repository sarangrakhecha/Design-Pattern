package wordCount.util;

/**
 * <p>
 * This enum is used to store different file opening modes
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public enum FileMode {
	READ_ONLY("r"), READ_WRITE("rw");

	/**
	 * This variable is used to store a file mode which is used while creating a
	 * file object.
	 */
	private String mode = null;

	FileMode(String modeIn) {
		mode = modeIn;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}
}