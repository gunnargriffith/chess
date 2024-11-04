package dataaccess;

import model.GameData;
import model.UserData;

import javax.xml.crypto.Data;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public interface GameDAO {

  GameData createGame(GameData game) throws DataAccessException;

  GameData getGame(int gameID) throws DataAccessException;

  void deleteGame(GameData game) throws DataAccessException;

  HashMap<Integer, GameData> listGames()throws DataAccessException;
}
