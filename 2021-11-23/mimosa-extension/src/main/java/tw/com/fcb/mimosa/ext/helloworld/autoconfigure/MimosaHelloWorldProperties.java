package tw.com.fcb.mimosa.ext.helloworld.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "mimosa.hello-world")
public class MimosaHelloWorldProperties {

  String name = "World";

}
