package scheduler;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

public class SchedulerDisplay extends JComponent {
	
	// GUI elements
	protected JPanel inputsPanel; 		// north
	protected JPanel calViewPanel;	 	// center
	protected JPanel operPanel; 		// south

	protected JLabel firstNameLabel = new JLabel("First Name: ");
	protected JTextField firstNameText = new JTextField(8);
	protected JLabel lastNameLabel = new JLabel("Last Name: ");
	protected JTextField lastNameText = new JTextField(8);
	protected JLabel stuIDLabel = new JLabel("Student ID: ");
	protected JTextField stuIDText = new JTextField(8);
	
	protected JLabel startTimeLabel = new JLabel("Time In: ");
	protected NumberFormat startFormat;
	protected JFormattedTextField startText;
	
	protected JLabel endTimeLabel = new JLabel("Time Out: ");	
	protected NumberFormat endFormat;
	protected JFormattedTextField endText;
	
	protected JButton checkButton = new JButton("Check Exisiting Reservations");
	protected JButton makeResvButton = new JButton("Make Reservation");
	
	protected JComboBox pickType;
	protected JSlider start_slider;
	protected JSlider end_slider;
	
	
	
	/**
	 * Default Constructor
	 */
	public SchedulerDisplay() {
		super();
		
		this.setLayout( new BorderLayout() );
		this.add( addInputsPanel(), BorderLayout.NORTH );
		this.add( addCalViewPanel(), BorderLayout.CENTER );
		this.add (addOperPanel(), BorderLayout.SOUTH );	
	}
	
	
	/**
	 * Add a panel that holds the operation buttons
	 * @return
	 */
	private Component addOperPanel() {
		operPanel = new JPanel();
		operPanel.add(checkButton);
		operPanel.add(makeResvButton);
		return operPanel;
	}
	

	/**
	 * Add sliders to the calViewPanel
	 */
	private void addSliders()	{
		start_slider = new JSlider(0, 24, 0);
		end_slider = new JSlider(0, 24, 0);
		
		//Turn on labels at major tick marks.
		start_slider.setMajorTickSpacing(24);
		start_slider.setMinorTickSpacing(1);
		start_slider.setPaintTicks(true);
		end_slider.setMajorTickSpacing(24);
		end_slider.setMinorTickSpacing(1);
		end_slider.setPaintTicks(true);
		start_slider.setPreferredSize(new Dimension(400, 80));
		end_slider.setPreferredSize(new Dimension(400, 80));

		Hashtable<Integer, JLabel> position = new Hashtable<Integer, JLabel>();
		for (int i = 0; i <= 24; i+=2)
			position.put(i, new JLabel(Integer.toString(i)));
		
		start_slider.setLabelTable(position);
		start_slider.setPaintLabels(true);
		end_slider.setLabelTable(position);
		end_slider.setPaintLabels(true);	
	}
	
	
	/**
	 * Add a panel that holds the slider bar and time inputs
	 * @return
	 */
	private Component addCalViewPanel() {
		calViewPanel = new JPanel();
		calViewPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		addSliders();
		
		// first column
		gbc.gridx = 0;
		gbc.gridy = 0;
		calViewPanel.add(startTimeLabel, gbc);
        gbc.gridy++;
        calViewPanel.add(endTimeLabel, gbc);
        
        // second column
        gbc.gridx++;
        gbc.gridy = 0;	//reset to default
        calViewPanel.add(startText, gbc);
        gbc.gridy++;
        calViewPanel.add(endText, gbc);
        
        // third column
        gbc.gridx++;
        gbc.gridy = 0;	//reset to default
        calViewPanel.add(start_slider, gbc);
        gbc.gridy++;
        calViewPanel.add(end_slider, gbc);
        
		return calViewPanel;
	}

	
	/**
	 * Create a panel that holds all the textfields regarding the student
	 * @return
	 */
	public JPanel addInputsPanel() {
		formattedTextFields();
		inputsPanel = new JPanel();
		inputsPanel.add(firstNameLabel);
		inputsPanel.add(firstNameText);
		inputsPanel.add(lastNameLabel);
		inputsPanel.add(lastNameText);
		inputsPanel.add(stuIDLabel);
		inputsPanel.add(stuIDText);
		
		String[] roomTypes = { "Group Study", "Meeting" };		 
        //Create the combo box, select the item at index 0
        pickType = new JComboBox(roomTypes);
        pickType.setSelectedIndex(0);
        inputsPanel.add(pickType);
        
		return inputsPanel;
	}
	
	
	/**
	 * Crate a text field that is formatted
	 */
	public void formattedTextFields()	{
		startText = new JFormattedTextField(startFormat);
		startText.setValue(new Integer(0));
		startText.setColumns(3);
		
		endText = new JFormattedTextField(endFormat);
		endText.setValue(new Integer(1));
		endText.setColumns(3);
	}
	
	
	/**
	 * Attach the controller as an action listener to the button
	 * @param controller
	 */
	public void addActionListener (ActionListener controller)	{
		//once addButton is hit, pass in the text and time inputs
		checkButton.addActionListener(controller);
		makeResvButton.addActionListener (controller);
		pickType.addActionListener(controller);
	}

	
	/**
	 * Attach the controller as a change listener to the slider
	 * @param controller
	 */
	public void addChangeListener(ChangeListener controller)	{
		start_slider.addChangeListener(controller);
		end_slider.addChangeListener(controller);
	}
	
	
	/**
	 * Attach the controller as a key listener to the textField
	 * @param controller
	 */
	public void addPropertyChangeListener(PropertyChangeListener controller)	{
		 startText.addPropertyChangeListener(controller);
		 endText.addPropertyChangeListener(controller);
	}


	/**
	 * Create a pop up window for error message on notvalid inputs
	 */
	public void errorMsg_invalidInputs()	{
		JOptionPane.showMessageDialog(this, "The Time Is Not Valid.", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	
	/**
	 * Create a pop up window for error message on time limit
	 */
	public void errorMsg_Limit()	{
		JOptionPane.showMessageDialog(this, "Users are limited to reserving rooms for three hour slots per day.", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	
	/**
	 * Create a pop up window for error message on time conflicts
	 * @return
	 */
	public boolean errorMsg_Conflict()	{
		Object[] options = {"Try again",
                "Waitlist"};
		// create an option dialog
		int n = JOptionPane.showOptionDialog(this,
				"This time all the rooms are booked.", "Sorry!", 
			    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
			    null, //do not use a custom Icon
			    options, //the titles of buttons
			    options[0]); //default button title
		
		boolean wl = false;
		if (n == JOptionPane.NO_OPTION)	{
			wl = true;
			System.out.println("Wanna be waitlisted!");
		}
		
		return wl;
	}
	
	
	/**
	 * Create a pop up window to confirm you are waitlisted
	 */
	public void msg_wl()	{
		JOptionPane.showMessageDialog(this, "You are added to the waitlist.", "Feedback", JOptionPane.ERROR_MESSAGE);
	}
	
	
	/**
	 * Create a pop up window to confirm the reservation
	 * @param info
	 */
	public void msg_newResv(String info)	{
		JOptionPane.showMessageDialog(this, info, "New Reservation!", JOptionPane.PLAIN_MESSAGE);
	}
			
	
	
	
}
