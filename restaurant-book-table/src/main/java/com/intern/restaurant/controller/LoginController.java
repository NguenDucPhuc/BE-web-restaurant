package com.intern.restaurant.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.intern.restaurant.service.UserService;
import com.intern.restaurant.dto.UserDTO;
import com.intern.restaurant.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/index")
	public String showHome(Model model, HttpSession session) {
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		if (userDTO != null) {
			model.addAttribute("user",userDTO);
		}
		return "index";
	}
	
//	@PostMapping(value = "/register", consumes = "application/json")
//	public ResponseEntity<User> registerAccountHandler(@Valid @RequestBody User user) {
//		return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
//		return ResponseEntity.ok(userService.createUser(user));
//	}
	
//	@PostMapping("/register")
//    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
//        try {
//            userService.createUser(user);
//            return ResponseEntity.ok("Registration successful");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail registration: " + e.getMessage());
//        }
//    }
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new User());
	     
	    return "signup_form";
	}
	
	@PostMapping("/register-process")
	public String userRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        userService.createUser(user);
        return "register_success";
	}
	
	@GetMapping("/login")
	public String login() {
		return("login");
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
	    List<UserDTO> listUsers = userService.getAllUser();
	    model.addAttribute("listUsers", listUsers);
	     
	    return "users";
	}
}
