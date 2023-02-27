package com.algaworks.algafood.api.exceptionhandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class) /*também subclasses*/
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {

        HttpStatus statusNotFound = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        final Problem problem2 = createProblemType(statusNotFound, problemType, ex.getMessage()).build();
//        Problem problem = Problem.builder()
//                .status(statusNotFound.value())
//                .type("http://localhost:8080/entidade-nao-encontrada")
//                .title("Entidade não encontrada")
//                .detail(ex.getMessage())
//                .build();

        return handleExceptionInternal(ex, problem2, new HttpHeaders(), statusNotFound, request);

    }

    @ExceptionHandler(NegocioException.class) /*também subclasses*/
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        /**
         * se o (Object) body não for nullo, ele é do tipo Problem (Especificação RFC 7807) e não é necessário
         * criar um novo objeto.
         * Se ele for nullo, a exceção foi tratada pelo SpringMVC interno, o corpo da exceção vem nulla.
         * Se ele for apenas uma String também um problem vai ser instanciado e construído a partir da RFC 7807
         **/
        if(body == null || body instanceof String) {
            body = Problem.builder()
                    .title(body instanceof String ? (String) body : status.getReasonPhrase())
                    .status(status.value())
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemType (HttpStatus status, ProblemType problemType, String detail){

        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }
}
