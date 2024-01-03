package GUIdesign;

import java.awt.Component;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import player.Team;

import java.awt.TextArea;
import java.awt.Panel;
import java.awt.Rectangle;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Box;
import javax.swing.ImageIcon;

public class DraftingTeamPanel extends JPanel {

	protected static final long serialVersionUID = 1L;
	protected TextArea textArea;
	protected JPanel panel_1 = new JPanel();
	public Team team;
	private Box verticalBox;
	private JLabel lblNewLabel_1;


	/**
	 * Create the panel.
	 */
	public DraftingTeamPanel(Team tm) {
		team = tm;
        setAlignmentY(Component.BOTTOM_ALIGNMENT);
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        

        JLabel lblNewLabel = new JLabel(team.getName());
        lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        
        
        verticalBox = Box.createVerticalBox();
        verticalBox.add(lblNewLabel);
        add(verticalBox);
        
        lblNewLabel_1 = new JLabel("");
        //lblNewLabel_1.setIcon(new ImageIcon(team.getImagePath()));
        verticalBox.add(lblNewLabel_1);

        panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(panel_1);
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
        textArea= new TextArea();
        textArea.setEditable(false);
        panel_1.add(textArea);
        
        
    }
	
	/**
	 * Sets @textArea according to player names of @team 
	 */
	public void setTextArea() {
		panel_1.remove(textArea);
		textArea= new TextArea();
		team.getRoster().keySet().forEach((String s) -> {textArea.append(s + "\n");});
        panel_1.add(textArea);

	}
}


