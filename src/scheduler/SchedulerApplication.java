package scheduler;
import javax.swing.JFrame;

public class SchedulerApplication {
	public static void main( String[] args )
	{	
		JFrame schedulerFrame = new JFrame( "Scheduler by Zhiling Hu" );
		SchedulerController controller = new SchedulerController();
		schedulerFrame.getContentPane().add( controller );
		schedulerFrame.setSize( 800, 400 );
		schedulerFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		schedulerFrame.setVisible( true );
		
	}
}
