package tw.com.fcb.mimosa.workshop.rediskafka;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MakeAppointmentMessage {

  String id;
  String name;
  LocalDateTime time;

}

