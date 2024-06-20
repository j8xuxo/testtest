package tw.com.fcb.mimosa.workshop.vaccine.ddd.application.usecase;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tw.com.fcb.mimosa.ext.ddd.application.ApplicationUseCase;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.application.command.ReplaceResidentProfile;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.AppointmentRepository;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.domain.event.ResidentProfileReplaced;

@Service
@RequiredArgsConstructor
public class ReplaceResidentProfileUseCase implements
  ApplicationUseCase<ReplaceResidentProfile, Void> {

  final AppointmentRepository repository;
  final ApplicationEventPublisher publisher;

  @Override
  public Void execute(
    @NotNull @Valid ReplaceResidentProfile command) {
    var domain = repository.findById(command.getId());
    if (StringUtils.hasText(command.getPhoneNo())) {
      domain.setPhoneNo(command.getPhoneNo());
      repository.save(domain);
      var event = new ResidentProfileReplaced();
      event.setResidentId(domain.getId());
      event.setPhoneNo(domain.getPhoneNo());
      publisher.publishEvent(event);
    }
    return null;
  }
}
