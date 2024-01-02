package GUIs;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.IntStream;

import javax.swing.JFrame;

import player.Player;
import player.Team;
import user.User;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JButton;

public class Drafting extends NBAFrame {
	
	private class CustomPanel extends JPanel {

	    public CustomPanel() {
	        setAlignmentY(Component.BOTTOM_ALIGNMENT);
	        setAlignmentX(Component.LEFT_ALIGNMENT);
	        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

	        JLabel lblNewLabel = new JLabel("TeamName");
	        lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
	        add(lblNewLabel);

	        JPanel panel_1 = new JPanel();
	        panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
	        add(panel_1);
	        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

	        JLabel lblNewLabel_1 = new JLabel("New label");
	        panel_1.add(lblNewLabel_1);

	        JButton btnNewButton = new JButton("New button");
	        panel_1.add(btnNewButton);
	    }
	}
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Drafting frame = new Drafting();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@Override
	protected void windowBuilder() {
		setBounds(100, 100, 450, 515);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create a BoxLayout with Y_AXIS orientation
        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the BoxLayout
        add(Box.createHorizontalGlue()); // Add horizontal glue to center the BoxLayout horizontally

        // Create a vertical Box to hold components
        Box verticalBox = Box.createVerticalBox();
        add(verticalBox);
        verticalBox.add(new CustomPanel());
        verticalBox.add(Box.createVerticalStrut(5));
		
		
	}
	public Drafting(User loggedUser) throws HeadlessException {
		super(loggedUser);
	}

	public Drafting() {
		super();


	}
	
	public static ArrayList<Team> formTeamList() throws IOException{
		ArrayList<Team> rtrn = new ArrayList<Team>();
		LinkedList<Player> playerList = Player.formPlayerList();
		for(int i : IntStream.rangeClosed(0, 19).toArray()) {
			if(i == 0) {rtrn.add(new Team("Your Team", loggedUser.getImagePath()));}
			else {
				
			}
		}
	}
}
