package com.algaworks.algafood.api.exceptionhandler;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class) /*também subclasses*/
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {

        HttpStatus statusNotFound = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        final Problem problem = createProblemType(statusNotFound, problemType, ex.getMessage()).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), statusNotFound, request);
    }

    @ExceptionHandler(NegocioException.class) /*também subclasses*/
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {

        final HttpStatus statusBadRequest = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        final Problem problem = createProblemType(statusBadRequest, problemType, ex.getMessage()).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), statusBadRequest, request);

    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){

        final HttpStatus statusConflict = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
        final Problem problem = createProblemType(statusConflict, problemType, ex.getMessage()).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), statusConflict, request);
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

    /**
     *  Algumas variáveis do parâmetro não precisam ser definidas pois a superclasse já atribiu valores
     * @param ex the exception
     * @param headers the headers to be written to the response
     * @param status the selected response status.
     * @param request the current request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        final Throwable rootCause = ExceptionUtils.getRootCause(ex);

        if(rootCause instanceof InvalidFormatException){
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        ProblemType problemType = ProblemType.CORPO_NAO_LEGIVEL;
        String detail = "O corpo da requisição está inválido. Verifique o possível erro de sintaxe.";
        final Problem problem = createProblemType(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.CORPO_NAO_LEGIVEL;

        final String path = getCollect(ex.getPath());

        String detail = String.format("A propriedade '%s' recebeu o valor '%s' que é de um tipo inválido. " +
                "Corrija e informe um valor compatível com o tipo '%s'.", path, ex.getValue(), ex.getTargetType().getSimpleName());

        final Problem problem = createProblemType(status, problemType, detail).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }


    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        final String path = getCollect(ex.getPath());

        ProblemType problemType = ProblemType.CORPO_NAO_LEGIVEL;

        String detail = null;

        if(ex instanceof IgnoredPropertyException){
            detail = String.format("A propriedade '%s'" +
                    " está indisponível.", path);
        } else if(ex instanceof UnrecognizedPropertyException){
            detail = String.format("A propriedade '%s' é desconhecida." +
                    " Corrija e informe uma propriedade existente.", path);
        }

        final Problem problem = createProblemType(status, problemType, detail).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemType (HttpStatus status, ProblemType problemType, String detail){

        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }


    private static String getCollect(List<JsonMappingException.Reference> ex) {
        return ex
                .stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));
    }

}
