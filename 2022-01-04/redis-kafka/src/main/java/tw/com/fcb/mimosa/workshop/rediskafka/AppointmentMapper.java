package tw.com.fcb.mimosa.workshop.rediskafka;

import org.mapstruct.Mapper;

@Mapper
public interface AppointmentMapper {

  MakeAppointmentMessage
      toMessage(MakeAppointmentRequest request);

  MakeAppointmentData
    toData(MakeAppointmentRequest request);
}
