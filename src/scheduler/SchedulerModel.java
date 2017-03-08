package scheduler;

import dataStructures.QueueLL;
import objectType.Interval;
import objectType.Room;
import objectType.Student;

/**
 * Storing data of existing rooms,
 * processing logic of adding reservations,
 * maintaining status.
 * @author Zhiling
 *
 */
public class SchedulerModel {
	
	// queue for unprocessed reservations
	protected QueueLL<Interval> groupResvQueue;		// group study
	protected QueueLL<Interval> meetingResvQueue;	// meetings rooms

	// array for holding rooms
	protected Room[] groupR; // group study
	protected Room[] meetingR; // meeting rooms
	
	
	/**
	 * Default constructor
	 * @param arr
	 */
	public SchedulerModel(Room[] groupArr, Room[] meetingArr)	{
		this.groupR = groupArr;
		this.meetingR = meetingArr;
		groupResvQueue = new QueueLL<Interval>();
		meetingResvQueue = new QueueLL<Interval>();
	}
	
	
	/**
	 * XML reader passed in an array
	 * @param arr
	 */

	/**
	 * Getter for the group study array
	 * @return
	 */
	public Room[] getGroupArr()	{
		return groupR;
	}
	
	/**
	 * Getter for the meeting rooms array
	 * @return
	 */
	public Room[] getMeetingArr()	{
		return meetingR;
	}
	
	
	/**
	 * Print out the information on rooms by traversing a particular array
	 * @param arr
	 * @return
	 */
	public String getArrInfo(Room[] arr)	{
		String s = " ";
		for (int i = 0; i < arr.length; i ++)	{
			s += arr[i].roomName() + ": " + arr[i].roomLoc() + ", ";
		}
		return s;
	}
	
	
	/**
	 * Count of all group rooms
	 * @return
	 */
	public int groupRoomsCount()	{
		return groupR.length;
	}
	
	
	/**
	 * Count of all meeting rooms
	 * @return
	 */
	public int meetingRoomsCount()	{
		return meetingR.length;
	}
	
	/**
	 * Count of all reservable rooms
	 * @return
	 */
	public int allRoomsCount()	{
		return (groupRoomsCount() + meetingRoomsCount());
	}
	
	
	/**
	 * Create a new student with passed in information
	 * @param last
	 * @param first
	 * @param id
	 */
	public void createNewStu(String last, String first, String id)	{
		Student newStu = new Student(last, first, id);
	}
	
	
	/**
	 * Make a new reservations, 
	 * and associate it with a student
	 * @param last
	 * @param first
	 * @param id
	 * @param start
	 * @param end
	 * @return
	 */
	public Interval createResv(String last, String first, String id, int start, int end )	{
		Student newStu = new Student(last, first, id);
		Interval newResv = new Interval(start, end, newStu);
		return newResv;
	}
	
	
	
//	public boolean checkStuResv(Interval newResv){
//		String ID = newResv.resvStu().studentID();
//		if ()
//	}
//	public Student searchStu()	{
//		
//	}
	public boolean checkValidInputs(Interval newResv)	{
		int start = newResv.whenStart();
		int end = newResv.whenEnd();
		if (start >= end)
			return false;
		if (start < 0 || end < 0 || start >24 || end >24)
			return false;
		return true;
	}
	
	public boolean checkLimit(Interval newResv)		{
		int start = newResv.whenStart();
		int end = newResv.whenEnd();
		if ( end - start > 3)
			return false;
		return true;
	}
	

	/**
	 * Check all the group study rooms from beginning to see 
	 * if there is any available slot
	 * @param newResv
	 * @return
	 */
	public boolean checkAndAdd_Group(Interval newResv)	{
		for (int i = 0; i < groupR.length; i++) {
			if (groupR[i].checkAvail(newResv))	{
				groupR[i].checkAvailAndAdd(newResv);
				return true;
			}
		}
		groupResvQueue.enqueue(newResv);	// add to the wait list
		return false;
	}
	
	
	/**
	 * Check all the meeting rooms from beginning to see 
	 * if there is any available slot
	 * @param newResv
	 * @return
	 */
	public boolean checkAndAdd_Meeting(Interval newResv)	{
		for (int i = 0; i < meetingR.length; i++) {
			if (meetingR[i].checkAvail(newResv))	{
				meetingR[i].checkAvailAndAdd(newResv);
				return true;
			}
		}
		return false;
	}

	/**
	 * Add to the wait list of the meeting rooms
	 * @param newResv
	 */
	public void waitlisted(String model, Interval newResv)	{
		switch (model) {//check for a match
		case "Group Study":
			groupResvQueue.enqueue(newResv);	
			break;
		case "Meeting":
			meetingResvQueue.enqueue(newResv);	
			break;
		default:
			System.out.println("weird choice for wl");
        }
	}
	
	/**
	 * Print out the existing reservations
	 * @return
	 */
	public String existingResv()	{
		String s = "Group Study Room: " + "\n";
		for (int i = 0; i < groupR.length; i++) 
			s += groupR[i].roomName() + groupR[i].printResv() + "\n";
		s += "Meeting Room: " + "\n";
		for (int i = 0; i < meetingR.length; i++) 
			s += meetingR[i].roomName() + meetingR[i].printResv() + "\n";
		return s;
		
	}

}
