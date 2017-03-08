package tester;

import static org.junit.Assert.*;

import org.junit.Test;

import objectType.Interval;
import objectType.Student;

public class IntervalTest {

	Student maryLyon = new Student("Lyon", "Mary", "520");
	Student francesPerkins = new Student("Perkins", "Frances", "1902");
	@Test
	public void testConstructor() {
		Interval resv = new Interval(2, 5, maryLyon);
		assertEquals(2, resv.whenStart());
		assertEquals(5, resv.whenEnd());
		
		assertEquals("Lyon, Mary", resv.resvStuName());
		assertEquals(maryLyon, resv.resvStu());
		assertEquals("Lyon, Mary: 2 to 5", resv.printInfo());
	}
	
	@Test
	public void testChangeTime()	{
		Interval resv = new Interval(2, 5, maryLyon);
		resv.changeTime(4, 8);
		assertEquals(4, resv.whenStart());
		assertEquals(8, resv.whenEnd());
	}
	
	@Test
	public void testCompareTo()	{
		Interval resv1 = new Interval(2, 5, maryLyon);
		Interval resv2 = new Interval(1, 3, francesPerkins);
		assertEquals(0, resv1.compareTo(resv2));
		resv1.changeTime(0, 1);
		assertEquals(-1, resv1.compareTo(resv2));
		assertEquals(1, resv2.compareTo(resv1));
	}
	

}
