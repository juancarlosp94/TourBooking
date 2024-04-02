package com.group5.tourbooking.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        logger.error("Se ha producido una Exception", ex);
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ha ocurrido un error interno. Por favor, inténtalo de nuevo más tarde.", System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
}
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex) {
        // Registrar el error completo con stack trace en tus logs
        logger.error("Se ha producido una NullPointerException", ex);
        // Crear una respuesta amigable para el cliente
        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ha ocurrido un error interno. Por favor, inténtalo de nuevo más tarde.", System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<String> handleArrayIndexOutOfBounds(ArrayIndexOutOfBoundsException ex) {
        logger.error("Se ha producido una ArrayIndexOutOfBoundsException", ex);
        return new ResponseEntity<>("Índice fuera de los límites del array.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        logger.error("Se ha producido una ResourceNotFoundException", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
        logger.error("Se ha producido una BadRequestException", ex);
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(SameNameExeption.class)
    public ResponseEntity<ErrorResponse> handleSameNameException(SameNameExeption ex) {
        logger.error("Se ha producido una SameNameExeption", ex);
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Ya existe un Tour con este Nombre, ingresar uno diferente", System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDatabaseConstraintsViolation(DataIntegrityViolationException ex) {
        logger.error("Se ha producido una DataIntegrityViolationException", ex);
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "El registro no cumple con las restricciones de la base de datos.", System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotAllowed(MethodNotAllowedException ex) {
        logger.error("Se ha producido una MethodNotAllowedException", ex);
        ErrorResponse error = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFormatException(InvalidFormatException ex) {
        logger.error("Se ha producido una InvalidFormatException", ex);
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "El archivo no es una imagen válida. Solo se aceptan formatos JPG, JPEG y PNG.", System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UserNotFoundUuid.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundUuid(UserNotFoundUuid ex){
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "No se encontró un usuario con el UUID pasado", System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        return errors;
    }

}
