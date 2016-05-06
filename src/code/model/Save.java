package code.model;

/**
 * @author 
 *
 */
public class Save {
	
	/**
	 * 
	 */
	
	String playerLine = "";
	
	/**
	 * 
	 */
	String tileLine = "";
	
	/**
	 * @param players
	 * @param boardState
	 * @param lastInsertion
	 */
	public Save(Player[] players, AbstractTile[][] boardState, int lastInsertion) {
		for(int i = 0; i < players.length; i++) {
			playerLine += addNewPlayerState(i, players[i]);
			if(i<players.length){
				playerLine += ",";
			}
		}
		for(int i = 0; i < players.length;) {
			tileLine += addNewTileState(i, boardState[i/7][i%7]);
		}
	}
	
	/**
	 * @param i
	 * @param player
	 * @return
	 */
	private String addNewPlayerState(int i, Player player) {
		String newPlayerState = "[";
		
		newPlayerState += player.getName() + ",";
		newPlayerState += player.getColor() + ",";
		newPlayerState += player.getWands() + ",[";
		newPlayerState += player.getIngredient(1) + "," + player.getIngredient(2)+ ","+ player.getIngredient(3) + "],[";
		for(int p = 0; p<player.getTokens().size(); p++){
			newPlayerState += player.getTokens().get(p) + ",";
		}
		newPlayerState += "]]";
		
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
		return newPlayerState;
	}
	
	/**
	 * @param n
	 * @param tile
	 * @return
	 */
	private String addNewTileState(int n, AbstractTile tile) {
		String newTileState = "[";
		newTileState = tile.getTileId() + ",";
		if(tile.hasToken()){
			newTileState += tile.getToken().getValue() + ",[";
		}
		else newTileState += 0 + ",[";
		for(int i = 0; i<tile.getPlayers().size(); i++){
			newTileState += tile.getPlayers().get(i).getColor();
			if(tile.getPlayers().size()<i){
				newTileState += ",";
			}
		}
		newTileState += "]]";
		
		return newTileState;
	}
}
