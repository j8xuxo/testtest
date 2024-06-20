package tw.com.fcb.mimosa.workshop.vaccine.ddd.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/residents")
public interface ResidentApi {

  @PostMapping
  long makeAppointment(
    @RequestBody MakeAppointmentRequest request);

  @PutMapping("/{id}/vaccines")
  void chooseVaccine(@PathVariable("id") long id,
    @RequestBody ChooseVaccineRequest request);

  @DeleteMapping("/{id}/vaccines")
  void cancelVaccine(@PathVariable("id") long id,
    @RequestBody CancelVaccineRequest request);
}
