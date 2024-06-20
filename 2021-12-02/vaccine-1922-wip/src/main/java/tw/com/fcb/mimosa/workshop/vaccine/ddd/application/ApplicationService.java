package tw.com.fcb.mimosa.workshop.vaccine.ddd.application;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.assembler.CommandAssembler;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.CancelVaccine;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.ChooseVaccine;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.MakeAppointment;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.Appointment;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.AppointmentRepository;

@Service
@RequiredArgsConstructor
public class ApplicationService {

  final AppointmentRepository repository;
  final CommandAssembler assembler;

  public long makeAppointment(MakeAppointment command){
    return repository.save(assembler.toDomain(command));
  }

  public void chooseVaccine(ChooseVaccine command) {
    var domain = repository.findById(command.getId());
    var append = command.getVaccines().stream()
      .map(assembler::toChoose)
      .collect(Collectors.toList());
    domain.getChooses().addAll(append);
    repository.save(domain);
  }

  public void cancelVaccine(CancelVaccine command) {
  }
}
