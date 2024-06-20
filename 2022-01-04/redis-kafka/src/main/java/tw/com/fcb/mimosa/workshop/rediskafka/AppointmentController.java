package tw.com.fcb.mimosa.workshop.rediskafka;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.fcb.mimosa.ext.cache.StringKeyRedisTemplate;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
class AppointmentController {

  final StringKeyRedisTemplate redisTemplate;
  final KafkaTemplate<String, Object> kafkaTemplate;
  final AppointmentMapper mapper;

  @PostMapping
  String makeAppointment(
    @RequestBody MakeAppointmentRequest request) {
    var id = RandomStringUtils
      .random(128, true, true);

    var data = mapper.toData(request);
    data.getStatus().add("Accepted at: " + LocalDateTime.now());
    redisTemplate.opsForValue().set(id, data);

    var message = mapper
      .toMessage(request);
    message.setId(id);
    kafkaTemplate.send("appointment", message);

    return id;
  }

  @GetMapping("/{id}")
  Map<MakeAppointmentData, Long> getAppointment(@PathVariable String id) {
    var data = (MakeAppointmentData) redisTemplate.opsForValue().get(id);
    if (data == null) {
      return Map.of();
    }
    var expire = redisTemplate.getExpire(id, TimeUnit.SECONDS);
    return Map.of(data, expire);
  }
}
