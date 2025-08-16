package com.eazybytes.accounts.exception;

import com.eazybytes.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the exception thrown when a customer already exists in the system.
     * Constructs an appropriate error response with relevant details for the exception.
     *
     * @param exception the exception that triggered this handler, encapsulating details about the duplication error
     * @param request the current web request that provides additional context about the API call
     * @return a ResponseEntity containing the error response DTO and a HTTP status code of BAD_REQUEST
     * false verildiğinde: İstemci bilgileri (IP, session vs.) hariç tutulur. Tipik çıktı “uri=/accounts/123” gibi olur.
     * true verilseydi: “uri=/accounts/123;client=127.0.0.1” gibi istemci bilgilerini de ekleyebilirdi.
     */
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception,
                                                                                 WebRequest request) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

}
