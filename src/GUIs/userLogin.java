package GUIs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exceptions.NonexistentUserException;
import user.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class userLogin extends NBAFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userLogin frame = new userLogin();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 609);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Log In");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 0, 446, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nickname:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 63, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 85, 198, 20);
		contentPane.add(textField);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(241, 63, 65, 14);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(241, 85, 200, 20);
		contentPane.add(passwordField);
		
		/**
		 * Log In is done using User(nickname, password)
		 * NonexistentUserException is thrown for unfound user entry
		 */
		JButton loginButton = new JButton("Log In");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					User loggedUser = UserForActionListener();
					mainFrame frame = new mainFrame(loggedUser);
					frame.setVisible(true);
					close();
				}
				catch(IOException excep){
					JOptionPane.showMessageDialog(null, "users.txt is corrupted","Attention", JOptionPane.ERROR_MESSAGE);
				} catch (NonexistentUserException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Attention", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginButton.setBounds(184, 116, 89, 23);
		contentPane.add(loginButton);
		
		JButton btnBackToMain = new JButton("Back to Main Frame");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame frame = new mainFrame(loggedUser);
				frame.setVisible(true);
				close();
			}
		});
		btnBackToMain.setBounds(158, 150, 139, 23);
		contentPane.add(btnBackToMain);
	}
	
	public userLogin() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
	}

	public userLogin(User loggedUser) throws HeadlessException {
		super(loggedUser);
		// TODO Auto-generated constructor stub
	}

	private User UserForActionListener() throws IOException, NonexistentUserException{
		return new User(textField.getText(), passwordField.getText());
	}
}
