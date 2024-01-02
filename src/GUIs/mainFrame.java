/************** Pledge of Honor ******************************************
I hereby certify that I have completed this programming project on my own
without any help from anyone else. The effort in the project thus belongs
completely to me. I did not search for a solution, or I did not consult any
program written by others or did not copy any program from other sources. I
read and followed the guidelines provided in the project description.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: <Göktuğ Gülsoy, 0079687>
*************************************************************************/
package GUIs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import user.User;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class mainFrame extends NBAFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame(new User("gokku5885", "gokku5885"));
					//mainFrame frame = new mainFrame();
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
		setBounds(100, 100, 450, 581);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NBA Simulator");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 434, 28);
		getContentPane().add(lblNewLabel);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPlay.setBounds(57, 87, 291, 48);
		getContentPane().add(btnPlay);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userLogin frame = new userLogin(loggedUser);
				frame.setVisible(true);
				close();
			}
		});
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogIn.setBounds(57, 146, 291, 48);
		getContentPane().add(btnLogIn);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserSignUp frame = new UserSignUp(loggedUser);
				frame.setVisible(true);
				close();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSignUp.setBounds(57, 205, 291, 48);
		getContentPane().add(btnSignUp);
		/*
		 * Play and Information Buttons can be seen if a User is logged in
		 */
		JButton btnViewUserInformation = new JButton("View User Information");
		btnViewUserInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(loggedUser == null) {
					JOptionPane.showMessageDialog(null, "Please log in to access user information", "Attention", JOptionPane.ERROR_MESSAGE);
				}
				else {}
			}
		});
		btnViewUserInformation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnViewUserInformation.setBounds(57, 264, 291, 48);
		getContentPane().add(btnViewUserInformation);
		
		JButton btnExitFromProfile = new JButton("Exit From Profile");
		btnExitFromProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame frame = new mainFrame();
				frame.setVisible(true);
				close();
			}
		});
		btnExitFromProfile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExitFromProfile.setBounds(57, 323, 291, 48);
		getContentPane().add(btnExitFromProfile);
		
	}

	public mainFrame() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
	}

	public mainFrame(User loggedUser) throws HeadlessException {
		super(loggedUser);
		// TODO Auto-generated constructor stub
	}
	
}
