package dataaccess;

import model.AuthData;


import java.util.HashMap;

public class MemoryAuthDAO implements AuthDAO{

  final private HashMap<String, AuthData> authTokenList = new HashMap<>();

  @Override
  public AuthData createAuth(AuthData auth) throws DataAccessException {
    auth = new AuthData(auth.getAuthToken(), auth.getUsername());
    if (authTokenList.containsKey(auth.getAuthToken())){
      throw new DataAccessException("authToken already exists");
    }
    authTokenList.put(auth.getAuthToken(), auth);

    return auth;
  }

  @Override
  public AuthData getAuth(String authToken) throws DataAccessException {
    if(!authTokenList.containsKey(authToken)){
      throw new DataAccessException("authToken doesn't exist");
    }
    return authTokenList.get(authToken);
  }

  @Override
  public void deleteAuth(String authToken) throws DataAccessException {
    if(!authTokenList.containsKey(authToken)){
      throw new DataAccessException("authToken doesn't exist");
    }
    authTokenList.remove(authToken);
  }

  @Override
  public void clearAuthList() {
    authTokenList.clear();
  }


}
