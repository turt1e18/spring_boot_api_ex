package com.example.mvc.advice

import com.example.mvc.controller.put.PutApiController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

//@ControllerAdvice page를 정의하는 Controller 어노테이션이 붙은 컨트롤러 파일에서 에러발생시 실행됨
//@RestControllerAdvice// RestController 어노테이션이 붙은 컨트롤러 파일에서 에러발생시 실행됨
class GlobalControllerAdvice {  //(basePackageClasses = [컨트롤러이름::class]) 으로 지정시 지정된 곳의 예외만 처리 가능


    @ExceptionHandler(value = [RuntimeException::class])
    fun exception(e:RuntimeException): String {
        return "Server Error"
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e:IndexOutOfBoundsException): ResponseEntity<String> { //String 타입으로 가면 200 으로 내려감 그래서 ResponseEntity 로 정의함
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }
}