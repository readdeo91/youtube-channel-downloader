package hu.readdeo.youtube.channel.downloader.quality;

import jakarta.annotation.PostConstruct;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Slf4j
@Configuration
public class QualityConfig {

  private Properties yamlProps;

  @PostConstruct
  private void qualityLoader() {
    YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
    yaml.setResources(new ClassPathResource("quality.yaml"));
    this.yamlProps = yaml.getObject();
  }

  public String getQualitySwitch(String name) {
    return yamlProps.get(name).toString();
  }
}
