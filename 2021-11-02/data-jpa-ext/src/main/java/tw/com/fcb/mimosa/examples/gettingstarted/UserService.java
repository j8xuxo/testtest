package tw.com.fcb.mimosa.examples.gettingstarted;

import java.util.Collection;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

  final UserRepository repository;
  final UserDtoMapper mapper;

  public Collection<User> getUsers() {
    return repository.findAll();
  }

  public User createUser(CreateUserDto user) {
    return repository.save(mapper.createUser(user));
  }

  public User replaceUser(long id, ReplaceUserDto user) {
    return repository.findById(id).map(db -> {
      mapper.copyUser(user, db);
      return db;
    }).map(repository::save)
        .orElseThrow(() -> new IllegalArgumentException("id [" + id + "] not exist"));
  }

  public void delete(long id) {
    repository.deleteById(id);
  }

}
