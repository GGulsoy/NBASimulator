package player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Team {
	private String name;
	private String imagePath;
	private HashMap<String,Player> roster = new HashMap<String,Player>();
	public Team(String name, String imagePath) {
		this.name = name;
		this.imagePath = imagePath;
	}
	public void addPlayer(Player player) {
		roster.put(player.getName(), player);
	}

}
