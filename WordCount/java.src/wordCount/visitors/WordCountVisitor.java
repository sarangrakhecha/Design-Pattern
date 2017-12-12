/**
 * 
 */
package wordCount.visitors;

import java.util.List;
import java.util.Stack;

import wordCount.io.OutputWriterI;
import wordCount.treesForStrings.Node;
import wordCount.treesForStrings.TreeI;

/**
 * @author Anand Kulkarni
 *
 */
public class WordCountVisitor implements TreeProcessingVisitorI {

	/**
	 * <p>
	 * This variable is used to store the instance of Writer variable.
	 * </p>
	 */
	private OutputWriterI outputWriter = null;

	/**
	 * <p>
	 * This variable stores characters that are used to represent the line
	 * separator by the underlying operating system.
	 * </p>
	 */
	private static String LINE_SEPARATOR = System.getProperty("line.separator");

	public WordCountVisitor(OutputWriterI outputWriterIn) {
		outputWriter = outputWriterIn;
	}

	/**
	 * <p>
	 * This method contains the logic for collecting following statistics from a
	 * given tree:
	 * <li>Total number of words.</li>
	 * <li>Most frequently occurring words.</li>
	 * <li>Frequency of most frequent words..</li>
	 * <li>Total number of characters.</li>
	 * </p>
	 */
	@Override
	public void visit(TreeI tree) {
		TreeStats treeStats = getStats(tree);
		outputWriter.write(String.format(
				"The total number of words is: %d" + LINE_SEPARATOR + "The most frequently used word is: %s"
						+ LINE_SEPARATOR + "The frequency of the most frequently used word is: %d" + LINE_SEPARATOR
						+ "The number of characters (without whitespaces) is: %d" + LINE_SEPARATOR,
				treeStats.getTotalWordCount(), toString(treeStats.getMostFreqWords()),
				treeStats.getFrequencyMostFreqWord(), treeStats.getTotalNumberOfCharacters()));
	}

	private String toString(List<String> words) {
		if (words.isEmpty()) {
			return "";
		}
		StringBuilder listAsString = new StringBuilder();
		words.stream().forEach(word -> {
			listAsString.append(word).append(",");
		});
		return listAsString.substring(0, listAsString.length() - 1);
	}

	/**
	 * <p>
	 * This method traverses the tree by using in-order traversal and required
	 * information from each node.
	 * </p>
	 * 
	 * @param tree
	 *            tree to be traversed.
	 * @return Statistics
	 */
	private TreeStats getStats(TreeI tree) {
		Node rootNode = tree.getRootNode();
		TreeStats treeStats = new TreeStats();
		if (rootNode == null) {
			return treeStats;
		}
		Stack<Node> stack = new Stack<Node>();
		Node currentNode = rootNode;
		while (!stack.empty() || currentNode != null) {
			if (currentNode != null) {
				stack.push(currentNode);
				currentNode = currentNode.getLeftChild();
			} else {
				Node node = stack.pop();
				treeStats.updateStats(node.getWord(), node.getNoOccurences());
				treeStats.setTotalWordCount(treeStats.getTotalWordCount() + node.getNoOccurences());
				treeStats.setTotalNumberOfCharacters(
						treeStats.getTotalNumberOfCharacters() + (node.getNoOccurences() * node.getNoCharacters()));
				// TODO: replace multiplication operation.
				currentNode = node.getRightChild();
			}
		}
		return treeStats;
	}
}