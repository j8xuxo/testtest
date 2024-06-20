package tw.com.fcb.mimosa.workshop.rediskafka;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tw.com.fcb.mimosa.ext.cache.StringKeyRedisTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppointmentConsumer {

  final StringKeyRedisTemplate redisTemplate;

  @KafkaListener(topics = "appointment")
  void onMessage(MakeAppointmentMessage message) throws InterruptedException {
    // 等 30 秒, 每一秒把剩餘秒數印出
    // 30秒完成後, 把 message 印出
    for (int i = 30; i > 0; i--) {
      log.info("{}", i);
      TimeUnit.SECONDS.sleep(1);
    }
    log.info("Received message from Kafka: {}", message);
    var data = (MakeAppointmentData) redisTemplate.opsForValue()
      .get(message.getId());
    data.getStatus().add("Finished at " + LocalDateTime.now());
    redisTemplate.opsForValue().set(message.getId(), data, 1, TimeUnit.MINUTES);
  }

}
