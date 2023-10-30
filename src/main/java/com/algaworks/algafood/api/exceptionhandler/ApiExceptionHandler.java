package com.algaworks.algafood.api.exceptionhandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<?> handlerEntidadeNaoEncontradaException(
          EntidadeNaoEncontradaException e, WebRequest request) {

    HttpStatus status = HttpStatus.NOT_FOUND;
    String detail = e.getMessage();
    Problem problem = createProblemBuilder(status, ProblemType.ENTIDADE_NAO_ENCONTRADA, detail).build();

    return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }


  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> handlerNegocioException(
          NegocioException e, WebRequest request) {

    return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }


  @ExceptionHandler(EntidadeEmUsoException.class)
  public ResponseEntity<?> handlerEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {
    return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
          Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {


    if (body == null) {
      body = Problem.builder()
              .status(statusCode.value())
              .title(((HttpStatus) statusCode).getReasonPhrase())
              .detail(ex.getMessage())
              .build();


    } else if (body instanceof String) {
      body = Problem.builder()
              .status(statusCode.value())
              .title(((HttpStatus) statusCode).getReasonPhrase())
              .detail((String) body)
              .build();
    }


    return super.handleExceptionInternal(ex, body, headers, statusCode, request);
  }

  private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {

    return Problem.builder()
            .status(status.value())
            .type(problemType.getUri())
            .title(problemType.getTitle())
            .detail(detail);
  }
}
