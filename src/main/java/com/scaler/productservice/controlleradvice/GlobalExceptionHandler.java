package com.scaler.productservice.controlleradvice;

import com.scaler.productservice.dto.ErrorDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/*Note: Global exception handler for all controllers can be defined here. */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ErrorDto handleNullPointerExceptionGlobal()
    {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("something went wrong globally.");
        errorDto.setStatus("FATAL ERROR");
        return errorDto;
    }
}
