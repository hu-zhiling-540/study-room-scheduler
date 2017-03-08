package tester;
import static org.junit.Assert.*;
import org.junit.Test;
import dataStructures.DoublyLinkedList;


public class DoublyLinkedListTest {

	@Test
	public void testConstructor()
	{
		DoublyLinkedList<String> testList = new DoublyLinkedList<String>();
		// test an empty list
		assertEquals("head should be null",
				null, testList.getFirstNode());
		assertEquals("tail should be null",
				null, testList.getLastNode());
	}
	

	
	@Test
	public void testInsertFirst()
	{	
		DoublyLinkedList<String> testList = new DoublyLinkedList<String>();
		
		// test an empty list
		testList.insertFirst("Monica");
		assertEquals("next should be null",
				null, testList.getFirstNode().getNext());
		
		// test a filled list
		testList.insertFirst("Phoebe");
		testList.insertFirst("Rachel");
		testList.insertFirst("Chandler");

		assertEquals("The list should be Chandler, Rachel, Phoebe, and Monica.",
					"{Chandler, Rachel, Phoebe, Monica, }", testList.toString());
		assertEquals("Head should be Chandler.",
				"Chandler", testList.getFirstNode().getData());
		assertEquals("Tail should be Monica",
				"Monica", testList.getLastNode().getData());
		assertEquals("The second should be Rachel",
				"Rachel", testList.getFirstNode().getNext().getData());
	}
	
	@Test
	public void testInsertAfter()
	{
		DoublyLinkedList<String> testList = new DoublyLinkedList<String>();
		
		// test an empty list
		testList.insertAfter("Friends", "Monica");
		assertEquals("The list finds nothing but create a Monica node",
					"{Monica, }", testList.toString());
		
		// try to find a non-existent node
		testList.insertAfter("Monica", "Phoebe");
		assertEquals("The list finds nothing and still prints out Monica",
					"{Monica, Phoebe, }", testList.toString());

		assertEquals("tail should be Monica",
				"Phoebe", testList.getLastNode().getData());
		assertEquals("head ",
				"Monica", testList.getFirstNode().getData());
	}
	
	@Test
	public void testInsertBefore()
	{
		DoublyLinkedList<String> testList = new DoublyLinkedList<String>();
		
		// test an empty list
		testList.insertBefore("Monica", "Friends");
		assertEquals("The list finds nothing but create a Monica node",
					"{Monica, }", testList.toString());
		
		// try to find a non-existent node
		testList.insertBefore("Phoebe", "Monica");
		assertEquals("The list finds nothing and still prints out Monica",
					"{Phoebe, Monica, }", testList.toString());

		assertEquals("tail should be Monica",
				"Phoebe", testList.getFirstNode().getData());
		assertEquals("head ",
				"Monica", testList.getLastNode().getData());
		
		testList.insertBefore("Rachel", "Monica");
		assertEquals("The list finds nothing and still prints out Monica",
				"{Phoebe, Rachel, Monica, }", testList.toString());
		assertEquals("head ",
				"Phoebe", testList.getFirstNode().getData());
		assertEquals("head.next ",
				"Rachel", testList.getFirstNode().getNext().getData());
		assertEquals("tail ",
				"Monica", testList.getLastNode().getData());
	}
	
	
	@Test
	public void testInsertLast()
	{
		DoublyLinkedList<String> testList = new DoublyLinkedList<String>();
		
		// test an empty list
		testList.insertLast("Monica");
		assertEquals("next should be null",
				null, testList.getFirstNode().getNext());
		
		// test a filled list
		testList.insertLast("Phoebe");
		testList.insertLast("Rachel");
		testList.insertLast("Chandler");
		assertEquals("The list should be Monica, Phoebe, Rachel, and Chandler.",
					"{Monica, Phoebe, Rachel, Chandler, }", testList.toString());
		assertEquals("tail should be Chandler",
				"Chandler", testList.getLastNode().getData());
		assertEquals("the second should be Phoebe",
				"Phoebe", testList.getFirstNode().getNext().getData());		
	}
	
	
	@Test
	public void testDeleteFirst()
	{
		DoublyLinkedList<String> testList = new DoublyLinkedList<String>();
		
	    // test an empty list
	    testList.deleteFirst();
		assertEquals("The list find nothing.",
				"{}", testList.toString());
	    
	    // test a filled list
		testList.insertFirst("Monica");
		testList.insertFirst("Phoebe");
		testList.insertFirst("Chandler");
		testList.deleteFirst();
		
		assertEquals("The list should print Phoebe and Monica.",
				"{Phoebe, Monica, }", testList.toString());
		assertEquals("The list should print Phoebe",
				"Phoebe", testList.getFirstNode().getData());
		assertEquals("The list should print Monica",
				"Monica", testList.getLastNode().getData());
		testList.deleteFirst();
		assertEquals("The list should print Chandler",
				"Monica", testList.getLastNode().getData());
		
		//after deletes, test if it is possible to add a new node
		testList.insertFirst("Janice");
		assertEquals("The list should print Janice and Monica.",
				"{Janice, Monica, }", testList.toString());
	}
	

	@Test
	public void testDeleteLast()
	{
		DoublyLinkedList<String> testList = new DoublyLinkedList<String>();
		
		testList.insertFirst("Monica");
		testList.insertFirst("Ross");
		testList.insertFirst("Chandler");
		testList.deleteLast();
		
		int count = testList.size();
		assertEquals("The list has two nodes so it should return 2.",
				2, count);
		assertEquals("The list should print {Chandler, Ross, }.",
				"{Chandler, Ross, }", testList.toString());
		assertEquals("The list should print Ross",
				"Ross", testList.getLastNode().getData());
		
		// delete again
		testList.deleteLast();
		count = testList.size();
		assertEquals("The list has one node so it should return 1.",
				1, count);
		assertEquals("The list should print {Chandler, }.",
				"{Chandler, }", testList.toString());
		
		// delete until the list becomes empty
		testList.deleteLast();
		count = testList.size();
		assertEquals("The list has one node so it should return 0.",
				0, count);
		assertEquals("The list is now empty.",
				"{}", testList.toString());
		assertEquals("The list should print nothing",
				null, testList.getLastNode());
	}

	
	@Test
	public void testGetLastNode()
	{	
		DoublyLinkedList<String> testList = new DoublyLinkedList<String>();
		
		// test an empty list
		assertEquals("last node should be null",
				null, testList.getLastNode());
		
		testList.insertFirst("Phoebe");
		testList.insertFirst("Rachel");
		testList.insertFirst("Chandler");
		// test a filled list
		assertEquals("last node should be Phoebe",
				"Phoebe", testList.getLastNode().getData());
	}
	
	@Test
	public void testGetLast()
	{	
		DoublyLinkedList<String> testList = new DoublyLinkedList<String>();
		
		testList.insertFirst("Phoebe");
		testList.insertFirst("Rachel");
		testList.insertFirst("Chandler");
		// test a filled list
		assertEquals("last node should be Phoebe",
				"Phoebe", testList.getLast());
	}

	@Test
	public void testTraversalString()
	{
		DoublyLinkedList<String> testList = new DoublyLinkedList<String>();
		testList.insertFirst("Gunther");
		testList.insertFirst("Janice");
		testList.insertFirst("Emma");
		
		assertEquals("The list should have Emma, Janice and Gunther from the beginning.",
				"Gunther, Janice, Emma, }", testList.traversalString());
	}
	
}
