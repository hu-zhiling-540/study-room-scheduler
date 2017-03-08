package dataStructures;
/**
 * A Doubly Linked List that extends from a Singly Linked List.
 * @author Zhiling
 *
 * @param <T>
 */
public class DoublyLinkedList<T> extends SinglyLinkedList<T>
{
	protected DoublyLinkedListNode<T> tail; // reference to the tail node
	
	
	/**
	 * Default Constructor that creates an empty list
	 */
	public DoublyLinkedList()	{
		super();
		tail = null;
	}
	
	
	
	/**
	 * Returns the tail node
	 */
	@Override
	public DoublyLinkedListNode<T> getLastNode()	{
		return tail;
	}
	
	

	/**
	 * Insert a new node with data at the beginning of the list.
	 */
	@Override
	public void insertFirst( T data )	{
		// create a new node with data passed in
		DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(data);

		if (isEmpty())
			tail = newNode;
		else	{
			// links the old head after the new node
			newNode.setNext(head); 
			((DoublyLinkedListNode<T>) head).setPre(newNode);
		}
		// makes the head point to the new node
		head = newNode;
	}
	

	 
	/**
	 * Insert a new node (holding data2) after currentNode (holding data1)
	 */
	@Override
	public void insertAfter( T data1, T data2 )	{
		// creates a new node with data passed in
		DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(data2);

		// if the list is empty or no such node exists
		if (isEmpty() || search(data1)==null)	{
			// sets both head and tail of the list to be the new node
		    head = newNode;
		    tail = newNode;
		    return;
		}
		// if only one node in the list
		else if (head.equals(tail))	{
			head.setNext(newNode);
			newNode.setPre((DoublyLinkedListNode<T>) head);
			tail = newNode;
		}
		else	{
			DoublyLinkedListNode<T> preNode =  (DoublyLinkedListNode<T>) head;
			DoublyLinkedListNode<T> tempNode = (DoublyLinkedListNode<T>) head.getNext();

			// loop through the list
			while ( preNode!= null )	{
				// if we come to a node that contains the data1 we are looking for
				if ( preNode.getData().equals(data1) )	{
					if (preNode.getNext()== null)	{
						preNode.setNext(newNode);
						newNode.setPre(preNode);
						tail = newNode;
					}
					else{
						newNode.setPre(preNode);
						((DoublyLinkedListNode<T>) preNode.getNext()).setPre(newNode);
						newNode.setNext(preNode.getNext());	
						preNode.setNext(newNode);
					}
					return;
				}
				// otherwise move on to the next node if there is one
				preNode = tempNode;
				tempNode = (DoublyLinkedListNode<T>) tempNode.getNext();
			}
		}
	 }
	
	/**
	 * Insert a new node (holding data1) before currentNode (holding data2)
	 */
	public void insertBefore( T data1, T data2 )	{
		// creates a new node with data passed in
		DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(data1);

		// if the list is empty or no such node exists
		if (isEmpty() || search(data2)==null)	{
			// sets both head and tail of the list to be the new node
		    head = newNode;
		    tail = newNode;
		    return;
		}
		// if only one node in the list
		else if (head.equals(tail))	{
			((DoublyLinkedListNode<T>) head).setPre(newNode);
			newNode.setNext(head);
			head = newNode;
		}
		else	{
			DoublyLinkedListNode<T> preNode =  (DoublyLinkedListNode<T>) head;
			DoublyLinkedListNode<T> tempNode = (DoublyLinkedListNode<T>) head.getNext();

			// loop through the list
			while ( tempNode!= null )	{
				// if we come to a node that contains the data1 we are looking for
				if ( tempNode.getData().equals(data2) )	{
					newNode.setPre(preNode);
					((DoublyLinkedListNode<T>) preNode.getNext()).setPre(newNode);
					newNode.setNext(preNode.getNext());	
					preNode.setNext(newNode);
				}
					return;
				}
				// otherwise move on to the next node if there is one
				preNode = tempNode;
				tempNode = (DoublyLinkedListNode<T>) tempNode.getNext();
			}
	}
	
	/**
	 * Insert a new node with data at the end of the list
	 */
	@Override
	public void insertLast (T data)	{
		// create a new node with data passed in
		DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(data);
		
		// if the list is empty
		if (isEmpty())
			// make the head point to the new node
			head = newNode;
		else	{
			// set the next pointer of the old tail to newNode
			tail.setNext(newNode);
			newNode.setPre(tail);
		}
		
		tail = newNode;	// resets the tail
	}
	
	
	
	/**
	 * Remove the first node
	 */
	@Override
	public void deleteFirst()	{
		if (head == null)
			return;
		else if (head == tail)	{
			head = null;
			tail = null;
		}
		else		{
			((DoublyLinkedListNode<T>) head.getNext()).setPre(null);
			// makes the head point to the second node;
			head = head.getNext();
		}
		
		
	}
	 
	
	/**
	 * Remove the last node
	 */
	@Override
	public void deleteLast()	{
		if (isEmpty())
			return;
		// if there is only one node
		if ( head == tail )	{
			head = null;
			tail = null;
			return;
		}
		else	{
			tail = tail.getPre();
			tail.setNext(null);
		}
	}
	
	/**
	 * Delete the next node after the node we pass in
	 */
	@Override
	public void deleteNext( T data )
	{
		DoublyLinkedListNode<T> node = (DoublyLinkedListNode<T>) search(data);

		// if list is empty or no such node exists or there is no node existing after
		if (isEmpty() || node == null || node.getNext() == null)
			return;
		// if the next node is the tail
		if (node.getNext().getNext() == null)	{
			deleteLast();	// simply deletes the tail
		}
		// if there exists more than one node after
		else	{
			// skip the next node and links to the next next node
			((DoublyLinkedListNode<T>) node.getNext().getNext()).setPre((DoublyLinkedListNode<T>) node);
			((DoublyLinkedListNode<T>) node).setNext(node.getNext().getNext());
		}			
	}

	
	/**
	 * Return a String representation of the list for the GUI
	 */
	public String newToString()
	{
		String output = "";

		if (head != null)
		{
			DoublyLinkedListNode<T> tempNode = (DoublyLinkedListNode<T>) head;
			while (tempNode != null)
			{
				output += tempNode.getData().getClass() + ";";
				tempNode = (DoublyLinkedListNode<T>) tempNode.getNext();
			}
			
		}
		output += "[END]";
		return output;
	}
	
	/**
	 * Return a traversed String representation of the list 
	 * @return
	 */
	public String traversalString()
	{
		String output = "";

		if (head != null)
		{
			DoublyLinkedListNode<T> tempNode = (DoublyLinkedListNode<T>) tail;
			while (tempNode != null)
			{
				output += tempNode.getData() + ", ";
				tempNode = (DoublyLinkedListNode<T>) tempNode.getPre();
			}
			
		}
		output += "}";
		return output;
	}



	public void setHead(DoublyLinkedListNode<T> nextNode) {
		if (isEmpty())	{
			head = nextNode;
			tail = nextNode;
		}
		else 
			head = nextNode;
		
	}
	
	
	
}
