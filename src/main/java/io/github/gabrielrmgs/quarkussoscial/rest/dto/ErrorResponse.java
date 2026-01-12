package io.github.gabrielrmgs.quarkussoscial.rest.dto;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;

public class ErrorResponse {

    private String errorMessage;
    private Collection<FieldError> errors;

    public ErrorResponse(String errorMessage, Collection<FieldError> errors) {
        this.errorMessage = errorMessage;
        this.errors = errors;
    }

    public static <T> ErrorResponse createFromValidation(Set<ConstraintViolation<T>> violations) {

        List<FieldError> errors = violations.stream()
                .map(vc -> new FieldError(vc.getPropertyPath().toString(), vc.getMessage()))
                .collect(Collectors.toList());

        String message = "Violation Error";

        ErrorResponse errorResponse = new ErrorResponse(message, errors);

        return errorResponse;

    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Collection<FieldError> getErrors() {
        return this.errors;
    }

    public void setErrors(Collection<FieldError> errors) {
        this.errors = errors;
    }

}
