package com.inventory.Service.impl;

import com.inventory.Repository.LocalUserRepository;
import com.inventory.Service.EncryptionService;
import com.inventory.Service.JWTService;
import com.inventory.Service.UserService;
import com.inventory.dto.LoginBody;
import com.inventory.dto.RegistrationBody;
import com.inventory.exception.UserAlreadyExistsException;
import com.inventory.model.LocalUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService  {
    private LocalUserRepository localUserRepository;
    private EncryptionService encryptionService;
    private JWTService jwtService;
    @Autowired
    public UserServiceImpl(LocalUserRepository localUserRepository, EncryptionService encryptionService,JWTService jwtService) {
        this.localUserRepository = localUserRepository;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }
    @Override
    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
    if(localUserRepository.findByUsernameIgnoreCase(registrationBody.getEmail()).isPresent() || localUserRepository.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()){
        throw new UserAlreadyExistsException();
    }
    LocalUser user = new LocalUser();
    user.setEmail(registrationBody.getEmail());
    user.setFirstName(registrationBody.getFirstName());
    user.setLastName(registrationBody.getLastName());
    user.setUsername(registrationBody.getUsername());
    user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
    return localUserRepository.save(user);
    }

    @Override
    public String loginUSer(LoginBody loginBody) {
        Optional<LocalUser> opUser = localUserRepository.findByUsernameIgnoreCase(loginBody.getUsername());
        if (opUser.isPresent()){
           LocalUser user = opUser.get();
           if(encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())){
            return jwtService.generateJWT(user);
           }

        }
        return null;
    }
}
