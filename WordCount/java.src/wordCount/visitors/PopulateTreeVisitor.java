/**
 * 
 */
package wordCount.visitors;

import java.util.List;

import wordCount.io.InputReaderI;
import wordCount.treesForStrings.TreeI;

/**
 * @author Anand Kulkarni
 *
 */
public class PopulateTreeVisitor implements TreeProcessingVisitorI {

	/**
	 * <p>
	 * This variable is used to store the instance of Reader variable.
	 * </p>
	 */
	private InputReaderI reader;

	public PopulateTreeVisitor(InputReaderI readerIn) {
		reader = readerIn;
	}

	/**
	 * <p>
	 * This method contains the logic to insert words into a tree data
	 * structure.
	 * </p>
	 * 
	 * @param tree
	 *            Instance of tree implementation to populate.
	 */
	@Override
	public void visit(TreeI tree) {
		List<String> words = null;
		while ((words = reader.read()) != null) {
			words.stream().filter(word -> ((null != word) && !(word.isEmpty()) && !(word.equals(""))))
					.forEach(word -> {
						tree.insert(word);
					});
		}
	}
}