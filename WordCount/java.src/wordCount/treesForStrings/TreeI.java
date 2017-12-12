/**
 * 
 */
package wordCount.treesForStrings;

import wordCount.visitors.TreeProcessingVisitorI;

/**
 * <p>
 * This interface declares all operation that needs to be implemented by every
 * concrete tree implementation.
 * </p>
 * 
 * @author Sarang Rakhecha
 *
 */
public interface TreeI {

	/**
	 * <p>
	 * This method should insert a new value into the tree.
	 * </p>
	 * 
	 * @param value
	 *            value to be inserted into the tree.
	 */
	public void insert(String value);

	/**
	 * <p>
	 * This method is used to search a value from a tree.
	 * </p>
	 * 
	 * @param searchString
	 *            value to be searched.
	 * @return Node in case searchString is present in the tree, null otherwise.
	 */
	public Node search(String searchString);

	/**
	 * This method is used to print the contents of the tree using in-order
	 * traversal.
	 */
	public void printInorder();

	/**
	 * This method is a part of templates to implement visitor pattern.
	 * 
	 * @param visitor
	 */
	public void accept(TreeProcessingVisitorI visitor);

	/**
	 * This method returns the rootNode of the tree.
	 * 
	 * @return Returns the rootNode, Returns null if tree is empty.
	 */
	public Node getRootNode();

}
