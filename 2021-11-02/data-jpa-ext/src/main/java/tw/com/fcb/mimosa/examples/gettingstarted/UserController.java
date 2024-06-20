package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  final UserDtoMapper mapper;
  final UserService service;

  @GetMapping
  List<UserDto> getUsers() {
    return service.getUsers().stream()
        .map(mapper::from)
        .collect(Collectors.toList());
  }

  @PostMapping
  void createUser(@Validated @RequestBody CreateUserDto user) {
    service.createUser(user);
  }

  @PutMapping("/{id}")
  void replaceUser(@Min(0) @PathVariable("id") Long id, @Validated @RequestBody ReplaceUserDto user) {
    service.replaceUser(id, user);
  }

  @DeleteMapping("/{id}")
  void deleteUser(@Min(0) @PathVariable("id") Long id) {
    service.delete(id);
  }

}
