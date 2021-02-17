package com.example.mvc.controller.exception

import com.example.mvc.model.http.Error
import com.example.mvc.model.http.ErrorResponse
import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@RestController
@RequestMapping("api/exception")
class ExceptionController {

    @GetMapping("/hello")
    fun hello(){
        /*if(true){
            throw RuntimeException("강제 exception 발생!")
        }
         */

        val list = mutableListOf<String>()
        val temp = list[0]
    }

    @GetMapping("")
    fun get(
            @NotBlank
            @Size(min=2,max=6)
            @RequestParam name:String,

            @Min(2)
            @RequestParam age:Int
    ): String {
        println(name)
        println(age)
        return "$name $age"
    }

    @PostMapping("")
    fun post(@Valid @RequestBody userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest

    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class]) //advice를 타지않고 컨트롤러 내부에서 처리해서 해당 컨트롤러 안에서 만 처리 가능
    fun indexOutOfBoundsException(e:IndexOutOfBoundsException): ResponseEntity<String> {
        println("controller exception handler")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error")
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(e:MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<Error>()

        e.bindingResult.allErrors.forEach { errorObject ->
            val error = Error().apply {
                this.field = (errorObject as FieldError).field
                this.message = errorObject.defaultMessage
                this.value = errorObject.rejectedValue
            }
            errors.add(error)
        }
        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "요청에 에러가 발생했습니다."
            this.path = request.requestURI.toString()
            this.timetamp = LocalDateTime.now()
            this.errors = errors
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun constraintViolationException(e:ConstraintViolationException, request:HttpServletRequest) : ResponseEntity<ErrorResponse>{
        val errors = mutableListOf<Error>()

        e.constraintViolations.forEach{
            val error = Error().apply {
                this.field=it.propertyPath.last().name
                this.message=it.message
                this.value=it.invalidValue
            }
            errors.add(error)
        }
        val errorResponse = ErrorResponse().apply {
            this.resultCode = "FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = "요청에 에러가 발생했습니다."
            this.path = request.requestURI.toString()
            this.timetamp = LocalDateTime.now()
            this.errors = errors
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }
}