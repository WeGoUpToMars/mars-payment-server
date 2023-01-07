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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CustomException.class)
  protected ResponseEntity<ExceptionResponse> handleCustomException(CustomException e) {
    e.printStackTrace();
    return new ResponseEntity<>(ExceptionResponse.of(e),
                                e.getStatus());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    e.printStackTrace();
    return new ResponseEntity<>(ExceptionResponse.of(CommonExceptionInfo.INVALID_INPUT_VALUE.exception(), e.getBindingResult()),
                                HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
    e.printStackTrace();
    return new ResponseEntity<>(ExceptionResponse.of(CommonExceptionInfo.INVALID_TYPE_VALUE.exception()),
                                HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    e.printStackTrace();
    return new ResponseEntity<>(ExceptionResponse.of(CommonExceptionInfo.METHOD_NOT_ALLOWED.exception()),
                                HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpClientErrorException.class)
  protected ResponseEntity<ExceptionResponse> handleHttpClientErrorException(HttpClientErrorException e) {
    e.printStackTrace();
    return new ResponseEntity<>(ExceptionResponse.of(CustomException.of(e.getMessage(), e.getStatusCode())),
                                e.getStatusCode());
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ExceptionResponse> handleUnknownException(Exception e) {
    log.error("unknownException", e);
    return new ResponseEntity<>(ExceptionResponse.of(CommonExceptionInfo.UNKNOWN_EXCEPTION.exception()),
                                CommonExceptionInfo.UNKNOWN_EXCEPTION.getStatus());
  }
}
