package com.mars.common.exception.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

  private String message;
  private HttpStatus status;
  private List<FieldError> errors;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class FieldError {
    private String field;
    private String value;
    private String reason;
  }
}
