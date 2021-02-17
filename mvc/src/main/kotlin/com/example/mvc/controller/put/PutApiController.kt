package com.example.mvc.controller.put

import com.example.mvc.model.http.Result
import com.example.mvc.model.http.UserRequest
import com.example.mvc.model.http.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mapping") //put mapping
    fun putMapping(): String {
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping - put method"
    }

    @PutMapping(path = ["put-mapping/object"]) //PUT의 경우 request가 있으면 보내주고 없으면 생성 후 보냄
    fun putMappingObject(@Valid @RequestBody userRequest: UserRequest, bindingResult: BindingResult) : ResponseEntity<String> { //부분검증할때 @Valid 를 이용

        val msg = StringBuilder()
        if(bindingResult.hasErrors()){ //컨트롤러에 들어와서 값을 검증한 후 결과값을 서버에 에러띄우는게 아니라 요청값이 잘못된것을 클라이언트에게 에러메세지를 출력함
            bindingResult.allErrors.forEach{
                val field = it as FieldError
                val message = it.defaultMessage
                msg.append(field.field+ " : " + message+"\n")
            }
            return ResponseEntity.badRequest().body(msg.toString())
        }
        return ResponseEntity.ok("")

        /* return UserResponse().apply {
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply {
            this.descriptin = "~~~~~"
        }.apply {
            val userList = mutableListOf<UserRequest>()
            userList.add(userRequest)
            userList.add(UserRequest().apply {
                this.name = "a"
                this.age = 10
                this.email = "a@ccc.com"
                this.address = "a aaaaa"
                this.phoneNumber = "010-2123-3123"
            })
            userList.add(UserRequest().apply {
                this.name = "b"
                this.age = 20
                this.email = "b@ccc.com"
                this.address = "b bbbbb"
                this.phoneNumber = "010-2233-3123"
            })
            this.userRequest = userList
        }
        */
    }


    /* 위의 데이터 케이스
        "result" : { //객체
            "result_code" : "OK"
            "result_message" : "성공"
            },
        "description" : "~~~~~~", //변수
        "user" : [ //배열
            {
                "name" : "steve",
                "age" : "10",
                "address" : "",
                "phoneNumber" : ""
            },
            {
                "name" : "a",
                "age" : "20",
                "address" : "",
                "phoneNumber" : ""
            },
            {
                "name" : "b",
                "age" : "30",
                "address" : "",
                "phoneNumber" : ""
            },


        ]
    */
}