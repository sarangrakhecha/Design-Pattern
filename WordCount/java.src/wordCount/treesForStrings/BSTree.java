package wordCount.treesForStrings;

import java.util.Stack;

import wordCount.visitors.TreeProcessingVisitorI;

/**
 * <p>
 * This class contains logic to implement Binary Search Tree.
 * </p>
 * 
 * @author Sarang Rakhecha
 *
 */
public class BSTree implements TreeI {

	/**
	 * <p>
	 * This node is used to store the root of the tree data structure.
	 * </p>
	 */
	private Node rootNode = null;

	/**
	 * 
	 */
	@Override
	public void insert(String value) {
		Node newNode = new Node(value);
		if (rootNode == null) {
			rootNode = newNode;
			return;
		}
		Node currentNode = rootNode;
		int result;
		while (true) {
			result = currentNode.getWord().compareTo(newNode.getWord());
			if (result > 0) {
				if (null == currentNode.getLeftChild()) {
					currentNode.setLeftChild(newNode);
					break;
				} else {
					currentNode = currentNode.getLeftChild();
				}
			} else if (result < 0) {
				if (null == currentNode.getRightChild()) {
					currentNode.setRightChild(newNode);
					break;
				} else {
					currentNode = currentNode.getRightChild();
				}
			} else {
				currentNode.setNoOccurences(currentNode.getNoOccurences() + 1);
				break;
			}
		}
	}

	/**
	 * <p>
	 * This method is used to search a 'search-string' in a given tree.
	 * </p>
	 * 
	 * @param searchString
	 *            string to be searched.
	 * @return A Node object if a node with given string is found, null
	 *         otherwise.
	 */
	@Override
	public Node search(String searchString) {
		if (rootNode == null) {
			return null;
		}
		Node currentNode = rootNode;
		while (null != currentNode) {
			int result = currentNode.getWord().compareTo(searchString);
			if (result > 0) {
				currentNode = currentNode.getLeftChild();
			} else if (result < 0) {
				currentNode = currentNode.getRightChild();
			} else {
				return currentNode;
			}
		}
		return null;
	}

	/**
	 * <p>
	 * This method is used to print the contents of the tree using in-order
	 * traversal.
	 * </p>
	 */
	@Override
	public void printInorder() {
		if (rootNode == null) {
			return;
		}
		Stack<Node> s = new Stack<Node>();
		Node currentNode = rootNode;
		while (!s.empty() || currentNode != null) {
			if (currentNode != null) {
				s.push(currentNode);
				currentNode = currentNode.getLeftChild();
			} else {
				Node n = s.pop();
				System.out.println(n.getWord() + " " + n.getNoOccurences());
				currentNode = n.getRightChild();
			}
		}
	}

	/**
	 * <p>
	 * This method is a part of visitor pattern and used by visitors to access
	 * different data members of an element.
	 * </p>
	 * 
	 * @param visitor
	 *            Concrete visitor implementation.
	 */
	@Override
	public void accept(TreeProcessingVisitorI visitor) {
		visitor.visit(this);
	}

	/**
	 * <p>
	 * This method returns the rootNode of the tree data structure.
	 * </p>
	 * 
	 * @return A Node which is the root of the tree data structure.
	 */
	@Override
	public Node getRootNode() {
		return rootNode;
	}

	@Override
	public String toString() {
		return "BSTree [rootNode=" + rootNode + "]";
	}

}
