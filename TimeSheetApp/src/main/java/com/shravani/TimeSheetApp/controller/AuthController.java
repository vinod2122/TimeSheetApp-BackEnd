package com.shravani.TimeSheetApp.controller;

import com.shravani.TimeSheetApp.dto.UserDto;
import com.shravani.TimeSheetApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AuthController {


    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>>  getUsers()
    {
        List<UserDto> users=userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")

    public ResponseEntity<UserDto> registeredUser(@Valid @RequestBody UserDto userDto,BindingResult result)
    {
        UserDto existingUser=userService.findUserByEmail(userDto.getEmail());
        if(existingUser !=null && existingUser.getEmail() !=null && !existingUser.getEmail().isEmpty())
        {
            result.rejectValue("email",null,"There is already an account registered with the same email");
        }
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(userDto);
        }
        UserDto savedUser=userService.saveUser(userDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

 /*   @GetMapping("/user")
    public ResponseEntity<UserDto> getUserProfile(Authentication authentication)
    {
        String userName=authentication.getName();
        UserDto existingUser=userService.findUserByName(userName);

        if(existingUser!=null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(existingUser);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }*/

    /* @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {

        if (email != null && password.equals("admin123")) {
            // Login successful
            return ResponseEntity.ok("Login successful");
        } else {
            // Login failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }  */

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        // Authenticate the user based on the email and password
        UserDto user = userService.findUserByEmail(email);
        /* if (user == null) {
            // User not found
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        if (!isValidPassword(user, password)) {
            // Invalid password
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
      */
        // Assuming authentication is successful
        // Generate a token or session for the logged-in user
        String token = generateToken(user); // Replace this with your own token generation logic

        // Return the token in the response
        return ResponseEntity.ok(token);
    }

    private boolean isValidPassword(UserDto user, String password) {
        // Compare the provided password with the user's stored password
        // You can use your password hashing and verification logic here

        // Assuming a simple comparison for demonstration purposes
        return user.getPassword().equals(password);
    }

    private String generateToken(UserDto user) {
        // Implement your token generation logic here
        // Generate a unique token for the user

        // Replace this with your own token generation code
        String token = user.getId() + "_" + user.getEmail();

        return token;
    }



}
