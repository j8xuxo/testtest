package tw.com.fcb.mimosa.examples.gettingstarted.client;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tw.com.fcb.mimosa.examples.gettingstarted.CreateUserDto;
import tw.com.fcb.mimosa.examples.gettingstarted.ReplaceUserDto;
import tw.com.fcb.mimosa.examples.gettingstarted.UserDto;
import tw.com.fcb.mimosa.http.APIRequest;
import tw.com.fcb.mimosa.http.APIResponse;

@FeignClient(name = "users", url = "http://localhost:8081/users")
public interface UserClient {

  @GetMapping
  APIResponse<List<UserDto>> getUsers();

  @PostMapping
  APIResponse<Long> createUser(
      @Validated @RequestBody APIRequest<CreateUserDto> user);

  @PutMapping("/{id}")
  void replaceUser(@Min(0) @PathVariable("id") Long id,
      @Validated @RequestBody ReplaceUserDto user);

  @DeleteMapping("/{id}")
  void deleteUser(@Min(0) @PathVariable("id") Long id);
}
