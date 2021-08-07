package com.example.kotlinapitemplate.error

import com.example.kotlinapitemplate.error.exception.BusinessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(BusinessException::class)
    fun handle(e: BusinessException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(e.getCode(), e.getErrorMessage()), e.getHttpStatus())
    }
}