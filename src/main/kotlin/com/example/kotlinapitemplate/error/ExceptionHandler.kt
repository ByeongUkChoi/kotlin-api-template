package com.example.kotlinapitemplate.error

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.RuntimeException

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler
    fun handle(exception: RuntimeException) {
    }
}