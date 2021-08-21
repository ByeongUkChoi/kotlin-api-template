package com.example.kotlinapitemplate.error.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val code: Int, val httpStatus: HttpStatus, val message: String) {
    MISSING_REQUIRED_VALUES(1001, HttpStatus.BAD_REQUEST, "필수 값이 누락되었습니다."),
    MISSING_REQUEST_HEADER(1002, HttpStatus.FORBIDDEN, "헤더 값이 누락되었습니다."),
    NOT_FOUND_ORDER(2001, HttpStatus.NOT_FOUND, "주문 정보를 찾을 수 없습니다.")
}