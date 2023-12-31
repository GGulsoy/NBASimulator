package GUIs;
/**
 * info change will be written
 */
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import user.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import exceptions.InvalidUserLogin;
import exceptions.NonexistentUserException;
import exceptions.OccupiedUserException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class UserInformation extends NBAFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInformation frame = new UserInformation(new User("gokku5885", "gokku5885"));
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
	protected void windowBuilder(){
		setBounds(100, 100, 450, 654);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(10, 80, 72, 29);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(loggedUser.getName());
		lblNewLabel_1.setBounds(86, 80, 338, 29);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblSurname = new JLabel("Surname: ");
		lblSurname.setBounds(10, 157, 72, 29);
		getContentPane().add(lblSurname);
		
		JLabel lblSurname_1 = new JLabel(loggedUser.getSurname());
		lblSurname_1.setBounds(86, 157, 338, 29);
		getContentPane().add(lblSurname_1);
		
		JLabel lblEmail = new JLabel("Age:");
		lblEmail.setBounds(10, 232, 72, 29);
		getContentPane().add(lblEmail);
		
		JLabel lblEmail_1 = new JLabel(loggedUser.getAge());
		lblEmail_1.setBounds(86, 232, 338, 29);
		getContentPane().add(lblEmail_1);
		
		JLabel lblEmail_3 = new JLabel("E-Mail");
		lblEmail_3.setBounds(10, 304, 72, 29);
		getContentPane().add(lblEmail_3);
		
		JLabel lblEmail_3_1 = new JLabel(loggedUser.getEMail());
		lblEmail_3_1.setBounds(86, 304, 338, 29);
		getContentPane().add(lblEmail_3_1);
		
		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setBounds(10, 11, 72, 29);
		getContentPane().add(lblNickname);
		
		JLabel lblNickname_1 = new JLabel(loggedUser.getNickname());
		lblNickname_1.setBounds(86, 11, 338, 29);
		getContentPane().add(lblNickname_1);
		
		JLabel imagePanel = new JLabel("");
		imagePanel.setBounds(276, 417, 135, 132);
		ImageIcon initial = new ImageIcon(loggedUser.getImagePath());
		Image image = initial.getImage().getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_DEFAULT);
		ImageIcon finalIcon = new ImageIcon(image);
		imagePanel.setIcon(finalIcon);
		getContentPane().add(imagePanel);
		
		JButton btnNewButton = new JButton("Change the Profile Photo");
		btnNewButton.setBounds(258, 555, 166, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnChangeThePassword = new JButton("Change the Password");
		btnChangeThePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = JOptionPane.showInputDialog("Enter your password:");
				try {
					User.changeUser(loggedUser, User.UserField.PASSWORD, password);
				} catch (InvalidUserLogin | OccupiedUserException | NonexistentUserException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Attention", JOptionPane.ERROR_MESSAGE);
				}
				catch(IOException excep){
					JOptionPane.showMessageDialog(null, "users.txt is corrupted","Attention", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnChangeThePassword.setBounds(10, 398, 166, 23);
		getContentPane().add(btnChangeThePassword);
		
		JButton btnChangeTheNickname = new JButton("Change the Nickname");
		btnChangeTheNickname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nickname = JOptionPane.showInputDialog("Enter your nickname:");
				try {
					User.changeUser(loggedUser, User.UserField.NICKNAME, nickname);
				} catch (InvalidUserLogin | OccupiedUserException | NonexistentUserException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"Attention", JOptionPane.ERROR_MESSAGE);
				}
				catch(IOException excep){
					JOptionPane.showMessageDialog(null, "users.txt is corrupted","Attention", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnChangeTheNickname.setBounds(10, 51, 166, 23);
		getContentPane().add(btnChangeTheNickname);
		
		JButton btnChangeTheNickname_1 = new JButton("Change the Name");
		btnChangeTheNickname_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChangeTheNickname_1.setBounds(10, 123, 166, 23);
		getContentPane().add(btnChangeTheNickname_1);
		
		JButton btnChangeTheNickname_1_1 = new JButton("Change the Surname");
		btnChangeTheNickname_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChangeTheNickname_1_1.setBounds(10, 198, 166, 23);
		getContentPane().add(btnChangeTheNickname_1_1);
		
		JButton btnChangeTheNickname_1_1_1 = new JButton("Change the Age");
		btnChangeTheNickname_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChangeTheNickname_1_1_1.setBounds(10, 270, 166, 23);
		getContentPane().add(btnChangeTheNickname_1_1_1);
		
		JButton btnChangeTheEmail = new JButton("Change the e-Mail");
		btnChangeTheEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnChangeTheEmail.setBounds(10, 344, 166, 23);
		getContentPane().add(btnChangeTheEmail);
		// TODO Auto-generated constructor stub
		
	}

	public UserInformation() throws HeadlessException {
		super();
		
	}

	public UserInformation(User loggedUser) throws HeadlessException {
		super(loggedUser);
		// TODO Auto-generated constructor stub
	}
}
