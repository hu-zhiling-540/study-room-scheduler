package tester;

import static org.junit.Assert.*;

import org.junit.Test;

import objectType.Interval;
import objectType.Room;
import objectType.Student;

public class RoomTest {

	Room myRoom = new Room("dorm", "pro");
	
	@Test
	public void testConstructor() {
		assertEquals("dorm", myRoom.roomName());
		assertEquals("pro", myRoom.roomLoc());
		assertFalse(myRoom.hasResv());
		assertEquals("{ }", myRoom.printResv());
	}
	
	@Test
	public void testAddResv()	{
		Student newStu = new Student("Hu", "Zhiling", "517");
		Interval newResv = new Interval (2, 4, newStu);
		myRoom.addResv(newResv);
		assertEquals("{ Hu, Zhiling: 2 to 4; }", myRoom.printResv());
	}
	
	@Test
	public void testCheckAvailAndAdd()	{
		Student newStu = new Student("Green", "Rachel", "513");
		Interval newResv = new Interval (2, 4, newStu);
		assertTrue(myRoom.checkAvail(newResv));
		myRoom.checkAvailAndAdd(newResv);
		assertEquals("{ Green, Rachel: 2 to 4; }", myRoom.printResv());
		
		Student newStu2 = new Student("Geller", "Monica", "517");
		Interval newResv2 = new Interval (2, 4, newStu2);
		assertFalse(myRoom.checkAvail(newResv2));
		myRoom.checkAvailAndAdd(newResv2);
		assertEquals("{ Green, Rachel: 2 to 4; }", myRoom.printResv());
		
		newResv2.changeTime(0, 1);
		assertTrue(myRoom.checkAvail(newResv2));
		myRoom.checkAvailAndAdd(newResv2);
		assertEquals("{ Geller, Monica: 0 to 1; Green, Rachel: 2 to 4; }", myRoom.printResv());
		
		Student newStu3 = new Student("Buffay", "Phoebe", "514");
		Interval newResv3 = new Interval (4, 6, newStu3);
		assertTrue(myRoom.checkAvail(newResv3));
		myRoom.checkAvailAndAdd(newResv3);
		assertEquals("{ Geller, Monica: 0 to 1; Green, Rachel: 2 to 4; "
				+ "Buffay, Phoebe: 4 to 6; }", myRoom.printResv());
		
		Student newStu4 = new Student("Geller", "Ross", "514");
		Interval newResv4 = new Interval (3, 5, newStu4);
		assertFalse(myRoom.checkAvail(newResv4));
		myRoom.checkAvailAndAdd(newResv4);
		assertEquals("{ Geller, Monica: 0 to 1; Green, Rachel: 2 to 4; "
				+ "Buffay, Phoebe: 4 to 6; }", myRoom.printResv());
		
		newResv4.changeTime(1, 2);
		assertTrue(myRoom.checkAvail(newResv4));
		myRoom.checkAvailAndAdd(newResv4);
		assertEquals("{ Geller, Monica: 0 to 1; Geller, Ross: 1 to 2; "
				+ "Green, Rachel: 2 to 4; Buffay, Phoebe: 4 to 6; }", myRoom.printResv());
		

	}

}
