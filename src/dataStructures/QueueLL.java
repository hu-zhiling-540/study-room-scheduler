package dataStructures;

/**
 * Create a queue linked list
 * @author Zhiling
 *
 * @param <T>
 */
public class QueueLL<T> implements Queue<T>	{

	SinglyLinkedList<T> list;
	
	
	/**
	 * Default Constructor
	 */
	public QueueLL()	{
		list = new SinglyLinkedList<T>();
	}
	
	@Override
	public boolean isEmpty()		{	
		return list.isEmpty();
	}

	@Override
	/** 
	 * Gets the element at the front of the queue without removing it.
	 * @return the peeked data
	 **/
	public T peek()		{
		return list.getFirst();
	}

	@Override
	/**
	 * Removes the front element of the queue and returns it.
	 * @return the dequeued data
	 **/
	public T dequeue()	{
		T temp = list.getFirst();
		list.deleteFirst();
		return temp;
	}

	@Override
	/**
	 * Adds an element to the end of the queue.
	 **/
	public void enqueue(T data)	{
		list.insertLast(data);
	}

}
