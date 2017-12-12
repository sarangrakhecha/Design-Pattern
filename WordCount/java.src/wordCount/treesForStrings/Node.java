/**
 * 
 */
package wordCount.treesForStrings;

/**
 * @author Sarang Rakhecha
 *
 */
public class Node {

	/**
	 * <p>
	 * This variable holds the word.
	 * </p>
	 */
	private String word;

	/**
	 * <p>
	 * This variable stores left child of the current node.
	 * </p>
	 */
	private Node leftChild;

	/**
	 * <p>
	 * This variable stores right child of the current node.
	 * </p>
	 */
	private Node rightChild;

	/**
	 * <p>
	 * This variable stores number of characters required to represent the word.
	 * </p>
	 */
	private int noCharacters;

	/**
	 * <p>
	 * This variable stores the number of times a word has occurred in a text
	 * file.
	 * </p>
	 */
	private int noOccurences;

	public Node(String wordIn) {
		word = wordIn;
		noCharacters = word.length();
		noOccurences = 1;
	}

	/**
	 * @return the value
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setWord(String value) {
		this.word = value;
	}

	/**
	 * @return the leftChild
	 */
	public Node getLeftChild() {
		return leftChild;
	}

	/**
	 * @param leftChild
	 *            the leftChild to set
	 */
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * @return the rightChild
	 */
	public Node getRightChild() {
		return rightChild;
	}

	/**
	 * @param rightChild
	 *            the rightChild to set
	 */
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	/**
	 * @return the noCharacters
	 */
	public int getNoCharacters() {
		return noCharacters;
	}

	/**
	 * @param noCharacters
	 *            the noCharacters to set
	 */
	public void setNoCharacters(int noCharacters) {
		this.noCharacters = noCharacters;
	}

	/**
	 * @return the noOccurences
	 */
	public int getNoOccurences() {
		return noOccurences;
	}

	/**
	 * @param noOccurences
	 *            the noOccurences to set
	 */
	public void setNoOccurences(int noOccurences) {
		this.noOccurences = noOccurences;
	}

	@Override
	public String toString() {
		return "Node [value=" + word + ", leftChild=" + leftChild + ", rightChild=" + rightChild + ", noCharacters="
				+ noCharacters + ", noOccurences=" + noOccurences + "]";
	}
}
