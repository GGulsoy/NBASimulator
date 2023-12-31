package user;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import exceptions.*;

public class User {
	private String Nickname; //unique
	private String Password;
	private String Name;
	private String Surname;
	private String Age;
	private String EMail; //unique
	private String ImagePath; //if null, default profile photo will be shown
	
	public enum UserField {
        NICKNAME(0),
        PASSWORD(1),
        NAME(2),
        SURNAME(3),
        AGE(4),
        EMAIL(5),
        IMAGE_PATH(6);

        private final int value;

        UserField(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
	/**
	 * In this constructor, the image is selected from src/photos/default.png
	 * 
	 * @param nickname
	 * @param password
	 * @param name
	 * @param surname
	 * @param age
	 * @param eMail
	 */
	public User(String nickname, String password, String name, String surname, String age, String eMail) {
		Nickname = nickname;
		Password = password;
		Name = name;
		Surname = surname;
		Age = age;
		EMail = eMail;
		ImagePath = "src/photos/default.png";
	}
	public User(String nickname, String password, String name, String surname, String age, String eMail,
			String imagePath) {
		Nickname = nickname;
		Password = password;
		Name = name;
		Surname = surname;
		Age = age;
		EMail = eMail;
		ImagePath = imagePath;
	}
	
	
	
	//getters and setters
	public String getNickname() {
		return Nickname;
	}
	public void setNickname(String nickname) {
		Nickname = nickname;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSurname() {
		return Surname;
	}
	public void setSurname(String surname) {
		Surname = surname;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	public String getEMail() {
		return EMail;
	}
	public void setEMail(String eMail) {
		EMail = eMail;
	}
	public String getImagePath() {
		return ImagePath;
	}
	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}
	/**
	 * Checks 	@param Nickname: can only include letter and number characters.
	 * 			@param Password: can only include letter and number characters.
	 * 			@param Name: should have at least three characters (only letters).
	 * 			@param Surname: should have at least three characters (only letters).
	 * 			@param Age: must be at least 12
	 * 			@param eMail: should be in the correct format. (e.g., name@domain.com).
	 * @throws InvalidUserLogin
	 */
	public void UserValidator() throws InvalidUserLogin{
		String message = "";
		if (!Nickname.matches("[a-zA-Z0-9]+")) {
			message += "Please enter only number or letter for nickname.\n";
		}
		if (Password.length() < 8) {
			message += "Password must have at least 8 characters.\n";
		}
		if (Name.length() < 3) {
			message += "Enter your name having at least 3 letters.\n";
		}
		if (Surname.length() < 3) {
			message += "Write your surname having at least 3 letters.\n";
		}
		
		try {
			if(Integer.parseInt(Age) < 12) {
				message += "Your age must be bigger than 12 to play game.\n";
			}
		}
		catch(NumberFormatException e) {
			message += "Please write your age with numbers.\n";
		}
		//RFC 5322
		if(!EMail.matches("[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+")) {
			message += "Please enter valid eMail.\n";
		}
		
		if(!message.equals("")) {throw new InvalidUserLogin(message);}
	}
	/**
	 * Checks all users saved at users.txt
	 * 	Looks to @Nickname and @EMail if it is unique
	 * @throws OccupiedUserException, if user nickname or password is not unique
	 * @throws IOException: for existance of users.txt
	 */
	public void UserHistoryChecker() throws OccupiedUserException, IOException{
		
		Scanner input = new Scanner(Paths.get("src/user/users.txt"));
		while (input.hasNext()) {  
			String word = input.nextLine();
			String[] words = word.split(";");
			if(words[0].equals(Nickname) && words[5].equals(EMail)) {
				input.close(); throw new OccupiedUserException("Entered Nickname and EMail is alredy in use.\nPlease enter different ones.");
			}
			else if(words[0].equals(Nickname)) {
				input.close(); throw new OccupiedUserException("Entered Nickname is alredy in use.\nPlease enter different one.");
			}
			else if(words[5].equals(EMail)) {
				input.close(); throw new OccupiedUserException("Entered EMail is alredy in use.\nPlease enter different one.");
			}
		}    
		input.close();
	}
	/**
	 * 
	 * @throws IOException: for existance of users.txt
	 */
	public void UserSaver() throws IOException{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/user/users.txt", true))) {
			writer.write(String.format("%s;%s;%s;%s;%s;%s;%s%n", Nickname, Password, Name, Surname, Age, EMail, ImagePath));
		}
	}
	/**
	 * 
	 * @param nickname
	 * @param password
	 * @throws NonexistentUserException
	 * @throws IOException
	 */
	public User(String nickname, String password) throws NonexistentUserException, IOException{
		boolean[] check = {false, false}; 
		String word; String[] words = new String[7];//dummy initialization
		Scanner input = new Scanner(Paths.get("src/user/users.txt"));
		while (input.hasNext()) {  
			word = input.nextLine();
			words = word.split(";");
			if(words[0].equals(nickname)) {
				check[0] = true; check[1] = words[1].equals(password); break;
			}
		}    
		input.close();
		
		if(check[0] && check[1]) {
			Nickname = nickname;
			Password = password;
			Name = words[2];
			Surname = words[3];
			Age = words[4];
			EMail = words[5];
			ImagePath = words[6];
		}
		else if(check[0]) {throw new NonexistentUserException("Password is wrong");}
		else {throw new NonexistentUserException("There is not such a user, but you can sign up");}
	}
	public static ArrayList<String[]> getUserList() throws IOException{
		ArrayList<String[]> list = new ArrayList<String[]>();
		String word; String[] words = new String[7];//dummy initialization
		Scanner input = new Scanner(Paths.get("src/user/users.txt"));
		while (input.hasNext()) {  
			word = input.nextLine();
			words = word.split(";");
			list.add(words);
		}    
		input.close();
		return list;
	}
	public static User changeUser(User loggedUser, UserField variable, String enter) 
			throws IOException, InvalidUserLogin, OccupiedUserException, NonexistentUserException {
		ArrayList<String[]> list = getUserList(); User rtrn = null; String willBeWritten = "";
		for(String[] user : list) {
			//scan to change with enterance data
			if(loggedUser.getNickname().equals(user[variable.value])) {
				user[variable.value] = enter;
				rtrn = new User(user[0], user[1], user[2], user[3],
						user[4], user[5], user[6]);
				rtrn.UserValidator(); rtrn.UserHistoryChecker();
			}
			willBeWritten += String.format("%s;%s;%s;%s;%s;%s;%s%n", 
					user[0], user[1], user[2], user[3], user[4], user[5], user[6]);
		}
		if(rtrn == null) {
			throw new NonexistentUserException("Logged User is not found, \nprobably there is problem at users.txt, \nplease login again.");
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("src/user/users.txt")); 
		writer.write(willBeWritten);
		writer.close();
		return rtrn;
	}
	
}
