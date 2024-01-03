package GUIdesign;

import java.awt.Panel;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComboBox;

import player.Player;
import player.Team;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class DraftingUserTeamPanel extends DraftingTeamPanel {

	private static final long serialVersionUID = 1L;
    public JButton selectButton = new JButton("Select");
    public DefaultComboBoxModel<String> comboBoxModel;
    public JComboBox<String> comboBox = new JComboBox<String>();



	/**
	 * Create the panel.
	 */
	public DraftingUserTeamPanel(Team team) {
		super(team);
		Panel panel = new Panel();
        panel_1.add(panel);

        
        panel.add(comboBox);
        
        panel.add(selectButton);


	}
	
	
	public void setComboBox(List<Player> playerSelection) {
		comboBoxModel  = new DefaultComboBoxModel<String>();
		for(Player player : playerSelection) {
			comboBoxModel.addElement(player.getName());
		}
		comboBox.setModel(comboBoxModel);
	}
}
