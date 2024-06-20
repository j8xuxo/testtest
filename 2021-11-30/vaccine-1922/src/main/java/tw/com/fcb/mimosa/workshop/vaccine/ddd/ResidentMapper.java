package tw.com.fcb.mimosa.workshop.vaccine.ddd;

import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import tw.com.fcb.mimosa.workshop.vaccine.crud.repository.CrudCancelEntity;
import tw.com.fcb.mimosa.workshop.vaccine.crud.repository.CrudChooseEntity;
import tw.com.fcb.mimosa.workshop.vaccine.crud.repository.CrudResidentEntity;
import tw.com.fcb.mimosa.workshop.vaccine.crud.web.ResidentDto;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.repository.CancelEntity;
import tw.com.fcb.mimosa.workshop.vaccine.ddd.repository.ChooseEntity;
import tw.com.fcb.mimosa.workshop.vaccine.sharedkernel.Vaccine;

@Mapper
public interface ResidentMapper {

//  CrudResidentEntity toEntity(ResidentDto dto);

  default ChooseEntity toChooseEntity(Vaccine vaccine) {
    var entity = new ChooseEntity();
    entity.setChooseTime(LocalDateTime.now());
    entity.setVaccine(vaccine);
    return entity;
  }

  default CancelEntity toCancelEntity(Vaccine vaccine) {
    var entity = new CancelEntity();
    entity.setCancelTime(LocalDateTime.now());
    entity.setVaccine(vaccine);
    return entity;
  }
}
