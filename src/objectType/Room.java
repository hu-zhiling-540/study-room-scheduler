package objectType;
import dataStructures.DoublyLinkedList;
import dataStructures.DoublyLinkedListNode;

/**
 * A class of Room object,
 * holds information regarding the room:
 * including its name and a list of reservations
 * @author Zhiling
 *
 */
public class Room {
	String roomName;		
	String location;
	String information;		// further step: print out the information on that room
	DoublyLinkedList<Interval> resvList;		// a list of reservations that room holds
	
	public Room(String roomName, String location)	{
		this.location = location;
		this.roomName = roomName;
		resvList = new DoublyLinkedList<Interval>();
	}
	
	public String roomName()	{
		return this.roomName;
	}
	
	public String roomLoc()	{
		return this.location;
	}
	
	public String roomInfo()	{
		return this.information;
	}
	
	/**
	 * Check if the room is empty
	 * @return
	 */
	public Boolean hasResv()	{
		return !resvList.isEmpty(); // has resv if list is not empty
	}
	
	/**
	 * Return a traversed String representation of the list 
	 * @return
	 */
	public String printResv()	{
		String output = "{ ";
		if (hasResv())	{
			DoublyLinkedListNode<Interval> tempNode = (DoublyLinkedListNode<Interval>) resvList.getFirstNode();
			while (tempNode != null)		{
				output += tempNode.getData().printInfo() + "; ";
				tempNode = (DoublyLinkedListNode<Interval>) tempNode.getNext();
			}
		}
		output += "}";
		return output;
	}

	/**
	 * Add a new reservation
	 * @param newResv
	 */
	public void addResv(Interval newResv)	{
		resvList.insertFirst(newResv);
	}
	
	/**
	 * Delete a particular reservation
	 * @param oldResv
	 */
	public void deleteResv(Interval oldResv)	{
		resvList.search(oldResv);
	}
	
	/**
	 * Check the availability of making a reservation in this room at a certain time
	 * @param newResv
	 * @return
	 */
	public Boolean checkAvailAndAdd(Interval newResv)	{
		if (!hasResv())	{		// if has no reservations
			addResv(newResv);	// add resv directly
			return true;
		}
		else	{
			DoublyLinkedListNode<Interval> temp = (DoublyLinkedListNode<Interval>) resvList.getFirstNode();	// starting from the head
			
			while (temp!=null)	{
				// if there's a time conflict
				if (temp.getData().compareTo(newResv) == 0)
					return false;
				// if new resv is before the existing one
				if (temp.getData().compareTo(newResv) == 1)	{
					resvList.insertBefore(newResv, temp.getData());
					return true;
				}
				else if (temp.getNext() == null)	{
					resvList.insertAfter(temp.getData(),newResv);
					return true;
				}
				temp = (DoublyLinkedListNode<Interval>) temp.getNext();
			}
		}
		return true;
	}
	
	
	/**
	 * Check availability of a pacific interval
	 * @param newResv
	 * @return
	 */
	public Boolean checkAvail(Interval newResv)	{
		if (!hasResv())		// if has no reservations
			return true;
		else	{
			DoublyLinkedListNode<Interval> temp = (DoublyLinkedListNode<Interval>) resvList.getFirstNode();	// starting from the head
			
			while (temp!=null)	{
				// if there's a time conflict
				if (temp.getData().compareTo(newResv) == 0)
					return false;
				// if new resv is before the existing one
				if (temp.getData().compareTo(newResv) == 1)
					return true;
				else if (temp.getNext() == null)
					return true;
				temp = (DoublyLinkedListNode<Interval>) temp.getNext();
			}
		return true;
		}
	}
	
	
}