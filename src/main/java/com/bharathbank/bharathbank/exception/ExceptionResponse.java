package com.bharathbank.bharathbank.exception;

import java.util.Date;

public class ExceptionResponse {
    private String errorMessage;
    private String requestURI;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    private Date timeStamp;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestedURI) {
        this.requestURI = requestedURI;
    }
}
