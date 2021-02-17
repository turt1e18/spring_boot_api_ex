package com.example.mvc.controller.response

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    @GetMapping("") // GET http://localhost:8080/api/response
    fun getMapping(@RequestParam age: Int?): ResponseEntity<String> { //age 값이 필수적임을 required로 표현 true 값이 기본형이며 true 값일 시 반드시 필요함

        return age?.let {                                                                      //코틀린 버전
            if(age<20)
                return ResponseEntity.status(400).body("age 값이 20 미만입니다.")
            return ResponseEntity.ok("OK")
        }?: kotlin.run {
            return ResponseEntity.status(400).body("age 값이 누락이거나 null 입니다.")
        }

       /* if(age == null){                                                              //java 버전
            return ResponseEntity.status(400).body("age 값이 누락이거나 null 입니다.")
        }
        if(age < 20){
            return ResponseEntity.status(400).body("age 값이 20 미만입니다.")
        }
        */
    }

    @PostMapping("")
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
        return ResponseEntity.status(200).body(userRequest)

    }

    @PutMapping("")
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)

    }

    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id:Int): ResponseEntity<Any> {
        return ResponseEntity.status(500).body(null)
    }


}