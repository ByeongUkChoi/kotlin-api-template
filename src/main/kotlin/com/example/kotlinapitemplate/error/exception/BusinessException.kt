package com.example.kotlinapitemplate.error.exception

import java.lang.RuntimeException

class BusinessException : RuntimeException {
    constructor(errorCode: ErrorCode)
}
