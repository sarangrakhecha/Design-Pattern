/**
 * 
 */
package wordCount.visitors;

import wordCount.io.OutputWriterI;
import wordCount.treesForStrings.Node;
import wordCount.treesForStrings.TreeI;

/**
 * @author Sarang Rakhecha
 *
 */
public class GrepVisitor implements TreeProcessingVisitorI {

	/**
	 * <p>
	 * This variable is used to store the instance of Writer variable.
	 * </p>
	 */
	private OutputWriterI outputWriter = null;
	/**
	 * <p>
	 * This variable is used to store search string.
	 * </p>
	 */
	private String searchString;

	/**
	 * <p>
	 * This variable stores characters that are used to represent the line
	 * separator by the underlying operating system.
	 * </p>
	 */
	private static String LINE_SEPARATOR = System.getProperty("line.separator");

	public GrepVisitor(OutputWriterI outputWriterIn, String searchStringIn) {
		outputWriter = outputWriterIn;
		searchString = searchStringIn;
	}

	/**
	 * <p>
	 * This method contains the logic for searching a String/word in a tree.
	 * </p>
	 * 
	 * @param tree Instance of tree implementation to search in.
	 */
	@Override
	public void visit(TreeI tree) {
		Node node = tree.search(searchString);
		if (null != node) {
			outputWriter.write(
					String.format("The word " + searchString + " occurs the following times: %d" + LINE_SEPARATOR,
							node.getNoOccurences()));
		} else {
			outputWriter.write(
					String.format("The word " + searchString + " occurs the following times: 0" + LINE_SEPARATOR));
		}
	}
}