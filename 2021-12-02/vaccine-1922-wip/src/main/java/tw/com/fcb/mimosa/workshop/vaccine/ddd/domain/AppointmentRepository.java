package tw.com.fcb.mimosa.workshop.vaccine.ddd.domain;

public interface AppointmentRepository {

  long save(Appointment domain);

  Appointment findById(long id);
}
