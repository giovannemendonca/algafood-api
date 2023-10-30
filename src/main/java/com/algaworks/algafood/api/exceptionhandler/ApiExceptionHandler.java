package com.algaworks.algafood.api.exceptionhandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorrência de erro interno no sistema. Tente novamente e se o problema persistir, entre em contato "
          + "com o administrador do sistema.";

  @ExceptionHandler(EntidadeNaoEncontradaException.class)
  public ResponseEntity<?> handleEntidadeNaoEncontradaException(
          EntidadeNaoEncontradaException ex, WebRequest request) {

    HttpStatus status = HttpStatus.NOT_FOUND;
    ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
    String detail = ex.getMessage();

    Problem problem = createProblemBuilder(status, problemType, detail).build();

    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(EntidadeEmUsoException.class)
  public ResponseEntity<?> handleEntidadeEmUsoException(
          EntidadeEmUsoException ex, WebRequest request) {

    HttpStatus status = HttpStatus.CONFLICT;
    ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
    String detail = ex.getMessage();

    Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage(detail)
            .build();

    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {

    HttpStatus status = HttpStatus.BAD_REQUEST;
    ProblemType problemType = ProblemType.ERRO_NEGOCIO;
    String detail = ex.getMessage();

    Problem problem = createProblemBuilder(status, problemType, detail).build();

    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleUncaught(Exception ex, WebRequest request) {

    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    ProblemType problemType = ProblemType.ERROR_DE_SISTEMA;
    String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;

    Problem problem = createProblemBuilder(status, problemType, detail).build();

    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
          HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

    Throwable rootCause = ExceptionUtils.getRootCause(ex);

    if (rootCause instanceof InvalidFormatException) {
      return handleInvalidFormat((InvalidFormatException) rootCause, headers, statusCode, request);
    } else if (rootCause instanceof PropertyBindingException) {
      return handlePropertyBinding((PropertyBindingException) rootCause, headers, statusCode, request);
    }

    HttpStatus status = HttpStatus.BAD_REQUEST;
    ProblemType problemType = ProblemType.MESSAGEM_INCOMPREENSIVEL;
    String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

    Problem problem = createProblemBuilder(status, problemType, detail).build();


    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
          Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
    if (body == null) {
      body = Problem.builder()
              .title(((HttpStatus) statusCode).getReasonPhrase())
              .status(statusCode.value())
              .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
              .timestamp(LocalDateTime.now())
              .build();
    } else if (body instanceof String) {
      body = Problem.builder()
              .title((String) body)
              .status(statusCode.value())
              .timestamp(LocalDateTime.now())
              .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
              .build();
    }

    return super.handleExceptionInternal(ex, body, headers, statusCode, request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    if (ex instanceof MethodArgumentTypeMismatchException) {
      return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
    }
    return super.handleTypeMismatch(ex, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

    HttpStatus status = (HttpStatus) statusCode;
    ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
    String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());
    Problem problem = createProblemBuilder(status, problemType, detail).build();

    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  private ResponseEntity<Object> handleInvalidFormat(
          InvalidFormatException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

    String path = ex.getPath().stream()
            .map(ref -> ref.getFieldName())
            .collect(Collectors.joining("."));


    HttpStatus status = HttpStatus.BAD_REQUEST;
    ProblemType problemType = ProblemType.MESSAGEM_INCOMPREENSIVEL;
    String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
                    + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
            path,
            ex.getValue(),
            ex.getTargetType().getSimpleName());

    Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
            .build();

    return handleExceptionInternal(ex, problem, headers, statusCode, request);
  }

  private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
          MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

    HttpStatus status = (HttpStatus) statusCode;
    ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
    String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
                    + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
            ex.getName(),
            ex.getValue(),
            ex.getRequiredType().getSimpleName());

    Problem problem = createProblemBuilder(status, problemType, detail).build();

    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
                                                       HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

    HttpStatus status = (HttpStatus) statusCode;
    String path = joinPath(ex.getPath());
    ProblemType problemType = ProblemType.MESSAGEM_INCOMPREENSIVEL;
    String detail = String.format("A propriedade '%s' não existe. "
            + "Corrija ou remova essa propriedade e tente novamente.", path);

    Problem problem = createProblemBuilder(status, problemType, detail)
            .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
            .build();

    return handleExceptionInternal(ex, problem, headers, status, request);
  }

  private Problem.ProblemBuilder createProblemBuilder(HttpStatus status,
                                                      ProblemType problemType, String detail) {


    return Problem.builder()
            .timestamp(LocalDateTime.now())
            .status(status.value())
            .type(problemType.getUri())
            .title(problemType.getTitle())
            .detail(detail);
  }

  private String joinPath(List<JsonMappingException.Reference> path) {
    return path.stream()
            .map(ref -> ref.getFieldName())
            .collect(Collectors.joining("."));

  }


}