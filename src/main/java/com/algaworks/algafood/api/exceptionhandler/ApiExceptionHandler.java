package com.algaworks.algafood.api.exceptionhandler;

import com.algaworks.algafood.core.validation.ValidacaoException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o " +
            "proplema persistir, entre em contato com o administrador do sistema.";

    @ExceptionHandler(EntidadeNaoEncontradaException.class) /*também subclasses*/
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
        HttpStatus statusNotFound = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        final Problem problem = createProblemType(statusNotFound, problemType, ex.getMessage()).userMessage(ex.getMessage()).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), statusNotFound, request);
    }

    @ExceptionHandler(NegocioException.class) /*também subclasses*/
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {

        final HttpStatus statusBadRequest = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.ERRO_NEGOCIO;
        final Problem problem = createProblemType(statusBadRequest, problemType, ex.getMessage()).userMessage(ex.getMessage()).build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), statusBadRequest, request);

    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){

        final HttpStatus statusConflict = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
        final Problem problem = createProblemType(statusConflict, problemType, ex.getMessage()).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

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
                    .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                    .timeStamp(OffsetDateTime.now())
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
        final Problem problem = createProblemType(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    protected ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {
        ProblemType problemType = ProblemType.CORPO_NAO_LEGIVEL;

        final String path = getPath(ex.getPath());

        String detail = String.format("A propriedade '%s' recebeu o valor '%s' que é de um tipo inválido. " +
                "Corrija e informe um valor compatível com o tipo '%s'.", path, ex.getValue(), ex.getTargetType().getSimpleName());

        final Problem problem = createProblemType(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }


    protected ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        final String path = getPath(ex.getPath());

        ProblemType problemType = ProblemType.CORPO_NAO_LEGIVEL;

        String detail = null;

        if(ex instanceof IgnoredPropertyException){
            detail = String.format("A propriedade '%s'" +
                    " está indisponível.", path);
        } else if(ex instanceof UnrecognizedPropertyException){
            detail = String.format("A propriedade '%s' é desconhecida." +
                    " Corrija e informe uma propriedade existente.", path);
        }

        final Problem problem = createProblemType(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if(ex instanceof MethodArgumentTypeMismatchException){
            return handleMethodArgumentTypeMismacthException((MethodArgumentTypeMismatchException) ex, headers, status, request);
        }
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    protected ResponseEntity<Object> handleMethodArgumentTypeMismacthException(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String campoUrl = ex.getName();
        final Object valorEsperado = ex.getValue();
        /*Pode vir nulo, então é usado Objects.requireNonNull*/
        final String tipoRequerido = java.util.Objects.requireNonNull(ex.getRequiredType()).getSimpleName();

        final ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe um valor" +
                " compatível com o tipo '%s'.", campoUrl, valorEsperado, tipoRequerido);


        final Problem problema = createProblemType(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

        return handleExceptionInternal(ex, problema, headers, status, request);

    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {

        final ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
        final String requestURL = ex.getRequestURL();
        String detail = String.format("O recurso '%s' é inexistente", requestURL);
        final Problem problema = createProblemType(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherAllException (Exception ex, WebRequest request){

        final HttpHeaders headers = new HttpHeaders();
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        final ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
        String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;
        ex.printStackTrace();

        Problem problema = createProblemType(status, problemType, detail).build();

        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex,
                                                            BindingResult bindingResult, HttpHeaders headers,
                                                            HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.DADOS_INVALIDOS;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return Problem.Object.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        Problem problem = createProblemType(status, problemType, detail)
                .userMessage(detail)
                .objects(problemObjects)
                .build();

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(ValidacaoException.class)
    protected ResponseEntity<Object> handleValidacaoException(ValidacaoException ex, WebRequest request){

        return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    private Problem.ProblemBuilder createProblemType (HttpStatus status, ProblemType problemType, String detail){

        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .timeStamp(OffsetDateTime.now())
                .detail(detail);
    }


    private static String getPath(List<JsonMappingException.Reference> ex) {
        return ex
                .stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));
    }

}
