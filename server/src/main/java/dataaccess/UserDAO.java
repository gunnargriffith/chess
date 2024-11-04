package dataaccess;

import model.UserData;

public interface UserDAO {

  UserData createUser(UserData user) throws DataAccessException;

  UserData getUser(String username) throws DataAccessException;

  void deleteUser(UserData user) throws DataAccessException;

  void clearUsers() throws DataAccessException;

}
