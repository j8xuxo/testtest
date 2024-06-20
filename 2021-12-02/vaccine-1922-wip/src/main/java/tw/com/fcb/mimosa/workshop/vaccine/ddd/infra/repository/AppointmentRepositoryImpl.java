package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.Appointment;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.AppointmentRepository;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.assembler.ResidentAssembler;

@Repository
@RequiredArgsConstructor
public class AppointmentRepositoryImpl implements AppointmentRepository {

  final ResidentRepository jpaRepository;
  final ResidentAssembler assembler;

  @Override
  public long save(Appointment domain) {
    return jpaRepository
      .save(assembler.toEntity(domain))
      .getId();
  }

  @Override
  public Appointment findById(long id) {
    return null;
  }
}
