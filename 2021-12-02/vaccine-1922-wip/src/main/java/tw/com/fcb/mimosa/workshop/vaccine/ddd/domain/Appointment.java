package tw.com.fcb.mimosa.workshop.vaccine.ddd.domain;

import java.util.List;
import lombok.Data;

@Data
public class Appointment {

  Long id;
  String nhiNo;
  String phoneNo;
  List<Choose> chooses;
  List<Cancel> cancels;
}
