package com.inventory.Service;

import jakarta.annotation.PostConstruct;

public interface EncryptionService {
public void postConstruct();
public String encryptPassword(String password);
public boolean verifyPassword(String password, String hash);
}
