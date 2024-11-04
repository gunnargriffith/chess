package service;

import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.UserData;


public class UserService {
  private final UserDAO userDataAccess;

  public UserService(UserDAO userDataAccess) {
    this.userDataAccess = userDataAccess;
  }

  public UserData createUser(UserData user) throws DataAccessException {
    return userDataAccess.createUser(user);
  }

  public UserData getUser(String username) throws DataAccessException{
    return userDataAccess.getUser(username);
  }

  public void deleteUser(UserData user) throws DataAccessException{
    userDataAccess.deleteUser(user);
  }

  public void clearUsers() throws DataAccessException{
    userDataAccess.clearUsers();
  }
}
