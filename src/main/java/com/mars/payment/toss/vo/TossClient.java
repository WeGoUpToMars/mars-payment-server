package com.mars.payment.toss.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "toss")
public class TossClient {
  private String clientKey;
  private String clientSecret;
}
