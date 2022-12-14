package com.mars.common.exception.handler;

import com.mars.common.constant.CommonExceptionInfo;
import com.mars.common.exception.dto.ExceptionResponse;
import com.mars.common.exception.vo.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomException.class)
  protected ResponseEntity<ExceptionResponse> handleCustomException(CustomException e) {
    log.error("customException", e);
    return new ResponseEntity<>(ExceptionResponse.builder()
                                                 .message(e.getMessage())
                                                 .build(),
                                e.getStatus());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    log.error("methodArgumentNotValidException", e);
    return new ResponseEntity<>(ExceptionResponse.builder()
                                                 .message(e.getMessage())
                                                 .build(),
                                HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
    log.error("methodArgumentTypeMismatchException", e);
    return new ResponseEntity<>(ExceptionResponse.builder()
                                                 .message(e.getMessage())
                                                 .build(),
                                HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    log.error("httpRequestMethodNotSupportedException", e);
    return new ResponseEntity<>(ExceptionResponse.builder()
                                                 .message(e.getMessage())
                                                 .build(),
                                HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ExceptionResponse> handleUnknownException(Exception e) {
    log.error("unknownException", e);
    return new ResponseEntity<>(ExceptionResponse.builder()
                                                 .message(CommonExceptionInfo.UNKNOWN_EXCEPTION.getMessage())
                                                 .build(),
                                CommonExceptionInfo.UNKNOWN_EXCEPTION.getStatus());
  }
}
