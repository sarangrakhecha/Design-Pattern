/**
 * 
 */
package wordCount.visitors;

import wordCount.treesForStrings.TreeI;

/**
 * <p>
 * This interface is a part of a template for implementing A visitor design
 * pattern. It contains visit methods for each element to be visited by the
 * visitor.
 * </p>
 * 
 * @author Anand Kulkarni
 *
 */
public interface TreeProcessingVisitorI {

	/**
	 * <p>
	 * Every Visitor implementation which is implementing this interface needs
	 * to provide implementation for this method.
	 * </p>
	 * 
	 * @param tree
	 *            A Tree object from which new information is to be extracted.
	 */
	public void visit(TreeI tree);
}