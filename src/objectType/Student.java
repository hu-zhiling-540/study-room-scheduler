package objectType;

/**
 * A class for object Student,
 * holds the information regarding the name and student ID
 * @author Zhiling
 *
 */
public class Student {
	
	protected String lastName;
	protected String firstName;
	protected String studentID;
	
	
	/**
	 * Default Constructor
	 * @param last
	 * @param first
	 * @param id
	 */
	public Student(String last, String first, String id)	{
		this.lastName = last;
		this.firstName = first;
		this.studentID = id;
		System.out.println("STUDENT: last " + lastName + " first " + firstName);
	}
	
	
	/**
	 * Getter for the variable firstName
	 * @return
	 */
	public String firstNme()	{
		return this.firstName;
	}
	
	
	/**
	 * Setter for the variable firstName
	 * @return
	 */
	public void setFirstNme(String firstName)	{
		this.firstName = firstName;
	}
	
	
	/**
	 * Getter for the variable lastName
	 * @return
	 */
	public String lastName()	{
		return this.lastName;
	}
	
	/**
	 * Setter for the variable lastName
	 * @return
	 */
	public void setLasttNme(String lastName)	{
		this.lastName = lastName;
	}
	
	
	/**
	 * Getter for the variable studentID
	 * @return
	 */
	public String studentID()	{
		return this.studentID;
	}
	
	
	/**
	 * Return a string of student's full name
	 * @return
	 */
	public String fullName()	{
		String full = lastName + ", " + firstName;
		return full;
	}
	

}
