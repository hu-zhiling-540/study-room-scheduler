package objectType;

/**
 * A class of Interval object,
 * holds the start time and end time for a reservation
 * @author Zhiling
 *
 */
public class Interval implements Comparable<Interval> {
	
	protected int startTime;
	protected int endTime;
	protected Student student;
	

	/**
	 * Default Constructor
	 * @param startTime
	 * @param endTime
	 * @param student
	 */
	public Interval(int startTime, int endTime, Student student)	{
		this.startTime = startTime;
		this.endTime = endTime;
		this.student = student;
		System.out.println("INTERVAL: from " + startTime + " to " + endTime);
	}
	
	
	/**
	 * Getter for the start time
	 * @return
	 */
	public int whenStart()	{
		return this.startTime;
	}
	
	
	/**
	 * Getter for the end time
	 * @return
	 */
	public int whenEnd()	{
		return this.endTime;
	}
	
	
	/**
	 * Getter for Student object
	 * @return
	 */
	public Student resvStu()	{
		return this.student;
	}
	
	
	/**
	 * Getter for the student's name
	 * @return
	 */
	public String resvStuName()	{
		return student.fullName();
	}
	
	
	/**
	 * Setter for both start time and end time
	 * @param newStart
	 * @param newEnd
	 */
	public void changeTime(int newStart, int newEnd)	{
		this.startTime = newStart;
		this.endTime = newEnd;
	}
	
	
	/**
	 * Print out the info for student and resv time
	 * @return
	 */
	public String printInfo()	{
		String info = student.fullName() + ": " + startTime + " to " + endTime;
		return info;
	}

	
	/**
	 * Compare two intervals
	 */
	public int compareTo(Interval newAppt) {
		// new interval's startTime comes after this interval
		if (this.endTime <= newAppt.startTime) 
			return -1;
		// new interval's endTime comes before this interval
		if (newAppt.endTime <= this.startTime) 
			return 1;
		return 0;
	}


}
