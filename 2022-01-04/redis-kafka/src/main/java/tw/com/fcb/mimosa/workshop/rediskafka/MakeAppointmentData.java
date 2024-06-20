package tw.com.fcb.mimosa.workshop.rediskafka;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class MakeAppointmentData {
  String name;
  String time;
  List<String> status = new ArrayList<>();
}
