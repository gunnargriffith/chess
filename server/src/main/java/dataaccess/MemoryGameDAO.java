package dataaccess;

import model.GameData;
import model.UserData;

import java.util.Collection;
import java.util.HashMap;

public class MemoryGameDAO implements GameDAO{

  final private HashMap<Integer, GameData> gameList = new HashMap<>();

  @Override
  public GameData createGame(GameData game) throws DataAccessException {
    if(gameList.containsKey(game.getGameID())){
      throw new DataAccessException("game already exists");
    }
    game = new GameData(game.getGameID(), game.getWhiteUsername(), game.getBlackUsername(), game.getGameName(), game.getGame());
    return game;
  }

  @Override
  public GameData getGame(int gameID) throws DataAccessException {
    if(!gameList.containsKey(gameID)){
      throw new DataAccessException("Game doesn't exist");
    }
    return gameList.get(gameID);
  }

  @Override
  public void deleteGame(GameData game) throws DataAccessException {
    if(!gameList.containsKey(game.getGameID())){
      throw new DataAccessException("Game doesn't exist");
    }
    gameList.remove(game.getGameID());
  }

  @Override
  public HashMap<Integer, GameData> listGames() throws DataAccessException {
    //maybe do an error for an empty list 
    return gameList;
  }
}
