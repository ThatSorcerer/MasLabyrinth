package code.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Restore {
	/**
	 * @author slgreco, andreirv
	 */
	private String dir = "saves/";
	private String path = "mySave.mls";
	private File restoreFile = new File(dir + path);
	/**
	 * @author slgreco, andreirv
	 */
	private String playerLine = null; // This saves the line that contains Player data
	/**
	 * @author slgreco, andreirv
	 */
	private String currentPlayerData = ""; // This saves the current Player being restored
	
	/**
	 * @author slgreco, andreirv
	 */
	private String tileLine = null;
	
	/**
	 * @author slgreco, andreirv
	 */
	private String currentTileData = "";
	
	/**
	 * @author slgreco, andreirv
	 */ 
	private ArrayList<Player> players;
	
	Player[] restoredPlayers;
	
	AbstractTile[] boardState = new AbstractTile[49];
	
//	public Restore(){
//		players =  new ArrayList();
//		if(saveExists()){
//			sendPlayers();
//		}
//	}
	/**
	 * @author slgreco, andreirv
	 */
	private void getPlayersLine() {
		try {
			FileReader fileReader = new FileReader(dir + path);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			if (bufferedReader.readLine() != null){ playerLine = bufferedReader.readLine(); }
				System.out.println("Restoring Player from: " + dir + path + " | " + playerLine);
			
			bufferedReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println(
	                "Unable to open file '" + 
	                dir + path + "'");  
		}
		catch (IOException e) {
			System.out.println(
	                "Error reading file '" 
	                + dir + path + "'");                  
	            // Or we could just do this: 
	            // e.printStackTrace();
		}
	}
	/**
	 * @author slgreco, andreirv
	 */
	private void getTilesLine() {
		try {
			FileReader fileReader = new FileReader(dir + path);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			if (bufferedReader.readLine() != null){ tileLine = bufferedReader.readLine() + ","; }
				System.out.println("Restoring Boatd State from: " + dir + path + " | " + tileLine);
			
			bufferedReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println(
	                "Unable to open file '" + 
	                dir + path + "'");  
		}
		catch (IOException e) {
			System.out.println(
	                "Error reading file '" 
	                + dir + path + "'");                  
	            // Or we could just do this: 
	            // e.printStackTrace();
		}
	}
	/**
	 * @author slgreco, andreirv
	 */
	private void getCurrentPlayerData() {
		
		int pos = 0;
		
		char ch0 = '[',
			 ch1 = 'n';
		
		while( ch0 != ']' && ch1 != ']') {
			//Update char @ pos and pos +1
			ch0 = playerLine.charAt(pos);
			ch1 = playerLine.charAt(pos+1);
			pos++;
			currentPlayerData = currentPlayerData + ch0;
		}
		currentPlayerData += "]";
		System.out.println("Begining Restore for: " + currentPlayerData);
	}
	
	/**
	 * @author slgreco, ccaballe
	 */
	private void getCurrentTileData(int start) {
		
		int pos = start;
		
		char ch0 = '[',
			 ch1 = 'n';
		
		while( ch0 != ']' && ch1 != ']') {
			//Update char @ pos and pos +1
			ch0 = tileLine.charAt(pos);
			ch1 = tileLine.charAt(pos+1);
			pos++;
			currentTileData = currentTileData + ch0;
		}
		currentTileData += "]";
		System.out.println("Begining Restore for: " + currentTileData);
	}
	
	/**
	 * @author slgreco
	 */
	private void restorePlayer() {
		
		int pos = currentPlayerData.length()-3; //Moves past brackets
		char ch = ']';
		Player me = null;
		GenericFormulaCard myCard = null;
		ArrayList<Token> myTokens = new ArrayList<Token>();
		
		while(ch != '[') {
			ch = currentPlayerData.charAt(pos);
			pos-=2;
			int token = Character.getNumericValue(ch);
			myTokens.add(new Token(token, "TOKEN" + token));
		}
		
		pos-=3;
		
		int ing3 = Character.getNumericValue(pos);
		pos-=2;
		int ing2 = Character.getNumericValue(pos);
		pos-=2;
		int ing1 = Character.getNumericValue(pos);
		
		pos-=3;
		
		myCard = new GenericFormulaCard(ing1, ing2, ing3);
		
		int myWands = Character.getNumericValue(currentPlayerData.charAt(pos));
		pos-=1;
		
		String myColor = "";
		ch = 'x';
		while(ch != ',') {
			pos-=1;
			ch = currentPlayerData.charAt(pos);
			myColor = ch + myColor;
		}
		
		ch = 'x';
		String myName = "";
		
		while(ch != '[') {
			pos-=1;
			ch = currentPlayerData.charAt(pos);
			myName = ch + myName;
		}
		
		me = new Player(myName, myColor, myWands, myTokens, myCard);
		
		playerLine.replace(currentPlayerData+",", "");
		players.add(me);
		if (playerLine.charAt(0)=='[') { restorePlayer(); }
	}

	/**
	 * @author slgreco
	 */
	int tileFocus = 0; // This is the position atwhich the Tile will be added to the boardState[]
	
	public AbstractTile[] restoreBoard() {
		
		int pos = 0;
		//currentTileData.length()-2;
		
		Token token;
		
		char ch = currentTileData.charAt(pos+=1);
		char chRot = currentPlayerData.charAt(pos+=1);
		
		int x = pos%7;
		int y = pos/7;
		if (x%2 == 0 && y%2 == 0) {
			boardState[tileFocus] = new FixedTile(""+ch);	
			boardState[tileFocus].rotate(Character.getNumericValue(chRot)*90);
		} else {
			boardState[tileFocus] = new MoveableTile(""+ch);
			boardState[tileFocus].rotate(Character.getNumericValue(chRot)*90);
		}
		
		ch = currentTileData.charAt(pos+=2);
		int value = Character.getNumericValue(ch);
		if (value != 0) {
			token = new Token(value, "NAME");
			boardState[tileFocus].setToken(token);
		}
		
		pos+=3;
		
		while(ch != ']') {
			//pos+=1
			ch = currentTileData.charAt(pos);
			char ch2 = currentTileData.charAt(pos+1);
			String color = "";
			
			while(ch != ',' && (ch != ']' || ch2 != ']')) {
				color = ch + color;
				ch = currentTileData.charAt(pos+=1);
				ch2 = currentTileData.charAt(pos+1);
			}
			for(int i = 0; i < restoredPlayers.length; i++) {
				if (restoredPlayers[i].getColor() == color) { boardState[tileFocus].addPlayer(restoredPlayers[i]); }
			}
			pos++;
		}
		pos+=3;
		tileFocus++;
		if (pos > currentTileData.length()) {
			return boardState;
		}else {
			getCurrentTileData(pos);
			restoreBoard();	
		}
		return boardState;
	}
	
	/**
	 * @author slgreco, ccaballe
	 */
	public Restore restoreSave() {
		
		getPlayersLine();
		getCurrentPlayerData();
		restorePlayer();
		
		restoredPlayers = new Player[players.size()];
		for (int i = 0; i < players.size(); i++) {
			restoredPlayers[i] = players.get(i);
		}
		
		players.clear();
		
		getTilesLine();
		getCurrentTileData(0);
		restoreBoard();
		
		return this;
		
	}
	
	/**
	 * @author slgreco, andreirv
	 */ boolean saveExists(){
		return restoreFile.exists() && restoreFile.isFile();
	}
}
