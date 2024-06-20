package tw.com.fcb.mimosa.examples.gettingstarted.client;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.RequiredArgsConstructor;
import tw.com.fcb.mimosa.examples.gettingstarted.CreateUserDto;
import tw.com.fcb.mimosa.examples.gettingstarted.ReplaceUserDto;
import tw.com.fcb.mimosa.examples.gettingstarted.UserDto;
import tw.com.fcb.mimosa.ext.cache.support.MimosaCacheManager;
import tw.com.fcb.mimosa.http.APIRequest;
import tw.com.fcb.mimosa.http.APIResponse;

@RestController
@RequestMapping("/client/users")
@RequiredArgsConstructor
public class MyClientController {

  static final String GET_USRES_CACHE_NAME = "my:cache";
  static final String GET_USRES_CACHE_KEY = "get-users";

  //  final UserRestTemplate service;
  final UserClient service;
  final MimosaCacheManager cacheManager;

  @GetMapping
  APIResponse<List<UserDto>> getUsers() {
    Optional<APIResponse<List<UserDto>>> cached = cacheManager.get(
        GET_USRES_CACHE_NAME,
        GET_USRES_CACHE_KEY,
        // import com.fasterxml.jackson.core.type.TypeReference;
        new TypeReference<APIResponse<List<UserDto>>>() {
        },
        this::loadUsers);
    return cached.orElse(APIResponse.success(Collections.emptyList()));
  }

  Optional<APIResponse<List<UserDto>>> loadUsers() {
    APIResponse<List<UserDto>> data = service.getUsers();
    if (data.isSuccess() && !data.getClientResponse().isEmpty()) {
      return Optional.of(data);
    }
    return Optional.empty();
  }

  @PostMapping
  APIResponse<Long> createUser(@Validated @RequestBody APIRequest<CreateUserDto> user) {
    APIResponse<Long> response = service.createUser(user);
    cacheManager.evict(GET_USRES_CACHE_NAME);
    return response;
  }

  @PutMapping("/{id}")
  void replaceUser(@Min(0) @PathVariable("id") Long id,
      @Validated @RequestBody ReplaceUserDto user) {
    service.replaceUser(id, user);
    cacheManager.evict(GET_USRES_CACHE_NAME);
  }

  @DeleteMapping("/{id}")
  void deleteUser(@Min(0) @PathVariable("id") Long id) {
    service.deleteUser(id);
    cacheManager.evict(GET_USRES_CACHE_NAME);
  }

}
