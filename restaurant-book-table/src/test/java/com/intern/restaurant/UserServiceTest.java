package com.intern.restaurant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.intern.restaurant.dto.UserDTO;
import com.intern.restaurant.exception.UserException;
import com.intern.restaurant.exception.UserNotFoundException;
import com.intern.restaurant.model.User;
import com.intern.restaurant.service.UserService;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void testDeleteByID() {
		userService.deleteUser(3);
	}
	
	@Test
	public void testCreateUserExists() {
		assertThatThrownBy(() -> {
			userService.createUser(new User(1,"luongloi","123abc","Luong Loi","ha noi", "0987654321",1,LocalDateTime.now(),"No","admin","luongloi@gmail.com"));
		}).isInstanceOf(UserException.class)
		.hasMessageContaining("User already exists");
	}
	
	@Test
	public void testCreateUser() {
		User user = userService.createUser(new User(1,"nguyenvana","123abc","Nguyen Van A","ha noi", "0887654321",1,LocalDateTime.now(),"No","user","nguyenvana@gmail.com"));
		assertThat(user).isNotNull();
	}
	

}
