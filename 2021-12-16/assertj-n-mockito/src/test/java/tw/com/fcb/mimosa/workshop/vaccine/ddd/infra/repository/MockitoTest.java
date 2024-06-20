package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
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

  @Test
  void mockInput() {
    // arrange
    var mock = Mockito.mock(AppointmentRepository.class);
    Mockito.when(mock.countByName(Mockito.anyString())).thenReturn(1);
    Mockito.when(mock.countByName(Mockito.eq("hi"))).thenReturn(2);

    // act input hello
    var actual = mock.countByName("hello");
    Assertions.assertThat(actual)
        .isPositive()
        .isEqualTo(1);

    // act input hi
    Assertions.assertThat(mock.countByName("hi"))
        .isPositive()
        .isEqualTo(2);
  }

  @Test
  void mockThrow() {
    var mock = Mockito.mock(AppointmentRepository.class);
    Mockito.when(mock.countByNameLike(Mockito.anyString()))
        .thenThrow(IllegalStateException.class);

    // act input hello
    var throwable = Assertions.catchThrowable(() -> mock.countByNameLike("hi"));

    // assert catch IllegalStateException
    Assertions.assertThat(throwable)
        .isInstanceOf(IllegalStateException.class);
  }

  @Test
  void mockVoidThrow() {
    var mock = Mockito.mock(AppointmentRepository.class);
    Mockito.doThrow(IllegalStateException.class).when(mock).print();

    // assert print throw exception
    Assertions.assertThatIllegalStateException()
        .isThrownBy(mock::print);
  }

  @Test
  void spy() {
    //    var repository = new AppointmentRepository();
    //    System.out.println(repository.count()); // -1
    //    var mock = Mockito.mock(AppointmentRepository.class);
    //    System.out.println(mock.count()); // 0

    var spy = Mockito.spy(AppointmentRepository.class);
    Mockito.doThrow(IllegalStateException.class)
        .when(spy).print();

    // assert print() throw IllegalStateException
    Assertions.assertThatIllegalStateException()
        .isThrownBy(() -> spy.print());

    // assert count() returns -1
    Assertions.assertThat(spy.count()).isEqualTo(-1);
  }

  @Test
  void spyVerify() {
    //arrange
    var spy = Mockito.spy(AppointmentRepository.class);

    //act
    spy.call(3);

    // assert
    Mockito.verify(spy, Mockito.times(3)).count();
    Mockito.verify(spy, Mockito.atLeastOnce()).count();
    Mockito.verify(spy, Mockito.never()).print();
  }

  @Test
  void spyVerifyInOrder() {
    // arrange
    var spy = Mockito.spy(AppointmentRepository.class);
    var inOrder = Mockito.inOrder(spy);

    //act
    spy.call(3);

    // assert
    inOrder.verify(spy).call(3);
    inOrder.verify(spy, Mockito.atLeast(2)).count();
  }

  static class AppointmentRepository {

    int count() {
      return -1;
    }

    int countByName(String name) {
      return -1;
    }

    int countByNameLike(String name) {
      return -1;
    }

    void print() {

    }

    void call(int num) {
      for (int i = 0; i < num; i++) {
        count();
      }
    }
  }

}
