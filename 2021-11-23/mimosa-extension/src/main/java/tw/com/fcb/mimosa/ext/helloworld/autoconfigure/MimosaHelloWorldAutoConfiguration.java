package tw.com.fcb.mimosa.ext.helloworld.autoconfigure;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableConfigurationProperties(MimosaHelloWorldProperties.class)
@RequiredArgsConstructor
public class MimosaHelloWorldAutoConfiguration {

  final MimosaHelloWorldProperties properties;

  @PostConstruct
  public void init() {
    log.info("Hello {}", properties.getName());
  }

}
