package com.inventory.Service;


import com.inventory.model.LocalUser;

public interface JWTService {
public void postConstruct();
public String generateJWT(LocalUser user);

public String getUsername(String token);
}
