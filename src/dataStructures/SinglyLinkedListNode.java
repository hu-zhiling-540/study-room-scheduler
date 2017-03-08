package dataStructures;
/**
 * Each node contains data and a link to another node
 * @author Zhiling
 *
 * @param <T>
 */
public class SinglyLinkedListNode<T>	{
	
	protected T data;	//A piece of data is stored as an instance variable of the node.
	protected SinglyLinkedListNode<T> next;	//reference to a node stored in an instance variable of the node

	
	/**
	 * Default constructor that creates a null node
	 */
	public SinglyLinkedListNode() {
		this.data = null;
		next = null;
	}
	
	
	
	/**
	 * Constructor that helps build a node with data stored
	 * @param data
	 */
	public SinglyLinkedListNode(T data)	{
		this.data = data;
		next = null;
	}
	
	
	
	/**
	 * Sets the data stored at this node
	 */
	public void setData( T data )	{
		this.data = data;
	}
	 
	
	
	/**
	 * Returns the data stored at this node
	 */
	public T getData()	{
		return data;
	}
	 
	
	
	/**
	 * Sets the next pointer to a specific node
	 */
	public void setNext( SinglyLinkedListNode<T> node )	{
		this.next = node;
	}
	 
	
	
	/**
	 * Gets (pointer to) the next node.
	 */
	public SinglyLinkedListNode<T> getNext()	{
		return next;
	}
	
	
	 
	/**
	 * Returns a String representation of this node.
	 */
	public String toString()	{
		if( data == null )
			return "Data: null, Next: null; ";
		if( getNext() != null)
			return "Data: " + getData() + ", Next: " + getNext().getData() + "; ";
		else
			return "Data: " + getData() + ", Next: null" +"; ";
	}
}