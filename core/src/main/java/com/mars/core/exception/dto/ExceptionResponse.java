package com.mars.core.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mars.core.exception.vo.CustomException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(Include.NON_EMPTY)
public class ExceptionResponse {

  private String message;
  private HttpStatus status;
  private List<FieldResponse> fieldResponses;

  private ExceptionResponse(final CustomException exception, final List<FieldResponse> fieldResponses) {
    this.message = exception.getMessage();
    this.status = exception.getStatus();
    this.fieldResponses = fieldResponses;
  }

  private ExceptionResponse(final CustomException exception) {
    this.message = exception.getMessage();
    this.status = exception.getStatus();
    this.fieldResponses = new ArrayList<>();
  }

  public static ExceptionResponse of(final CustomException exception, final BindingResult bindingResult) {
    return new ExceptionResponse(exception, FieldResponse.of(bindingResult));
  }

  public static ExceptionResponse of(final CustomException exception) {
    return new ExceptionResponse(exception);
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class FieldResponse {
    private String field;
    private String value;
    private String reason;

    private FieldResponse(String field, String value, String reason) {
      this.field = field;
      this.value = value;
      this.reason = reason;
    }

    public static FieldResponse of(FieldError fieldError) {
      return new FieldResponse(fieldError.getField(),
                               Optional.ofNullable(fieldError.getRejectedValue())
                                       .map(Object::toString)
                                       .orElse(StringUtils.EMPTY),
                               fieldError.getDefaultMessage());
    }

    public static List<FieldResponse> of(final BindingResult bindingResult) {
      return bindingResult.getFieldErrors()
                          .stream()
                          .map(FieldResponse::of)
                          .collect(Collectors.toList());
    }
  }
}
