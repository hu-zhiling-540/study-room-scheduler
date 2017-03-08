package scheduler;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import objectType.Interval;
import objectType.Room;

public class SchedulerController extends JPanel implements ActionListener, 
												ChangeListener, PropertyChangeListener{
	
	protected RoomsFileReader reader;
	protected SchedulerDisplay display;
	protected SchedulerModel model;
	
	
	/**
	 * Default Constructor
	 */
	public SchedulerController()		{
		super(new BorderLayout());
		readFileAndModel();
		setUpDisplay();
		add(display, BorderLayout.CENTER);
	}
	
	
	/**
	 * Import data from xml file
	 */
	public void readFileAndModel()	{
		reader = new RoomsFileReader("rooms.xml");
		Room[] groupRooms = reader.getGroupArr();
		Room[] meetingRooms = reader.getMeetingArr();
		model = new SchedulerModel(groupRooms, meetingRooms);
		
		System.out.println(model.getArrInfo(groupRooms));
		System.out.println(model.getArrInfo(meetingRooms));
	}
	
	
	/**
	 * Create a new instance of the view and add listeners to it
	 */
	public void setUpDisplay()	{
		display = new SchedulerDisplay();
		display.addActionListener(this);
		display.addChangeListener(this);
		display.addPropertyChangeListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {	        
		if (e.getSource() == display.makeResvButton)	{
			String firstNameInput = display.firstNameText.getText().toString();
			String lastNameInput = display.lastNameText.getText().toString();
			Integer startTimeInput = Integer.parseInt(display.startText.getText().toString());
			Integer endTimeInput = Integer.parseInt(display.endText.getText().toString());
			String stuIDInput = display.stuIDText.getText().toString();
			System.out.println(firstNameInput + lastNameInput);
			
			Interval newResv = model.createResv(lastNameInput, firstNameInput, stuIDInput, 
							startTimeInput, endTimeInput);
			String type = (String) display.pickType.getSelectedItem();
            newResvRequest(type, newResv);
        	}
		
		if (e.getSource() == display.checkButton)	{
			System.out.println(model.existingResv());
		}
	}
	
	
	/**
	 * Pull out a new request for reservation
	 * @param type
	 * @param newResv
	 */
	public void newResvRequest(String type, Interval newResv)	{
		if (!model.checkValidInputs(newResv))	{
			display.errorMsg_invalidInputs();
			return;
		}
		if (!model.checkLimit(newResv))		{
			display.errorMsg_Limit();
			return;
		}
		// if not available to make a resveration
		if (!typeRoom(type, newResv))	{
			// if adding to the waitlist
			if (display.errorMsg_Conflict())	{
				model.waitlisted(type, newResv);
				display.msg_wl();
			}	
		}
		else
			display.msg_newResv("New Reservation: " + newResv.printInfo());
	}
	
	
	/**
	 * Make a reservation for a specific type
	 * @param choice
	 * @param newResv
	 */
	public boolean typeRoom(String choice, Interval newResv)	{
		boolean avail = false;
		switch (choice) {//check for a match
			case "Group Study":
				avail = model.checkAndAdd_Group(newResv);
				break;
			case "Meeting":
				avail = model.checkAndAdd_Meeting(newResv);
				break;
			default:
				System.out.println("weird choice");
	        }
		return avail;
	}
	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		display.startText.setText(String.valueOf(display.start_slider.getValue()));
		display.endText.setText(String.valueOf(display.end_slider.getValue()));
	}


	@Override
	public void propertyChange(PropertyChangeEvent e) {
		String startInput = display.startText.getText();
		String endInput = display.endText.getText();
		
		if (e.getSource()== display.startText) {
	        int start = Integer.parseInt(startInput);
	        display.start_slider.setValue(start);
		}
		if (e.getSource()== display.endText) {
	        int end = Integer.parseInt(endInput);
	        display.end_slider.setValue(end);
		}
	}
	
	
	
	
	
}
