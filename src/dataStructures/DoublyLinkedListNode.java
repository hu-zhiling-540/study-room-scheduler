package dataStructures;
/**
 * A Doubly Linked List Node, extending from a Singly Linked List Node,
 * is an object with an attribute T data and two other pointer attributes, next and pre
 * @author Zhiling
 *
 * @param <T>
 */
public class DoublyLinkedListNode<T> extends SinglyLinkedListNode<T>	{
	
	protected DoublyLinkedListNode<T> pre; // reference to the previous node 

	
	/**
	 * Constructor
	 */
	public DoublyLinkedListNode(T data) 	{
		super(data);
		pre = null; // Instantiate the DLL node
	}
	
	
	
	/**
	 * Set the previous pointer to a node.
	 */
	public void setPre( DoublyLinkedListNode<T> node )	{
		this.pre = node;
	}
	 
	
	
	/**
	 * Get (pointer to) previous node.
	 */
	public DoublyLinkedListNode<T> getPre()	{
		return pre;
	}
	
	

}
