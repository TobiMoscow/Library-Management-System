package com.library.management;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path = "api/v1/library/user")

public class UserController {
		
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping 
	public List<User>getUser() {
		return userService.getUser();
	}
	
	@PostMapping 
	public void registerNewUser(@RequestBody User user) {
		userService.addNewUser(user);
	}
	
	@DeleteMapping(path = "{userId}")
	public void deleteUser(@PathVariable Integer userId) {
		userService.deleteUser(userId);
	}
	
	@PutMapping(path = "{userId}")
	public void updateUser(
			@PathVariable("userId") Integer userId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email) {
		userService.updateUser(userId, name, email);	
	}
}
