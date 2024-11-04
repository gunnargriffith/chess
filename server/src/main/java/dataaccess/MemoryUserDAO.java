package dataaccess;

import model.UserData;

import java.util.HashMap;
import java.util.HashSet;

public class MemoryUserDAO implements UserDAO{

  final private HashMap<String,UserData> userList = new HashMap<>();

  @Override
  public UserData createUser(UserData user) throws DataAccessException{
    user = new UserData(user.getUsername(), user.getPassword(), user.getEmail());
    if (userList.containsKey(user.getUsername())){
      throw new DataAccessException("user already exists");
    }
    userList.put(user.getUsername(), user);
    return user;
  }

  @Override
  public UserData getUser(String username) throws DataAccessException{
    if (!userList.containsKey(username)){
      throw new DataAccessException("user doesn't exists");
    }
    UserData user = userList.get(username);
    return user;
  }

  @Override
  public void deleteUser(UserData user) throws DataAccessException {
    if (!userList.containsKey(user.getUsername())){
      throw new DataAccessException("user doesn't exists");
    }
    String username = user.getUsername();
    userList.remove(username);
  }

  @Override
  public void clearUsers() throws DataAccessException {
    userList.clear();
  }
}
