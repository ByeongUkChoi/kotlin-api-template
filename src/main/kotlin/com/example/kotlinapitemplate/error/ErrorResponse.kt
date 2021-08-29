package com.example.kotlinapitemplate.error

import com.example.kotlinapitemplate.error.exception.ErrorCode

data class ErrorResponse(
    val code: Int,
    val message: String
) {
    constructor(errorCode: ErrorCode) : this(errorCode.code, errorCode.message)
}
