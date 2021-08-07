package com.example.kotlinapitemplate.error.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val code: Int, val httpStatus: HttpStatus, val message: String) {
    NOT_FOUND_ORDER(1001, HttpStatus.NOT_FOUND, "주문 정보를 찾을 수 없습니다.")
}