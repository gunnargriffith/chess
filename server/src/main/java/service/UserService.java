package service;

import dataaccess.UserDAO;

public class UserService {
  private final UserDAO userDataaccess;

  public UserService(UserDAO userDataaccess) {
    this.userDataaccess = userDataaccess;
  }
}
