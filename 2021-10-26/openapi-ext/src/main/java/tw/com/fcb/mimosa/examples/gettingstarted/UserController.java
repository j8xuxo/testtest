package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	static List<User> users = new ArrayList<User>();

	@Autowired
	UserDtoMapper mapper;
	
	@GetMapping
	List<UserDto> getUsers() {
//		User user = new User();
//		user.setName("matt");
//		user.setAge(18);
//		return user;
//		return User.builder()
//				.age(18)
//				.name("matt")
//				.build();

		List<UserDto> dtos = new ArrayList<>();
		for (User user : users) {
//			UserDto dto = UserDto.builder()
//					.name(user.getName()) // BeanUtils.copyProperties();
//					.build();
			UserDto dto = mapper.from(user);
			dtos.add(dto);
		}

//		List<UserDto> java8dto = users.stream()
//		.map(user -> UserDto.builder().name(user.getName()).build())
//		.collect(Collectors.toList());

		return dtos;
	}

	@PostMapping
	void createUser(@RequestBody User user) {
		users.add(user);
	}
	
	// repository: 2021-10-26 -> public
	
	// modify user
	// @PutMapping
	
	// delete user
	// @DeleteMapping
	
}
