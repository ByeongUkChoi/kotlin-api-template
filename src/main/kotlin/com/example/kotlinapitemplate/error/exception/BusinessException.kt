package com.example.kotlinapitemplate.error.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

class BusinessException constructor(private val errorCode: ErrorCode) : RuntimeException() {

    fun getCode(): Int {
        return errorCode.code
    }

    fun getErrorMessage(): String {
        return errorCode.message
    }

    fun getHttpStatus(): HttpStatus {
        return errorCode.httpStatus
    }
}
