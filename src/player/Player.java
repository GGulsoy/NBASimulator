package player;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Player {
	protected String name;
	protected double[] playerScoreField = new double[5];
	protected final String positionAbbreviation;
	private final double[] weights;
	private HashMap<String, String> playerData = new HashMap<String, String>();
	

	protected static Random random = new Random(); //undecided if will be static or not
	/**
	 * @param index: Indicates which indexes of @playerScoreField and @weights corresponds to which score
	 * @param abbreviation: Indicates the abbreviation at the CSV data
	 */
	public enum PlayerScoreKind{
		POINTS(0, "PTS"),
		TOTAL_REBOUNDS(1, "TRB"),
		ASSISTS(2, "AST"),
		BLOCKS(3, "BLK"),
		STEALS(4, "STL");
		
		private int index;
		private String abbreviation;
		
		PlayerScoreKind(int index, String abbreviation) {
			this.index = index; this.abbreviation = abbreviation;
		}
		
		public int index() {
			return index;
		}
		public String abbreviation() {
			return abbreviation;
		}
	}
	
	protected enum PlayerPositionKind {
	    PG(new double[]{0.25, 0.05, 0.3, 0.05, 0.35}),//POINT_GUARD
	    SG(new double[]{0.3, 0.1, 0.2, 0.05, 0.35}),//SHOOTING_GUARD
	    PF(new double[]{0.3, 0.1, 0.2, 0.05, 0.35}),//POWER_FORWARD
	    SF(new double[]{0.3, 0.1, 0.2, 0.05, 0.35}),//SMALL_FORWARD
	    C(new double[]{0.3, 0.1, 0.2, 0.05, 0.35}),//CENTER
		PF_SF(new double[]{0.3, 0.1, 0.2, 0.05, 0.35}),
		SF_SG(new double[]{0.3, 0.1, 0.2, 0.05, 0.35}),
		SG_PG(new double[]{0.275, 0.075, 0.3, 0.05, 0.35});

	    private double[] weights;

	    PlayerPositionKind(double[] weights) {
	        this.weights = weights;
	    }
	}

	/**
	 * 
	 * @return PlayerPositionKind enum is used to determine th weights by subclasses
	 */
	protected PlayerPositionKind getPositionEnum() {
		for(PlayerPositionKind kind : PlayerPositionKind.values()) {
			if(kind.toString().equals(positionAbbreviation)) {return kind;}
		}
		return null;
	}
	public Player(String name, double[] playerScoreField, String positionAbbreviation) {
		super();
		this.name = name;
		this.playerScoreField = playerScoreField;
		this.positionAbbreviation = positionAbbreviation;
		this.weights = this.getPositionEnum().weights;
	}
	public String getName() {
		return name;
	}
	public double[] getPlayerScoreField() {
		return playerScoreField;
	}
	public void addData(String header, String data) {
		playerData.put(header, data);
	}
	/**
	 * 
	 * @return instance of the subclass
	 */
	
	
	/**
	 * Heders of the CSV file is abbreviations(see enums @PlayerScoreKind and @PlayerPositionKind)
	 * @return list
	 * @throws IOException
	 */
	public static LinkedList<Player> formPlayerList() throws IOException{
		LinkedList<Player> list = new LinkedList<Player>();
		String word; String[] words = new String[7];//dummy initialization
		Scanner input = new Scanner(Paths.get("src/player/2022-2023 NBA Player Stats - Regular.csv"));
		
		/**
		 * Finding indexes of headers
		 * 
		 */
		List<String> tempList = Arrays.asList(input.nextLine().split(";"));
		ArrayList<String> header = new ArrayList<>(tempList);
		
		
		while (input.hasNext()) {  
			word = input.nextLine();
			words = word.split(";");
			String playerName = words[header.indexOf("Player")];
			if(!doesContain(list, playerName)) {
				String positionAbbreviation = words[header.indexOf("Pos")];
				double[] playerScoreField = new double[5];
				for(PlayerScoreKind kind : PlayerScoreKind.values()) {
					int dataIndex = header.indexOf(kind.abbreviation());
					playerScoreField[kind.index()] = Double.parseDouble(words[dataIndex]);
				}
				
				try {
					Player player = new Player(playerName, playerScoreField, positionAbbreviation);
					for(int i : IntStream.range(0, header.size()).toArray()) {
						player.addData(header.get(i), words[i]);
					}
					list.add(player);
				} catch (NullPointerException e) {//catches positionAbbreviations PF-SF, SF-SG, SG-PG
					positionAbbreviation = positionAbbreviation.replace("-", "_");
					
					
					Player player = new Player(playerName, playerScoreField, positionAbbreviation);
					for(int i : IntStream.range(0, header.size()).toArray()) {
						player.addData(header.get(i), words[i]);
					}
					list.add(player);
				}
			}
		}    
		input.close();
		return list;
	}
	/**
	 * written to check entries
	 * found that there is 1 or 3 entries for each player
	 * @throws IOException
	 */
	private static void checkPossiblePersonEntries() throws IOException {
		String word; String[] words = new String[7];//dummy initialization
		Scanner input = new Scanner(Paths.get("src/player/2022-2023 NBA Player Stats - Regular.csv"));
		HashMap<String, Integer> personsAndEntries = new HashMap<String, Integer>();
		
		/**
		 * Finding indexes of headers
		 * 
		 */
		List<String> tempList = Arrays.asList(input.nextLine().split(";"));
		ArrayList<String> header = new ArrayList<>(tempList);
		int playerIndex = header.indexOf("Player");
		
		while (input.hasNext()) {  
			word = input.nextLine();
			words = word.split(";");
			if(personsAndEntries.containsKey(words[playerIndex])) {
				personsAndEntries.put(words[playerIndex], personsAndEntries.get(words[playerIndex]) + 1);
			}
			else {personsAndEntries.put(words[playerIndex], 1);}
		}    
		input.close();
		TreeSet<Integer> entries = new TreeSet<Integer>();
		for(String name : personsAndEntries.keySet()) {
			entries.add(personsAndEntries.get(name));
			System.out.printf("personsAndEntries: %s %s%n", name, personsAndEntries.get(name));
		}
		for(Integer i : entries) {
			System.out.println(i);
		}
	}
	
	private static boolean doesContain(List<Player> list, String playerName) {
		boolean rtrn = false;
		for(Player player : list) {
			if(player.name.equals(playerName)) {rtrn = true; break;}
		}
		return rtrn;
	}
	
	public double calculateRandomScore() {
		double[] stddevs = IntStream.rangeClosed(0, 4)
				.mapToDouble((int x) -> {return (double) playerScoreField[x]/3.0;}).toArray();
		double[] uncorrectedScores = IntStream.rangeClosed(0, 4)
				.mapToDouble((int x) -> {return random.nextGaussian(playerScoreField[x], stddevs[x]);}).toArray();
		double[] Scores = IntStream.rangeClosed(0, 4)
				.mapToDouble((int x) -> {
					return (uncorrectedScores[x] < 0 ? 0 : uncorrectedScores[x])
						> playerScoreField[x]*2 ? playerScoreField[x]*2 : uncorrectedScores[x];}).toArray();
		return IntStream.rangeClosed(0, 4).map((int x) -> {return (int) Math.round(Scores[x]*weights[x]);}).sum();
	}
	
	public static void main(String[] args) {
		
		try {
			for(Player p : formPlayerList()) {System.out.printf("%s%n", p.getName());}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Finding indexes of headers
	
		

	}
}
