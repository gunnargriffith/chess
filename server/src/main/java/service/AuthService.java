package service;

import model.AuthData;

public class AuthService {

  final private AuthData authDataAccess;

  public AuthService(AuthData authDataAccess) {
    this.authDataAccess=authDataAccess;
  }


}
