package io.github.gabrielrmgs.quarkussoscial.rest.dto;

public class ErrorResponse {
    private String errorMessage;
    private Integer status;

    public ErrorResponse() {
    }

    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public ErrorResponse(String errorMessage, Integer status) {
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ErrorResponse status(Integer status) {
        setStatus(status);
        return this;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorResponse errorMessage(String errorMessage) {
        setErrorMessage(errorMessage);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " errorMessage='" + getErrorMessage() + "'" +
            "}";
    }
 

}
