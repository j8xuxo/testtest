package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import java.util.List;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import tw.com.fcb.mimosa.test.MimosaTest;

@MimosaTest
@Transactional
class ResidentRepositoryTest {

  @Autowired
  ResidentRepository repository;

  ResidentEntity entity;

  @BeforeEach
  void arrange() {
    entity = new ResidentEntity();
    entity.setPhoneNo("0987654321");
    entity.setChooses(List.of());
    entity.setCancels(List.of());
  }

  @Test
  void generateId() {
    // arrange

    // act
    repository.save(entity);

    // assert
    Assertions.assertThat(entity)
        .extracting("id")
        .matches(Objects::nonNull);
  }

  @Test
  void generateLastModifiedDate() {
    // arrange

    // act
    repository.save(entity);

    // assert
    Assertions.assertThat(entity)
        .extracting(ResidentEntity::getLastModifiedDate)
        .matches(Objects::nonNull);
  }

  //  @Commit
  @Test
  void crud() {
    // create
    var id = repository.save(entity).getId();
    Assertions.assertThat(id).isNotNull().isNotNegative();

    // read
    var found = repository.findById(id);
    Assertions.assertThat(found)
        .isPresent()
        .get()
        .extracting(ResidentEntity::getPhoneNo)
        .isEqualTo("0987654321");

    // update
    entity.setNhiNo("1234567890");
    repository.save(entity);
    found = repository.findById(id);
    Assertions.assertThat(found)
        .isPresent()
        .get()
        .extracting(ResidentEntity::getPhoneNo, ResidentEntity::getNhiNo)
        .contains("0987654321", "1234567890");

    // delete
    repository.deleteById(id);
    Assertions.assertThat(repository.findById(id))
        .isEmpty();
  }
}
