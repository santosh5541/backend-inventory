package com.inventory.Service;

import com.inventory.dto.LoginBody;
import com.inventory.dto.RegistrationBody;
import com.inventory.exception.UserAlreadyExistsException;
import com.inventory.model.LocalUser;
import jakarta.validation.Valid;
import jdk.jshell.spi.ExecutionControl;

public interface UserService {
    LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException;
    public String loginUSer(LoginBody loginBody);
}
