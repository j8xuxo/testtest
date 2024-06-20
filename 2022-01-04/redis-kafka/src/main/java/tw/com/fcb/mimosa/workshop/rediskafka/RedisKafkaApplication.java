package tw.com.fcb.mimosa.workshop.rediskafka;

import tw.com.fcb.mimosa.Mimosa;
import tw.com.fcb.mimosa.MimosaBootstrap;

@MimosaBootstrap
public class RedisKafkaApplication {

  public static void main(String[] args) {
    Mimosa.bootstrap(RedisKafkaApplication.class, args);
  }
}
