package tester;
import static org.junit.Assert.*;
import org.junit.Test;
import objectType.Student;

public class StudentTest {

	@Test
	public void test() {
		Student me = new Student ("Hu", "Zhiling", "517");
		assertEquals("Zhiling", me.firstNme());
		assertEquals("Hu", me.lastName());
		assertEquals("Hu, Zhiling", me.fullName());
		assertEquals("517", me.studentID());
	}

}
