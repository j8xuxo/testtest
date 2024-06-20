package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AssertJTest {

  @Test
  @DisplayName("空的測試")
  void test() {
    //Assertions.fail("failed");
  }

  @Test
  void assertNumbers() {
    // arrange
    int number = 1;

    // act
//    number = -1;

    // assert
    Assertions.assertThat(number)
      .isNotZero()
      .isGreaterThanOrEqualTo(0)
      .isLessThan(100)
      .isPositive()
      .isNotNegative()
      .isIn(1, 2, 3, 4, 5);
  }

  @Test
  void assertStrings() {
    String string = "hello world";
    Assertions.assertThat(string)
      .isNotBlank()
      .isEqualToIgnoringCase("HELLO WORLD")
      .isLowerCase()
      .contains("hello");
  }

  @Test
  void assertBoolean() {
    boolean bool = true;
    Assertions.assertThat(bool)
      .isTrue();
  }

  @Test
  void assertLocalDate() {
    LocalDate date = LocalDate.now();
    Assertions.assertThat(date)
      .isToday()
      .isBetween(date.minusDays(1), date.plusDays(1))
      .isAfterOrEqualTo(LocalDate.now());
  }

  @Test
  void assertOptional() {
    var opt = Optional.of(1);
    Assertions.assertThat(opt)
      .isPresent()
      .hasValue(1)
      .get().isEqualTo(1);
  }

  @Disabled
  @Test
  void softAssert() {
    try (var assertions = new AutoCloseableSoftAssertions()) {
      assertions.assertThat("matt").isUpperCase();
      assertions.assertThat(0).isPositive();
    }
  }

  @Test
  void assertObject() {
    var entity = new ResidentEntity();
    entity.setNhiNo("1234567890");
    entity.setPhoneNo("0987654321");

    Assertions.assertThat(entity)
//      .extracting("nhiNo", "phoneNo")
      .extracting(ResidentEntity::getNhiNo, ResidentEntity::getPhoneNo)
      .contains("1234567890", "0987654321");
  }

  @Test
  void assertCollection() {
    // arrange
    var entity1 = new ResidentEntity();
    entity1.setPhoneNo("a");
    entity1.setNhiNo("a1");
    var entity2 = new ResidentEntity();
    entity2.setPhoneNo("b");
    entity2.setNhiNo("b1");

//    var list = new ArrayList<ResidentEntity>();
//    list.add(entity1);
//    list.add(entity2);

    var list = List.of(entity1, entity2);
    Assertions.assertThat(list)
      .isNotEmpty()
      .hasSize(2)
      .extracting(ResidentEntity::getPhoneNo, ResidentEntity::getNhiNo)
      .contains(
        Assertions.tuple("a", "a1"),
        Assertions.tuple("b", "b1")
      );
  }

  @Test
  void assertMap() {
//    var map = new HashMap<String, String>();
//    map.put("a", "a1");
//    map.put("b", "b1");
//    map.put("c", "c1");

    var map = Map.of(
      "a", "a1",
      "b", "b1",
      "c", "c1"
    );

    Assertions.assertThat(map)
      .isNotEmpty()
      .hasSize(3)
      .containsKeys("a", "b", "c")
      .matches(actual -> {
        // 驗證 map 中每個 value 加上數字 1 等於其 key
//        boolean pass = true;
//        for (var key : actual.keySet()) {
//          var value = actual.get(key);
//          if (pass && value.equals(key + "1")) {
//            pass = true;
//          } else {
//            pass = false;
//          }
//        }
//        return pass;
        return actual.entrySet().stream().allMatch(
          entry -> entry.getValue().equals(entry.getKey() + "1")
        );
      }, "value equals to key + '1'");
  }

  @Test
  void assertException() {
    Assertions.assertThatThrownBy(() -> {
      var list = List.of(1, 2);
      list.get(2);
    }).isInstanceOf(IndexOutOfBoundsException.class);
  }

  @Test
  void assertExceptionType() {
    Assertions.assertThatExceptionOfType(IndexOutOfBoundsException.class)
      .isThrownBy(() -> {
        List.of().get(1);
      });
  }

  @Test
  void assertCommonExceptionType() {
    Assertions.assertThatNullPointerException()
      .isThrownBy(() -> {
        Object obj = null;
        obj.toString();
      });
  }

  @Test
  void assertCatchException() {
    var throwable = Assertions.catchThrowable(() -> {
      double d = 1 / 0;
    });

    Assertions.assertThat(throwable)
      .isInstanceOf(ArithmeticException.class);
  }

  @Test
  void assertRootCauseException() {
    var throwable = Assertions.catchThrowable(() -> {
      throw new IllegalStateException("hello",
        new IllegalArgumentException("world")
      );
    });

    Assertions.assertThat(throwable)
      .isInstanceOf(IllegalStateException.class)
      .hasMessageContaining("hello")
      .hasRootCauseExactlyInstanceOf(IllegalArgumentException.class)
      .hasRootCauseMessage("world");
  }

}
