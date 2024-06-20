package tw.com.fcb.mimosa.examples.gettingstarted;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import tw.com.fcb.mimosa.domain.t9n.Translated;

@Data
@Entity
@Table
public class ErrorCode implements Translated {

  @Id
  @GeneratedValue
  Long id;
  String category;
  String code;
  String translation;
}
