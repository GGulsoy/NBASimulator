package GUIs;

/*TO DO: 
 * default photo doesn't showed
 * signup test should be made
 * connections -to main page- to be made
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import user.User;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class UserSignUp extends NBAFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nicknameField;
	private JPasswordField passwordCheck;
	private JPasswordField passwordField;
	private JTextField nameField;
	private JTextField ageField;
	private JTextField surnameMap;
	private JTextField mailField;
	private String holdedImagePathName = "src.default.png";
	private JLabel imageLabel = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserSignUp frame = new UserSignUp();
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
		setBounds(100, 100, 467, 651);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sign Up");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 441, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nickname:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 90, 65, 14);
		contentPane.add(lblNewLabel_1);
		
		nicknameField = new JTextField();
		nicknameField.setBounds(10, 112, 198, 20);
		contentPane.add(nicknameField);
		nicknameField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(241, 90, 65, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Name:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 205, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Surname:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(241, 205, 79, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Age:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(10, 254, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("eMail:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(241, 254, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Confirm Password:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(241, 142, 121, 14);
		contentPane.add(lblNewLabel_7);
		
		passwordCheck = new JPasswordField();
		passwordCheck.setBounds(241, 167, 200, 20);
		contentPane.add(passwordCheck);
		passwordCheck.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(241, 112, 200, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(10, 223, 198, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		ageField = new JTextField();
		ageField.setBounds(10, 273, 198, 20);
		contentPane.add(ageField);
		ageField.setColumns(10);
		
		surnameMap = new JTextField();
		surnameMap.setColumns(10);
		surnameMap.setBounds(241, 223, 198, 20);
		contentPane.add(surnameMap);
		
		mailField = new JTextField();
		mailField.setColumns(10);
		mailField.setBounds(241, 273, 198, 20);
		contentPane.add(mailField);
		
		
		
		
		
		
		
		/**
		 * signUpButton first forms user
		 * 	validates using UserValidator and UserHistoryChecker
		 * 	if successful, swiches to mainFrame
		 * saves the photo if there is a photo uploaded,
		 * 	and saves the path with the user
		 */
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.setBounds(181, 432, 89, 23);
		contentPane.add(signUpButton);
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {;
				/**/
				if(passwordField.getText().equals(passwordCheck.getText())) {
					try {
						User user = new User(nicknameField.getText(), passwordField.getText(), nameField.getText(), 
								surnameMap.getText(), ageField.getText(), mailField.getText());
						user.UserValidator(); user.UserHistoryChecker();
						
						//if there is new photo, it is saved at src/photos based on nickname and file format
						if(!holdedImagePathName.equals("src/photos/default.png")) {
		                	// Specify the destination directory (change this to your desired destination)
		                    String destinationDirectory = "src/photos";
		                    File destinationDir = new File(destinationDirectory);
		                    // Ensure the destination directory exists
		                    if (!destinationDir.exists()) {
		                        destinationDir.mkdirs();
		                    }
		                    // Construct the destination path
		                    int pointIndex = holdedImagePathName.lastIndexOf(".");
		                    String newName = String.format("%s%s", user.getNickname(), holdedImagePathName.substring(pointIndex));
		                    Path destinationPath = destinationDir.toPath().resolve(newName);
		                    
		                    // Perform the file copy
		                    Files.copy(new File(holdedImagePathName).toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
		                    int lastIndex = destinationPath.toString().indexOf("/src/");
		                    String setter = destinationPath.toString().substring(lastIndex+1);
		                    user.setImagePath(setter);
						}
						
						try {
							saveAndClose(user);
						}
						catch(IOException excep){
							JOptionPane.showMessageDialog(null, "users.txt is corrupted","Attention", JOptionPane.ERROR_MESSAGE);
						}
					}
					catch(Exception excep) {
						JOptionPane.showMessageDialog(null, excep.getMessage(),"Attention", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please confirm your password by entering the same password.","Attention", JOptionPane.ERROR_MESSAGE);
				}
				/**/
				
			}
		});
		
		JButton imageButton = new JButton("Load Photo");
		imageButton.setBounds(181, 398, 89, 23);
		contentPane.add(imageButton);
		
		imageLabel = new JLabel("");
		imageLabel.setBounds(181, 304, 89, 87);
		saveImage();
		contentPane.add(imageLabel);
		
		JButton btnBackToMain = new JButton("Back to Main Frame");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame frame = new mainFrame(loggedUser);
				frame.setVisible(true);
				close();
			}
		});
		btnBackToMain.setBounds(156, 462, 139, 23);
		contentPane.add(btnBackToMain);
		
		
		imageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File file = openFileChooser();
					if(file != null) {
						String testPath = file.getAbsolutePath();
						int pointIndex = testPath.lastIndexOf(".");
		                String legalformats = ".png, .jpg, .jpeg, .gif";
		                
		                if(legalformats.indexOf(testPath.substring(pointIndex)) == -1) {
		                	throw new Exception("Please choose the file of png, jpg, jpeg, or gif format");
		                }
		                setHoldedPath(testPath);
		                saveImage();
					}
					else {throw new Exception("Please choose a file.");}
				}
				catch(Exception excep) {
					JOptionPane.showMessageDialog(null, excep.getMessage(),"Attention", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	}
	
	public UserSignUp() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserSignUp(User loggedUser) throws HeadlessException {
		super(loggedUser);
		// TODO Auto-generated constructor stub
	}

	private File openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }
	
	private void saveAndClose(User user) throws IOException {
		user.UserSaver();
		mainFrame frame = new mainFrame(user);
		frame.setVisible(true);
		close();
	}
	
	private void saveImage() {
		ImageIcon initial = new ImageIcon(holdedImagePathName);
		Image image = initial.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_DEFAULT);
		ImageIcon finalIcon = new ImageIcon(image);
		imageLabel.setIcon(finalIcon);
	}
	private void setHoldedPath(String testPath) {
		holdedImagePathName = testPath;
	}


}
