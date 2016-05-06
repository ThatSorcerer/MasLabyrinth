package code.model;

public class Save {
	String playerLine = "";
	String tileLine = "";
	public Save(Player[] players, AbstractTile[][] boardState, int lastInsertion) {
		for(int i = 0; i < players.length; i++) {
			playerLine += addNewPlayerState();
		}
		for(int i = 0; i < players.length;) {
			tileLine += addNewTileState();
		}
	}
	private String addNewPlayerState() {
		String newPlayerState = "[";
		/*
		 * 1) Get Name, then concatenate
		 * 2) Get Color, then concatenate
		 * 3) Get Wands, then concatenate
		 * 4.1) += "["
		 * 4.2) Get ingriedent 1, then concatenate
		 * 4.3) Get ingriedent 2, then concatenate
		 * 4.4) Get ingreident 3, then concatenate
		 * 4.5) += "]"
		 * 5.1) += "["
		 * 5.2) Get token index 0, then concatenate
		 * ...	...
		 * 5.n) Get token index n, then concatenate
		 * 5.n+1) += "]"
 		 */
		return null;
	}
	private String addNewTileState() {
		String newTileState = "[";
		return null;
	}
}
