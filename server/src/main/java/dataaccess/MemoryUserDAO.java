package dataaccess;

import model.UserData;

public class MemoryUserDAO implements UserDAO{
  public UserData createUser(UserData user) throws DataAccessException{
    user = new UserData(user)

  }

  public UserData getUser(String username) throws DataAccessException{

  }

  public void deleteUser(UserData user) throws DataAccessException{

  }

}
