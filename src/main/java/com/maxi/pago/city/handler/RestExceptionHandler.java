package com.maxi.pago.city.handler;


import com.maxi.pago.city.error.ErrorDetails;
import com.maxi.pago.city.error.NoContentException;
import com.maxi.pago.city.error.ResourceNotFoundException;
import com.maxi.pago.city.error.ServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException) {
        ErrorDetails rnfDetails = ErrorDetails.Builder
                .newBuilder()
                .status_code(HttpStatus.NOT_FOUND.value())
                .status_message(HttpStatus.NOT_FOUND.getReasonPhrase())
                .detail_parameter(rfnException.getMessage())
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<?> handleResourceServiceUnavailableException(ServiceUnavailableException rfnException) {
        ErrorDetails rnfDetails = ErrorDetails.Builder
                .newBuilder()
                .status_code(HttpStatus.SERVICE_UNAVAILABLE.value())
                .status_message(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase())
                .detail_parameter(rfnException.getMessage())
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<?> handleNoContentException(NoContentException rfnException) {
        ErrorDetails rnfDetails = ErrorDetails.Builder
                .newBuilder()
                .status_code(HttpStatus.NO_CONTENT.value())
                .status_message(HttpStatus.NO_CONTENT.getReasonPhrase())
                .detail_parameter(rfnException.getMessage())
                .build();
        return new ResponseEntity<>(rnfDetails, HttpStatus.NO_CONTENT);
    }

}
