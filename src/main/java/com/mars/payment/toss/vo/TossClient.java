package com.mars.payment.toss.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.Base64;

@Data
@Configuration
@ConfigurationProperties(prefix = "toss")
public class TossClient {
  private String clientKey;
  private String clientSecret;

  public HttpHeaders getHeader() {
    final HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString((clientSecret + ":").getBytes()));
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }
}
