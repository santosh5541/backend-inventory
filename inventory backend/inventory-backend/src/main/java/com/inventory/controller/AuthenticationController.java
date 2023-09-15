package com.inventory.controller;

import com.inventory.Service.UserService;
import com.inventory.dto.LoginBody;
import com.inventory.dto.LoginResponse;
import com.inventory.dto.RegistrationBody;
import com.inventory.exception.UserAlreadyExistsException;
import com.inventory.model.LocalUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private UserService  userService;
    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException exception){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody){
        String jwt = userService.loginUSer(loginBody);
        if (jwt==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else {
            LoginResponse response =new LoginResponse();
            response.setJwt(jwt);
            return ResponseEntity.ok(response);

        }

    }

    @GetMapping("/me")
    public LocalUser getLoggedInUserProfile(@AuthenticationPrincipal LocalUser user){
        return user;
    }

}
