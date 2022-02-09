package com.library.management;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

private final UserRepository userRepository;
	
	@Autowired 
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User>getUser() {
		return userRepository.findAll();
	}
	
	public Optional<User> getUserById(Integer id) {
		return userRepository.findById(id);
	}

	public void addNewUser(User user) {
		Optional<User>userOptional = userRepository.findUserByName(user.getName());
		if(userOptional.isPresent()) {
			throw new IllegalStateException("name taken");
		}
		userRepository.save(user);
	}
	public void deleteUser(Integer userId) {
		boolean exists = userRepository.existsById(userId);
		if(!exists) {
			throw new IllegalStateException(
					"user with id  " + userId + " does not exists");
		}
		userRepository.deleteById(userId);
	}
	
	@Transactional
	public void updateUser(Integer userId,
							String name,
							String email) {
		User user = userRepository.findUserByName(name)
				.orElseThrow(() -> new IllegalStateException(
				"user with name " + name + " does not exist"));
				if(name != null &&
						name.length() > 0 &&
						!Objects.equals(user.getName(), name)) {
					user.setName(name);
				}
				if(email != null &&
						email.length() > 0 &&
						!Objects.equals(user.getEmail(), email)) {
					Optional<User> userOptional = userRepository
							.findUserByEmail(email);
					if (userOptional.isPresent()) {
						throw new IllegalStateException("email taken");
					}
					user.setEmail(email);
					}
		}
}
