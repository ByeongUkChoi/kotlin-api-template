package com.example.kotlinapitemplate.error

import com.example.kotlinapitemplate.error.exception.BusinessException
import com.example.kotlinapitemplate.error.exception.ErrorCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.validation.UnexpectedTypeException

@ControllerAdvice
class ExceptionHandler {

    /**
     * 컨트롤러 필수값 누락 예외 처리
     */
    @ExceptionHandler(UnexpectedTypeException::class)
    fun requestParamInvalidExceptionHandle(e: UnexpectedTypeException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(ErrorCode.MISSING_REQUIRED_VALUES.code, ErrorCode.MISSING_REQUIRED_VALUES.message), ErrorCode.MISSING_REQUIRED_VALUES.httpStatus)
    }

    @ExceptionHandler(BusinessException::class)
    fun businessExceptionHandle(e: BusinessException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(e.getCode(), e.getErrorMessage()), e.getHttpStatus())
    }

}