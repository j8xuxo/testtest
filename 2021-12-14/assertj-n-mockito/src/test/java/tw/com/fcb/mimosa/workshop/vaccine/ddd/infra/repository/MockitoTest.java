package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MockitoTest {

  @Test
  void mock() {
    // arrange
    var repository = Mockito.mock(AppointmentRepository.class);
    Mockito.when(repository.count()).thenReturn(1);

    // act
    var actual = repository.count();

    // assert
    Assertions.assertThat(actual)
      .isGreaterThanOrEqualTo(0)
      .isNotNegative();
  }

  static class AppointmentRepository {

    int count() {
      return -1;
    }
  }

}
