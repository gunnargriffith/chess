package service;

import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.UserData;


public class UserService {
  private final UserDAO userDataaccess;

  public UserService(UserDAO userDataaccess) {
    this.userDataaccess = userDataaccess;
  }

  public UserData createUser(UserData user) throws DataAccessException {
    return userDataaccess.createUser(user);
  }
}
