package GUIs;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import GUIdesign.*;

import java.awt.BorderLayout;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Drafting extends NBAFrame {
	
	private static final long serialVersionUID = 1L;
	private Random random = new Random();
	ArrayList<Team> teamList;
	LinkedList<Player> playerSelection;
	int draftingCount;
	DraftingUserTeamPanel userPanel;
	ArrayList<DraftingTeamPanel> botTeams;
	Box verticalBox;
    JButton continueBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Drafting frame = new Drafting(new User("gokku5885", "gokku5885"));
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
		try {
		/*
		 * this list is to be used to dump into userpanel and botPanels, not to be manupilated
		 * Teams of userPanel and botPanels will be manuplated
		 */
		teamList = formTeamList();
		playerSelection = Player.formPlayerList();
		draftingCount = 0;
		
		setBounds(100, 100, 450, 515);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create a BoxLayout with Y_AXIS orientation
        BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        // Create a vertical Box to hold components
        verticalBox = Box.createVerticalBox();
        getContentPane().add(verticalBox);
        
        userPanel = new DraftingUserTeamPanel(teamList.get(0));
        userPanel.setComboBox(playerSelection);
        verticalBox.add(userPanel);
        botTeams = new ArrayList<DraftingTeamPanel>();
        IntStream.rangeClosed(1, 19)
        	.forEach((int j) -> {
        		botTeams.add(new DraftingTeamPanel(teamList.get(j)));
        		verticalBox.add(botTeams.get(j-1));
        		});
        
        // Create a JScrollPane and add the vertical Box to it
        JScrollPane scrollPane = new JScrollPane(verticalBox);

        // Set up the main content pane
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(scrollPane);

        setContentPane(contentPane);
        
        userPanel.selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleButton();

			}
			
        });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		continueBtn = new JButton("Continue");
		continueBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter("src/logs/drafting.txt"));
					String willBeWritten = userPanel.team.getName() + ":"
							+ userPanel.team.getRoster().keySet().stream().reduce("", (a,b) -> a + ";" + b ) + "\n";
					Stream<Team> manipulatedTeams = botTeams.stream().map((DraftingTeamPanel p) -> {return p.team;});
					for(Team t : manipulatedTeams.toList()) {
						willBeWritten += 
								t.getName() + ":" + t.getRoster().keySet().stream().reduce("", (a,b) -> a + ";" + b )+ "\n";
					}
					writer.write(willBeWritten);
					writer.close();
					//open seasons
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
			
        });
		

	}
	public Drafting(User loggedUser) throws HeadlessException {
		super(loggedUser);
	}

	public Drafting() {
		super();


	}
	
	public ArrayList<Team> formTeamList() throws IOException{
		ArrayList<Team> rtrn = new ArrayList<Team>();
		for(int i : IntStream.rangeClosed(0, 19).toArray()) {
			if(i == 0) {rtrn.add(new Team("Your Team", loggedUser.getImagePath()));}
			else {
				rtrn.add(new Team(String.format("Team %s", i), String.format("src/teamPhotos/team%s.png", i)));
			}
		}
		return rtrn;
	}
	/**
	 * issue: swaps the window after selection
	 */
	private void handleButton() {
		if(draftingCount < 5) {
			String selectedPlayerName = (String) userPanel.comboBox.getSelectedItem();
			
			for(int k = 0; k < playerSelection.size(); k++) {
				if(playerSelection.get(k).getName().equals(selectedPlayerName)) {
					Player player = playerSelection.get(k); playerSelection.remove(k);
					userPanel.team.addPlayer(player);
					userPanel.setTextArea();
					break;
				}
			}
			for(int k = 0; k < 19; k++) {
				//teamName includes k + 1
				//botTeams: k
				int index = random.nextInt(playerSelection.size());
				Player player = playerSelection.get(index); playerSelection.remove(index);
				botTeams.get(k).team.addPlayer(player);
				botTeams.get(k).setTextArea();
			}
			draftingCount++;
			userPanel.setComboBox(playerSelection);
		}
		else {
			if(draftingCount == 5) {
				verticalBox.add(continueBtn, 0);
				
			}
		}
		
		
	}
}
