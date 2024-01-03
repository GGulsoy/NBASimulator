package player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Team implements Comparable<Team>{
	private String name;
	private String imagePath;
	/**
	 * String: player name
	 */
	private HashMap<String,Player> roster = new HashMap<String,Player>();
	private int wins = 0;
	public Team(String name, String imagePath) {
		this.name = name;
		this.imagePath = imagePath;
	}
	public void addPlayer(Player player) {
		roster.put(player.getName(), player);
	}
	
	public void win() {wins++;}
	
	public String getName() {
		return name;
	}
	public String getImagePath() {
		return imagePath;
	}
	public HashMap<String, Player> getRoster() {
		return roster;
	}
	public int getWins() {
		return wins;
	}
	@Override
	public int compareTo(Team o) {
		// TODO Auto-generated method stub
		return (this.wins > o.getWins() ? 1 : (this.wins < o.getWins() ? -1 : 0));
	}
}
