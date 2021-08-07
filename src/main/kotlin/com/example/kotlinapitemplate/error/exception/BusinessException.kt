package com.example.kotlinapitemplate.error.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException

class BusinessException : RuntimeException {
    private var errorCode: ErrorCode

    constructor(errorCode: ErrorCode) {
        this.errorCode = errorCode
    }

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
